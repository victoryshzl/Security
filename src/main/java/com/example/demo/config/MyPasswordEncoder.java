package com.example.demo.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author by HZL
 * @date 2019/12/19 20:07
 * @description
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence agr0){
        return agr0.toString();
    }

    @Override
    public boolean matches(CharSequence arg0,String arg1){
        return arg1.equals(arg0.toString());
    }
}
