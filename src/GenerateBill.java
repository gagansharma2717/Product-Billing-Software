import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class GenerateBill implements ActionListener,Runnable
{
	GregorianCalendar gc;
	Thread datetimeThread;
	JPanel panel,centerPanel,topPanel,bottomPanel;
	JLabel dateLabel,timeLabel,billLabel,customeridLabel,nameLabel, addressLabel,cityLabel,phoneLabel,emailLabel,productnameLabel,priceLabel,gstLabel,quantityLabel,totalqtyLabel,totalLabel;
	JTextField dateText,timeText,billText,nameText,cityText,phoneText,emailText,priceText,quantityText,gstText,totalqtyText,totalText;
	JScrollPane addressScroll;
	JTextArea addressTextarea;
	DefaultTableModel model;
	JTable jt1;
	JScrollPane spane;
	JButton addButton,submitButton,resetButton;
	JComboBox idCombo,nameCombo;
	JSeparator sp,sp4;
	BorderLayout border,border2;
	String finalquantstring=null,s=null;
	public GenerateBill()
	{
		panel=new JPanel();
		topPanel=new JPanel();
		topPanel.setPreferredSize(new Dimension(30, 215));
		centerPanel=new JPanel();
		bottomPanel=new JPanel();
		bottomPanel.setPreferredSize(new Dimension(30,100));
	
		dateLabel=new JLabel("Date:");
		timeLabel=new JLabel("Time:");
		customeridLabel=new JLabel("Customer Id:");
		billLabel=new JLabel("Invoice No. : ");
		nameLabel=new JLabel("Customer's Name : ");
		addressLabel=new JLabel("Customer's Address : ");
		cityLabel=new JLabel("City : ");
		phoneLabel=new JLabel("Phone Number : ");
		emailLabel=new JLabel("Email Address :");
		productnameLabel=new JLabel("Product Name:");
		priceLabel=new JLabel("Product Price :");
		quantityLabel=new JLabel("Quantity :");
		gstLabel=new JLabel("G.S.T :");
		totalqtyLabel=new JLabel("Total Qty :");
		totalLabel=new JLabel("Total :");
		
		dateText=new JTextField(10);
		dateText.setEditable(false);
		timeText=new JTextField(10);
		timeText.setEditable(false);
		billText=new JTextField(10);
		nameText=new JTextField(10);
		nameText.setEditable(false);
		cityText=new JTextField(10);
		cityText.setEditable(false);
		phoneText=new JTextField(10);
		phoneText.setEditable(false);
		emailText=new JTextField(10);
		emailText.setEditable(false);
		priceText=new JTextField(10);
		priceText.setEditable(false);
		quantityText=new JTextField(10);
		totalqtyText=new JTextField(10);
		totalqtyText.setEditable(false);
		gstText=new JTextField(10);
		gstText.setEditable(false);
		totalText=new JTextField(10);
		totalText.setEditable(false);
		
		addressTextarea=new JTextArea(5,10);
		addressTextarea.setLineWrap(true);
		addressTextarea.setWrapStyleWord(true);
		addressTextarea.setEditable(false);
		addressScroll=new JScrollPane(addressTextarea);
	
		model = new DefaultTableModel(new String[]{"Product Name", "Price", "Quantity","Net Amount","G.S.T","Total"}, 0);
		model.setRowCount(0);		
		jt1 =new JTable(model)
		{
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {                
             return false;               
			};
		};
		jt1.setBounds(100,40,1000,300);          
	    spane=new JScrollPane(jt1);    
	    spane.setBorder(BorderFactory.createEmptyBorder());
	    
		addButton=new JButton("Add");
		addButton.setFocusPainted(false);
		submitButton=new JButton("Submit");
		submitButton.setFocusPainted(false);
		resetButton=new JButton("Reset Details");
		resetButton.setFocusPainted(false);
		
		idCombo=new JComboBox();
		idCombo.insertItemAt("Select Customer", 0);
		idCombo.setSelectedIndex(0);
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
			PreparedStatement ps=con.prepareStatement("select * from CustomerDetails");
			ResultSet rs=ps.executeQuery();	//insert, update, delete
			while(rs.next())
			{
				String id=rs.getString("customerID");
				idCombo.addItem(id);
			}
		}
		catch(Exception ee)
		{
		}
		idCombo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				if(idCombo.getSelectedIndex()==0)
			{	
					nameText.setText("");
					addressTextarea.setText("");
					cityText.setText("");
					phoneText.setText("");
					emailText.setText("");
			}
				try
				{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
					String tmp=(String) idCombo.getSelectedItem();
					PreparedStatement ps=con.prepareStatement("select name,address,city,phone,email from CustomerDetails where customerId=?");
					ps.setString(1,tmp);
					ResultSet rs=ps.executeQuery();	//insert, update, delete
					if(rs.next())
					{
						String name=rs.getString("name");
						String address=rs.getString("address");
						String city=rs.getString("city");
						String phone=rs.getString("phone");
						String email=rs.getString("email");
						
						nameText.setText(name.toString().trim());
						addressTextarea.setText(address.toString().trim());
						cityText.setText(city.toString().trim());
						phoneText.setText(phone.toString().trim());
						emailText.setText(email.toString().trim());
					}
				}
				catch(Exception ee)
				{
				}		
			}
		});
		nameCombo=new JComboBox();
		nameCombo.insertItemAt("Select Product", 0);
		nameCombo.setSelectedIndex(0);
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
			PreparedStatement ps=con.prepareStatement("select * from ProductDetails");
			ResultSet rs=ps.executeQuery();	//insert, update, delete
			while(rs.next())
			{
				String name=rs.getString("name");
				nameCombo.addItem(name);
			}
		}
		catch(Exception ee)
		{
		}
		nameCombo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				if(nameCombo.getSelectedIndex()==0)
				{	
					priceText.setText("");
					quantityText.setText("");
					gstText.setText("");
				}
				try
				{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
					String tmp=(String) nameCombo.getSelectedItem();
					PreparedStatement ps=con.prepareStatement("select price,quantity,gst from ProductDetails where name=?");
					ps.setString(1,tmp);
					ResultSet rs=ps.executeQuery();	//insert, update, delete
					if(rs.next())
					{
						String price=rs.getString("price");
						String quantity=rs.getString("quantity");
						String gst=rs.getString("gst");
						
						priceText.setText(price.toString().trim());
						quantityText.setText(quantity.toString().trim());
						gstText.setText(gst.toString().trim());
					}
				}
				catch(Exception ee)
				{
				}		
			}
		});		
		sp = new JSeparator();
		sp.setBackground(Color.BLACK);
		sp4 = new JSeparator();
		sp4.setBackground(Color.BLACK);
		border=new BorderLayout();
		border2=new BorderLayout();

		panel.setLayout(border);
		panel.add(topPanel, BorderLayout.NORTH);
		panel.add(bottomPanel, BorderLayout.SOUTH);
		panel.add(centerPanel,BorderLayout.CENTER);
	
		centerPanel.setLayout(border2);
		centerPanel.add(spane);
		topPanel.setLayout(null);
		bottomPanel.setLayout(null);
		billLabel.setBounds(60,10,180,30);
		billText.setBounds(190,10,180,30);
		customeridLabel.setBounds(60,50,180,30);
		idCombo.setBounds(190,50,180,30);
		nameLabel.setBounds(60,90,180,30);
		nameText.setBounds(190,90,180,30);
		addressLabel.setBounds(425,10,180,30);
		addressScroll.setBounds(540,10,180,70);
		cityLabel.setBounds(425,90,180,30);
		cityText.setBounds(540,90,180,30);
		phoneLabel.setBounds(755,50,100,30);
		phoneText.setBounds(850,50,180,30);
		emailLabel.setBounds(755,10,180,30);
		emailText.setBounds(850,10,180,30);
		dateLabel.setBounds(755,90,180,30);
		dateText.setBounds(800,90,100,30);
		timeLabel.setBounds(920,90,180,30);
		timeText.setBounds(960,90,100,30);
		sp.setBounds(0,130,1500,30);		
		
		productnameLabel.setBounds(60,140,180,30);
		nameCombo.setBounds(190,140,180,30);
		priceLabel.setBounds(455,140,180,30);
		priceText.setBounds(540,140,100,30);
		quantityLabel.setBounds(695,140,180,30);
		quantityText.setBounds(760,140,100,30);
		gstLabel.setBounds(920,140,180,30);
		gstText.setBounds(960,140,100,30);
		addButton.setBounds(1000,180,150,30);
		
		totalqtyLabel.setBounds(700,10,100,30);
		totalqtyText.setBounds(780,10,100,30);
		totalLabel.setBounds(915,10,100,30);
		totalText.setBounds(960,10,100,30);
		
		sp4.setBounds(0,50,1500,30);
		submitButton.setBounds(350,60,150,30);
		resetButton.setBounds(510,60,150,30);
		
		topPanel.add(billLabel);
		topPanel.add(billText);
		topPanel.add(customeridLabel);
		topPanel.add(idCombo);
		topPanel.add(nameLabel);
		topPanel.add(nameText);
		topPanel.add(addressLabel);
		topPanel.add(addressScroll);
		topPanel.add(cityLabel);
		topPanel.add(cityText);
		topPanel.add(phoneLabel);
		topPanel.add(phoneText);
		topPanel.add(emailLabel);
		topPanel.add(emailText);
		topPanel.add(dateLabel);
		topPanel.add(dateText);
		topPanel.add(timeLabel);
		topPanel.add(timeText);
		topPanel.add(sp);

		topPanel.add(productnameLabel);
		topPanel.add(nameCombo);
		topPanel.add(quantityLabel);
		topPanel.add(quantityText);
		topPanel.add(priceLabel);
		topPanel.add(priceText);
		topPanel.add(gstLabel);
		topPanel.add(gstText);
		topPanel.add(addButton);
	
		bottomPanel.add(totalqtyLabel);
		bottomPanel.add(totalqtyText);
		bottomPanel.add(totalLabel);
		bottomPanel.add(totalText);
		bottomPanel.add(sp4);
		bottomPanel.add(submitButton);
		bottomPanel.add(resetButton);
		
		topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0,Color.BLACK));
		centerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0,Color.BLACK));
		
		addButton.addActionListener(this);
		submitButton.addActionListener(this);
		resetButton.addActionListener(this);
	
		datetimeThread=new Thread(this);
		datetimeThread.start();
	}
	public JPanel getPanel()
	{
		return panel;
	}
	public void actionPerformed(ActionEvent evt) 
	{
		if(evt.getSource()==addButton)
		{
			if(billText.getText().equals(""))
			{
			JOptionPane.showMessageDialog(panel, "Enter Invoice No. First!!!");
			billText.requestFocus();
			}
			else if(nameText.getText().equals(""))
			{
				JOptionPane.showMessageDialog(panel, "Please Select Customer Id To Fill The Details");
			}
			else if(priceText.getText().equals(""))
			{
				JOptionPane.showMessageDialog(panel, "Please Select Product To Add Into The List");
			}
			else
			{	
			String stdbquantity=null;
			try
			{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
				String tmp=(String) nameCombo.getSelectedItem();
				PreparedStatement ps=con.prepareStatement("select quantity from ProductDetails where name=?");
				ps.setString(1,tmp);
				ResultSet rs=ps.executeQuery();	//insert, update, delete
				if(rs.next())
				{
					stdbquantity=rs.getString("quantity");
				}
			}
			catch(Exception ee)
			{
			}		
			Vector<String> row=new Vector<String>();
			String name=(String) nameCombo.getSelectedItem();
			Double price=Double.parseDouble(priceText.getText());
			String pricestring = Double.toString(price);
			Double gst=Double.parseDouble(gstText.getText());
			String gststring=Double.toString(gst);
			Double quantity=Double.parseDouble(quantityText.getText());
			Double itdbquantity=Double.parseDouble(stdbquantity);
			Double finalquantity=itdbquantity-quantity;
			System.out.println(finalquantity);
			if(quantity>itdbquantity)
			{
				JOptionPane.showMessageDialog(panel, "Quantity Should be less than avilable quantity!!!");
				quantityText.setText("");
				quantityText.requestFocus();
			}
			else if(itdbquantity==0)
			{
				JOptionPane.showMessageDialog(panel, "Quantity Should be less than avilable quantity!!!");
			}
			else
			{
			String quantitystring = new DecimalFormat("##.##").format(quantity);
			Double netamt=price*quantity;
			String netamtstring = Double.toString(netamt);
			Double total;
			if(gst.equals("0"))
			{
				total=netamt;
			}
			else
			{	
			total=netamt+((netamt*gst)/100);
			}
			String totalstring = Double.toString(total);
			row.add(name.toString().trim());
			row.add(pricestring);
			row.add(quantitystring);
			row.add(netamtstring);
			row.add(gststring);
			row.add(totalstring);
			model.addRow(row);
			String totalquantity=null;
			int totalquanti = 0;
			String finaltotal=null;
			double finaltotal2 = 0;
			
			for(int i = 0; i < jt1.getRowCount(); i++)
			{	    double quan = Double.parseDouble(jt1.getValueAt(i, 2)+"");
					int quant=(int)quan;
					totalquanti=quant+totalquanti;
					totalquantity=new DecimalFormat("##").format(totalquanti);
					
					double total1 = Double.parseDouble(jt1.getValueAt(i, 5)+"");
					finaltotal2=total1+finaltotal2;
					finaltotal=new DecimalFormat("##.##").format(finaltotal2); 
			        
					totalqtyText.setText(totalquantity);		        
					totalText.setText(finaltotal);			
			 }
		}
	}		
	}
		else if(evt.getSource()==submitButton)
		{
			if(totalText.getText().equals(""))
			{
				JOptionPane.showMessageDialog(panel, "Please Add product to create a bill...");
			}
			else
			{
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
				PreparedStatement ps2=con.prepareStatement("insert into CustomerPaymentDetails values(?,?,?,?,?,?,?)");			 
				ps2.setString(1,billText.getText());
				ps2.setString(2,idCombo.getSelectedItem().toString().trim());
				ps2.setString(3,nameText.getText().toString().trim());
				ps2.setString(4,totalqtyText.getText().toString().trim());
				ps2.setString(5,totalText.getText().toString().trim());
				ps2.setString(6,dateText.getText().toString().trim());
				ps2.setString(7,timeText.getText().toString().trim());
				int s=ps2.executeUpdate();
				if(s>0)
				{	
				    	int rows = jt1.getRowCount();
				    	String query = "Insert into PurchasedItems values (?,?,?,?,?,?,?);";
				    	PreparedStatement pst = con.prepareStatement(query);
				    	for (int row = 0; row < rows; row++) {
				    	String name = (String) jt1.getValueAt(row, 0);
				    	String price =(String)  jt1.getValueAt(row, 1);
				        String quantity =(String)  jt1.getValueAt(row, 2);
				        String netamt =(String)  jt1.getValueAt(row, 3);
				        String gst =(String)  jt1.getValueAt(row, 4);
				        String total =(String)  jt1.getValueAt(row, 5);
				        
				        pst.setString(1,billText.getText());
				        pst.setString(2,name);
				        pst.setString(3,price);
				        pst.setString(4,quantity);
				        pst.setString(5,netamt);
				        pst.setString(6,gst);
				        pst.setString(7,total);
				        
				        pst.addBatch();
				    }
				    pst.executeBatch();

					Statement ps3=con.createStatement();
					for(int row =0; row <jt1.getRowCount(); row++)
					{
						String name=jt1.getValueAt(row,0).toString();
						String quantity = jt1.getValueAt(row,2).toString();					
						ps3.executeUpdate("UPDATE ProductDetails SET quantity=quantity-'"+quantity+"'WHERE name='"+name+"'");			
					}	
					String billing=billText.getText();
					new BillFormat(billing);
				    model.setRowCount(0);	
					nameCombo.setSelectedIndex(0);
					idCombo.setSelectedIndex(0);
					billText.setText("");
					nameText.setText("");
					addressTextarea.setText("");
					cityText.setText("");
					phoneText.setText("");
					gstText.setText("");
					emailText.setText("");				
					priceText.setText("");
					quantityText.setText("");
					totalqtyText.setText("");
					totalText.setText("");
					}
		}
			catch(Exception ee)
			{
				JOptionPane.showMessageDialog(panel,"Cannot Insert Duplicate Invoice Number");
				billText.requestFocus();
			}
		}
		}
		else if(evt.getSource() == resetButton)
		{
			model.setRowCount(0);	
			nameCombo.setSelectedIndex(0);
			idCombo.setSelectedIndex(0);
			billText.setText("");
			nameText.setText("");
			addressTextarea.setText("");
			cityText.setText("");
			phoneText.setText("");
			gstText.setText("");
			emailText.setText("");				
			priceText.setText("");
			quantityText.setText("");
			totalqtyText.setText("");
			totalText.setText("");	
			billText.requestFocus();
		}
	}
	public void run() {
		try {
			while(true)
			{
				gc=new GregorianCalendar();
				int DD=gc.get(Calendar.DATE);
				int MM=gc.get(Calendar.MONTH);
				int YYYY=gc.get(Calendar.YEAR);
				dateText.setText(DD+"/"+MM+"/"+YYYY);
				dateText.setCaretColor(Color.WHITE);
				int hh=gc.get(Calendar.HOUR);
				if(hh==0) 
				{
					hh=12;
				}
				int mm=gc.get(Calendar.MINUTE);
				int ampm=gc.get(Calendar.AM_PM);
				if(ampm==1)
				{	
				timeText.setText(hh+":"+mm+" PM");
				Thread.sleep(1000);
				}
				else
				{
					timeText.setText(hh+":"+mm+" AM");
					Thread.sleep(1000);
				}
				timeText.setCaretColor(Color.WHITE);
			}
		} catch (Exception e)
		{
		}		
	}
	}