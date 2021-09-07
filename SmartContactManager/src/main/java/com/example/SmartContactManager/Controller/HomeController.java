package com.example.SmartContactManager.Controller;

import com.example.SmartContactManager.Helper.Message;
import com.example.SmartContactManager.dao.UserRepository;
import com.example.SmartContactManager.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
public class HomeController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home- SmartContact Manager");
        return "home";
    }

    @RequestMapping("/about")
    public String About(Model model) {
        model.addAttribute("title", "About- SmartContact Manager");
        return "about";
    }

    @RequestMapping("/SignUp")
    public String SignUp(Model model) {
        model.addAttribute("title", "Register- SmartContact Manager");
        model.addAttribute("user", new User());
        return "SignUp";
    }

//    Handler for Registering User

    @RequestMapping(value = "/do_register", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user,  BindingResult result, @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model, HttpSession session) {
        try {
            if (!agreement) {
                System.out.println("You have not checked the Terms and Conditions");
                throw new Exception("You have not agreed to Terms & Conditions");
            }

            if (result.hasErrors()){
                System.out.println("ERROR " + result.toString());
                model.addAttribute("user", user); // TO FILL DATA AUTOMATICALLY IN THE FORM EVEN AFTER ERROR IS FOUND
                return "SignUp";
            }

            user.setRole("ROLE_USER");
            user.setEnabled(true);
            user.setImageurl("default.png");
            user.setPassword(passwordEncoder.encode(user.getPassword()));


            System.out.println("Agreement" + agreement);
            System.out.println("USER" + user);
            this.userRepository.save(user);

            model.addAttribute("user", new User());
            session.setAttribute("message", new Message("Successfully Registered!!", "alert-success"));
            return "SignUp";

            //model.addAttribute("user", user);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("user", user);
            session.setAttribute("message", new Message("Something went wrong!!" + e.getMessage(), "alert-danger"));
            return "SignUp";
        }



    }

//    Handler for Custom Login


    @GetMapping("/signin")
    public String customLogIn(Model model){

        model.addAttribute("title","LogIn page");
        return "LogIn";
    }
}








