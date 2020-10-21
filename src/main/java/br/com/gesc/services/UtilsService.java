package br.com.gesc.services;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UtilsService {
    public void createCSVFile(List<String> headers, List<String> records, String fileName)
            throws IOException {
        FileWriter out = new FileWriter(StringUtils.join(fileName, ".csv"));
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
                .withHeader(headers.toArray(new String[0])))) {
            records.forEach(record -> {
                try {
                    printer.printRecord(record);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
