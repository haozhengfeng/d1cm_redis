package com.d1cm.redis.member.model;

public class MemberComment {

	private String id;
	private String m_id;
	private String article_id;
	private String author_id;
	private String cmt_uhead;
	private String cmt_time;
	private String cmt_content;
	private String floor_index;
	private String like_state;
	private String like_count;
	private String save_state;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getArticle_id() {
		return article_id;
	}

	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}

	public String getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(String author_id) {
		this.author_id = author_id;
	}

	public String getCmt_uhead() {
		return cmt_uhead;
	}

	public void setCmt_uhead(String cmt_uhead) {
		this.cmt_uhead = cmt_uhead;
	}

	public String getCmt_time() {
		return cmt_time;
	}

	public void setCmt_time(String cmt_time) {
		this.cmt_time = cmt_time;
	}

	public String getCmt_content() {
		return cmt_content;
	}

	public void setCmt_content(String cmt_content) {
		this.cmt_content = cmt_content;
	}

	public String getFloor_index() {
		return floor_index;
	}

	public void setFloor_index(String floor_index) {
		this.floor_index = floor_index;
	}

	public String getLike_state() {
		return like_state;
	}

	public void setLike_state(String like_state) {
		this.like_state = like_state;
	}

	public String getLike_count() {
		return like_count;
	}

	public void setLike_count(String like_count) {
		this.like_count = like_count;
	}

	public String getSave_state() {
		return save_state;
	}

	public void setSave_state(String save_state) {
		this.save_state = save_state;
	}

}
