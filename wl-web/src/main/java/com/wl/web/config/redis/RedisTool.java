package com.wl.web.config.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisCluster;

@Service
@Slf4j
public class RedisTool {
	@Autowired
	private JedisCluster jedisCluster;

	/**
	 * 
	 * @Title: insertRedis
	 * @Description: 插入redis
	 * @author: czn
	 * @date: 2017年8月7日 上午10:58:00
	 * @version: V1.0
	 * @param key
	 * @param field
	 * @param object void
	 *
	 * @updateDate:
	 * @update:
	 */
	public void insert(String key, String field, Object object) {
		try {
			jedisCluster.hset(key, field, JSONObject.toJSONString(object));
		} catch (Exception e) {
			log.error("redis insert", e);
			e.printStackTrace();
		}
	}

	public void insert(String key, Object object) {
		try {
			jedisCluster.set(key, JSONObject.toJSONString(object));
		} catch (Exception e) {
			log.error("redis insert", e);
			e.printStackTrace();
		}
	}

	public void insert(String key, Object object, long time/** 过期时间 */
	) {
		try {
			jedisCluster.psetex(key, time, JSONObject.toJSONString(object));
		} catch (Exception e) {
			log.error("redis insert", e);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: updateRedis
	 * @Description: 更新redis
	 * @author: czn
	 * @date: 2017年8月7日 上午10:54:02
	 * @version: V1.0 void
	 *
	 * @updateDate:
	 * @update:
	 */
	public void update(String key, String field, Object object) {
		try {
			jedisCluster.hset(key, field, JSONObject.toJSONString(object));
		} catch (Exception e) {
			log.error("redis update", e);
			e.printStackTrace();
		}
	}

	public void update(String key, Object object, long time) {
		try {
			jedisCluster.psetex(key, time, JSONObject.toJSONString(object));
		} catch (Exception e) {
			log.error("redis update", e);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: deleteRedis
	 * @Description: 删除redis
	 * @author: czn
	 * @date: 2017年8月9日 下午2:06:37
	 * @version: V1.0
	 * @param key
	 * @param field
	 * @param object void
	 *
	 * @updateDate:
	 * @update:
	 */
	public void delete(String key, String field) {
		try {
			jedisCluster.hdel(key, field);
		} catch (Exception e) {
			log.error("redis del", e);
			e.printStackTrace();
		}
	}

	public void delete(String key) {
		try {
			jedisCluster.del(key);
		} catch (Exception e) {
			log.error("redis del", e);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: selectRedis
	 * @Description: redis 获取
	 * @author: czn
	 * @date: 2017年8月14日 下午5:03:06
	 * @version: V1.0
	 * @param         <T>
	 * @param key
	 * @param field
	 * @param classes
	 * @return Object
	 *
	 * @updateDate:
	 * @update:
	 */
	public <T> T select(String key, String field, Class<T> classes) {
		String result = jedisCluster.hget(key, field);
		if (StringUtils.isNotBlank(result)) {
			return (T) JSONObject.parseObject(result, classes);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @Title: select
	 * @Description: 查询redis hget
	 * @author: czn
	 * @date: 2018年1月15日 上午10:33:01
	 * @version: V1.0
	 * @param key
	 * @param field
	 * @return String
	 *
	 */
	public String select(String key, String field) {
		String result = jedisCluster.hget(key, field);
		if (StringUtils.isNotBlank(result)) {
			return result;
		}
		return "";
	}
	
	/**
	 * 
	 * @Title: select
	 * @Description: 查询redis get
	 * @author: czn
	 * @date: 2018年1月15日 上午10:37:33
	 * @version: V1.0
	 * @param key
	 * @return String
	 *
	 */
	public String select(String key) {
		String result = jedisCluster.get(key);
		if (StringUtils.isNotBlank(result)) {
			return result;
		}
		return "";
	}

	/**
	 * 
	 * @Title: select
	 * @Description: 查询redis 返回对象
	 * @author: czn
	 * @date: 2018年1月15日 上午10:39:03
	 * @version: V1.0
	 * @param key
	 * @param clazz
	 * @return T
	 *
	 */
	public <T> T select(String key, Class<T> clazz) {
		String result = jedisCluster.get(key);
		if (StringUtils.isNotBlank(result)) {
			return JSONObject.parseObject(result, clazz);
		}
		return null;
	}
}
