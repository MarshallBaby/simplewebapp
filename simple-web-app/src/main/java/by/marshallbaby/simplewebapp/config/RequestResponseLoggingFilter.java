package by.marshallbaby.simplewebapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class RequestResponseLoggingFilter extends GenericFilterBean {

    final private Logger logger = LoggerFactory.getLogger("by.marshallbaby.request-logger");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);

        chain.doFilter(requestWrapper, responseWrapper);

        UUID uuid = UUID.randomUUID();

        logger.info(
                String.format("%s IN %s %s",
                        uuid,
                        ((HttpServletRequest) request).getMethod(),
                        new String(requestWrapper.getContentAsByteArray(), requestWrapper.getCharacterEncoding())
                        )
        );

        logger.info(
                String.format("%s OUT %s %s",
                        uuid,
                        ((HttpServletRequest) request).getMethod(),
                        new String(responseWrapper.getContentAsByteArray(), requestWrapper.getCharacterEncoding())
                        )
        );

        responseWrapper.copyBodyToResponse();

    }
}
