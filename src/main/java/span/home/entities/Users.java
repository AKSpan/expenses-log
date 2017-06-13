package span.home.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Создано Span 18.04.2017.
 */
@Entity
@Table(schema = "expenses_log", name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "USERS_SEQ")
    @SequenceGenerator(name = "USERS_SEQ", sequenceName = "EL.USERS_SEQ", allocationSize = 1, schema = "expenses_log")
    private Long id;

    @Column(unique = true, length = 32)
    private String username;
    @JsonIgnoreProperties
    @Column(length = 60)
    private String password;

    @JsonIgnoreProperties
    @OneToOne(targetEntity = Users_role.class)
    private Users_role role_id;

    public Users() {
    }

    public Users(String username) {
        this.username = username;
    }
    public Users(Long id) {
        this.id = id;
    }


    public Users(String username, String password, Users_role role_id) {
        this.username = username;
        this.password = password;
        this.role_id = role_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole_id(Users_role role_id) {
        this.role_id = role_id;
    }

    public Users_role getRole_id() {
        return role_id;
    }
}
