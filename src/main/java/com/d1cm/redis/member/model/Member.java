package com.d1cm.redis.member.model;

import java.io.Serializable;

public class Member implements Serializable {

	//账号信息
	private String m_ID;
	private String m_Email;
	private String m_Mobile;
	private String m_UserName;
	private String m_PassWord;
	private String m_true_pass;
	private String reg_source;
	private String reg_ip;
	private String reg_time;
	private String IsState;
	private String IsCompanyAdmin;
	private String CompanyID;
	private String boundComState;
	private String IsMobileActived;
	private String IsShowData;
	private String IsExpert;
	private String last_login_time;
	private String last_login_ip;
	private String login_count;
	
	//会员信息
	private String mb_name;
	private String mb_sex;
	private String mb_birth;
	private String mb_address;
	private String mb_headphoto;
	
	
	//private String login_token;

	public String getM_ID() {
		return m_ID;
	}

	public void setM_ID(String m_ID) {
		this.m_ID = m_ID;
	}

	public String getM_Email() {
		return m_Email;
	}

	public void setM_Email(String m_Email) {
		this.m_Email = m_Email;
	}

	public String getM_Mobile() {
		return m_Mobile;
	}

	public void setM_Mobile(String m_Mobile) {
		this.m_Mobile = m_Mobile;
	}

	public String getM_UserName() {
		return m_UserName;
	}

	public void setM_UserName(String m_UserName) {
		this.m_UserName = m_UserName;
	}

	public String getM_PassWord() {
		return m_PassWord;
	}

	public void setM_PassWord(String m_PassWord) {
		this.m_PassWord = m_PassWord;
	}

	public String getM_true_pass() {
		return m_true_pass;
	}

	public void setM_true_pass(String m_true_pass) {
		this.m_true_pass = m_true_pass;
	}

	public String getReg_source() {
		return reg_source;
	}

	public void setReg_source(String reg_source) {
		this.reg_source = reg_source;
	}

	public String getReg_ip() {
		return reg_ip;
	}

	public void setReg_ip(String reg_ip) {
		this.reg_ip = reg_ip;
	}

	public String getReg_time() {
		return reg_time;
	}

	public void setReg_time(String reg_time) {
		this.reg_time = reg_time;
	}

	public String getIsState() {
		return IsState;
	}

	public void setIsState(String isState) {
		IsState = isState;
	}

	public String getIsCompanyAdmin() {
		return IsCompanyAdmin;
	}

	public void setIsCompanyAdmin(String isCompanyAdmin) {
		IsCompanyAdmin = isCompanyAdmin;
	}

	public String getCompanyID() {
		return CompanyID;
	}

	public void setCompanyID(String companyID) {
		CompanyID = companyID;
	}

	public String getBoundComState() {
		return boundComState;
	}

	public void setBoundComState(String boundComState) {
		this.boundComState = boundComState;
	}

	public String getIsMobileActived() {
		return IsMobileActived;
	}

	public void setIsMobileActived(String isMobileActived) {
		IsMobileActived = isMobileActived;
	}

	public String getIsShowData() {
		return IsShowData;
	}

	public void setIsShowData(String isShowData) {
		IsShowData = isShowData;
	}

	public String getIsExpert() {
		return IsExpert;
	}

	public void setIsExpert(String isExpert) {
		IsExpert = isExpert;
	}

	public String getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(String last_login_time) {
		this.last_login_time = last_login_time;
	}

	public String getLast_login_ip() {
		return last_login_ip;
	}

	public void setLast_login_ip(String last_login_ip) {
		this.last_login_ip = last_login_ip;
	}

	public String getLogin_count() {
		return login_count;
	}

	public void setLogin_count(String login_count) {
		this.login_count = login_count;
	}

//	public String getLogin_token() {
//		return login_token;
//	}

//	public void setLogin_token(String login_token) {
//		this.login_token = login_token;
//	}

	public String getMb_name() {
		return mb_name;
	}

	public void setMb_name(String mb_name) {
		this.mb_name = mb_name;
	}

	public String getMb_sex() {
		return mb_sex;
	}

	public void setMb_sex(String mb_sex) {
		this.mb_sex = mb_sex;
	}

	public String getMb_birth() {
		return mb_birth;
	}

	public void setMb_birth(String mb_birth) {
		this.mb_birth = mb_birth;
	}

	public String getMb_address() {
		return mb_address;
	}

	public void setMb_address(String mb_address) {
		this.mb_address = mb_address;
	}

	public String getMb_headphoto() {
		return mb_headphoto;
	}

	public void setMb_headphoto(String mb_headphoto) {
		this.mb_headphoto = mb_headphoto;
	}

}
