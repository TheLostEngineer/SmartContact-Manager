package com.example.SmartContactManager.Controller;

import com.example.SmartContactManager.dao.UserRepository;
import com.example.SmartContactManager.entities.Contact;
import com.example.SmartContactManager.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    public String processContact(@ModelAttribute Contact contact,
                                 @RequestParam("profileImage") MultipartFile file,
                                 Principal principal){

        try {

            String name = principal.getName();
            User user = this.userRepository.getUserByUserName(name);

//            Processing and Uploading File...

            if (file.isEmpty()){

                System.out.println("File is Empty");

            } else {
                contact.setName(file.getOriginalFilename());

                File saveFile = new ClassPathResource("static/image").getFile();

                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());

                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Image is Uploaded");

            }

            boolean add = user.getContacts().add(contact);

            contact.setUser(user);



            this.userRepository.save(user);

            System.out.println("Data " + contact);

            System.out.println("Added to DataBase");
        }catch (Exception e){
            System.out.println("Error " +e.getMessage());
            e.printStackTrace();
        }

        return "normal/add_contact_form";
    }
}
