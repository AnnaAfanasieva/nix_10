package ua.com.alevel.util.csv;

import ua.com.alevel.persistence.entity.Transaction;

import java.util.Set;

public final class ConvertingSetToString {

    private static final String SEPARATOR = ",";

    private ConvertingSetToString() { }

    public static String setTransactionsToString(Set<Transaction> transactions) {
        StringBuilder finalResult = new StringBuilder();
        for (Transaction transaction : transactions) {
            finalResult.append(transaction.getId()).append(SEPARATOR);
            finalResult.append(transaction.getCreated()).append(SEPARATOR);
            finalResult.append(transaction.getAccount().getUser().getUserName()).append(SEPARATOR);
            finalResult.append(transaction.getCategory().getCategoryName()).append(SEPARATOR);
            finalResult.append(transaction.getSum()).append("\n");
        }
        return finalResult.toString();
    }
}
