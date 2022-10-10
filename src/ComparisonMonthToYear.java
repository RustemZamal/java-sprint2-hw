public class ComparisonMonthToYear {

     MonthName mName = new MonthName(); //

    /**
     *
     * @param monthsReport
     * ArrayList monthsReport типа MonthlyReport это объектом класса MonthlyReport в котором monthData
     * это поле, а monthData это HashMap<String, MonthData> являеющиеся объектом класса MonthData.
     *
     * @param yearsReport
     *  ArrayList yearsReport типа YearlyReport это объектом класса YearlyReport в котором data
     *   это поле, а data это HashMap<Integer, YearlyReportData> являеющиеся объектом класса YearlyReportData
     * @param i
     *  переменная i цыкла for используется как ключ в HashMap<Integer, YearlyReportData> data;
     *  так как ключами являются цыфры. Так же i используется чтобы пройтись по массиву monthName
     *  который является полем объекта mName класса MonthName (Возможно я не правильно описываю)
     *
     */
      void compareMonthToYear(MonthlyReport monthsReport, YearlyReport yearsReport, int i) {
            int monthsIncome = getMonthTotalIncome(monthsReport); // возвращается сумарный доход покаждому месяцу
            int monthsExpense = getMonthTotalExpense(monthsReport); // возвращается сумарный расход покаждому месяцу
            int yearlyIncomeByMonth = 0;
            int yearlyExpenseByMonth = 0;
            YearlyReportData monthKey = yearsReport.data.get(i + 1);
            yearlyIncomeByMonth = monthKey.income;
            yearlyExpenseByMonth = monthKey.expenses;

            if (monthsIncome != yearlyIncomeByMonth) {
                System.out.println("Данные по доходам за " + mName.monthName[i] + " не совпали");
            }
            if (monthsExpense != yearlyExpenseByMonth) {
                System.out.println("Данные по расходам за " + mName.monthName[i] + " не совпали");
            }
        }


      int getMonthTotalIncome(MonthlyReport monthsReport) {
         int monthsIncome = 0;
         for (String key : monthsReport.monthData.keySet()) {
             MonthData itemName = monthsReport.monthData.get(key);
             monthsIncome += itemName.income;
         }
         return monthsIncome;

     }

     int getMonthTotalExpense(MonthlyReport monthsReport) {
        int monthsExpense = 0;
        for (String key : monthsReport.monthData.keySet()) {
            MonthData itemName = monthsReport.monthData.get(key);
            monthsExpense += itemName.expense;
        }
        return monthsExpense;
    }
}
