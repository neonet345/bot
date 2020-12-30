package conf;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:app.properties")
@EnableAutoConfiguration
@Import(DataConfig.class)
@ComponentScan({"controller", "service", "controller.exception"})
public class WebConfig {}
