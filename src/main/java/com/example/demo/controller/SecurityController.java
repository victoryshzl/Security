package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by HZL
 * @date 2019/12/19 20:25
 * @description
 */


@RestController
public class SecurityController {
    @RequestMapping("/springboot")
    public String security(){
        return "hello world no security";
    }

    @PreAuthorize("hasRole('ROLE_DBM')")
    @RequestMapping("/")
    public String authorize(){
        return "must security";
    }

    @RequestMapping("/hello/springboot")
    public String hello(){
        return "hello world must security";
    }

}
