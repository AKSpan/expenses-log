package span.home.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Создано Span 04.05.2017.
 */
@Entity
@Table(schema = "expenses_log")
public class Categories {
    public final static int EXPENSE_TYPE = 0;
    public final static int INCOME_TYPE = 1;
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "CATEGORIES_SEQ")
    @SequenceGenerator(name = "CATEGORIES_SEQ", sequenceName = "EL.CATEGORIES_SEQ", allocationSize = 1, schema = "expenses_log")
    private Long id;

    @JsonIgnore
    @OneToOne(targetEntity = Users.class)
    private Users user_id;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private byte type;
    @Column(nullable = false)
    private boolean isUserLastChoice;


    public Categories() {
    }

    public Categories(Long id) {
        this.id = id;
    }

    public Categories(String category, byte type, Long id) {
        this.category = category;
        this.type = type;
        this.user_id = new Users(id);
    }

    public Categories(String category, byte type, boolean isUserLastChoice, Users user_id) {
        this.user_id = user_id;
        this.category = category;
        this.type = type;
        this.isUserLastChoice = isUserLastChoice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser_id() {
        return user_id;
    }

    public void setUser_id(Users user_id) {
        this.user_id = user_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public boolean isUserLastChoice() {
        return isUserLastChoice;
    }

    public void setUserLastChoice(boolean userLastChoice) {
        isUserLastChoice = userLastChoice;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", category='" + category + '\'' +
                ", type=" + type +
                ", isUserLastChoice=" + isUserLastChoice +
                '}';
    }

}