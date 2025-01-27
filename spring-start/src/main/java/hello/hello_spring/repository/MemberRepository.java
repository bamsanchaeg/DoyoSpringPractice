package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import java.util.List;
import java.util.Optional;

//단순히 넣었다 뺐다 하는 이름(단순히 기계적인 이름 선택, 단순히 데이터를 넣었다 빼는 역할)
public interface MemberRepository {

    Member save(Member member);

    //Optional이란? : 탐색을 할때 Null을 반환하는 대신 optional로 반환, 자바 8에 들어가있는 기능
    Optional<Member> findById(Long id);

    Optional<Member> findByName(String name);

    List<Member> findAll();

}
