package at.ac.fhsalzburg.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Johan on 11.12.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDto {

    @JsonProperty("Amount")
    private Long amount;
    @JsonProperty("Price")
    private Double price;

    public Long getAmount() {
        return amount;
    }

    public TransactionDto setAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public TransactionDto setPrice(Double price) {
        this.price = price;
        return this;
    }
}
