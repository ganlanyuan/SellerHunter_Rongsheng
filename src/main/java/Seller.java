/**
 * Created by designer01 on 6/7/17.
 */
public class Seller {
    private int price;
    private int positive;
    private int totalRating;
    private boolean isPrime = false;

    public Seller(int price, int positive, int totalRating, boolean isPrime) {
        this.price = price;
        this.positive = positive;
        this.totalRating = totalRating;
        this.isPrime = isPrime;
    }

    public Seller() {}

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

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

    public boolean isPrime() {
        return isPrime;
    }

    public void setPrime(boolean prime) {
        isPrime = prime;
    }
}
