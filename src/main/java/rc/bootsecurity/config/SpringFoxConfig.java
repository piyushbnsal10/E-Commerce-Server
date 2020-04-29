package rc.bootsecurity.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

	@Bean
	public Docket api() {
	    final String swaggerToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb2hpdCIsImV4cCI6MTU4ODgzMTEwMX0._ZbeSyzbHh__vJvTrCp6kdUPnANJiQLFGRxKCJzKRUXsdJkASfgmi0q0IBU_mD7oLQGJJacNoLObTPmh224t2A";    
	    return new Docket(DocumentationType.SWAGGER_2)
	            .globalOperationParameters(Collections.singletonList(
	                    new ParameterBuilder()
	                            .name("Authorization")
	                            .modelRef(new ModelRef("string"))
	                            .parameterType("header")
	                            .required(true)
	                            .hidden(true)
	                            .defaultValue("Bearer " + swaggerToken)
	                            .build()
	                    )
	            );
	}
}