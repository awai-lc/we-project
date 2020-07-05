package com.hbzf.draw.common;


import com.hbzf.draw.service.UserService;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserSessionInterceptor implements HandlerInterceptor {
    /*public final static String USER_SESSION_COOKIE_NAME = "draw_session_id";

    private UserService userService;

    public UserSessionInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        String sessionId = new CookieHelper().getSessionId(request);

        userService.validateUserSession(sessionId);

        // now, see if there is a @LoginRequired annotation on the handler method
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod)handler;
            LoginRequired annotation = method.getMethodAnnotation(LoginRequired.class);
            // should there be any @LoginRequired annotation, check if there is a valid user session
            if (annotation != null) {
                if (userService.getCurrentUser() == null) {
                    // if not, use the specified status code for response and prevent further handling of this request
                    int statusCode = annotation.statusCode();
                    response.setStatus(statusCode);
                    return false;
                }
            }
        }

        // everything looks good. proceed.
        return true;
    }
*/
}
