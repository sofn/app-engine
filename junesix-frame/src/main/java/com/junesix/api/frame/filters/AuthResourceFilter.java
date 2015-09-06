/**
 *
 */
package com.junesix.api.frame.filters;


import com.junesix.api.auth.annotation.AuthType;
import com.junesix.api.auth.service.AuthService;
import com.junesix.api.frame.spring.ApplicationContextHolder;
import com.junesix.common.context.ThreadLocalContext;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jolestar
 */
public class AuthResourceFilter extends OncePerRequestFilter {

    public static final String MULTIPART = "multipart/";

    private AuthType authType;

    private AuthService authService;

    public AuthResourceFilter() {
//        this.authType = authType;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest httpRequest = ThreadLocalContext.getServletRequest();
//        ServletContext servletContext = request.getSession().getServletContext();
//        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//        authService = ctx.getBean(AuthService.class);
//        System.out.println(getAuthService().getAuth());
//        AuthRequest authRequest = new HttpServletAuthRequest(httpRequest);
//        AuthResponse response = this.authService.auth(authRequest, authType);
//        RequestContext context = ThreadLocalContext.getInstance().get();
//        context.setCurrentUid(response.getUid());
//        context.setAppId(response.getClientDetails().getClientId().getAppID());
//        context.setOfficialApp(response.getClientDetails().isOfficial());
//        context.setIp(response.getIp());
//        context.setPlatform(response.getPlatform());
//        context.setAttribute("auth_type", response.getAuthedBy());
//        if (ApiLogger.isDebugEnabled()) {
//            ApiLogger.debug("ContainerRequest.filter:request.getHeaderValue(ClientVersion.VERSION_HEADER) =" + request.getHeaderValue(ClientVersion.VERSION_HEADER));
//        }
//        context.setClientVersion(ClientVersion.valueOf(request.getHeaderValue(ClientVersion.VERSION_HEADER)));
        filterChain.doFilter(request, response);
    }

    public AuthService getAuthService() {
        if (this.authService == null) {
            this.authService = ApplicationContextHolder.getBean(AuthService.class);
        }
        return this.authService;
    }
}
