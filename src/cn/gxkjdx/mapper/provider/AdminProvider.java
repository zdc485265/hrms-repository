package cn.gxkjdx.mapper.provider;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class AdminProvider {
	
	private static final Logger logger =LogManager.getLogger(AdminProvider.class);
	
	public String findByIds(String adminIds){
		return "SELECT * FROM tb_admin WHERE admin_id in("+adminIds+")";
	}
	
	public String findByConditionToPage(@Param("entity") Map<String, Object> entity ,@Param("start") Object start,@Param("size") Object size) {
		String sql="SELECT * FROM tb_admin WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("admin_id")!= null) {
				builder.append(" AND  admin_id=#{entity.admin_id}");
			}
			
			if(entity.get("admin_name")!= null) {
				builder.append(" AND  admin_name like CONCAT('%',#{entity.admin_name},'%')");
			}
			
			if(entity.get("admin_account")!= null) {
				builder.append(" AND  admin_account like CONCAT('%',#{entity.admin_account},'%')");
			}
			
			if(entity.get("admin_pwd")!= null) {
				builder.append(" AND  admin_pwd=#{entity.admin_pwd}");
			}
			
			if(entity.get("admin_status")!= null) {
				builder.append(" AND  admin_status=#{entity.admin_status}");
			}
			
			if(entity.get("role_id")!= null) {
				builder.append(" AND  role_id=#{entity.role_id}");
			}
			
			if(entity.get("dept_id")!= null) {
				builder.append(" AND  dept_id=#{entity.dept_id}");
			}
			
		}
		builder.append(" LIMIT #{start},#{size}");
		logger.debug("权限模糊查询："+builder.toString());
		return builder.toString();
	}
	
	public String countByCondition(Map<String, Object> entity) {
		String sql="SELECT count(*) FROM tb_admin WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("admin_id")!= null) {
				builder.append(" AND  admin_id=#{admin_id}");
			}
			
			if(entity.get("admin_name")!= null) {
				builder.append(" AND  admin_name like CONCAT('%',#{admin_name},'%')");
			}
			
			if(entity.get("admin_account")!= null) {
				builder.append(" AND  admin_account like CONCAT('%',#{admin_account},'%')");
			}
			
			if(entity.get("admin_pwd")!= null) {
				builder.append(" AND  admin_pwd=#{admin_pwd}");
			}
			
			if(entity.get("admin_status")!= null) {
				builder.append(" AND  admin_status=#{admin_status}");
			}
			
			if(entity.get("role_id")!= null) {
				builder.append(" AND  role_id=#{role_id}");
			}
			
			if(entity.get("dept_id")!= null) {
				builder.append(" AND  dept_id=#{dept_id}");
			}
			
		}
		logger.debug("权限模糊查询："+builder.toString());
		
		return builder.toString();
	}
	
	public String deleteAdminByIds(String adminIds){
		return "DELETE FROM tb_admin WHERE admin_id in ("+adminIds+")";
	}
}
