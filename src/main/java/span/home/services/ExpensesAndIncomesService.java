package span.home.services;

import org.springframework.stereotype.Service;
import span.home.entities.*;
import span.home.repo.ExpensesAndIncomesRepo;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

/**
 * Создано Span 15.05.2017.
 */
@Service
public class ExpensesAndIncomesService {
    private final ExpensesAndIncomesRepo expensesAndIncomesRepo;

    public ExpensesAndIncomesService(ExpensesAndIncomesRepo expensesAndIncomesRepo) {
        this.expensesAndIncomesRepo = expensesAndIncomesRepo;
    }

    /*******************************************/
    /*************Расходы за периоды************/
    /*******************************************/
    public Double getExpensesSumForToday(String username) throws ParseException {
        DateAndTime dateAndTime = DateAndTime.getDayInterval();
        Double d = expensesAndIncomesRepo.getExpSumForInterval(username, dateAndTime.getDateAfter(), dateAndTime.getDateBefore());
        return d != null ? Math.round(d * 100) / 100.0 : 0;
    }

    public Double getExpensesSumForWeek(String sessionID) throws ParseException {
        DateAndTime dateAndTime = DateAndTime.getWeekInterval();
        Double d = expensesAndIncomesRepo.getExpSumForInterval(sessionID, dateAndTime.getDateAfter(), dateAndTime.getDateBefore());
        return d != null ? Math.round(d * 100) / 100.0 : 0;
    }

    public Double getExpensesSumForMonth(String sessionID) throws ParseException {
        DateAndTime dateAndTime = DateAndTime.getMonthInterval();
        Double d = expensesAndIncomesRepo.getExpSumForInterval(sessionID, dateAndTime.getDateAfter(), dateAndTime.getDateBefore());
        return d != null ? Math.round(d * 100) / 100.0 : 0;
    }

    public Double getExpensesSumForYear(String sessionID) throws ParseException {
        DateAndTime dateAndTime = DateAndTime.getYearInterval();
        Double d = expensesAndIncomesRepo.getExpSumForInterval(sessionID, dateAndTime.getDateAfter(), dateAndTime.getDateBefore());
        return d != null ? Math.round(d * 100) / 100.0 : 0;
    }

    public List<ExpensesAndIncomesList> getExpensesListForToday(String sessionID) throws ParseException {
        DateAndTime dateAndTime = DateAndTime.getDayInterval();
        return expensesAndIncomesRepo.getExpListForInterval(sessionID, dateAndTime.getDateAfter(), dateAndTime.getDateBefore());
    }

    public List<ExpensesAndIncomesList> getExpensesListForWeek(String sessionID) throws ParseException {
        DateAndTime dateAndTime = DateAndTime.getWeekInterval();
        return expensesAndIncomesRepo.getExpListForInterval(sessionID, dateAndTime.getDateAfter(), dateAndTime.getDateBefore());
    }

    public List<ExpensesAndIncomesList> getExpensesListForMonth(String sessionID) throws ParseException {
        DateAndTime dateAndTime = DateAndTime.getMonthInterval();
        return expensesAndIncomesRepo.getExpListForInterval(sessionID, dateAndTime.getDateAfter(), dateAndTime.getDateBefore());
    }

    public List<ExpensesAndIncomesList> getExpensesListForYear(String sessionID) throws ParseException {
        DateAndTime dateAndTime = DateAndTime.getYearInterval();
        return expensesAndIncomesRepo.getExpListForInterval(sessionID, dateAndTime.getDateAfter(), dateAndTime.getDateBefore());
    }

    public List<ExpensesAndIncomesList> getExpensesListForPeriod(String sessionID, String dateAfter, String dateBefore) {
        DateAndTime dateAndTime = DateAndTime.getDateForPeriod(dateAfter, dateBefore);
        return expensesAndIncomesRepo.getExpListForInterval(sessionID, dateAndTime.getDateAfter(), dateAndTime.getDateBefore());

    }
    /*******************************************/
    /*************Доходы за периоды*************/
    /*******************************************/
    public Double getIncomesSumForToday(String sessionID) throws ParseException {
        DateAndTime dateAndTime = DateAndTime.getDayInterval();
        Double d = expensesAndIncomesRepo.getIncSumForInterval(sessionID, dateAndTime.getDateAfter(), dateAndTime.getDateBefore());
        return d != null ? Math.round(d * 100) / 100.0 : 0;
    }

    public Double getIncomesSumForWeek(String sessionID) throws ParseException {
        DateAndTime dateAndTime = DateAndTime.getWeekInterval();
        Double d = expensesAndIncomesRepo.getIncSumForInterval(sessionID, dateAndTime.getDateAfter(), dateAndTime.getDateBefore());
        return d != null ? Math.round(d * 100) / 100.0 : 0;
    }

    public Double getIncomesSumForMonth(String sessionID) throws ParseException {
        DateAndTime dateAndTime = DateAndTime.getMonthInterval();
        Double d = expensesAndIncomesRepo.getIncSumForInterval(sessionID, dateAndTime.getDateAfter(), dateAndTime.getDateBefore());
        return d != null ? Math.round(d * 100) / 100.0 : 0;
    }

    public Double getIncomesSumForYear(String sessionID) throws ParseException {
        DateAndTime dateAndTime = DateAndTime.getYearInterval();
        Double d = expensesAndIncomesRepo.getIncSumForInterval(sessionID, dateAndTime.getDateAfter(), dateAndTime.getDateBefore());
        return d != null ? Math.round(d * 100) / 100.0 : 0;
    }

    public List<ExpensesAndIncomesList> getIncomesListForToday(String sessionID) throws ParseException {
        DateAndTime dateAndTime = DateAndTime.getDayInterval();
        return expensesAndIncomesRepo.getIncListForInterval(sessionID, dateAndTime.getDateAfter(), dateAndTime.getDateBefore());
    }

    public List<ExpensesAndIncomesList> getIncomesListForWeek(String sessionID) throws ParseException {
        DateAndTime dateAndTime = DateAndTime.getWeekInterval();
        return expensesAndIncomesRepo.getIncListForInterval(sessionID, dateAndTime.getDateAfter(), dateAndTime.getDateBefore());
    }

    public List<ExpensesAndIncomesList> getIncomesListForMonth(String sessionID) throws ParseException {
        DateAndTime dateAndTime = DateAndTime.getMonthInterval();
        return expensesAndIncomesRepo.getIncListForInterval(sessionID, dateAndTime.getDateAfter(), dateAndTime.getDateBefore());
    }

    public List<ExpensesAndIncomesList> getIncomesListForYear(String sessionID) throws ParseException {
        DateAndTime dateAndTime = DateAndTime.getYearInterval();
        return expensesAndIncomesRepo.getIncListForInterval(sessionID, dateAndTime.getDateAfter(), dateAndTime.getDateBefore());
    }

    public List<ExpensesAndIncomesList> getIncomesListForPeriod(String sessionID, String dateAfter, String dateBefore) {
        DateAndTime dateAndTime = DateAndTime.getDateForPeriod(dateAfter, dateBefore);
        return expensesAndIncomesRepo.getIncListForInterval(sessionID, dateAndTime.getDateAfter(), dateAndTime.getDateBefore());

    }
    /*******************************************/
    /*************Баланс за периоды*************/
    /*******************************************/
    public Double getBalanceForYear(String sessionID) throws ParseException {

        Double inc = expensesAndIncomesRepo.getBalanceSumForMonthByType(sessionID, (byte) 1);
        Double exp = expensesAndIncomesRepo.getBalanceSumForMonthByType(sessionID, (byte) 0);

        return Math.round(100 * ((inc == null ? 0 : inc) - (exp == null ? 0 : exp))) / 100.0;
    }

    /*******************************************/
    public int saveExpenseData(ExpensesAndIncomesList expensesAndIncomesList) {
        ExpensesAndIncomesList res = expensesAndIncomesRepo.save(expensesAndIncomesList);
        return res.getId() != null ? 1 : 0;
    }
    /**************************************/
    /********Баланс за год по месяцам******/
    /**************************************/

    public BalanceCostByMonth getBalanceForYear(int year, String username) {
        StringBuilder sbDB;
        StringBuilder sbDA;
        DateAndTime dateAndTime;
        Double expCost, incCost;
        BalanceCostByMonth.BalancePerMonth expPerMonth;
        BalanceCostByMonth.BalancePerMonth incPerMonth;
        BalanceCostByMonth balanceCostByMonth = new BalanceCostByMonth();
        for (byte i = 0; i < 12; i++) {
            sbDB = new StringBuilder();
            sbDA = new StringBuilder();
            if (i < 9) {
                sbDA.append("01.").append("0").append(i + 1).append(".").append(year);
                sbDB.append("31.").append("0").append(i + 1).append(".").append(year);
            } else {
                sbDA.append("01.").append(i + 1).append(".").append(year);
                sbDB.append("31.").append(i + 1).append(".").append(year);
            }

            dateAndTime = DateAndTime.getDateForPeriod(sbDA.toString(), sbDB.toString());
            expCost = expensesAndIncomesRepo.getExpSumForInterval(username, dateAndTime.getDateAfter(), dateAndTime.getDateBefore());
            incCost = expensesAndIncomesRepo.getIncSumForInterval(username, dateAndTime.getDateAfter(), dateAndTime.getDateBefore());
            expPerMonth = new BalanceCostByMonth.BalancePerMonth(i, expCost == null ? 0 : expCost);
            incPerMonth = new BalanceCostByMonth.BalancePerMonth(i, incCost == null ? 0 : incCost);
            balanceCostByMonth.addValueExpPerMonthCost(expPerMonth);
            balanceCostByMonth.addValueIncPerMonthCost(incPerMonth);
            // balanceCostByMonth.calcBalancePerMonths();
            // costsByMonth.add(balanceCostByMonth);
        }
        balanceCostByMonth.calcBalancePerMonths();
        return balanceCostByMonth;
    }

    public List<ShortExpIncData> getDataByCategories(String username, RequestForAllExpAndInc requestForAllExpAndInc) {
            return expensesAndIncomesRepo.getDataByCategories(username, requestForAllExpAndInc.getType(),requestForAllExpAndInc.getDateAndTime().getDateAfter(),requestForAllExpAndInc.getDateAndTime().getDateBefore());
    }

    @Deprecated
    public void initExpensesAndIncomesTable() {
        expensesAndIncomesRepo.save(new ExpensesAndIncomesList(new Categories(1L), 100d, LocalDateTime.now(), "today1"));
        expensesAndIncomesRepo.save(new ExpensesAndIncomesList(new Categories(2L), 200d, LocalDateTime.now(), "today2"));
        expensesAndIncomesRepo.save(new ExpensesAndIncomesList(new Categories(3L), 300d, LocalDateTime.now(), "today3"));
        expensesAndIncomesRepo.save(new ExpensesAndIncomesList(new Categories(2L), 205d, LocalDateTime.of(2017, Month.MAY, 15, 1, 30), "week1"));
        expensesAndIncomesRepo.save(new ExpensesAndIncomesList(new Categories(2L), 210d, LocalDateTime.of(2017, Month.MAY, 17, 22, 15), "week2"));
        expensesAndIncomesRepo.save(new ExpensesAndIncomesList(new Categories(2L), 220d, LocalDateTime.of(2017, Month.MAY, 1, 23, 0), "month1"));
        expensesAndIncomesRepo.save(new ExpensesAndIncomesList(new Categories(2L), 230d, LocalDateTime.of(2017, Month.MAY, 2, 15, 25), "month2"));
        expensesAndIncomesRepo.save(new ExpensesAndIncomesList(new Categories(2L), 240d, LocalDateTime.of(2017, Month.MAY, 3, 2, 3), "month3"));
        expensesAndIncomesRepo.save(new ExpensesAndIncomesList(new Categories(4L), 400d, LocalDateTime.now(), "comment4"));
        ExpensesAndIncomesList e = new ExpensesAndIncomesList(new Categories(4L), 400d, LocalDateTime.now(), "comment4");
        // System.out.println(e.getLocalDateTime());
    }
}
