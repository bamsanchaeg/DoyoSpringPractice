package hello.hello_spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// 테스트 코드는 과감하게 한글로 적어도 된다(영어권 사람들과 일하는게 아니라면)
// 빌드에는 테스트 코드가 포함되지 않는다.
// given when then 의 순서를 지키면서 작성하면 협업할때 도움이 된다.

//static import 단축키 : option + enter
//assertThat은 assertJ에 포함되어 있는것이다(Junit 이 아닌..)
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //테스트를 실행하기전에 각 객체를 생성해서 넣어준다. 같은 memberRepository를 사용한다.
    //DI(dependency injection)
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    //Ctr + R : 이전에 실행시킨것 다시 실행
    @AfterEach
    public void afterEach() {
        memberRepository.clearSore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findedMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findedMember.getName());
    }

    @Test
    void 중복_회원_예외() {

        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //뒤의 로직을 던지면 앞의 예외가 터져야한다
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
/*        try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/

        //then

    }

    @Test
    void findOne() {
    }
}