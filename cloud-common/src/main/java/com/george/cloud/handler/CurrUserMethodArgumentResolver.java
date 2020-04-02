package com.george.cloud.handler;

import com.george.cloud.annotation.RequestUser;
import com.george.cloud.model.RequestSubject;
import com.george.cloud.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

/**
 * <p>
 *
 * </p>
 *
 * @author GeorgeChan 2020/2/4 13:13
 * @version 1.0
 * @since jdk1.8
 */
public class CurrUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private JwtUtils jwtUtils;
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(RequestUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String authorization = request.getHeader("Authorization");
        RequestSubject subject = null;
        RequestUser token = null;
        if(authorization!=null){
            Annotation[] methodAnnotations = methodParameter.getParameterAnnotations();
            for (Annotation methodAnnotation : methodAnnotations) {
                if(methodAnnotation instanceof RequestUser){
                    token = (RequestUser) methodAnnotation;
                    break;
                }
            }
            if(token!=null){
                subject = jwtUtils.get(authorization);
            }
        }
        return subject;
    }
}
