import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class Login extends JFrame{
	private User user = new User();
	private PayFrame frame;
	private JTextField tfUserID, tfPassword, phone;
	private JButton btnEnroll, btnLogin;
	private JPanel panel = (JPanel) this.getContentPane();
	private JLabel ID, Password, userPhone;
	private OrderPage orderPage;
	private int userId;
	private Menu menu;
	private OrderStore store;
	
	public Login(Menu menu, OrderStore store) {
		this.store = store;
		this.menu = menu;
		orderPage = new OrderPage(menu,store);
		createdTextField();
		createButton();
		panel = new JPanel(new GridLayout(4,1));
		ID = new JLabel("Student ID");
		ID.setHorizontalAlignment(SwingConstants.CENTER);
		ID.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		Password = new JLabel("password");
		Password.setHorizontalAlignment(SwingConstants.CENTER);
		Password.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		userPhone = new JLabel("Phone");
		userPhone.setHorizontalAlignment(SwingConstants.CENTER);
		userPhone.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		Layout();
		setSize(390,206);
		setTitle("");
	}
	//your code
	public void createdTextField() {
		tfUserID = new JTextField(10);
		tfUserID.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		tfUserID.setText("");
		tfPassword = new JTextField(10);
		tfPassword.setText("");
		phone = new JTextField(10);
		phone.setText("  ");
	}
	public void createButton() {
		btnEnroll = new JButton("Enroll");
		btnEnroll.setBackground(new Color(255, 255, 255));
		btnEnroll.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(255, 255, 255));
		btnLogin.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		
		btnEnroll.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					user.add(tfUserID.getText(),tfPassword.getText());
				} catch (PasswordError e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,"PasswordError: Password should be 8 letter");
				} catch (UserError e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,"UserError: Username can't be empty");
				}
			}
		});
		
		btnLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

				try {
					user.checkUserExist(tfUserID.getText());
					
				} catch (UserError e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,"UserError: Can't find user" );
				}
				try {
					user.checkPassword(tfUserID.getText(),tfPassword.getText());
				} catch (PasswordError e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,"PasswordError: password is wrong");
					return;
				}
				
//				JPanel orderPanel = orderPage.getContentPane();
				//orderPage.setVisible(true);
				menu.changePanel(orderPage.getContentPane());
				orderPage.setUserName(getUserId());
				setVisible(false);
			}
		});
	}
	
	
	public void Layout() {
		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(255, 255, 255));
		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(255, 255, 255));
		JPanel panel3 = new JPanel();
		panel3.setBackground(new Color(255, 255, 255));
		JPanel panel4 = new JPanel();
		panel4.setBackground(new Color(255, 255, 255));
		panel1.setLayout(new GridLayout(0, 2, 0, 0));

		panel1.add(ID);
		panel1.add(tfUserID);
		panel2.setLayout(new GridLayout(0, 2, 0, 0));
		
		panel2.add(Password);
		panel2.add(tfPassword);
		panel3.setLayout(new GridLayout(0, 2, 0, 0));
		
		panel3.add(userPhone);
		panel3.add(phone);
		panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel4.add(btnEnroll);
		panel4.add(btnLogin);
		
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		
		getContentPane().add(panel);
	}
	public int getUserId() {  //獲取登入的使用者ID
		userId = Integer.valueOf(tfUserID.getText());
		return userId;
	}
	
	public void reset() {
		tfUserID.setText(null);
		tfPassword.setText(null);
		phone.setText(null);
	}
}

