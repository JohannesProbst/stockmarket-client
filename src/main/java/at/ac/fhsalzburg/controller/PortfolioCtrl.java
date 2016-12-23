package at.ac.fhsalzburg.controller;

import at.ac.fhsalzburg.DTO.Acquisition;
import at.ac.fhsalzburg.DTO.OrderDto;
import at.ac.fhsalzburg.DTO.StockDto;
import at.ac.fhsalzburg.exception.StockExchangeConnectionException;
import at.ac.fhsalzburg.service.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johan on 10.12.2016.
 */
@Controller
@RequestMapping("/app")
public class PortfolioCtrl {

    @Autowired
    StockExchangeService stockExchangeService;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = "/secure/portfolio", method = RequestMethod.GET)
    public String getPortfolio(Model model, Authentication authentication) {

        List<Acquisition> acquisitionList = new ArrayList<>();
        List<StockDto> stocks = new ArrayList<>();
        try {
            stocks = stockExchangeService.getAllStock();
        } catch (StockExchangeConnectionException e) {
            model.addAttribute("errorStock", "connection");
        }
        try {
            List<OrderDto> orders = stockExchangeService.getAllOrdersByCustomer(authentication.getName());
            final List<StockDto> finalStocks = stocks;
            orders.forEach(orderDto -> {
                Acquisition acquisition = new Acquisition(orderDto, finalStocks);
                if("Sell".equals(orderDto.getType())) {
                    acquisition.setAmountInfo(messageSource.getMessage("portfolio.amount.info.sold", new Object[]{acquisition.getTotalAmount()}, "sold", null));
                }else {
                    acquisition.setAmountInfo(messageSource.getMessage("portfolio.amount.info.bought", new Object[]{acquisition.getTotalAmount()}, "bought", null));
                }
                acquisitionList.add(acquisition);
            });
        } catch (StockExchangeConnectionException e) {
            model.addAttribute("errorOrder", "connection");
        }

        model.addAttribute("stocks", acquisitionList);
        return "portfolio";
    }
}
