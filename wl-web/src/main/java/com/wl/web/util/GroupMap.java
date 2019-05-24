package com.wl.web.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupMap {
	private Map<String, List<Object>> groupMap = new HashMap<>();

	public Map<String, List<Object>> getGroupMap() {
		return groupMap;
	}

	public void setGroupMap(Map<String, List<Object>> groupMap) {
		this.groupMap = groupMap;
	}
	/**
	 * 
	 * @Title: add  
	 * @Description: 添加分组
	 * @author: czn
	 * @date: 2018年2月9日 上午10:11:48
	 * @version: V1.0
	 * @param key
	 * @param o void
	 *
	 */
	public void add(String key,Object o) {
		List<Object> objects = groupMap.get(key);
		if(null == objects) {
			objects = new ArrayList<>();
		}
		objects.add(o);
		groupMap.put(key, objects);
	}
	
	public void add(Integer key,Object o) {
		add(key.toString(),o);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> get(String key,Class<T> clazz) {
		List<Object> list = groupMap.get(key);
		return (List<T>) list;
	}
}
