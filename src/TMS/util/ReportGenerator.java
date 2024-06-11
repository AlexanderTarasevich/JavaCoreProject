package TMS.util;

import TMS.model.Transaction;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ReportGenerator {
    public static void generateReport(Transaction transaction, boolean success, String failName, String errorMessage) throws IOException {
        List<String> reportLines = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String status = success ? "успешно обработан" : "ошибка во время обработки, " + errorMessage;
        String reportLine = LocalDateTime.now().format(formatter) + " | " + failName + " перевод с " + transaction.getFromAccount() + " на  " + transaction.getToAccount() + " сумма перевода: " + transaction.getAmount() + " | " + status;

        reportLines.add(reportLine);
        FileHandler.appendFile("report.txt", reportLines);

    }
}
