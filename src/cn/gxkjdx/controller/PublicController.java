package cn.gxkjdx.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Scope(value="request")
public class PublicController{
	
	private static final Logger logger =LogManager.getLogger(PersonnelController.class);
	

	@RequestMapping(value="/index")
	public String login(){
		System.out.println("start to index");
		return "index";
	}
	
	/**
	 * 上传简历
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value="upload")
	@ResponseBody
	public Object upload(MultipartFile file,HttpServletRequest request){
		logger.debug("personnelController-------------------upload:"+file);
		logger.debug("文件名："+file.getOriginalFilename());
		logger.debug(request.getContextPath());
		//创建一个文件
		String fileName=new Date().getTime()+file.getOriginalFilename();
		File destFile=new File("D:\\file\\"+fileName);
		Map<String, Object> info=new HashMap<>();
		info.put("msgStatus", 1);
		info.put("msgInfo", fileName);
		//将文件写入destFile
		try {
			file.transferTo(destFile);
			info.put("msgStatus", 0);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return info;
	}
	
	
	/**
	 * 获取简历
	 * @param personnelInfo
	 * @return
	 */
	@RequestMapping(value="download")
	public void download(String resumeInfo,HttpServletResponse response){
		logger.debug("personnelController-------------------download:"+resumeInfo);
		if(null!=resumeInfo&&!"".equals(resumeInfo)){
			try {
				File file =new File("d:\\file\\"+URLDecoder.decode(resumeInfo, "UTF-8"));
				byte[] array = FileUtils.readFileToByteArray(file);
				response.addHeader("Content-Disposition", "attachment;filename="+new String(resumeInfo.getBytes(),"ISO-8859-1"));
				response.getOutputStream().write(array);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
