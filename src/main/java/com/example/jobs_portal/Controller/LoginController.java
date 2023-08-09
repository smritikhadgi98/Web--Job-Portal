package com.example.jobs_portal.Controller;

import com.example.jobs_portal.entity.User;
import com.example.jobs_portal.pojo.UserPojo;
import com.example.jobs_portal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class LoginController {
    private final UserService userService;

//    @GetMapping("/login")
//    public String showLoginPage(Model model,@RequestParam(value ="error", required = false)
//                                String error) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(error!=null){
//            model.addAttribute("message","invalid");
//        }
//        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
//            return "redirect:/user/create";
//        }
//        return "redirect:/user/homepage";
//    }
    @PostMapping("/logout")
    public String logout(Authentication authentication) {
        if (authentication.isAuthenticated()) {
            SecurityContextHolder.clearContext();
        }
        return "redirect:/user/create";
    }
}
