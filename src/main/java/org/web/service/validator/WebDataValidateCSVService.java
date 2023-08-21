package org.web.service.validator;

import org.springframework.stereotype.Service;
import org.web.model.CsvData;

import java.util.Map;

@Service
public class WebDataValidateCSVService {

    public void validateAndUpdateSourceWithError(Map<String, CsvData> sourceCSVDataMap,  Map<String, CsvData> destinationCSVDataMap){

        sourceCSVDataMap.forEach( (key,val) -> {
            if(!(destinationCSVDataMap.get(key) != null && destinationCSVDataMap.get(key).toString().equalsIgnoreCase(val.toString()))){
                val.setError(true);
            }
        });



    }


}
