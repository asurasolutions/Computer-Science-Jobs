package csjobs.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import csjobs.model.User;
import csjobs.util.DefaultUrls;

public class LogoutRedirectHandler implements LogoutSuccessHandler {
	
	@Autowired
    DefaultUrls defaultUrls;
	
    @SuppressWarnings("static-access")
	@Override
    public void onLogoutSuccess( HttpServletRequest request,
        HttpServletResponse response, Authentication authentication )
        throws IOException, ServletException
    {
        if( authentication != null )
        {
            User user = (User) authentication.getPrincipal();
            System.out.println( user.getUsername() + " signed out." );
        }

        SimpleUrlLogoutSuccessHandler logoutSuccessHandler = new SimpleUrlLogoutSuccessHandler();
        logoutSuccessHandler.setDefaultTargetUrl( defaultUrls.anonymousHomeUrl( request ) );
        logoutSuccessHandler.onLogoutSuccess( request, response, authentication );
        System.out.println("logout complete");
    }

}
