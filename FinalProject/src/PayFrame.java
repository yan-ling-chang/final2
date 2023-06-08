import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PayFrame extends JFrame {
	private JLabel sause, takeOut, order, payment, payMentShow; 
	private JTextArea orderShow;
	private JCheckBox 蔥Button, 薑Button, 蒜Button, 香油Button, 辣椒Button; //配料按鈕
	private JRadioButton takeOutButton, hereButton; //內用外帶按鈕
	private JButton finishButton, fiAllButton; //輸入配料和內外帶按鈕、完成整個頁面按鈕
	private Box box;
	ButtonGroup group;
	private JPanel panel1, panel2, allPanel;
	private Order orderResult;
	private OrderStore store;
	private Waiting waitFrame;
	private Menu menu;
	
	
	public PayFrame(Order order,OrderStore store,Menu menu) {
		setBackground(new Color(255, 255, 255));
		this.menu = menu;
		//this.setTitle("備註和付款");
		//this.setSize(800,600);
		orderResult = order;
		this.store = store;
		
		createLabel();
		createCheckBox();
		createRadioButton();
		createButton();
		createLayout();	
		
	}
	
	public void createLabel() {
		sause = new JLabel("選擇配料");
		sause.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		sause.setBackground(new Color(255, 255, 255));
		takeOut = new JLabel("內用還是外帶？");
		takeOut.setFont(new Font("微軟正黑體",Font.PLAIN,18));
		order = new JLabel("您的訂單內容：");
		order.setFont(new Font("微軟正黑體",Font.PLAIN,18));
		orderShow = new JTextArea(" ");
		payment = new JLabel("金額：");
		payment.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		payMentShow = new JLabel(" ");
	}
	
	public void createCheckBox() {
		box = new Box(BoxLayout.X_AXIS);
		蔥Button = new JCheckBox("蔥");
		蔥Button.setBackground(new Color(255, 255, 255));
		蔥Button.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		蔥Button.setHorizontalAlignment(SwingConstants.CENTER);
		薑Button = new JCheckBox("薑");
		薑Button.setBackground(new Color(255, 255, 255));
		薑Button.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		薑Button.setHorizontalAlignment(SwingConstants.CENTER);
		蒜Button = new JCheckBox("蒜");
		蒜Button.setBackground(new Color(255, 255, 255));
		蒜Button.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		蒜Button.setHorizontalAlignment(SwingConstants.CENTER);
		香油Button = new JCheckBox("香油");
		香油Button.setBackground(new Color(255, 255, 255));
		香油Button.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		香油Button.setHorizontalAlignment(SwingConstants.CENTER);
		辣椒Button = new JCheckBox("辣椒");
		辣椒Button.setBackground(new Color(255, 255, 255));
		辣椒Button.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		辣椒Button.setHorizontalAlignment(SwingConstants.CENTER);
		
		box.add(蔥Button);
		box.add(薑Button);
		box.add(蒜Button);
		box.add(香油Button);
		box.add(辣椒Button);
	}
	
	public void createRadioButton() {
		takeOutButton = new JRadioButton("外帶");	
		takeOutButton.setBackground(new Color(255, 255, 255));
		takeOutButton.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		hereButton = new JRadioButton("內用");
		hereButton.setBackground(new Color(255, 255, 255));
		hereButton.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		group = new ButtonGroup();
		group.add(takeOutButton);
		group.add(hereButton);
		
	}
	
	public void createButton() {
		finishButton = new JButton("確認");
		finishButton.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		finishButton.setBackground(new Color(255, 255, 255));
		fiAllButton = new JButton("完成");
		fiAllButton.setBackground(new Color(255, 255, 255));
		fiAllButton.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		
		finishButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String sauseInfo = "配料： ";
				if(蔥Button.isSelected()) {
					sauseInfo += "蔥 ";
					orderResult.addSides("蔥");
				}if(薑Button.isSelected()) {
					sauseInfo += "薑 ";
					orderResult.addSides("薑");
				}if(蒜Button.isSelected()) {
					sauseInfo += "蒜 ";
					orderResult.addSides("蒜");
				}if(香油Button.isSelected()) {
					sauseInfo += "香油 ";
					orderResult.addSides("香油");
				}if(辣椒Button.isSelected()) {
					sauseInfo += "辣椒 ";
					orderResult.addSides("辣椒");
				}if(sauseInfo.equals("配料： ")) {
					sauseInfo = "未選擇配料";
					orderResult.addSides("未選擇配料");
				}
				
				String takeOutInfo = "內用外帶選擇： ";
				if(takeOutButton.isSelected()) {
					takeOutInfo += "外帶";
					orderResult.addSides("外帶");
				}else if(hereButton.isSelected()) {
					takeOutInfo += "內用";
					orderResult.addSides("內用");
				}else{
					takeOutInfo = "未選擇內用和外帶";
					orderResult.addSides("未選擇外帶和內用");
				}
				
				String orderInfo = "";
				for(int i = 0;i<orderResult.getContent().size();i++) {
					orderInfo += String.format("%10s%10d%20.2f\n",orderResult.getContent().get(i),orderResult.getOrderQ().get(i),orderResult.getPrice().get(i)); 
				}
				
				if(orderResult.getVege() != null) {
					orderInfo += "青菜："+" "+orderResult.getVege();
				}
				//取得訂單資訊
				//code
				
				String info = orderInfo + "\n" + sauseInfo + "\n" + takeOutInfo;
				orderShow.setText(info);
				
				int pay = 0;
				pay = (int)orderResult.calTotal();
				//取得訂單金額
				payMentShow.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
				payMentShow.setText(pay + "");
			}
		});
		fiAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.add(orderResult);
				waitFrame = new Waiting(store,orderResult,menu);
				menu.changePanel(waitFrame.getContentPane());
				//連接到下一個頁面
			}
		});
	}
	
	public void createLayout() {
		panel1 = new JPanel();
		panel1.setBackground(new Color(255, 255, 255));
		panel1.add(payment);
		panel1.add(payMentShow);
		panel1.add(fiAllButton);
		
		panel2 = new JPanel();
		panel2.setBackground(new Color(255, 255, 255));
		panel2.add(takeOutButton);
		panel2.add(hereButton);
		
		allPanel = new JPanel(new GridLayout(8, 1));
		allPanel.setBackground(new Color(255, 255, 255));
		allPanel.add(sause);
		allPanel.add(box);
		allPanel.add(takeOut);
		allPanel.add(panel2);
		allPanel.add(finishButton);
		allPanel.add(order);
		JScrollPane scrollPane = new JScrollPane(orderShow);
		allPanel.add(scrollPane);
		allPanel.add(panel1);
		
		setContentPane(allPanel);
	}

	public JPanel getContentPane() {
		return allPanel;
	}
	
}
