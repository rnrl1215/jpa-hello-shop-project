package jpabook.jpashop.domain;


import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany // item 과 다대다 관계  중간 테이블 맵핑 실무에서는 거의 안쓰인다.
    @JoinTable(name = "category_item",
               joinColumns = @JoinColumn(name = "category_id"),
               inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    // 자기 자신 맵핑
    @ManyToOne(fetch = LAZY) // 부모 정의
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private  List<Category> child = new ArrayList<>();

    //==연관관계메서드 양방향일때 쓰면좋다==//
    //child 를 집어넣으면 부모와 자식 다 들어가야한다.
    public void addChildCategory(Category child)  {
        this.child.add(child);
        child.setParent(this);
    }

}
