package projcet.familystory.Controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import projcet.familystory.domain.User;
import projcet.familystory.form.LoginForm;
import projcet.familystory.service.UserService;
import projcet.familystory.session.SessionConst;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @RequestMapping("/")
    public String home(Model model) {
        log.info("home controller");
        model.addAttribute("loginForm", new LoginForm());
        return "home";
    }



//    @GetMapping("/users/login")
//    public String homeLogin(HttpServletRequest request, Model model) {
//
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            return "home";
//        }
//
//        User loginUser = (User)session.getAttribute(SessionConst.LOGIN_USER);
//
//        if (loginUser == null) {
//            return "home";
//        }
//
//        model.addAttribute("loginUser", loginUser);
//        return "loginHome";
//    }
//


    @GetMapping("/users/login")
    //세션에 들어있으면 그 내용을 User user객체에 넣어줌. 원래는 바로 위의 코드야
    public String homeLogin(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false)User loginUser,  Model model) {

       if (loginUser == null) {
            return "home";
        }

        model.addAttribute("loginUser", loginUser);
        return "loginHome";
    }


//    @GetMapping("/users/login")
//    //로그인 안한 사용자도 들어와야하니까 required false
//    public String homeLogin(@CookieValue(name = "userId", required = false) Long userUid, Model model) {
//
//        if (userUid == null) {
//            return "home";
//        }
//
//
//        User loginUser = userService.findByUid(userUid);
////        Optional<User> loginUser = userService.findByUid(userUid);
//        if (loginUser == null) {
//            return "home";
//        }
//
//        model.addAttribute("loginUser", loginUser);
//        return "loginHome";
//    }
}
