package cn.gxkjdx.service;

import java.util.Map;

import cn.gxkjdx.util.Page;

public interface ResumeService {

	Page findResumeByPage(Map<String, Object> entity,String index,String size);
	Map<String, Object> toAddResume();
	Map<String, Object> addResume(Map<String, Object> entity);
	Map<String, Object> toEditResume(Object resumeId);
	Map<String, Object> editResume(Map<String, Object> entity);
	Map<String, Object> deleteResume(Object resumeId);
	Map<String, Object> toResumeDetailed(Object resumeId);
}
