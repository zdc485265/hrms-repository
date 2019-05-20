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

import cn.gxkjdx.mapper.provider.JobProvider;

@Repository
public interface JobMapper {

	/**
	 * ��ѯ��������
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_job")
	List<Map<String, Object>> findAll();
	
	/**
	 * ͨ��id��������
	 * @param jobId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_job WHERE job_id=#{jobId}")
	Map<String, Object> findById(Object jobId);
	
	/**
	 * ��������id����job��
	 * @param jobIds
	 * @return
	 */
	//@Select(value="SELECT * FROM tb_job WHERE job_id in(${jobIds})")
	@SelectProvider(type = JobProvider.class, method="findByIds")
	List<Map<String, Object>> findByIds(String jobIds);
	
	/**
	 * ��㱲���start��ʼsize������
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	@SelectProvider(type = JobProvider.class, method="findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") Object start,@Param("size") Object size);

	/**
	 * ��㱲������ݸ���
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = JobProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);
	
	/**
	 * ͨ��code��ѯ����
	 * @param jobCode
	 * @return
	 */
	@Select(value="SELECT * FROM tb_job WHERE job_code=#{jobCode}")
	Map<String, Object> findByCode(Object jobCode);
	
	/**
	 * ͨ��name��ѯ����
	 * @param jobName
	 * @return
	 */
	@Select(value="SELECT * FROM tb_job WHERE job_name=#{jobName}")
	Map<String, Object> findByName(Object jobName);
	
	/**
	 *  ��������
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_job (job_code, job_name, classify_id, dept_id, job_description, job_remark, job_status) VALUES (#{job_code},#{job_name},#{classify_id},#{dept_id},#{job_description},#{job_remark},#{job_status})")
	int insert(Map<String, Object> entity);
	
	/**
	 * �޸�����
	 */
	@Update(value="UPDATE tb_job SET job_code=#{job_code}, job_name=#{job_name}, classify_id=#{classify_id}, dept_id=#{dept_id}, job_description=#{job_description}, job_remark=#{job_remark}, job_status=#{job_status} WHERE job_id=#{job_id}")
	int update(Map<String, Object> entity);
	
	/**
	 * ɾ������
	 */
	@DeleteProvider(type=JobProvider.class,method="deleteJobByIds")
	int delete(String jobIds);
	
	/**
	 * ͨ��ְλ����Id��ѯְλ������Ϣ
	 */
	@Select(value="SELECT * FROM tb_classify WHERE classify_id=#{classifyId}")
	Map<String, Object> findClassifyByClassifyId(Object classifyId);
	
	/**
	 * ��ѯ����ְλ����
	 */
	@Select(value="SELECT * FROM tb_classify")
	List<Map<String, Object>> findClassifyAll();
	
	/**
	 * ��ѯͬһ���ŵ�����ְλ
	 */
	@Select(value="SELECT * FROM tb_job WHERE dept_id=#{deptId}")
	List<Map<String, Object>> findByDeptId(Object deptId);
}
