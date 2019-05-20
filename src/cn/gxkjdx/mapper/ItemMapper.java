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
	 * ��ѯ��������
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_item ORDER BY item_sort")
	List<Map<String, Object>> findAll();
	
	/**
	 * ͨ��id��������
	 * @param itemId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_item WHERE item_id=#{itemId}")
	Map<String, Object> findById(Object itemId);
	
	/**
	 * ��������id����item��
	 * @param itemIds
	 * @return
	 */
	//@Select(value="SELECT * FROM tb_item WHERE item_id in(${itemIds})")
	@SelectProvider(type = ItemProvider.class, method="findByIds")
	List<Map<String, Object>> findByIds(String itemIds);
	
	/**
	 * ��㱲���start��ʼsize������
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	@SelectProvider(type = ItemProvider.class, method="findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") Object start,@Param("size") Object size);

	/**
	 * ��㱲������ݸ���
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = ItemProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);
	
	
	/**
	 * ͨ��name��ѯ����
	 * @param itemName
	 * @return
	 */
	@Select(value="SELECT * FROM tb_item WHERE item_name=#{itemName}")
	Map<String, Object> findByName(Object itemName);
	
	/**
	 *  ��������
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_item (item_name, item_sort) VALUES (#{item_name},#{item_sort})")
	int insert(Map<String, Object> entity);
	
	/**
	 * �޸�����
	 */
	@Update(value="UPDATE tb_item SET item_name=#{item_name}, item_sort=#{item_sort} WHERE item_id=#{item_id}")
	int update(Map<String, Object> entity);
	
	/**
	 * ɾ������
	 */
	@DeleteProvider(type=ItemProvider.class,method="deleteItemByIds")
	int delete(String itemIds);
}
