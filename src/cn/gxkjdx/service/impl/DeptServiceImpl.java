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
	 * �������
	 */
	@Override
	public Map<String, Object> toAddDept(Object deptId) {
		Map<String, Object> info=new HashMap<>();
		Map<String, Object> dept = deptMapper.findById(deptId);
		info.put("msgStatus", 1);
		info.put("msgInfo", "���˵�С����,������");
		if(null!=dept){
			info.put("msgStatus", 0);
		}
		info.put("dept", dept);
		return info;
	}

	/**
	 * ��ȡҳ����ʾ����
	 */
	@Override
	public Map<String, Object> toEditDept(Object deptId) {
		Map<String, Object> info = new HashMap<>();
		Map<String, Object> deptParent = new HashMap<>();
		Map<String, Object> dept = deptMapper.findById(deptId);
		info.put("msgStatus", 1);
		info.put("msgInfo", "���˵�С����,������");
		if(null!=dept){
			Object parentId = dept.get("dept_parent");
			if(!(parentId.toString()).equals("0")){
				deptParent = deptMapper.findById(parentId);
			}else {
				deptParent.put("dept_id", 0);
				deptParent.put("dept_name", "��������");
			}
			dept.put("deptParent", deptParent);
			info.put("msgStatus", 0);
		}
		info.put("dept", dept);
		return info;
	}

	
	/**
	 * ɾ������
	 */
	@Override
	public Map<String, Object> deleteDept(Object deptId) {
		Map<String, Object> info=new HashMap<>();
		info.put("msgStatus", 1);
		info.put("msgInfo", "���˵�С����,������");
		int delete = deptMapper.delete(deptId);
		if(delete>0){
			info.put("msgStatus", 0);
			info.put("msgInfo", "ɾ���ɹ�");
		}
		return info;
	}
	
	/**
	 * ��ȡ��
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
		StringBuffer sb=new StringBuffer("����ʧ��,");
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
				sb.append("��֯ȫ�Ʋ���Ϊ��,");
				num++;
			}
			if(null==deptCode||"".equals(deptCode)){
				sb.append("��֯���벻��Ϊ��,");
				num++;
			}
			if(null==deptAbbreviation||"".equals(deptAbbreviation)){
				sb.append("��֯��Ʋ���Ϊ��,");
				num++;
			}
			if(null==deptParent||"".equals(deptParent)){
				sb.append("������֯����Ϊ��,");
				num++;
			}
			if(null==deptAddress||"".equals(deptAddress)){
				sb.append("��ַ����Ϊ��,");
				num++;
			}
			if(null==deptIntroduction||"".equals(deptIntroduction)){
				sb.append("��鲻��Ϊ��,");
				num++;
			}
			if(null==deptRemarks||"".equals(deptRemarks)){
				sb.append("��ע����Ϊ��,");
				num++;
			}
			if(null==deptStatus||"".equals(deptStatus)){
				sb.append("�Ƿ����ò���Ϊ��,");
				num++;
			}
			if(setUp.equals("1")&&num==0){
				int insert = deptMapper.insert(map);
				if(insert>0){
					info.put("dept_set_up_msg", "�����ɹ�");
				}else{
					info.put("dept_set_up_msg", "����ʧ��");
				}
			}
			if(setUp.equals("2")&&num==0){
				Map<String, Object> dept = deptMapper.findById(deptId);
				if(null!=dept){
					int update = deptMapper.update(map);
					if(update>0){
						info.put("dept_set_up_msg", "�༭�ɹ�");
					}else{
						info.put("dept_set_up_msg", "�༭ʧ��");
					}
				}else {
					info.put("dept_set_up_msg", "δ�ҵ�����֯");
				}
			}
		}else {
			info.put("dept_set_up_msg", "��ѡ����������");
		}
		return info;
	}

}
