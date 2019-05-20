package cn.gxkjdx.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptMapper {

	/**
	 * ��ѯ��������
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_dept")
	List<Map<String, Object>> findAll();
	
	/**
	 * ͨ��id��������
	 * @param deptId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_dept WHERE dept_id=#{deptId}")
	Map<String, Object> findById(Object deptId);
	
	/**
	 *  ��������
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_dept (dept_name,dept_code,dept_abbreviation,dept_parent,dept_address,dept_introduction,dept_remarks,dept_status) VALUES (#{dept_name},#{dept_code},#{dept_abbreviation},#{dept_parent},#{dept_address},#{dept_introduction},#{dept_remarks},#{dept_status})")
	int insert(Map<String, Object> entity);
	
	/**
	 * �޸�����
	 */
	@Update(value="UPDATE tb_dept SET dept_name=#{dept_name},dept_code=#{dept_code},dept_abbreviation=#{dept_abbreviation},dept_parent=#{dept_parent},dept_address=#{dept_address},dept_introduction=#{dept_introduction},dept_remarks=#{dept_remarks},dept_status=#{dept_status} WHERE dept_id=#{dept_id}")
	int update(Map<String, Object> entity);
	
	/**
	 * ɾ������
	 */
	@Delete(value="DELETE FROM tb_dept WHERE dept_id=#{deptId}")
	int delete(Object deptId);
	
	/**
	 * 
	 * @param deptId
	 * @return
	 */
	@Select(value="SELECT * FROM tb_dept WHERE dept_parent=#{deptId}")
	List<Map<String, Object>> findByParentId(Object deptId);	
}
