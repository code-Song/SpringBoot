package com.hello.hellospring;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hello.hellospring.repository.JdbcMemberRepository;
import com.hello.hellospring.repository.JdbcTemplateMemberRepository;
import com.hello.hellospring.repository.JpaMemberRepository;
import com.hello.hellospring.repository.MemberRepository;
import com.hello.hellospring.repository.MemoryMemberRepository;
import com.hello.hellospring.service.MemberService;

@Configuration
public class SpringConfig {
	//dataSource는 데이터베이스 커넥션을 획득할 때 사용하는 객체다.스프링 부트는 데이터베이스 커넥션 정보를 바탕으로 DataSource를 생성하고 스프링 빈으로 만들어둔다. 그래서 DI를 받을 수 있다.
//	@Autowired //autowired로 연결해도 되고 생성자로 DI 해줘도 됨
//	private final DataSource dataSource;
//	
//	public SpringConfig(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}
	
	private EntityManager em;
	
	@Autowired
	public SpringConfig(EntityManager em) {
		this.em = em;
	}
	
	//리턴되는 객체를 IOC Bean에 등록
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository() {
//		return new MemoryMemberRepository();
//		return new JdbcMemberRepository(dataSource);
//		return new JdbcTemplateMemberRepository(dataSource);
		return new JpaMemberRepository(em);
	}
}
