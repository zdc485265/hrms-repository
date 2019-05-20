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
import cn.gxkjdx.service.JobService;
import cn.gxkjdx.util.Page;

@Controller
@Scope(value="request")
@RequestMapping(value="job")
public class JobController {
	
	private static final Logger logger =LogManager.getLogger(JobController.class);
	
	@Autowired
	JobService jobService;

	/**
	 * 页面列表
	 * @param map
	 * @return
	 */
	@RequestMapping(value="toJobList")
	public Object toJobList(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("jobController-------------------toJobList:"+map);
		String index =(String) map.get("index");
		Page page = jobService.findJobByPage(null,index, null);
		request.setAttribute("page", page);
		return "jobList";
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="toAddJob")
	public Object toAddJob(HttpServletRequest request){
		logger.debug("jobController-------------------toAddJob");
		Map<String, Object> info = jobService.toAddJob();
		request.setAttribute("classifys", info.get("classifys"));
		request.setAttribute("depts", info.get("depts"));
		return "jobAdd";
	}
	
	/**
	 * 添加数据
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addJob")
	public Object addJob(@RequestParam Map<String, Object> entity,HttpServletRequest request){
		logger.debug("jobController-------------------addJob");
		Map<String, Object> info = jobService.addJob(entity);
		request.setAttribute("job_add_msg", info.get("job_add_msg"));
		return toAddJob(request);
	}
	
	/**
	 * 编辑页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toEditJob")
	public Object toEditJob(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("jobController-------------------toEditJob:"+map);
		Object jobId=map.get("job_id");
		Map<String, Object> info = jobService.toEditJob(jobId);
		request.setAttribute("job",info.get("job"));
		request.setAttribute("classifys", info.get("classifys"));
		request.setAttribute("depts", info.get("depts"));
		Object rmsg = request.getAttribute("job_edit_msg");
		Object imsg = info.get("job_edit_msg");
		if(null==rmsg){
			rmsg="";
		}
		if(null==imsg){
			imsg="";
		}
		request.setAttribute("job_edit_msg",rmsg+" "+imsg);
		return "jobEdit";
	}
	
	/**
	 * 修改数据
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value="editJob")
	public Object editJob(@RequestParam Map<String, Object> entity,HttpServletRequest request){
		logger.debug("jobController-------------------editJob:"+entity);
		Map<String, Object> info = jobService.editJob(entity);
		request.setAttribute("job_edit_msg", info.get("job_edit_msg"));
		return toEditJob(entity, request);
	}
	
	/**
	 * 明细页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toJobDetailed")
	public Object toJobDetailed(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("jobController-------------------toJobDetailed:"+map);
		Object jobId = map.get("job_id");
		Map<String, Object> info = jobService.toJobDetailed(jobId);
		request.setAttribute("job", info.get("job"));
		request.setAttribute("job_detailed_msg", info.get("job_detailed_msg"));
		return "jobDetailed";
	}
	
	/**
	 * 删除数据
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteJob")
	public Object deleteJob(Long[] job_id,HttpServletRequest request){
		logger.debug("jobController-------------------deleteJob:"+Arrays.toString(job_id));
		Map<String, Object> info = jobService.deleteJob(job_id);
		request.setAttribute("job_list_msg", info.get("job_list_msg"));
		return toJobList(new HashMap<>(), request);
	}
}
