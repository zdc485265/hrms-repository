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

import cn.gxkjdx.mapper.provider.RecruitProvider;

@Repository
public interface RecruitMapper {

	/**
	 * 查询所有数据
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_recruit")
	List<Map<String, Object>> findAll();
	
	/**
	 * 通过id查找数据
	 * @param recruitId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_recruit WHERE recruit_id=#{recruitId}")
	Map<String, Object> findById(Object recruitId);
	
	/**
	 * 查找所有id集的recruit集
	 * @param recruitIds
	 * @return
	 */
	//@Select(value="SELECT * FROM tb_recruit WHERE recruit_id in(${recruitIds})")
	@SelectProvider(type = RecruitProvider.class, method="findByIds")
	List<Map<String, Object>> findByIds(String recruitIds);
	
	/**
	 * 恍惚查找start开始size个数据
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	@SelectProvider(type = RecruitProvider.class, method="findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") Object start,@Param("size") Object size);

	/**
	 * 恍惚查找数据个数
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = RecruitProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);

	/**
	 *  增加数据
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_recruit (recruit_num, recruit_start, recruit_end, recruit_req, recruit_sort, job_id, admin_id) VALUES (#{recruit_num},#{recruit_start},#{recruit_end},#{recruit_req},#{recruit_sort},#{job_id},#{admin_id})")
	int insert(Map<String, Object> entity);
	
	/**
	 * 修改数据
	 */
	@Update(value="UPDATE tb_recruit SET recruit_num=#{recruit_num}, recruit_start=#{recruit_start}, recruit_end=#{recruit_end}, recruit_req=#{recruit_req}, recruit_sort=#{recruit_sort}, job_id=#{job_id}, admin_id=#{admin_id} WHERE recruit_id=#{recruit_id}")
	int update(Map<String, Object> entity);
	
	/**
	 * 删除数据
	 */
	@Delete(value="DELETE FROM tb_recruit WHERE recruit_id =#{recruitId}")
	int delete(Object recruitId);
}
