package org.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String webUrl = "";
        Document doc = Jsoup.connect(webUrl).get();
        System.out.println(doc);

        //Selenium
        System.setProperty("webdriver.edge.driver","");
        WebDriver driver = new EdgeDriver();
        driver.get(webUrl);
        Document pageDoc = Jsoup.parse(driver.getPageSource());
        Elements tables =pageDoc.select("table");
        System.out.println("Content is "+ tables);
        driver.quit();



    }
}