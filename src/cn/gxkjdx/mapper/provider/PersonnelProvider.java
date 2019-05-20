package cn.gxkjdx.mapper.provider;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PersonnelProvider {
	
	private static final Logger logger =LogManager.getLogger(PersonnelProvider.class);
	
	
	public String findByConditionToPage(@Param("entity") Map<String, Object> entity ,@Param("start") Object start,@Param("size") Object size) {
		String sql="SELECT * FROM tb_personnel WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("personnel_id")!= null) {
				builder.append(" AND  personnel_id=#{entity.personnel_id}");
			}
			
			if(entity.get("personnel_name")!= null) {
				builder.append(" AND  personnel_name=#{entity.personnel_name}");
			}
			
		}
		builder.append(" LIMIT #{start},#{size}");
		logger.debug("模糊查询："+builder.toString());
		return builder.toString();
	}
	
	public String countByCondition(Map<String, Object> entity) {
		String sql="SELECT count(*) FROM tb_personnel WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("personnel_id")!= null) {
				builder.append(" AND  personnel_id=#{personnel_id}");
			}
			
			if(entity.get("personnel_name")!= null) {
				builder.append(" AND  personnel_num=#{personnel_name}");
			}
		}
		logger.debug("模糊查询："+builder.toString());
		
		return builder.toString();
	}
}
