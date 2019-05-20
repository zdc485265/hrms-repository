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

import cn.gxkjdx.mapper.provider.AdminProvider;

@Repository
public interface AdminMapper {
	
	/**
	 * 通过账号名查询记录
	 * @param accountName
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_admin  WHERE admin_account = #{accountAccount}")
	Map<String, Object> findByAccount(Object accountAccount);
	
	/**
	 * 查找所有id集的admin集
	 * @param adminIds
	 * @return
	 */
	//@Select(value="SELECT * FROM tb_admin WHERE admin_id in(${adminIds})")
	@SelectProvider(type = AdminProvider.class, method="findByIds")
	List<Map<String, Object>> findByIds(String adminIds);
	
	/**
	 * 恍惚查找start开始size个数据
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	@SelectProvider(type = AdminProvider.class, method="findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") Object start,@Param("size") Object size);

	/**
	 * 恍惚查找数据个数
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = AdminProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);
	
	/**
	 * 查询所有数据
	 * @return
	 */
	@Select(value="SELECT * FROM tb_admin")
	List<Map<String, Object>> findAll();
	
	/**
	 * 通过id查询数据
	 * @param ppId
	 * @return
	 */
	@Select(value="SELECT * FROM tb_admin WHERE admin_id=#{adminId}")
	Map<String, Object> findById(Object adminId);
	
	/**
	 * 通过name查询数据
	 * @param adminName
	 * @return
	 */
	@Select(value="SELECT * FROM tb_admin WHERE admin_name=#{adminName}")
	Map<String, Object> findByName(Object adminName);
	
	/**
	 *  增加数据
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_admin (admin_name, admin_account,admin_pwd, admin_status, role_id, dept_id) VALUES (#{admin_name},#{admin_account},#{admin_pwd},#{admin_status},#{role_id},#{dept_id})")
	int insert(Map<String, Object> entity);
	
	/**
	 * 修改数据
	 */
	@Update(value="UPDATE tb_admin SET admin_name=#{admin_name}, admin_account=#{admin_account},admin_pwd=#{admin_pwd}, admin_status=#{admin_status}, role_id=#{role_id}, dept_id=#{dept_id} WHERE admin_id=#{admin_id}")
	int update(Map<String, Object> entity);
	
	/**
	 * 删除数据
	 */
	@DeleteProvider(type=AdminProvider.class,method="deleteAdminByIds")
	int delete(String adminIds);
}
