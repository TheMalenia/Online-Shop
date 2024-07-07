import java.util.ArrayList;
import java.util.List;

public class Seller extends User{
    public String walletAddress;
    public int confirmation;
    public String caption;
    public List<Product> products;

    public Seller(String username, String password, String email, String phoneNumber, String walletAddress, String caption) {
        super(username, password, email, phoneNumber);
        this.confirmation = 0;
        this.walletAddress = walletAddress;
        this.products = new ArrayList<>();
        this.caption = caption;
    }

    //add
    public void addProduct(Product product) {
        this.products.add(product);
    }

    // get
    public String getCompanyName() {
        return this.username;
    }

    public int getConfirmation() {
        return this.confirmation;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    // set
    public void setConfirmation() {
        this.confirmation = 1;
    }
}