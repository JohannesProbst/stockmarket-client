package at.ac.fhsalzburg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Johan on 21.12.2016.
 */
@Configuration
@Profile("dev")
public class DevConfig {

    @Bean
    public HttpMethodConfig devMethodConfig(){
        return new DevHttpMethodConfig();
    }
}
