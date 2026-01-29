import java.sql.*;
import java.time.ZoneOffset;

public class DataRetriever {
    private Connection connection;

    public DataRetriever(Connection connection) {
        this.connection = connection;
    }

    public Ingredient saveIngredient(Ingredient toSave) {
        String sql = "INSERT INTO stock_movement (id, id_ingredient, quantity, type, unit, creation_datetime) " +
                "VALUES (?, ?, ?, ?::movement_type, ?::unit_type, ?) " +
                "ON CONFLICT (id) DO NOTHING";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (StockMovement sm : toSave.getStockMovementList()) {
                pstmt.setInt(1, sm.getId());
                pstmt.setInt(2, toSave.getId());
                pstmt.setDouble(3, sm.getValue().getQuantity());
                pstmt.setString(4, sm.getType().name());
                pstmt.setString(5, sm.getValue().getUnit().name());
                pstmt.setTimestamp(6, Timestamp.from(sm.getCreationDatetime()));
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toSave;
    }
}
        public Order saveOrder(Order orderToSave) {

            orderToSave.validateNotDelivered();

            String sql = "INSERT INTO \"order\" (reference, type, status) VALUES (?, ?::order_type, ?::order_status) " +
                    "ON CONFLICT (reference) DO UPDATE SET type = EXCLUDED.type, status = EXCLUDED.status";

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, orderToSave.getReference());
                pstmt.setString(2, orderToSave.getType().name());
                pstmt.setString(3, orderToSave.getStatus().name());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return orderToSave;
        }
        public Order findOrderByReference(String reference) {
            String sql = "SELECT * FROM \"order\" WHERE reference = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, reference);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    Order order = new Order();
                    order.setReference(rs.getString("reference"));
                    order.setType(OrderType.valueOf(rs.getString("type")));
                    order.setStatus(OrderStatus.valueOf(rs.getString("status")));
                    return order;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }