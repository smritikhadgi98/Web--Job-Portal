package com.example.jobs_portal.Controller;


import com.example.jobs_portal.entity.*;
import com.example.jobs_portal.pojo.ApplicationPojo;
import com.example.jobs_portal.pojo.JobsPojo;
import com.example.jobs_portal.pojo.TeamsPojo;
import com.example.jobs_portal.pojo.UserPojo;
import com.example.jobs_portal.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private  final UserService userService;
    private  final TeamServices teamServices;
    private  final ApplicationService applicationService;
    private  final JobsService jobsService;
    private  final ContactServices contactServices;

//
    @GetMapping("/dashboard")
    public String getHomepage(Model model) {
        Long application=applicationService.countRows();
        model.addAttribute("app",application);

        Long users=userService.countRows();
        model.addAttribute("userss",users);

        Long jobs=jobsService.countRows();
        model.addAttribute("jobs",jobs);

        Long teams=teamServices.countRows();
        model.addAttribute("teams",teams);

        Long dateTime= userService.countRowsLastMonth();
        model.addAttribute("date",dateTime);

        Long countTeam = teamServices.countRowsLastMonth();
        model.addAttribute("countTeam",countTeam);

        Long countJobs= jobsService.countRowsLastMonth();
        model.addAttribute("countJobs",countJobs);

        Long countApp= applicationService.countRowsLastMonth();
        model.addAttribute("countApp",countApp);

        Long countContact= contactServices.countRowsLastMonth();
        model.addAttribute("countCont",countContact);

        return "Admin/dashboard";
    }

//    @GetMapping("/profile")
//    public String getSetting(Model model) {
//        return "Admin/profile";
//    }


    @GetMapping("/jobslist")
    public String getJobsList(Model model) {
        List<Jobs> jobs = jobsService.fetchAll();
        model.addAttribute("jobslist", jobs);
        return "Admin/JobsList";
    }


    @GetMapping("/jobscreate")
    public String createUser(Model model) {
        model.addAttribute("jobs", new JobsPojo());
        return "Admin/Addjobss";
    }

    @PostMapping("/saveJobs")
    public String saveJobs(@Valid JobsPojo jobsPojo, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        Map<String, String> requestError = validateRequest(bindingResult);
        if (requestError != null) {
            redirectAttributes.addFlashAttribute("requestError", requestError);
            return "redirect:/jobs/create";
        }

        jobsService.save(jobsPojo);
        redirectAttributes.addFlashAttribute("successMsg", "jobs saved successfully");
        return "redirect:/jobs/list";
    }


    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        Jobs jobs = jobsService.fetchById(id);
        model.addAttribute("jobs", new JobsPojo(jobs));
        return "Admin/Addjobss";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        jobsService.deleteById(id);
        return "redirect:/jobs/list";
    }




    @GetMapping("/alluser")
    public String getbook(Model model) {
       List<User> users = userService.fetchAll();
       model.addAttribute("userlist", users.stream().map(user ->
               User.builder()
                       .id(user.getId())
                       .imageBase64(getImageBase64(user.getImage()))
                       .email(user.getEmail())
                       .fullname(user.getFullname())
                       .mobileNo(user.getMobileNo())
                       .build()

       ));


        return "Admin/allUser";
    }


    @GetMapping("/seeteams")
    public String viewUser(Model model) {
            List<Teams> teams = teamServices.fetchAll();
            model.addAttribute("teamslist", teams);
            return "Admin/OurTeams";
        }


    @GetMapping("/createteam")
    public String createTeams(Model model) {
        model.addAttribute("teams", new TeamsPojo());
        return "Admin/AddTeams";
    }


    @PostMapping("/saveTeam")
    public String saveTeams(@Valid TeamsPojo teamsPojo,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        Map<String, String> requestError = validateRequest(bindingResult);
        if (requestError != null) {
            redirectAttributes.addFlashAttribute("requestError", requestError);
            return "redirect:/admin/createteam";
        }

        teamServices.save(teamsPojo);
        redirectAttributes.addFlashAttribute("successMsg", "teams saved successfully");
        return "redirect:/admin/seeteams";
    }


    @GetMapping("/editmem/{id}")
    public String editMembers(@PathVariable("id") Integer id, Model model) {
        Teams teams = teamServices.fetchById(id);
        model.addAttribute("teams", new TeamsPojo(teams));
        return "Admin/AddTeams";
    }

    @GetMapping("/deletemem/{id}")
    public String deleteMembers(@PathVariable("id") Integer id) {
        teamServices.deleteById(id);
        return "redirect:/admin/seeteams";
    }




    @GetMapping("/application")
    public String getApplication(Model model) {
        List<Application> applications = applicationService.fetchAll();
        model.addAttribute("applicationlist",applications);

        return "Admin/ViewApplication";
    }




    @GetMapping("/deleteApp/{id}")
    public String deleteApplication(@PathVariable("id") Integer id) {
        applicationService.deleteById(id);
        return "redirect:/admin/application";
    }







    @GetMapping("/profile")
    public String getUserProfile( Integer id, Model model, Principal principal ) {
//        User user = userService.fetchById(id);
        model.addAttribute("userdata",userService.findByEmail(principal.getName()));
//        model.addAttribute("userdata", user);
        model.addAttribute("user", new UserPojo());
        return "Admin/profile";
    }


    @PostMapping("/updateuser")
    public String updateUser(Model model,@Valid UserPojo userPojo,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes
    ) throws IOException {

        Map<String, String> requestError = validateRequest(bindingResult);
        if (requestError != null) {
//            model.addAttribute("message","invalid");
            redirectAttributes.addFlashAttribute("requestError", requestError);
            return "redirect:/login";
        }
        userService.save(userPojo);
        redirectAttributes.addFlashAttribute("successMsg", "User saved successfully");
        return "redirect:/admin/profile";


    }

    @GetMapping("/contactlist")
    public String getContact(Model model) {
        List<Contact> contacts = contactServices.fetchAll();
        model.addAttribute("contactList",contacts);
        return "Admin/ViewContact";
    }


    @GetMapping("/deleteCont/{id}")
    public String deleteContact(@PathVariable("id") Integer id) {
        contactServices.deleteById(id);
        return "redirect:/admin/application";
    }






    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/Jobs_Image_Server/";
        File file = new File(filePath + fileName);
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String base64 = Base64.getEncoder().encodeToString(bytes);
        return base64;
    }


    public String getFileName(String fileName) {
        String filePath = System.getProperty("user.dir") + "/jobs_application_files/";
        File file = new File(filePath + fileName);
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String base64 = Base64.getEncoder().encodeToString(bytes);
        return base64;
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


}
