import java.util.ArrayList;
import java.util.List;

public class Seller extends User{
    private String walletAddress;
    private int confirmation;
    private String caption;
    private List<Product> products;
    private List<Sale> sales;

    public Seller(String username, String password, String email, String phoneNumber, String walletAddress, String caption) {
        super(username, password, email, phoneNumber);
        this.confirmation = 0;
        this.walletAddress = walletAddress;
        this.products = new ArrayList<>();
        this.caption = caption;
        this.sales = new ArrayList<>();

    }

    //add
    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void addSales(Sale sale) {
        this.sales.add(sale);
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

    public String getCaption() {
        return this.caption;
    }

    public List<Sale> getSales() {
        return this.sales;
    }

    // set
    public void setConfirmation() {
        this.confirmation = 1;
    }

    // remove
    public void removeProduct(Product product) {
        this.products.remove(product);
    }
}