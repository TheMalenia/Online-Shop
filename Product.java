import java.util.ArrayList;
import java.util.List;

public class Product {
    protected  String name;
    protected String caption;
    private int inventory;
    protected double price;
    private List<String> comments;
    protected Seller seller;
    // Category

    public Product(String name, String caption, int inventory, double price, Seller seller) {
        this.name = name;
        this.caption = caption;
        this.inventory = inventory;
        this.price = price;
        this.comments = new ArrayList<>();
        this.seller = seller;
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
}