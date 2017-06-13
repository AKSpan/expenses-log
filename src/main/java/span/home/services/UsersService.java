package span.home.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import span.home.entities.Users;
import span.home.entities.Users_role;
import span.home.repo.UsersRepo;

/**
 * Создано Span 18.04.2017.
 */
@Service
public class UsersService {
    private final
    UsersRepo usersRepo;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UsersService(UsersRepo usersRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepo = usersRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Users getUserByUsername(String username) {
        return usersRepo.findByUsername(username);
    }


    public void save(Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersRepo.save(user);
    }


   /* public void updateUserSessionID(String username, String sessionID) {
        usersRepo.setUserSessionID(username, sessionID);
    }

    public Long getUserIDbySession(String session) {
        return usersRepo.getUserIDbySession(session);
    }

*/


    public void initUsersTable() {
        this.save(new Users("span", "123", new Users_role(1L, "")));
        this.save(new Users("user", "user", new Users_role(2L, "")));
        this.save(new Users("guest", "guest", new Users_role(3L, "")));
    }
    /* public void clearSession(String sess) {
        usersRepo.clearSession(sess);
    }*/
}
