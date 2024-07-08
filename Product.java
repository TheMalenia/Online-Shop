import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Product {
    protected  String name;
    protected String caption;
    private int inventory;
    protected double price;
    private List<String> comments;
    protected Seller seller;
    private Category category;
    private String value1, value2; // for Category Dynamic Data!

    public Product(String name, String caption, int inventory, double price, Seller seller, Category category, String value1, String value2) {
        this.name = name;
        this.caption = caption;
        this.inventory = inventory;
        this.price = price;
        this.comments = new ArrayList<>();
        this.category = category;
        this.seller = seller;
        this.value1 = value1;
        this.value2 = value2;
    }
    
    public void print() {

    }

    // For sale
    public Product(String name, String caption, double price, Seller seller) {
        this.name = name;
        this.caption = caption;
        this.price = price;
        this.seller = seller;
    }

    // add
    public void addCommnet(String comment) {
        this.comments.add(comment);
    }
    // set
    public void removeInventory(int number) {
        this.inventory -= number;
    }

    // get
    public String getName() {
        return this.name;
    }

    public String getCaption() {
        return this.caption;
    }
    public double getPrice() {
        return this.price;
    }

    public int getInventory() {
        return this.inventory;
    }

    public List<String> getComments() {
        return this.comments;
    }

    public Seller getSeller() {
        return this.seller;
    }

    public Category getCategory() {
        return this.category;
    }

    public String getValue1() {
        return this.value1;
    }

    public String getValue2() {
        return this.value1;
    }
}