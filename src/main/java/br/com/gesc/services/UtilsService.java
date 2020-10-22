package br.com.gesc.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.List;

public class UtilsService {
    public void createCSVFile(List<String> headers, List<?> records, String fileName)
            throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(StringUtils.join(fileName, ".csv")),
                "Cp1252"));
        fillHeaderWithSemiColon(headers, out);

        try (CSVPrinter printer = new CSVPrinter(out,
                CSVFormat.EXCEL
                        .withEscape('\"')
                        .withQuoteMode(QuoteMode.NONE)
        )
        ) {
            records.forEach(record -> {
                try {
                    printer.printRecord(record);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void exportToFile(List<String> headers, List<?> records, String fileName)
            throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(StringUtils.join(fileName, ".csv")),
                "ISO-8859-1"));
        fillHeaderWithSemiColon(headers, out);

        {
            records.forEach(record -> {
                try {
                    out.write(StringUtils.join(record.toString(), "\n"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        out.flush();
        out.close();
    }

    public void exportToJson(List<?> records, String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonArray recordsJson = gson.toJsonTree(records).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("root", recordsJson);

        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(StringUtils.join(fileName, ".json"))));
            out.write(jsonObject.toString());
            out.flush();
            out.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillHeaderWithSemiColon(List<String> headers, BufferedWriter out) throws IOException {
        for (int i = 0; i < headers.size(); i++) {
            out.write(headers.get(i).concat("|"));
        }
        out.write("\n");
    }
}
