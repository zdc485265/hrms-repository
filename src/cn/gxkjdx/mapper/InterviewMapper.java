package cn.gxkjdx.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewMapper {
	/**
	 * 查询所有数据
	 * @return
	 */
	@Select(value="SELECT * FROM tb_interview")
	List<Map<String, Object>> findAll();
	
	/**
	 * 通过id查询数据
	 * @param interviewId
	 * @return
	 */
	@Select(value="SELECT * FROM tb_interview WHERE interview_id=#{interviewId}")
	Map<String, Object> findById(Object interviewId);
	
	/**
	 * 查询数据
	 * @param resumeId
	 * @return
	 */
	@Select(value="SELECT * FROM tb_interview WHERE resume_id=#{resumeId}")
	List<Map<String, Object>> findByResumeId(Object resumeId);
	
	/**
	 *  增加数据
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_interview (interview_date, interview_info,interview_status, resume_id, admin_id) VALUES (#{interview_date},#{interview_info},#{interview_status},#{resume_id},#{admin_id})")
	int insert(Map<String, Object> entity);
	
	/**
	 * 修改数据
	 */
	@Update(value="UPDATE tb_interview SET interview_date=#{interview_date}, interview_info=#{interview_info},interview_status=#{interview_status}, resume_id=#{resume_id}, admin_id=#{admin_id} WHERE interview_id=#{interview_id}")
	int update(Map<String, Object> entity);
	
	/**
	 * 删除数据
	 */
	@Delete(value="DELETE FROM tb_interview WHERE resume_id=#{resumeId}")
	int delete(Object resumeId);
}
