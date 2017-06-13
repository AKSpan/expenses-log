package span.home.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Created by Alexey on 14.05.2017 20:53.
 */
@Entity
@Table(schema = "expenses_log")
public class ExpensesAndIncomesList {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "EXP_AND_INC_LIST_SEQ")
    @SequenceGenerator(name = "EXP_AND_INC_LIST_SEQ", sequenceName = "EL.EXP_AND_INC_LIST_SEQ", allocationSize = 1, schema = "expenses_log")
    private Long id;

    @JsonIgnoreProperties
    @OneToOne(targetEntity = Categories.class)
    private Categories category_id;
    @Column(nullable = false)
    private Double summary;
    @Column(nullable = false)
    @JsonFormat(locale = "ru", shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm", timezone = "Europe/Moscow")
    private Date dateAndTime;
    @Column(nullable = false)
    private String commentary;

    public ExpensesAndIncomesList() {
    }

    public ExpensesAndIncomesList(Categories category_id, Double sum, LocalDateTime date, String comment) {
        this.category_id = category_id;
        this.summary = sum;
        this.dateAndTime = DateAndTime.convertLocalDateTimeToDate(date);
        this.commentary = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categories getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Categories category_id) {
        this.category_id = category_id;
    }

    public Double getSummary() {
        return summary;
    }

    public void setSummary(Double summary) {
        this.summary = summary;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public LocalDateTime getLocalDateTime() {
        Instant instant = Instant.ofEpochMilli(this.dateAndTime.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

}