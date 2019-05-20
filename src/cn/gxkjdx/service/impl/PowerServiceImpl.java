package cn.gxkjdx.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gxkjdx.mapper.ModularMapper;
import cn.gxkjdx.mapper.PowerMapper;
import cn.gxkjdx.service.PowerService;
import cn.gxkjdx.util.Page;

@Service
public class PowerServiceImpl implements PowerService{
	
	@Autowired
	PowerMapper powerMapper;
	
	@Autowired
	ModularMapper modularMapper;

	/**
	 * 获取page
	 */
	@Override
	public Page findPowerByPage(Map<String, Object> entity,String indexo, String sizeo) {
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
		List<Map<String, Object>> data = powerMapper.findByConditionToPage(entity,index*size,size);
		//遍历data
		for (Map<String, Object> power : data) {
			//获取当前power的modualr信息
			Long modularId = (Long)power.get("modular_id");
			if(modularId!=null){
				Map<String, Object> modular = modularMapper.findById(modularId);
				power.put("modular", modular);
			}
			//获取当前power的parent_power信息
			Long ppId = (Long)power.get("power_parent");
			if(ppId!=null){
				Map<String, Object> parent = powerMapper.findById(ppId);
				power.put("parent",parent);
			}
			
		}
		//获取数据个数
		int count = powerMapper.countByCondition(entity);
		//构建page
		Page page=new Page(index, size, count, data);
		
		return page;
	}

	/**
	 * 获取modulars和powers
	 */
	@Override
	public Map<String, Object> toAddPower() {
		List<Map<String, Object>> modulars = modularMapper.findAll();
		List<Map<String, Object>> powers = powerMapper.findAll();
		Map<String, Object> map=new HashMap<>();
		map.put("modulars", modulars);
		map.put("powers", powers);
		return map;
	}

	/**
	 * 添加数据
	 */
	@Override
	public Map<String, Object> addPower(Map<String, Object> entity) {
		Map<String, Object> info=new HashMap<>();
		StringBuffer sb=new StringBuffer("新增失败,");
		Object powerName = entity.get("power_name");
		Object powerAction = entity.get("power_action");
		Object powerUrl = entity.get("power_url");
		int num=0;
		if(null==powerName||"".equals(powerName)){
			sb.append("权限名不能为空,");
			num++;
		}else{
			Map<String, Object> power = powerMapper.findByName(powerName);
			if(null!=power){
				sb.append("权限名已重复,");
				info.put("power_add_msg", sb.toString());
				return info;
			}
		}
		if(null==powerAction||"".equals(powerAction)){
			sb.append("请求路径不能为空,");
			num++;
		}
		if(null==powerUrl||"".equals(powerUrl)){
			sb.append("URL不能为空,");
			num++;
		}
		sb.setLength(sb.length()-1);
		if(num==0){
			int insert = powerMapper.insert(entity);
			if(insert>0){
				sb=new StringBuffer("新增成功！");
			}
		}
		info.put("power_add_msg", sb.toString());
		return info;
	}

	/**
	 * 获取页面显示数据
	 */
	@Override
	public Map<String, Object> toEditPower(Object powerId) {
		Map<String, Object> info=new HashMap<>();
		if(null!=powerId&&!"".equals(powerId)){
			Map<String, Object> power = powerMapper.findById(powerId);
			info.put("power", power);
		}else {
			info.put("power_edit_msg", "出错了,找不到此权限");
		}
		List<Map<String, Object>> powers = powerMapper.findAll();
		List<Map<String, Object>> modulars = modularMapper.findAll();
		info.put("powers", powers);
		info.put("modulars", modulars);
		return info;
	}

	/**
	 * 修改数据
	 */
	@Override
	public Map<String, Object> editPower(Map<String, Object> entity) {
		Map<String, Object> info = new HashMap<>();
		StringBuffer sb =new StringBuffer("编辑失败,");
		Object powerName = entity.get("power_name");
		Object powerAction = entity.get("power_action");
		Object powerUrl = entity.get("power_url");
		int num=0;
		if(null==powerName||"".equals(powerName)){
			sb.append("权限名不能为空,");
			num++;
		}else{
			Map<String, Object> power = powerMapper.findByName(powerName);
			if(null!=power && !(power.get("power_id").toString()).equals(entity.get("power_id"))){
				sb.append("权限名已重复,");
				info.put("power_edit_msg", sb.toString());
				return info;
			}
		}
		if(null==powerAction||"".equals(powerAction)){
			sb.append("请求路径不能为空,");
			num++;
		}
		if(null==powerUrl||"".equals(powerUrl)){
			sb.append("URL不能为空,");
			num++;
		}
		sb.setLength(sb.length()-1);
		if(0==num){
			int update = powerMapper.update(entity);
			if(update>0){
				sb=new StringBuffer("编辑成功！");
			}
		}
		info.put("power_edit_msg", sb.toString());
		return info;
	}

	/**
	 * 删除数据
	 */
	@Override
	public Map<String, Object> deletePower(Long[] power_id) {
		Map<String, Object> info=new HashMap<>();
		String msg="删除失败!";
		try {
			if(power_id.length>0){
				String powerIdStr = Arrays.toString(power_id);
				String powerIds = powerIdStr.substring(1, powerIdStr.length()-1);
				int delete = powerMapper.delete(powerIds);
				if(delete>0){
					msg="删除成功！";
				}
			}
		} catch (Exception e) {
			msg="未知异常";
		}
		info.put("power_list_msg", msg);
		return info;
	}

}
