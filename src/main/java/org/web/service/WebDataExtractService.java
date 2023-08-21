package org.web.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web.model.CsvData;
import org.web.service.validator.WebDataValidateCSVService;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
@Service
public class WebDataExtractService {
    private final CSVDataService csvDataService;
    private final WebContentURLExtractorService webContentExtractorService;
    private final WebDataValidateCSVService validateCSV;

    @Value("${destination.csv}")
    private String destinationCSV;

    public WebDataExtractService(CSVDataService csvDataService, WebContentURLExtractorService webContentExtractorService, WebDataValidateCSVService webDataValidateCSVService){
        this.csvDataService = csvDataService;
        this.webContentExtractorService = webContentExtractorService;
        this.validateCSV = webDataValidateCSVService;
    }

    public Collection<CsvData> validateAndExtractData() throws IOException {
        webContentExtractorService.extractDataContent();
        Map<String, CsvData> sourceCSVDataMap = this.csvDataService.readCsvData("data.csv");
        Map<String, CsvData> destinationCSVDataMap = this.csvDataService.readCsvData(destinationCSV);
        this.validateCSV.validateAndUpdateSourceWithError(sourceCSVDataMap,destinationCSVDataMap);
        return sourceCSVDataMap.values();


    }
}
