package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id") // 컬럼 이름지정
    private Long id;

    private String name;

    @Embedded
    private Address address;

    // order 테이블에 있는 member에 맵핑이 되었다는 뜻임.
    // 맵핑을 하는것이 아니라 매핑을 당하는것이다.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
