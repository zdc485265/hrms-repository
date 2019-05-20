package cn.gxkjdx.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gxkjdx.mapper.AdminMapper;
import cn.gxkjdx.mapper.EmploymentMapper;
import cn.gxkjdx.mapper.InterviewMapper;
import cn.gxkjdx.mapper.JobMapper;
import cn.gxkjdx.mapper.RecruitMapper;
import cn.gxkjdx.mapper.ResumeMapper;
import cn.gxkjdx.service.ResumeService;
import cn.gxkjdx.util.Page;

@Service
public class ResumeServiceImpl implements ResumeService{
	
	@Autowired
	ResumeMapper resumeMapper;
	
	@Autowired
	InterviewMapper interviewMapper;
	
	@Autowired
	EmploymentMapper employmentMapper;
	
	@Autowired
	JobMapper jobMapper;
	
	@Autowired
	RecruitMapper recruitMapper;
	
	@Autowired
	AdminMapper adminMapper;

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
		entity.put("resume_in", "0,1,2,3,4,5");
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
	 * 添加数据
	 */
	@Override
	public Map<String, Object> addResume(Map<String, Object> entity) {
		Map<String, Object> info=new HashMap<>();
		StringBuffer sb=new StringBuffer("新增失败,");
		Object resumeName = entity.get("resume_name");
		Object resumeSex = entity.get("resume_sex");
		Object resumeEmail= entity.get("resume_email");
		Object resumePhone = entity.get("resume_phone");
		Object resumeHokouAddress = entity.get("resume_hokou_address");
		Object resumeAddress = entity.get("resume_address");
		Object resumeZzmm = entity.get("resume_zzmm");
		Object resumeIdentity= entity.get("resume_identity");
		Object resumeSchool = entity.get("resume_school");
		Object resumeXl = entity.get("resume_xl");
		Object resumeExperience = entity.get("resume_experience");
		Object resumeSalReq = entity.get("resume_sal_req");
		Object resumeMajor= entity.get("resume_major");
		Object resumeIsCurrent = entity.get("resume_is_current");
		Object resumeIsInword = entity.get("resume_is_inword");
		Object resumeLoginDate = entity.get("resume_login_date");
		Object resumeInfo = entity.get("resume_info");
		Object resumeRecommendDate= entity.get("resume_recommend_date");
		Object resumeStatus = entity.get("resume_status");
		Object jobId = entity.get("job_id");
		Object adminId = entity.get("admin_id");
		int num=0;
		
		if(null==resumeName||"".equals(resumeName)){
			sb.append("姓名不能为空,");
			num++;
		}
		
		if(null==resumeSex||"".equals(resumeSex)){
			sb.append("性别不能为空,");
			num++;
		}
		
		if(null==resumeEmail||"".equals(resumeEmail)){
			sb.append("email不能为空,");
			num++;
		}
		
		if(null==resumePhone||"".equals(resumePhone)){
			sb.append("联系电话不能为空,");
			num++;
		}
		
		if(null==resumeHokouAddress||"".equals(resumeHokouAddress)){
			sb.append("户口所在地不能为空,");
			num++;
		}
		
		if(null==resumeAddress||"".equals(resumeAddress)){
			sb.append("住址不能为空,");
			num++;
		}
		
		if(null==resumeZzmm||"".equals(resumeZzmm)){
			sb.append("政治面貌不能为空,");
			num++;
		}
		
		if(null==resumeIdentity||"".equals(resumeIdentity)){
			sb.append("身份证号不能为空,");
			num++;
		}
		
		if(null==resumeSchool||"".equals(resumeSchool)){
			sb.append("毕业院校不能为空,");
			num++;
		}
		
		if(null==resumeXl||"".equals(resumeXl)){
			sb.append("学历不能为空,");
			num++;
		}
		
		if(null==resumeExperience||"".equals(resumeExperience)){
			sb.append("工作经验不能为空,");
			num++;
		}
		
		if(null==resumeSalReq||"".equals(resumeSalReq)){
			sb.append("薪资要求不能为空,");
			num++;
		}
		
		if(null==resumeMajor||"".equals(resumeMajor)){
			sb.append("专业不能为空,");
			num++;
		}
		
		if(null==resumeIsCurrent||"".equals(resumeIsCurrent)){
			sb.append("是否是应届生不能为空,");
			num++;
		}
		
		if(null==resumeIsInword||"".equals(resumeIsInword)){
			sb.append("是否在职不能为空,");
			num++;
		}
		
		if(null==resumeLoginDate||"".equals(resumeLoginDate)){
			sb.append("登记时间不能为空,");
			num++;
		}
		
		if(null==resumeInfo||"".equals(resumeInfo)){
			sb.append("简历附件不能为空,");
			num++;
		}
		
		if(null==resumeRecommendDate||"".equals(resumeRecommendDate)){
			sb.append("推荐时间不能为空,");
			num++;
		}
		
		if(null==resumeStatus||"".equals(resumeStatus)){
			sb.append("是否推荐面试不能为空,");
			num++;
		}
		
		if(null==jobId||"".equals(jobId)){
			sb.append("招聘职位不能为空,");
			num++;
		}
		
		if(null==adminId||"".equals(adminId)){
			sb.append("登记人不能为空,");
			num++;
		}
		
		sb.setLength(sb.length()-1);
		if(num==0){
			int insert = resumeMapper.insert(entity);
			if(insert>0){
				sb=new StringBuffer("新增成功！");
			}
		}
		info.put("resume_add_msg", sb.toString());
		return info;
	}

	/**
	 * 获取添加页面显示数据
	 */
	@Override
	public Map<String, Object> toAddResume() {
		Map<String, Object> info =new HashMap<>();
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
	 * 获取修改页面显示数据
	 */
	@Override
	public Map<String, Object> toEditResume(Object resumeId) {
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
		}else {
			info.put("resume_edit_msg", "出错了,未找到此简历");
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
	 * 修改数据
	 */
	@Override
	public Map<String, Object> editResume(Map<String, Object> entity) {
		Map<String, Object> info = new HashMap<>();
		StringBuffer sb =new StringBuffer("编辑失败,");
		Object resumeName = entity.get("resume_name");
		Object resumeSex = entity.get("resume_sex");
		Object resumeEmail= entity.get("resume_email");
		Object resumePhone = entity.get("resume_phone");
		Object resumeHokouAddress = entity.get("resume_hokou_address");
		Object resumeAddress = entity.get("resume_address");
		Object resumeZzmm = entity.get("resume_zzmm");
		Object resumeIdentity= entity.get("resume_identity");
		Object resumeSchool = entity.get("resume_school");
		Object resumeXl = entity.get("resume_xl");
		Object resumeExperience = entity.get("resume_experience");
		Object resumeSalReq = entity.get("resume_sal_req");
		Object resumeMajor= entity.get("resume_major");
		Object resumeIsCurrent = entity.get("resume_is_current");
		Object resumeIsInword = entity.get("resume_is_inword");
		Object resumeLoginDate = entity.get("resume_login_date");
		Object resumeInfo = entity.get("resume_info");
		Object resumeRecommendDate= entity.get("resume_recommend_date");
		Object resumeStatus = entity.get("resume_status");
		Object jobId = entity.get("job_id");
		Object adminId = entity.get("admin_id");
		int num=0;
		
		if(null==resumeName||"".equals(resumeName)){
			sb.append("姓名不能为空,");
			num++;
		}
		
		if(null==resumeSex||"".equals(resumeSex)){
			sb.append("性别不能为空,");
			num++;
		}
		
		if(null==resumeEmail||"".equals(resumeEmail)){
			sb.append("email不能为空,");
			num++;
		}
		
		if(null==resumePhone||"".equals(resumePhone)){
			sb.append("联系电话不能为空,");
			num++;
		}
		
		if(null==resumeHokouAddress||"".equals(resumeHokouAddress)){
			sb.append("户口所在地不能为空,");
			num++;
		}
		
		if(null==resumeAddress||"".equals(resumeAddress)){
			sb.append("住址不能为空,");
			num++;
		}
		
		if(null==resumeZzmm||"".equals(resumeZzmm)){
			sb.append("政治面貌不能为空,");
			num++;
		}
		
		if(null==resumeIdentity||"".equals(resumeIdentity)){
			sb.append("身份证号不能为空,");
			num++;
		}
		
		if(null==resumeSchool||"".equals(resumeSchool)){
			sb.append("毕业院校不能为空,");
			num++;
		}
		
		if(null==resumeXl||"".equals(resumeXl)){
			sb.append("学历不能为空,");
			num++;
		}
		
		if(null==resumeExperience||"".equals(resumeExperience)){
			sb.append("工作经验不能为空,");
			num++;
		}
		
		if(null==resumeSalReq||"".equals(resumeSalReq)){
			sb.append("薪资要求不能为空,");
			num++;
		}
		
		if(null==resumeMajor||"".equals(resumeMajor)){
			sb.append("专业不能为空,");
			num++;
		}
		
		if(null==resumeIsCurrent||"".equals(resumeIsCurrent)){
			sb.append("是否是应届生不能为空,");
			num++;
		}
		
		if(null==resumeIsInword||"".equals(resumeIsInword)){
			sb.append("是否在职不能为空,");
			num++;
		}
		
		if(null==resumeLoginDate||"".equals(resumeLoginDate)){
			sb.append("登记时间不能为空,");
			num++;
		}
		
		if(null==resumeInfo||"".equals(resumeInfo)){
			sb.append("简历附件不能为空,");
			num++;
		}
		
		if(null==resumeRecommendDate||"".equals(resumeRecommendDate)){
			sb.append("推荐时间不能为空,");
			num++;
		}
		
		if(null==resumeStatus||"".equals(resumeStatus)){
			sb.append("是否推荐面试不能为空,");
			num++;
		}
		
		if(null==jobId||"".equals(jobId)){
			sb.append("招聘职位不能为空,");
			num++;
		}
		
		if(null==adminId||"".equals(adminId)){
			sb.append("登记人不能为空,");
			num++;
		}
		
		sb.setLength(sb.length()-1);
		if(0==num){
			int update = resumeMapper.update(entity);
			if(update>0){
				sb=new StringBuffer("编辑成功！");
			}
		}
		info.put("resume_edit_msg", sb.toString());
		return info;
	}

	/**
	 * 删除数据
	 */
	@Override
	public Map<String, Object> deleteResume(Object resumeId) {
		Map<String, Object> info=new HashMap<>();
		String msg="删除失败!";
		employmentMapper.delete(resumeId);
		interviewMapper.delete(resumeId);
		int delete = resumeMapper.delete(resumeId);
		if(delete>0){
			msg="删除成功！";
		}
		info.put("resume_list_msg", msg);
		return info;
	}
	
	/**
	 * 查看数据
	 */
	@Override
	public Map<String, Object> toResumeDetailed(Object resumeId) {
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
	
}
