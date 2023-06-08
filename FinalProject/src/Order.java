import java.util.ArrayList;

public class Order {

	private int userID;
	private ArrayList<String>content;
	private ArrayList<Integer>orderQ;
	private ArrayList<Double>price;
	private String vege;
	private ArrayList<String>sides;
	
	public Order(int userID) {
		this.userID = userID;
		this.content = new ArrayList<String>(); 
		this.orderQ = new ArrayList<Integer>();
		this.price = new ArrayList<Double>();
		sides = new ArrayList<String>();
		vege = null;
	}
	
	public boolean added() {
		if(content != null) {
			return true;
		}
		else {
			return false;
		}
	}

	public void test() {
		for(Integer i:orderQ) {
			System.out.println(i);
		}
	}

	
	public void removeEmpty() {
		int idex = 0;
		for(int i:orderQ) {
			idex = orderQ.indexOf(i);
			if(i == 0) {
				content.remove(idex);
				orderQ.remove(idex);
				price.remove(idex);
			}
		}
	}

	public ArrayList<String> getContent() {
		return content;
	}

	public ArrayList<Integer> getOrderQ() {
		return orderQ;
	}

	public ArrayList<Double> getPrice() {
		return price;
	}
	
	public void addOrder(String food,int quantity,double price) { // 把已選取的食材加到訂單內容的array儲存
		if(food != "請選擇") {
			if(content.contains(food)) {
				findAndUpdate(food,quantity);
			}
			else{
				content.add(food);
				this.price.add(price);
				orderQ.add(quantity);
			}
		}
	}
	
	public void findAndUpdate(String foodName,int num) {
		int index;
		
		for(String s:content) {
			if(s.equals(foodName)) {
				index = content.indexOf(s);
				orderQ.set(index, orderQ.get(index)+num);
				break;
			}
		}
		 //更新點餐內容
	}
	
	public double calTotal() { // 總價錢
		double Total = 0;
		for(int i = 0;i<content.size();i++) {
			Total += orderQ.get(i)*price.get(i);
		}
		return Total;
	}
	
	public void updateOrder(String name,int quantity) {
		orderQ.set(findMatchIndx(name), quantity);	
	}
	
	public int findMatchIndx(String name) {
		for(String s:content) {
			if(s.equals(name)) {
				return content.indexOf(s);
			}
		}
		return -1;
	}

	public int getUserID() {
		return userID;
	}

	public String getVege() {
		return vege;
	}

	public void setVege(String vege) {
		this.vege = vege;
	}
	
	public void addSides(String sides) {
		this.sides.add(sides);
	}
}



