package at.ac.fhsalzburg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Johan on 08.12.2016.
 */
@Controller
public class LoginCtrl {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getLogin(Model model) {

        return "login";
    }
}
