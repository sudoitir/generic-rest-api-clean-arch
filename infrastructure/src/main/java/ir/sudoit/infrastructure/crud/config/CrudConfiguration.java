package ir.sudoit.infrastructure.crud.config;

import ir.sudoit.core.crud.utility.PropertiesConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CrudConfiguration {


    @Bean
    public PropertiesConfig propertiesConfig(){
        return new PropertiesConfig();
    }
}
