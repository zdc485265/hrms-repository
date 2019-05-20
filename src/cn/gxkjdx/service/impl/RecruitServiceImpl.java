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
	 * 获取page
	 */
	@Override
	public Page findRecruitByPage(Map<String, Object> entity,String indexo, String sizeo) {
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
		//获取数据个数
		int count = recruitMapper.countByCondition(entity);
		//构建page
		Page page=new Page(index, size, count, data);
		
		return page;
	}
	/**
	 * 添加数据
	 */
	@Override
	public Map<String, Object> addRecruit(Map<String, Object> entity) {
		Map<String, Object> info=new HashMap<>();
		StringBuffer sb=new StringBuffer("新增失败,");
		Object recruitNum = entity.get("recruit_num");
		Object recruitStart = entity.get("recruit_start");
		Object recruitEnd= entity.get("recruit_end");
		Object recruitSort = entity.get("recruit_sort");
		Object jobId = entity.get("job_id");
		Object adminId = entity.get("admin_id");
		int num=0;
		
		if(null==recruitNum||"".equals(recruitNum)){
			sb.append("招聘人数不能为空,");
			num++;
		}
		
		if(null==recruitStart||"".equals(recruitStart)){
			sb.append("登记日期不能为空,");
			num++;
		}
		
		if(null==recruitEnd||"".equals(recruitEnd)){
			sb.append("截至日期不能为空,");
			num++;
		}
		if(null==recruitSort||"".equals(recruitSort)){
			sb.append("招聘类型不能为空,");
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
			int insert = recruitMapper.insert(entity);
			if(insert>0){
				sb=new StringBuffer("新增成功！");
			}
		}
		info.put("recruit_add_msg", sb.toString());
		return info;
	}

	/**
	 * 获取添加页面显示数据
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
	 * 获取修改页面显示数据
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
			info.put("recruit_edit_msg", "出错了,未找到此数据");
		}
		return info;
	}

	/**
	 * 修改数据
	 */
	@Override
	public Map<String, Object> editRecruit(Map<String, Object> entity) {
		Map<String, Object> info = new HashMap<>();
		StringBuffer sb =new StringBuffer("编辑失败,");
		Object recruitNum = entity.get("recruit_num");
		Object recruitStart = entity.get("recruit_start");
		Object recruitEnd= entity.get("recruit_end");
		Object recruitSort = entity.get("recruit_sort");
		Object jobId = entity.get("job_id");
		Object adminId = entity.get("admin_id");
		int num=0;
		
		if(null==recruitNum||"".equals(recruitNum)){
			sb.append("招聘人数不能为空,");
			num++;
		}
		
		if(null==recruitStart||"".equals(recruitStart)){
			sb.append("登记日期不能为空,");
			num++;
		}
		
		if(null==recruitEnd||"".equals(recruitEnd)){
			sb.append("截至日期不能为空,");
			num++;
		}
		
		if(null==recruitSort||"".equals(recruitSort)){
			sb.append("招聘类型不能为空,");
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
			int update = recruitMapper.update(entity);
			if(update>0){
				sb=new StringBuffer("编辑成功！");
			}
		}
		info.put("recruit_edit_msg", sb.toString());
		return info;
	}

	/**
	 * 删除数据
	 */
	@Override
	public Map<String, Object> deleteRecruit(Object recruitId) {
		Map<String, Object> info=new HashMap<>();
		String msg="删除失败!";
		int delete = recruitMapper.delete(recruitId);
		
		if(delete>0){
			msg="删除成功！";
		}
		info.put("recruit_list_msg", msg);
		return info;
	}
	
	/**
	 * 查看数据
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
			info.put("recruit_detailed_msg", "查看明细失败,未找到此招聘信息");
		}
		info.put("recruit", recruit);
		return info;
	}
	
	/**
	 * 查找同一部门的所有职位
	 */
	@Override
	public Map<String, Object> findJobs(Object deptId) {
		Map<String, Object> info=new HashMap<>();
		if(null!=deptId&&!"".equals(deptId)){
			List<Map<String, Object>> jobs = jobMapper.findByDeptId(deptId);
			StringBuffer sb=new StringBuffer("<option value='0'>--请选择--</option>");
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
	 * 查找job
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
