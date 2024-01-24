package digital.coin.predict.controller;

import digital.coin.predict.dto.UserRequestDto;
import digital.coin.predict.service.SessionService;
import digital.coin.predict.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Controller
@RequiredArgsConstructor
public class LoginController {
    final UserService userService;
    final SessionService sessionService;

    @GetMapping("/join")
    public String userJoinView(@ModelAttribute("userRequestDto") UserRequestDto userRequestDto) {
        return "join";
    }

    @PostMapping("/join")
    public String userJoin(@ModelAttribute("userRequestDto") UserRequestDto userRequestDto) {
        if (userService.userExists(userRequestDto.getName()))
            return "join";

        userService.joinUser(userRequestDto.getName());
        return "login";
    }

    @GetMapping("/login")
    public String userLoginView(@ModelAttribute("userRequestDto") UserRequestDto userRequestDto, Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String userLogin(@ModelAttribute("userRequestDto") UserRequestDto userRequestDto, HttpServletRequest request, HttpServletResponse response) {
        if (!userService.userExists(userRequestDto.getName()))
            return "login";

        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute("userName", userRequestDto.getName());
        httpSession.setMaxInactiveInterval(1800);

        Cookie cookie = new Cookie("userName", userRequestDto.getName());
        cookie.setAttribute("sessionId", httpSession.getId());
        response.addCookie(cookie);

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String userLogOut(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession(false);
        String userName = (String)httpSession.getAttribute("userName");

        if (userName != null) {
            Cookie logoutCookie = new Cookie("userName", null);
            logoutCookie.setAttribute("sessionId", null);
            logoutCookie.setMaxAge(0);

            httpSession.removeAttribute("userName");
            httpSession.invalidate();

            response.addCookie(logoutCookie);
        }

        return "redirect:/";
    }
}
