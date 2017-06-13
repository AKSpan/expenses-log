package span.home;

import span.home.entities.BalanceCostByMonth;

/**
 * Создано Span 15.05.2017.
 */
public class Main {
    public static void main(String[] args) {
        long e, s = System.currentTimeMillis();
       /* System.out.println(DateAndTime.getDayInterval());
        System.out.println(DateAndTime.getWeekInterval());
        System.out.println(DateAndTime.getMonthInterval());
        System.out.println(DateAndTime.getYearInterval());
        System.out.println(DateAndTime.getDateForPeriod("10.01.2017","10.08.2017"));

        e= System.currentTimeMillis();
        System.out.println(e-s);
        System.out.println(DateAndTime.getDateForPeriod("10.01.2017","10.08.2017"));
        System.out.println(DateAndTime.getDateForYearPeriod(2016));*/
        BalanceCostByMonth balanceCostByMonth = new BalanceCostByMonth();
       // balanceCostByMonth.addValueBalancePerMonthCost(new BalanceCostByMonth.BalancePerMonth(10, 100.));
      //  balanceCostByMonth.addValueBalancePerMonthCost(new BalanceCostByMonth.BalancePerMonth(1, 300.));
     //   balanceCostByMonth.addValueBalancePerMonthCost(new BalanceCostByMonth.BalancePerMonth(2, 500.));
     //   System.out.println(balanceCostByMonth);
     //   System.out.println(balanceCostByMonth.getTotalCost());
    }
}
