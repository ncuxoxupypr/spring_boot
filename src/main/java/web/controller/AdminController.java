package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login");
        return modelAndView;
    }

    @GetMapping("userslist")
    public String userslist(ModelMap model) {
        model.addAttribute("user", userService.findAll());
        return "admin/userslist";
    }

    @GetMapping("adduser")
    public ModelAndView addUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/adduser");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("roles", userService.findAllRole());
        return modelAndView;
    }

    @PostMapping("adduser")
    public ModelAndView addUser(User user) {
        ModelAndView modelAndView = new ModelAndView();
        userService.saveUser(user);
        modelAndView.setViewName("redirect:/admin/userslist");
        return modelAndView;
    }

    @GetMapping("edituser/{id}")
    public ModelAndView updateUser(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userService.findById(id));
        modelAndView.addObject("roles", userService.findAllRole());
        modelAndView.setViewName("admin/edituser");
        return modelAndView;
    }

    @PostMapping("edituser/{id}")
    public ModelAndView updateUser(User user) {
        ModelAndView modelAndView = new ModelAndView();
        userService.saveUser(user);
        modelAndView.setViewName("redirect:/admin/userslist");
        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        userService.deleteById(id);
        modelAndView.setViewName("redirect:/admin/userslist");
        return modelAndView;
    }
}
