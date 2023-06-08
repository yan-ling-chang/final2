import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Statement;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.Component;
import javax.swing.JToolBar;

public class OrderPage extends JFrame {

	private JPanel contentPane;
	private JTable menu;
	private DBConnecter con;
	private JButton add,delete,confirm;
	private DefaultTableModel tblModel;
	private JComboBox items,quantity;
	private PreparedStatement pst;
	private ResultSet rs;
	private String query;
	private JTable ordered;
	private String selectedItem = "";
	private Order order;
	private ArrayList<String>excecuteQuery;
	private JLabel total;
	private int idx = 1,buttonClicked = 1;
	private int userID;
	private Vegetable vege;
	private PayFrame payFrame;
	private OrderStore store;
	private Menu menu1;

	/**
	 * Create the frame.
	 * @param store2 
	 */
	public OrderPage(Menu menu1, OrderStore store2) {
		this.menu1 = menu1;
		setTitle("點餐頁面");
		setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setPreferredSize(new Dimension(800,600));
		excecuteQuery = new ArrayList<String>();
		order = new Order(userID);
		vege = new Vegetable(order,this.menu1);
		store = store2;
		payFrame = new PayFrame(order,store2,menu1);
		
		createTable();
		createComboBox();
		createTable();
		createButtonAction();
		

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(new Color(255, 255, 255));
		contentPane.add(menuPanel, BorderLayout.NORTH);
		menuPanel.setLayout(new BorderLayout(0, 0));
		menuPanel.setPreferredSize(new Dimension(200, 300)); 
		
		JLabel menuLabel = new JLabel(String.format("%s\n%s\n%s\n%s","今","日","菜","單"));
		menuLabel.setForeground(new Color(255, 154, 53));
		menuLabel.setFont(new Font("微軟正黑體", Font.BOLD, 35));
		menuLabel.setBackground(new Color(255, 255, 255));
		menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		menuPanel.add(menuLabel, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane(menu);
		menuPanel.add(scrollPane, BorderLayout.CENTER);
		
		JPanel orderAndTablePanel = new JPanel();
		contentPane.add(orderAndTablePanel, BorderLayout.CENTER);
		orderAndTablePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel orderPanel = new JPanel();
		orderPanel.setBackground(Color.WHITE);
		orderAndTablePanel.add(orderPanel, BorderLayout.WEST);
		orderPanel.setLayout(new GridLayout(0, 2, 0, 0));
		orderPanel.setPreferredSize(new Dimension(300,100));
		
		JLabel selectItemLabel = new JLabel("請選擇餐點");
		selectItemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		selectItemLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		orderPanel.add(selectItemLabel);
		
		items.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		items.setBackground(Color.WHITE);
		orderPanel.add(items);
		
		JLabel quantityLabel = new JLabel("請選擇數量");
		quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		quantityLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		orderPanel.add(quantityLabel);

		orderPanel.add(quantity);
		orderPanel.add(add);
		orderPanel.add(delete);
		
		JPanel listPanel = new JPanel();
		listPanel.setBackground(Color.WHITE);
		orderAndTablePanel.add(listPanel);
		listPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel selected = new JLabel("已選餐點");
		selected.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		selected.setBackground(Color.WHITE);
		selected.setPreferredSize(new Dimension(100,30));
		listPanel.add(selected, BorderLayout.NORTH);
		
		JScrollPane scrollPane_1 = new JScrollPane(ordered);
		listPanel.add(scrollPane_1, BorderLayout.CENTER);
		
		JPanel totalPanel = new JPanel();
		totalPanel.setBackground(new Color(255, 255, 255));
		contentPane.add(totalPanel, BorderLayout.SOUTH);
		totalPanel.setLayout(new GridLayout(1, 0, 0, 0));
		totalPanel.setPreferredSize(new Dimension(100,50));
		
		total = new JLabel("總計：");
		total.setBackground(new Color(255, 255, 255));
		total.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		totalPanel.add(total);

		totalPanel.add(confirm);
		
	}
	
	public void reOrder() {
		total.setText("總計：");
		//result.reset();
		for(int i = 0;i<ordered.getModel().getRowCount();i++) {
			((DefaultTableModel) ordered.getModel()).removeRow(i);
		}
		items.setEnabled(true);
		quantity.setEnabled(true);
		add.setEnabled(true);
		delete.setEnabled(true);
		items.setSelectedItem("請選擇");
		
	}

	public void createTable() {
		con = new DBConnecter();
		String[] columnNames = {"序號","名稱","價錢","數量"};
		Object[][] data = new Object[40][4];
		query = "SELECT * FROM `food`;";
		int i = 0;
		try {
			pst = con.mkDataBase().prepareStatement(query);
			rs = pst.executeQuery();
			while(rs.next()) {
				data[i][0] = rs.getInt("f_ID");
				data[i][1] = rs.getString("f_name");
				data[i][2] = rs.getDouble("f_prize");
				data[i][3] = rs.getInt("f_quantity");
				i++;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		menu = new JTable(data,columnNames);
		menu.setBackground(new Color(255, 255, 255));
		menu.setFillsViewportHeight(true);
		menu.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		menu.setRowSelectionAllowed(false);
		menu.setShowVerticalLines(false);
		menu.setShowHorizontalLines(false);
		menu.setEnabled(false);
		
		String[] colNames = {"名稱","數量","價錢"};
		Object data1[][] = null;
		
		tblModel = new DefaultTableModel(data1,colNames);
		ordered = new JTable(tblModel);
		ordered.setShowGrid(false);
		ordered.setFillsViewportHeight(true);
		ordered.setBorder(new EmptyBorder(15, 15, 0, 15));
		ordered.setBackground(Color.WHITE);
		ordered.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		ordered.setShowVerticalLines(false);
		ordered.setShowHorizontalLines(false);
	}
	
	
	public void createComboBox() {
		items = new JComboBox();
		con = new DBConnecter();
		query = "SELECT `f_name` FROM `food`;";
		items.addItem("請選擇");
		try {
			pst = con.mkDataBase().prepareStatement(query);
			rs = pst.executeQuery();
			while(rs.next()) {
				items.addItem(rs.getString("f_name"));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			
		}
		
		quantity = new JComboBox();
		quantity.setBackground(Color.WHITE);
		quantity.setForeground(new Color(0, 0, 0));
		quantity.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		items.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					if(e.getItem() == items.getSelectedItem()) {
						confirm.setText("確認訂單");
						selectedItem = e.getItem().toString();
						quantity.removeAllItems();
						query = String.format("SELECT `f_quantity` FROM `food` WHERE `f_name` = '%s';",selectedItem);
						try {
							pst = con.mkDataBase().prepareStatement(query);
							rs = pst.executeQuery(query);
							while(rs.next()) {
								if(rs.getInt("f_quantity") != 0) {
									add.setEnabled(true);
									quantity.setEnabled(true);
									for(int i = 0;i<rs.getInt("f_quantity");i++) {
										quantity.addItem(i+1);
									}
								}
								else {
									quantity.addItem("已售完");
									quantity.setEnabled(false);
									add.setEnabled(false);
								}
							}
						}catch(Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		});
		}	
		
	
	
	public void createButtonAction() {
		add = new JButton("加入"); // finished
		add.setBackground(Color.WHITE);
		add.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				con = new DBConnecter();
				int originalQ = 0;
				if(items.getSelectedItem().toString() != "請選擇"){// 把資料加入表格中 & 儲存在Result class裡面 & 減少數量
					query = String.format("SELECT `f_prize` FROM `food` WHERE `f_name` = '%s';",items.getSelectedItem().toString());
					String query1 = String.format("SELECT `f_quantity` FROM `food` WHERE `f_name` = '%s';",items.getSelectedItem().toString());
					try{ 					
						pst = con.mkDataBase().prepareStatement(query);
						rs = pst.executeQuery();
						while(rs.next()) {
							order.addOrder(items.getSelectedItem().toString(), (int)quantity.getSelectedItem(), rs.getDouble("f_prize"));
							String name = items.getSelectedItem().toString();
							if(findName(name) && tblModel.getRowCount() != 0) {
								int doneQ = Integer.valueOf(tblModel.getValueAt(getMatchIndx(name), 1).toString());
								int newQ = Integer.valueOf(quantity.getSelectedItem().toString());
								tblModel.setValueAt(doneQ + newQ, getMatchIndx(name), 1);	
							}
							else {
								Object data[] = {items.getSelectedItem().toString(),Integer.valueOf(quantity.getSelectedItem().toString()),rs.getDouble("f_prize")};
								tblModel.addRow(data);
							}
						}
					}catch(Exception ex) {
						ex.printStackTrace();
					}
					
					total.setText("總計：   " + order.calTotal());
				}
				else {
					JOptionPane.showMessageDialog(null,"請選擇食材", "error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		delete = new JButton("更改"); 
		delete.setBackground(Color.WHITE);
		delete.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				// 獲取要刪除的食材的資料
				if(ordered.getSelectedRowCount() != 0) {
					int selectedRow = ordered.getSelectedRow();
					int getUpdate = Integer.valueOf(JOptionPane.showInputDialog("請輸入要更改的數量"));
					
					if(getUpdate == 0) {  // 有問題
						order.updateOrder(ordered.getValueAt(selectedRow, 0).toString(),getUpdate); // 有問題
						tblModel.removeRow(selectedRow); // not
						total.setText("總計：   " + order.calTotal());
					}
					
					else if(getUpdate > 0){ // finished
						tblModel.setValueAt(getUpdate, selectedRow, 1);
						order.updateOrder(ordered.getValueAt(selectedRow, 0).toString(),getUpdate);
						total.setText("總計：   " + order.calTotal());
					}
					
					else {
						JOptionPane.showMessageDialog(null,"請輸入大於 0 的數量", "error",JOptionPane.ERROR_MESSAGE);
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null,"沒有可更改的食材", "error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		
		confirm = new JButton("確認訂單");  // finished
		confirm.setBackground(Color.WHITE);
		confirm.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		
		
		class ClickEvent implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("確認訂單")) {
					if(tblModel.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null,"尚未選擇餐點", "error",JOptionPane.ERROR_MESSAGE);
					}
					
					else {
						// 更新資料庫資料
						con = new DBConnecter();
						try{
							for(int i = 0;i<tblModel.getRowCount();i++) {
								String name = ordered.getValueAt(i, 0).toString();
								int quan = (int)ordered.getValueAt(i, 1);
								
								String query1 = String.format("SELECT `f_quantity` FROM `food` WHERE `f_name` = '%s';",name); 
								
								pst = con.mkDataBase().prepareStatement(query1);
								rs = pst.executeQuery();
								while(rs.next()) {
									int originalQ = rs.getInt("f_quantity");
									query = String.format("UPDATE `food` SET `f_quantity` = %d WHERE `f_name` = '%s'", originalQ - quan,name);
									excecuteQuery.add(query);
								}
							}
						}catch(Exception ex) {
							ex.printStackTrace();
						}
							
						if(order.calTotal() >= 95) {
							menu1.changePanel(vege.getContentPane());
//							vege.setVisible(true);
						}
						else {
//							payFrame.setVisible(true);
							menu1.changePanel(payFrame.getContentPane());
						}
					
					}
				}
			}
		}
		confirm.addActionListener(new ClickEvent());
	}
	
	public boolean findName(String name) { // 找是否有在已選清單中
		for(int i = 0;i<tblModel.getRowCount();i++) {
			String existName = tblModel.getValueAt(i, 0).toString();
			if(existName.equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public int getMatchIndx(String name) {
		for(int i = 0;i<tblModel.getRowCount();i++) {
			String existName = tblModel.getValueAt(i, 0).toString();
			if(existName.equals(name)) {
				return i;
			}
		}
		return -1;
	}
	

	public void setUserName(int userId) {
		this.userID = userId;
	}

	public int getUserID() {
		return userID;
	}
	
	public JPanel getContentPane() {
		return contentPane;
	}

	public Order getOrder() {
		return order;
	}
}

