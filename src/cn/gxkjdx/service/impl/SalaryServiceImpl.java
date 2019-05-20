package cn.gxkjdx.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gxkjdx.mapper.AdminMapper;
import cn.gxkjdx.mapper.ItemMapper;
import cn.gxkjdx.mapper.SalaryMapper;
import cn.gxkjdx.service.SalaryService;
import cn.gxkjdx.util.Page;

@Service
public class SalaryServiceImpl implements SalaryService{
	
	@Autowired
	SalaryMapper salaryMapper;
	
	@Autowired
	ItemMapper itemMapper;
	
	@Autowired
	AdminMapper adminMapper;

	/**
	 * 获取page
	 */
	@Override
	public Page findSalaryByPage(Map<String, Object> entity,String indexo, String sizeo) {
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
		List<Map<String, Object>> data = salaryMapper.findByConditionToPage(entity,index*size,size);
		
		//获取数据个数
		int count = salaryMapper.countByCondition(entity);
		//构建page
		Page page=new Page(index, size, count, data);
		
		return page;
	}
	
	/**
	 * 添加页面
	 */
	@Override
	public Map<String, Object> toAddSalary() {
		Map<String, Object> info=new HashMap<>();
		List<Map<String, Object>> items = itemMapper.findAll();
		info.put("items", items);
		Date date = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String newDate = sdf.format(date);
		info.put("newDate", newDate);
		return info;
	}
	
	/**
	 * 添加数据
	 */
	@Override
	public Map<String, Object> addSalary(Map<String, Object> entity,Long[]item_id,Float[]salary_item_value) {
		Map<String, Object> info=new HashMap<>();
		StringBuffer sb=new StringBuffer("新增失败,");
		Object salaryName = entity.get("salary_name");
		Object salaryCode = entity.get("salary_code");
		Object salaryStatus = entity.get("salary_status");
		Object salaryDate = entity.get("salary_date");
		Object salaryMarker = entity.get("salary_marker");
		Object adminId = entity.get("admin_id");
		int num=0;
		if(null==salaryName||"".equals(salaryName)){
			sb.append("薪酬名不能为空,");
			num++;
		}else{
			Map<String, Object> salary = salaryMapper.findByName(salaryName);
			if(null!=salary){
				sb.append("薪酬名已重复,");
				info.put("salary_add_msg", sb.toString());
				return info;
			}
		}
		if(null==salaryCode||"".equals(salaryCode)){
			sb.append("薪酬编码不能为空,");
			num++;
		}else{
			Map<String, Object> salary = salaryMapper.findByCode(salaryCode);
			if(null!=salary){
				sb.append("薪酬编码已重复,");
				info.put("salary_add_msg", sb.toString());
				return info;
			}
		}
		if(null==salaryStatus||"".equals(salaryStatus)){
			entity.put("salary_status", 0);
		}
		if(null==salaryDate||"".equals(salaryDate)){
			sb.append("登记时间不能为空,");
			num++;
		}
		if(null==salaryMarker||"".equals(salaryMarker)){
			sb.append("制定人不能为空,");
			num++;
		}
		if(null==adminId||"".equals(adminId)){
			sb.append("登记人不能为空,");
			num++;
		}
		sb.setLength(sb.length()-1);
		if(num==0){
			int insert = salaryMapper.insert(entity);
			if(insert>0){
				Map<String, Object> map =new HashMap<>();
				Map<String, Object> salary = salaryMapper.findByCode(salaryCode);
				int i=0;
				for(Long itemId:item_id){
					if(itemId!=null){
						Float salaryItemValue = salary_item_value[i];
						map.put("item_id", itemId);
						map.put("salary_item_value", salaryItemValue);
						map.put("salary_id", salary.get("salary_id"));
						salaryMapper.insertSalaryItem(map);
						i++;
					}
				}
				sb=new StringBuffer("新增成功！");
			}
		}
		info.put("salary_add_msg", sb.toString());
		return info;
	}

	/**
	 * 获取页面显示数据
	 */
	@Override
	public Map<String, Object> toEditSalary(Object salaryId) {
		Map<String, Object> info =new HashMap<>();
		if(null!=salaryId&&!"".equals(salaryId)){
			Map<String, Object> salary = salaryMapper.findById(salaryId);
			Object adminId = salary.get("admin_id");
			Map<String, Object> admin = adminMapper.findById(adminId);
			salary.put("admin", admin);
			info.put("salary", salary);
			List<Map<String, Object>> items = itemMapper.findAll();
			info.put("items", items);
			List<Map<String, Object>> salaryItems = salaryMapper.findSalaryItemBySalaryId(salaryId);
			info.put("salaryItems", salaryItems);
			float num=0;
			for (Map<String, Object> item : items) {
				Object itemId = item.get("item_id");
				if(null!=itemId&&!"".equals(itemId)){
					for (Map<String, Object> salaryItem : salaryItems) {
						if(null!=salaryItem&&itemId.equals(salaryItem.get("item_id"))){
							Object salaryItemValue = salaryItem.get("salary_item_value");
							item.put("salary_item_value", salaryItemValue);
							if(null!=salaryItemValue&&!"".equals(salaryItemValue)){
								num+=(float)salaryItemValue;
							}
						}else {
							item.put("salary_item_value", 0.0);
						}
					}
				}
			}
			info.put("num", num);
		}else {
			info.put("salary_edit_msg", "出错了,未找到此薪酬");
		}
		return info;
	}

	/**
	 * 获取明细数据
	 */
	@Override
	public Map<String, Object> toSalaryDetailed(Object salaryId) {
		Map<String, Object> info =new HashMap<>();
		if(null!=salaryId&&!"".equals(salaryId)){
			Map<String, Object> salary = salaryMapper.findById(salaryId);
			Object adminId = salary.get("admin_id");
			Map<String, Object> admin = adminMapper.findById(adminId);
			salary.put("admin", admin);
			info.put("salary", salary);
			List<Map<String, Object>> items = itemMapper.findAll();
			info.put("items", items);
			List<Map<String, Object>> salaryItems = salaryMapper.findSalaryItemBySalaryId(salaryId);
			info.put("salaryItems", salaryItems);
			float num=0;
			for (Map<String, Object> item : items) {
				Object itemId = item.get("item_id");
				if(null!=itemId&&!"".equals(itemId)){
					for (Map<String, Object> salaryItem : salaryItems) {
						if(null!=salaryItem&&itemId.equals(salaryItem.get("item_id"))){
							Object salaryItemValue = salaryItem.get("salary_item_value");
							item.put("salary_item_value", salaryItemValue);
							if(null!=salaryItemValue&&!"".equals(salaryItemValue)){
								num+=(float)salaryItemValue;
							}
						}
					}
				}
			}
			info.put("num", num);
		}else {
			info.put("salary_detail_msg", "出错了,未找到此薪酬");
		}
		return info;
	}
	
	/**
	 * 修改数据
	 */
	@Override
	public Map<String, Object> editSalary(Map<String, Object> entity,Long[]item_id,Float[]salary_item_value) {
		Map<String, Object> info = new HashMap<>();
		StringBuffer sb =new StringBuffer("编辑失败,");
		Object salaryId = entity.get("salary_id");
		if (null!=salaryId&&!"".equals(salaryId)) {
			Object salaryName = entity.get("salary_name");
			Object salaryCode = entity.get("salary_code");
			Object salaryStatus = entity.get("salary_status");
			Object salaryDate = entity.get("salary_date");
			Object salaryMarker = entity.get("salary_marker");
			Object adminId = entity.get("admin_id");
			int num=0;
			if(null==salaryName||"".equals(salaryName)){
				sb.append("薪酬名不能为空,");
				num++;
			}else{
				Map<String, Object> salary = salaryMapper.findByName(salaryName);
				if(null!=salary && !(salary.get("salary_id").toString()).equals(salaryId)){
					sb.append("薪酬名已重复,");
					info.put("salary_edit_msg", sb.toString());
					return info;
				}
			}
			if(null==salaryCode||"".equals(salaryCode)){
				sb.append("薪酬编码不能为空,");
				num++;
			}else{
				Map<String, Object> salary = salaryMapper.findByCode(salaryCode);
				if(null!=salary && !(salary.get("salary_id").toString()).equals(salaryId)){
					sb.append("薪酬编码已重复,");
					info.put("salary_add_msg", sb.toString());
					return info;
				}
			}
			if(null==salaryStatus||"".equals(salaryStatus)){
				entity.put("salary_status", 0);
			}
			if(null==salaryDate||"".equals(salaryDate)){
				sb.append("登记时间不能为空,");
				num++;
			}
			if(null==salaryMarker||"".equals(salaryMarker)){
				sb.append("制定人不能为空,");
				num++;
			}
			if(null==adminId||"".equals(adminId)){
				sb.append("登记人不能为空,");
				num++;
			}
			sb.setLength(sb.length()-1);
			if(0==num){
				int update = salaryMapper.update(entity);
				if(update>0){
					Map<String, Object> map =new HashMap<>();
					map.put("salary_id", salaryId);
					int i=0;
					for(Long itemId:item_id){
						if(null!=itemId){
							Float salaryItemValue = salary_item_value[i];
							map.put("item_id", itemId);
							map.put("salary_item_value", salaryItemValue);
							Map<String, Object> salaryItem = salaryMapper.findSalaryItemBySalaryIdAndItemId(map);
							if(null!=salaryItem){
								salaryMapper.updateSalaryItem(map);
							}else {
								salaryMapper.insertSalaryItem(map);
							}
							i++;
						}
					}
					sb=new StringBuffer("编辑成功！");
				}
			}
		
		}else {
			info.put("salary_edit_msg", "未找到此薪酬");
		}
		info.put("salary_edit_msg", sb.toString());
		return info;
	}

	/**
	 * 删除数据
	 */
	@Override
	public Map<String, Object> deleteSalary(Object salaryId) {
		Map<String, Object> info=new HashMap<>();
		String msg="删除失败!";
		if(null!=salaryId&&!"".equals(salaryId)){
			int delete = salaryMapper.delete(salaryId);
			if(delete>0){
				int deleteSalaryItem = salaryMapper.deleteSalaryItemBySalaryId(salaryId);
				if(deleteSalaryItem>0){
					msg="删除成功！";
				}
			}
		}
		info.put("salary_list_msg", msg);
		return info;
	}
	

}
