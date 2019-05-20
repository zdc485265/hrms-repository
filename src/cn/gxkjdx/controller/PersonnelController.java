package cn.gxkjdx.controller;

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

import cn.gxkjdx.service.PersonnelService;
import cn.gxkjdx.util.Page;

@Controller
@Scope(value="request")
@RequestMapping(value="personnel")
public class PersonnelController {
	
	private static final Logger logger =LogManager.getLogger(PersonnelController.class);
	
	@Autowired
	PersonnelService personnelService;

	/**
	 * 页面列表
	 * @param map
	 * @return
	 */
	@RequestMapping(value="toPersonnelList")
	public Object toPersonnelList(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("personnelController-------------------toPersonnelList:"+map);
		String index =(String) map.get("index");
		Page page = personnelService.findPersonnelByPage(null,index, null);
		request.setAttribute("page", page);
		return "personnelList";
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="toAddPersonnel")
	public Object toAddPersonnel(HttpServletRequest request){
		logger.debug("personnelController-------------------toAddPersonnel:");
		Map<String, Object> info = personnelService.toAddPersonnel();
		request.setAttribute("depts", info.get("depts"));
		request.setAttribute("salarys", info.get("salarys"));
		request.setAttribute("newDate", info.get("newDate"));
		return "personnelAdd";
	}
	
	/**
	 * 添加数据
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addPersonnel")
	public Object addPersonnel(@RequestParam Map<String, Object> entity,HttpServletRequest request){
		logger.debug("personnelController-------------------addPersonnel:");
		Map<String, Object> info = personnelService.addPersonnel(entity);
		Object rmg = request.getAttribute("personnel_add_msg");
		Object img = info.get("personnel_add_msg");
		if(null==rmg){
			rmg="";
		}
		if(null==img){
			img="";
		}
		request.setAttribute("personnel_add_msg",rmg+" "+ img);
		return toAddPersonnel(request);
	}
	
	/**
	 * 编辑页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toEditPersonnel")
	public Object toEditPersonnel(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("personnelController-------------------toEditPersonnel:"+map);
		Object personnelId=map.get("personnel_id");
		Map<String, Object> info = personnelService.toEditPersonnel(personnelId);
		Object rmsg = request.getAttribute("personnel_edit_msg");
		Object imsg = info.get("personnel_edit_msg");
		if(null==rmsg){
			rmsg="";
		}
		if(null==imsg){
			imsg="";
		}
		request.setAttribute("personnel_edit_msg",rmsg+" "+imsg);
		request.setAttribute("personnel",info.get("personnel"));
		request.setAttribute("jobs", info.get("jobs"));
		request.setAttribute("depts", info.get("depts"));
		request.setAttribute("salarys", info.get("salarys"));
		return "personnelEdit";
	}
	
	/**
	 * 修改数据
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value="editPersonnel")
	public Object editPersonnel(@RequestParam Map<String, Object> entity,HttpServletRequest request){
		logger.debug("personnelController-------------------editPersonnel:"+entity);
		Map<String, Object> info = personnelService.editPersonnel(entity);
		request.setAttribute("personnel_edit_msg", info.get("personnel_edit_msg"));
		return toEditPersonnel(entity, request);
	}
	
	/**
	 * 明细页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toPersonnelDetailed")
	public Object toPersonnelDetailed(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("personnelController-------------------toPersonnelDetailed:"+map);
		Object personnelId = map.get("personnel_id");
		Map<String, Object> info = personnelService.toPersonnelDetailed(personnelId);
		request.setAttribute("personnel", info.get("personnel"));
		request.setAttribute("jobs", info.get("jobs"));
		request.setAttribute("depts", info.get("depts"));
		request.setAttribute("salarys", info.get("salarys"));
		request.setAttribute("personnel_detailed_msg", info.get("personnel_detailed_msg"));
		return "personnelDetailed";
	}
	
	/**
	 * 删除数据
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deletePersonnel")
	public Object deletePersonnel(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("personnelController-------------------deletePersonnel:"+map);
		Map<String, Object> info = personnelService.deletePersonnel(map);
		request.setAttribute("personnel_list_msg", info.get("personnel_list_msg"));
		return toPersonnelList(new HashMap<>(), request);
	}
	
	/**
	 * 删除数据
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="foreverDeletePersonnel")
	public Object foreverDeletePersonnel(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("personnelController-------------------deletePersonnel:"+map);
		Map<String, Object> info = personnelService.foreverDeletePersonnel(map);
		request.setAttribute("personnel_list_msg", info.get("personnel_list_msg"));
		return toPersonnelList(new HashMap<>(), request);
	}
	
	/**
	 * 删除数据
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="recoveryDeletePersonnel")
	public Object recoveryDeletePersonnel(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("personnelController-------------------deletePersonnel:"+map);
		Map<String, Object> info = personnelService.recoveryDeletePersonnel(map);
		request.setAttribute("personnel_list_msg", info.get("personnel_list_msg"));
		return toPersonnelList(new HashMap<>(), request);
	}
}
