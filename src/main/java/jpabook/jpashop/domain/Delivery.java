package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    // ORDINAL 숫자로 들어가게됨 절대 쓰면안됨
    // String 으로 해야 나중에 추가 되더라도 문제가 없다.
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //READY, COMP
}
