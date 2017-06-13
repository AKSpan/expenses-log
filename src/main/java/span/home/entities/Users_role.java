package span.home.entities;

import javax.persistence.*;

/**
 * Создано Span 20.04.2017.
 */
@Entity
@Table(name = "roles",schema = "expenses_log")
public class Users_role {
    @Id
    private Long id;
    @Column(length = 20)
    private String role;

    public Users_role() {
    }

    public Users_role(Long id) {
        this.id = id;
    }

    public Users_role(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
