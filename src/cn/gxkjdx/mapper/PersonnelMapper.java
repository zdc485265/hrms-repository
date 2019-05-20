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
	 * ��ѯ��������
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_personnel")
	List<Map<String, Object>> findAll();
	
	/**
	 * ͨ��id��������
	 * @param personnelId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_personnel WHERE personnel_id=#{personnelId}")
	Map<String, Object> findById(Object personnelId);
	
	/**
	 * ��㱲���start��ʼsize������
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	@SelectProvider(type = PersonnelProvider.class, method="findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") Object start,@Param("size") Object size);

	/**
	 * ��㱲������ݸ���
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = PersonnelProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);

	/**
	 *  ��������
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO `hrs`.`tb_personnel` (`personnel_code`,`personnel_name`,`personnel_email`,`personnel_sex`,`personnel_phone`,`personnel_address`,`personnel_identify`,`personnel_hokou_address`,`personnel_zzmm`,`personnel_school`,`personnel_major`,`personnel_xl`,`personnel_photo`,`personnel_bank`,`personnel_bank_card`,`personnel_social_security_card`,`personnel_register_date`,`personnel_identify_info`,`personnel_family_info`,`personnel_remarks`,`personnel_resume_info`,`personnel_examine_status`,`personnel_status`,`salary_id`,`admin_id`,`job_id`) VALUES(#{personnel_code},#{personnel_name},#{personnel_email},#{personnel_sex},#{personnel_phone},#{personnel_address},#{personnel_identify},#{personnel_hokou_address},#{personnel_zzmm},#{personnel_school},#{personnel_major},#{personnel_xl},#{personnel_photo},#{personnel_bank},#{personnel_bank_card},#{personnel_social_security_card},#{personnel_register_date},#{personnel_identify_info},#{personnel_family_info},#{personnel_remarks},#{personnel_resume_info},#{personnel_examine_status},#{personnel_status},#{salary_id},#{admin_id},#{job_id}) ")
	int insert(Map<String, Object> entity);
	
	/**
	 * �޸�����
	 */
	@Update(value="UPDATE `hrs`.`tb_personnel` SET `personnel_name` = #{personnel_name},`personnel_email` = #{personnel_email},`personnel_sex` = #{personnel_sex},`personnel_phone` = #{personnel_phone},`personnel_address` = #{personnel_address},`personnel_identify` = #{personnel_identify},`personnel_hokou_address` = #{personnel_hokou_address},`personnel_zzmm` = #{personnel_zzmm},`personnel_school` = #{personnel_school},`personnel_major` = #{personnel_major},`personnel_xl` = #{personnel_xl},`personnel_photo` = #{personnel_photo},`personnel_bank` = #{personnel_bank},`personnel_bank_card` = #{personnel_bank_card},`personnel_social_security_card` = #{personnel_social_security_card},`personnel_register_date` = #{personnel_register_date},`personnel_identify_info` = #{personnel_identify_info},`personnel_family_info` = #{personnel_family_info},`personnel_remarks` = #{personnel_remarks},`personnel_resume_info` = #{personnel_resume_info},`personnel_examine_status` = #{personnel_examine_status},`personnel_status` = #{personnel_status},`salary_id` = #{salary_id},`admin_id` = #{admin_id},`job_id` = #{job_id} where `personnel_id` = #{personnel_id} ")
	int update(Map<String, Object> entity);
	
	/**
	 * �޸�״̬����
	 */
	@Update(value="UPDATE `hrs`.`tb_personnel` SET `personnel_status` = #{personnel_status} where `personnel_id` = #{personnel_id} ")
	int updateStatus(Map<String, Object> entity);
	
	
	/**
	 * ɾ������
	 */
	@Delete(value="DELETE FROM tb_personnel WHERE personnel_id=#{personnelId}")
	int delete(Object personnelId);
}
