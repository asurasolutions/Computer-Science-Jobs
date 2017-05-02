package csjobs.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import csjobs.model.User;
import csjobs.util.DefaultUrls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    DefaultUrls defaultUrls;
    
    private HttpSession session;
    
    @Override
    public void onAuthenticationSuccess( HttpServletRequest request, HttpServletResponse response, Authentication authentication ) throws ServletException, IOException {
    	System.out.println("came to authorization success function");
        User user = (User) authentication.getPrincipal();
        
        session = request.getSession();
        if (user == null) {
        	session.setAttribute("loginerror", "Invalid username or password, please try again");
        	System.out.println("Added error message.");
        } else {
        	session.removeAttribute("loginerror");
        	logger.info( user.getFirstName() + " signed in." );
        }

        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest( request, response );
        if( savedRequest != null )
        {
            super.onAuthenticationSuccess( request, response, authentication );
            return;
        }
        System.out.println("Coming below signin message !");
        getRedirectStrategy().sendRedirect( request, response, defaultUrls.userHomeUrl( request ) );
    }
}
