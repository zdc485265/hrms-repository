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
import cn.gxkjdx.service.SalaryService;
import cn.gxkjdx.util.Page;

@Controller
@Scope(value="request")
@RequestMapping(value="salary")
public class SalaryController {
	
	private static final Logger logger =LogManager.getLogger(SalaryController.class);
	
	@Autowired
	SalaryService salaryService;

	/**
	 * 页面列表
	 * @param map
	 * @return
	 */
	@RequestMapping(value="toSalaryList")
	public Object toSalaryList(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("salaryController-------------------toSalaryList:"+map);
		String index =(String) map.get("index");
		Page page = salaryService.findSalaryByPage(null,index, null);
		request.setAttribute("page", page);
		return "salaryStandardList";
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="toAddSalary")
	public Object toAddSalary(HttpServletRequest request){
		logger.debug("salaryController-------------------toAddSalary");
		Map<String, Object> info = salaryService.toAddSalary();
		request.setAttribute("items", info.get("items"));
		request.setAttribute("newDate", info.get("newDate"));
		return "salaryStandardAdd";
	}
	
	/**
	 * 添加数据
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addSalary")
	public Object addSalary(@RequestParam Map<String, Object> entity,Long[]item_id,Float[]salary_item_value,HttpServletRequest request){
		logger.debug("salaryController-------------------addSalary:"+entity+","+Arrays.toString(item_id)+","+Arrays.toString(salary_item_value));
		Map<String, Object> info = salaryService.addSalary(entity,item_id,salary_item_value);
		request.setAttribute("salary_add_msg", info.get("salary_add_msg"));
		return toAddSalary(request);
	}
	
	/**
	 * 编辑页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toEditSalary")
	public Object toEditSalary(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("salaryController-------------------toEditSalary:"+map);
		Object salaryId=map.get("salary_id");
		Map<String, Object> info = salaryService.toEditSalary(salaryId);
		Object rmsg = request.getAttribute("salary_edit_msg");
		Object imsg = info.get("salary_edit_msg");
		if(null==rmsg){
			rmsg="";
		}
		if(null==imsg){
			imsg="";
		}
		request.setAttribute("salary_edit_msg",rmsg+" "+ imsg);
		request.setAttribute("salary",info.get("salary"));
		request.setAttribute("items",info.get("items"));
		request.setAttribute("num", info.get("num"));
		return "salaryStandardEdit";
	}
	
	/**
	 * 修改数据
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value="editSalary")
	public Object editSalary(@RequestParam Map<String, Object> entity,Long[]item_id,Float[]salary_item_value,HttpServletRequest request){
		logger.debug("salaryController-------------------editSalary:"+entity+","+Arrays.toString(item_id)+","+Arrays.toString(salary_item_value));
		Map<String, Object> info = salaryService.editSalary(entity,item_id,salary_item_value);
		request.setAttribute("salary_edit_msg", info.get("salary_edit_msg"));
		return toEditSalary(entity, request);
	}
	
	/**
	 * 明细页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toSalaryDetailed")
	public Object toSalaryDetailed(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("salaryController-------------------toEditSalary:"+map);
		Object salaryId=map.get("salary_id");
		Map<String, Object> info = salaryService.toSalaryDetailed(salaryId);
		request.setAttribute("salary_detail_msg",info.get("salary_detail_msg"));
		request.setAttribute("salary",info.get("salary"));
		request.setAttribute("items",info.get("items"));
		request.setAttribute("num", info.get("num"));
		return "salaryStandardDetail";
	}
	
	/**
	 * 删除数据
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteSalary")
	public Object deleteSalary(@RequestParam Map<String,Object> map,HttpServletRequest request){
		logger.debug("salaryController-------------------deleteSalary:"+map);
		Object salaryId = map.get("salary_id");
		Map<String, Object> info = salaryService.deleteSalary(salaryId);
		request.setAttribute("salary_list_msg", info.get("salary_list_msg"));
		return toSalaryList(new HashMap<>(), request);
	}
}
