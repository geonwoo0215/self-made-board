package hello.selfmadeboard.controller;

import hello.selfmadeboard.dto.MemberDto;
import hello.selfmadeboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/createAccountForm")
    public String createForm() {
        return "member/createAccountForm";
    }

    @PostMapping("/createAccountForm")
    public String createMember(MemberDto memberDto) {
        memberService.join(memberDto);
        return "redirect:/";
    }

}
