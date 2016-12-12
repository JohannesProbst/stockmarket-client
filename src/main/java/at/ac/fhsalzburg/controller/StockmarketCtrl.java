package at.ac.fhsalzburg.controller;

import at.ac.fhsalzburg.service.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Johan on 10.12.2016.
 */
@Controller
@RequestMapping("/app")
public class StockmarketCtrl {

    @Autowired
    private StockExchangeService stockExchangeService;

    @RequestMapping(value = "/secure/stockmarket", method = RequestMethod.GET)
    public String getStockmarket(Model model) {

        model.addAttribute("stocks", stockExchangeService.getAllStock());
        return "stockmarket";
    }
}
