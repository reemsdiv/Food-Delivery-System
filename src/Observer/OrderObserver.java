package Observer;

/**
 *
 * @author remys
 */
public abstract class OrderObserver {
 
    protected OrderSubject subject;
    public abstract void update();
}
