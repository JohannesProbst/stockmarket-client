package at.ac.fhsalzburg.controller;

import at.ac.fhsalzburg.DTO.OrderDto;
import at.ac.fhsalzburg.DTO.StockDto;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Johan on 10.12.2016.
 */
@Controller
@RequestMapping("/app")
public class StockmarketCtrl {

    @Autowired
    private StockExchangeService stockExchangeService;

    @RequestMapping(value = "/secure/stockmarket", method = RequestMethod.GET)
    public String getStockmarket(Model model, @RequestParam(value = "market", required = false) String selectedMarket ) {
        List<StockDto> stockDtoList = new ArrayList<>();

            try {
                stockDtoList = stockExchangeService.getAllStock();
                Set<String> markets = stockDtoList.stream().map(StockDto::getIdBoerse).collect(Collectors.toSet());
                model.addAttribute("exchangeMarkets", markets);
            } catch (StockExchangeConnectionException e) {
                model.addAttribute("errorStock", "connection");
            }

        if(selectedMarket != null && !selectedMarket.isEmpty()){
            List<OrderDto> orders = new ArrayList<>();
            List<StockDto> stocks = new ArrayList<>();
            try {
                orders = stockExchangeService.getAllOrder();
                orders = orders.stream().filter(orderDto -> selectedMarket.equals(orderDto.getIdBoerse())).collect(Collectors.toList());
                model.addAttribute("orders", orders);
            } catch (StockExchangeConnectionException e) {
                model.addAttribute("errorOrder", "connection");
            }
            try {
                stocks = stockExchangeService.getAllStock();
                stocks = stocks.stream().filter(stockDto -> selectedMarket.equals(stockDto.getIdBoerse())).collect(Collectors.toList());
                model.addAttribute("stocks", stocks);
            } catch (StockExchangeConnectionException e) {
                model.addAttribute("errorStock", "connection");
            }
        }

        model.addAttribute("newOrder", new OrderDto());

        return "stockmarket";
    }

    @RequestMapping(value = "/secure/stockmarket", method = RequestMethod.POST)
    public String newOrderSubmit(@ModelAttribute OrderDto order, Authentication authentication, RedirectAttributes redir) {

        if (order.getType() == null)
            order.setType("Sell");
        order.setIdCustomer(authentication.getName());
        try {
            stockExchangeService.putOrder(order);
            redir.addFlashAttribute("newOrderNotification", "success");
        } catch (StockExchangeInputException e) {
            redir.addFlashAttribute("newOrderNotification", "error");
        }

        return "redirect:stockmarket?market="+order.getIdBoerse();
    }
}