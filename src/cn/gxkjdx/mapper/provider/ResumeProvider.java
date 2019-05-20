package cn.gxkjdx.mapper.provider;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ResumeProvider {
	
	private static final Logger logger =LogManager.getLogger(ResumeProvider.class);
	
	public String findByIds(String resumeIds){
		return "SELECT * FROM tb_resume WHERE resume_id in("+resumeIds+")";
	}
	
	public String findByConditionToPage(@Param("entity") Map<String, Object> entity ,@Param("start") Object start,@Param("size") Object size) {
		String sql="SELECT * FROM tb_resume WHERE resume_status in("+entity.get("resume_in")+") ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("resume_id")!= null) {
				builder.append(" AND  resume_id=#{entity.resume_id}");
			}
			
			if(entity.get("resume_name")!= null) {
				builder.append(" AND  resume_name=#{entity.resume_name}");
			}
			
		}
		builder.append(" LIMIT #{start},#{size}");
		logger.debug("模糊查询："+builder.toString());
		return builder.toString();
	}
	
	public String countByCondition(Map<String, Object> entity) {
		String sql="SELECT count(*) FROM tb_resume WHERE resume_status in("+entity.get("resume_in")+") ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("resume_id")!= null) {
				builder.append(" AND  resume_id=#{resume_id}");
			}
			
			if(entity.get("resume_name")!= null) {
				builder.append(" AND  resume_num=#{resume_name}");
			}
		}
		logger.debug("模糊查询："+builder.toString());
		
		return builder.toString();
	}
	
}
