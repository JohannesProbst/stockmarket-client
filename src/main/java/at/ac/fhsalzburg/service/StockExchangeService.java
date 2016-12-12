package at.ac.fhsalzburg.service;

import at.ac.fhsalzburg.DTO.OrderDto;
import at.ac.fhsalzburg.DTO.StockDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Johan on 11.12.2016.
 */

public interface StockExchangeService {

    /**
     * sends the given order to the stock exchange for persisting
     * @param order
     * @return saved order
     */
    OrderDto putOrder(OrderDto order);

    /**
     * retrieves the via id requested order from the stock exchange
     * @param orderId
     * @return requested order
     */
    OrderDto getOrder(Long orderId);

    /**
     * retrieves the complete list of orders at the stock exchange
     * @return list of orders
     */
    List<OrderDto> getAllOrder();

    /**
     * deletes a order with the given id
     * @param orderId
     * @return true if the deletion was successful
     */
    Boolean deleteOrder(Long orderId);

    /**
     * retrieves all available stocks from the stock exchange
     * @return list of stocks
     */
    StockDto getAllStock();
}
