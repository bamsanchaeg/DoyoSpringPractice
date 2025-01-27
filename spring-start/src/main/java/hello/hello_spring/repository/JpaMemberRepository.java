package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;



public class JpaMemberRepository implements MemberRepository {


    //JPA는 Entitymanager로 모든걸 한다. JPA를 쓰려면 우선적으로 주입받아야 함,
    // 줄여서 em이라고 많이 씀
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name ", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    //객체를 대상으로 쿼리를 날리면 SQL로 번역이 되는 문법이다
    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
