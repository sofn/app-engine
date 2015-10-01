package com.junesix.api.frame.filters;

import com.junesix.api.auth.annotation.BaseInfo;
import com.junesix.api.auth.model.AuthRequest;
import com.junesix.api.auth.model.AuthResponse;
import com.junesix.api.auth.service.AuthService;
import com.junesix.api.frame.context.RequestContext;
import com.junesix.api.frame.context.ThreadLocalContext;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Optional;

/** TODO 拦截不生效
 * @author sofn
 */
@Service
public class AuthResourceFilter extends RequestMappingHandlerAdapter {

    private AuthService authService;

    @Override
    protected ModelAndView handleInternal(HttpServletRequest request, HttpServletResponse response,
                                          HandlerMethod handlerMethod) throws Exception {
        RequestContext context = ThreadLocalContext.getRequestContext();
        AuthRequest authRequest = new AuthRequest(request);

        Method method = handlerMethod.getMethod();
        BaseInfo baseInfo = null;
        if (method.isAnnotationPresent(BaseInfo.class)) {
            baseInfo = method.getAnnotation(BaseInfo.class);
        }
        AuthResponse authResponse = authService.auth(authRequest, Optional.ofNullable(baseInfo));

        System.out.println("handle auth");

        return super.handleInternal(request, response, handlerMethod);
    }

    //    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        HttpServletRequest httpRequest = ThreadLocalContext.getServletRequest();
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
//        filterChain.doFilter(request, response);
//    }

    /*@Override
    public void afterPropertiesSet() {
        this.authService = getApplicationContext().getBean(AuthService.class);
    }*/
}
