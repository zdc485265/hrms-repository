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
	 * ��ѯ��������
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_salary")
	List<Map<String, Object>> findAll();
	
	/**
	 * ͨ��id��������
	 * @param salaryId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_salary WHERE salary_id=#{salaryId}")
	Map<String, Object> findById(Object salaryId);
	
	
	/**
	 * ��㱲���start��ʼsize������
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	@SelectProvider(type = SalaryProvider.class, method="findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") Object start,@Param("size") Object size);

	/**
	 * ��㱲������ݸ���
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = SalaryProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);
	
	
	/**
	 * ͨ��name��ѯ����
	 * @param salaryName
	 * @return
	 */
	@Select(value="SELECT * FROM tb_salary WHERE salary_name=#{salaryName}")
	Map<String, Object> findByName(Object salaryName);
	
	/**
	 * ͨ��code��ѯ����
	 * @param salaryCode
	 * @return
	 */
	@Select(value="SELECT * FROM tb_salary WHERE salary_code=#{salaryCode}")
	Map<String, Object> findByCode(Object salaryCode);
	
	/**
	 * ��ѯsalarItem
	 * @param salaryId
	 * @return
	 */
	@Select(value="SELECT * FROM tb_salary_item WHERE salary_id=#{salaryId}")
	List<Map<String, Object>> findSalaryItemBySalaryId(Object salaryId);
	
	/**
	 * ��ѯsalarItem
	 * @param salaryId
	 * @return
	 */
	@Select(value="SELECT * FROM tb_salary_item WHERE salary_id=#{salary_id} AND item_id=#{item_id}")
	Map<String, Object> findSalaryItemBySalaryIdAndItemId(Map<String, Object> map);
	
	
	/**
	 * ���salaryItem
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_salary_item (salary_item_value, salary_id, item_id) VALUES (#{salary_item_value},#{salary_id},#{item_id})")
	int insertSalaryItem(Map<String, Object> entity);
	
	/**
	 * �޸�salaryItem
	 */
	@Update(value="UPDATE tb_salary_item SET salary_item_value=#{salary_item_value} WHERE salary_id=#{salary_id} AND item_id=#{item_id}")
	int updateSalaryItem(Map<String, Object> entity);
	
	/**
	 * deletesalaryItem
	 */
	@Delete(value="DELETE FROM tb_salary_item WHERE salary_id=#{salaryId}")
	int deleteSalaryItemBySalaryId(Object salaryId);
	
	/**
	 *  ��������
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_salary (salary_name, salary_code, salary_status, salary_date, salary_marker, admin_id) VALUES (#{salary_name},#{salary_code},#{salary_status},#{salary_date},#{salary_marker},#{admin_id})")
	int insert(Map<String, Object> entity);
	
	/**
	 * �޸�����
	 */
	@Update(value="UPDATE tb_salary SET salary_name=#{salary_name}, salary_code=#{salary_code}, salary_status=#{salary_status}, salary_date=#{salary_date}, salary_marker=#{salary_marker}, admin_id=#{admin_id} WHERE salary_id=#{salary_id}")
	int update(Map<String, Object> entity);
	
	/**
	 * ɾ������
	 */
	@Delete(value="DELETE FROM tb_salary WHERE salary_id=#{salaryId}")
	int delete(Object salaryId);
}
