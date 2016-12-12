package at.ac.fhsalzburg.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Johan on 11.12.2016.
 */
public class CustomDateDeserializer extends JsonDeserializer<Date> {

    String dateFormat ="yyyy-MM-dd'T'hh:mm:ss.SS";

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        String date = p.getText();
        try {
            return format.parse(date);
        } catch (ParseException e){
            throw new RuntimeException(e);
        }
    }
}
