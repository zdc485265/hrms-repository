package cn.gxkjdx.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Repository
public interface EmploymentMapper {
	/**
	 * 查询所有数据
	 * @return
	 */
	@Select(value="SELECT * FROM tb_employment")
	List<Map<String, Object>> findAll();
	
	/**
	 * 通过id查询数据
	 * @param employmentId
	 * @return
	 */
	@Select(value="SELECT * FROM tb_employment WHERE employment_id=#{employmentId}")
	Map<String, Object> findById(Object employmentId);
	
	/**
	 * 查询数据
	 * @param resumeId
	 * @return
	 */
	@Select(value="SELECT * FROM tb_employment WHERE resume_id=#{resumeId}")
	Map<String, Object> findByResumeId(Object resumeId);
	
	/**
	 *  增加数据
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_employment (employment_date, employment_info, resume_id, admin_id) VALUES (#{employment_date},#{employment_info},#{resume_id},#{admin_id})")
	int insert(Map<String, Object> entity);
	
	/**
	 * 修改数据
	 */
	@Update(value="UPDATE tb_employment SET employment_date=#{employment_date}, employment_info=#{employment_info}, resume_id=#{resume_id}, admin_id=#{admin_id} WHERE employment_id=#{employment_id}")
	int update(Map<String, Object> entity);
	
	/**
	 * 删除数据
	 */
	@Delete(value="DELETE FROM tb_employment WHERE resume_id =#{resumeId}")
	int delete(Object resumeId);
}
