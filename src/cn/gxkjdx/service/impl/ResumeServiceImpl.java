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
	 * ��ȡpage
	 */
	@Override
	public Page findResumeByPage(Map<String, Object> entity,String indexo, String sizeo) {
		int index,size;
		
		//�п�
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
		//��ȡ����
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
		//��ȡ���ݸ���
		int count = resumeMapper.countByCondition(entity);
		//����page
		Page page=new Page(index, size, count, data);
		
		return page;
	}
	/**
	 * �������
	 */
	@Override
	public Map<String, Object> addResume(Map<String, Object> entity) {
		Map<String, Object> info=new HashMap<>();
		StringBuffer sb=new StringBuffer("����ʧ��,");
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
			sb.append("��������Ϊ��,");
			num++;
		}
		
		if(null==resumeSex||"".equals(resumeSex)){
			sb.append("�Ա���Ϊ��,");
			num++;
		}
		
		if(null==resumeEmail||"".equals(resumeEmail)){
			sb.append("email����Ϊ��,");
			num++;
		}
		
		if(null==resumePhone||"".equals(resumePhone)){
			sb.append("��ϵ�绰����Ϊ��,");
			num++;
		}
		
		if(null==resumeHokouAddress||"".equals(resumeHokouAddress)){
			sb.append("�������ڵز���Ϊ��,");
			num++;
		}
		
		if(null==resumeAddress||"".equals(resumeAddress)){
			sb.append("סַ����Ϊ��,");
			num++;
		}
		
		if(null==resumeZzmm||"".equals(resumeZzmm)){
			sb.append("������ò����Ϊ��,");
			num++;
		}
		
		if(null==resumeIdentity||"".equals(resumeIdentity)){
			sb.append("���֤�Ų���Ϊ��,");
			num++;
		}
		
		if(null==resumeSchool||"".equals(resumeSchool)){
			sb.append("��ҵԺУ����Ϊ��,");
			num++;
		}
		
		if(null==resumeXl||"".equals(resumeXl)){
			sb.append("ѧ������Ϊ��,");
			num++;
		}
		
		if(null==resumeExperience||"".equals(resumeExperience)){
			sb.append("�������鲻��Ϊ��,");
			num++;
		}
		
		if(null==resumeSalReq||"".equals(resumeSalReq)){
			sb.append("н��Ҫ����Ϊ��,");
			num++;
		}
		
		if(null==resumeMajor||"".equals(resumeMajor)){
			sb.append("רҵ����Ϊ��,");
			num++;
		}
		
		if(null==resumeIsCurrent||"".equals(resumeIsCurrent)){
			sb.append("�Ƿ���Ӧ��������Ϊ��,");
			num++;
		}
		
		if(null==resumeIsInword||"".equals(resumeIsInword)){
			sb.append("�Ƿ���ְ����Ϊ��,");
			num++;
		}
		
		if(null==resumeLoginDate||"".equals(resumeLoginDate)){
			sb.append("�Ǽ�ʱ�䲻��Ϊ��,");
			num++;
		}
		
		if(null==resumeInfo||"".equals(resumeInfo)){
			sb.append("������������Ϊ��,");
			num++;
		}
		
		if(null==resumeRecommendDate||"".equals(resumeRecommendDate)){
			sb.append("�Ƽ�ʱ�䲻��Ϊ��,");
			num++;
		}
		
		if(null==resumeStatus||"".equals(resumeStatus)){
			sb.append("�Ƿ��Ƽ����Բ���Ϊ��,");
			num++;
		}
		
		if(null==jobId||"".equals(jobId)){
			sb.append("��Ƹְλ����Ϊ��,");
			num++;
		}
		
		if(null==adminId||"".equals(adminId)){
			sb.append("�Ǽ��˲���Ϊ��,");
			num++;
		}
		
		sb.setLength(sb.length()-1);
		if(num==0){
			int insert = resumeMapper.insert(entity);
			if(insert>0){
				sb=new StringBuffer("�����ɹ���");
			}
		}
		info.put("resume_add_msg", sb.toString());
		return info;
	}

	/**
	 * ��ȡ���ҳ����ʾ����
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
	 * ��ȡ�޸�ҳ����ʾ����
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
			info.put("resume_edit_msg", "������,δ�ҵ��˼���");
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
	 * �޸�����
	 */
	@Override
	public Map<String, Object> editResume(Map<String, Object> entity) {
		Map<String, Object> info = new HashMap<>();
		StringBuffer sb =new StringBuffer("�༭ʧ��,");
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
			sb.append("��������Ϊ��,");
			num++;
		}
		
		if(null==resumeSex||"".equals(resumeSex)){
			sb.append("�Ա���Ϊ��,");
			num++;
		}
		
		if(null==resumeEmail||"".equals(resumeEmail)){
			sb.append("email����Ϊ��,");
			num++;
		}
		
		if(null==resumePhone||"".equals(resumePhone)){
			sb.append("��ϵ�绰����Ϊ��,");
			num++;
		}
		
		if(null==resumeHokouAddress||"".equals(resumeHokouAddress)){
			sb.append("�������ڵز���Ϊ��,");
			num++;
		}
		
		if(null==resumeAddress||"".equals(resumeAddress)){
			sb.append("סַ����Ϊ��,");
			num++;
		}
		
		if(null==resumeZzmm||"".equals(resumeZzmm)){
			sb.append("������ò����Ϊ��,");
			num++;
		}
		
		if(null==resumeIdentity||"".equals(resumeIdentity)){
			sb.append("���֤�Ų���Ϊ��,");
			num++;
		}
		
		if(null==resumeSchool||"".equals(resumeSchool)){
			sb.append("��ҵԺУ����Ϊ��,");
			num++;
		}
		
		if(null==resumeXl||"".equals(resumeXl)){
			sb.append("ѧ������Ϊ��,");
			num++;
		}
		
		if(null==resumeExperience||"".equals(resumeExperience)){
			sb.append("�������鲻��Ϊ��,");
			num++;
		}
		
		if(null==resumeSalReq||"".equals(resumeSalReq)){
			sb.append("н��Ҫ����Ϊ��,");
			num++;
		}
		
		if(null==resumeMajor||"".equals(resumeMajor)){
			sb.append("רҵ����Ϊ��,");
			num++;
		}
		
		if(null==resumeIsCurrent||"".equals(resumeIsCurrent)){
			sb.append("�Ƿ���Ӧ��������Ϊ��,");
			num++;
		}
		
		if(null==resumeIsInword||"".equals(resumeIsInword)){
			sb.append("�Ƿ���ְ����Ϊ��,");
			num++;
		}
		
		if(null==resumeLoginDate||"".equals(resumeLoginDate)){
			sb.append("�Ǽ�ʱ�䲻��Ϊ��,");
			num++;
		}
		
		if(null==resumeInfo||"".equals(resumeInfo)){
			sb.append("������������Ϊ��,");
			num++;
		}
		
		if(null==resumeRecommendDate||"".equals(resumeRecommendDate)){
			sb.append("�Ƽ�ʱ�䲻��Ϊ��,");
			num++;
		}
		
		if(null==resumeStatus||"".equals(resumeStatus)){
			sb.append("�Ƿ��Ƽ����Բ���Ϊ��,");
			num++;
		}
		
		if(null==jobId||"".equals(jobId)){
			sb.append("��Ƹְλ����Ϊ��,");
			num++;
		}
		
		if(null==adminId||"".equals(adminId)){
			sb.append("�Ǽ��˲���Ϊ��,");
			num++;
		}
		
		sb.setLength(sb.length()-1);
		if(0==num){
			int update = resumeMapper.update(entity);
			if(update>0){
				sb=new StringBuffer("�༭�ɹ���");
			}
		}
		info.put("resume_edit_msg", sb.toString());
		return info;
	}

	/**
	 * ɾ������
	 */
	@Override
	public Map<String, Object> deleteResume(Object resumeId) {
		Map<String, Object> info=new HashMap<>();
		String msg="ɾ��ʧ��!";
		employmentMapper.delete(resumeId);
		interviewMapper.delete(resumeId);
		int delete = resumeMapper.delete(resumeId);
		if(delete>0){
			msg="ɾ���ɹ���";
		}
		info.put("resume_list_msg", msg);
		return info;
	}
	
	/**
	 * �鿴����
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
			info.put("resume_detailed_msg", "������,δ�ҵ��˼���");
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
