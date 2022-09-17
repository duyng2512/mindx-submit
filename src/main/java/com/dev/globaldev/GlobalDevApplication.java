package com.dev.globaldev;

import com.dev.globaldev.helper.PasswordEncoder;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GlobalDevApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlobalDevApplication.class, args);
    }

}
