import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Shop {
    private String name;
    private String webAddress;
    private String supportNumber;
    private List<User> users;
    private List<Admin> admins;
    private List<Seller> watingSellers;
    private List<Seller> sellers;
    private List<Product> products;
    private List<Sale> sales;
    private HashMap<User, Double> chargeRequests;
    private double profit;
    private List<Category> categories;

    public Shop(String name, String webAddress, String supportNumber) {
        this.name = name;
        this.webAddress = webAddress;
        this.supportNumber = supportNumber;
        this.users = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.sellers = new ArrayList<>();
        this.watingSellers = new ArrayList<>();
        this.products = new ArrayList<>();
        this.sales = new ArrayList<>();
        this.chargeRequests = new HashMap<>();
        this.profit = 0;
        this.categories = new ArrayList<>();
    }

    // add
    public void addUser(User user) {
        this.users.add(user);
    }

    public void addAdmin(Admin admin) {
        this.admins.add(admin);
    }

    public void addSeller(Seller seller) {
        this.sellers.add(seller);
    }

    public void addSales(Sale sale) {
        this.sales.add(sale);
    }

    public void addWatingSellers(Seller seller) {
        this.watingSellers.add(seller);
    }

    public void removeWatingSellerAndAddtoSellers(Seller seller) {
        this.watingSellers.remove(seller);
        this.sellers.add(seller);
    }

    public void removeChargeRequest(User user) {
        this.chargeRequests.remove(user);
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }
    
    public void addChargeRequest(double charge, User user) {
        this.chargeRequests.put(user, charge);
    }

    public void addProfit(double profit) {
        this.profit += profit;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }


    // get
    public String getName() {
        return this.name;
    }

    public String getWebAddress() {
        return this.webAddress;
    }

    public String getSupportNumber() {
        return this.supportNumber;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public List<Seller> getSellers() {
        return this.sellers;
    }

    public List<Seller> getWatingSellers() {
        return this.watingSellers;
    }

    public List<Admin> getAdmins() {
        return this.admins;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public double getProfit() {
        return this.profit;
    }

    public HashMap<User, Double> getChargeRequests() {
        return this.chargeRequests;
    }

    public List<Sale> getSales() {
        return this.sales;
    }

    public List<Category> getCategories() {
        return this.categories;
    }

}