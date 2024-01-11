package digital.coin.predict.controller;

import digital.coin.predict.dto.UserRequestDto;
import digital.coin.predict.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class LoginController {
    final UserService userService;

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
    public String userLoginView(@ModelAttribute("userRequestDto") UserRequestDto userRequestDto) {
        return "login";
    }

    @PostMapping("/login")
    public String userLogin(@ModelAttribute("userRequestDto") UserRequestDto userRequestDto, HttpServletRequest request) {
        if (userService.userExists(userRequestDto.getName())) {
            HttpSession httpSession = request.getSession(true);
            httpSession.setAttribute("userName", userRequestDto.getName());
            httpSession.setMaxInactiveInterval(1800);

            return "redirect:/";
        }
        return "1";
    }

    @GetMapping("/logout")
    public String userLogOut() {
        return "logout";
    }

    @PostMapping("/logout")
    public String userLogOut(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        String userName = (String)httpSession.getAttribute("userName");
        System.out.println(userName);

        if (userName != null) {
            httpSession.removeAttribute("userName");
            httpSession.invalidate();
        }

        return "redirect:/";
    }
}
