package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository //자동으로 스프링 빈에 등록
@RequiredArgsConstructor  // 롬복을 적용하면 생성자 주입 없어도 된다.
public class MemberRepository {



    //@Autowired로는 EntityManger 가 주입이 원래 안되지만 스프링부트가 지원해준다.


    // 아래를 주석 처리한 이유는 @RequiredArgsConstructor 가 있기 때문이다.
    // @RequiredArgsConstructor 는 final 로된 것을 자동으로 주입해준다.

    //@PersistenceContext //해당 어노테이션이 있으면 자동으로 스프링이
    // EntityManager를 주입해준다.
    private final EntityManager em;

    public void save(Member  member) {
        // member 가 저장됨
        // @GeneratedValue 전략에서는  persist 를 한다고 insert 문이 나가지 않는다.
        // DB 마다 다르긴하다.
        em.persist(member);
    }

    public Member findOne(Long id) {
        // id로 멤버를 찾음.
        // 1. 타입, 2 pk key
        return em.find(Member.class, id);
    }

    public List<Member> findALl() {
        // jpql, 반환타입.
        // getResultList 해당 조회된 데이터를 list로 만들어 준다.
        // jpql 은 sql 과 다르다.
        // from의 대상이 다르다: spl 은 테이블이 대상 , jpql 은 객체가 대상
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
