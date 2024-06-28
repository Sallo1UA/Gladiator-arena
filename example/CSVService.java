package org.example;

import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CSVService {
    public void writeToCSV(Message msg, String lang) {
        try (FileWriter writer = new FileWriter("data.csv", true)) {
            StringBuilder sb = new StringBuilder();
            Date date = new Date(msg.getDate() * 1000L);
            String dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss - Z").format(date);
            sb.append(msg.getChatId())
                    .append(",")
                    .append(msg.getChat().getUserName())
                    .append(",")
                    .append(dateFormat)
                    .append(",")
                    .append(lang)
                    .append("\n");
            writer.append(sb.toString());
            writer.close();
            System.out.println("done");

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public List<List<String>> readCSV() {
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("data.csv"))) {
            while(scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return records;
    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }
}
