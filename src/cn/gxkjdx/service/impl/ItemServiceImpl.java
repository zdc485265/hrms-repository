package cn.gxkjdx.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gxkjdx.mapper.ItemMapper;
import cn.gxkjdx.service.ItemService;
import cn.gxkjdx.util.Page;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	ItemMapper itemMapper;

	/**
	 * ��ȡpage
	 */
	@Override
	public Page findItemByPage(Map<String, Object> entity,String indexo, String sizeo) {
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
		List<Map<String, Object>> data = itemMapper.findByConditionToPage(entity,index*size,size);
		
		//��ȡ���ݸ���
		int count = itemMapper.countByCondition(entity);
		//����page
		Page page=new Page(index, size, count, data);
		
		return page;
	}
	/**
	 * �������
	 */
	@Override
	public Map<String, Object> addItem(Map<String, Object> entity) {
		Map<String, Object> info=new HashMap<>();
		StringBuffer sb=new StringBuffer("����ʧ��,");
		Object itemName = entity.get("item_name");
		Object itemSort = entity.get("item_sort");
		int num=0;
		if(null==itemName||"".equals(itemName)){
			sb.append("н����Ŀ������Ϊ��,");
			num++;
		}else{
			Map<String, Object> item = itemMapper.findByName(itemName);
			if(null!=item){
				sb.append("н����Ŀ�����ظ�,");
				info.put("item_add_msg", sb.toString());
				return info;
			}
		}
		if(null==itemSort||"".equals(itemSort)){
			sb.append("н����Ŀ������Ϊ��,");
			num++;
		}
		sb.setLength(sb.length()-1);
		if(num==0){
			int insert = itemMapper.insert(entity);
			if(insert>0){
				sb=new StringBuffer("�����ɹ���");
			}
		}
		info.put("item_add_msg", sb.toString());
		return info;
	}

	/**
	 * ��ȡҳ����ʾ����
	 */
	@Override
	public Map<String, Object> toEditItem(Object itemId) {
		Map<String, Object> info =new HashMap<>();
		if(null!=itemId&&!"".equals(itemId)){
			Map<String, Object> item = itemMapper.findById(itemId);
			info.put("item", item);
		}else {
			info.put("item_edit_msg", "������,δ�ҵ���н����Ŀ");
		}
		return info;
	}

	/**
	 * �޸�����
	 */
	@Override
	public Map<String, Object> editItem(Map<String, Object> entity) {
		Map<String, Object> info = new HashMap<>();
		StringBuffer sb =new StringBuffer("�༭ʧ��,");
		Object itemName = entity.get("item_name");
		Object itemSort = entity.get("item_sort");
		int num=0;
		if(null==itemName||"".equals(itemName)){
			sb.append("н����Ŀ������Ϊ��,");
			num++;
		}else{
			Map<String, Object> item = itemMapper.findByName(itemName);
			if(null!=item && !(item.get("item_id").toString()).equals(entity.get("item_id"))){
				sb.append("н����Ŀ�����ظ�,");
				info.put("item_edit_msg", sb.toString());
				return info;
			}
		}
		if(null==itemSort||"".equals(itemSort)){
			sb.append("н����Ŀ������Ϊ��,");
			num++;
		}
		sb.setLength(sb.length()-1);
		if(0==num){
			int update = itemMapper.update(entity);
			if(update>0){
				sb=new StringBuffer("�༭�ɹ���");
			}
		}
		info.put("item_edit_msg", sb.toString());
		return info;
	}

	/**
	 * ɾ������
	 */
	@Override
	public Map<String, Object> deleteItem(Long[] item_id) {
		Map<String, Object> info=new HashMap<>();
		String msg="ɾ��ʧ��!";
		try {
			if(item_id.length>0){
				String itemIdStr = Arrays.toString(item_id);
				String itemIds = itemIdStr.substring(1, itemIdStr.length()-1);
				int delete = itemMapper.delete(itemIds);
				if(delete>0){
					msg="ɾ���ɹ���";
				}
			}
		} catch (Exception e) {
			msg="δ֪�쳣";
		}
		info.put("item_list_msg", msg);
		return info;
	}

}
