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
import cn.gxkjdx.service.ItemService;
import cn.gxkjdx.util.Page;

@Controller
@Scope(value="request")
@RequestMapping(value="item")
public class ItemController {
	
	private static final Logger logger =LogManager.getLogger(ItemController.class);
	
	@Autowired
	ItemService itemService;

	/**
	 * 页面列表
	 * @param map
	 * @return
	 */
	@RequestMapping(value="toItemList")
	public Object toItemList(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("itemController-------------------toItemList:"+map);
		String index =(String) map.get("index");
		Page page = itemService.findItemByPage(null,index, null);
		request.setAttribute("page", page);
		return "itemList";
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="toAddItem")
	public Object toAddItem(HttpServletRequest request){
		logger.debug("itemController-------------------toAddItem");
		return "itemAdd";
	}
	
	/**
	 * 添加数据
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addItem")
	public Object addItem(@RequestParam Map<String, Object> entity,HttpServletRequest request){
		logger.debug("itemController-------------------addItem");
		Map<String, Object> info = itemService.addItem(entity);
		request.setAttribute("item_add_msg", info.get("item_add_msg"));
		return toAddItem(request);
	}
	
	/**
	 * 编辑页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toEditItem")
	public Object toEditItem(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("itemController-------------------toEditItem:"+map);
		Object itemId=map.get("item_id");
		Map<String, Object> info = itemService.toEditItem(itemId);
		Object rmsg = request.getAttribute("item_edit_msg");
		Object imsg = info.get("item_edit_msg");
		if(null==rmsg){
			rmsg="";
		}
		if(null==imsg){
			imsg="";
		}
		request.setAttribute("item_edit_msg",rmsg+" "+ imsg);
		request.setAttribute("item",info.get("item"));
		return "itemEdit";
	}
	
	/**
	 * 修改数据
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value="editItem")
	public Object editItem(@RequestParam Map<String, Object> entity,HttpServletRequest request){
		logger.debug("itemController-------------------editItem:"+entity);
		Map<String, Object> info = itemService.editItem(entity);
		request.setAttribute("item_edit_msg", info.get("item_edit_msg"));
		return toEditItem(entity, request);
	}
	
	/**
	 * 删除数据
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteItem")
	public Object deleteItem(Long[] item_id,HttpServletRequest request){
		logger.debug("itemController-------------------deleteItem:"+Arrays.toString(item_id));
		Map<String, Object> info = itemService.deleteItem(item_id);
		request.setAttribute("item_list_msg", info.get("item_list_msg"));
		return toItemList(new HashMap<>(), request);
	}
}
