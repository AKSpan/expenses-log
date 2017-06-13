package span.home.entities;

/**
 * Создано Span 01.06.2017.
 */
public class ShortExpIncData  {
    private Categories categories;
    private String category;
    private double sum;


    public ShortExpIncData(Categories id, String category, double sum) {
        this.categories = id;
        this.category = category;
        this.sum = sum;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories id) {
        this.categories = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}