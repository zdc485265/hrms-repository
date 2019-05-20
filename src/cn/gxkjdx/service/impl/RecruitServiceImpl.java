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
import cn.gxkjdx.mapper.RecruitMapper;
import cn.gxkjdx.service.RecruitService;
import cn.gxkjdx.util.Page;

@Service
public class RecruitServiceImpl implements RecruitService{
	
	@Autowired
	RecruitMapper recruitMapper;
	
	@Autowired
	JobMapper jobMapper;
	
	@Autowired
	DeptMapper deptMapper;
	
	@Autowired
	AdminMapper adminMapper;

	/**
	 * ��ȡpage
	 */
	@Override
	public Page findRecruitByPage(Map<String, Object> entity,String indexo, String sizeo) {
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
		List<Map<String, Object>> data = recruitMapper.findByConditionToPage(entity,index*size,size);
		for (Map<String, Object> recruit : data) {
			Object jobId = recruit.get("job_id");
			Map<String, Object> job = jobMapper.findById(jobId);
			Object classifyId = job.get("classify_id");
			Object deptId = job.get("dept_id");
			Map<String, Object> classify = jobMapper.findClassifyByClassifyId(classifyId);
			Map<String, Object> dept = deptMapper.findById(deptId);
			job.put("dept", dept);
			job.put("classify", classify);
			recruit.put("job", job);
		}
		//��ȡ���ݸ���
		int count = recruitMapper.countByCondition(entity);
		//����page
		Page page=new Page(index, size, count, data);
		
		return page;
	}
	/**
	 * �������
	 */
	@Override
	public Map<String, Object> addRecruit(Map<String, Object> entity) {
		Map<String, Object> info=new HashMap<>();
		StringBuffer sb=new StringBuffer("����ʧ��,");
		Object recruitNum = entity.get("recruit_num");
		Object recruitStart = entity.get("recruit_start");
		Object recruitEnd= entity.get("recruit_end");
		Object recruitSort = entity.get("recruit_sort");
		Object jobId = entity.get("job_id");
		Object adminId = entity.get("admin_id");
		int num=0;
		
		if(null==recruitNum||"".equals(recruitNum)){
			sb.append("��Ƹ��������Ϊ��,");
			num++;
		}
		
		if(null==recruitStart||"".equals(recruitStart)){
			sb.append("�Ǽ����ڲ���Ϊ��,");
			num++;
		}
		
		if(null==recruitEnd||"".equals(recruitEnd)){
			sb.append("�������ڲ���Ϊ��,");
			num++;
		}
		if(null==recruitSort||"".equals(recruitSort)){
			sb.append("��Ƹ���Ͳ���Ϊ��,");
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
			int insert = recruitMapper.insert(entity);
			if(insert>0){
				sb=new StringBuffer("�����ɹ���");
			}
		}
		info.put("recruit_add_msg", sb.toString());
		return info;
	}

	/**
	 * ��ȡ���ҳ����ʾ����
	 */
	@Override
	public Map<String, Object> toAddRecruit() {
		Map<String, Object> info =new HashMap<>();
		List<Map<String, Object>> depts = deptMapper.findAll();
		info.put("depts", depts);
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
	public Map<String, Object> toEditRecruit(Object recruitId) {
		Map<String, Object> info = new HashMap<>();
		if(null!=recruitId&&!"".equals(recruitId)){
			Map<String, Object> recruit = recruitMapper.findById(recruitId);
			if(null!=recruit){				
				Object jobId = recruit.get("job_id");
				Object adminId = recruit.get("admin_id");
				Map<String, Object> job = jobMapper.findById(jobId);
				Map<String, Object> admin = adminMapper.findById(adminId);
				if(null!=job){
					Object deptId = job.get("dept_id");
					Object classifyId = job.get("classify_id");
					Map<String, Object> dept = deptMapper.findById(deptId);
					Map<String, Object> classify = jobMapper.findClassifyByClassifyId(classifyId);
					List<Map<String, Object>> jobs = jobMapper.findByDeptId(deptId);
					info.put("jobs", jobs);
					job.put("dept", dept);
					job.put("classify", classify);
				}
				recruit.put("job", job);
				recruit.put("admin", admin);
			}
			List<Map<String, Object>> depts = deptMapper.findAll();
			info.put("depts", depts);
			info.put("recruit", recruit);
		}else {
			info.put("recruit_edit_msg", "������,δ�ҵ�������");
		}
		return info;
	}

	/**
	 * �޸�����
	 */
	@Override
	public Map<String, Object> editRecruit(Map<String, Object> entity) {
		Map<String, Object> info = new HashMap<>();
		StringBuffer sb =new StringBuffer("�༭ʧ��,");
		Object recruitNum = entity.get("recruit_num");
		Object recruitStart = entity.get("recruit_start");
		Object recruitEnd= entity.get("recruit_end");
		Object recruitSort = entity.get("recruit_sort");
		Object jobId = entity.get("job_id");
		Object adminId = entity.get("admin_id");
		int num=0;
		
		if(null==recruitNum||"".equals(recruitNum)){
			sb.append("��Ƹ��������Ϊ��,");
			num++;
		}
		
		if(null==recruitStart||"".equals(recruitStart)){
			sb.append("�Ǽ����ڲ���Ϊ��,");
			num++;
		}
		
		if(null==recruitEnd||"".equals(recruitEnd)){
			sb.append("�������ڲ���Ϊ��,");
			num++;
		}
		
		if(null==recruitSort||"".equals(recruitSort)){
			sb.append("��Ƹ���Ͳ���Ϊ��,");
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
			int update = recruitMapper.update(entity);
			if(update>0){
				sb=new StringBuffer("�༭�ɹ���");
			}
		}
		info.put("recruit_edit_msg", sb.toString());
		return info;
	}

	/**
	 * ɾ������
	 */
	@Override
	public Map<String, Object> deleteRecruit(Object recruitId) {
		Map<String, Object> info=new HashMap<>();
		String msg="ɾ��ʧ��!";
		int delete = recruitMapper.delete(recruitId);
		
		if(delete>0){
			msg="ɾ���ɹ���";
		}
		info.put("recruit_list_msg", msg);
		return info;
	}
	
	/**
	 * �鿴����
	 */
	@Override
	public Map<String, Object> toRecruitDetailed(Object recruitId) {
		Map<String, Object> info = new HashMap<>();
		Map<String, Object> recruit = recruitMapper.findById(recruitId);
		if(null!=recruit){
			Object jobId = recruit.get("job_id");
			Object adminId = recruit.get("admin_id");
			Map<String, Object> job = jobMapper.findById(jobId);
			Map<String, Object> admin = adminMapper.findById(adminId);
			Object deptId = job.get("dept_id");
			Object classifyId = job.get("classify_id");
			Map<String, Object> dept = deptMapper.findById(deptId);
			Map<String, Object> classify = jobMapper.findClassifyByClassifyId(classifyId);
			job.put("dept", dept);
			job.put("classify", classify);
			recruit.put("job", job);
			recruit.put("admin", admin);
		}else {
			info.put("recruit_detailed_msg", "�鿴��ϸʧ��,δ�ҵ�����Ƹ��Ϣ");
		}
		info.put("recruit", recruit);
		return info;
	}
	
	/**
	 * ����ͬһ���ŵ�����ְλ
	 */
	@Override
	public Map<String, Object> findJobs(Object deptId) {
		Map<String, Object> info=new HashMap<>();
		if(null!=deptId&&!"".equals(deptId)){
			List<Map<String, Object>> jobs = jobMapper.findByDeptId(deptId);
			StringBuffer sb=new StringBuffer("<option value='0'>--��ѡ��--</option>");
			for (Map<String, Object> job : jobs) {
				sb.append("<option value='");
				sb.append(job.get("job_id").toString());
				sb.append("'>");
				sb.append(job.get("job_name"));
				sb.append("</option>");
			}
			info.put("msgStatus", 0);
			info.put("msgInfo", sb.toString());
		}else {
			info.put("msgStatus", 1);
		}
		return info;
	}
	
	/**
	 * ����job
	 */
	@Override
	public Map<String, Object> findJob(Object jobId) {
		Map<String, Object> info=new HashMap<>();
		if(null!=jobId&&!"".equals(jobId)&&!jobId.equals(0)){
			Map<String, Object> job = jobMapper.findById(jobId);
			if(null!=job){
				Object classifyId = job.get("classify_id");
				Map<String, Object> classify = jobMapper.findClassifyByClassifyId(classifyId);
				job.put("classify", classify);
				info.put("msgStatus", 0);
				info.put("msgInfo", job);
				return info;
			}
		}
		info.put("msgStatus", 1);
		return info;
	}

}
