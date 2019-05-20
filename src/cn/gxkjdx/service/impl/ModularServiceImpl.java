package cn.gxkjdx.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gxkjdx.mapper.ModularMapper;
import cn.gxkjdx.service.ModularService;
import cn.gxkjdx.util.Page;

@Service
public class ModularServiceImpl implements ModularService{
	
	@Autowired
	ModularMapper modularMapper;

	/**
	 * 获取page
	 */
	@Override
	public Page findModularByPage(Map<String, Object> entity,String indexo, String sizeo) {
		int index,size;
		
		//判空
		if(null==indexo || "".equals(indexo)){
			index=0;
		}else {
			index=new Integer(indexo);
		}
		if(null==sizeo || "".equals(indexo)){
			size=5;
		}else {
			size=new Integer(sizeo);
		}
		//获取数据
		List<Map<String, Object>> data = modularMapper.findByConditionToPage(entity,index*size,size);
		
		//获取数据个数
		int count = modularMapper.countByCondition(entity);
		//构建page
		Page page=new Page(index, size, count, data);
		
		return page;
	}
	/**
	 * 添加数据
	 */
	@Override
	public Map<String, Object> addModular(Map<String, Object> entity) {
		Map<String, Object> info=new HashMap<>();
		StringBuffer sb=new StringBuffer("新增失败,");
		Object modularName = entity.get("modular_name");
		Object modularSort = entity.get("modular_sort");
		int num=0;
		if(null==modularName||"".equals(modularName)){
			sb.append("模块名不能为空,");
			num++;
		}else{
			Map<String, Object> modular = modularMapper.findByName(modularName);
			if(null!=modular){
				sb.append("模块名已重复,");
				info.put("modular_add_msg", sb.toString());
				return info;
			}
		}
		if(null==modularSort||"".equals(modularSort)){
			sb.append("排序序号不能为空,");
			num++;
		}
		sb.setLength(sb.length()-1);
		if(num==0){
			try {
				entity.put("modular_sort", Long.valueOf((String)modularSort));
				int insert = modularMapper.insert(entity);
				if(insert>0){
					sb=new StringBuffer("新增成功！");
				}
			} catch (NumberFormatException e) {
				sb.append(",排序序号必须为数值");
			}catch (Exception e) {
				sb.append(",未知错误");
			}
		}
		info.put("modular_add_msg", sb.toString());
		return info;
	}

	/**
	 * 获取页面显示数据
	 */
	@Override
	public Map<String, Object> toEditModular(Object modularId) {
		Map<String, Object> info =new HashMap<>();
		if(null!=modularId&&!"".equals(modularId)){
			Map<String, Object> modular = modularMapper.findById(modularId);
			info.put("modular", modular);
		}else {
			info.put("modular_edit_msg", "出错了,未找到此模块");
		}
		return info;
	}

	/**
	 * 修改数据
	 */
	@Override
	public Map<String, Object> editModular(Map<String, Object> entity) {
		Map<String, Object> info = new HashMap<>();
		StringBuffer sb =new StringBuffer("编辑失败,");
		Object modularName = entity.get("modular_name");
		Object modularSort = entity.get("modular_sort");
		int num=0;
		if(null==modularName||"".equals(modularName)){
			sb.append("模块名不能为空,");
			num++;
		}else{
			Map<String, Object> modular = modularMapper.findByName(modularName);
			if(null!=modular && !(modular.get("modular_id").toString()).equals(entity.get("modular_id"))){
				sb.append("模块名已重复,");
				info.put("modular_edit_msg", sb.toString());
				return info;
			}
		}
		if(null==modularSort||"".equals(modularSort)){
			sb.append("模块排序不能为空,");
			num++;
		}
		sb.setLength(sb.length()-1);
		if(0==num){
			int update = modularMapper.update(entity);
			if(update>0){
				sb=new StringBuffer("编辑成功！");
			}
		}
		info.put("modular_edit_msg", sb.toString());
		return info;
	}

	/**
	 * 删除数据
	 */
	@Override
	public Map<String, Object> deleteModular(Long[] modular_id) {
		Map<String, Object> info=new HashMap<>();
		String msg="删除失败!";
		try {
			if(modular_id.length>0){
				String modularIdStr = Arrays.toString(modular_id);
				String modularIds = modularIdStr.substring(1, modularIdStr.length()-1);
				int delete = modularMapper.delete(modularIds);
				if(delete>0){
					msg="删除成功！";
				}
			}
		} catch (Exception e) {
			msg="未知异常";
		}
		info.put("modular_list_msg", msg);
		return info;
	}

}
