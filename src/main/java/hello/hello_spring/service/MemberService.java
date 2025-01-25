package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//비즈니스에 의존적으로 설계를 한다
//테스트 자동 설계 단축키 : cmd + shift + T
//어노테이션이 없다면 스프링이 인식을 할 수 없다(순수 자바 코드이기 떄문에)

@Service
@Transactional // 트랜젝션이란...?
public class MemberService {

    //테스트 코드의 DB 주소가 다른 것을 대비해 생성자를 내부에 만든다.
    private final MemberRepository memberRepository;

    //멤버서비스는 레포지토리가 필요하기때문에 이 생성자를 컨테이너에 같이 추가해준다.
    //(MemoryMemberRepository)를 가지고오겠지...
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     **/
    public Long join(Member member) {


        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);

        //직접 바로 꺼내는 것을 권장하지 않는다.
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // 같은 이름이 있는 중복 회원을 받지 않는다.
        // Optional을 바로 반환하는 것은 좋지 않기 떄문에 아래 이런 스트림으로 정리할 수 있다.
        // Optional<Member> result = memberRepository.findByName(member.getName());
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
