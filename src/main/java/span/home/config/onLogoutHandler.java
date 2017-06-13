/*
package span.home.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import span.home.services.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

*/
/**
 * Created by Alexey on 17.05.2017 20:58.
 *//*

public class onLogoutHandler implements LogoutHandler {
    @Autowired
    private UsersService usersService;

    public onLogoutHandler(UsersService us) {
        this.usersService = us;
    }



    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String sess = ((WebAuthenticationDetails) authentication.getDetails()).getSessionId();
        System.out.println("logout");
        System.out.println(sess);
    }
}*/
