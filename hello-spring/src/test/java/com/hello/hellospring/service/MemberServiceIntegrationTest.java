package com.hello.hellospring.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.hello.hellospring.domain.Member;
import com.hello.hellospring.repository.MemberRepository;
import com.hello.hellospring.repository.MemoryMemberRepository;

@SpringBootTest
@Transactional //Test시작 전에 Transaction을 걸고 끝나면 rollback을 해버림 
public class MemberServiceIntegrationTest {
	
	//테스트는 제일 끝단이라 그냥 간단하게 @Autowired로 해보자
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	
	//원래는 객체를 내부에서 만들어서 넣어줬으나 이제 Spring Bean을 이용
//	@BeforeEach
//	public void beforeEach() {
//		memberRepository = new MemoryMemberRepository();
//		memberService = new MemberService(memberRepository); //DI하게 MemberRepository 생성
//	}
	
	//여러번 테스트 할 수 있어야되는데 하나 넣으면 중복된 회원이라서 두번 실행못함
	//Transaction을 이용하여 테스트가 끝난 후 알아서 rollback을 시킴 => @Transactional
	@Test
	public void 회원가입() throws Exception{
		//given
		Member member = new Member();
		member.setName("Song");
		
		//when
		Long saveId = memberService.join(member);
		
		//then
		Member findMember = memberRepository.findById(saveId).get();
		assertEquals(member.getName(), findMember.getName());
	}
	
	@Test
	public void 중복_회원_예외() throws Exception{
		//given
		Member member1 = new Member();
		member1.setName("spring");
		
		Member member2 = new Member();
		member2.setName("spring");
		
		//when
		memberService.join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class, ()->memberService.join(member2));
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
	}
}















