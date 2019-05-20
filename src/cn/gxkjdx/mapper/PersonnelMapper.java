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

import cn.gxkjdx.mapper.provider.PersonnelProvider;

@Repository
public interface PersonnelMapper {

	/**
	 * 查询所有数据
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_personnel")
	List<Map<String, Object>> findAll();
	
	/**
	 * 通过id查找数据
	 * @param personnelId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_personnel WHERE personnel_id=#{personnelId}")
	Map<String, Object> findById(Object personnelId);
	
	/**
	 * 恍惚查找start开始size个数据
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	@SelectProvider(type = PersonnelProvider.class, method="findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") Object start,@Param("size") Object size);

	/**
	 * 恍惚查找数据个数
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = PersonnelProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);

	/**
	 *  增加数据
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO `hrs`.`tb_personnel` (`personnel_code`,`personnel_name`,`personnel_email`,`personnel_sex`,`personnel_phone`,`personnel_address`,`personnel_identify`,`personnel_hokou_address`,`personnel_zzmm`,`personnel_school`,`personnel_major`,`personnel_xl`,`personnel_photo`,`personnel_bank`,`personnel_bank_card`,`personnel_social_security_card`,`personnel_register_date`,`personnel_identify_info`,`personnel_family_info`,`personnel_remarks`,`personnel_resume_info`,`personnel_examine_status`,`personnel_status`,`salary_id`,`admin_id`,`job_id`) VALUES(#{personnel_code},#{personnel_name},#{personnel_email},#{personnel_sex},#{personnel_phone},#{personnel_address},#{personnel_identify},#{personnel_hokou_address},#{personnel_zzmm},#{personnel_school},#{personnel_major},#{personnel_xl},#{personnel_photo},#{personnel_bank},#{personnel_bank_card},#{personnel_social_security_card},#{personnel_register_date},#{personnel_identify_info},#{personnel_family_info},#{personnel_remarks},#{personnel_resume_info},#{personnel_examine_status},#{personnel_status},#{salary_id},#{admin_id},#{job_id}) ")
	int insert(Map<String, Object> entity);
	
	/**
	 * 修改数据
	 */
	@Update(value="UPDATE `hrs`.`tb_personnel` SET `personnel_name` = #{personnel_name},`personnel_email` = #{personnel_email},`personnel_sex` = #{personnel_sex},`personnel_phone` = #{personnel_phone},`personnel_address` = #{personnel_address},`personnel_identify` = #{personnel_identify},`personnel_hokou_address` = #{personnel_hokou_address},`personnel_zzmm` = #{personnel_zzmm},`personnel_school` = #{personnel_school},`personnel_major` = #{personnel_major},`personnel_xl` = #{personnel_xl},`personnel_photo` = #{personnel_photo},`personnel_bank` = #{personnel_bank},`personnel_bank_card` = #{personnel_bank_card},`personnel_social_security_card` = #{personnel_social_security_card},`personnel_register_date` = #{personnel_register_date},`personnel_identify_info` = #{personnel_identify_info},`personnel_family_info` = #{personnel_family_info},`personnel_remarks` = #{personnel_remarks},`personnel_resume_info` = #{personnel_resume_info},`personnel_examine_status` = #{personnel_examine_status},`personnel_status` = #{personnel_status},`salary_id` = #{salary_id},`admin_id` = #{admin_id},`job_id` = #{job_id} where `personnel_id` = #{personnel_id} ")
	int update(Map<String, Object> entity);
	
	/**
	 * 修改状态数据
	 */
	@Update(value="UPDATE `hrs`.`tb_personnel` SET `personnel_status` = #{personnel_status} where `personnel_id` = #{personnel_id} ")
	int updateStatus(Map<String, Object> entity);
	
	
	/**
	 * 删除数据
	 */
	@Delete(value="DELETE FROM tb_personnel WHERE personnel_id=#{personnelId}")
	int delete(Object personnelId);
}
