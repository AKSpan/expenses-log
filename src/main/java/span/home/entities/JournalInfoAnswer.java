package span.home.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Создано Span 15.05.2017.
 */
public class JournalInfoAnswer {
    @JsonProperty
    private double expPerDay;
    @JsonProperty
    private double expPerWeek;
    @JsonProperty
    private double expPerMonth;
    @JsonProperty
    private double incPerMonth;
    @JsonProperty
    private double balance;


    public JournalInfoAnswer(double expPerDay, double expPerWeek, double expPerMonth, double incPerMonth, double balance) {
        this.expPerDay = expPerDay;
        this.expPerWeek = expPerWeek;
        this.expPerMonth = expPerMonth;
        this.incPerMonth = incPerMonth;
        this.balance = balance;
    }
}
