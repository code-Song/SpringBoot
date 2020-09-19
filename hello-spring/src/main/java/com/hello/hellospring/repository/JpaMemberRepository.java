package com.hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.hello.hellospring.domain.Member;

public class JpaMemberRepository implements MemberRepository{
	
	//JPA라이브러리를 추가하면 스프링부트가 알아서 EntityManager를 만들어준다.
	private final EntityManager em;
	
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Member save(Member member) {
		em.persist(member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		Member member = em.find(Member.class, id); //조회할 타입과 식별자를 넣어준다.
		return Optional.ofNullable(member);
	}

	//pk기반이면 간단하지만 pk기반이 아닌 것들은 JPQL을 작성해준다.
	@Override
	public Optional<Member> findByName(String name) {
		List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
		  .setParameter("name", name)
		  .getResultList();
		
		return result.stream().findAny();
	}

	@Override
	public List<Member> findAll() {
		//JPQL : Talbe 대상이 아닌 객체를 대상으로 Query를 날림
		return em.createQuery("select m from Member m", Member.class)
			     .getResultList();
	}

}
