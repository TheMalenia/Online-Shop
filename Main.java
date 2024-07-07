
import java.awt.SystemTray;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void print(Object s) {
        System.out.println(s);
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }

    public static void Register(Shop shop) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Register for: ");
        System.out.println("1. User");
        System.out.println("2. Seller");
        System.out.println("0. back");

        int status = scanner.nextInt();
        scanner.nextLine();

        clear();
        if(status == 0 || (status != 2 && status != 1)) {
            System.out.println("Bad status. Register failed.");
            return;
        }

        String username = "";
        while(true) { 
            boolean found = false;
            if(status == 1) {
                System.out.println("Enter Username:");
            }
            if(status == 2) {
                System.out.println("Enter Company Name (Username of your account):");
            }
            username = scanner.nextLine();
            if(status == 1){
                for(User user: shop.getUsers()) {
                    if(user.getUsername().equals(username)) {
                        found = true;
                        break;
                    }
                }
            }
            if(status == 2){
                for(Seller seller: shop.getSellers()) {
                    if(seller.getUsername().equals(username)) {
                        found = true;
                        break;
                    }
                }
            }
            if(!found) {
                break;
            }
            clear();
            if(status == 1) {
                System.out.println("Username is taken. Choose new one.");
            }
            if(status == 2) {
                System.out.println("Company Name is taken. Choose new one.");
            }
        }
        
        clear();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        clear();
        System.out.println("Enter Email:");
        String email = scanner.nextLine();

        clear();
        System.out.println("Enter phone number:");
        String phoneNumber = scanner.nextLine();

        

        if(status == 1) {
            clear();
            System.out.println("Enter address:");
            String address = scanner.nextLine();

            User user = new User(username, password, email, phoneNumber, address);
            shop.addUser(user);

            clear();
            System.out.println("Register Done.");
        }
        else if(status == 2) {
            clear();
            System.out.println("Enter what do you want to sell:");
            String caption = scanner.nextLine();

            clear();
            System.out.println("Enter walletAddress:");
            String walletAddress = scanner.nextLine();

            Seller seller = new Seller(username, password, email, phoneNumber, walletAddress, caption);
            shop.addWatingSellers(seller);

            clear();
            System.out.println("Register Done. Wait for admin confirmation.");
        }
        else{
            clear();
            System.out.println("Bad status. Register failed.");
        }
    }
    
    public static void Login(Shop shop){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Login as: ");
        System.out.println("1. a User");
        System.out.println("2. a Seller");
        System.out.println("3. an Admin");
        System.out.println("0. back");

        int status = scanner.nextInt();
        scanner.nextLine();
        if(status == 0) {
            clear();
            return;
        }

        clear();
        System.out.println("Enter Username:");
        String username = scanner.nextLine();

        clear();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        if(status == 1) {
            for(User user: shop.getUsers()) {
                if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    clear();
                    System.out.println("Logged in successfully");
                    UserMenu(shop, user);
                    return;
                }
            }
        }
        else if(status == 2){
            for(Seller seller: shop.getSellers()){
                if(seller.getUsername().equals(username) && seller.getPassword().equals(password)) {
                    clear();
                    System.out.println("Logged in successfully");
                    SellerMenu(shop, seller);
                    return;
                }
            }

            for(Seller seller: shop.getWatingSellers()){
                if(seller.getUsername().equals(username) && seller.getPassword().equals(password)) {
                    clear();
                    System.out.println("Logged in successfully");
                    SellerMenu(shop, seller);
                    return;
                }
            }

        }
        else if(status == 3){
            for(Admin admin: shop.getAdmins()){
                if(admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                    clear();
                    System.out.println("Logged in successfully");
                    AdminMenu(shop, admin);
                    return;
                }
            }
        }
        else {
            clear();
        }

        clear();
        System.out.println("Wrong Username or Password!");
        Login(shop);
    }

    public static void UserMenu(Shop shop, User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello " + user.getUsername());
        System.out.println("1. Search");
        System.out.println("2. Categories");
        System.out.println("3. Shopping Cart");
        System.out.println("4. Buy Shopping Cart Items");
        System.out.println("5. Charge wallet");
        System.out.println("6. Purchased products");
        System.out.println("7. Edit Account");
        System.out.println("8. Wating products");
        System.out.println("9. Add comment");
        System.out.println("10. Wallet");
        System.out.println("0. Log out");

        int status = scanner.nextInt();
        scanner.nextLine();

        if(status == 0) {
            clear();
            return;
        }
        
        else if(status == 1) {
            clear();
            Search(shop, user);
        }

        else if(status == 3) {
            shoppingCart(user);
        }

        else if(status == 4) {
            buyShoppingCart(shop, user);
        }

        else if(status == 5) {
            chargeWallet(shop, user);
        }

        else if(status == 6) {
            orderedProducts(user);
        }

        else if(status == 7) {
            clear();
            editAccount(shop, user);
        }
        
        else if(status == 8) {
            waitingProducts(user);
        }

        else if(status == 9) {
            addCommnet(shop, user);
        }

        else if(status == 10){
            clear();
            System.out.println("You have " + user.getWallet() + " dollors.");
        }

        else{
            System.out.println("Command not found! Try Again");
        }
        UserMenu(shop, user);
    }

    public static void addCommnet(Shop shop, User user) {
        clear();
        Scanner scanner = new Scanner(System.in);
        HashMap<Product, Integer> orderedProducts = user.getOrderedProducts();
        int number = 1;
        for (Product i : orderedProducts.keySet()) {
            System.out.println(number + ".\n" + "Name: " + i.getName() + "\nNumber: " + orderedProducts.get(i) + "\n--------------");
            number += 1;
        }
        if(number == 1) {
            System.out.println("Purchased Products is empty. You cant write comment for anything");
            return;
        }

        int num = scanner.nextInt();
        scanner.nextLine();
        number = 1;

        clear();
        for (Product i : orderedProducts.keySet()) {
            if(num == number) {
                System.out.println("Write commnet:");
                String commnet = scanner.nextLine();
                i.addCommnet(commnet);
                clear();
                System.out.println("Comment added to product! Thanks!");
                return;
            }
            number += 1;
        }
        System.out.println("Product not found.");
    }

    public static void editAccount(Shop shop, User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to edit?");
        System.out.println("1. username");
        System.out.println("2. password");
        System.out.println("3. email");
        System.out.println("4. phone number");
        System.out.println("5. address");
        System.out.println("0. back");
        int status = scanner.nextInt();
        scanner.nextLine();

        clear();
        if(status == 0) {
            return;
        }

        else if(status == 1) {
            System.out.println("Enter New Username:");
            String username = scanner.nextLine();
            boolean found = false;
            for(User i: shop.getUsers()) {
                if(i.getUsername() == username) {
                    found = true;
                    break;
                }
            }
            if(!found) {
                clear();
                user.setUsername(username);
                System.out.println("Username Changed!");
            }
            else {
                clear();
                System.out.println("Username is taken. The command was not executed.");
            }
        }

        else if(status == 2) {
            System.out.println("Enter New Password:");
            String password = scanner.nextLine();
            user.setPassword(password);
            clear();
            System.out.println("Password Changed!");
        }

        else if(status == 3) {
            System.out.println("Enter New Email:");
            String email = scanner.nextLine();
            user.setEmail(email);
            clear();
            System.out.println("Email Changed!");
        }

        else if(status == 4) {
            System.out.println("Enter New Phone number:");
            String phoneNumber = scanner.nextLine();
            user.setNumber(phoneNumber);
            clear();
            System.out.println("Phone Number Changed!");
        }

        else if(status == 5) {
            System.out.println("Enter New address:");
            String address = scanner.nextLine();
            user.setAddress(address);
            clear();
            System.out.println("Address Changed!");
        }

        else {
            clear();
            System.out.println("Command Not found!");   
        }

        editAccount(shop, user);


    }
    
    public static void orderedProducts(User user) {
        clear();
        HashMap<Product, Integer> orderedProducts = user.getOrderedProducts();
        int number = 1;
        for (Product i : orderedProducts.keySet()) {
            System.out.println(number + ".\n" + "Name: " + i.getName() + "\nNumber: " + orderedProducts.get(i) + "\n--------------");
            number += 1;
        }
        if(number == 1) {
            System.out.println("Purchased Products is empty.");
        }
    }

    public static void waitingProducts(User user) {
        clear();
        HashMap<Product, Integer> watingProducts = user.getWaitingProducts();
        int number = 1;
        for (Product i : watingProducts.keySet()) {
            System.out.println(number + ".\n" + "Name: " + i.getName() + "\nNumber: " + watingProducts.get(i) + "\n--------------");
            number += 1;
        }
        if(number == 1) {
            System.out.println("Wating Products is empty.");
        }
    }
    
    public static void chargeWallet(Shop shop, User user) {
        clear();
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many dollars do you want to charge your account?");
        double charge = scanner.nextDouble();
        scanner.nextLine();

        clear();
        if(charge <= 0) {
            System.out.println("Number is wrong!!");
            return;
        }

        shop.addChargeRequest(charge, user);
        System.out.println("Charge Request Sent to Admin.");
    }

    public static void buyShoppingCart(Shop shop, User user) {
        HashMap<Product, Integer> shoppingCart = user.getShoppingCart();
        double price = 0;
        clear();
        for (Product i : shoppingCart.keySet()) {
            price += i.getPrice() * shoppingCart.get(i);
        }
        if(price > user.getWallet()) {
            System.out.println("Not enough money in wallet. Charge it.");
            return;
        }
        if(price == 0) {
            System.out.println("Shopping Cart is empty.");
            return;
        }
        for (Product i : shoppingCart.keySet()) {
            int number = shoppingCart.get(i);
            user.addWaitingProducts(i, number);
            i.removeInventory(number);
        }
        user.clearShoppingCart();
        System.out.println("The purchase was made successfully! Wait for admin confirm!");
    }

    public static void shoppingCart(User user) {
        HashMap<Product, Integer> shoppingCart = user.getShoppingCart();
        int number = 1;
        clear();
        for (Product i : shoppingCart.keySet()) {
            System.out.println(number + ".\n" + "Name: " + i.getName() + "\nNumber: " + shoppingCart.get(i) + "\nPrice: " + i.getPrice() + "\n--------------");
            number += 1;
        }
        if(number == 1) {
            System.out.println("Shopping Cart is empty.");
        }
    }
    
    public static void Search(Shop shop, User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give String for searching: ");
        String search = scanner.nextLine();
        List<Product> searchedList = new ArrayList<>();

        Pattern pattern = Pattern.compile(search , Pattern.CASE_INSENSITIVE);
        for(Product product: shop.getProducts()) {
            Matcher matcher = pattern.matcher(product.getName());
            boolean matchFound = matcher.find();
            if(matchFound) {
                searchedList.add(product);
            }
        }
        clear();
        if(searchedList.size() == 0) {
            System.out.println("Nothing found.");
            return;
        }

        int number = 1;
        for(Product i: searchedList) {
            System.out.println(number + ".");
            System.out.println(i.getName());
            System.out.println("Price: " + i.getPrice());
            System.out.println("Caption: " + i.getCaption());
            System.out.println("Inventory: " + i.getInventory());
            System.out.println("------------------");
            number += 1;
        }

        System.out.println("Which product do you want to see?");
        System.out.println("0. Back to menu");
        int status = scanner.nextInt();
        scanner.nextLine();

        clear();
        if(status == 0) {
            return;
        }

        showProduct(shop, user, searchedList.get(status-1));
    }

    public static void showProduct(Shop shop, User user, Product product) {
        Scanner scanner = new Scanner(System.in);

        String name = product.getName();
        String caption = product.getCaption();
        double price = product.getPrice();
        int inventory = product.getInventory();
        List<String> comments = product.getComments();

        System.out.println("Name: " + name + '\n' + "Caption: " + caption + '\n' + "Price: " + price + '\n' + "Inventory: " + inventory + "\nComments: ");
        for(String i: comments) {
            System.out.println(i);
        }
        System.out.println("--------------------");
        while(true) {
            System.out.println("Do you want to add it to Shopping Cart? Enter the number of this product you want to buy. \n0. back");
            
            int status = scanner.nextInt();
            scanner.nextLine();

            clear();
            if(status == 0) {
                return;
            }

            else if(status > inventory) {
                if(inventory == 0) {
                    System.out.println("The product inventory is zero. Sorry!");
                    return;
                }
                System.out.println("We do not have enough stock! Try again.");
            }

            else {
                user.addShoppingCart(product, status);
                System.out.println("Added to Shopping Cart!");
                return;
            }

        }
    }

    public static void AdminMenu(Shop shop, Admin admin) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello admin!");
        System.out.println("1. Seller confirmation");
        System.out.println("2. User charge confirmation");
        System.out.println("3. Show Users");
        System.out.println("4. Show Sellers");
        System.out.println("5. Show Shop Profit");
        System.out.println("6. Buy confirmation");
        System.out.println("7. Add admin");
        System.out.println("0. Log out");

        int status = scanner.nextInt();
        scanner.nextLine();

        if(status == 0) {
            clear();
            return;
        }

        else if(status == 1) {
            ConfirmSeller(shop);
        }

        else if(status == 2) {
            ConfirmChargeRequest(shop);
        }

        else if(status == 3) {
            ShowUsers(shop);
        }

        else if(status == 4) {
            ShowSellers(shop);
        }

        else if(status == 5) {
            clear();
            System.out.println("Profit: " + shop.getProfit());
        }

        else if(status == 6) {
            ConfirmBuy(shop);
        }

        else if(status == 7) {
            addAdmin(shop);
        }

        else{
            System.out.println("Command not found! Try Again");
        }

        AdminMenu(shop, admin);
    }

    public static void ShowUsers(Shop shop) {
        clear();
        int number = 1;
        for(User i: shop.getUsers()) {
            System.out.println(number + ". Username: " + i.getUsername() + ", Password: " + i.getPassword() + ", Address: " + i.getAddress() + ", Email: " + i.getEmail() + ", Phone number: " + i.getPhoneNumber() + "Wallet: " + i.getWallet()+ "\n--------------");
            number+=1;
        }
        if(number == 1) {
            System.out.println("There is no user.");
        }
    }

    public static void ShowSellers(Shop shop) {
        clear();
        int number = 1;
        for(Seller i: shop.getSellers()) {
            System.out.println(number + ". Company Name: " + i.getUsername() + ", Password: " + i.getPassword() + ", Address: " + i.getAddress() + ", Email: " + i.getEmail() + ", Phone number: " + i.getPhoneNumber() + "Wallet: " + i.getWallet() + ", Caption: " + i.getCaption() + "\n--------------");
            number+=1;
        }
        if(number == 1) {
            System.out.println("There is no seller.");
        }
    }

    public static void ConfirmChargeRequest(Shop shop) {
        clear();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Give Request code to confirm. \n0. back");
        int number = 1;

        HashMap<User, Double> chargeRequests = shop.getChargeRequests();
        for(User i: chargeRequests.keySet()) {
            double price = chargeRequests.get(i);
            System.out.println(number + ". Username: " + i.getUsername() + ", Value: " + price);
            number += 1;
        }

        if(number == 1) {
            clear();
            System.out.println("There is no Charge Request.");
            return;
        }

        number = scanner.nextInt();
        scanner.nextLine();
        int num = 1;
        User user; double price;
        for(User i: chargeRequests.keySet()){
            if(num == number) {
                user = i;
                price = chargeRequests.get(i);
                shop.removeChargeRequest(user);
                user.addWallet(price);
                
                clear();
                System.out.println("Charge confirmed!");
                return;
            }
            num+=1; 
        }
        clear();
        System.out.println("Charge Failed!");
    }   

    public static void ConfirmSeller(Shop shop) {
        clear();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Give seller code to confirm. \n0. back");
        int number = 1;

        List<Seller> sellers = shop.getWatingSellers();
        for(Seller i: sellers) {
            System.out.println(number + ". Username: " + i.getUsername() + ", Password: " + i.getPassword() + ", Email: " + i.getEmail() + ", Caption: " + i.getCaption() + ", Phone number: " + i.getPhoneNumber() + "\n--------------" );
            number += 1;
        }

        if(number == 1) {
            clear();
            System.out.println("There is no Waiting Sellers.");
            return;
        }

        number = scanner.nextInt();
        scanner.nextLine();
        Seller seller = sellers.get(number-1);
        shop.removeWatingSellerAndAddtoSellers(seller);
        seller.setConfirmation();

        clear();
        System.out.println("Seller confirmed!");
        ConfirmSeller(shop);
    }

    public static void ConfirmBuy(Shop shop) {
        Scanner scanner = new Scanner(System.in);
        clear();
        int number = 1;
        for(User user: shop.getUsers()) {
            HashMap<Product, Integer> waitingProducts = user.getWaitingProducts();
            if(waitingProducts.size() == 0) {
                continue;
            }

            double price = 0;
            System.out.println(number + ". Username: " + user.getUsername());
            for(Product product: waitingProducts.keySet()) {
                price += waitingProducts.get(product) * product.getPrice();
                System.out.println("Product: " + product.getName() + ", number: " + waitingProducts.get(product));
            }
            System.out.println("Price: " + price + ", User Wallet: " + user.getWallet() + "\n--------------");

            number += 1;
        }
        if(number == 1) {
            clear();
            System.out.println("We dont have Waiting Purchase.");
            return;
        }
        else{
            System.out.println("0. back");
        }
        
        number = scanner.nextInt();
        scanner.nextLine();
        clear();
        
        int num = 1;
        if(number == 0) {
            return;
        }

        for(User user: shop.getUsers()) {
            HashMap<Product, Integer> waitingProducts = user.getWaitingProducts();
            if(waitingProducts.size() == 0) {
                continue;
            }
            if(num == number) {
                double price = 0;
                for(Product product: waitingProducts.keySet()) {
                    double productCost = waitingProducts.get(product) * product.getPrice();
                    price += productCost;
                    user.addOrderedProducts(product, waitingProducts.get(product));
                    product.getSeller().addWallet(productCost*90/100);
                    shop.addProfit(productCost*10/100);

                    Sale sale = new Sale(product.getName(), product.getCaption(), waitingProducts.get(product), product.getPrice(), product.getSeller(), user);
                    shop.addSales(sale);
                    product.getSeller().addSales(sale);
                }
                user.addWallet(-1*price);
                user.clearWaitingProduct();

                clear();
                System.out.println("Purchase confirmed!");
                break;
            }
            num+=1;
        }



    }

    public static void SellerMenu(Shop shop, Seller seller) {
        if(seller.getConfirmation() == 0) {
            System.out.println("Your account has not yet been approved by the admin.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello " + seller.getCompanyName() + "!");
        System.out.println("1. Add product");
        System.out.println("2. Show Products");
        System.out.println("3. Remove Product");
        System.out.println("4. Wallet");
        System.out.println("5. Sales");
        System.out.println("0. Log out");

        int status = scanner.nextInt();
        scanner.nextLine();
        clear();

        if(status == 0) {
            return;
        }

        else if(status == 1) {
            addProduct(shop, seller);
        }

        else if(status == 2) {
            showSellerProducts(shop, seller);
        }

        else if(status == 3) {
            removeProduct(shop, seller);
        }

        else if(status == 4) {
            System.out.println("You have " + seller.getWallet() + " dollors.");
        }

        else if(status == 5) {
            showSales(seller);
        }

        else{
            System.out.println("Command not found! Try Again");
        }

        SellerMenu(shop, seller);

    }

    public static void showSales(Seller seller) {
        int number = 1;
        for(Sale sale: seller.getSales()) {
            print(1);
            sale.print();
            number += 1;
        }
    }

    public static void showSellerProducts(Shop shop, Seller seller) {
        clear();
        int number = 1;
        for(Product i: seller.getProducts()) {
            System.out.println(number + ". " + i.getName() + ", caption: " + i.getCaption() + ", inventory: " + i.getInventory() + ", price: " + i.getPrice());
            number += 1;
        }
        if(number == 1) {
            System.out.println("You dont have any products.");
        }
    }

    public static void removeProduct(Shop shop, Seller seller) {
        clear();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give Product code to remove. \n0. back");
        int number = 1;
        for(Product i: seller.getProducts()) {
            System.out.println(number + ". " + i.getName() + ", caption: " + i.getCaption() + ", inventory: " + i.getInventory() + ", price: " + i.getPrice());
            number += 1;
        }
        if(number == 1) {
            clear();
            System.out.println("You dont have any products.");
            return;
        }
        
        int status = scanner.nextInt();
        scanner.nextLine();
        clear();
        if(status == 0) {
            return;
        }
        number = 1;
        for(Product i: seller.getProducts()) {
            if(status == number) {
                shop.removeProduct(i);
                seller.removeProduct(i);
                clear();
                System.out.println("Product removed!");
                return;
            }
            number += 1;
        }
        clear();
        System.out.println("Failed to remove any product. Try again.");
    }

    public static void addProduct(Shop shop, Seller seller) {

        clear();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give product name: ");
        String name = scanner.nextLine();

        clear();
        System.out.println("Give product caption: ");
        String caption = scanner.nextLine();

        clear();
        System.out.println("Give product inventory: ");
        int inventory = scanner.nextInt();
        scanner.nextLine();

        clear();
        System.out.println("Give product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        Product newProduct = new Product(name, caption, inventory, price, seller);
        seller.addProduct(newProduct);
        shop.addProduct(newProduct);

        clear();
        System.out.println("Product added to shop!");
    }

    public static void addAdmin(Shop shop) {
        Scanner scanner = new Scanner(System.in);
        String username = "";
        clear();
        while(true) { 
            boolean found = false;
            System.out.println("Enter Username:");
            username = scanner.nextLine();
            for(Admin admin: shop.getAdmins()) {
                if(admin.getUsername().equals(username)) {
                    found = true;
                    break;
                }
            }
            if(!found) {
                break;
            }
            clear();
            System.out.println("Username is taken. Choose new one.");
        }
        

        clear();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        clear();
        System.out.println("Enter Email:");
        String email = scanner.nextLine();

        Admin admin = new Admin(username, password, email);
        shop.addAdmin(admin);

        clear();
        System.out.println("Register Done.");

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Shop shop = new Shop("Digikala", "www.Digikala.com", "0212387");
        Admin admin = new Admin("admin", "admin", "admin@mail.com");
        shop.addAdmin(admin);
        clear();
        while(true) { 
            System.out.println("Welcome to the Online Shop!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int status = scanner.nextInt();

            try {
                switch (status) {
                    case 1:
                        clear();
                        Register(shop);
                        break;
                    case 2:
                        clear();
                        Login(shop);
                        break;
                    case 3:
                        System.out.println("Thanks for using out online Shop!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Code not found.");
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
     
    }
}
