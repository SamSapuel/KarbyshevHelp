package com.tasks.application.components;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/")
public class SessionController {

    @GetMapping(value = "/{userEmail}")
    public void createUserCookie(HttpServletResponse response, @PathVariable String userEmail) throws IOException {
        Cookie cookie = new Cookie("userEmail", userEmail);
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);
        response.sendRedirect("http://localhost:25585/api/v1/user");
    }

    @GetMapping(value = "/user")
    public String getCurrentUserId(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userEmail")) {
                return (cookie.getValue());
            }
        }
        return "0";
    }

}
