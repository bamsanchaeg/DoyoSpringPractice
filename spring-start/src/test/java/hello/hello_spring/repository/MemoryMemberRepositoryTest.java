package hello.hello_spring.repository;

//static으로 들어가면 바로 쓸 수 있음
//모든 테스트는 순서가 보장되지않고, 메서드별로 따로 돌아갈 수 있게 설정되어야 한다.
//테스트가 하나 끝나면 데이터를 클리어해줘야한다.
import static org.assertj.core.api.Assertions.*;

import hello.hello_spring.domain.Member;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트가 실행되고 끝날때마다 저장소를 다 지워준다. 테스트할때 이 부분을 중요하게 생각하세요
    //서로 의존관계가 없이 설계되어야한다.
    //테스트 클래스를 작성한 뒤에 설계를 할 수도 있다. 이것을 TDD라고 한다. 테스트 먼저 작성 후 구현
    @AfterEach
    public void afterEach(){
        repository.clearSore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //shift + F6 = rename
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }
}
