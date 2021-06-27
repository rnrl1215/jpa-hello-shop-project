package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext // 스프링이 자동으로 주입을 해준다.
    private EntityManager en;

    public Long save(Member member) {
        en.persist(member);
        return member.getId();
    }

    public Member find(Long id) {
        return en.find(Member.class, id);
    }
}
