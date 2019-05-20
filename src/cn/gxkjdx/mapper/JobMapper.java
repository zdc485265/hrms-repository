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
	 * 查询所有数据
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_job")
	List<Map<String, Object>> findAll();
	
	/**
	 * 通过id查找数据
	 * @param jobId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_job WHERE job_id=#{jobId}")
	Map<String, Object> findById(Object jobId);
	
	/**
	 * 查找所有id集的job集
	 * @param jobIds
	 * @return
	 */
	//@Select(value="SELECT * FROM tb_job WHERE job_id in(${jobIds})")
	@SelectProvider(type = JobProvider.class, method="findByIds")
	List<Map<String, Object>> findByIds(String jobIds);
	
	/**
	 * 恍惚查找start开始size个数据
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	@SelectProvider(type = JobProvider.class, method="findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") Object start,@Param("size") Object size);

	/**
	 * 恍惚查找数据个数
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = JobProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);
	
	/**
	 * 通过code查询数据
	 * @param jobCode
	 * @return
	 */
	@Select(value="SELECT * FROM tb_job WHERE job_code=#{jobCode}")
	Map<String, Object> findByCode(Object jobCode);
	
	/**
	 * 通过name查询数据
	 * @param jobName
	 * @return
	 */
	@Select(value="SELECT * FROM tb_job WHERE job_name=#{jobName}")
	Map<String, Object> findByName(Object jobName);
	
	/**
	 *  增加数据
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_job (job_code, job_name, classify_id, dept_id, job_description, job_remark, job_status) VALUES (#{job_code},#{job_name},#{classify_id},#{dept_id},#{job_description},#{job_remark},#{job_status})")
	int insert(Map<String, Object> entity);
	
	/**
	 * 修改数据
	 */
	@Update(value="UPDATE tb_job SET job_code=#{job_code}, job_name=#{job_name}, classify_id=#{classify_id}, dept_id=#{dept_id}, job_description=#{job_description}, job_remark=#{job_remark}, job_status=#{job_status} WHERE job_id=#{job_id}")
	int update(Map<String, Object> entity);
	
	/**
	 * 删除数据
	 */
	@DeleteProvider(type=JobProvider.class,method="deleteJobByIds")
	int delete(String jobIds);
	
	/**
	 * 通过职位分类Id查询职位分类信息
	 */
	@Select(value="SELECT * FROM tb_classify WHERE classify_id=#{classifyId}")
	Map<String, Object> findClassifyByClassifyId(Object classifyId);
	
	/**
	 * 查询所有职位分类
	 */
	@Select(value="SELECT * FROM tb_classify")
	List<Map<String, Object>> findClassifyAll();
	
	/**
	 * 查询同一部门的所有职位
	 */
	@Select(value="SELECT * FROM tb_job WHERE dept_id=#{deptId}")
	List<Map<String, Object>> findByDeptId(Object deptId);
}
