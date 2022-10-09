package ru.mironovmike.store.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Component
public class UserContextFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        UserContext userContext = UserContextHolder.getUserContext();
        String header = request.getHeader(UserContext.REQUEST_ID);
        userContext.setRequestId(header);
        log.info(String.format("Request id: %s", UserContextHolder.getUserContext().getRequestId()));
        filterChain.doFilter(request, servletResponse);
    }
}
