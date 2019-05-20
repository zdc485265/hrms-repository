package cn.gxkjdx.mapper.provider;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class JobProvider {
	
	private static final Logger logger =LogManager.getLogger(JobProvider.class);
	
	public String findByIds(String jobIds){
		return "SELECT * FROM tb_job WHERE job_id in("+jobIds+")";
	}
	
	public String findByConditionToPage(@Param("entity") Map<String, Object> entity ,@Param("start") Object start,@Param("size") Object size) {
		String sql="SELECT * FROM tb_job WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("job_id")!= null) {
				builder.append(" AND  job_id=#{entity.job_id}");
			}
			
			if(entity.get("job_code")!= null) {
				builder.append(" AND  job_code like CONCAT('%',#{entity.job_code},'%')");
			}
			
			if(entity.get("job_name")!= null) {
				builder.append(" AND  job_name like CONCAT('%',#{entity.job_name},'%')");
			}
			
			if(entity.get("classify_id")!= null) {
				builder.append(" AND  classify_id=#{entity.classify_id}");
			}
			
			if(entity.get("dept_id")!= null) {
				builder.append(" AND  dept_id=#{entity.dept_id}");
			}
			
			if(entity.get("job_description")!= null) {
				builder.append(" AND  job_description=#{entity.job_description}");
			}
			
			if(entity.get("job_remark")!= null) {
				builder.append(" AND  job_remark=#{entity.job_remark}");
			}
			
			if(entity.get("job_status")!= null) {
				builder.append(" AND  job_status=#{entity.job_status}");
			}
			
		}
		builder.append(" LIMIT #{start},#{size}");
		logger.debug("权限模糊查询："+builder.toString());
		return builder.toString();
	}
	
	public String countByCondition(Map<String, Object> entity) {
		String sql="SELECT count(*) FROM tb_job WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("job_id")!= null) {
				builder.append(" AND  job_id=#{job_id}");
			}
			
			if(entity.get("job_name")!= null) {
				builder.append(" AND  job_name like CONCAT('%',#{job_name},'%')");
			}
			
			if(entity.get("job_code")!= null) {
				builder.append(" AND  job_code like CONCAT('%',#{job_code},'%')");
			}
			
			if(entity.get("classify_id")!= null) {
				builder.append(" AND  classify_id=#{classify_id}");
			}
			
			if(entity.get("dept_id")!= null) {
				builder.append(" AND  dept_id=#{dept_id}");
			}
			
			if(entity.get("job_description")!= null) {
				builder.append(" AND  job_description=#{job_description}");
			}
			
			if(entity.get("job_remark")!= null) {
				builder.append(" AND  job_remark=#{job_remark}");
			}
			
			if(entity.get("job_status")!= null) {
				builder.append(" AND  job_status=#{job_status}");
			}
		}
		logger.debug("权限模糊查询："+builder.toString());
		
		return builder.toString();
	}
	
	public String deleteJobByIds(String jobIds){
		return "DELETE FROM tb_job WHERE job_id in ("+jobIds+")";
	}
}
