package span.home.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import span.home.entities.Users_role;
import span.home.repo.RoleRepo;

/**
 * Created by Alexey on 14.05.2017 21:42.
 */
@Service
public class RolesService {
    @Autowired
    RoleRepo roleRepo;

    public void initRoleTable() {
        roleRepo.save(new Users_role(1L, "ROLE_ADMIN"));
        roleRepo.save(new Users_role(2L, "ROLE_USER"));
        roleRepo.save(new Users_role(3L, "ROLE_GUEST"));
    }
}
