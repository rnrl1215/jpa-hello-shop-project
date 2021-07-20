package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
public class ItemUpdateTest {

    @Autowired
    EntityManager em;


    @Test
    public void updateTest() throws Exception {
        Book book = em.find(Book.class, 1L);

        // TX
        // 이름을 바꾸고
        book.setName("asdfasdf");

        // TX commit 이 되면 변경건에서 JPA가 찾아서
        // update query를 자동으로 생성 해서 업데이트 한다.
        // 이게 변경 감지다.
        // dirty checking 이라고 한다.
    }

}
