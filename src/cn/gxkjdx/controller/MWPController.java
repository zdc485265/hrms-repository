package cn.gxkjdx.controller;

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

import cn.gxkjdx.service.MWPService;

@Controller
@Scope(value="request")
@RequestMapping(value="mwp")
public class MWPController {
	
	private static final Logger logger =LogManager.getLogger(MWPController.class);
	
	@Autowired
	MWPService mwpService;
	
	/**
	 * ÐÞ¸ÄÃÜÂëÒ³Ãæ
	 * @param map
	 * @return
	 */
	@RequestMapping(value="toEditPwd")
	public Object toEditPwd(){
		logger.debug("MWPController-------------------------toEditPwd");
		return "mwpEditPwd";
	}
	
	/**
	 * ÐÞ¸ÄÃÜÂë
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="editPwd")
	public Object editPwd(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("MWPController-------------------------editPwd:"+map);
		Map<String, Object> info = mwpService.editPwd(map);
		request.setAttribute("mwp_edit_msg",info.get("mwp_edit_msg"));
		return "mwpEditPwd";
	}
	/**
	 * ×¢Ïú
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="undo")
	public Object undo(HttpSession session){
		logger.debug("MWPController-------------------------undo");
		session.setAttribute("admin", null);
		return "redirect:/login.jsp";
	}
}
