

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 public class Waiting extends JFrame{
	  private JLabel number ;
	  private JLabel time ;
	  private JPanel allPanel,labelPanel;
	  private OrderPage orderPage;
	  private OrderStore store;
	  private Menu menu;
	  public Waiting(OrderStore store,Order order, Menu menu) {
		  this.store = store;
		  this.menu = menu;
		  this.setSize(800, 600);
		  number = new JLabel("您的訂單編號： "+store.getArrayList());
		  number.setFont(new Font("微軟正黑體",Font.PLAIN,25));
		  time = new JLabel("等待時間： "+store.getTime(order)+" 分鐘");
		  time.setFont(new Font("微軟正黑體",Font.PLAIN,25));
		  labelPanel = new JPanel();
		  labelPanel.add(number);
		  labelPanel.add(time);
		  
		  allPanel = new JPanel(new BorderLayout());
		  allPanel.add(labelPanel, BorderLayout.CENTER);
		  createButton();
		  
  }
	  public JPanel getContentPane() {
		  return allPanel;
	  }
	  
	  public void createButton() {
		  JButton btnOrder = new JButton("繼續點餐");
		  btnOrder.setBackground(Color.WHITE);
		  btnOrder.setFont(new Font("微軟正黑體",Font.PLAIN,30));
		  allPanel.add(btnOrder,BorderLayout.SOUTH);
		  btnOrder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					orderPage = new OrderPage(menu,store);
					JPanel panel = orderPage.getContentPane();
					menu.changePanel(panel);
				}
			});
	  }
 }