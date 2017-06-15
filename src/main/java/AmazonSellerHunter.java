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
    private static final String AMAZONURL = "https://www.amazon.com";
    private static final String BASEURL1 = "https://www.amazon";
    private static final String BASEURL2 = "/gp/offer-listing/";
    private static final String PRODUCTPATH = "document/products.txt";
    private static final String BESTSELLERPATH = "document/bestSellers.txt";
    private static String urlPostfix = ".com";
    private static int positive = 90;
    private static int totalRating = 100;

    public int getPositive() {
        return positive;
    }
    public void setPositive(int positive) {
        this.positive = positive;
    }
    public int getTotalRating() { return totalRating; }
    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public AmazonSellerHunter(String str, int pos, int rating) {
        urlPostfix = str;
        positive = pos;
        totalRating = rating;
    }
    public AmazonSellerHunter() {
    }

    public static void main(String[] args) {
        AmazonSellerHunter hunter = new AmazonSellerHunter();
        hunter.searchPages();
    }

    public static void searchPages() {
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        File products = new File(PRODUCTPATH);
        WriteFile wf = new WriteFile();
        wf.setFile(BESTSELLERPATH);

        String data = "Best Sellers for the following products: \n";
        wf.setData(data);
        wf.write(false);

        try {
            FileReader fr = new FileReader(products);
            BufferedReader bf = new BufferedReader(fr);

            String productId;
            while ((productId = bf.readLine()) != null) {
                String bestSellerName = "";
                String bestSellerUrl = "";
                double price = 0;

                String url = BASEURL1 + urlPostfix + BASEURL2 + productId;
                while (url != "") {
                    driver.get(url);

                    List<WebElement> allSellers = driver.findElements(By.className("olpOffer"));
                    for (WebElement seller : allSellers) {
                        // get positive and total ratings
                        int positiveNum = 0;
                        int ratingNum = 0;
                        if (seller.findElements(By.cssSelector(".olpSellerColumn p")).size() > 0) {
                            String str = seller.findElement(By.cssSelector(".olpSellerColumn p")).getText();
                            int percentageIndex = str.indexOf('%');
                            int ratingIndex1 = str.indexOf('(');
                            int ratingIndex2 = str.indexOf(" total ratings)");
                            positiveNum =  (percentageIndex != -1) ? Integer.parseInt(str.substring(0, percentageIndex)) : 0;
                            ratingNum = (ratingIndex1 != -1 && ratingIndex2 != -1) ? Integer.parseInt(str.substring(ratingIndex1 + 1, ratingIndex2).replaceAll(",", "")) : 0;
                        } else {
                            // Amazon
                            positiveNum = positive + 10;
                            ratingNum = totalRating + 10;
                        }

                        // get price
                        String priceString = seller.findElement(By.className("olpOfferPrice")).getText();
                        int dollarIndex = priceString.indexOf("$");
                        double productPrice = Double.parseDouble(priceString.substring(dollarIndex + 1).trim().replaceAll(",", ""));
                        double shippingPrice = 0;
                        double totalPrice;

                        // prime seller?
                        if (seller.findElements(By.cssSelector(".olpPriceColumn .supersaver")).size() > 0 && productPrice < 35) {
                            shippingPrice = 4.98;
                        } else if (seller.findElements(By.className("olpShippingPrice")).size() > 0) {
                            String priceString2 = seller.findElement(By.className("olpShippingPrice")).getText();
                            int dollarIndex2 = priceString2.indexOf("$");
                            shippingPrice = Double.parseDouble(priceString2.substring(dollarIndex2 + 1).trim().replaceAll(",", ""));
                        }
                        totalPrice = productPrice + shippingPrice;

                        // initialize price, best seller information
                        if (price == 0) {
                            price = totalPrice;

                            if (seller.findElements(By.cssSelector(".olpSellerName a")).size() > 0) {
                                WebElement sl = seller.findElement(By.cssSelector(".olpSellerName a"));
                                bestSellerName = sl.getText();
                                bestSellerUrl = sl.getAttribute("href");
                            } else {
                                bestSellerName = "Amazon";
                                bestSellerUrl = AMAZONURL;
                            }
                        }

                        // reset bestSeller if price is lower than current one
                        // and positive, total ratings are fit
                        if (positiveNum >= positive && ratingNum >= totalRating && totalPrice < price) {
                            if (seller.findElements(By.cssSelector(".olpSellerName a")).size() > 0) {
                                WebElement sl = seller.findElement(By.cssSelector(".olpSellerName a"));
                                bestSellerName = sl.getText();
                                bestSellerUrl = sl.getAttribute("href");
                            } else {
                                bestSellerName = "Amazon";
                                bestSellerUrl = AMAZONURL;
                            }
                        }
                    }

                    // has next page?
                    if (driver.findElements(By.className("a-pagination")).size() > 0 &&
                            driver.findElement(By.cssSelector(".a-pagination .a-selected + li")).getAttribute("class").indexOf("a-last") == -1) {
                        url = driver.findElement(By.cssSelector(".a-pagination .a-selected + li a")).getAttribute("href");
                    } else {
                        url = "";
                    }
                }

                data = "\nProduct ID: " + productId + "\n" + bestSellerName + ": " + bestSellerUrl + "\n";
                wf.setData(data);
                wf.write(true);
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

