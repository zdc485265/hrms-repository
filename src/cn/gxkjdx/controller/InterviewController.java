package cn.gxkjdx.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.gxkjdx.service.InterviewService;
import cn.gxkjdx.util.Page;

@Controller
@Scope(value="request")
@RequestMapping(value="interview")
public class InterviewController {
	
	private static final Logger logger =LogManager.getLogger(InterviewController.class);
	
	@Autowired
	InterviewService interviewService;

	/**
	 * 页面列表
	 * @param map
	 * @return
	 */
	@RequestMapping(value="toInterviewList")
	public Object toInterviewList(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("interviewController-------------------toInterviewList:"+map);
		String index =(String) map.get("index");
		Page page = interviewService.findResumeByPage(null,index, null);
		request.setAttribute("page", page);
		return "interviewList";
	}
	
	
	
	/**
	 * 编辑页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toRegisterInterview")
	public Object toRegisterInterview(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("interviewController-------------------toRegisterInterview:"+map);
		Object resumeId=map.get("resume_id");
		Map<String, Object> info = interviewService.toRegisterInterview(resumeId);
		Object rmsg = request.getAttribute("interview_register_msg");
		Object imsg = info.get("interview_register_msg");
		if(null==rmsg){
			rmsg="";
		}
		if(null==imsg){
			imsg="";
		}
		request.setAttribute("interview_register_msg", rmsg+" "+imsg);
		request.setAttribute("resume", info.get("resume"));
		request.setAttribute("recruits", info.get("recruits"));
		request.setAttribute("interviews", info.get("interviews"));
		request.setAttribute("newDate", info.get("newDate"));
		return "interviewRegister";
	}
	
	/**
	 * 修改数据
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value="registerInterview")
	public Object registerInterview(@RequestParam Map<String, Object> entity,HttpServletRequest request,HttpSession session){
		logger.debug("interviewController-------------------registerInterview:"+entity);
		Map<String, Object> admin =(Map<String, Object>) session.getAttribute("admin");
		entity.put("admin_id", admin.get("admin_id"));
		Map<String, Object> info = interviewService.registerInterview(entity);
		request.setAttribute("interview_register_msg", info.get("interview_register_msg"));
		return toRegisterInterview(entity, request);
	}
	
	@RequestMapping(value="toInterviewDetailed")
	public Object toInterviewDetailed(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("interviewController-------------------toInterviewDetailed:"+map);
		Object resumeId = map.get("resume_id");
		Map<String, Object> info = interviewService.toInterviewDetailed(resumeId);
		request.setAttribute("resume", info.get("resume"));
		request.setAttribute("recruits", info.get("recruits"));
		request.setAttribute("interview1", info.get("interview1"));
		request.setAttribute("interview2", info.get("interview2"));
		request.setAttribute("interview3", info.get("interview3"));
		request.setAttribute("interview_detailed_msg", info.get("interview_detailed_msg"));
		return "interviewDetailed";
	}
	
	/**
	 * 删除数据
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteInterview")
	public Object deleteInterview(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("interviewController-------------------deleteInterview:"+map);
		Map<String, Object> info = interviewService.deleteInterview(map.get("resume_id"));
		request.setAttribute("interview_list_msg", info.get("interview_list_msg"));
		return toInterviewList(new HashMap<>(), request);
	}
}
