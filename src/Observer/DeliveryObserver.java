package Observer;

/**
 *
 * @author remys
 */

// Concrete Observer: Delivery Driver.

public class DeliveryObserver extends OrderObserver {
 
    public DeliveryObserver(OrderSubject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }
 
    /**
     * Called automatically by the subject on every status change.
     * Delivery reacts to ORDER_PLACED, READY, and DELIVERED.
     */
    @Override
    public void update() {
        String status = subject.getStatus();
        String order  = subject.getOrderDetails();
 
        if (status.equals("ORDER_PLACED")) {
            System.out.println("[Delivery]  Order logged. Waiting for kitchen.");
        } else if (status.equals("READY")) {
            System.out.println("[Delivery]  Order ready! Picking up for delivery.");
        } else if (status.equals("DELIVERED")) {
            System.out.println("[Delivery]  Order successfully delivered.");
        }
    } 
}
