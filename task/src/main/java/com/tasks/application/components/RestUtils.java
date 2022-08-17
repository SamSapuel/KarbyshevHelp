package com.tasks.application.components;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.Cookie;
import java.net.URI;

public class RestUtils {

    public static HttpHeaders createLocationHeaderFromCurrentUri(String path, Object... uriVariableValues) {
        assert path != null;

        final URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path(path).buildAndExpand(
                uriVariableValues).toUri();
        final HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.LOCATION, location.toASCIIString());
        return headers;
    }

    /**
     * Find cookie with user id from all cookies.
     */
    public static String getCookieUserId(Cookie[] cookies) {
        for (Cookie cookie : cookies)
            if (cookie.getName().equals("userEmail"))
                return (cookie.getValue());
        return "0";
    }
}
