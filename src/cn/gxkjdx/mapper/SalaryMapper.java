package cn.gxkjdx.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import cn.gxkjdx.mapper.provider.SalaryProvider;

@Repository
public interface SalaryMapper {

	/**
	 * 查询所有数据
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_salary")
	List<Map<String, Object>> findAll();
	
	/**
	 * 通过id查找数据
	 * @param salaryId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_salary WHERE salary_id=#{salaryId}")
	Map<String, Object> findById(Object salaryId);
	
	
	/**
	 * 恍惚查找start开始size个数据
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	@SelectProvider(type = SalaryProvider.class, method="findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") Object start,@Param("size") Object size);

	/**
	 * 恍惚查找数据个数
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = SalaryProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);
	
	
	/**
	 * 通过name查询数据
	 * @param salaryName
	 * @return
	 */
	@Select(value="SELECT * FROM tb_salary WHERE salary_name=#{salaryName}")
	Map<String, Object> findByName(Object salaryName);
	
	/**
	 * 通过code查询数据
	 * @param salaryCode
	 * @return
	 */
	@Select(value="SELECT * FROM tb_salary WHERE salary_code=#{salaryCode}")
	Map<String, Object> findByCode(Object salaryCode);
	
	/**
	 * 查询salarItem
	 * @param salaryId
	 * @return
	 */
	@Select(value="SELECT * FROM tb_salary_item WHERE salary_id=#{salaryId}")
	List<Map<String, Object>> findSalaryItemBySalaryId(Object salaryId);
	
	/**
	 * 查询salarItem
	 * @param salaryId
	 * @return
	 */
	@Select(value="SELECT * FROM tb_salary_item WHERE salary_id=#{salary_id} AND item_id=#{item_id}")
	Map<String, Object> findSalaryItemBySalaryIdAndItemId(Map<String, Object> map);
	
	
	/**
	 * 添加salaryItem
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_salary_item (salary_item_value, salary_id, item_id) VALUES (#{salary_item_value},#{salary_id},#{item_id})")
	int insertSalaryItem(Map<String, Object> entity);
	
	/**
	 * 修改salaryItem
	 */
	@Update(value="UPDATE tb_salary_item SET salary_item_value=#{salary_item_value} WHERE salary_id=#{salary_id} AND item_id=#{item_id}")
	int updateSalaryItem(Map<String, Object> entity);
	
	/**
	 * deletesalaryItem
	 */
	@Delete(value="DELETE FROM tb_salary_item WHERE salary_id=#{salaryId}")
	int deleteSalaryItemBySalaryId(Object salaryId);
	
	/**
	 *  增加数据
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_salary (salary_name, salary_code, salary_status, salary_date, salary_marker, admin_id) VALUES (#{salary_name},#{salary_code},#{salary_status},#{salary_date},#{salary_marker},#{admin_id})")
	int insert(Map<String, Object> entity);
	
	/**
	 * 修改数据
	 */
	@Update(value="UPDATE tb_salary SET salary_name=#{salary_name}, salary_code=#{salary_code}, salary_status=#{salary_status}, salary_date=#{salary_date}, salary_marker=#{salary_marker}, admin_id=#{admin_id} WHERE salary_id=#{salary_id}")
	int update(Map<String, Object> entity);
	
	/**
	 * 删除数据
	 */
	@Delete(value="DELETE FROM tb_salary WHERE salary_id=#{salaryId}")
	int delete(Object salaryId);
}
