package cn.gxkjdx.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gxkjdx.mapper.AdminMapper;
import cn.gxkjdx.mapper.DeptMapper;
import cn.gxkjdx.mapper.ModularMapper;
import cn.gxkjdx.mapper.PowerMapper;
import cn.gxkjdx.mapper.RoleMapper;
import cn.gxkjdx.service.AdminService;
import cn.gxkjdx.util.Md5Utils;
import cn.gxkjdx.util.Page;

@Service
public class AdminServiceImpl implements AdminService{
	
	private static final Logger logger =LogManager.getLogger(AdminServiceImpl.class);
	
	
	@Autowired
	AdminMapper adminMapper;
	
	@Autowired
	ModularMapper modularMapper;
	
	@Autowired
	RoleMapper roleMapper;
	
	@Autowired
	PowerMapper powerMapper;
	
	@Autowired
	DeptMapper deptMapper;

	@Override
	public Map<String, Object> login(Map<String, Object> entity) {
		logger.debug("AdminServiceImpl---login");
		//��ȡ�˺ź�����
		Object adminAccount=entity.get("admin_account");
		Object adminPwd=entity.get("admin_pwd");
		//ͨ���˺Ų����û�
		Map<String, Object> admin = adminMapper.findByAccount(adminAccount);
		if(adminPwd.equals(admin.get("admin_pwd"))){
			//��ȡ��ɫ�û���ɫ��Ϣ
			Long roleId=(Long)admin.get("role_id");
			Map<String, Object> role = roleMapper.findById(roleId);
			//��ȡȨ����Ϣ
			String powerIds = (String)role.get("role_powers");
			List<Map<String, Object>> powers = powerMapper.findByIds(powerIds);
			role.put("powers", powers);
			//��ȡ���е�ģ��
			List<Map<String, Object>> modularAlls = modularMapper.findAll();
			List<Map<String, Object>> modulars =new ArrayList<>();
			for (Map<String, Object> modular : modularAlls) {
				Object modularId = modular.get("modular_id");
				for (Map<String, Object> power : powers) {
					Object modularId2 = power.get("modular_id");
					if(modularId.equals(modularId2)){
						modulars.add(modular);
						break;
					}
				}
			}
			admin.put("modulars", modulars);
			admin.put("role", role);
			return admin;
		}
		return null;
	}

	/**
	 * ��ȡpage
	 */
	@Override
	public Page findAdminByPage(Map<String, Object> entity,String indexo, String sizeo) {
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
		List<Map<String, Object>> data = adminMapper.findByConditionToPage(entity,index*size,size);
		for (Map<String, Object> admin : data) {
			Object roleId = admin.get("role_id");
			if(null!=roleId){
				Map<String, Object> role = roleMapper.findById(roleId);
				admin.put("role", role);
			}
		}
		//��ȡ���ݸ���
		int count = adminMapper.countByCondition(entity);
		//����page
		Page page=new Page(index, size, count, data);
		
		return page;
	}
	
	/**
	 * ���ҳ��
	 */
	public Map<String, Object> toAddAdmin(){
		Map<String, Object> map= new HashMap<>();
		List<Map<String, Object>> roles = roleMapper.findAll();
		List<Map<String, Object>> depts = deptMapper.findAll();
		map.put("roles", roles);
		map.put("depts", depts);
		return map;
	}
	
	/**
	 * �������
	 */
	@Override
	public Map<String, Object> addAdmin(Map<String, Object> entity) {
		Map<String, Object> info=new HashMap<>();
		StringBuffer sb=new StringBuffer("����ʧ��,");
		Object adminName = entity.get("admin_name");
		Object adminAccount = entity.get("admin_account");
		Object adminPwd = entity.get("admin_pwd");
		int num=0;
		if(null==adminName||"".equals(adminName)){
			sb.append("�û�������Ϊ��,");
			num++;
		}
		if(null==adminAccount||"".equals(adminAccount)){
			sb.append("�˺�������Ϊ��,");
			num++;
		}else{
			Map<String, Object> admin = adminMapper.findByAccount(adminAccount);
			if(null!=admin){
				sb.append("�˺������ظ�,");
				info.put("admin_add_msg", sb.toString());
				return info;
			}
		}
		
		if(null==adminPwd||"".equals(adminPwd)){
			sb.append("���벻��Ϊ��,");
			num++;
		}else{
			entity.put("admin_pwd", Md5Utils.md5(adminPwd.toString()));
		}
		
		sb.setLength(sb.length()-1);
		if(num==0){
			int insert = adminMapper.insert(entity);
			if(insert>0){
				sb=new StringBuffer("�����ɹ���");
			}
		}
		info.put("admin_add_msg", sb.toString());
		return info;
	}

	/**
	 * ��ȡҳ����ʾ����
	 */
	@Override
	public Map<String, Object> toEditAdmin(Object adminId) {
		Map<String, Object> info=new HashMap<>();
		if(null!=adminId&&!"".equals(adminId)){
			Map<String, Object> admin = adminMapper.findById(adminId);
			info.put("admin", admin);
		}else {
			info.put("admin_edit_msg", "������,δ�ҵ����û�");
		}
		List<Map<String, Object>> roles = roleMapper.findAll();
		List<Map<String, Object>> depts = deptMapper.findAll();
		info.put("roles", roles);
		info.put("depts", depts);
		return info;
	}

	/**
	 * �޸�����
	 */
	@Override
	public Map<String, Object> editAdmin(Map<String, Object> entity) {
		Map<String, Object> info = new HashMap<>();
		StringBuffer sb =new StringBuffer("�༭ʧ��,");
		Object adminName = entity.get("admin_name");
		Object adminAccount = entity.get("admin_account");
		Object adminPwd = entity.get("admin_pwd");
		int num=0;
		if(null==adminName||"".equals(adminName)){
			sb.append("�û�������Ϊ��,");
			num++;
		}
		if(null==adminAccount||"".equals(adminAccount)){
			sb.append("�˺�������Ϊ��,");
			num++;
		}else{
			Map<String, Object> admin = adminMapper.findByAccount(adminAccount);
			
			if(null!=admin && !(admin.get("admin_id").toString()).equals(entity.get("admin_id"))){
				sb.append("�˺������ظ�,");
				info.put("admin_edit_msg", sb.toString());
				return info;
			}
			Object oldAdminPwd = admin.get("admin_pwd");
			
			if(null==adminPwd||"".equals(adminPwd)){
				sb.append("���벻��Ϊ��,");
				num++;
			}else {
				if(!adminPwd.equals(oldAdminPwd)){
					entity.put("admin_pwd", Md5Utils.md5(adminPwd.toString()));
				}
			}
		}
		
		sb.setLength(sb.length()-1);
		if(0==num){
			int update = adminMapper.update(entity);
			if(update>0){
				sb=new StringBuffer("�༭�ɹ���");
			}
		}
		info.put("admin_edit_msg", sb.toString());
		return info;
	}

	/**
	 * ɾ������
	 */
	@Override
	public Map<String, Object> deleteAdmin(Long[] admin_id) {
		Map<String, Object> info=new HashMap<>();
		String msg="ɾ��ʧ��!";
		try {
			if(admin_id.length>0){
				String adminIdStr = Arrays.toString(admin_id);
				String adminIds = adminIdStr.substring(1, adminIdStr.length()-1);
				int delete = adminMapper.delete(adminIds);
				if(delete>0){
					msg="ɾ���ɹ���";
				}
			}
		} catch (Exception e) {
			msg="δ֪�쳣";
		}
		
		info.put("admin_list_msg", msg);
		return info;
	}
	
}
