package com.d1cm.redis.cache;

import java.util.Map;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.core.HashOperations;

import com.d1cm.redis.common.BaseReidsCache;
import com.d1cm.redis.member.model.Member;
import com.d1cm.redis.utils.ObjectMapConvert;

public class MemberInfoCache extends BaseReidsCache implements Cache {

	@Override
	public ValueWrapper get(Object key) {
		log.info("获取缓存：" + key);
		final String keyf = key.toString();
		Object object = null;

		HashOperations opsForHash = getRedisTemplate().opsForHash();
		Map<String, Object> entry = opsForHash.entries(keyf);
		
		if(entry.get("m_ID")==null){
			return null;
		}

		try {
			object = ObjectMapConvert.mapToObject(entry, Member.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ValueWrapper obj = (object != null ? new SimpleValueWrapper(object) : null);
		log.info("获取到内容：" + key);
		return obj;
	}

	@Override
	public void put(Object key, Object value) {

		try {
			Member m = (Member) value;
			//key = key.toString() + m.getM_ID();
			log.info("加入缓存：" + key);

			Map<String, Object> member = ObjectMapConvert.objectToMap(m);

			HashOperations opsForHash = getRedisTemplate().opsForHash();
			opsForHash.putAll(key, member);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
