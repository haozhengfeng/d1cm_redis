package com.d1cm.redis.member.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.d1cm.redis.member.mapper.MemberCommentMapper;
import com.d1cm.redis.member.mapper.MemberMapper;
import com.d1cm.redis.member.model.Member;
import com.d1cm.redis.member.model.MemberComment;
import com.d1cm.redis.member.model.MemberToken;
import com.d1cm.redis.utils.Md5Utils;

@Service
public class MemberService implements IMemberService {

	@Autowired
	MemberMapper memberMapper;

	@Autowired
	MemberCommentMapper memberCommentMapper;

	@Override
	@Cacheable(cacheNames = "member:token:", key = "'member:token:'+#token")
	public MemberToken checkLogin(String token) {
		// TODO 目前数据库未保存token 直接从redis中获取
		return null;
	}

	@Override
	@CachePut(cacheNames = "member:token:", key = "'member:token:'")
	@CacheEvict(cacheNames = "member:token:", key = "#mid")
	public MemberToken updateToken(String mid, String token) {
		MemberToken memberToken = new MemberToken();
		memberToken.setM_ID(mid);
		memberToken.setLogin_token(token);
		return memberToken;
	}

	@Override
	@CachePut(cacheNames = "member:info:", key = "'member:info:'")
	public Member checkMember(String username, String password) {
		String md5_login_pwd = Md5Utils.encryptMD5(password, 16);
		Member member = memberMapper.checkMember(username, md5_login_pwd);
		return member;
	}

	@Override
	public Map<String, String> checkMember(String openid) {
		Map<String, String> member = memberMapper.checkMemberByOpenid(openid);
		return member;
	}

	@Override
	public String generateToken() {
		return UUID.randomUUID().toString();
	}

	@Override
	@Cacheable(cacheNames = "member:info:", key = "'member:info:'+#mid")
	public Member getMember(String mid) {
		Member member = memberMapper.getMember(mid);
		return member;
	}

	@Override
	@CachePut(cacheNames = "member:info:", key = "'member:info:'+#member.m_ID")
	public Member updateMember(Member member) {
		// TODO 修改会员信息

		return member;
	}

	@Override
	@Cacheable(cacheNames = "member:comment:", key = "'member:comment_article:'+#articleid")
	public List<MemberComment> getArticleComment(String articleid) {
		return memberCommentMapper.getMemberCommentByArticle(articleid);
	}

	@Override
	@CachePut(cacheNames = "member:comment:", key = "'member:comment_article:'")
	public MemberComment updateComent(MemberComment comment) {
		return comment;
	}

	@Override
	@Cacheable(cacheNames = "member:comment:", key = "'member:comment_member:'+#mid")
	public List<MemberComment> getMemberComment(String mid) {
		return memberCommentMapper.getMemberCommentByMember(mid);
	}
	
	@Override
	@Cacheable(cacheNames = "member:comment:", key = "'member:comment_product:'+#productid")
	public List<MemberComment> getProductComment(String productid) {
		return null;
	}

}
