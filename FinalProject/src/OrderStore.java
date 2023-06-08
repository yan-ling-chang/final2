import java.util.ArrayList;

public class OrderStore {
	private int userId;
	private ArrayList<Order>orders;
	private int waiting;
	
	public OrderStore() {
		orders = new ArrayList<Order>();
	}
	
	public void add(Order newOrder) {
		newOrder.removeEmpty();
		orders.add(newOrder);
	}
	
	public void test() {
		if(orders != null) {
			System.out.println("Added");
		}
	}
	
	
	public Order findOrderById(int userId) {
		userId = this.userId;
		for(Order o:orders) {
			if(o.getUserID() == userId) {
				return o;
			}
		}
		return null;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getArrayList() {
		return orders.size();
	}
	
	public int getTime(Order order) {
		if(order.calTotal() >= 95) {
			waiting += 10;
		}
		else{
			waiting += 5;
		}
		return waiting;
	}
}
