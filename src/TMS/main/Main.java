package TMS.main;

import TMS.model.Transaction;
import TMS.service.ArchiveService;
import TMS.service.TransferService;
import TMS.util.FileHandler;
import TMS.util.Parser;
import TMS.util.ReportGenerator;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        TransferService transferService = new TransferService();
        ArchiveService archiveService = new ArchiveService();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите операцию:");
        System.out.println("1 - вызов операции парсинга файлов перевода из input");
        System.out.println("2 - вызов операции вывода списка всех переводов из файла-отчета");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                File folder = new File("input");

                File[] listOfFiles = folder.listFiles();

                for (File file : listOfFiles) {
                    if (file.isFile()) {
                        List<String> lines = FileHandler.readFile(file.getPath());

                        for (String line : lines) {
                            if (line == null || line.trim().isEmpty()) {
                                System.out.println("Ошибка: пустая строка в файле !" + file.getName());
                                continue;
                            }

                            Transaction transaction = null;
                            try {

                                transaction = Parser.parseLine(line);
                                transferService.transfer(transaction);
                                ReportGenerator.generateReport(transaction, true, file.getName(), null);

                            } catch (Exception e) {
                                System.out.println("Ошибка при обработке строки: " + line);
                                e.printStackTrace();
                                ReportGenerator.generateReport(transaction, false, file.getName(), e.getMessage());

                            }

                        }
                        archiveService.archiveFile(file);
                    }
                }

                break;
            case 2:
                File reportFile = new File("report.txt");
                if (reportFile.length() == 0) {
                    System.out.println("Файл-отчет пуст!");
                } else {
                    List<String> reportLines = FileHandler.readFile("report.txt");
                    for (String reportLine : reportLines) {
                        System.out.println(reportLine);
                    }
                }
                break;
            default:
                System.out.println("Неверный выбор операции.");
        }
    }
}
