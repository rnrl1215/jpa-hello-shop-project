package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    // 좋은 방법은 아니다.
    /*
    @Transactional
    public void updateItem(Long itemId, Book param) {
        // 1 영속성 객체를 찾는다.
        Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice(param.getPrice());
        findItem.setName(param.getName());
        findItem.setStockQuantity(param.getStockQuantity());


        // 호출 할 필요가 없다.
        //itemRepository.save(findItem);
        // 영속성을 가져 왔기 때문에 @Transactional 이 커밋을 한다.
        // 그러면 플러쉬를 날린다.
        // 변경된 애를 다 찾는다.
        // 바뀐 값을 DB에 업데이트를 친다.
    }
    */

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {

        // 1 영속성 객체를 찾는다.
        // set 을 해주면 jpa가 관리 하기 때문에 커밋한다.
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }


    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId)
    {
        return itemRepository.findOne(itemId);
    }

}
