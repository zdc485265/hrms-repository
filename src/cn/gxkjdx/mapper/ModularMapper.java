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
	 * ��ѯ��������
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_modular ORDER BY modular_sort")
	List<Map<String, Object>> findAll();
	
	/**
	 * ͨ��id��������
	 * @param modularId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_modular WHERE modular_id=#{modularId}")
	Map<String, Object> findById(Object modularId);
	
	/**
	 * ��������id����modular��
	 * @param modularIds
	 * @return
	 */
	//@Select(value="SELECT * FROM tb_modular WHERE modular_id in(${modularIds})")
	@SelectProvider(type = ModularProvider.class, method="findByIds")
	List<Map<String, Object>> findByIds(String modularIds);
	
	/**
	 * ��㱲���start��ʼsize������
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	@SelectProvider(type = ModularProvider.class, method="findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") Object start,@Param("size") Object size);

	/**
	 * ��㱲������ݸ���
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = ModularProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);
	
	
	/**
	 * ͨ��name��ѯ����
	 * @param modularName
	 * @return
	 */
	@Select(value="SELECT * FROM tb_modular WHERE modular_name=#{modularName}")
	Map<String, Object> findByName(Object modularName);
	
	/**
	 *  ��������
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_modular (modular_name, modular_sort) VALUES (#{modular_name},#{modular_sort})")
	int insert(Map<String, Object> entity);
	
	/**
	 * �޸�����
	 */
	@Update(value="UPDATE tb_modular SET modular_name=#{modular_name}, modular_sort=#{modular_sort} WHERE modular_id=#{modular_id}")
	int update(Map<String, Object> entity);
	
	/**
	 * ɾ������
	 */
	@DeleteProvider(type=ModularProvider.class,method="deleteModularByIds")
	int delete(String modularIds);
}
