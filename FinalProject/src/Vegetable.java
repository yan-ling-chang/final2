
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
 public class Vegetable extends JFrame{
  private JRadioButton cabbage, spinach;
  private JButton confirm;
  private Order order;
  private PayFrame payFrame;
  private OrderStore store;
  private JPanel panel;
  private Menu menu;
  
  public Vegetable(Order order, Menu menu) {
	  this.menu = menu;
	  this.order = order;
	  store = new OrderStore();
	  payFrame = new PayFrame(order,store,menu);
	  setTitle("送青菜");
   
	  cabbage = new JRadioButton("高麗菜");
	  cabbage.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
	  spinach = new JRadioButton("青江菜");
	  spinach.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
   
	  ButtonGroup vegetable = new ButtonGroup();
   
	  vegetable.add(cabbage);
	  vegetable.add(spinach);
	  
	  createButton();
	  JPanel vegetablePanel = new JPanel();
	  vegetablePanel.add(cabbage);
	  vegetablePanel.add(spinach);
	  vegetablePanel.add(confirm);
	  
	  panel = new JPanel();
	  panel.setLayout(new BorderLayout());
	  panel.add(vegetablePanel, BorderLayout.CENTER);
	  panel.setBackground(new Color(255, 255, 255));
	  setContentPane(panel);
//      setLayout(new BorderLayout());
//      add(vegetablePanel, BorderLayout.CENTER);

      setSize(300,150);
  }
   
   public void createButton() {
      confirm = new JButton("確認");
      confirm.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
      confirm.setBackground(new Color(255, 255, 255));
      confirm.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
    	 
          setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          String server = "jdbc:mysql://140.119.19.73:3315/";
          String database = "111306008"; // change to your own database
          String url = server + database + "?useSSL=false";
          String username = "111306008"; // change to your own user name
          String password = "tch2m"; // change to your own password
          try (Connection conn = DriverManager.getConnection(url, username, password)) {
        	  String query;
        	  boolean sucess;
        	  Statement stat = conn.createStatement();
        	  if(cabbage.isSelected()) {
        		  query = "DELETE FROM food WHERE f_name = 'cabbage';";
        		  //query2 = "INSERT INTO food";將青菜加入訂單
        		  sucess = stat.execute(query);
        		  //sucess = stat.execute(query2);
        		  order.setVege("高麗菜");
        		  
        	  }
        	  else if(spinach.isSelected()) {
        		  query = "DELETE FROM food WHERE f_name = 'spinach';";
        		  //query2 = "INSERT INTO food";將青菜加入訂單
        		  sucess = stat.execute(query);
        		  //sucess = stat.execute(query2);
        		  order.setVege("青江菜"); 
        	  }
          } catch (SQLException e1) {
        	  // TODO Auto-generated catch block
        	  e1.printStackTrace();
          }
          menu.changePanel(payFrame.getContentPane());
//          payFrame.setVisible(true);
          setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
      }
      });
      
  
   }
   
   public JPanel getContentPane() {
	   return panel;
   }
 }