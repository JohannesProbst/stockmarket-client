package at.ac.fhsalzburg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Johan on 10.12.2016.
 */
@Controller
@RequestMapping("/app")
public class PortfolioCtrl {

    @RequestMapping(value = "/secure/portfolio", method = RequestMethod.GET)
    public String getPortfolio(HttpSession session) {

        return "portfolio";
    }
}
