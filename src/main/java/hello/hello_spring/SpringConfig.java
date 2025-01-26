package hello.hello_spring;


import hello.hello_spring.aop.TimeTraceAop;
import hello.hello_spring.repository.JpaMemberRepository;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//DB를 바꾸게 될때 코드 변경없이 바꾸게 될 일이 생기면 이러한 자바 코드로 빈을 등록하는 방법이 유용하다.

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private final EntityManager em;
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }

}


/*

    private EntityManager em;
    private final MemberRepository memberRepository;

    @Autowired //생성자가 하나인 경우 생략가능
    public SpringConfig(MemberRepository memberRepository) {
        //this.em = em;
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        //memberRepository 엮어주기
        return new MemberService(memberRepository);
    }

    @Bean
    public MemberRepository memberRepository(){
       return new JpaMemberRepository(em);
    }*/
