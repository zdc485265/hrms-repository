package cn.gxkjdx.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gxkjdx.mapper.DeptMapper;
import cn.gxkjdx.mapper.JobMapper;
import cn.gxkjdx.service.JobService;
import cn.gxkjdx.util.Page;

@Service
public class JobServiceImpl implements JobService{
	
	@Autowired
	JobMapper jobMapper;
	
	@Autowired
	DeptMapper deptMapper;

	/**
	 * ��ȡpage
	 */
	@Override
	public Page findJobByPage(Map<String, Object> entity,String indexo, String sizeo) {
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
		List<Map<String, Object>> data = jobMapper.findByConditionToPage(entity,index*size,size);
		for (Map<String, Object> job : data) {
			Object classifyId = job.get("classify_id");
			Object deptId = job.get("dept_id");
			Map<String, Object> classify = jobMapper.findClassifyByClassifyId(classifyId);
			Map<String, Object> dept = deptMapper.findById(deptId);
			job.put("dept", dept);
			job.put("classify", classify);
		}
		//��ȡ���ݸ���
		int count = jobMapper.countByCondition(entity);
		//����page
		Page page=new Page(index, size, count, data);
		
		return page;
	}
	/**
	 * �������
	 */
	@Override
	public Map<String, Object> addJob(Map<String, Object> entity) {
		Map<String, Object> info=new HashMap<>();
		StringBuffer sb=new StringBuffer("����ʧ��,");
		Object jobCode = entity.get("job_code");
		Object jobName = entity.get("job_name");
		Object classifyId = entity.get("classify_id");
		Object deptId = entity.get("dept_id");
		Object jobDescription = entity.get("job_description");
		Object jobRemark = entity.get("job_remark");
		Object jobStatus = entity.get("job_status");
		int num=0;
		if(null==jobCode||"".equals(jobCode)){
			sb.append("ְλ���벻��Ϊ��,");
			num++;
		}else{
			Map<String, Object> job = jobMapper.findByCode(jobCode);
			if(null!=job){
				sb.append("ְλ�������ظ�,");
				info.put("job_add_msg", sb.toString());
				return info;
			}
		}
		if(null==jobName||"".equals(jobName)){
			sb.append("ְλ������Ϊ��,");
			num++;
		}else{
			Map<String, Object> job = jobMapper.findByName(jobName);
			if(null!=job){
				sb.append("ְλ�����ظ�,");
				info.put("job_add_msg", sb.toString());
				return info;
			}
		}
		
		if(null==classifyId||"".equals(classifyId)){
			sb.append("ְλ���಻��Ϊ��,");
			num++;
		}
		if(null==deptId||"".equals(deptId)){
			sb.append("�������Ų���Ϊ��,");
			num++;
		}
		if(null==jobDescription||"".equals(jobDescription)){
			sb.append("ְλ��������Ϊ��,");
			num++;
		}
		if(null==jobRemark||"".equals(jobRemark)){
			sb.append("ְλ��ע����Ϊ��,");
			num++;
		}
		if(null==jobStatus||"".equals(jobStatus)){
			sb.append("�Ƿ����ò���Ϊ��,");
			num++;
		}
		sb.setLength(sb.length()-1);
		if(num==0){
			int insert = jobMapper.insert(entity);
			if(insert>0){
				sb=new StringBuffer("�����ɹ���");
			}
		}
		info.put("job_add_msg", sb.toString());
		return info;
	}

	/**
	 * ��ȡ���ҳ����ʾ����
	 */
	@Override
	public Map<String, Object> toAddJob() {
		Map<String, Object> info =new HashMap<>();
		List<Map<String, Object>> classifys = jobMapper.findClassifyAll();
		List<Map<String, Object>> depts = deptMapper.findAll();
		info.put("classifys", classifys);
		info.put("depts", depts);
		return info;
	}
	
	/**
	 * ��ȡ�޸�ҳ����ʾ����
	 */
	@Override
	public Map<String, Object> toEditJob(Object jobId) {
		Map<String, Object> info = new HashMap<>();
		if(null!=jobId&&!"".equals(jobId)){			
			Map<String, Object> job = jobMapper.findById(jobId);
			info.put("job", job);
		}else {
			info.put("job_edit_msg", "������,δ�ҵ���ְλ");
		}
		List<Map<String, Object>> classifys = jobMapper.findClassifyAll();
		List<Map<String, Object>> depts = deptMapper.findAll();
		info.put("classifys", classifys);
		info.put("depts", depts);
		return info;
	}

	/**
	 * �޸�����
	 */
	@Override
	public Map<String, Object> editJob(Map<String, Object> entity) {
		Map<String, Object> info = new HashMap<>();
		StringBuffer sb =new StringBuffer("�༭ʧ��,");
		Object jobCode = entity.get("job_code");
		Object jobName = entity.get("job_name");
		Object classifyId = entity.get("classify_id");
		Object deptId = entity.get("dept_id");
		Object jobDescription = entity.get("job_description");
		Object jobRemark = entity.get("job_remark");
		Object jobStatus = entity.get("job_status");
		int num=0;
		if(null==jobCode||"".equals(jobCode)){
			sb.append("ְλ���벻��Ϊ��,");
			num++;
		}else{
			Map<String, Object> job = jobMapper.findByCode(jobCode);
			if(null!=job&&!(job.get("job_id").toString()).equals(entity.get("job_id"))){
				sb.append("ְλ�������ظ�,");
				info.put("job_edit_msg", sb.toString());
				return info;
			}
		}
		if(null==jobName||"".equals(jobName)){
			sb.append("ְλ������Ϊ��,");
			num++;
		}
		
		if(null==classifyId||"".equals(classifyId)){
			sb.append("ְλ���಻��Ϊ��,");
			num++;
		}
		if(null==deptId||"".equals(deptId)){
			sb.append("�������Ų���Ϊ��,");
			num++;
		}
		if(null==jobDescription||"".equals(jobDescription)){
			sb.append("ְλ��������Ϊ��,");
			num++;
		}
		if(null==jobRemark||"".equals(jobRemark)){
			sb.append("ְλ��ע����Ϊ��,");
			num++;
		}
		if(null==jobStatus||"".equals(jobStatus)){
			sb.append("�Ƿ����ò���Ϊ��,");
			num++;
		}
		sb.setLength(sb.length()-1);
		if(0==num){
			int update = jobMapper.update(entity);
			if(update>0){
				sb=new StringBuffer("�༭�ɹ���");
			}
		}
		info.put("job_edit_msg", sb.toString());
		return info;
	}

	/**
	 * ɾ������
	 */
	@Override
	public Map<String, Object> deleteJob(Long[] job_id) {
		Map<String, Object> info=new HashMap<>();
		String msg="ɾ��ʧ��!";
		try {
			if(job_id.length>0){
				String jobIdStr = Arrays.toString(job_id);
				String jobIds = jobIdStr.substring(1, jobIdStr.length()-1);
				int delete = jobMapper.delete(jobIds);
				if(delete>0){
					msg="ɾ���ɹ���";
				}
			}
		} catch (Exception e) {
			msg="δ֪�쳣";
		}
		info.put("job_list_msg", msg);
		return info;
	}
	
	/**
	 * �鿴����
	 */
	@Override
	public Map<String, Object> toJobDetailed(Object jobId) {
		Map<String, Object> info = new HashMap<>();
		Map<String, Object> job = jobMapper.findById(jobId);
		if(null!=job){
			Object classifyId = job.get("classify_id");
			Object deptId = job.get("dept_id");
			Map<String, Object> classify = jobMapper.findClassifyByClassifyId(classifyId);
			Map<String, Object> dept = deptMapper.findById(deptId);
			job.put("classify", classify);
			job.put("dept",dept);
			info.put("job_detailed_msg", "");
		}else {
			info.put("job_detailed_msg", "�鿴��ϸʧ��,δ�ҵ���ְλ");
		}
		info.put("job", job);
		return info;
	}

}
