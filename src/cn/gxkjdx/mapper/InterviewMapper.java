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
	 * ��ѯ��������
	 * @return
	 */
	@Select(value="SELECT * FROM tb_interview")
	List<Map<String, Object>> findAll();
	
	/**
	 * ͨ��id��ѯ����
	 * @param interviewId
	 * @return
	 */
	@Select(value="SELECT * FROM tb_interview WHERE interview_id=#{interviewId}")
	Map<String, Object> findById(Object interviewId);
	
	/**
	 * ��ѯ����
	 * @param resumeId
	 * @return
	 */
	@Select(value="SELECT * FROM tb_interview WHERE resume_id=#{resumeId}")
	List<Map<String, Object>> findByResumeId(Object resumeId);
	
	/**
	 *  ��������
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_interview (interview_date, interview_info,interview_status, resume_id, admin_id) VALUES (#{interview_date},#{interview_info},#{interview_status},#{resume_id},#{admin_id})")
	int insert(Map<String, Object> entity);
	
	/**
	 * �޸�����
	 */
	@Update(value="UPDATE tb_interview SET interview_date=#{interview_date}, interview_info=#{interview_info},interview_status=#{interview_status}, resume_id=#{resume_id}, admin_id=#{admin_id} WHERE interview_id=#{interview_id}")
	int update(Map<String, Object> entity);
	
	/**
	 * ɾ������
	 */
	@Delete(value="DELETE FROM tb_interview WHERE resume_id=#{resumeId}")
	int delete(Object resumeId);
}
