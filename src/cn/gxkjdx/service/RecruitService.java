package cn.gxkjdx.service;

import java.util.Map;

import cn.gxkjdx.util.Page;

public interface RecruitService {

	Page findRecruitByPage(Map<String, Object> entity,String index,String size);
	Map<String, Object> toAddRecruit();
	Map<String, Object> addRecruit(Map<String, Object> entity);
	Map<String, Object> toEditRecruit(Object recruitId);
	Map<String, Object> editRecruit(Map<String, Object> entity);
	Map<String, Object> deleteRecruit(Object recruitId);
	Map<String, Object> toRecruitDetailed(Object recruitId);
	Map<String, Object> findJobs(Object deptId);
	Map<String, Object> findJob(Object jobId);
}
