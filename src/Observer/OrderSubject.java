package Observer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author remys
 */
public class OrderSubject {
 
    // List of registered observers 
    private List<OrderObserver> observers = new ArrayList<OrderObserver>();
 
    // The current state: order details and status
    private String orderDetails;
    private String status;
 
    // Register an observer 
    public void attach(OrderObserver observer) {
        observers.add(observer);
    }
 
    // Getters so observers can read the current state
    public String getOrderDetails() {
        return orderDetails;
    }
 
    public String getStatus() {
        return status;
    }
 
    // Change the order status and notify all observers.
    public void setOrderStatus(String orderDetails, String status) {
        this.orderDetails = orderDetails;
        this.status = status;
        System.out.println("\n[Order Update] Status changed to: " + status);
        notifyAllObservers();
    }
 
    // Notify all registered observers
    public void notifyAllObservers() {
        for (OrderObserver observer : observers) {
            observer.update();
        }
    }
 
}
