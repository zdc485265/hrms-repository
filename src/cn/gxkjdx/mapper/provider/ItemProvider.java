package cn.gxkjdx.mapper.provider;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ItemProvider {
	
	private static final Logger logger =LogManager.getLogger(ItemProvider.class);
	
	public String findByIds(String itemIds){
		return "SELECT * FROM tb_item WHERE item_id in("+itemIds+")";
	}
	
	public String findByConditionToPage(@Param("entity") Map<String, Object> entity ,@Param("start") Object start,@Param("size") Object size) {
		String sql="SELECT * FROM tb_item WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("item_id")!= null) {
				builder.append(" AND  item_id=#{entity.item_id}");
			}
			
			if(entity.get("item_name")!= null) {
				builder.append(" AND  item_name like CONCAT('%',#{entity.item_name},'%')");
			}
			
			if(entity.get("item_sort")!= null) {
				builder.append(" AND  item_sort=#{entity.item_sort}");
			}
			
		}
		builder.append(" LIMIT #{start},#{size}");
		logger.debug("权限模糊查询："+builder.toString());
		return builder.toString();
	}
	
	public String countByCondition(Map<String, Object> entity) {
		String sql="SELECT count(*) FROM tb_item WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("item_id")!= null) {
				builder.append(" AND  item_id=#{item_id}");
			}
			
			if(entity.get("item_name")!= null) {
				builder.append(" AND  item_name like CONCAT('%',#{item_name},'%')");
			}
			
			if(entity.get("item_sort")!= null) {
				builder.append(" AND  item_sort=#{item_sort}");
			}
		}
		logger.debug("权限模糊查询："+builder.toString());
		
		return builder.toString();
	}
	
	public String deleteItemByIds(String itemIds){
		return "DELETE FROM tb_item WHERE item_id in ("+itemIds+")";
	}
}
