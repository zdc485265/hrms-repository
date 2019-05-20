package cn.gxkjdx.mapper.provider;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RoleProvider {
	
	private static final Logger logger =LogManager.getLogger(RoleProvider.class);
	
	public String findByIds(String roleIds){
		return "SELECT * FROM tb_role WHERE role_id in("+roleIds+")";
	}
	
	public String findByConditionToPage(@Param("entity") Map<String, Object> entity ,@Param("start") Object start,@Param("size") Object size) {
		String sql="SELECT * FROM tb_role WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("role_id")!= null) {
				builder.append(" AND  role_id=#{entity.role_id}");
			}
			
			if(entity.get("role_name")!= null) {
				builder.append(" AND  role_name like CONCAT('%',#{entity.role_name},'%')");
			}
			
			if(entity.get("role_powers")!= null) {
				builder.append(" AND  role_powers=#{entity.role_powers}");
			}
			
		}
		builder.append(" LIMIT #{start},#{size}");
		logger.debug("权限模糊查询："+builder.toString());
		return builder.toString();
	}
	
	public String countByCondition(Map<String, Object> entity) {
		String sql="SELECT count(*) FROM tb_role WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("role_id")!= null) {
				builder.append(" AND  role_id=#{role_id}");
			}
			
			if(entity.get("role_name")!= null) {
				builder.append(" AND  role_name like CONCAT('%',#{role_name},'%')");
			}
			
			if(entity.get("role_powers")!= null) {
				builder.append(" AND  role_powers=#{role_powers}");
			}
		}
		logger.debug("权限模糊查询："+builder.toString());
		
		return builder.toString();
	}
	
	public String deleteRoleByIds(String roleIds){
		return "DELETE FROM tb_role WHERE role_id in ("+roleIds+")";
	}
}
