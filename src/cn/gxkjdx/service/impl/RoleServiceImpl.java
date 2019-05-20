package cn.gxkjdx.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gxkjdx.mapper.ModularMapper;
import cn.gxkjdx.mapper.PowerMapper;
import cn.gxkjdx.mapper.RoleMapper;
import cn.gxkjdx.service.RoleService;
import cn.gxkjdx.util.Page;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleMapper roleMapper;
	
	@Autowired
	ModularMapper modularMapper;
	
	@Autowired
	PowerMapper powerMapper;

	/**
	 * ��ȡpage
	 */
	@Override
	public Page findRoleByPage(Map<String, Object> entity,String indexo, String sizeo) {
		int index,size;
		
		//�п�
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
		//��ȡ����
		List<Map<String, Object>> data = roleMapper.findByConditionToPage(entity,index*size,size);
		//��ȡ���ݸ���
		int count = roleMapper.countByCondition(entity);
		//����page
		Page page=new Page(index, size, count, data);
		
		return page;
	}

	/**
	 * ��ȡmodulars��roles
	 */
	@Override
	public Map<String, Object> toAddRole() {
		Map<String, Object> map=new HashMap<>();
		List<Map<String, Object>> modulars = modularMapper.findAll();
		for (Map<String, Object> modular : modulars) {
			Object modularId = modular.get("modular_id");
			List<Map<String, Object>> powers = powerMapper.findByModularId(modularId);
			modular.put("powers", powers);
		}
		map.put("modulars", modulars);
		return map;
	}

	/**
	 * �������
	 */
	@Override
	public Map<String, Object> addRole(Map<String, Object> entity) {
		Map<String, Object> info=new HashMap<>();
		StringBuffer sb=new StringBuffer("����ʧ��,");
		Object roleName = entity.get("role_name");
		Object rolePowers = entity.get("role_powers");
		int num=0;
		if(null==roleName||"".equals(roleName)){
			sb.append("��ɫ������Ϊ��,");
			num++;
		}else{
			Map<String, Object> role = roleMapper.findByName(roleName);
			if(null!=role){
				sb.append("��ɫ�����ظ�,");
				info.put("role_add_msg", sb.toString());
				return info;
			}
		}
		if(null==rolePowers||"".equals(rolePowers)){
			sb.append("��ɫȨ�޲���Ϊ��,");
			num++;
		}
		sb.setLength(sb.length()-1);
		if(num==0){
			entity.put("role_powers", ((String)rolePowers).replaceAll(" ", ""));
			int insert = roleMapper.insert(entity);
			if(insert>0){
				sb=new StringBuffer("�����ɹ���");
			}
		}
		info.put("role_add_msg", sb.toString());
		return info;
	}

	/**
	 * ��ȡҳ����ʾ����
	 */
	@Override
	public Map<String, Object> toEditRole(Object roleId) {
		Map<String, Object> info=new HashMap<>();
		if(null!=roleId&&!"".equals(roleId)){
			Map<String, Object> role = roleMapper.findById(roleId);
			info.put("role", role);
		}else {
			info.put("role_edit_msg", "������,�Ҳ����˽�ɫ");
		}
		List<Map<String, Object>> modulars = modularMapper.findAll();
		for (Map<String, Object> modular : modulars) {
			Object modularId = modular.get("modular_id");
			List<Map<String, Object>> powers = powerMapper.findByModularId(modularId);
			modular.put("powers", powers);
		}
		info.put("modulars", modulars);
		return info;
	}

	/**
	 * �޸�����
	 */
	@Override
	public Map<String, Object> editRole(Map<String, Object> entity) {
		Map<String, Object> info = new HashMap<>();
		StringBuffer sb =new StringBuffer("�༭ʧ��,");
		Object roleName = entity.get("role_name");
		Object rolePowers = entity.get("role_powers");
		int num=0;
		if(null==roleName||"".equals(roleName)){
			sb.append("��ɫ������Ϊ��,");
			num++;
		}else{
			Map<String, Object> role = roleMapper.findByName(roleName);
			if(null!=role && !(role.get("role_id").toString()).equals(entity.get("role_id"))){
				sb.append("��ɫ�����ظ�,");
				info.put("role_edit_msg", sb.toString());
				return info;
			}
		}
		if(null==rolePowers||"".equals(rolePowers)){
			sb.append("��ɫȨ�޲���Ϊ��,");
			num++;
		}
		sb.setLength(sb.length()-1);
		if(0==num){
			entity.put("role_powers", ((String)rolePowers).replaceAll(" ", ""));
			int update = roleMapper.update(entity);
			if(update>0){
				sb=new StringBuffer("�༭�ɹ���");
			}
		}
		info.put("role_edit_msg", sb.toString());
		return info;
	}

	/**
	 * ɾ������
	 */
	@Override
	public Map<String, Object> deleteRole(Long[] role_id) {
		Map<String, Object> info=new HashMap<>();
		String msg="ɾ��ʧ��!";
		try {
			if(role_id.length>0){
				String roleIdStr = Arrays.toString(role_id);
				String roleIds = roleIdStr.substring(1, roleIdStr.length()-1);
				int delete = roleMapper.delete(roleIds);
				if(delete>0){
					msg="ɾ���ɹ���";
				}
			}
		} catch (Exception e) {
			msg="δ֪�쳣";
		}
		info.put("role_list_msg", msg);
		return info;
	}

}
