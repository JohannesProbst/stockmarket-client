package at.ac.fhsalzburg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Johan on 08.12.2016.
 */
@Controller
@RequestMapping("/app")
public class LoginCtrl {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(HttpSession session) {

        return "login";
    }
}
