package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 자동으로 서비스 등록됨
@Transactional(readOnly = true)  // jpa 수행시 필수. spring 이 제공하는 Annotation을 쓰는게 좋다.
                                  // readOnly 모드로 읽을때 최적화 시킴

@RequiredArgsConstructor  // 롬복을 적용하면 생성자 주입 없어도 된다.
                          // final 로 된 필드만 주입함.
public class MemberService {


    private final MemberRepository memberRepository;

    //@Autowired 가 없어도 생성자가 하나이기 때문에 알아서 주입해준다.
    /* RequiredArgsConstructor 사용으로 생성자가 없어도 된다.
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    */

    /**
     *  회원가입
     */
    @Transactional // 여기서는 Transactional 만 사용 readOnly가 true면 저장이 안된다.
    public Long join(Member member) {
        // 중복 회원 검증 로직
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    void validateDuplicateMember(Member member) {
        // Exception
        // 저장시 동시에 2개가 들어오면 둘다 저장 될수가 있기 때문에
        // 방어코드로 id를 unique 로 설정 해준다.
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    //@Transactional(readOnly = true) // 조회시 성능최적화
    public List<Member> findMembers() {
        return memberRepository.findALl();
    }

    // 회원 단건 조회
    //@Transactional(readOnly = true) // 위에서 이미 적용됨
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
