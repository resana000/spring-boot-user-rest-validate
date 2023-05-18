package com.example.demo.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Configuration
public class RequestHeadersServiceConfiguration {

    @Bean
    public Filter getFilter() {
        return new RequestHeadersContextFilter();
    }

    private static class RequestHeadersContextFilter implements Filter {

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, IOException {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            RequestHeadersContext context = new RequestHeadersContext(
                    httpServletRequest.getHeader(RequestHeadersContext.X_SOURCE));
            RequestHeadersContextHolder.setContext(context);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
