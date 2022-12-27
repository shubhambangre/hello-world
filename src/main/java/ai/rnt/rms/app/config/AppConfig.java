package ai.rnt.rms.app.config;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

//import ai.rnt.tms.controller.MailSchedule;

@Configuration
@EnableWebMvc
@Async
@EnableScheduling
@ComponentScan(basePackages = "ai.rnt.*")
public class AppConfig extends WebMvcConfigurerAdapter {
	//MailSchedule mailsch = new MailSchedule();

	@Bean
	public ViewResolver viewResolver(ServletContext context) {
		context.setAttribute("cacheBust", UUID.randomUUID().toString());
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getResolver() throws IOException {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();

		// Set the maximum allowed size (in bytes) for each individual file.
		resolver.setMaxUploadSizePerFile(5242880);// 5MB
		// You may also set other available properties.

		return resolver;
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/images/**").addResourceLocations("/static/");
		registry.addResourceHandler("/fonts/**").addResourceLocations("/static/");
		registry.addResourceHandler("/css/**").addResourceLocations("/static/");
		registry.addResourceHandler("/js/**").addResourceLocations("/static/");
		registry.addResourceHandler("/datatable/**").addResourceLocations("/datatable/");

	}


}