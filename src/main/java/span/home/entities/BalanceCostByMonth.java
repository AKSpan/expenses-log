package span.home.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 26.05.2017 20:41.
 */
public class BalanceCostByMonth {
    @JsonProperty
    private int totalCost;
    @JsonProperty
    private List<BalancePerMonth> balancePerMonths = new ArrayList<>();
    private List<BalancePerMonth> expPerMonths = new ArrayList<>();
    private List<BalancePerMonth> incPerMonths = new ArrayList<>();

    public int getTotalCost() {
        return totalCost;
    }

    public BalanceCostByMonth() {
    }

    public void addValueExpPerMonthCost(BalancePerMonth b) {
        this.expPerMonths.add(b);
//        this.totalCost -= b.getCostValue();
    }

    public void addValueIncPerMonthCost(BalancePerMonth b) {
        this.incPerMonths.add(b);
//        this.totalCost += b.getCostValue();

    }

    public void calcBalancePerMonths() {
        for (int i = 0; i < this.getExpPerMonths().size(); i++) {
            balancePerMonths.add(new BalancePerMonth(this.getIncPerMonths().get(i).getMonthID(), this.getIncPerMonths().get(i).getCostValue() - this.getExpPerMonths().get(i).getCostValue()));
            this.totalCost += balancePerMonths.get(i).getCostValue();
        }
    }

    private List<BalancePerMonth> getExpPerMonths() {
        return expPerMonths;
    }

    private List<BalancePerMonth> getIncPerMonths() {
        return incPerMonths;
    }

    public static class BalancePerMonth {
        private final String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

        public String getMonthName() {
            return monthName;
        }

        private int monthID;
        @JsonProperty
        private String monthName;

        @JsonProperty
        private Double costValue;

        public BalancePerMonth(int monthID, Double costValue) {
            this.monthID = monthID;
            this.monthName = months[this.monthID];
            this.costValue = costValue;
        }

        int getMonthID() {
            return monthID;
        }

        Double getCostValue() {
            return costValue;
        }

        @Override
        public String toString() {
            return "BalancePerMonth{" +
                    ", monthName='" + monthName + '\'' +
                    ", costValue=" + costValue +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BalanceCostByMonth{" +
                "totalCost=" + totalCost +
                ", balancePerMonths=" + balancePerMonths +
                '}';
    }
}
