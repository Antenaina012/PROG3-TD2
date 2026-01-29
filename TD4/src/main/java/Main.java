import model.*;
import repository.DataRetriever;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/nom_db", "user", "password"
            );

            DataRetriever retriever = new DataRetriever(connection);

            List<StockMovement> movements = new ArrayList<>();
            movements.add(new StockMovement(1, new StockValue(5.0, Unit.KG), MovementTypeEnum.IN, Instant.parse("2024-01-05T08:00:00Z")));
            movements.add(new StockMovement(2, new StockValue(0.2, Unit.KG), MovementTypeEnum.OUT, Instant.parse("2024-01-06T12:00:00Z")));

            Ingredient laitue = new Ingredient(1, "Laitue", 1.5, CategoryEnum.LEGUME, movements);
            retriever.saveIngredient(laitue);

            Instant t = Instant.parse("2024-01-06T12:00:00Z");
            System.out.println("Stock Laitue: " + laitue.getStockValueAt(t).getQuantity());

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}