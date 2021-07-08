package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter // 값은 변경 되면 안된다.
public class Address {
    private String city;
    private String street;
    private String zipcode;

    // 직접 못쓰도록 protected 까지 허용된다.
    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
