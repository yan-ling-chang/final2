import java.awt.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;


public class Frame1 extends JFrame{

    JLabel idLabel;
    JLabel passLabel;
	JLabel background;
	JLabel headerLabel;
	JLabel devInfo;
    JTextField id;
    JPasswordField pass;
    JButton submit;
    
    private Menu menu;
    private Frame2new frame2;
    private JPanel panel;

   public Frame1(Menu menu){
		setTitle("安九滷味管理系統");
		this.menu = menu;
		
		
		//Background
		this.background = new JLabel(new ImageIcon("C:\\Users\\Asus\\Downloads\\Java-Project-Final (1)\\Java Project Final\\image\\burger.jpg"));

		
		this.createLayout();

		add(background);
		background.setVisible(true);  //C:\\Java Project Final\\image
        this.pack();
		this.setSize(700,400);
		this.setLocationRelativeTo(null);
		panel = new JPanel();
    }



    public void createLayout(){
		headerLabel = new JLabel();
		this.headerLabel.setText("安九滷味管理系統");
		this.headerLabel.setBounds(270,1,200,100);
		this.headerLabel.setFont(new Font(null, Font.BOLD, 25));
		headerLabel.setForeground(Color.red);
		add(headerLabel);
		
		
		idLabel = new JLabel();
		this.idLabel.setText("使用者名稱");
		this.idLabel.setBounds(190,110,100,50);
		this.idLabel.setFont(new Font(null, Font.BOLD, 20));
		idLabel.setForeground(Color.blue);
		add(idLabel);
		
        passLabel=new JLabel("密碼");
		this.passLabel.setBounds(190,165,100,50);
		this.passLabel.setFont(new Font(null, Font.BOLD, 20));
		passLabel.setForeground(Color.blue);
		add(passLabel);
				
			
		id=new JTextField();
		this.id.setBounds(300,125,200,30);
		add(id);
		
		pass=new JPasswordField();
		this.add(pass);
		this.pass.setBounds(300,175,200,30);
		
		
	
        
		this.id.setVisible(true);
	  
		
      

       this.submit=new JButton("Login");
	   this.submit.setBounds(400,230,100,25);
	   add(submit);
	   
       submit.addActionListener(this::submitActionPerformed);


    } 



   public void submitActionPerformed(java.awt.event.ActionEvent evt){

	   if(id.getText().equals("老闆") && pass.getText().equals("12345")){


		  this.hide();
		  frame2 =new Frame2new();
		  panel.add(frame2.getAllPanel());
		  menu.changePanel(panel);
		  frame2.showButtonDemo();

   }

   else{

    JOptionPane.showMessageDialog(null, "Invalid password!");

   }

   }



}
//class MyGui{
//	public static void main(String[] a){
//		Frame1 f = new Frame1();
//		f.setVisible(true);
//		
//	}

