package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("showuser")
    public ModelAndView showUserForm(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView("/user/showuser");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("userslist")
    public String userlist(ModelMap model) {
        model.addAttribute("user", userService.findAll());
        return "user/userslist";
    }
}
