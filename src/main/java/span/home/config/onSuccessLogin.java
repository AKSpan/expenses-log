/*
package span.home.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import span.home.services.ExpensesAndIncomesService;
import span.home.services.UsersService;

import java.text.ParseException;

*/
/**
 * Created by Alexey on 14.05.2017 1:14.
 *//*

@Service
public class onSuccessLogin implements
        ApplicationListener<AuthenticationSuccessEvent> {

    private final
    UsersService usersService;
    private final ExpensesAndIncomesService expensesAndIncomesService;

    @Autowired
    public onSuccessLogin(UsersService usersService, ExpensesAndIncomesService expensesAndIncomesService) {
        this.usersService = usersService;
        this.expensesAndIncomesService = expensesAndIncomesService;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
       */
/* System.out.println("success log in");
        String userSess = ((WebAuthenticationDetails) ((UsernamePasswordAuthenticationToken) authenticationSuccessEvent.getSource()).getDetails()).getSessionId();
        String username = ((UserDetails) authenticationSuccessEvent.getAuthentication().getPrincipal()).getUsername();
        System.out.println("username " + username);
        System.out.println("session " + userSess);

        usersService.updateUserSessionID(userSess, username);*//*


    }
}
*/
