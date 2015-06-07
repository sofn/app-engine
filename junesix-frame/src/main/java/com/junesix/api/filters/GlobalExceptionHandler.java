package com.junesix.api.filters;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Authors: sofn
 * Version: 1.0  Created at 15-6-7 20:55.
 */
@Component
public class GlobalExceptionHandler implements HandlerExceptionResolver {

   /* @Override
    public String getErrorPath() {
        return "/help/error";
    }*/

    /*@Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return new MyCustomizer();
    }

    private static class MyCustomizer implements EmbeddedServletContainerCustomizer {

        @Override
        public void customize(ConfigurableEmbeddedServletContainer container) {
//            container.
//            container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/help/error"));
        }

    }*/

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
//        Map<String, Object> model = new HashMap();
//        model.put("ex", ex);

        // 根据不同错误转向不同页面
        /*if (ex instanceof BusinessException) {
            return new ModelAndView("error-business", model);
        } else if (ex instanceof ParameterException) {
            return new ModelAndView("error-parameter", model);
        } else {
            return new ModelAndView("error", model);
        }*/
        request.setAttribute("exception", ex);
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", request.getRequestURL());
//        mav.setViewName("error");
        return mav;
//        return null;
    }
 /*   @ExceptionHandler(value = NoHandlerFoundException.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        System.out.println("222");
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
//        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }*/
}
