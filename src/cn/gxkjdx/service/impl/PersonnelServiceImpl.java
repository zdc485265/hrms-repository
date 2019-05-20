package cn.gxkjdx.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gxkjdx.mapper.AdminMapper;
import cn.gxkjdx.mapper.DeptMapper;
import cn.gxkjdx.mapper.JobMapper;
import cn.gxkjdx.mapper.SalaryMapper;
import cn.gxkjdx.mapper.PersonnelMapper;
import cn.gxkjdx.service.PersonnelService;
import cn.gxkjdx.util.Page;

@Service
public class PersonnelServiceImpl implements PersonnelService{
	
	@Autowired
	PersonnelMapper personnelMapper;
	
	@Autowired
	JobMapper jobMapper;
	
	@Autowired
	DeptMapper deptMapper;
	
	@Autowired
	SalaryMapper salaryMapper;
	
	@Autowired
	AdminMapper adminMapper;

	/**
	 * ��ȡpage
	 */
	@Override
	public Page findPersonnelByPage(Map<String, Object> entity,String indexo, String sizeo) {
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
		List<Map<String, Object>> data = personnelMapper.findByConditionToPage(entity,index*size,size);
		for (Map<String, Object> personnel : data) {
			Object jobId = personnel.get("job_id");
			Map<String, Object> job = jobMapper.findById(jobId);
			Object deptId = job.get("dept_id");
			Map<String, Object> dept = deptMapper.findById(deptId);
			job.put("dept", dept);
			personnel.put("job", job);
			
		}
		//��ȡ���ݸ���
		int count = personnelMapper.countByCondition(entity);
		//����page
		Page page=new Page(index, size, count, data);
		
		return page;
	}
	/**
	 * �������
	 */
	@Override
	public Map<String, Object> addPersonnel(Map<String, Object> entity) {
		Map<String, Object> info=new HashMap<>();
		StringBuffer sb=new StringBuffer("����ʧ��,");
		Object personnelName = entity.get("personnel_name");
		Object personnelSex = entity.get("personnel_sex");
		Object personnelEmail= entity.get("personnel_email");
		Object personnelPhone = entity.get("personnel_phone");
		Object personnelHokouAddress = entity.get("personnel_hokou_address");
		Object personnelAddress = entity.get("personnel_address");
		Object personnelZzmm = entity.get("personnel_zzmm");
		Object personnelIdentify= entity.get("personnel_identify");
		Object personnelSchool = entity.get("personnel_school");
		Object personnelXl = entity.get("personnel_xl");
		Object personnelBank = entity.get("personnel_bank");
		Object personnelMajor= entity.get("personnel_major");
		Object personnelBankCard = entity.get("personnel_bank_card");
		Object personnelSocialSecurityCard = entity.get("personnel_social_security_card");
		Object personnelRegisterDate = entity.get("personnel_register_date");
		Object personnelResumeInfo = entity.get("personnel_resume_info");
		Object personnelExamineStatus = entity.get("personnel_examine_status");
		Object personnelStatus = entity.get("personnel_status");
		Object salaryId = entity.get("salary_id");
		Object jobId = entity.get("job_id");
		Object adminId = entity.get("admin_id");
		int num=0;
		
		if(null==personnelName||"".equals(personnelName)){
			sb.append("��������Ϊ��,");
			num++;
		}
		
		if(null==personnelSex||"".equals(personnelSex)){
			sb.append("�Ա���Ϊ��,");
			num++;
		}
		
		if(null==personnelEmail||"".equals(personnelEmail)){
			sb.append("email����Ϊ��,");
			num++;
		}
		
		if(null==personnelPhone||"".equals(personnelPhone)){
			sb.append("��ϵ�绰����Ϊ��,");
			num++;
		}
		
		if(null==personnelHokouAddress||"".equals(personnelHokouAddress)){
			sb.append("�������ڵز���Ϊ��,");
			num++;
		}
		
		if(null==personnelAddress||"".equals(personnelAddress)){
			sb.append("סַ����Ϊ��,");
			num++;
		}
		
		if(null==personnelZzmm||"".equals(personnelZzmm)){
			sb.append("������ò����Ϊ��,");
			num++;
		}
		
		if(null==personnelIdentify||"".equals(personnelIdentify)){
			sb.append("���֤�Ų���Ϊ��,");
			num++;
		}
		
		if(null==personnelSchool||"".equals(personnelSchool)){
			sb.append("��ҵԺУ����Ϊ��,");
			num++;
		}
		
		if(null==personnelXl||"".equals(personnelXl)){
			sb.append("ѧ������Ϊ��,");
			num++;
		}
		
		if(null==personnelBank||"".equals(personnelBank)){
			sb.append("�����в���Ϊ��,");
			num++;
		}
		
		if(null==personnelMajor||"".equals(personnelMajor)){
			sb.append("רҵ����Ϊ��,");
			num++;
		}
		
		if(null==personnelBankCard||"".equals(personnelBankCard)){
			sb.append("���п��Ų���Ϊ��,");
			num++;
		}
		
		if(null==personnelSocialSecurityCard||"".equals(personnelSocialSecurityCard)){
			sb.append("�籣������Ϊ��,");
			num++;
		}
		
		if(null==personnelRegisterDate||"".equals(personnelRegisterDate)){
			sb.append("�Ǽ�ʱ�䲻��Ϊ��,");
			num++;
		}
		
		if(null==personnelResumeInfo||"".equals(personnelResumeInfo)){
			sb.append("������������Ϊ��,");
			num++;
		}
		
		if(null==personnelExamineStatus||"".equals(personnelExamineStatus)){
			entity.put("personnel_examine_status",0);
		}
		
		if(null==personnelStatus||"".equals(personnelStatus)){
			entity.put("personnel_status",0);
		}
		
		if(null==salaryId||"".equals(salaryId)){
			sb.append("н���׼����Ϊ��,");
			num++;
		}
		
		if(null==jobId||"".equals(jobId)){
			sb.append("ְλ����Ϊ��,");
			num++;
		}
		
		if(null==adminId||"".equals(adminId)){
			sb.append("�Ǽ��˲���Ϊ��,");
			num++;
		}
		
		sb.setLength(sb.length()-1);
		if(num==0){
			int countByCondition = personnelMapper.countByCondition(new HashMap<>());
			entity.put("personnel_code", "DN"+(100000+countByCondition));
			int insert = personnelMapper.insert(entity);
			if(insert>0){
				sb=new StringBuffer("�����ɹ���");
			}
		}
		info.put("personnel_add_msg", sb.toString());
		return info;
	}

	/**
	 * ��ȡ���ҳ����ʾ����
	 */
	@Override
	public Map<String, Object> toAddPersonnel() {
		Map<String, Object> info =new HashMap<>();
		List<Map<String, Object>> depts = deptMapper.findAll();
		List<Map<String, Object>> salarys = salaryMapper.findAll();
		info.put("depts", depts);
		info.put("salarys", salarys);
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
	public Map<String, Object> toEditPersonnel(Object personnelId) {
		Map<String, Object> info = new HashMap<>();
		if(null!=personnelId&&!"".equals(personnelId)){			
			Map<String, Object> personnel = personnelMapper.findById(personnelId);
			if(null!=personnel){				
				Object jobId = personnel.get("job_id");
				Object adminId = personnel.get("admin_id");
				Map<String, Object> job = jobMapper.findById(jobId);
				Map<String, Object> admin = adminMapper.findById(adminId);
				personnel.put("job", job);
				personnel.put("admin",admin);
				Object deptId = job.get("dept_id");
				List<Map<String, Object>> jobs = jobMapper.findByDeptId(deptId);
				info.put("jobs", jobs);
			}
			info.put("personnel", personnel);
		}else {
			info.put("personnel_edit_msg", "������,δ�ҵ������µ���");
		}
		List<Map<String, Object>> depts = deptMapper.findAll();
		List<Map<String, Object>> salarys = salaryMapper.findAll();
		info.put("salarys", salarys);
		info.put("depts", depts);
		return info;
	}

	/**
	 * �޸�����
	 */
	@Override
	public Map<String, Object> editPersonnel(Map<String, Object> entity) {
		Map<String, Object> info = new HashMap<>();
		StringBuffer sb =new StringBuffer("�༭ʧ��,");
		Object personnelName = entity.get("personnel_name");
		Object personnelSex = entity.get("personnel_sex");
		Object personnelEmail= entity.get("personnel_email");
		Object personnelPhone = entity.get("personnel_phone");
		Object personnelHokouAddress = entity.get("personnel_hokou_address");
		Object personnelAddress = entity.get("personnel_address");
		Object personnelZzmm = entity.get("personnel_zzmm");
		Object personnelIdentify= entity.get("personnel_identify");
		Object personnelSchool = entity.get("personnel_school");
		Object personnelXl = entity.get("personnel_xl");
		Object personnelBank = entity.get("personnel_bank");
		Object personnelMajor= entity.get("personnel_major");
		Object personnelBankCard = entity.get("personnel_bank_card");
		Object personnelSocialSecurityCard = entity.get("personnel_social_security_card");
		Object personnelRegisterDate = entity.get("personnel_register_date");
		Object personnelResumeInfo = entity.get("personnel_resume_info");
		Object personnelExamineStatus = entity.get("personnel_examine_status");
		Object personnelStatus = entity.get("personnel_status");
		Object salaryId = entity.get("salary_id");
		Object jobId = entity.get("job_id");
		Object adminId = entity.get("admin_id");
		int num=0;
		
		if(null==personnelName||"".equals(personnelName)){
			sb.append("��������Ϊ��,");
			num++;
		}
		
		if(null==personnelSex||"".equals(personnelSex)){
			sb.append("�Ա���Ϊ��,");
			num++;
		}
		
		if(null==personnelEmail||"".equals(personnelEmail)){
			sb.append("email����Ϊ��,");
			num++;
		}
		
		if(null==personnelPhone||"".equals(personnelPhone)){
			sb.append("��ϵ�绰����Ϊ��,");
			num++;
		}
		
		if(null==personnelHokouAddress||"".equals(personnelHokouAddress)){
			sb.append("�������ڵز���Ϊ��,");
			num++;
		}
		
		if(null==personnelAddress||"".equals(personnelAddress)){
			sb.append("סַ����Ϊ��,");
			num++;
		}
		
		if(null==personnelZzmm||"".equals(personnelZzmm)){
			sb.append("������ò����Ϊ��,");
			num++;
		}
		
		if(null==personnelIdentify||"".equals(personnelIdentify)){
			sb.append("���֤�Ų���Ϊ��,");
			num++;
		}
		
		if(null==personnelSchool||"".equals(personnelSchool)){
			sb.append("��ҵԺУ����Ϊ��,");
			num++;
		}
		
		if(null==personnelXl||"".equals(personnelXl)){
			sb.append("ѧ������Ϊ��,");
			num++;
		}
		
		if(null==personnelBank||"".equals(personnelBank)){
			sb.append("�����в���Ϊ��,");
			num++;
		}
		
		if(null==personnelMajor||"".equals(personnelMajor)){
			sb.append("רҵ����Ϊ��,");
			num++;
		}
		
		if(null==personnelBankCard||"".equals(personnelBankCard)){
			sb.append("���п��Ų���Ϊ��,");
			num++;
		}
		
		if(null==personnelSocialSecurityCard||"".equals(personnelSocialSecurityCard)){
			sb.append("�籣������Ϊ��,");
			num++;
		}
		
		if(null==personnelRegisterDate||"".equals(personnelRegisterDate)){
			sb.append("�Ǽ�ʱ�䲻��Ϊ��,");
			num++;
		}
		
		if(null==personnelResumeInfo||"".equals(personnelResumeInfo)){
			sb.append("������������Ϊ��,");
			num++;
		}
		
		if(null==personnelExamineStatus||"".equals(personnelExamineStatus)){
			personnelExamineStatus=0;
		}
		
		if(null==personnelStatus||"".equals(personnelStatus)){
			personnelStatus=0;
		}
		
		if(null==salaryId||"".equals(salaryId)){
			sb.append("н���׼����Ϊ��,");
			num++;
		}
		
		if(null==jobId||"".equals(jobId)){
			sb.append("ְλ����Ϊ��,");
			num++;
		}
		
		if(null==adminId||"".equals(adminId)){
			sb.append("�Ǽ��˲���Ϊ��,");
			num++;
		}
		
		sb.setLength(sb.length()-1);
		if(0==num){
			int update = personnelMapper.update(entity);
			if(update>0){
				sb=new StringBuffer("�༭�ɹ���");
			}
		}
		info.put("personnel_edit_msg", sb.toString());
		return info;
	}

	/**
	 * ɾ������
	 */
	@Override
	public Map<String, Object> deletePersonnel(Map<String, Object> map) {
		Map<String, Object> info=new HashMap<>();
		String msg="ɾ��ʧ��!";
		Object personnelId = map.get("personnel_id");
		if(null!=personnelId&&!"".equals(personnelId)){
			Map<String, Object> personnel = personnelMapper.findById(personnelId);
			if(null!=personnel){
				Object personnelStatus = personnel.get("personnel_status");
				if(null!=personnelStatus){
					int status=(int)personnelStatus;
					map.put("personnel_status", status+4);
					int updateDelete = personnelMapper.updateStatus(map);
					if(updateDelete>0){
						msg="ɾ���ɹ���";
					}
				}
			}
		}
		info.put("personnel_list_msg", msg);
		return info;
	}
	
	/**
	 * ɾ������
	 */
	@Override
	public Map<String, Object> foreverDeletePersonnel(Map<String, Object> map) {
		Map<String, Object> info=new HashMap<>();
		String msg="ɾ��ʧ��!";
		Object personnelId = map.get("personnel_id");
		if(null!=personnelId&&!"".equals(personnelId)){
			int delete = personnelMapper.delete(personnelId);
			if(delete>0){
				msg="����ɾ���ɹ���";
			}
		}
		info.put("personnel_list_msg", msg);
		return info;
	}
	
	/**
	 * ɾ������
	 */
	@Override
	public Map<String, Object> recoveryDeletePersonnel(Map<String, Object> map) {
		Map<String, Object> info=new HashMap<>();
		String msg="�ָ�ʧ��!";
		Object personnelId = map.get("personnel_id");
		if(null!=personnelId&&!"".equals(personnelId)){
			Map<String, Object> personnel = personnelMapper.findById(personnelId);
			if(null!=personnel){
				Object personnelStatus = personnel.get("personnel_status");
				if(null!=personnelStatus){
					int status=(int)personnelStatus;
					map.put("personnel_status", status-4);
					int updateDelete = personnelMapper.updateStatus(map);
					if(updateDelete>0){
						msg="�ָ��ɹ���";
					}
				}
			}
		}
		info.put("personnel_list_msg", msg);
		return info;
	}
	
	/**
	 * �鿴����
	 */
	@Override
	public Map<String, Object> toPersonnelDetailed(Object personnelId) {
		Map<String, Object> info = new HashMap<>();
		if(null!=personnelId&&!"".equals(personnelId)){			
			Map<String, Object> personnel = personnelMapper.findById(personnelId);
			if(null!=personnel){				
				Object jobId = personnel.get("job_id");
				Object adminId = personnel.get("admin_id");
				Map<String, Object> job = jobMapper.findById(jobId);
				Map<String, Object> admin = adminMapper.findById(adminId);
				personnel.put("job", job);
				personnel.put("admin",admin);
				Object deptId = job.get("dept_id");
				List<Map<String, Object>> jobs = jobMapper.findByDeptId(deptId);
				info.put("jobs", jobs);
			}
			info.put("personnel", personnel);
		}else {
			info.put("personnel_detailed_msg", "������,δ�ҵ������µ���");
		}
		List<Map<String, Object>> depts = deptMapper.findAll();
		List<Map<String, Object>> salarys = salaryMapper.findAll();
		info.put("depts", depts);
		info.put("salarys", salarys);
		return info;
	}
	
}
