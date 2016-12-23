package at.ac.fhsalzburg.DTO;

import java.util.List;
import java.util.Optional;

/**
 * Created by Johan on 22.12.2016.
 */
public class Acquisition {

    private String id;
    private String name;
    private Double price;
    private Long amount;
    private Long totalAmount;
    private String amountInfo;
    private String idBoerse;

    public Acquisition(OrderDto orderDto, List<StockDto> stockDtoList) {
        this.id = orderDto.getIdStock();
        Optional<StockDto> stockOptional = stockDtoList.stream().filter(stockDto -> stockDto.getId().equals(orderDto.getIdStock())).findFirst();
        this.name = !stockOptional.isPresent()? "":stockOptional.get().getName();
        this.price = !stockOptional.isPresent()? null: stockOptional.get().getPrice();
        Long amount = orderDto.getTxHistory() == null || orderDto.getTxHistory().isEmpty()? 0L:orderDto.getTxHistory().stream().mapToLong(TransactionDto::getAmount).sum();
        Long actualAmount = 0L;
        if(orderDto.getAmount()!=0L) {
            /*if ("Sell".equals(orderDto.getType())) {
                //actualAmount = orderDto.getAmount() - amount;
                actualAmount = amount;
            } else {
                actualAmount = amount;
            }*/
            this.totalAmount = orderDto.getAmount();
        }else{
            this.totalAmount = amount;
        }
        this.amount = amount;
        this.idBoerse = orderDto.getIdBoerse();
    }

    public Acquisition(){}

    public String getId() {
        return id;
    }

    public Acquisition setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Acquisition setName(String name) {
        this.name = name;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Acquisition setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Long getAmount() {
        return amount;
    }

    public Acquisition setAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public Acquisition setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public String getAmountInfo() {
        return amountInfo;
    }

    public Acquisition setAmountInfo(String amountInfo) {
        this.amountInfo = amountInfo;
        return this;
    }

    public String getIdBoerse() {
        return idBoerse;
    }

    public Acquisition setIdBoerse(String idBoerse) {
        this.idBoerse = idBoerse;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Acquisition)) return false;

        Acquisition that = (Acquisition) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
