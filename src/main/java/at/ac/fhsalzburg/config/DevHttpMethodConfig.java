package at.ac.fhsalzburg.config;

import org.springframework.http.HttpMethod;

/**
 * Created by Johan on 21.12.2016.
 */
public class DevHttpMethodConfig implements HttpMethodConfig {
    @Override
    public HttpMethod getOrderSetMethod() {
        return HttpMethod.POST;
    }
}
