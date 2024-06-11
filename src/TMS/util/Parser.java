package TMS.util;

import TMS.model.Transaction;

public class Parser {
    public static Transaction parseLine(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 3) {
            throw new IllegalArgumentException("В строке недостаточно данных для парсинга!");
        }
        return new Transaction(parts[0], parts[1], Integer.parseInt(parts[2]));
    }
}
