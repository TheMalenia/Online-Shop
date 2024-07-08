public class Sale extends Product {
    private int number;
    private User user;

    public Sale(String name, String caption, int number, double price, Seller seller, User user) {
        super(name, caption, price, seller);
        this.number = number;
        this.user = user;
    }

    @Override
    public void print() {
        System.out.println("Name: " + this.name + ", Caption: " + this.caption + ", Number: " + this.number + ", Price: " + this.price);
    }

    // get
    public User getUser() {
        return this.user;
    }

}