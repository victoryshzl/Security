package com.example.demo.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author by HZL
 * @date 2019/12/19 20:47
 * @description
 */
public class BCryptPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence arg0) {
        return arg0.toString();
    }

    @Override
    public boolean matches(CharSequence arg0, String arg1) {
        return arg1.equals(arg0.toString());
    }
}
