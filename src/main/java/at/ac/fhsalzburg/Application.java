package at.ac.fhsalzburg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Johan on 01.12.2016.
 */
@SpringBootApplication
@RestController
public class Application {

    @RequestMapping("/")
    public String home(){
        return "Hello Docker World!";
    }

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
