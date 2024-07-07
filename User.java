import java.util.HashMap;

public class User {
    protected String username;
    protected String password;
    protected String email;
    protected String phoneNumber;
    protected String address;
    protected HashMap<Product, Integer> shoppingCart;
    protected HashMap<Product, Integer> watingProducts;
    protected HashMap<Product, Integer> orderedProducts;
    protected double wallet;

    public User(String username, String password, String email, String phoneNumber, String address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.shoppingCart = new HashMap<>();
        this.orderedProducts = new HashMap<>();
        this.watingProducts = new HashMap<>();
        this.wallet = 0;
    }

    // for admins
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.wallet = 0;
    }

    // for sellers
    public User(String username, String password, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.wallet = 0;
    }

    // get
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public HashMap<Product, Integer> getShoppingCart() {
        return this.shoppingCart;
    }

    public HashMap<Product, Integer> getOrderedProducts() {
        return this.orderedProducts;
    }

    public HashMap<Product, Integer> getWaitingProducts() {
        return this.watingProducts;
    }
    
    public double getWallet() {
        return this.wallet;
    }

    public String getAddress() {
        return this.address;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    // set
    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public void clearShoppingCart() {
        this.shoppingCart.clear();
    }

    public void clearWaitingProduct() {
        this.watingProducts.clear();
    }

    public void Charge(double charge) {
        this.wallet += charge;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    // add
    public void addShoppingCart(Product product, int number) {
        this.shoppingCart.put(product, number);
    }

    public void addOrderedProducts(Product product, int number) {
        this.orderedProducts.put(product, number);
    }

    public void addWaitingProducts(Product product, int number) {
        this.watingProducts.put(product, number);
    }

    public void addWallet(Double price) {
        this.wallet += price;
    }
}