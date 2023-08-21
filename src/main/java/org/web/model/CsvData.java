package org.web.model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class CsvData {

    private String appName;
    private String hostName;
    private String version;
    private String mode;

    private boolean isError;

    @Override
    public String toString() {
        return appName+hostName+version+mode;
    }
}

