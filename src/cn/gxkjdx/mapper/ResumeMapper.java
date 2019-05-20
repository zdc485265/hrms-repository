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

import cn.gxkjdx.mapper.provider.ResumeProvider;

@Repository
public interface ResumeMapper {

	/**
	 * 查询所有数据
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_resume")
	List<Map<String, Object>> findAll();
	
	/**
	 * 通过id查找数据
	 * @param resumeId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_resume WHERE resume_id=#{resumeId}")
	Map<String, Object> findById(Object resumeId);
	
	/**
	 * 查找所有id集的resume集
	 * @param resumeIds
	 * @return
	 */
	//@Select(value="SELECT * FROM tb_resume WHERE resume_id in(${resumeIds})")
	@SelectProvider(type = ResumeProvider.class, method="findByIds")
	List<Map<String, Object>> findByIds(String resumeIds);
	
	/**
	 * 恍惚查找start开始size个数据
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	@SelectProvider(type = ResumeProvider.class, method="findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") Object start,@Param("size") Object size);

	/**
	 * 恍惚查找数据个数
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = ResumeProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);

	/**
	 *  增加数据
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_resume (resume_name, resume_sex, resume_email, resume_phone, resume_hokou_address, resume_address, resume_zzmm, resume_identity, resume_school, resume_xl, resume_experience, resume_sal_req, resume_major, resume_is_current, resume_is_inword, resume_login_date, resume_identity_info, resume_info, resume_recommend_date, resume_status, resume_suggest, job_id, admin_id) VALUES (#{resume_name},#{resume_sex},#{resume_email},#{resume_phone},#{resume_hokou_address},#{resume_address},#{resume_zzmm},#{resume_identity},#{resume_school},#{resume_xl},#{resume_experience},#{resume_sal_req},#{resume_major},#{resume_is_current},#{resume_is_inword},#{resume_login_date},#{resume_identity_info},#{resume_info},#{resume_recommend_date},#{resume_status},#{resume_suggest},#{job_id},#{admin_id})")
	int insert(Map<String, Object> entity);
	
	/**
	 * 修改数据
	 */
	@Update(value="UPDATE tb_resume SET resume_name=#{resume_name}, resume_sex=#{resume_sex}, resume_email=#{resume_email}, resume_phone=#{resume_phone}, resume_hokou_address=#{resume_hokou_address}, resume_address=#{resume_address}, resume_zzmm=#{resume_zzmm}, resume_identity=#{resume_identity}, resume_school=#{resume_school}, resume_xl=#{resume_xl}, resume_experience=#{resume_experience}, resume_sal_req=#{resume_sal_req}, resume_major=#{resume_major}, resume_is_current=#{resume_is_current}, resume_is_inword=#{resume_is_inword}, resume_login_date=#{resume_login_date}, resume_identity_info=#{resume_identity_info}, resume_info=#{resume_info}, resume_recommend_date=#{resume_recommend_date}, resume_status=#{resume_status}, resume_suggest=#{resume_suggest}, job_id=#{job_id}, admin_id=#{admin_id} WHERE resume_id=#{resume_id}")
	int update(Map<String, Object> entity);
	
	/**
	 * 删除数据
	 */
	@Delete(value="DELETE FROM tb_resume WHERE resume_id =#{resumeId}")
	int delete(Object resumeId);
}
