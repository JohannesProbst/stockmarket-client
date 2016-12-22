package at.ac.fhsalzburg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Johan on 21.12.2016.
 */
@Configuration
@Profile("prod")
public class ProdConfig {

    @Bean
    public HttpMethodConfig prodMethodConfig(){
        return new ProdHttpMethodConfig();
    }

}
