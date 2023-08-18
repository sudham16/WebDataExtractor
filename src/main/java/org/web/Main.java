package org.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        String webUrl = "file:///C:/Users/sudha/Downloads/test.html";
        String csvFilePath = "data.csv";
        System.setProperty("webdriver.edge.driver","C:\\Users\\sudha\\Downloads\\edgedriver_win64\\msedgedriver.exe");

        WebDriver driver = new EdgeDriver();
        driver.get(webUrl);
      /*  WebElement button = driver.findElement(By.id(""));
        button.click();
        WebElement confirmButton = driver.findElement(By.id(""));
        confirmButton.click();*/
        Document pageDoc = Jsoup.parse(driver.getPageSource());
        Elements table =pageDoc.select("table");
        Elements rows = table.select("tr");
        List<List<String>> resultsList =  new ArrayList<>();
        //System.out.println("Content is "+ rows);
        rows.stream().forEach(
                it -> {
                    Elements cols = it.select("td");

                    if(cols.size() >0 ) {
                        List<String> values = cols.stream().map(it1 -> it1.text()).collect(Collectors.toList());
                        resultsList.add(values);

                    }

                }

        );
        System.out.println(resultsList);

        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFilePath))) {
            resultsList.forEach(listOfStr -> writer.println(String.join(",", listOfStr)));


            System.out.println("Data has been written to the CSV file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.quit();



    }
}