import java.time.Instant;
import java.util.List;

public class Ingredient {
    private int id;
    private String name;
    private Double price;
    private List<StockMovement> stockMovementList;

    public Ingredient(int id, String name, Double price, List<StockMovement> stockMovementList) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockMovementList = stockMovementList;
    }

    public StockValue getStockValueAt(Instant t) {
        double total = 0.0;
        for (StockMovement sm : stockMovementList) {
            if (!sm.getCreationDatetime().isAfter(t)) {
                if (sm.getType() == MovementTypeEnum.IN) {
                    total += sm.getValue().getQuantity();
                } else {
                    total -= sm.getValue().getQuantity();
                }
            }
        }
        return new StockValue(total, Unit.KG);
    }

    public int getId() { return id; }
    public List<StockMovement> getStockMovementList() { return stockMovementList; }
}