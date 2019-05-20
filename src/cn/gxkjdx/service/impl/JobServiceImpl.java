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
	 * 获取page
	 */
	@Override
	public Page findJobByPage(Map<String, Object> entity,String indexo, String sizeo) {
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
		List<Map<String, Object>> data = jobMapper.findByConditionToPage(entity,index*size,size);
		for (Map<String, Object> job : data) {
			Object classifyId = job.get("classify_id");
			Object deptId = job.get("dept_id");
			Map<String, Object> classify = jobMapper.findClassifyByClassifyId(classifyId);
			Map<String, Object> dept = deptMapper.findById(deptId);
			job.put("dept", dept);
			job.put("classify", classify);
		}
		//获取数据个数
		int count = jobMapper.countByCondition(entity);
		//构建page
		Page page=new Page(index, size, count, data);
		
		return page;
	}
	/**
	 * 添加数据
	 */
	@Override
	public Map<String, Object> addJob(Map<String, Object> entity) {
		Map<String, Object> info=new HashMap<>();
		StringBuffer sb=new StringBuffer("新增失败,");
		Object jobCode = entity.get("job_code");
		Object jobName = entity.get("job_name");
		Object classifyId = entity.get("classify_id");
		Object deptId = entity.get("dept_id");
		Object jobDescription = entity.get("job_description");
		Object jobRemark = entity.get("job_remark");
		Object jobStatus = entity.get("job_status");
		int num=0;
		if(null==jobCode||"".equals(jobCode)){
			sb.append("职位编码不能为空,");
			num++;
		}else{
			Map<String, Object> job = jobMapper.findByCode(jobCode);
			if(null!=job){
				sb.append("职位编码已重复,");
				info.put("job_add_msg", sb.toString());
				return info;
			}
		}
		if(null==jobName||"".equals(jobName)){
			sb.append("职位名不能为空,");
			num++;
		}else{
			Map<String, Object> job = jobMapper.findByName(jobName);
			if(null!=job){
				sb.append("职位名已重复,");
				info.put("job_add_msg", sb.toString());
				return info;
			}
		}
		
		if(null==classifyId||"".equals(classifyId)){
			sb.append("职位分类不能为空,");
			num++;
		}
		if(null==deptId||"".equals(deptId)){
			sb.append("所属部门不能为空,");
			num++;
		}
		if(null==jobDescription||"".equals(jobDescription)){
			sb.append("职位描述不能为空,");
			num++;
		}
		if(null==jobRemark||"".equals(jobRemark)){
			sb.append("职位备注不能为空,");
			num++;
		}
		if(null==jobStatus||"".equals(jobStatus)){
			sb.append("是否启用不能为空,");
			num++;
		}
		sb.setLength(sb.length()-1);
		if(num==0){
			int insert = jobMapper.insert(entity);
			if(insert>0){
				sb=new StringBuffer("新增成功！");
			}
		}
		info.put("job_add_msg", sb.toString());
		return info;
	}

	/**
	 * 获取添加页面显示数据
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
	 * 获取修改页面显示数据
	 */
	@Override
	public Map<String, Object> toEditJob(Object jobId) {
		Map<String, Object> info = new HashMap<>();
		if(null!=jobId&&!"".equals(jobId)){			
			Map<String, Object> job = jobMapper.findById(jobId);
			info.put("job", job);
		}else {
			info.put("job_edit_msg", "出错了,未找到此职位");
		}
		List<Map<String, Object>> classifys = jobMapper.findClassifyAll();
		List<Map<String, Object>> depts = deptMapper.findAll();
		info.put("classifys", classifys);
		info.put("depts", depts);
		return info;
	}

	/**
	 * 修改数据
	 */
	@Override
	public Map<String, Object> editJob(Map<String, Object> entity) {
		Map<String, Object> info = new HashMap<>();
		StringBuffer sb =new StringBuffer("编辑失败,");
		Object jobCode = entity.get("job_code");
		Object jobName = entity.get("job_name");
		Object classifyId = entity.get("classify_id");
		Object deptId = entity.get("dept_id");
		Object jobDescription = entity.get("job_description");
		Object jobRemark = entity.get("job_remark");
		Object jobStatus = entity.get("job_status");
		int num=0;
		if(null==jobCode||"".equals(jobCode)){
			sb.append("职位编码不能为空,");
			num++;
		}else{
			Map<String, Object> job = jobMapper.findByCode(jobCode);
			if(null!=job&&!(job.get("job_id").toString()).equals(entity.get("job_id"))){
				sb.append("职位编码已重复,");
				info.put("job_edit_msg", sb.toString());
				return info;
			}
		}
		if(null==jobName||"".equals(jobName)){
			sb.append("职位名不能为空,");
			num++;
		}
		
		if(null==classifyId||"".equals(classifyId)){
			sb.append("职位分类不能为空,");
			num++;
		}
		if(null==deptId||"".equals(deptId)){
			sb.append("所属部门不能为空,");
			num++;
		}
		if(null==jobDescription||"".equals(jobDescription)){
			sb.append("职位描述不能为空,");
			num++;
		}
		if(null==jobRemark||"".equals(jobRemark)){
			sb.append("职位备注不能为空,");
			num++;
		}
		if(null==jobStatus||"".equals(jobStatus)){
			sb.append("是否启用不能为空,");
			num++;
		}
		sb.setLength(sb.length()-1);
		if(0==num){
			int update = jobMapper.update(entity);
			if(update>0){
				sb=new StringBuffer("编辑成功！");
			}
		}
		info.put("job_edit_msg", sb.toString());
		return info;
	}

	/**
	 * 删除数据
	 */
	@Override
	public Map<String, Object> deleteJob(Long[] job_id) {
		Map<String, Object> info=new HashMap<>();
		String msg="删除失败!";
		try {
			if(job_id.length>0){
				String jobIdStr = Arrays.toString(job_id);
				String jobIds = jobIdStr.substring(1, jobIdStr.length()-1);
				int delete = jobMapper.delete(jobIds);
				if(delete>0){
					msg="删除成功！";
				}
			}
		} catch (Exception e) {
			msg="未知异常";
		}
		info.put("job_list_msg", msg);
		return info;
	}
	
	/**
	 * 查看数据
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
			info.put("job_detailed_msg", "查看明细失败,未找到此职位");
		}
		info.put("job", job);
		return info;
	}

}
