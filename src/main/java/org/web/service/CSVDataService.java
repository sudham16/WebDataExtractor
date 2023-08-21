package org.web.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.web.model.CsvData;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class CSVDataService {
    public Map<String, CsvData> readCsvData(String csvFilePath) throws  IOException {
        Map<String,CsvData> csvDataMap = new LinkedHashMap<>();

        try (Reader reader = new FileReader(csvFilePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.builder().setDelimiter(',').setHeader().setSkipHeaderRecord(true).build())) {
                for (CSVRecord csvRecord : csvParser) {
                    CsvData csvData = new CsvData();
                    csvData.setAppName(csvRecord.get(0));
                    csvData.setHostName(csvRecord.get(1));
                    csvData.setVersion(csvRecord.get(2));
                    csvData.setMode(csvRecord.get(3));
                    csvDataMap.put(csvRecord.get(0), csvData);

            }
        }

        return csvDataMap;
    }
}
