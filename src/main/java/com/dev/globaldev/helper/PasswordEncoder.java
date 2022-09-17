package com.dev.globaldev.helper;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    public static String encode(String rawPassword){
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt(12));
    }

    public static Boolean validate(String rawPassword, String hashedPassword){
        return BCrypt.checkpw(rawPassword, hashedPassword);
    }
}
