package cn.gxkjdx.mapper.provider;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PowerProvider {
	
	private static final Logger logger =LogManager.getLogger(PowerProvider.class);
	
	public String findByIds(String powerIds){
		return "SELECT * FROM tb_power WHERE power_id in("+powerIds+")";
	}
	
	public String findByConditionToPage(@Param("entity") Map<String, Object> entity ,@Param("start") Object start,@Param("size") Object size) {
		String sql="SELECT * FROM tb_power WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("power_id")!= null) {
				builder.append(" AND  power_id=#{entity.power_id}");
			}
			
			if(entity.get("power_name")!= null) {
				builder.append(" AND  power_name like CONCAT('%',#{entity.power_name},'%')");
			}
			
			if(entity.get("power_action")!= null) {
				builder.append(" AND  power_action like CONCAT('%',#{entity.power_action},'%')");
			}
			
			if(entity.get("power_parent")!= null) {
				builder.append(" AND  power_parent=#{entity.power_parent}");
			}
			
			if(entity.get("power_is_show")!= null) {
				builder.append(" AND  power_is_show=#{entity.power_is_show}");
			}
			
			if(entity.get("modular_id")!= null) {
				builder.append(" AND  modular_id=#{entity.modular_id}");
			}
			
		}
		builder.append(" LIMIT #{start},#{size}");
		logger.debug("权限模糊查询："+builder.toString());
		return builder.toString();
	}
	
	public String countByCondition(Map<String, Object> entity) {
		String sql="SELECT count(*) FROM tb_power WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("power_id")!= null) {
				builder.append(" AND  power_id=#{power_id}");
			}
			
			if(entity.get("power_name")!= null) {
				builder.append(" AND  power_name like CONCAT('%',#{power_name},'%')");
			}
			
			if(entity.get("power_action")!= null) {
				builder.append(" AND  power_action like CONCAT('%',#{power_action},'%')");
			}
			
			if(entity.get("power_parent")!= null) {
				builder.append(" AND  power_parent=#{power_parent}");
			}
			
			if(entity.get("power_is_show")!= null) {
				builder.append(" AND  power_is_show=#{power_is_show}");
			}
			
			if(entity.get("modular_id")!= null) {
				builder.append(" AND  modular_id=#{modular_id}");
			}
			
			
		}
		logger.debug("权限模糊查询："+builder.toString());
		
		return builder.toString();
	}
	
	public String deletePowerByIds(String powerIds){
		return "DELETE FROM tb_power WHERE power_id in ("+powerIds+")";
	}
}
