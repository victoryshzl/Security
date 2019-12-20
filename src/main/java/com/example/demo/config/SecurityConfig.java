package com.example.demo.config;

import com.example.demo.dao.UserRedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;


/**
 * @author by HZL
 * @date 2019/12/19 20:02
 * @description
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Resource(name="stringRedisTemplate")
    ValueOperations<String, String> valOpsStr;

    @Resource
    private UserRedisDao userRedisDao;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        /**inMemoryAuthentication：从内存中获取*/
//        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("HZL").password("123456").roles("USER");
//        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("ADMIN").password("123456").roles("ADMIN","DBM");
//    }

    @Override
    public void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.inMemoryAuthentication().withUser(valOpsStr.get("user1")).password(passwordEncoder().encode(valOpsStr.get("password1"))).roles("USER");
        auth.inMemoryAuthentication().withUser(valOpsStr.get("user2")).password(passwordEncoder().encode(valOpsStr.get("password2"))).roles("ADMIN","DBM");
    }


    /**定义安全策略*/
    @Override
    protected void configure(HttpSecurity security) throws Exception{
        /**配置安全策略*/
        security.authorizeRequests()
                /**定义/springboot请求不需要验证*/
                .antMatchers("/springboot").permitAll()
                .antMatchers("/hello/**").hasRole("DBM")
                /**其余请求都需要验证*/
                .anyRequest().authenticated()
                .and()
                /**定义logout不需要验证*/
                .logout().permitAll()
                .and()
                /**使用form表单登录*/
                .formLogin();
    }
}
