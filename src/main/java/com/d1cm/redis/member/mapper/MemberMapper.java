package com.d1cm.redis.member.mapper;

import java.util.Map;

import com.d1cm.redis.member.model.Member;

public interface MemberMapper {
	public Member checkMember(String username, String password);

	public Map<String, String> checkMemberByOpenid(String openid);
	
	public Member getMember(String mid);
}
