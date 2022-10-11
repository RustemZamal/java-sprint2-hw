
import java.util.HashMap;

public class MonthlyReport {

    HashMap<String, MonthData> monthData = new HashMap<>();
    FileReader fileReader = new FileReader();

    public MonthlyReport (String path) {
        String content = fileReader.readFileContentsOrNull(path);
        if ((content == null) || (content.equals(""))) {
            System.out.println("В файле отсуствуют данные!");
            return;
        }
            String[] lines = content.split(System.lineSeparator());


            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];
                String[] tokens = line.split(",");
                String itemName = tokens[0];
                boolean isExpense = Boolean.parseBoolean(tokens[1]);
                int quantity = Integer.parseInt(tokens[2]);
                int sumOfOne = Integer.parseInt(tokens[3]);
                if (!monthData.containsKey(itemName)) {
                    // создал пустой класс в котором поля будут значениями  monthData
                    monthData.put(itemName, new MonthData());
                }
                MonthData mData = monthData.get(itemName); // достаю поля и присваеваю им значения
                if (isExpense) {
                    mData.expense += quantity * sumOfOne;
                } else {
                    mData.income += quantity * sumOfOne;
                }
            }

    }
    public static void showMonthStatistic(MonthlyReport monthsReport, String monthName) {
        int maxIncome = 0;
        int maxExpense = 0;
        String nameMaxExpense = "";
        String nameMaxProfItem = "";
        System.out.println(monthName + ":");
        for (String itemName : monthsReport.monthData.keySet()) {
            MonthData Mdata = monthsReport.monthData.get(itemName);
            if (Mdata.income > maxIncome) {
                maxIncome = Mdata.income;
                nameMaxProfItem = itemName;
            } else if (Mdata.expense > maxExpense) {
                maxExpense = Mdata.expense;
                nameMaxExpense = itemName;
            }
        }
        System.out.println("Самый прибыльный товар: " + nameMaxProfItem + ". Доход: " + maxIncome);
        System.out.println("Самая большая трата: " + nameMaxExpense + ". Потрачено: " + maxExpense);
        System.out.println();
    }
}