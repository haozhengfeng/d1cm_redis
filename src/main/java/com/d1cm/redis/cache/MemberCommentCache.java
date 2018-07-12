package com.d1cm.redis.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;

import com.d1cm.redis.common.BaseReidsCache;
import com.d1cm.redis.member.model.MemberComment;
import com.d1cm.redis.utils.ObjectMapConvert;

public class MemberCommentCache extends BaseReidsCache implements Cache {

	@Override
	public ValueWrapper get(Object key) {
		try {
			List<MemberComment> mcs = new ArrayList<MemberComment>();

			ListOperations opsForList = getRedisTemplate().opsForList();
			List<String> cids = opsForList.range(key, 0, 10);

			HashOperations opsForHash = getRedisTemplate().opsForHash();
			for (String cid : cids) {
				Map cmap = opsForHash.entries("member:comment:" + cid);
				MemberComment comment = (MemberComment) ObjectMapConvert.mapToObject(cmap, MemberComment.class);
				mcs.add(comment);
			}

			ValueWrapper obj = (mcs != null && mcs.size() > 0 ? new SimpleValueWrapper(mcs) : null);
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
			List<MemberComment> mcList = (List<MemberComment>) value;
			for (MemberComment mc : mcList) {
				// 缓存评论
				Map<String, Object> map = ObjectMapConvert.objectToMap(mc);
				HashOperations opsForHash = getRedisTemplate().opsForHash();
				opsForHash.putAll("member:comment:" + mc.getId(), map);

				// 更新列表
				ListOperations opsForList = getRedisTemplate().opsForList();
				opsForList.leftPush(key, mc.getId());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
