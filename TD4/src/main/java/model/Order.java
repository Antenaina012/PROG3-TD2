public class Order {
    private String reference;
    private OrderType type;
    private OrderStatus status;
    // ... autres attributs existants (liste de plats, etc.)

    public void validateNotDelivered() {
        if (this.status == OrderStatus.DELIVERED) {
            throw new IllegalStateException("Une commande livrée ne peut plus être modifiée.");
        }
    }

    // Getters et Setters
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public OrderType getType() { return type; }
    public void setType(OrderType type) { this.type = type; }
}