package cn.gxkjdx.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gxkjdx.mapper.InterviewMapper;
import cn.gxkjdx.mapper.JobMapper;
import cn.gxkjdx.mapper.AdminMapper;
import cn.gxkjdx.mapper.EmploymentMapper;
import cn.gxkjdx.mapper.RecruitMapper;
import cn.gxkjdx.mapper.ResumeMapper;
import cn.gxkjdx.service.InterviewService;
import cn.gxkjdx.util.Page;

@Service
public class InterviewServiceImpl implements InterviewService{
	
	
	@Autowired
	InterviewMapper interviewMapper;
	
	@Autowired
	EmploymentMapper employmentMapper;

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
		entity.put("resume_in", "1,2,3,4");
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
	public Map<String, Object> toRegisterInterview(Object resumeId) {
		Map<String, Object> info = new HashMap<>();
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
			}
			info.put("resume", resume);
			List<Map<String, Object>> interviews = interviewMapper.findByResumeId(resumeId);
			for (Map<String, Object> interview : interviews) {
				Object adminId = interview.get("admin_id");
				Map<String, Object> admin = adminMapper.findById(adminId);
				interview.put("admin", admin);
			}
			info.put("interviews", interviews);
		}else {
			info.put("interview_register_msg", "出错了,未找到此简历");
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
	public Map<String, Object> registerInterview(Map<String, Object> entity) {
		Map<String, Object> info=new HashMap<>();
		Object resumeId = entity.get("resume_id");
		Object resumeStatus = entity.get("resume_status");
		if(null!=resumeId&&!"".equals(resumeId)&&null!=resumeStatus&&!"".equals(resumeStatus)){
			long status=Long.valueOf((String)resumeStatus);
			if(status>=2){
				Object interviewId1 = entity.get("interview_id1");
				Object interviewInfo1 = entity.get("interview_info1");
				Map<String, Object> interview1 = interviewMapper.findById(interviewId1);
				if(null!=interviewId1&&!"".equals(interviewId1)&&null!=interview1){
					interview1.put("interview_info", interviewInfo1);
					int update = interviewMapper.update(interview1);
					if(update<=0){
						info.put("interview_register_msg", "一面信息修改失败");
					}
				}else {
					interview1=new HashMap<>();
					Object adminId = entity.get("admin_id");
					interview1.put("interview_info", interviewInfo1);
					interview1.put("interview_date",entity.get("interview_date1"));
					interview1.put("interview_status", 2);
					interview1.put("resume_id", resumeId);
					interview1.put("admin_id", adminId);
					int insert = interviewMapper.insert(interview1);
					if(insert<=0){
						info.put("interview_register_msg", "一面信息添加失败");
					}
				}
				if(status>=3){
					Object interviewId2 = entity.get("interview_id2");
					Object interviewInfo2 = entity.get("interview_info2");
					Map<String, Object> interview2 = interviewMapper.findById(interviewId2);
					if(null!=interviewId2&&!"".equals(interviewId2)&&null!=interview2){
						interview2.put("interview_info", interviewInfo2);
						int update = interviewMapper.update(interview2);
						if(update<=0){
							info.put("interview_register_msg", info.get("interview_register_msg")+" "+"二面信息修改失败");
						}
					}else {
						interview2=new HashMap<>();
						Object adminId = entity.get("admin_id");
						interview2.put("interview_info", interviewInfo2);
						interview2.put("interview_date",entity.get("interview_date2"));
						interview2.put("interview_status", 3);
						interview2.put("resume_id", resumeId);
						interview2.put("admin_id", adminId);
						int insert = interviewMapper.insert(interview2);
						if(insert<=0){
							info.put("interview_register_msg",info.get("interview_register_msg")+" "+ "二面添加失败");
						}
					}
					if(status>=4){
						Object interviewId3 = entity.get("interview_id3");
						Object interviewInfo3 = entity.get("interview_info3");
						Map<String, Object> interview3 = interviewMapper.findById(interviewId3);
						if(null!=interviewId3&&!"".equals(interviewId3)&&null!=interview3){
							interview3.put("interview_info", interviewInfo3);
							int update = interviewMapper.update(interview3);
							if(update<=0){
								info.put("interview_register_msg", info.get("interview_register_msg")+" "+ "三面面修改失败");
							}
						}else {
							interview3=new HashMap<>();
							Object adminId = entity.get("admin_id");
							interview3.put("interview_info", interviewInfo3);
							interview3.put("interview_date",entity.get("interview_date3"));
							interview3.put("interview_status", 4);
							interview3.put("resume_id", resumeId);
							interview3.put("admin_id", adminId);
							int insert = interviewMapper.insert(interview3);
							if(insert<=0){
								info.put("interview_register_msg",info.get("interview_register_msg")+" "+ "添加失败");
							}
						}
					}
				}
			}
			Map<String, Object> resume = resumeMapper.findById(resumeId);
			if(null!=resume){
				resume.put("resume_status", resumeStatus);
				resumeMapper.update(resume);
			}else {
				info.put("interview_register_msg", "出错了");
				return info;
			}
		}else {
			info.put("interview_register_msg", "出错了");
		}
		return info;
	}
	
	/**
	 * 明细页面
	 */
	@Override
	public Map<String, Object> toInterviewDetailed(Object resumeId) {
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
						Object iterviewAdminId = interview.get("admin_id");
						Map<String, Object> iterviewAdmin = adminMapper.findById(iterviewAdminId);
						interview.put("admin", iterviewAdmin);
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
		}else {
			info.put("resume_detailed_msg", "出错了,未找到此简历");
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
	public Map<String, Object> deleteInterview(Object resumeId) {
		Map<String, Object> info=new HashMap<>();
		String msg="删除失败!";
		employmentMapper.delete(resumeId);
		interviewMapper.delete(resumeId);
		int delete = resumeMapper.delete(resumeId);
		if(delete>0){
			msg="删除成功！";
		}
		info.put("interview_list_msg", msg);
		return info;
	}

	
}
