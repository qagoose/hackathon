package com.qagoose.hackathon.traditional;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class DataLoader {
    public HashMap<String, RecentTransactionRow> loadRecentTransactions(String resourceName) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(getClass().getClassLoader().getResource(resourceName).getFile()));
        RecentTransactions transactions = gson.fromJson(reader, RecentTransactions.class);
        HashMap<String, RecentTransactionRow> keyedRows = new HashMap<>();

        for(RecentTransactionRow row : transactions.getTransactions()) {
            keyedRows.put(row.getDescription(), row);
        }

        return keyedRows;
    }
}
