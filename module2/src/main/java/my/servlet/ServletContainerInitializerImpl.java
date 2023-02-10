package my.servlet;

import my.ConfigService;
import my.config.ConfigSources;
import my.controller.WebConfiguration;

import my.security.WebSecurityConfig;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

public class ServletContainerInitializerImpl implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        AnnotationConfigWebApplicationContext context =
                new AnnotationConfigWebApplicationContext();
        context.register(WebConfiguration.class);
        context.register(ConfigSources.class);
        context.register(WebSecurityConfig.class);
        context.register(ConfigService.class);
        DispatcherServlet dispatcherServlet =
                new DispatcherServlet(context);

        final ServletRegistration.Dynamic servletRegistration =
                ctx.addServlet("dispatcherServlet", dispatcherServlet);
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/index.html");
        servletRegistration.addMapping("*.html");
        servletRegistration.addMapping("*.action");
        servletRegistration.addMapping("*.view");
        servletRegistration.addMapping("*.jpg");

    }
}
