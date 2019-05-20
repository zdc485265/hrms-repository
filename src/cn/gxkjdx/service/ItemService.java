package cn.gxkjdx.service;

import java.util.Map;

import cn.gxkjdx.util.Page;

public interface ItemService {

	Page findItemByPage(Map<String, Object> entity,String index,String size);
	Map<String, Object> addItem(Map<String, Object> entity);
	Map<String, Object> toEditItem(Object itemId);
	Map<String, Object> editItem(Map<String, Object> entity);
	Map<String, Object> deleteItem(Long[] item_id);
}
