package jpabook.jpashop.service;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 // 원래는 delivery repository 해서 저장해줘야하는데...
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문상품 생성 // jpa 값을 셋팅해주고 넣어줘야 하는데..
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 생성자를 통해서 생성하는 것을 막아야 한다.
        // 그러기 위해서는 클래스 위에
        // @NoArgdConstructor(access = AccessLevel.PROTECTED 를해준다.
        // OrderItem orderItem1 = new OrderItem()

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장 // 여기 하나만 저장하고 있다. 왜?
        // order에 있는 cascade 옵션때문에 그렇다.
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        order.cancel();
    }

    //검색
}