package cn.gxkjdx.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.gxkjdx.service.ResumeService;
import cn.gxkjdx.util.Page;

@Controller
@Scope(value="request")
@RequestMapping(value="resume")
public class ResumeController {
	
	private static final Logger logger =LogManager.getLogger(ResumeController.class);
	
	@Autowired
	ResumeService resumeService;

	/**
	 * 页面列表
	 * @param map
	 * @return
	 */
	@RequestMapping(value="toResumeList")
	public Object toResumeList(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("resumeController-------------------toResumeList:"+map);
		String index =(String) map.get("index");
		Page page = resumeService.findResumeByPage(null,index, null);
		request.setAttribute("page", page);
		return "resumeList";
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="toAddResume")
	public Object toAddResume(HttpServletRequest request){
		logger.debug("resumeController-------------------toAddResume:");
		Map<String, Object> info = resumeService.toAddResume();
		request.setAttribute("recruits", info.get("recruits"));
		request.setAttribute("newDate", info.get("newDate"));
		return "resumeAdd";
	}
	
	/**
	 * 添加数据
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addResume")
	public Object addResume(@RequestParam Map<String, Object> entity,HttpServletRequest request){
		logger.debug("resumeController-------------------addResume:");
		Map<String, Object> info = resumeService.addResume(entity);
		Object rmg = request.getAttribute("resume_add_msg");
		Object img = info.get("resume_add_msg");
		if(null==rmg){
			rmg="";
		}
		if(null==img){
			img="";
		}
		request.setAttribute("resume_add_msg",rmg+" "+ img);
		return toAddResume(request);
	}
	
	/**
	 * 编辑页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toEditResume")
	public Object toEditResume(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("resumeController-------------------toEditResume:"+map);
		Object resumeId=map.get("resume_id");
		Map<String, Object> info = resumeService.toEditResume(resumeId);
		Object rmsg = request.getAttribute("resume_edit_msg");
		Object imsg = info.get("resume_edit_msg");
		if(null==rmsg){
			rmsg="";
		}
		if(null==imsg){
			imsg="";
		}
		request.setAttribute("resume_edit_msg",rmsg+" "+imsg);
		request.setAttribute("resume",info.get("resume"));
		request.setAttribute("recruits", info.get("recruits"));
		return "resumeEdit";
	}
	
	/**
	 * 修改数据
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value="editResume")
	public Object editResume(@RequestParam Map<String, Object> entity,HttpServletRequest request){
		logger.debug("resumeController-------------------editResume:"+entity);
		Map<String, Object> info = resumeService.editResume(entity);
		request.setAttribute("resume_edit_msg", info.get("resume_edit_msg"));
		return toEditResume(entity, request);
	}
	
	/**
	 * 明细页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toResumeDetailed")
	public Object toResumeDetailed(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("resumeController-------------------toResumeDetailed:"+map);
		Object resumeId = map.get("resume_id");
		Map<String, Object> info = resumeService.toResumeDetailed(resumeId);
		request.setAttribute("resume", info.get("resume"));
		request.setAttribute("recruits", info.get("recruits"));
		request.setAttribute("resume_detailed_msg", info.get("resume_detailed_msg"));
		return "resumeDetailed";
	}
	
	/**
	 * 删除数据
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteResume")
	public Object deleteResume(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("resumeController-------------------deleteResume:"+map);
		Map<String, Object> info = resumeService.deleteResume(map.get("resume_id"));
		request.setAttribute("resume_list_msg", info.get("resume_list_msg"));
		return toResumeList(new HashMap<>(), request);
	}
}
