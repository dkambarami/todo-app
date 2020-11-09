package com.delvin.rest.webservices.HelloWorld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HelloWorld {
   
   @RequestMapping(path = "/hello")
   public String greeting(){

    return "Hello World"; 

   }

   @RequestMapping(path = "helloBean")
   public HelloBean greetingBean(){

    return new HelloBean("Hello WORLDWIDE Sir from Spring Boot server"); 

   }

   @RequestMapping(path = "helloBean/{name}")
   public HelloBean greetingBean(@PathVariable String name){
    return new HelloBean("Hello Great Man from the Spring Boot server Sir "+name); 

   }


    
}
