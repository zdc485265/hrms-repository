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

import cn.gxkjdx.mapper.provider.PowerProvider;

@Repository
public interface PowerMapper {

	/**
	 * 查找所有id集的power集
	 * @param powerIds
	 * @return
	 */
	//@Select(value="SELECT * FROM tb_power WHERE power_id in(${powerIds})")
	@SelectProvider(type = PowerProvider.class, method="findByIds")
	List<Map<String, Object>> findByIds(String powerIds);
	
	/**
	 * 恍惚查找start开始size个数据
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	@SelectProvider(type = PowerProvider.class, method="findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") Object start,@Param("size") Object size);

	/**
	 * 恍惚查找数据个数
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = PowerProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);
	
	/**
	 * 查询所有数据
	 * @return
	 */
	@Select(value="SELECT * FROM tb_power")
	List<Map<String, Object>> findAll();
	
	/**
	 * 通过id查询数据
	 * @param ppId
	 * @return
	 */
	@Select(value="SELECT * FROM tb_power WHERE power_id=#{ppId}")
	Map<String, Object> findById(Object ppId);
	
	/**
	 * 通过name查询数据
	 * @param powerName
	 * @return
	 */
	@Select(value="SELECT * FROM tb_power WHERE power_name=#{powerName}")
	Map<String, Object> findByName(Object powerName);
	
	/**
	 *  增加数据
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_power (power_name, power_action,power_url, power_parent, power_is_show, modular_id) VALUES (#{power_name},#{power_action},#{power_url},#{power_parent},#{power_is_show},#{modular_id})")
	int insert(Map<String, Object> entity);
	
	/**
	 * 修改数据
	 */
	@Update(value="UPDATE tb_power SET power_name=#{power_name}, power_action=#{power_action},power_url=#{power_url}, power_parent=#{power_parent}, power_is_show=#{power_is_show}, modular_id=#{modular_id} WHERE power_id=#{power_id}")
	int update(Map<String, Object> entity);
	
	/**
	 * 删除数据
	 */
	@DeleteProvider(type=PowerProvider.class,method="deletePowerByIds")
	int delete(String powerIds);
	
	/**
	 * 获取同一模块的数据
	 */
	@Select(value="SELECT * FROM tb_power WHERE modular_id=#{modularId}")
	List<Map<String, Object>> findByModularId(Object modularId);
}
