package hello.hello_spring.controller;


import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


//스프링 통이 생기면서 이 annotation이 생기면서 이 통을 관리한다(스프링 빈이 관리된다는 의미로 보면 된다)
//스프링 빈을 등록하는 2가지 방법
// 1. 컴포넌트 스캔과 자동 의존관계 설정(각 어노테이션에 컴포넌트가 같이 들어가있다) Autowired는 각 계층을 연결시켜주는 어노테이션이라고 이해하자
// 2. 자바 코드로 직접 스프링 빈 등록하기
// application 패키지의 하위만 가능
// 스프링 빈을 등록할때는 기본으로 싱글톤으로 등록하여 하나만 공유한다.
@Controller
public class MemberController {

    //필드주입은 별로 좋은 선택이 아님, setter의 단점은 누군가가 호출했을때 public으로 열려있어야한다.
    private final MemberService memberService;

    //결론은 생성자 주입을 쓰자...
    //생성자에 Autowired 어노테이션이 붙어있으면 스프링이 멤버서비스를 가져와 memberService 컨테이너에 붙여준다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //Http의 get방식과 post방식에 대해서 정확히 짚고 넘어가자


    //데이터를 조회할때 쓰임(불러올때)
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    //데이터를 전달할때 쓰임(데이터를 등록할떄 주로 쓰임)
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/";

    }


    @GetMapping("/member")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
