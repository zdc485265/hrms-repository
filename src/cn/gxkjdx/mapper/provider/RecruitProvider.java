package cn.gxkjdx.mapper.provider;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RecruitProvider {
	
	private static final Logger logger =LogManager.getLogger(RecruitProvider.class);
	
	public String findByIds(String recruitIds){
		return "SELECT * FROM tb_recruit WHERE recruit_id in("+recruitIds+")";
	}
	
	public String findByConditionToPage(@Param("entity") Map<String, Object> entity ,@Param("start") Object start,@Param("size") Object size) {
		String sql="SELECT * FROM tb_recruit WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("recruit_id")!= null) {
				builder.append(" AND  recruit_id=#{entity.recruit_id}");
			}
			
			if(entity.get("recruit_num")!= null) {
				builder.append(" AND  recruit_num=#{entity.recruit_num}");
			}
			
			if(entity.get("recruit_start")!= null) {
				builder.append(" AND  recruit_start=#{entity.recruit_start}");
			}
			
			if(entity.get("recruit_end")!= null) {
				builder.append(" AND  recruit_end=#{entity.recruit_end}");
			}
			
			if(entity.get("recruit_req")!= null) {
				builder.append(" AND  recruit_req=#{entity.recruit_req}");
			}
			
			if(entity.get("recruit_sort")!= null) {
				builder.append(" AND  recruit_sort=#{entity.recruit_sort}");
			}
			
			if(entity.get("job_id")!= null) {
				builder.append(" AND  job_id=#{entity.job_id}");
			}
			
			if(entity.get("admin_id")!= null) {
				builder.append(" AND  admin_id=#{entity.admin_id}");
			}
			
		}
		builder.append(" LIMIT #{start},#{size}");
		logger.debug("模糊查询："+builder.toString());
		return builder.toString();
	}
	
	public String countByCondition(Map<String, Object> entity) {
		String sql="SELECT count(*) FROM tb_recruit WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("recruit_id")!= null) {
				builder.append(" AND  recruit_id=#{recruit_id}");
			}
			
			if(entity.get("recruit_num")!= null) {
				builder.append(" AND  recruit_num=#{recruit_num}");
			}
			
			if(entity.get("recruit_start")!= null) {
				builder.append(" AND  recruit_start=#{recruit_start}");
			}
			
			if(entity.get("recruit_end")!= null) {
				builder.append(" AND  recruit_end=#{recruit_end}");
			}
			
			if(entity.get("recruit_req")!= null) {
				builder.append(" AND  recruit_req=#{recruit_req}");
			}
			
			if(entity.get("recruit_sort")!= null) {
				builder.append(" AND  recruit_sort=#{recruit_sort}");
			}
			
			if(entity.get("job_id")!= null) {
				builder.append(" AND  job_id=#{job_id}");
			}
			
			if(entity.get("admin_id")!= null) {
				builder.append(" AND  admin_id=#{admin_id}");
			}
		}
		logger.debug("模糊查询："+builder.toString());
		
		return builder.toString();
	}
}
