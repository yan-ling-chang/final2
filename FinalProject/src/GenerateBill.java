

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GenerateBill extends JFrame{

   JTextField food,quantity;
   String[] columnNames = {"Food Name","Quantity","Price"};
   JTable cart;

   JLabel totalP = new JLabel();
   Object data[][] = new Object[100][3];
   int i = 0;
   double totalprice = 0;
   ArrayList<foodCart> foodList = new ArrayList<>();

   public GenerateBill(){
       JPanel jp1 = new JPanel();
       setBackground(Color.red);
		//jp1.getContentPane().setBackground(Color.orange);
		 
       this.setLayout(new GridLayout(2,2));
	   
       JLabel a = new JLabel("Food Name : ");
       jp1.add(a);
       food = new JTextField(50);
       jp1.add(food);
       JLabel b = new JLabel("Quantity : ");
       jp1.add(b);
       quantity = new JTextField(50);
       jp1.add(quantity);

       JButton ok = new JButton("OK");

       JPanel jp2 = new JPanel();
       jp2.setSize(700, 400);
       jp1.add(ok);
       ok.addActionListener(new ActionListener() {
    	   
         public void actionPerformed(ActionEvent e) {
            PreparedStatement pst;
            DBConnecter con = new DBConnecter();
            ResultSet rs;
            try{
                pst = con.mkDataBase().prepareStatement("select f_prize from food where f_name = ?");
                pst.setString(1, food.getText());
                rs = pst.executeQuery();

                while (rs.next()){
                    foodCart f = new foodCart();
                    f.name = food.getText();
                    f.quantity = Integer.parseInt(quantity.getText());
                    f.totalPer = f.quantity*rs.getDouble("f_prize");
                    totalprice += f.quantity*rs.getDouble("f_prize");

                    foodList.add(f);
                    data[i][0] = f.name;
                    data[i][1] = Integer.parseInt(quantity.getText());
                    data[i][2] = f.quantity*rs.getDouble("f_prize");
                    i++;
                    food.setText("");
                    quantity.setText("");
                    DefaultTableModel model = (DefaultTableModel) cart.getModel();
                    model.setRowCount(0);
                    cart = new JTable(data, columnNames);
                    System.out.println(totalprice);
                    removeAll();
                   
                    totalP.revalidate();
                    totalP.repaint();
                    revalidate();
                    repaint();
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            }
            catch(Exception ex){
                System.out.println(ex);
            }
         }
      });
       cart = new JTable(data, columnNames);
       cart.setSize(300, 450);
       //cart.setEnabled(false);
       //jp2.setLayout(new GridLayout(1,1));
       jp2.setLayout(new FlowLayout());
       jp2.add(totalP);
       jp2.add(new JScrollPane(cart, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
       JButton checkOut = new JButton("CheckOut");
       checkOut.setSize(40, 50);
       jp2.add(checkOut);
       checkOut.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            int count = 1;
            for(foodCart fc : foodList){
                System.out.println(count + ": Food Name : " + fc.name + " Quantity : "+ fc.quantity + " Price : " );

            }
            double dis = 0.8;
            System.out.println("Total Cost : " + (totalprice*dis));

            JOptionPane.showMessageDialog(null, "Total Cost (20%off) : " + (totalprice*0.8) );
            hide();
         }
      });


       this.add(jp1);
       this.add(jp2);
       this.setSize(600,550);
	   this.setLocationRelativeTo(null);
       this.setVisible(true);
   }


   class foodCart{
	   String name;
	   Double totalPer;
	   int quantity;
   }

}
