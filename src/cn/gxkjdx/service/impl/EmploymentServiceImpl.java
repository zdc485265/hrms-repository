package cn.gxkjdx.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gxkjdx.mapper.EmploymentMapper;
import cn.gxkjdx.mapper.InterviewMapper;
import cn.gxkjdx.mapper.JobMapper;
import cn.gxkjdx.mapper.AdminMapper;
import cn.gxkjdx.mapper.RecruitMapper;
import cn.gxkjdx.mapper.ResumeMapper;
import cn.gxkjdx.service.EmploymentService;
import cn.gxkjdx.util.Page;

@Service
public class EmploymentServiceImpl implements EmploymentService{
	
	
	@Autowired
	EmploymentMapper employmentMapper;

	@Autowired
	InterviewMapper interviewMapper;
	@Autowired
	ResumeMapper resumeMapper;
	
	@Autowired
	JobMapper jobMapper;
	
	@Autowired
	AdminMapper adminMapper;
	
	@Autowired
	RecruitMapper recruitMapper;
	/**
	 * 获取page
	 */
	@Override
	public Page findResumeByPage(Map<String, Object> entity,String indexo, String sizeo) {
		int index,size;
		
		//判空
		if(null==indexo || "".equals(indexo)){
			index=0;
		}else {
			index=new Integer(indexo);
		}
		if(null==sizeo || "".equals(indexo)){
			size=5;
		}else {
			size=new Integer(sizeo);
		}
		//获取数据
		if(null==entity||"".equals(entity)){
			entity=new HashMap<>();
		}
		entity.put("resume_in", "4,5");
		List<Map<String, Object>> data = resumeMapper.findByConditionToPage(entity,index*size,size);
		for (Map<String, Object> resume : data) {
			Object jobId = resume.get("job_id");
			Map<String, Object> job = jobMapper.findById(jobId);
			resume.put("job", job);
		}
		//获取数据个数
		int count = resumeMapper.countByCondition(entity);
		//构建page
		Page page=new Page(index, size, count, data);
		
		return page;
	}
	
	/**
	 * 登记页面
	 */
	@Override
	public Map<String, Object> toRegisterEmployment(Object resumeId) {
		Map<String, Object> info = new HashMap<>();
		info.put("interview1", null);
		info.put("interview2", null);
		info.put("interview3", null);
		if(null!=resumeId&&!"".equals(resumeId)){
			Map<String, Object> resume = resumeMapper.findById(resumeId);
			if(null!=resume){				
				Object jobId = resume.get("job_id");
				Object adminId = resume.get("admin_id");
				Map<String, Object> job = jobMapper.findById(jobId);
				Map<String, Object> admin = adminMapper.findById(adminId);
					if(null!=job){
						Object classifyId = job.get("classify_id");
						Map<String, Object> classify = jobMapper.findClassifyByClassifyId(classifyId);
						job.put("classify", classify);
					}
				resume.put("job", job);
				resume.put("admin",admin);
				List<Map<String, Object>> interviews = interviewMapper.findByResumeId(resumeId);
				if(null!=interviews){
					for (Map<String, Object> interview : interviews) {
						Object interviewAdminId = interview.get("admin_id");
						Map<String, Object> interviewAdmin = adminMapper.findById(interviewAdminId);
						interview.put("admin", interviewAdmin);
						Object status = interview.get("interview_status");
						if(null!=status){
							if(status.equals(2)){
								info.put("interview1", interview);
							}
							if(status.equals(3)){
								info.put("interview2", interview);
							}
							if(status.equals(4)){
								info.put("interview3", interview);
							}
						}
					}
				}
			}
			info.put("resume", resume);
			Map<String, Object> employment = employmentMapper.findByResumeId(resumeId);
			if(null!=employment){
				Object employmentAdminId = employment.get("admin_id");
				Map<String, Object> employmentAdmin = adminMapper.findById(employmentAdminId);
				employment.put("admin", employmentAdmin);
				info.put("employment", employment);
			}
		}else {
			info.put("employment_register_msg", "出错了,未找到此简历");
		}
		List<Map<String, Object>> recruits = recruitMapper.findAll();
		for (Map<String, Object> recruit : recruits) {
			Object jobId = recruit.get("job_id");
			Map<String, Object> job = jobMapper.findById(jobId);
			recruit.put("job", job);
		}
		info.put("recruits", recruits);
		Date date = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String newDate = sdf.format(date);
		info.put("newDate", newDate);
		return info;
	}
	
	/**
	 * 登记修改
	 */
	@Override
	public Map<String, Object> registerEmployment(Map<String, Object> entity) {
		Map<String, Object> info=new HashMap<>();
		Object resumeId = entity.get("resume_id");
		Object resumeStatus = entity.get("resume_status");
		if(null!=resumeId&&!"".equals(resumeId)&&null!=resumeStatus&&!"".equals(resumeStatus)){
			Long status = Long.valueOf((String)resumeStatus);
			if(status==5||status==0){
				if(status==5){
					Object employmentId = entity.get("employment_id");
					Object employmentInfo = entity.get("employment_info");
					Map<String, Object> employment = employmentMapper.findById(employmentId);
					if(null!=employment){
						employment.put("employment_info", employmentInfo);
						int update = employmentMapper.update(employment);
						if(update<=0){
							info.put("employment_register_msg", "修改失败");
						}
					}else {
						employment=new HashMap<>();
						employment.put("employment_date", entity.get("employment_date"));
						employment.put("employment_info", employmentInfo);
						employment.put("admin_id", entity.get("admin_id"));
						employment.put("resume_id", resumeId);
						int insert = employmentMapper.insert(employment);
						if(insert<=0){
							info.put("employment_register_msg", "新增失败");
						}
					}
				}
				Map<String, Object> resume = resumeMapper.findById(resumeId);
				resume.put("resume_status", status);
				resumeMapper.update(resume);
			}else{
				info.put("employment_register_msg", "此处只能进行‘录用’或‘存档’操作");
				return info;
			}
		}else {
			info.put("employment_register_msg", "未知错误");
		}
		return info;
	}
	
	/**
	 * 明细页面
	 */
	@Override
	public Map<String, Object> toEmploymentDetailed(Object resumeId) {
		Map<String, Object> info = new HashMap<>();
		info.put("interview1", null);
		info.put("interview2", null);
		info.put("interview3", null);
		if(null!=resumeId&&!"".equals(resumeId)){
			Map<String, Object> resume = resumeMapper.findById(resumeId);
			if(null!=resume){				
				Object jobId = resume.get("job_id");
				Object adminId = resume.get("admin_id");
				Map<String, Object> job = jobMapper.findById(jobId);
				Map<String, Object> admin = adminMapper.findById(adminId);
					if(null!=job){
						Object classifyId = job.get("classify_id");
						Map<String, Object> classify = jobMapper.findClassifyByClassifyId(classifyId);
						job.put("classify", classify);
					}
				resume.put("job", job);
				resume.put("admin",admin);
				List<Map<String, Object>> interviews = interviewMapper.findByResumeId(resumeId);
				if(null!=interviews){
					for (Map<String, Object> interview : interviews) {
						Object interviewAdminId = interview.get("admin_id");
						Map<String, Object> interviewAdmin = adminMapper.findById(interviewAdminId);
						interview.put("admin", interviewAdmin);
						Object status = interview.get("interview_status");
						if(null!=status){
							if(status.equals(2)){
								info.put("interview1", interview);
							}
							if(status.equals(3)){
								info.put("interview2", interview);
							}
							if(status.equals(4)){
								info.put("interview3", interview);
							}
						}
					}
				}
			}
			info.put("resume", resume);
			Map<String, Object> employment = employmentMapper.findByResumeId(resumeId);
			Object employmentAdminId = employment.get("admin_id");
			Map<String, Object> employmentAdmin = adminMapper.findById(employmentAdminId);
			employment.put("admin", employmentAdmin);
			info.put("employment", employment);
		}else {
			info.put("employment_detailed_msg", "出错了,未找到此简历");
		}
		List<Map<String, Object>> recruits = recruitMapper.findAll();
		for (Map<String, Object> recruit : recruits) {
			Object jobId = recruit.get("job_id");
			Map<String, Object> job = jobMapper.findById(jobId);
			recruit.put("job", job);
		}
		info.put("recruits", recruits);
		return info;
	}

	/**
	 * 删除数据
	 */
	@Override
	public Map<String, Object> deleteEmployment(Object resumeId) {
		Map<String, Object> info=new HashMap<>();
		String msg="删除失败!";
		employmentMapper.delete(resumeId);
		interviewMapper.delete(resumeId);
		int delete = resumeMapper.delete(resumeId);
		if(delete>0){
			msg="删除成功！";
		}
		info.put("employment_list_msg", msg);
		return info;
	}

	
}
