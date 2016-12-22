package at.ac.fhsalzburg.service;

import at.ac.fhsalzburg.DTO.OrderDto;
import at.ac.fhsalzburg.DTO.StockDto;
import at.ac.fhsalzburg.config.HttpMethodConfig;
import at.ac.fhsalzburg.exception.StockExchangeConnectionException;
import at.ac.fhsalzburg.exception.StockExchangeInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Johan on 11.12.2016.
 */
@Service
public class StockExchangeServiceImpl implements StockExchangeService{

    @Autowired
    private Environment environment;

    @Autowired
    private HttpMethodConfig httpMethodConfig;

    @Value("${stockExchange.orders}")
    private String orderResource;

    @Value("${stockExchange.stock}")
    private String stockResource;


    @Override
    public OrderDto putOrder(OrderDto order) {
        if(environment.getActiveProfiles()[1].equals("prod"))
            order.setId(0L);
        order.setIdBank("bank.reimar");
        order.setTimeStamp(new Date());
        //TODO: order.setIdBoerse()

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(order,headers);
        return post(entity, orderResource, OrderDto.class);
    }

    @Override
    public OrderDto getOrder(Long orderId) {
        return null;
    }

    @Override
    public List<OrderDto> getAllOrder() throws StockExchangeConnectionException {
        List<OrderDto> orders = getOrders();
        orders.sort(Comparator.comparing(OrderDto::getId).reversed());
        return orders;
    }

    @Override
    public Boolean deleteOrder(Long orderId) {
        return null;
    }

    @Override
    public List<StockDto> getAllStock() throws StockExchangeConnectionException {
        String stockRes = stockResource;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<StockDto>> stockResponse;
        try {
            stockResponse = restTemplate.exchange(stockRes, HttpMethod.GET, null, new ParameterizedTypeReference<List<StockDto>>() {
            });
        }catch (Exception e){
            throw new StockExchangeConnectionException();
        }
        List<StockDto> stockList = stockResponse.getBody();
        return stockList;
    }

    @Override
    public List<OrderDto> getAllOrdersByCustomer(String name) {

        List<OrderDto> orderList = getOrders();
        return orderList.stream().filter(order -> name.equals(order.getIdCustomer())).collect(Collectors.toList());
    }

    private List<OrderDto> getOrders(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<OrderDto>> orderResponse;

        try {
            orderResponse = restTemplate.exchange(orderResource, HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderDto>>() {
            });
        }catch (Exception e){
            throw new StockExchangeConnectionException();
        }
        List<OrderDto> order = orderResponse.getBody();
        return order;
    }

    private <T> T post(HttpEntity entity, String resource, Class<T> classType){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> orderResponse;
        try {
            //to use json-server use POST
            //NOTE: for productive this has to be PUT
            orderResponse = restTemplate.exchange(resource, httpMethodConfig.getOrderSetMethod(), entity, classType);
        }catch (Exception e){
            throw new StockExchangeInputException();
        }
        T response = orderResponse.getBody();
        return response;
    }
}
