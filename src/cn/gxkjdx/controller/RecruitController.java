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
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gxkjdx.service.RecruitService;
import cn.gxkjdx.util.Page;

@Controller
@Scope(value="request")
@RequestMapping(value="recruit")
public class RecruitController {
	
	private static final Logger logger =LogManager.getLogger(RecruitController.class);
	
	@Autowired
	RecruitService recruitService;

	/**
	 * 页面列表
	 * @param map
	 * @return
	 */
	@RequestMapping(value="toRecruitList")
	public Object toRecruitList(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("recruitController-------------------toRecruitList:"+map);
		String index =(String) map.get("index");
		Page page = recruitService.findRecruitByPage(null,index, null);
		request.setAttribute("page", page);
		return "recruitList";
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="toAddRecruit")
	public Object toAddRecruit(HttpServletRequest request){
		logger.debug("recruitController-------------------toAddRecruit");
		Map<String, Object> info = recruitService.toAddRecruit();
		request.setAttribute("depts", info.get("depts"));
		request.setAttribute("newDate", info.get("newDate"));
		return "recruitAdd";
	}
	
	/**
	 * 添加数据
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addRecruit")
	public Object addRecruit(@RequestParam Map<String, Object> entity,HttpServletRequest request){
		logger.debug("recruitController-------------------addRecruit");
		Map<String, Object> info = recruitService.addRecruit(entity);
		Object rmg = request.getAttribute("recruit_add_msg");
		Object img = info.get("recruit_add_msg");
		if(null==rmg){
			rmg="";
		}
		if(null==img){
			img="";
		}
		request.setAttribute("recruit_add_msg",rmg+" "+ img);
		return toAddRecruit(request);
	}
	
	/**
	 * 编辑页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toEditRecruit")
	public Object toEditRecruit(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("recruitController-------------------toEditRecruit:"+map);
		Object recruitId=map.get("recruit_id");
		Map<String, Object> info = recruitService.toEditRecruit(recruitId);
		Object rmsg = request.getAttribute("recruit_edit_msg");
		Object imsg = info.get("recruit_edit_msg");
		if(null==rmsg){
			rmsg="";
		}
		if(null==imsg){
			imsg="";
		}
		request.setAttribute("recruit_edit_msg",rmsg+" "+imsg);
		request.setAttribute("recruit",info.get("recruit"));
		request.setAttribute("depts", info.get("depts"));
		request.setAttribute("jobs", info.get("jobs"));
		return "recruitEdit";
	}
	
	/**
	 * 修改数据
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value="editRecruit")
	public Object editRecruit(@RequestParam Map<String, Object> entity,HttpServletRequest request){
		logger.debug("recruitController-------------------editRecruit:"+entity);
		Map<String, Object> info = recruitService.editRecruit(entity);
		request.setAttribute("recruit_edit_msg", info.get("recruit_edit_msg"));
		return toEditRecruit(entity, request);
	}
	
	/**
	 * 明细页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toRecruitDetailed")
	public Object toRecruitDetailed(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("recruitController-------------------toRecruitDetailed:"+map);
		Object recruitId = map.get("recruit_id");
		Map<String, Object> info = recruitService.toRecruitDetailed(recruitId);
		request.setAttribute("recruit", info.get("recruit"));
		request.setAttribute("recruit_detailed_msg", info.get("recruit_detailed_msg"));
		return "recruitDetailed";
	}
	
	/**
	 * 删除数据
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteRecruit")
	public Object deleteRecruit(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("recruitController-------------------deleteRecruit:"+map);
		Map<String, Object> info = recruitService.deleteRecruit(map.get("recruit_id"));
		request.setAttribute("recruit_list_msg", info.get("recruit_list_msg"));
		return toRecruitList(new HashMap<>(), request);
	}
	
	/**
	 * 查找jobs
	 * @param deptId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="findJobs")
	@ResponseBody
	public Object findJobs(Long deptId,HttpServletRequest request){
		logger.debug("recruitController-------------------findJobs:"+deptId);
		Map<String, Object> info = recruitService.findJobs(deptId);
		return info;
	}
	
	/**
	 * 查找job
	 * @param jobId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="findJob")
	@ResponseBody
	public Object findJob(Long jobId,HttpServletRequest request){
		logger.debug("recruitController-------------------findJob:"+jobId);
		Map<String, Object> info = recruitService.findJob(jobId);
		return info;
	}
}
