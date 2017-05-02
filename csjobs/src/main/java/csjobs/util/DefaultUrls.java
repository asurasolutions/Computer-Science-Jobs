package csjobs.util;

import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import csjobs.model.User;
import csjobs.security.SecurityUtils;

@Component
public class DefaultUrls {

    public String homeUrl( HttpServletRequest request ) {
        return SecurityUtils.isAuthenticated() ? userHomeUrl( request )
            : anonymousHomeUrl( request );
    }
    
    public String userHomeUrl( HttpServletRequest request ) {
        User user = SecurityUtils.getUser();
        
        System.out.println("The user id is: " + user.getId());
        HttpSession session = request.getSession();
		session.setAttribute("activeUser", user);
		session.setAttribute("currentUserId", user.getId());
		
		Set<String> roles = user.getRoles();
		
		String homeUrl = "";
		for (String role : roles) {
			if (role.equals("ROLE_ADMIN")) {
				homeUrl = "/adminhome.html";
			} else if (role.equals("ROLE_APPLICANT")) {
				homeUrl = "/applicanthome.html";
			} else if (role.equals("ROLE_REVIEWER")) {
				homeUrl = "/reviewerhome.html";
			}
		}
		
		System.out.println("The user home page is: " + homeUrl);
		
		return homeUrl;
    }
    
    public static String anonymousHomeUrl( HttpServletRequest request ) {
        Cookie cookie = WebUtils.getCookie( request, "default-dept" );
//        return cookie != null ? "/department/" + cookie.getValue() + "/" : "/";
        return cookie != null ? "/home.html" : "/login.html";
    }

	
}
