package cn.gxkjdx.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import cn.gxkjdx.mapper.provider.ItemProvider;

@Repository
public interface ItemMapper {

	/**
	 * 查询所有数据
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_item ORDER BY item_sort")
	List<Map<String, Object>> findAll();
	
	/**
	 * 通过id查找数据
	 * @param itemId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_item WHERE item_id=#{itemId}")
	Map<String, Object> findById(Object itemId);
	
	/**
	 * 查找所有id集的item集
	 * @param itemIds
	 * @return
	 */
	//@Select(value="SELECT * FROM tb_item WHERE item_id in(${itemIds})")
	@SelectProvider(type = ItemProvider.class, method="findByIds")
	List<Map<String, Object>> findByIds(String itemIds);
	
	/**
	 * 恍惚查找start开始size个数据
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	@SelectProvider(type = ItemProvider.class, method="findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") Object start,@Param("size") Object size);

	/**
	 * 恍惚查找数据个数
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = ItemProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);
	
	
	/**
	 * 通过name查询数据
	 * @param itemName
	 * @return
	 */
	@Select(value="SELECT * FROM tb_item WHERE item_name=#{itemName}")
	Map<String, Object> findByName(Object itemName);
	
	/**
	 *  增加数据
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_item (item_name, item_sort) VALUES (#{item_name},#{item_sort})")
	int insert(Map<String, Object> entity);
	
	/**
	 * 修改数据
	 */
	@Update(value="UPDATE tb_item SET item_name=#{item_name}, item_sort=#{item_sort} WHERE item_id=#{item_id}")
	int update(Map<String, Object> entity);
	
	/**
	 * 删除数据
	 */
	@DeleteProvider(type=ItemProvider.class,method="deleteItemByIds")
	int delete(String itemIds);
}
