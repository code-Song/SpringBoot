package com.hello.hellospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.hello.hellospring.domain.Member;
import com.hello.hellospring.service.MemberService;

@Controller
public class MemberController {
	private final MemberService memberService;
	
	//MemberService는 인스턴스를 생성할 때 MemberRepository가 필요함.
	//스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다.
	//객체 의존관계를 외부에서 넣어주는 것을 DI(Dependency Injection)이라 한다.
//	@Autowired //실무에서 정형화된 경우 컴포넌트 스캔을 사용. 상황에 따라 구현클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping(value = "/members/new")
	public String createForm() {
		return "members/createMemberForm";
	}
	
	@PostMapping(value = "/members/new")
	public String create(MemberForm form) {
		Member member = new Member();
		member.setName(form.getName());
		
		memberService.join(member);
		
		return "redirect:/";
	}
	
	@GetMapping(value = "/members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		return "members/memberList";
	}
}
