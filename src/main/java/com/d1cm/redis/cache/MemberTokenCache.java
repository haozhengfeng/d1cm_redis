package com.d1cm.redis.cache;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.core.HashOperations;

import com.d1cm.redis.common.BaseReidsCache;
import com.d1cm.redis.member.model.MemberToken;
import com.d1cm.redis.utils.ObjectMapConvert;

public class MemberTokenCache extends BaseReidsCache implements Cache {

	@Override
	public ValueWrapper get(Object key) {
		try {
			HashOperations opsForHash = getRedisTemplate().opsForHash();
			Map<String, Object> entry = opsForHash.entries(key);
			Object object = ObjectMapConvert.mapToObject(entry, MemberToken.class);
			ValueWrapper obj = (object != null ? new SimpleValueWrapper(object) : null);
			log.info("获取到内容：" + key);
			return obj;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void put(Object key, Object value) {
		try {
			MemberToken mt = (MemberToken) value;
			key = key.toString() + mt.getLogin_token();
			Map<String, Object> memberTokenMap = ObjectMapConvert.objectToMap(mt);
			HashOperations opsForHash = getRedisTemplate().opsForHash();
			opsForHash.putAll(key, memberTokenMap);
			getRedisTemplate().expire(key.toString(), 7200, TimeUnit.SECONDS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void evict(Object key) {
		log.info("删除缓存：" + key);
		final String keyf = key.toString();
		HashOperations opsForHash = getRedisTemplate().opsForHash();
		Object token = opsForHash.get("member:info:" + key, "login_token");
		getRedisTemplate().expire("member:token:" + key, 0, TimeUnit.SECONDS);
	}

}
