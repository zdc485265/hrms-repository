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
	 * ��ȡpage
	 */
	@Override
	public Page findModularByPage(Map<String, Object> entity,String indexo, String sizeo) {
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
		List<Map<String, Object>> data = modularMapper.findByConditionToPage(entity,index*size,size);
		
		//��ȡ���ݸ���
		int count = modularMapper.countByCondition(entity);
		//����page
		Page page=new Page(index, size, count, data);
		
		return page;
	}
	/**
	 * �������
	 */
	@Override
	public Map<String, Object> addModular(Map<String, Object> entity) {
		Map<String, Object> info=new HashMap<>();
		StringBuffer sb=new StringBuffer("����ʧ��,");
		Object modularName = entity.get("modular_name");
		Object modularSort = entity.get("modular_sort");
		int num=0;
		if(null==modularName||"".equals(modularName)){
			sb.append("ģ��������Ϊ��,");
			num++;
		}else{
			Map<String, Object> modular = modularMapper.findByName(modularName);
			if(null!=modular){
				sb.append("ģ�������ظ�,");
				info.put("modular_add_msg", sb.toString());
				return info;
			}
		}
		if(null==modularSort||"".equals(modularSort)){
			sb.append("������Ų���Ϊ��,");
			num++;
		}
		sb.setLength(sb.length()-1);
		if(num==0){
			try {
				entity.put("modular_sort", Long.valueOf((String)modularSort));
				int insert = modularMapper.insert(entity);
				if(insert>0){
					sb=new StringBuffer("�����ɹ���");
				}
			} catch (NumberFormatException e) {
				sb.append(",������ű���Ϊ��ֵ");
			}catch (Exception e) {
				sb.append(",δ֪����");
			}
		}
		info.put("modular_add_msg", sb.toString());
		return info;
	}

	/**
	 * ��ȡҳ����ʾ����
	 */
	@Override
	public Map<String, Object> toEditModular(Object modularId) {
		Map<String, Object> info =new HashMap<>();
		if(null!=modularId&&!"".equals(modularId)){
			Map<String, Object> modular = modularMapper.findById(modularId);
			info.put("modular", modular);
		}else {
			info.put("modular_edit_msg", "������,δ�ҵ���ģ��");
		}
		return info;
	}

	/**
	 * �޸�����
	 */
	@Override
	public Map<String, Object> editModular(Map<String, Object> entity) {
		Map<String, Object> info = new HashMap<>();
		StringBuffer sb =new StringBuffer("�༭ʧ��,");
		Object modularName = entity.get("modular_name");
		Object modularSort = entity.get("modular_sort");
		int num=0;
		if(null==modularName||"".equals(modularName)){
			sb.append("ģ��������Ϊ��,");
			num++;
		}else{
			Map<String, Object> modular = modularMapper.findByName(modularName);
			if(null!=modular && !(modular.get("modular_id").toString()).equals(entity.get("modular_id"))){
				sb.append("ģ�������ظ�,");
				info.put("modular_edit_msg", sb.toString());
				return info;
			}
		}
		if(null==modularSort||"".equals(modularSort)){
			sb.append("ģ��������Ϊ��,");
			num++;
		}
		sb.setLength(sb.length()-1);
		if(0==num){
			int update = modularMapper.update(entity);
			if(update>0){
				sb=new StringBuffer("�༭�ɹ���");
			}
		}
		info.put("modular_edit_msg", sb.toString());
		return info;
	}

	/**
	 * ɾ������
	 */
	@Override
	public Map<String, Object> deleteModular(Long[] modular_id) {
		Map<String, Object> info=new HashMap<>();
		String msg="ɾ��ʧ��!";
		try {
			if(modular_id.length>0){
				String modularIdStr = Arrays.toString(modular_id);
				String modularIds = modularIdStr.substring(1, modularIdStr.length()-1);
				int delete = modularMapper.delete(modularIds);
				if(delete>0){
					msg="ɾ���ɹ���";
				}
			}
		} catch (Exception e) {
			msg="δ֪�쳣";
		}
		info.put("modular_list_msg", msg);
		return info;
	}

}
