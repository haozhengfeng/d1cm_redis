package com.d1cm.redis;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.d1cm.redis.member.model.Member;
import com.d1cm.redis.member.model.MemberToken;
import com.d1cm.redis.member.service.IMemberService;

@Controller
public class IndexController {
	
	@Autowired
	IMemberService memberService;

	@RequestMapping(value = { "", "index" })
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {

		MemberToken memberToken = null;
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if("d_token".equals(cookie.getName())){
				memberToken = memberService.checkLogin(cookie.getValue());
			}
		}
		
		if(memberToken == null){
			Member m = memberService.checkMember("fuxing123", "a123456");
			if (m != null && m.getM_ID() != null) {
				String token = memberService.generateToken();
				memberService.updateToken(m.getM_ID(), token);
				memberService.updateMember(m);
				
				Cookie cookie = new Cookie("d_token", token);
				cookie.setMaxAge(1 * 60);// 设置为30min
				cookie.setDomain(".haozf.com");
				cookie.setPath("/");
				System.out.println("已添加===============");
				response.addCookie(cookie);
			}
		}
		
		return "index";
	}
	
	
	/**
	 * 
	 */
	@RequestMapping("/login_api")
	public void loginApi(HttpServletRequest request, HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			Cookie[] cookies = request.getCookies();// 这样便可以获取一个cookie数组
			if (null == cookies) {
				System.out.println("没有cookie=========");
			} else {
				List<String> ck = new ArrayList<String>();
				
				for (Cookie cookie : cookies) {
					ck.add(cookie.getName()+":'"+cookie.getValue()+"'");
					//System.out.println("name:" + cookie.getName() + ",value:" + cookie.getValue());
				}
				String str = StringUtils.collectionToDelimitedString(ck, ","); 
				out.println("var userCookie={"+str+"};");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 读取所有cookie 注意二、从客户端读取Cookie时，包括maxAge在内的其他属性都是不可读的，也不会被提交。
	 * 浏览器提交Cookie时只会提交name与value属性。maxAge属性只被浏览器用来判断Cookie是否过期
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/showCookies")
	public void showCookies(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();// 这样便可以获取一个cookie数组
		if (null == cookies) {
		} else {
			for (Cookie cookie : cookies) {
				System.out.println("name:" + cookie.getName() + ",value:" + cookie.getValue());
			}
		}

	}

	/**
	 * 添加cookie
	 * 
	 * @param response
	 * @param name
	 * @param value
	 */
	@RequestMapping("/addCookie")
	public void addCookie(HttpServletResponse response, String name, String value) {
		Cookie cookie = new Cookie(name.trim(), value.trim());
		cookie.setMaxAge(30 * 60);// 设置为30min
		cookie.setPath("/");
		System.out.println("已添加===============");
		response.addCookie(cookie);
	}

	/**
	 * 修改cookie
	 * 
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 *            注意一、修改、删除Cookie时，新建的Cookie除value、maxAge之外的所有属性，例如name、path、
	 *            domain等，都要与原Cookie完全一样。否则，浏览器将视为两个不同的Cookie不予覆盖，导致修改、删除失败。
	 */
	@RequestMapping("/editCookie")
	public void editCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
		Cookie[] cookies = request.getCookies();
		if (null == cookies) {
			System.out.println("没有cookie==============");
		} else {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					System.out.println("原值为:" + cookie.getValue());
					cookie.setValue(value);
					cookie.setPath("/");
					cookie.setMaxAge(30 * 60);// 设置为30min
					System.out.println("被修改的cookie名字为:" + cookie.getName() + ",新值为:" + cookie.getValue());
					response.addCookie(cookie);
					break;
				}
			}
		}

	}

	/**
	 * 删除cookie
	 * 
	 * @param request
	 * @param response
	 * @param name
	 */
	@RequestMapping("/delCookie")
	public void delCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		Cookie[] cookies = request.getCookies();
		if (null == cookies) {
			System.out.println("没有cookie==============");
		} else {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					cookie.setValue(null);
					cookie.setMaxAge(0);// 立即销毁cookie
					cookie.setPath("/");
					System.out.println("被删除的cookie名字为:" + cookie.getName());
					response.addCookie(cookie);
					break;
				}
			}
		}
	}

	/**
	 * 根据名字获取cookie
	 * 
	 * @param request
	 * @param name
	 *            cookie名字
	 * @return
	 */
	public Cookie getCookieByName(HttpServletRequest request,String name){  
    Map<String,Cookie> cookieMap = ReadCookieMap(request);  
    if(cookieMap.containsKey(name)){  
        Cookie cookie = (Cookie)cookieMap.get(name);  
        return cookie;  
    }else{  
        return null;  
    }    
}
	/**
	 * 将cookie封装到Map里面
	 * 
	 * @param request
	 * @return
	 */
	private Map<String,Cookie> ReadCookieMap(HttpServletRequest request){    
    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();  
    Cookie[] cookies = request.getCookies();  
    if(null!=cookies){  
        for(Cookie cookie : cookies){  
            cookieMap.put(cookie.getName(), cookie);  
        }  
    }  
    return cookieMap;  
}}