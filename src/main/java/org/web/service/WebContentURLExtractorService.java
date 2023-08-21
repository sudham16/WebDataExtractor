package org.web.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebContentURLExtractorService {

    @Value("${web.url}")
    private String webUrl;

    @Value("${edge.driver.loc}")
    private String edgeDriverLoc;

    public void extractDataContent() throws IOException {
        String csvFilePath = "data.csv";
        System.setProperty("webdriver.edge.driver",edgeDriverLoc);
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new EdgeDriver(options);
        driver.get(webUrl);
       /* WebElement button = driver.findElement(By.id(""));
        button.click();
        WebElement confirmButton = driver.findElement(By.id(""));
        confirmButton.click();*/

        Document pageDoc = Jsoup.parse(driver.getPageSource());
        Elements table =pageDoc.select("table[class=display]");
        Elements rows = table.select("tr");
        List<List<String>> resultsList =  new ArrayList<>();
        //System.out.println("Content is "+ rows);
        rows.stream().forEach(
                it -> {
                    Elements cols = it.select("td");

                    if(cols.size() >0 ) {
                        List<String> values = cols.stream().map(it1 -> it1.text()).collect(Collectors.toList());
                        if(!values.isEmpty() && !values.get(0).contains("_***_")) {
                            resultsList.add(values);
                        }

                    }

                }

        );
        System.out.println(resultsList);

        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFilePath))) {
            writer.println("App Name,Host name,Version,Mode");
            resultsList.forEach(listOfStr -> writer.println(listOfStr));
            System.out.println("Data has been written to the CSV file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.quit();


    }
}
