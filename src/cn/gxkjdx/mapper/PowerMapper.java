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
	 * ��������id����power��
	 * @param powerIds
	 * @return
	 */
	//@Select(value="SELECT * FROM tb_power WHERE power_id in(${powerIds})")
	@SelectProvider(type = PowerProvider.class, method="findByIds")
	List<Map<String, Object>> findByIds(String powerIds);
	
	/**
	 * ��㱲���start��ʼsize������
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	@SelectProvider(type = PowerProvider.class, method="findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") Object start,@Param("size") Object size);

	/**
	 * ��㱲������ݸ���
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = PowerProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);
	
	/**
	 * ��ѯ��������
	 * @return
	 */
	@Select(value="SELECT * FROM tb_power")
	List<Map<String, Object>> findAll();
	
	/**
	 * ͨ��id��ѯ����
	 * @param ppId
	 * @return
	 */
	@Select(value="SELECT * FROM tb_power WHERE power_id=#{ppId}")
	Map<String, Object> findById(Object ppId);
	
	/**
	 * ͨ��name��ѯ����
	 * @param powerName
	 * @return
	 */
	@Select(value="SELECT * FROM tb_power WHERE power_name=#{powerName}")
	Map<String, Object> findByName(Object powerName);
	
	/**
	 *  ��������
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_power (power_name, power_action,power_url, power_parent, power_is_show, modular_id) VALUES (#{power_name},#{power_action},#{power_url},#{power_parent},#{power_is_show},#{modular_id})")
	int insert(Map<String, Object> entity);
	
	/**
	 * �޸�����
	 */
	@Update(value="UPDATE tb_power SET power_name=#{power_name}, power_action=#{power_action},power_url=#{power_url}, power_parent=#{power_parent}, power_is_show=#{power_is_show}, modular_id=#{modular_id} WHERE power_id=#{power_id}")
	int update(Map<String, Object> entity);
	
	/**
	 * ɾ������
	 */
	@DeleteProvider(type=PowerProvider.class,method="deletePowerByIds")
	int delete(String powerIds);
	
	/**
	 * ��ȡͬһģ�������
	 */
	@Select(value="SELECT * FROM tb_power WHERE modular_id=#{modularId}")
	List<Map<String, Object>> findByModularId(Object modularId);
}
