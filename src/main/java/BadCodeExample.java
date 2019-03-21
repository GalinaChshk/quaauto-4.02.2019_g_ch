import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class BadCodeExample {
    public static void main(String[] args) {
        System.out.println("Hello, world ;)");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\25na8\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");

        String searchTerm = "Selenium";

        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("Selenium");
        searchField.sendKeys(Keys.RETURN);

        List<WebElement> searchResults = driver.findElements(By.xpath("//div[@class='srg']/div[@class='g']"));

        int iterator = 0;
        for (WebElement searchResult : searchResults) {
            iterator++;
            String searchResultString = searchResult.getText();

            System.out.println("Result number: " + iterator);
            System.out.println(searchResult.getText());

            if(searchResultString.contains("Selenium")) {
                System.out.println("SearchTerm found");
            } else {
                System.out.println("SearchTerm not found");

            }
        }
    }
}
