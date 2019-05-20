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
	 * 通过id查询数据
	 * @param roleId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_role  WHERE role_id=#{roleId}")
	Map<String, Object> findById(Object roleId);
	
	/**
	 * 查找所有id集的role集
	 * @param roleIds
	 * @return
	 */
	//@Select(value="SELECT * FROM tb_role WHERE role_id in(${roleIds})")
	@SelectProvider(type = RoleProvider.class, method="findByIds")
	List<Map<String, Object>> findByIds(String roleIds);
	
	/**
	 * 恍惚查找start开始size个数据
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	@SelectProvider(type = RoleProvider.class, method="findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") Object start,@Param("size") Object size);

	/**
	 * 恍惚查找数据个数
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = RoleProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);
	
	/**
	 * 查询所有数据
	 * @return
	 */
	@Select(value="SELECT * FROM tb_role")
	List<Map<String, Object>> findAll();
	
	/**
	 * 通过name查询数据
	 * @param roleName
	 * @return
	 */
	@Select(value="SELECT * FROM tb_role WHERE role_name=#{roleName}")
	Map<String, Object> findByName(Object roleName);
	
	/**
	 *  增加数据
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_role (role_name, role_powers) VALUES (#{role_name},#{role_powers})")
	int insert(Map<String, Object> entity);
	
	/**
	 * 修改数据
	 */
	@Update(value="UPDATE tb_role SET role_name=#{role_name}, role_powers=#{role_powers} WHERE role_id=#{role_id}")
	int update(Map<String, Object> entity);
	
	/**
	 * 删除数据
	 */
	@DeleteProvider(type=RoleProvider.class,method="deleteRoleByIds")
	int delete(String roleIds);
}
