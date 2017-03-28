package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration  						// -- this will let spring know this contains bean definitions.
@EnableWebMvc							// -- this is the same as <mvc:annotation-driven/>
@ComponentScan({"dao","controller","config"}) 	// -- this is the same as <context:component-scan base-package=”com.luckyryan.sample”/>
@Import({ SecurityConfig.class })

/*
 	Extend the class to use WebMvcConfigurerAdapter. 
 	This adds stub implementations from the WebMvcConfigurer interface which is used by @EnableWebMVC. 
 	It also gives us a chance to override resources and the default handler.
*/
public class AppConfig extends WebMvcConfigurerAdapter {
	
	
	/*
	 * Add bean for InternalResourceViewResolver
	*/
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	/*
	 * Set default servlet handler, this is the same as <mvc:default-servlet-handler/>
	*/
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	/*
	 * Declare our static resources. I added cache to the java config but it’s not required.
	*/
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926);
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
    }
	 	
}
