import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;


public class YearlyReport {
    static MonthName mName = new MonthName();
    public HashMap<Integer, YearlyReportData> data = new HashMap<>();

    public YearlyReport(String path) {
        String text = readFileContentsOrNull(path);
        String[] lines = text.split("\r?\n");
        // считаваю с 1 так как в начале файлов заголовки
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);
            if (!data.containsKey(month)) {
                data.put(month, new YearlyReportData(month));
            }
            YearlyReportData mRecord = data.get(month);
            if (isExpense) {
                mRecord.expenses += amount;
            } else {
                mRecord.income += amount;
            }
        }
    }

    public static void showYearlyStatistic(YearlyReport yearsReport, String path) {
        String[] lines = path.split("\\.");

        int year = Integer.parseInt(lines[1]);
        System.out.println("Расматрееваймый год: " +  year);
        int avarageIncome = 0;
        int avarageExpense = 0;
        for (int i = 1; i <= yearsReport.data.size(); i++) {
            int profit = yearsReport.data.get(i).income - yearsReport.data.get(i).expenses;
            avarageIncome += yearsReport.data.get(i).income;
            avarageExpense += yearsReport.data.get(i).expenses;
            System.out.println("Прибыль за " + mName.monthName[i - 1] + ": " + profit);
        }
        System.out.println("Средний расход за все месяцы в году: " + avarageExpense/ yearsReport.data.size());
        System.out.println("Ссредний доход за все мяцы в году: " + avarageIncome/ yearsReport.data.size());
    }


    private String readFileContentsOrNull(String path)
    {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

}