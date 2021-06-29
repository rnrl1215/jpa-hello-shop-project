package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {
/*

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false) // 테스트 코드이기 때문에 자동으로 롤백함
                     // 롤백을 false 로 해줘야 한다.
    public void testMember() {

        Member member = new Member();
        member.setUsername("memberA");
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());

        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        
        // 같은 트랜잭션 안이기 때문에 동일하다.
        // 영속성 때문에
        Assertions.assertThat(findMember).isEqualTo(member); //JPA 엔티티 동일성 보장

    }
     */
}
