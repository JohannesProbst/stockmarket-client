package at.ac.fhsalzburg.controller;

import at.ac.fhsalzburg.DTO.OrderDto;
import at.ac.fhsalzburg.exception.StockExchangeConnectionException;
import at.ac.fhsalzburg.exception.StockExchangeInputException;
import at.ac.fhsalzburg.service.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.net.ConnectException;

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

        try {
            model.addAttribute("orders", stockExchangeService.getAllOrder());
        } catch (StockExchangeConnectionException e) {
            model.addAttribute("errorOrder", "connection");
        }
        try {
            model.addAttribute("stocks", stockExchangeService.getAllStock());
        } catch (StockExchangeConnectionException e){
            model.addAttribute("errorStock", "connection");
        }

        model.addAttribute("newOrder", new OrderDto());

        return "stockmarket";
    }

    @RequestMapping(value = "/secure/stockmarket", method = RequestMethod.POST)
    public String newOrderSubmit(@ModelAttribute OrderDto order, Authentication authentication , RedirectAttributes redir) {

        if(order.getType() == null)
            order.setType("Sell");
        order.setIdCustomer(authentication.getName());
        try {
            stockExchangeService.putOrder(order);
            redir.addFlashAttribute("newOrderNotification","success");
        }catch (StockExchangeInputException e){
            redir.addFlashAttribute("newOrderNotification","error");
        }

        return "redirect:stockmarket";
    }
}