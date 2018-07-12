package com.d1cm.redis;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.d1cm.redis.member.model.Member;
import com.d1cm.redis.member.model.MemberComment;
import com.d1cm.redis.member.model.MemberToken;
import com.d1cm.redis.member.service.IMemberService;
import com.d1cm.redis.utils.ObjectMapConvert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestMember {
	@Autowired
	IMemberService memberService;

	@Resource
	RedisTemplate redisTemplate;

	@Test
	public void redis() {
		
		ValueOperations value = redisTemplate.opsForValue();
		value.set("aa", "aaa");
		
	}
	
	@Test
	public void login(){
		
		MemberToken memberToken = null;
		
		Member m = memberService.checkMember("fuxing123", "a123456");
		if (m != null && m.getM_ID() != null) {
			String token = memberService.generateToken();
			memberToken = memberService.updateToken(m.getM_ID(), token);
			//m.setLogin_token(token);
			memberService.updateMember(m);
		}
		memberService.checkLogin(memberToken.getLogin_token());
	}
	
	@Test
	public void updateToken(){
		memberService.updateToken("123", "123");
	}
	
	@Test
	public void checkLogin(){
		Member member = memberService.getMember("337842");
		MemberToken token = memberService.checkLogin("");
		try {
			System.out.println(ObjectMapConvert.objectToMap(token));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getArticleComment(){
		
		List<MemberComment> articleComments = memberService.getArticleComment("1357");
		for(MemberComment mc : articleComments){
			System.out.println("文章："+mc.getArticle_id());
			System.out.println("评论人："+mc.getAuthor_id());
			
			Member member = memberService.getMember(mc.getAuthor_id());
			System.out.println("评论人用户名："+member.getMb_name());
			System.out.println("评论人头像："+member.getMb_headphoto());
			System.out.println("评论人："+member.getMb_address());
			
			System.out.println("评论内容："+mc.getCmt_content());
			System.out.println("评论时间："+mc.getCmt_time());
			System.out.println("点赞数量："+mc.getLike_count());
		}
		
	}
	
	@Test
	public void getMemberComment(){
		
		List<MemberComment> articleComments = memberService.getMemberComment("337889");
		
		for(MemberComment mc : articleComments){
			System.out.println(mc.getCmt_content());
		}
		
	}

	@Test
	public void getMember() {

		long startTime = System.currentTimeMillis();

		// 业务代码
		Member member = memberService.getMember("337872");

		long endTime = System.currentTimeMillis();
		System.out.println("获取会员信息运行时间：" + (endTime - startTime) + "ms"); // 输出程序运行时间
		System.out.println(member.getM_UserName());
	}

	@Test
	public void testCountDownLatch() {

		try {
			int threadCount = 2000;

			// memberService.getMember("337842");

			final CountDownLatch latch = new CountDownLatch(threadCount);
			for (int i = 0; i < threadCount; i++) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Member member = memberService.getMember("337842");
							System.out.println(member.getM_UserName());
						} catch (Exception e) {
							e.printStackTrace();
						}
						latch.countDown();
					}
				}).start();
			}

			latch.await();
			System.out.println("10个线程已经完成会员信息获取");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
