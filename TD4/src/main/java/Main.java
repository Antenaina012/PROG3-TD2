import java.sql.Connection;
import java.sql.DriverManager;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/ton_nom_db", "user", "password"
            );

            DataRetriever retriever = new DataRetriever(connection);

            List<StockMovement> movementsLaitue = new ArrayList<>();
            movementsLaitue.add(new StockMovement(1, new StockValue(5.0, Unit.KG), MovementTypeEnum.IN, Instant.parse("2024-01-05T08:00:00Z")));
            movementsLaitue.add(new StockMovement(2, new StockValue(0.2, Unit.KG), MovementTypeEnum.OUT, Instant.parse("2024-01-06T12:00:00Z")));

            Ingredient laitue = new Ingredient(1, "Laitue", 1.5, movementsLaitue);

            retriever.saveIngredient(laitue);

            Instant t = Instant.parse("2024-01-06T12:00:00Z");
            StockValue stockAtT = laitue.getStockValueAt(t);

            System.out.println("Ingrédient : " + laitue.getName());
            System.out.println("Stock au " + t + " : " + stockAtT.getQuantity() + " " + stockAtT.getUnit());

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}