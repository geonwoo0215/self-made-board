package hello.selfmadeboard.controller;


import hello.selfmadeboard.controller.Form.LoginForm;
import hello.selfmadeboard.controller.Form.MemberForm;
import hello.selfmadeboard.domain.Member;
import hello.selfmadeboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member")
    public String MemberForm(@ModelAttribute("memberDto") MemberForm memberForm) {
        return "member/memberForm";
    }

    @PostMapping("/member")
    public String MemberForm(@Validated @ModelAttribute MemberForm memberForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "member/memberForm";
        }
        memberService.save(memberForm);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm form, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Member loginMember = memberService.login(form.getLoginId(), form.getPassword());

        if(loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        return "redirect:/";

    }


}
