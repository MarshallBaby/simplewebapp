package by.marshallbaby.simplewebapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

//@Component
public class RequestLoggingFilter extends AbstractRequestLoggingFilter {

    private final Logger logger = LoggerFactory.getLogger("by.marshallbaby.request-logger");

    RequestLoggingFilter(){
        setIncludeClientInfo(true);
        setIncludeHeaders(false);
        setIncludePayload(true);
        setIncludeQueryString(true);
        setAfterMessageSuffix("]");
        setAfterMessagePrefix("IN ");
        setMaxPayloadLength(10000);
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        logger.info(message);
    }
}
