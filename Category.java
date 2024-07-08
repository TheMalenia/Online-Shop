import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Category {

    protected String name;
    protected String caption;
    protected List<Product> products;
    protected Category Dad;
    protected boolean haveDad;

    public Category(String name, String caption) {
        this.name = name;
        this.caption = caption;
        this.products = new ArrayList<>();
        this.haveDad = false;
    }

    public Category(String name, String caption, Category Dad) {
        this.name = name;
        this.caption = caption;
        this.products = new ArrayList<>();
        this.Dad = Dad;
        this.haveDad = true;
    }

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
    
}