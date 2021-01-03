package conf;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@PropertySources({
        @PropertySource(value = "classpath:app.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "file:./app.properties", ignoreResourceNotFound = true)
})
@EnableAutoConfiguration
@Import(DataConfig.class)
@ComponentScan({"controller", "service", "controller.exception"})
public class WebConfig {}
