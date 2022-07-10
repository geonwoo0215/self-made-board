package hello.selfmadeboard.controller;

import hello.selfmadeboard.domain.Member;
import hello.selfmadeboard.repository.MemberRepository;
import hello.selfmadeboard.session.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginMemberController {


    @GetMapping("/loginHome")
    public String homeLogin(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model){

        if(loginMember==null) {
            return "board/boardList";
        }
        model.addAttribute("member", loginMember);
        return "login/loginHome";
    }


}
