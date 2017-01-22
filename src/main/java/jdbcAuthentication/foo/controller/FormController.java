package jdbcAuthentication.foo.controller;// Created by Mateusz Płuciennik on 13.09.16.

import jdbcAuthentication.foo.model.Roles;
import jdbcAuthentication.foo.model.WebUser;
import jdbcAuthentication.foo.service.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

import static java.util.Arrays.asList;

@Controller
public class FormController {
    
    @Autowired
    private WebUserService webUserService;

    @RequestMapping(value = "/login")
    public String showFormPage(){
        return "login";
    }

    @RequestMapping(value = "/admin")
    public String showAdminPage(){
        return "adminPage";
    }

    @RequestMapping(value = "/user")
    public String showUserPage() {
        return "userPage";
    }

    @RequestMapping(value = "/wrongData")
    public String showWrongDataPage() {
        return "wrongData";
    }

    @RequestMapping(value = "/registry")
    public String showRegistryPage() {
        return "registry";
    }

    @RequestMapping(value = "/registry", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute WebUser webUser, BindingResult result) {

        if (result.hasErrors()) {return "registryFailure";}

        try {
            webUser.setRoles(asList(new Roles("ROLE_USER")));

            webUserService.saveUser(webUser);
            return "accountCreated";
        } catch (Exception exception) {
            exception.printStackTrace();
            return "registryFailure";
        }
    }

    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public ModelAndView showUserList() {
        List<WebUser> webUsers = webUserService.showAllWebUsers();
        ModelAndView model = new ModelAndView("userList");
        model.addObject("webUsers", webUsers);
        return model;
    }
}
