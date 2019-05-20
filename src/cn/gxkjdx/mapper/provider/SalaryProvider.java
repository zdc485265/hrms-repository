package cn.gxkjdx.mapper.provider;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class SalaryProvider {
	
	private static final Logger logger =LogManager.getLogger(SalaryProvider.class);
	
	
	public String findByConditionToPage(@Param("entity") Map<String, Object> entity ,@Param("start") Object start,@Param("size") Object size) {
		String sql="SELECT * FROM tb_salary WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("salary_id")!= null) {
				builder.append(" AND  salary_id=#{entity.salary_id}");
			}
			
			if(entity.get("salary_name")!= null) {
				builder.append(" AND  salary_name like CONCAT('%',#{entity.salary_name},'%')");
			}
			
			if(entity.get("salary_sort")!= null) {
				builder.append(" AND  salary_sort=#{entity.salary_sort}");
			}
			
		}
		builder.append(" LIMIT #{start},#{size}");
		logger.debug("权限模糊查询："+builder.toString());
		return builder.toString();
	}
	
	public String countByCondition(Map<String, Object> entity) {
		String sql="SELECT count(*) FROM tb_salary WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("salary_id")!= null) {
				builder.append(" AND  salary_id=#{salary_id}");
			}
			
			if(entity.get("salary_name")!= null) {
				builder.append(" AND  salary_name like CONCAT('%',#{salary_name},'%')");
			}
			
			if(entity.get("salary_sort")!= null) {
				builder.append(" AND  salary_sort=#{salary_sort}");
			}
		}
		logger.debug("权限模糊查询："+builder.toString());
		
		return builder.toString();
	}
}
