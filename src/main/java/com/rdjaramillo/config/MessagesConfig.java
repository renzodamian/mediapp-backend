package com.rdjaramillo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class MessagesConfig {

    //carga de los properties
    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        return messageSource;

    }

    //Estalecer un default locale
    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver slr =  new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.ROOT);
        return slr;
    }

    //Para resolver las variables en messages
    public LocalValidatorFactoryBean getValidator(){
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean ();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

}
