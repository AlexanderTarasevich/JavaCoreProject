package TMS.test;

import TMS.model.Transaction;
import TMS.util.Parser;
import org.junit.*;

import static org.junit.Assert.*;


public class TransactionTest {

    @Test
    public void testTransactionParsing() {
        String validLine = "12345-67890|09876-54321|1000";
        Transaction transaction = Parser.parseLine(validLine);

        assertEquals("12345-67890", ((Transaction) transaction).getFromAccount());
        assertEquals("09876-54321", transaction.getToAccount());
        assertEquals(1000, transaction.getAmount());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testTransactionParsingWithInvalidAccountNumber() {
        String invalidLine = "1234-67890|09876-5432|1000";
        Parser.parseLine(invalidLine);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTransactionParsingWithNegativeAmount() {
        String invalidLine = "12345-67890|09876-54321|-1000";
        Parser.parseLine(invalidLine);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTransactionParsingWithInsufficientData() {
        String invalidLine = "12345-67890|1000";
        Parser.parseLine(invalidLine);
    }
}