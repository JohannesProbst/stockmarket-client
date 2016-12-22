package at.ac.fhsalzburg.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Johan on 11.12.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockDto {

    @JsonProperty("Id")
    private String id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Price")
    private Double price;
    @JsonProperty("IdBoerse")
    private String idBoerse;

    public String getId() {
        return id;
    }

    public StockDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public StockDto setName(String name) {
        this.name = name;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public StockDto setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getIdBoerse() {
        return idBoerse;
    }

    public StockDto setIdBoerse(String idBoerse) {
        this.idBoerse = idBoerse;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockDto)) return false;

        StockDto stockDto = (StockDto) o;

        if (id != null ? !id.equals(stockDto.id) : stockDto.id != null) return false;
        return idBoerse != null ? idBoerse.equals(stockDto.idBoerse) : stockDto.idBoerse == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idBoerse != null ? idBoerse.hashCode() : 0);
        return result;
    }
}