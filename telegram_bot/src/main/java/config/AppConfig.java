package config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@PropertySources({
        @PropertySource(value = "classpath:app.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "file:./app.properties", ignoreResourceNotFound = true)
})
@EnableAutoConfiguration
@ComponentScan({"service", "service.impl"})
public class AppConfig {

}
