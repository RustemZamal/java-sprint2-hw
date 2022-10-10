
/*Получил подскадзку. После считование файлов
делаю их объектами класса MonthlyReport and YearlyReport */

import java.util.Scanner;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        ArrayList<MonthlyReport> monthsReport = new ArrayList<>();
        ArrayList<YearlyReport> yearsReport = new ArrayList<>();
        MonthName mName = new MonthName();
        ComparisonMonthToYear comparosonMonthToYear = new ComparisonMonthToYear();
        Scanner scanner = new Scanner(System.in);



        while (true) {

            printMenu();
            System.out.print("Выберите команду: ");
            int command = scanner.nextInt();

            if (command == 1) {

                for(int i = 1; i <= 3; i++) {
                    monthsReport.add(new MonthlyReport("resources/m.20210" + i + ".csv"));
                }
                System.out.println("Данные успешно считаны!");
                System.out.println("-".repeat(50));
                System.out.println();

            } else if (command == 2) {

                yearsReport.add(new YearlyReport("resources/y.2021.csv"));
                System.out.println("Данные успешно считаны!");
                System.out.println("-".repeat(50));
                System.out.println();

            } else if (command == 3) {

                if (yearsReport.size() == 0) {
                    System.out.println("Похоже не все данные были считаны!");
                } else if (monthsReport.isEmpty()) {
                        System.out.println("Похоже не все данные были считаны!");
                    } else {
                        for (int i = 0; i < monthsReport.size(); i++) {
                            comparosonMonthToYear.compareMonthToYear(monthsReport.get(i), yearsReport.get(0), i);
                        }
                    System.out.println("Отчеты успешно прошли проверку");
                    }
                System.out.println("-".repeat(50));
                System.out.println();


            } else if (command == 4) {

                if (monthsReport.size() == 0) {
                    System.out.println("Не возможно вывсти информацию о месечных отчётов! Так как онм не были считаны");
                } else {
                    System.out.println();
                    for (int i = 0; i < monthsReport.size(); i++) {
                        MonthlyReport.showMonthStatistic(monthsReport.get(i), mName.monthName[i]);
                    }
                }
                System.out.println("-".repeat(50));
                System.out.println();

            } else if (command == 5) {

                if (yearsReport.isEmpty()) {
                    System.out.println("Не возможно вывсти информацию о годовам отчёте! Так как он не был считан");
                } else {
                    System.out.println();
                    YearlyReport.showYearlyStatistic(yearsReport.get(0), "resources/y.2021.csv");
                }
                System.out.println("-".repeat(50));
                System.out.println();

            } else if (command == 0) {

                System.out.println("Вы вышли из приложения");
                return;

            } else {
                System.out.println("Такой команды нет! Выберите правильную команду");
            }
        }
    }

    public static void printMenu() {
        System.out.println("1 - Считать все месячные отчеты");
        System.out.println("2 - Считать годовой отчет");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход из приложения");
    }
}
