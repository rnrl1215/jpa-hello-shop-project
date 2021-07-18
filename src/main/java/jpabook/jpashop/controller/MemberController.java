package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.nio.channels.FileChannel;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        // 객체를 실어서 해당 페이지로 넘겨줌.
        // 그렇게 되면 화면에서 저 객체에 접근이 가능하다.
        model.addAttribute("memberForm",new MemberForm());
        return "members/createMemberForm";
    }

    //@Valid 를 쓰면 NotEmpty 어노테이션을 보고 벨리데이션을 해준다.
    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result){

        // 이름이 없으면 whitelabel로 넘어가는게 아니라.
        // BindingResult  오류가 담겨서 실행된다.
        // 에러가 있다면 해당 페이지로 간다.
        // result 는 에러를 찾을수 있는 메서드가 많다.
        // binding result를 끌고가서 폼에서 쓰게 해준다.
        // 에러가 발생 하더라도 form 에 내용을 들고 다시 보낸다.
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);


        memberService.join(member);

        // 첫번쨰 페이지로 넘어감.
        return "redirect:/";
    }


    @GetMapping("/members")
    public String list(Model model) {
        model.addAttribute("members", memberService.findMembers());
        return "members/memberList";
    }
}
