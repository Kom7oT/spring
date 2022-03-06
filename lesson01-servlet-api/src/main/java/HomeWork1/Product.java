package HomeWork1;

public class Product {

    private Long id;
    private String title;
    private double cost;

    public Product(String title, double cost) {
        this.title = title;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public double getCost() {
        return cost;
    }

    public void setTitle(String username) {
        this.title = title;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
