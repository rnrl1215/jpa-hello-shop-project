package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // Transactional은 롤백한다.
               // 같은 id를 가지면 하나로 관리된다.
               // 그렇기 때문에 id가 같으면 객체는 동일하다고 판다.
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    //@Rollback(false) // DB에 저장이 된다.
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long savedId = memberService.join(member);

        // then
        em.flush(); // 영속성 컨테스트를 DB에 반영해준다.
        assertEquals(member, memberRepository.findOne(savedId));
    }



    @Test(expected = IllegalStateException.class)// 이걸 넣어주면  try catch 지울수 있다.
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        memberService.join(member1);

        /* 위에 @Test(expected = IllegalStateException.class) 을 넣어 줘서
        * 발생한 예외가 IllegalStateException 이면 된다는 뜻이다.
        * 그렇기 때문에 아래 try catch 는 필요가 없다.
        // when
        try {
            memberService.join(member2); // 예외 발생.
        } catch (IllegalStateException e) {
            return;
        }
        */

        // then
        fail("예외가 발생해야 한다.");
    }
}