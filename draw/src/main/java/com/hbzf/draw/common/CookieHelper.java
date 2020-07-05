package com.hbzf.draw.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieHelper {

    public String USER_SESSION_COOKIE_NAME = "draw_session_id";

    public String getSessionId(HttpServletRequest request) {
        String sessionId = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (USER_SESSION_COOKIE_NAME.equals(cookie.getName())) {
                    sessionId = cookie.getValue();
                }
            }
        }
        return sessionId;
    }

    public void setSessionId(HttpServletResponse response, String sessionId) {
        Cookie cookie = new Cookie(USER_SESSION_COOKIE_NAME, sessionId);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
