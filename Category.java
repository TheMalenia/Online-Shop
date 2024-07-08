import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Category {

    protected String name;
    protected String caption;
    protected List<Product> products;
    protected Category Dad;
    protected boolean haveDad;
    protected String data1; //, value1; 
    protected String data2; //, value2; 

    public Category(String name, String caption, String data1, String data2) {
        this.name = name;
        this.caption = caption;
        this.products = new ArrayList<>();
        this.haveDad = false;
        this.data1 = data1;
        this.data2 = data2;
    }

    public Category(String name, String caption, String data1, String data2, Category Dad) {
        this.name = name;
        this.caption = caption;
        this.products = new ArrayList<>();
        this.Dad = Dad;
        this.haveDad = true;
        this.data1 = data1;
        this.data2 = data2;
    }

    // get
    public String getName() {
        return this.name;
    }

    public String getCaption() {
        return this.caption;
    }

    public Category getDad() {
        return this.Dad;
    }

    public boolean getHaveDad() {
        return this.haveDad;
    }

    public String getData1() {
        return this.data1;
    }

    public String getData2() {
        return this.data2;
    }
    
}