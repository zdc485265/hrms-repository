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

import cn.gxkjdx.mapper.provider.RoleProvider;

@Repository
public interface RoleMapper {

	/**
	 * ͨ��id��ѯ����
	 * @param roleId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_role  WHERE role_id=#{roleId}")
	Map<String, Object> findById(Object roleId);
	
	/**
	 * ��������id����role��
	 * @param roleIds
	 * @return
	 */
	//@Select(value="SELECT * FROM tb_role WHERE role_id in(${roleIds})")
	@SelectProvider(type = RoleProvider.class, method="findByIds")
	List<Map<String, Object>> findByIds(String roleIds);
	
	/**
	 * ��㱲���start��ʼsize������
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	@SelectProvider(type = RoleProvider.class, method="findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") Object start,@Param("size") Object size);

	/**
	 * ��㱲������ݸ���
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = RoleProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);
	
	/**
	 * ��ѯ��������
	 * @return
	 */
	@Select(value="SELECT * FROM tb_role")
	List<Map<String, Object>> findAll();
	
	/**
	 * ͨ��name��ѯ����
	 * @param roleName
	 * @return
	 */
	@Select(value="SELECT * FROM tb_role WHERE role_name=#{roleName}")
	Map<String, Object> findByName(Object roleName);
	
	/**
	 *  ��������
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_role (role_name, role_powers) VALUES (#{role_name},#{role_powers})")
	int insert(Map<String, Object> entity);
	
	/**
	 * �޸�����
	 */
	@Update(value="UPDATE tb_role SET role_name=#{role_name}, role_powers=#{role_powers} WHERE role_id=#{role_id}")
	int update(Map<String, Object> entity);
	
	/**
	 * ɾ������
	 */
	@DeleteProvider(type=RoleProvider.class,method="deleteRoleByIds")
	int delete(String roleIds);
}
