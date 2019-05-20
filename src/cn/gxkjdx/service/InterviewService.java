package cn.gxkjdx.service;

import java.util.Map;

import cn.gxkjdx.util.Page;


public interface InterviewService {

	Page findResumeByPage(Map<String, Object> entity,String index,String size);
	Map<String, Object> toRegisterInterview(Object resumeId);
	Map<String, Object> registerInterview(Map<String, Object> entity);
	Map<String, Object> deleteInterview(Object resumeId);
	Map<String, Object> toInterviewDetailed(Object resumeId);
}
