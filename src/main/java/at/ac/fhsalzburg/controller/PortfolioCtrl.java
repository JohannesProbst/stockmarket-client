package at.ac.fhsalzburg.controller;

import at.ac.fhsalzburg.service.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Johan on 10.12.2016.
 */
@Controller
@RequestMapping("/app")
public class PortfolioCtrl {

    @Autowired
    StockExchangeService stockExchangeService;

    @RequestMapping(value = "/secure/portfolio", method = RequestMethod.GET)
    public String getPortfolio(Model model, Authentication authentication) {

        model.addAttribute("stocks", stockExchangeService.getAllOrdersByCustomer(authentication.getName()));
        return "portfolio";
    }
}
