package hello.hellospring.repository;

import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.hello.hellospring.domain.Member;
import com.hello.hellospring.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.*; //assertj의 api를 import

public class MemoryMemberRepositoryTest {
	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	//각 테스트가 종료될 때 마다 이 기능을 실행(DB에서 데이터 삭제)
	//테스트는 각각 독립적으로 실행되어야 한다. 테스트 순서에 의존관계가 있는 것은 좋은 테스트가 아니다.
	@AfterEach
	public void afterEach() {
		repository.clearStore();
	}
	
	@Test
	public void save() {
		//given
		Member member = new Member();
		member.setName("spring");
		
		//when
		repository.save(member);
		
		//then
		Member result = repository.findById(member.getId()).get(); //반환된 Optional객체에서 member객체를 반환
		assertThat(member).isEqualTo(result); //새로운 member와 sequence가 같은 기존의 member를 비교
	}
	
	@Test
	public void findByName() {
		//given
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		//when
		Member result = repository.findByName("spring1").get();
		
		//then
		assertThat(result).isEqualTo(member1);
	}
	
	@Test
	public void findAll() {
		//given
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		//when
		List<Member> result = repository.findAll();
		
		//then
		assertThat(result.size()).isEqualTo(2);
	}
}















