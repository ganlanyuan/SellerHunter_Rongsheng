import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.util.List;

/**
 * Created by designer01 on 6/6/17.
 */
public class AmazonSellerHunter {
    private static final String BASEURL = "https://www.amazon.com/gp/offer-listing/";
    private int positive = 90;
    private int totalRating = 100;

    public int getPositive() {
        return positive;
    }

    public void setPositive(int positive) {
        this.positive = positive;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public static void main(String[] args) {
        AmazonSellerHunter hunter = new AmazonSellerHunter();
        hunter.getBestSellers();
    }

    public static void getBestSellers() {
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        File products = new File("document/products.txt");
        WriteFile bestSellers = new WriteFile();
        bestSellers.setFile("document/bestSellers.txt");

        String str = "Best Sellers for the following products: \n";
        bestSellers.setData(str);
        bestSellers.run();

        try {
            FileReader fr = new FileReader(products);
            BufferedReader bf = new BufferedReader(fr);

            String line;
            while ((line = bf.readLine()) != null) {
                driver.get(BASEURL + line);
                driver.get("https://www.amazon.com/gp/offer-listing/B0087I8KTK");
                List<WebElement> page = driver.findElements(By.id("olpOfferListColumn"));
                List<WebElement> pagination = driver.findElements(By.cssSelector(".a-pagination > li"));

                if (page.size() > 0) {
                    if (pagination.size() > 0) {
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
