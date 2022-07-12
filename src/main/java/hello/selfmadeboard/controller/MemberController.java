package hello.selfmadeboard.controller;


import hello.selfmadeboard.controller.Form.LoginForm;
import hello.selfmadeboard.controller.Form.MemberForm;
import hello.selfmadeboard.domain.Member;
import hello.selfmadeboard.service.MemberService;
import hello.selfmadeboard.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final SessionManager sessionManager;

    @GetMapping("/member")
    public String MemberForm(@ModelAttribute("memberForm") MemberForm memberForm) {
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
    public String login(@Validated @ModelAttribute LoginForm form, BindingResult bindingResult,HttpServletRequest request){

        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Member loginMember = memberService.login(form.getLoginId(), form.getPassword());

        if(loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        return "redirect:/";

    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";

    }

}
