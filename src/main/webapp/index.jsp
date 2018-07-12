<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<script type="text/javascript" src="http://www.haozf.com/d1cm_redis/login_api"></script>
</head>
<body>
<!-- <iframe src="http://www.fuxing.com:8080/"></iframe> -->
</body>
<script type="text/javascript">

console.log(getCookie("d_token"));
try{
	//setCookie("d_token",userCookie.d_token,30);	
}catch(e) {
console.log(e);
}


function getCookie(c_name){
	if (document.cookie.length>0){
		var c_start=document.cookie.indexOf(c_name + "=");
		if (c_start!=-1){
			c_start=c_start + c_name.length+1; 
			c_end=document.cookie.indexOf(";",c_start);
			if (c_end==-1) c_end=document.cookie.length;
			return unescape(document.cookie.substring(c_start,c_end));
		} 
	}
	return "";
}

function setCookie(name,value,expiredays){
	var exp = new Date();
	exp.setTime(exp.getTime() + expiredays*24*60*60*1000);
	document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString()+";path=/";
}

</script>
</html>