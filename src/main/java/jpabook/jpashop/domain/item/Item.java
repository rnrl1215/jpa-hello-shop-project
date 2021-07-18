package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

// 전략을 잡아야한다.
// JOINED 은 정규화 테이블
// SINGLE_TABLE 하나의 테이블에 다 넣는방식
// TABLE_PERCLASS 는 book movie album 3개의 테이블만 나오는 전략
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // album, book 을 상속해서 만들었기 때문에 관계를 지정

// type에 따라서 무엇을 해줄것인지.
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;


    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items") // 맵핑해주면 끝.
    private List<Category> categories = new ArrayList<>();


    //==비즈니스 로직==//

    // 데이터를 가지고 있는 쪽에 메서드를 넣는것이
    // 응집력을 높인다.

    /**
     * stock 증가
     */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
