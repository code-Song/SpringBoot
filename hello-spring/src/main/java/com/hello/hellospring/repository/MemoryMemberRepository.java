package com.hello.hellospring.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.hello.hellospring.domain.Member;

//@Repository
public class MemoryMemberRepository implements MemberRepository{
	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L;
	
	@Override
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}
	@Override
	public Optional<Member> findById(Long id) {
		//만약 비어있지않으면 특정값으로 내림차순된 Optional객체를 생성
		return Optional.ofNullable(store.get(id));
	}
	@Override
	public Optional<Member> findByName(String name) {
		return store.values().stream()
				.filter(member->member.getName().equals(name)) //람다식
				.findAny(); //Optional 객체를 반환
	}
	@Override
	public List<Member> findAll() {
		return new ArrayList<>(store.values()); //Member들을 넣은 ArrayList를 반환
	}
	
	public void clearStore() {
		store.clear();
	}
}
