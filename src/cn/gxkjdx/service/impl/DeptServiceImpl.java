package cn.gxkjdx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gxkjdx.mapper.DeptMapper;
import cn.gxkjdx.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService{
	
	@Autowired
	DeptMapper deptMapper;

	/**
	 * 添加数据
	 */
	@Override
	public Map<String, Object> toAddDept(Object deptId) {
		Map<String, Object> info=new HashMap<>();
		Map<String, Object> dept = deptMapper.findById(deptId);
		info.put("msgStatus", 1);
		info.put("msgInfo", "出了点小问题,请重试");
		if(null!=dept){
			info.put("msgStatus", 0);
		}
		info.put("dept", dept);
		return info;
	}

	/**
	 * 获取页面显示数据
	 */
	@Override
	public Map<String, Object> toEditDept(Object deptId) {
		Map<String, Object> info = new HashMap<>();
		Map<String, Object> deptParent = new HashMap<>();
		Map<String, Object> dept = deptMapper.findById(deptId);
		info.put("msgStatus", 1);
		info.put("msgInfo", "出了点小问题,请重试");
		if(null!=dept){
			Object parentId = dept.get("dept_parent");
			if(!(parentId.toString()).equals("0")){
				deptParent = deptMapper.findById(parentId);
			}else {
				deptParent.put("dept_id", 0);
				deptParent.put("dept_name", "顶级部门");
			}
			dept.put("deptParent", deptParent);
			info.put("msgStatus", 0);
		}
		info.put("dept", dept);
		return info;
	}

	
	/**
	 * 删除数据
	 */
	@Override
	public Map<String, Object> deleteDept(Object deptId) {
		Map<String, Object> info=new HashMap<>();
		info.put("msgStatus", 1);
		info.put("msgInfo", "出了点小问题,请重试");
		int delete = deptMapper.delete(deptId);
		if(delete>0){
			info.put("msgStatus", 0);
			info.put("msgInfo", "删除成功");
		}
		return info;
	}
	
	/**
	 * 获取树
	 * @throws UnsupportedEncodingException 
	 */
	@Override
	public String getDepts(Object deptId){
		List<Map<String, Object>> depts = deptMapper.findByParentId(deptId);
		String jsonTree="";
		jsonTree+="[";
		for (Map<String, Object> dept : depts) {
			String deptName=(String) dept.get("dept_name");
			jsonTree+="{";
			List<Map<String, Object>> depts2 = deptMapper.findByParentId(deptId);
			if(depts2.size()==0){
				jsonTree+=String.format("\"id\": \"%s\", \"text\": \"%s\", \"state\": \"open\"", dept.get("dept_id") , deptName);
			}else{
				jsonTree+=String.format("\"id\": \"%s\", \"text\": \"%s\", \"state\": \"closed\"", dept.get("dept_id") , deptName);
			}
			jsonTree+="},";
	    }
		jsonTree=jsonTree.substring(0, jsonTree.length() - 1); 
		jsonTree+="]";
		return  jsonTree;
	}

	@Override
	public Map<String, Object> deptSetUp(Map<String, Object> map) {
		Map<String, Object> info=new HashMap<>();
		StringBuffer sb=new StringBuffer("操作失败,");
		Object setUp = map.get("set_up");
		if(null!=setUp&&!"".equals(setUp)){
			Object deptId = map.get("dept_id");
			Object deptName = map.get("dept_name");
			Object deptCode = map.get("dept_code");
			Object deptAbbreviation = map.get("dept_abbreviation");
			Object deptParent = map.get("dept_parent");
			Object deptAddress = map.get("dept_address");
			Object deptIntroduction = map.get("dept_introduction");
			Object deptRemarks = map.get("dept_remarks");
			Object deptStatus = map.get("dept_status");
			int num=0;
			if(null==deptName||"".equals(deptName)){
				sb.append("组织全称不能为空,");
				num++;
			}
			if(null==deptCode||"".equals(deptCode)){
				sb.append("组织编码不能为空,");
				num++;
			}
			if(null==deptAbbreviation||"".equals(deptAbbreviation)){
				sb.append("组织简称不能为空,");
				num++;
			}
			if(null==deptParent||"".equals(deptParent)){
				sb.append("所属组织不能为空,");
				num++;
			}
			if(null==deptAddress||"".equals(deptAddress)){
				sb.append("地址不能为空,");
				num++;
			}
			if(null==deptIntroduction||"".equals(deptIntroduction)){
				sb.append("简介不能为空,");
				num++;
			}
			if(null==deptRemarks||"".equals(deptRemarks)){
				sb.append("备注不能为空,");
				num++;
			}
			if(null==deptStatus||"".equals(deptStatus)){
				sb.append("是否启用不能为空,");
				num++;
			}
			if(setUp.equals("1")&&num==0){
				int insert = deptMapper.insert(map);
				if(insert>0){
					info.put("dept_set_up_msg", "新增成功");
				}else{
					info.put("dept_set_up_msg", "新增失败");
				}
			}
			if(setUp.equals("2")&&num==0){
				Map<String, Object> dept = deptMapper.findById(deptId);
				if(null!=dept){
					int update = deptMapper.update(map);
					if(update>0){
						info.put("dept_set_up_msg", "编辑成功");
					}else{
						info.put("dept_set_up_msg", "编辑失败");
					}
				}else {
					info.put("dept_set_up_msg", "未找到此组织");
				}
			}
		}else {
			info.put("dept_set_up_msg", "请选择所属部门");
		}
		return info;
	}

}
