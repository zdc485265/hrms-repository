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

import cn.gxkjdx.mapper.provider.ModularProvider;

@Repository
public interface ModularMapper {

	/**
	 * 查询所有数据
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_modular ORDER BY modular_sort")
	List<Map<String, Object>> findAll();
	
	/**
	 * 通过id查找数据
	 * @param modularId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_modular WHERE modular_id=#{modularId}")
	Map<String, Object> findById(Object modularId);
	
	/**
	 * 查找所有id集的modular集
	 * @param modularIds
	 * @return
	 */
	//@Select(value="SELECT * FROM tb_modular WHERE modular_id in(${modularIds})")
	@SelectProvider(type = ModularProvider.class, method="findByIds")
	List<Map<String, Object>> findByIds(String modularIds);
	
	/**
	 * 恍惚查找start开始size个数据
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	@SelectProvider(type = ModularProvider.class, method="findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") Object start,@Param("size") Object size);

	/**
	 * 恍惚查找数据个数
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = ModularProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);
	
	
	/**
	 * 通过name查询数据
	 * @param modularName
	 * @return
	 */
	@Select(value="SELECT * FROM tb_modular WHERE modular_name=#{modularName}")
	Map<String, Object> findByName(Object modularName);
	
	/**
	 *  增加数据
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_modular (modular_name, modular_sort) VALUES (#{modular_name},#{modular_sort})")
	int insert(Map<String, Object> entity);
	
	/**
	 * 修改数据
	 */
	@Update(value="UPDATE tb_modular SET modular_name=#{modular_name}, modular_sort=#{modular_sort} WHERE modular_id=#{modular_id}")
	int update(Map<String, Object> entity);
	
	/**
	 * 删除数据
	 */
	@DeleteProvider(type=ModularProvider.class,method="deleteModularByIds")
	int delete(String modularIds);
}
