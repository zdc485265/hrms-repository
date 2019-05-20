package cn.gxkjdx.service;

import java.util.Map;

import cn.gxkjdx.util.Page;

public interface JobService {

	Page findJobByPage(Map<String, Object> entity,String index,String size);
	Map<String, Object> toAddJob();
	Map<String, Object> addJob(Map<String, Object> entity);
	Map<String, Object> toEditJob(Object jobId);
	Map<String, Object> editJob(Map<String, Object> entity);
	Map<String, Object> deleteJob(Long[] job_id);
	Map<String, Object> toJobDetailed(Object jobId);
}
