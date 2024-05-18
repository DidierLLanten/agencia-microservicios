package com.agencia.viajes.security;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MessageConverterConfig {

    @Bean
    public HttpMessageConverters httpMessageConverters() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(jsonConverter);
        return new HttpMessageConverters(converters);
    }
}
