package com.dp.supps.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    
    private RequestCache cache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication auth)
            throws ServletException, IOException {

        // REST shouldn't need cached requests.
        // Would only make sense visually:
        //   - Url is requested
        //   - 302 to login
        //   - then back to the original Url
        // So, just discard any cached requests.
        SavedRequest cached = cache.getRequest(request, response);

        if (cached != null) {
            cache.removeRequest(request, response);
        }

        clearAuthenticationAttributes(request);
    }

    public void setRequestCache(RequestCache cache) {
        this.cache = cache;
    }

}
