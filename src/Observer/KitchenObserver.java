package Observer;

/**
 *
 * @author remys
 */

// Concrete Observer: Kitchen.

public class KitchenObserver extends OrderObserver {
 
    public KitchenObserver(OrderSubject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }
 
    /**
     * Called automatically by the subject on every status change.
     * Kitchen reacts to ORDER_PLACED, PREPARING, and READY.
     */
    @Override
    public void update() {
        String status = subject.getStatus();
        String order  = subject.getOrderDetails();
 
        if (status.equals("ORDER_PLACED")) {
            System.out.println("[Kitchen]   New order received! Starting preparation for: " + order);
        } else if (status.equals("PREPARING")) {
            System.out.println("[Kitchen]   Currently preparing: " + order);
        } else if (status.equals("READY")) {
            System.out.println("[Kitchen]   Order is ready for pickup: " + order);
        }
    } 
}
