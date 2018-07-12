package com.d1cm.redis.member.service;

import java.util.List;
import java.util.Map;

import com.d1cm.redis.member.model.Member;
import com.d1cm.redis.member.model.MemberComment;
import com.d1cm.redis.member.model.MemberToken;

public interface IMemberService {
	
	/**
	 * 
	 * @param token
	 * @return
	 */
	public MemberToken checkLogin(String token);

	/**
	 * 更新token
	 * @param mid
	 * @param token
	 * @return
	 */
	public MemberToken updateToken(String mid,String token);
	/**
	 * 通过用户名密码验证
	 * 
	 * @param username
	 * @param password
	 */
	public Member checkMember(String username, String password);

	/**
	 * 通过第三方平台openid验证
	 * 
	 * @param openid
	 */
	public Map<String, String> checkMember(String openid);

	/**
	 * 为会员生成令牌
	 */
	public String generateToken();

	/**
	 * 通过mid获取会员信息
	 * 
	 * @param mid
	 * @return
	 */
	public Member getMember(String mid);

	/**
	 * 更新会员信息
	 * @param member
	 * @return
	 */
	public Member updateMember(Member member);
	
	/**
	 * 获取文章评论
	 * @param articleid
	 * @return
	 */
	public List<MemberComment> getArticleComment(String articleid);
	
	/**
	 * 获取用户评论
	 * @param articleid
	 * @return
	 */
	public List<MemberComment> getMemberComment(String mid);
	
	/**
	 * 更新评论
	 * @param comment
	 * @return
	 */
	public MemberComment updateComent(MemberComment comment);
	
	/**
	 * 获取产品评论
	 * @param productid
	 * @return
	 */
	public List<MemberComment> getProductComment(String productid); 

}
