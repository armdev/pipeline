package io.project.app.eventcare;

import com.fasterxml.jackson.core.StreamReadConstraints;
import io.undertow.conduits.GzipStreamSourceConduit;
import io.undertow.server.handlers.encoding.RequestEncodingHandler;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaRepositories("io.project.app.eventcare.repositories")
@ComponentScan(basePackages = {"io.project"})
@EntityScan("io.project.app.eventcare.domain")
@EnableDiscoveryClient
@Slf4j
@EnableAsync
public class EventcareApplication {

    public static void main(String[] args) throws UnknownHostException {
        final SpringApplication application = new SpringApplication(EventcareApplication.class);
        application.setBannerMode(Banner.Mode.CONSOLE);
        application.setWebApplicationType(WebApplicationType.SERVLET);

        Environment environment = application.run(args).getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String serverPort = environment.getProperty("server.port");
        String contextPath
                = Optional.ofNullable(environment.getProperty("server.servlet.context-path")).orElse("");
        String applicationName = environment.getProperty("spring.application.name");
        log.info("""
             
             ----------------------------------------------------------
             \tApplication """ + applicationName + " is running! Access URLs:\n\t"
                + "Local Access URL: \t\thttp://localhost:" + serverPort + contextPath + "\n\t"
                + "External Access URL: \thttps://" + ip + ":" + serverPort + contextPath + "\n\t"
                + "----------------------------------------------------------");
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedMethods(CorsConfiguration.ALL)
                        .allowedHeaders(CorsConfiguration.ALL)
                        .allowedOriginPatterns(CorsConfiguration.ALL);
            }
        };
    }

    @Bean("eventAsyncExecutor")
    public Executor getAsyncExecutor() {
        return new ForkJoinPool(
                100, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true);
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customStreamReadConstraints() {
        return (builder) -> builder.postConfigurer((objectMapper) -> objectMapper.getFactory()
                .setStreamReadConstraints(StreamReadConstraints.builder().maxNestingDepth(4000).build()));
    }
    
    @Bean
    public UndertowServletWebServerFactory undertowServletWebServerFactory() {
        UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();
        factory.addDeploymentInfoCustomizers((deploymentInfo) -> deploymentInfo.addInitialHandlerChainWrapper(handler -> new RequestEncodingHandler(handler)
                .addEncoding("gzip", GzipStreamSourceConduit.WRAPPER)));
        return factory;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        log.info("EventcareApplication first event fired");
    }
}
