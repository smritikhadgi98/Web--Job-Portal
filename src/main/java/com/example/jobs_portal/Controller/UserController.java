package com.example.jobs_portal.Controller;


import com.example.jobs_portal.entity.Application;
import com.example.jobs_portal.entity.Jobs;
import com.example.jobs_portal.entity.Teams;
import com.example.jobs_portal.entity.User;
import com.example.jobs_portal.pojo.ApplicationPojo;
import com.example.jobs_portal.pojo.ContactPojo;
import com.example.jobs_portal.pojo.JobsPojo;
import com.example.jobs_portal.pojo.UserPojo;
import com.example.jobs_portal.service.*;
import com.example.jobs_portal.service.impl.JobsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private  final JobsService jobsService;
    private  final TeamServices teamServices;
    private  final ApplicationService applicationService;
    private  final ContactServices contactServices;
    private  final JobsServiceImpl jobsServiceimpl;


    @GetMapping("/homepage")
    public String getRegisterPage(Model model, Principal principal, Authentication authentication) {

        if (authentication!=null){
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                if (grantedAuthority.getAuthority().equals("Admin")) {
                    return "redirect:/admin/dashboard";
                }
            }
        }

//        List<Jobs> users = jobsService.fetchAll();
        List<Jobs> jobs = jobsService.getFourRandomData();
        List<Teams> team = teamServices.fetchAll();
        model.addAttribute("joblist", jobs);
        model.addAttribute("teamlist", team);
//        model.addAttribute("userdata",userService.findByEmail(principal.getName()));
        return "user/home";
    }



    @GetMapping("/create")
    public String createUser(Model model,@RequestParam(value ="error", required = false)
    String error) {
        if(error!=null){
            model.addAttribute("message","invalid");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("user", new UserPojo());
            return "user/login-register";
        }
        return "redirect:/user/homepage";
    }

    @PostMapping("/saveuser")
    public String createUser(Model model,@Valid UserPojo userPojo,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes
    ) throws IOException {

        Map<String, String> requestError = validateRequest(bindingResult);
        if (requestError != null) {
//            model.addAttribute("message","invalid");
            redirectAttributes.addFlashAttribute("requestError", requestError);
            return "redirect:/user/create";
        }
        userService.save(userPojo);
        redirectAttributes.addFlashAttribute("successMsg", "User saved successfully");
        return "redirect:/user/create";


    }


    @GetMapping("/services")
    public String getservice(Model model) {
        List<Jobs> users = jobsService.fetchAll();
        model.addAttribute("joblist", users);
        return "user/Services";
    }



    @GetMapping("/appledjobs/{id}")
    public  String getAppliedJos(@PathVariable("id") Integer id, Model model,Principal principal){
        List<Application> application= applicationService.findApplicationById(id);
        model.addAttribute("applicationData",application);
        model.addAttribute("userdata",userService.findByEmail(principal.getName()));
        return "user/AppliedJobs";
    }




//    @GetMapping("/applyjobs/{id}")
//    public String createApplication(@PathVariable("id") Integer id , Model model, Principal principal) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
//            return "redirect:/user/create";
//        }
//        Jobs jobs= jobsService.fetchById(id);
//        model.addAttribute("application", new ApplicationPojo());
//        model.addAttribute("jobs", new JobsPojo(jobs));
//        model.addAttribute("info",userService.findByEmail(principal.getName()));
//        return "user/MoreInfo";
//    }

    @GetMapping("/moreinfomation/{id}")
    public String getMoreInfo(@PathVariable("id") Integer id , Model model, Principal principal){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/user/create";
        }
        Jobs jobs= jobsService.fetchById(id);
        model.addAttribute("applyApplication", new ApplicationPojo());
        model.addAttribute("jobs", new JobsPojo(jobs));
        model.addAttribute("info",userService.findByEmail(principal.getName()));
        return "user/MoreInfo";
    }

    @PostMapping("/saveApplication")
    public String saveApplication(@Valid ApplicationPojo applicationPojo,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
        Map<String, String> requestError = validateRequest(bindingResult);
        if (requestError != null) {
            redirectAttributes.addFlashAttribute("requestError", requestError);
//            model.addAttribute("message","invalid");

            return "redirect:/user/applyjobs";
        }
        applicationService.save(applicationPojo);
        redirectAttributes.addFlashAttribute("successMsg", "User saved successfully");

        return "redirect:/user/homepage";
    }


    @GetMapping("/editApplication/{id}")
    public String editApplication(@PathVariable("id") Integer id, Model model) {
        Application application = applicationService.fetchById(id);
        model.addAttribute("applyApplication", application);
        return "user/EditAppliedJobs";
    }


    @GetMapping("/deleteApp/{id}")
    public String deleteApplicationByUser(@PathVariable("id") Integer id) {
        applicationService.deleteById(id);
        return "redirect:/user/homepage";
    }


    @GetMapping("/profile")
    public String getUserProfile(Model model, Principal principal ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/login";
        }
        model.addAttribute("userdata",userService.findByEmail(principal.getName()));
        model.addAttribute("user", new UserPojo());
        return "user/profile";
    }


    @GetMapping("/jobs")
    public String getJob(Model model, Principal principal) {
        List<Jobs> users = jobsService.fetchAll();
        model.addAttribute("joblist", users);
//        model.addAttribute("userdata",userService.findByEmail(principal.getName()));
        return "user/JobPage";
    }




    @GetMapping("/updateuser/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        User user = userService.fetchById(id);
        model.addAttribute("user", new UserPojo(user));
        return "redirect:/user/create";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(Model model,@PathVariable("id") Integer id){
        userService.deleteById(id);
        return "redirect:/user/list";
    }





    @GetMapping("/contact")
    public String getHomepage(Model model) {
        model.addAttribute("contact", new ContactPojo());
        return "user/Contact";
    }

    @PostMapping("/saveContact")
    public String saveContact(@Valid ContactPojo contactPojo){
        contactServices.save(contactPojo);
        return "redirect:/user/homepage";


    }



    public Map<String, String> validateRequest(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return null;
        }
        Map<String, String> errors = new HashMap<>();
        bindingResult.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;

    }




//    @GetMapping("/jobs/{letter}")
//    public String getEmployeesByFirstLetter(@PathVariable String letter,Model model) {
//     List<Jobs> jobs=   jobsService.findJobsByFirstLetter(letter);
//     model.addAttribute("searchjobs",jobs);
//        return "user/Search";
//    }


    @GetMapping("/search/{query}")
    public String search(@PathVariable String query, Model model) {
     List<Jobs> jobs=   jobsService.findJobsByFirstLetter(query);
     model.addAttribute("searchjobs",jobs);
        return "user/Search";
    }

    @GetMapping("description")
    public String getJobDescription() {
        return "/user/JobDetails";
    }




}
