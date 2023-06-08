
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.Color;

public class Frame2new {

   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel,allPanel;
   

   public Frame2new(){
      prepareGUI();
     
   }

//   public static void main(String[] args){
//      Frame2new  swingControlDemo = new Frame2new();
//      swingControlDemo.showButtonDemo();
//	  
//   }

   private void prepareGUI(){
     // mainFrame = new JFrame("安九管理");
//      mainFrame.setBounds(100,100,700,400);
//      mainFrame.setLayout(new GridLayout(3,1));
	  
//      mainFrame.getContentPane().setBackground(Color.orange);
	
	 
	  
//      mainFrame.addWindowListener(new WindowAdapter() {
//         public void windowClosing(WindowEvent windowEvent){
//            System.exit(0);
//         }
//      });
      headerLabel = new JLabel("", JLabel.CENTER);
      statusLabel = new JLabel("",JLabel.CENTER);

      statusLabel.setSize(350,300);

      controlPanel = new JPanel();
      controlPanel.setLayout(new GridLayout(1,5));
	  
      allPanel = new JPanel(new GridLayout(3,1));
      allPanel.setBackground(Color.orange);
      allPanel.setPreferredSize(new Dimension(800,600));
      allPanel.add(headerLabel);
      allPanel.add(controlPanel);
      allPanel.add(statusLabel);
      //mainFrame.setVisible(true);
	 
   }


   public void showButtonDemo(){

		headerLabel.setText("安九管理");
		this.headerLabel.setFont(new Font(null, Font.BOLD, 27));
		headerLabel.setForeground(Color.white);
		
		
		JButton fkButton = new JButton("食材資訊");
		JButton billButton = new JButton("進食材價格計算");
		JButton afButton = new JButton("加入食材");
		JButton ufButton = new JButton("更新食材");
		JButton dlButton = new JButton("刪除食材");

        fkButton.addActionListener(new ActionListener()
        {
        public void actionPerformed(ActionEvent e) {
            ItemInfo ii=new ItemInfo();
            try {
            	ii.showButtonDemo();
            } catch (SQLException ex) {
            	Logger.getLogger(Frame2new.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        });

        billButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	GenerateBill gb=new GenerateBill();}

        });
        afButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	EnterFood ef=new EnterFood();
        	ef.showButtonDemo();
        }
        });
        ufButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        
        	UpdateFood uf=new UpdateFood();
            uf.showButtonDemo();

        }
        });
	
        dlButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
           
            DeleteFood dl=new DeleteFood();
            dl.showButtonDemo();

        }
        });


        controlPanel.add(ufButton);
        controlPanel.add(afButton);
        controlPanel.add(billButton);
        controlPanel.add(fkButton);
        controlPanel.add(dlButton);

		  
       // mainFrame.setVisible(true);
       // mainFrame.setLocationRelativeTo(null);
	  }
   
   public JPanel getAllPanel() {
	   return allPanel;
   }
 }

