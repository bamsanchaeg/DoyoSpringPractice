package hello.hello_spring;


import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//DB를 바꾸게 될때 코드 변경없이 바꾸게 될 일이 생기면 이러한 자바 코드로 빈을 등록하는 방법이 유용하다.

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        //memberRepository 엮어주기
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
