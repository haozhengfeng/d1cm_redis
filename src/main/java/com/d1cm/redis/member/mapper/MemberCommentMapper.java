package com.d1cm.redis.member.mapper;

import java.util.List;

import com.d1cm.redis.member.model.MemberComment;

public interface MemberCommentMapper {
	public List<MemberComment> getMemberCommentByArticle(String articleid);

	public List<MemberComment> getMemberCommentByMember(String mid);
}
