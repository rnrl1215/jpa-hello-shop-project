package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    // model 데이터를 실어서 뷰에 넘길수 있다.
    public String hello(Model model) {
        // data에 hello 를 넘긴다.
        model.addAttribute("data", "hello2");

        // 뷰를 넘긴다. html을 호출함.
        return "hello";
    }


}
