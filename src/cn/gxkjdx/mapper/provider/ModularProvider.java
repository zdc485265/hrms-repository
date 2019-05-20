package cn.gxkjdx.mapper.provider;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ModularProvider {
	
	private static final Logger logger =LogManager.getLogger(ModularProvider.class);
	
	public String findByIds(String modularIds){
		return "SELECT * FROM tb_modular WHERE modular_id in("+modularIds+")";
	}
	
	public String findByConditionToPage(@Param("entity") Map<String, Object> entity ,@Param("start") Object start,@Param("size") Object size) {
		String sql="SELECT * FROM tb_modular WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("modular_id")!= null) {
				builder.append(" AND  modular_id=#{entity.modular_id}");
			}
			
			if(entity.get("modular_name")!= null) {
				builder.append(" AND  modular_name like CONCAT('%',#{entity.modular_name},'%')");
			}
			
			if(entity.get("modular_sort")!= null) {
				builder.append(" AND  modular_sort=#{entity.modular_sort}");
			}
			
		}
		builder.append(" LIMIT #{start},#{size}");
		logger.debug("权限模糊查询："+builder.toString());
		return builder.toString();
	}
	
	public String countByCondition(Map<String, Object> entity) {
		String sql="SELECT count(*) FROM tb_modular WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("modular_id")!= null) {
				builder.append(" AND  modular_id=#{modular_id}");
			}
			
			if(entity.get("modular_name")!= null) {
				builder.append(" AND  modular_name like CONCAT('%',#{modular_name},'%')");
			}
			
			if(entity.get("modular_sort")!= null) {
				builder.append(" AND  modular_sort=#{modular_sort}");
			}
		}
		logger.debug("权限模糊查询："+builder.toString());
		
		return builder.toString();
	}
	
	public String deleteModularByIds(String modularIds){
		return "DELETE FROM tb_modular WHERE modular_id in ("+modularIds+")";
	}
}
