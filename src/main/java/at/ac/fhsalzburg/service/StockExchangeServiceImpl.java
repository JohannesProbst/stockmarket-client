package at.ac.fhsalzburg.service;

import at.ac.fhsalzburg.DTO.OrderDto;
import at.ac.fhsalzburg.DTO.StockDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Johan on 11.12.2016.
 */
@Service
public class StockExchangeServiceImpl implements StockExchangeService{

    RestTemplate restTemplate = new RestTemplate();

    @Value("${stockExchange.orders}")
    String orderResource;

    @Override
    public OrderDto putOrder(OrderDto order) {
        return null;
    }

    @Override
    public OrderDto getOrder(Long orderId) {
        return null;
    }

    @Override
    public List<OrderDto> getAllOrder() {
        ResponseEntity<List<OrderDto>> orderResponse = restTemplate.exchange(orderResource, HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderDto>>() {});
        List<OrderDto> order = orderResponse.getBody();
        return order;
    }

    @Override
    public Boolean deleteOrder(Long orderId) {
        return null;
    }

    @Override
    public StockDto getAllStock() {
        return null;
    }
}
