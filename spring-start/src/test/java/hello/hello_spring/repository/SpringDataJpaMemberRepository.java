package hello.hello_spring.repository;


import hello.hello_spring.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

//인터페이스가 인터페이스를 받을땐 extends
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,
        Long>, MemberRepository {

    //select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
