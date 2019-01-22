package com.onionknight.data4dota2.config;

import com.ibasco.agql.protocols.valve.dota2.webapi.Dota2WebApiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author :fdy
 * @Date :Created in 12:57 2019/1/19
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
@Configuration
public class BeanConfig {
    @Bean
    public Dota2WebApiClient Dota2Client(){
        String token = "3799EF44464E180230ED399292D33CF1";
        return new Dota2WebApiClient(token);
    }
}
