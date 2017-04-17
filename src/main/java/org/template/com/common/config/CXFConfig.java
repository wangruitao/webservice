package org.template.com.common.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.template.com.service.UserService;
import org.template.com.service.impl.UserServiceImpl;

@Configuration
public class CXFConfig {

	@Bean
	public ServletRegistrationBean dispatcherServlet() {
		ServletRegistrationBean bean = new ServletRegistrationBean(new CXFServlet(), "/soap/*");
        bean.setLoadOnStartup(0);
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
	}

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}

	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}

	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), userService());
		endpoint.publish("/user");
		endpoint.getInInterceptors().add(new AuthInterceptor());
		return endpoint;
	}
	
	@Bean
	public Endpoint userEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), userService());
		endpoint.publish("/user1");
		endpoint.getInInterceptors().add(new AuthInterceptor());
		return endpoint;
	}
}
