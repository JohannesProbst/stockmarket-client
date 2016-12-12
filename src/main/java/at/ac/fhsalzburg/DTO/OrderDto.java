package at.ac.fhsalzburg.DTO;

import at.ac.fhsalzburg.util.CustomDateDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.util.List;

/**
 * Created by Johan on 11.12.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {

    @JsonProperty("Id")
    private Long id;
    @JsonProperty("IdStock")
    private String idStock;
    @JsonProperty("Amount")
    private Long amount;
    @JsonProperty("Price")
    private Double price;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("TxHistory")
    private List<TransactionDto> txHistory;
    @JsonDeserialize( using = CustomDateDeserializer.class)
    @JsonProperty("TimeStamp")
    private Date timeStamp;
    @JsonProperty("IdBoerse")
    private String idBoerse;
    @JsonProperty("Signature")
    private String signature;
    @JsonProperty("IdBank")
    private String idBank;
    @JsonProperty("IdCustomer")
    private String idCustomer;

    public Long getId() {
        return id;
    }

    public OrderDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getIdStock() {
        return idStock;
    }

    public OrderDto setIdStock(String idStock) {
        this.idStock = idStock;
        return this;
    }

    public Long getAmount() {
        return amount;
    }

    public OrderDto setAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public OrderDto setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getType() {
        return type;
    }

    public OrderDto setType(String type) {
        this.type = type;
        return this;
    }

    public List<TransactionDto> getTxHistory() {
        return txHistory;
    }

    public OrderDto setTxHistory(List<TransactionDto> txHistory) {
        this.txHistory = txHistory;
        return this;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public OrderDto setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public String getIdBoerse() {
        return idBoerse;
    }

    public OrderDto setIdBoerse(String idBoerse) {
        this.idBoerse = idBoerse;
        return this;
    }

    public String getSignature() {
        return signature;
    }

    public OrderDto setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    public String getIdBank() {
        return idBank;
    }

    public OrderDto setIdBank(String idBank) {
        this.idBank = idBank;
        return this;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public OrderDto setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDto)) return false;

        OrderDto orderDto = (OrderDto) o;

        if (id != null ? !id.equals(orderDto.id) : orderDto.id != null) return false;
        if (idStock != null ? !idStock.equals(orderDto.idStock) : orderDto.idStock != null) return false;
        if (type != null ? !type.equals(orderDto.type) : orderDto.type != null) return false;
        if (idBoerse != null ? !idBoerse.equals(orderDto.idBoerse) : orderDto.idBoerse != null) return false;
        if (idBank != null ? !idBank.equals(orderDto.idBank) : orderDto.idBank != null) return false;
        return idCustomer != null ? idCustomer.equals(orderDto.idCustomer) : orderDto.idCustomer == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idStock != null ? idStock.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (idBoerse != null ? idBoerse.hashCode() : 0);
        result = 31 * result + (idBank != null ? idBank.hashCode() : 0);
        result = 31 * result + (idCustomer != null ? idCustomer.hashCode() : 0);
        return result;
    }
}
