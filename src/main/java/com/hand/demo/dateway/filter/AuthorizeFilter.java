package com.hand.demo.dateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.Authenticator;
import java.util.Objects;
import java.util.UUID;

@Component
public class AuthorizeFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(Authenticator.class);
    private static String accessToken;

    public  AuthorizeFilter() {
        accessToken = UUID.randomUUID().toString();
        logger.info("==> accessToken : {}",accessToken);
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String accessToken = request.getParameter("access_token");
        if (Objects.equals(accessToken,AuthorizeFilter.accessToken)) {
            requestContext.setResponseStatusCode(HttpStatus.OK.value());
            requestContext.setResponseBody("Authorized.");
            requestContext.setSendZuulResponse(false);
        } else {
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            requestContext.setResponseBody(HttpStatus.UNAUTHORIZED.getReasonPhrase());
            requestContext.setSendZuulResponse(false);
        }
        return null;
    }
}
