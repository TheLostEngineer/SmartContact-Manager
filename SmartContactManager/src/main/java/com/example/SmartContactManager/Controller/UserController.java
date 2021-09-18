package com.example.SmartContactManager.Controller;

import com.example.SmartContactManager.dao.UserRepository;
import com.example.SmartContactManager.entities.Contact;
import com.example.SmartContactManager.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    public void addCommonData(Model model, Principal principal){

        String userName = principal.getName();
        System.out.println("USERNAME " + userName);
        // Get the user using username(Email)

        User user = userRepository.getUserByUserName(userName);
        System.out.println("USER " + user);

        model.addAttribute("user", user);


    }

//    Dashboard Home
    @RequestMapping("/index")
    public String dashboard(Model model, Principal principal){

        model.addAttribute("title", "Home");


        return "normal/user_dashboard";
    }

//    Add new Contact Handler
    @GetMapping("/addcontact")
    public String openContactForm(Model model){
        model.addAttribute("title", "Add Contact");
        model.addAttribute("conatct", new Contact());
        return "normal/add_contact_form";
    }

////    Processing Add Contact Form
//
//
    @PostMapping("/process-contact")
    public String processContact(@ModelAttribute Contact contact, Principal principal){

        String name = principal.getName();
        User user = this.userRepository.getUserByUserName(name);

        contact.setUser(user);

        boolean add = user.getContacts().add(contact);
        this.userRepository.save(user);

        System.out.println("Data "+ contact);

        System.out.println("Added to DataBase");

        return "normal/add_contact_form";
    }
}
