import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.*;
public class UpdateStock implements ActionListener,Runnable,KeyListener
{
	GregorianCalendar gc;
	Thread datetimeThread;
	JPanel panel;
	Font headingFont;
	JLabel headingLabel,idLabel,nameLabel,vendornameLabel,gstLabel,netamtLabel,priceLabel,quantityLabel,totalLabel,dateLabel,timeLabel;
	JTextField nameText,gstText,priceText,quantityText,totalText,netamtText,dateText,timeText;
	JComboBox idCombo,nameCombo;
	JButton updateButton, resetButton;
	JSeparator sp;
	public UpdateStock()
	{
		panel=new JPanel();
		
		headingFont=new Font("Times New Roman", Font.BOLD+Font.ITALIC, 50);
	    
		headingLabel=new JLabel("Update Product Details");
		dateLabel=new JLabel("Date:");
		timeLabel=new JLabel("Time:");
		idLabel=new JLabel("Product Id: ");
		nameLabel=new JLabel("Product Name: ");
		vendornameLabel=new JLabel("Vendor Name: ");
		netamtLabel=new JLabel("Net Amount : ");
		gstLabel=new JLabel("Enter G.S.T : ");
		priceLabel=new JLabel("Enter Product Price : ");
		quantityLabel=new JLabel("Enter Quantity : ");		
		totalLabel=new JLabel("Total : ");

		idCombo=new JComboBox();
		idCombo.insertItemAt("none", 0);
		idCombo.setSelectedItem("none");
		
		nameCombo=new JComboBox();
		nameCombo.insertItemAt("none", 0);
		nameCombo.setSelectedItem("none");
		
		idCombo.addActionListener(this);

		dateText=new JTextField(10);
		timeText=new JTextField(10);
		priceText=new JTextField(10);
		nameText=new JTextField(10);
		quantityText=new JTextField(10);
		gstText=new JTextField(10);
		totalText=new JTextField(10);
		netamtText=new JTextField(10);	
		updateButton=new JButton("Update");
		updateButton.setFocusPainted(false);
		resetButton=new JButton("Reset");
		resetButton.setFocusPainted(false);
		
		headingLabel.setBounds(10,10,500,50);
		headingLabel.setFont(headingFont);
		
		sp = new JSeparator();
		sp.setBackground(Color.BLACK);
		 	
		sp.setBounds(0,80,1500,30);
		dateLabel.setBounds(440,100,180,30);
		dateText.setBounds(480,100,100,30);
		timeLabel.setBounds(440,140,180,30);
		timeText.setBounds(480,140,100,30);
		idLabel.setBounds(10,100,180,30);
		idCombo.setBounds(190,100,180,30);
		nameLabel.setBounds(10,140,180,30);
		nameText.setBounds(190,140,180,30);
		vendornameLabel.setBounds(10,180,180,30);
		nameCombo.setBounds(190,180,180,30);
		priceLabel.setBounds(10,220,180,30);
		priceText.setBounds(190,220,180,30);
		quantityLabel.setBounds(10,260,180,30);
		quantityText.setBounds(190,260,180,30);
		netamtLabel.setBounds(10,300,180,30);
		netamtText.setBounds(190,300,180,30);
		gstLabel.setBounds(10,340,180,30);
		gstText.setBounds(190,340,180,30);
		totalLabel.setBounds(10,380,180,30);
		totalText.setBounds(190,380,180,30);
		
		updateButton.setBounds(50,440,120,30);
		resetButton.setBounds(200,440,120,30);
		
		panel.setLayout(null);
		panel.add(headingLabel);
		panel.add(sp);
		panel.add(dateLabel);
		panel.add(dateText);
		panel.add(timeLabel);
		panel.add(timeText);
		panel.add(idLabel);
		panel.add(idCombo);
		panel.add(nameLabel);
		panel.add(nameText);
		panel.add(vendornameLabel);
		panel.add(nameCombo);
		panel.add(gstLabel);
		panel.add(gstText);
		panel.add(netamtLabel);
		panel.add(netamtText);
		panel.add(priceLabel);
		panel.add(priceText);
		panel.add(quantityLabel);
		panel.add(quantityText);
		panel.add(totalLabel);
		panel.add(totalText);
		panel.add(updateButton);
		panel.add(resetButton);
	
		gstText.addKeyListener(this);
		priceText.addKeyListener(this);
		quantityText.addKeyListener(this);
		updateButton.addActionListener(this);
		resetButton.addActionListener(this);
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
			PreparedStatement ps=con.prepareStatement("select * from ProductDetails");
			ResultSet rs=ps.executeQuery();	//insert, update, delete
			while(rs.next())
			{
				String id=rs.getString("productID");
				idCombo.addItem(id);
			}
		}
		catch(Exception ee)
		{
		}
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
			PreparedStatement ps=con.prepareStatement("select * from VendorDetails");
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
		datetimeThread=new Thread(this);
		datetimeThread.start();
	}
	public JPanel getPanel()
	{
		return panel;
	}
	public void actionPerformed(ActionEvent evt1) 
	{
	if(evt1.getSource()==updateButton)
	{
		if(idCombo.getSelectedIndex()==0)
		{
			JOptionPane.showMessageDialog(panel,"Please Select Product Id to update the product...");
		}
		else if(nameCombo.getSelectedIndex()==0)
		{
			JOptionPane.showMessageDialog(panel,"Please Select Vendor Name to update the product...");
		}
		else if(nameText.getText().equals(""))
		{
			JOptionPane.showMessageDialog(panel, "Name is required!");
			nameText.requestFocus();
		}
		else if(netamtText.getText().equals(""))
		{
			JOptionPane.showMessageDialog(panel, "Net Amount is required!");
			netamtText.requestFocus();
		}	
		else if(gstText.getText().equals(""))
		{
			JOptionPane.showMessageDialog(panel, "G.S.T is required!");
			gstText.requestFocus();
		}
		else if(priceText.getText().equals(""))
		{
			JOptionPane.showMessageDialog(panel, "Price is required!");
			priceText.requestFocus();
		}
		else if(quantityText.getText().equals(""))
		{
			JOptionPane.showMessageDialog(panel, "Quantity is required!");
			quantityText.requestFocus();
		}
		else if(totalText.getText().equals(""))
		{
			JOptionPane.showMessageDialog(panel, "Total is required!");
			totalText.requestFocus();
		}
		else
		{
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
				PreparedStatement ps=con.prepareStatement("UPDATE ProductDetails SET name ='"+nameText.getText()+"',vendorname = ?,price= '"+priceText.getText()+"',quantity='"+quantityText.getText()+"',netamt='"+netamtText.getText()+"',gst='"+gstText.getText()+"',total='"+totalText.getText()+"',date='"+dateText.getText().toString().trim()+"',time='"+timeText.getText().toString().trim()+"'WHERE productID ='"+idCombo.getSelectedItem()+"'");
				String vdrname=nameCombo.getSelectedItem().toString().trim();
				ps.setString(1,vdrname);										
				int i=ps.executeUpdate();//insert, update, delete
				if(i>0)
				{
				JOptionPane.showMessageDialog(panel, "Record Updated Successfully!");
				idCombo.setSelectedItem("none");
				nameCombo.setSelectedItem(vdrname);
				nameText.setText("");
				priceText.setText("");
				quantityText.setText("");
				netamtText.setText("");
				gstText.setText("");
				totalText.setText("");
			}
		}
		catch(Exception ee)
		{
		}
		}
	}
	if(evt1.getSource()==resetButton)
	{
		idCombo.setSelectedItem("none");
		nameText.setText("");
		netamtText.setText("");
		gstText.setText(" ");
		priceText.setText(" ");
		quantityText.setText(" ");
		totalText.setText(" ");
		}
	if(evt1.getSource()==idCombo)
	{	
		if(idCombo.getSelectedIndex()==0)
		{
			nameText.setText("");
			nameCombo.setSelectedItem("none");
			priceText.setText("");
			quantityText.setText("");
			netamtText.setText("");
			gstText.setText("");
			totalText.setText("");
		}
		try
		{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
			String tmp=(String) idCombo.getSelectedItem();
			PreparedStatement ps=con.prepareStatement("select name,vendorname,price,quantity,netamt,gst,total from ProductDetails where productId=?");
			ps.setString(1,tmp);
			ResultSet rs=ps.executeQuery();	//insert, update, delete
			if(rs.next())
			{
				String name=rs.getString("name");
				String vendorname=rs.getString("vendorname");
				String price=rs.getString("price");
				String quantity=rs.getString("quantity");
				String netamt=rs.getString("netamt");
				String gst=rs.getString("gst");
				String total=rs.getString("total");
				
				nameText.setText(name.toString().trim());
				nameCombo.setSelectedItem(vendorname);
				priceText.setText(price.toString().trim());
				quantityText.setText(quantity.toString().trim());
				netamtText.setText(netamt.toString().trim());
				gstText.setText(gst.toString().trim());
				totalText.setText(total.toString().trim());
			}
		}
		catch(Exception ee)
		{
		}		
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
		} catch (Exception e) {
		}
}
	public void keyPressed(KeyEvent arg0) {		
	}
	public void keyReleased(KeyEvent keyevt) {
		if(keyevt.getSource()==quantityText)
		{	
			if(quantityText.getText().equals(""))
		{
			netamtText.setText("");
			totalText.setText("");
		}
			try
			{
				Double netamt=(Double.parseDouble(quantityText.getText())*Double.parseDouble(priceText.getText()));
				String netamtstring = new DecimalFormat("##.##").format(netamt);
				netamtText.setText(netamtstring);
				Double netamt2=(Double.parseDouble(netamtText.getText())/Double.parseDouble(gstText.getText()));
				Double total=(Double.parseDouble(netamtText.getText())+netamt2);
				String totalstring = new DecimalFormat("##.##").format(total);
				totalText.setText(totalstring);
			}
			catch(Exception e)
			{		
		}
		}	
		if(keyevt.getSource()==priceText)
		{	
			if(priceText.getText().equals(""))
		{
			netamtText.setText("");
			totalText.setText("");
		}
		if(quantityText.getText().equals(""))
			{
				}
			try
			{
				Double netamt=(Double.parseDouble(quantityText.getText())*Double.parseDouble(priceText.getText()));
				String netamtstring = new DecimalFormat("##.##").format(netamt);
				netamtText.setText(netamtstring);
				Double netamt2=(Double.parseDouble(netamtText.getText())/Double.parseDouble(gstText.getText()));
				Double total=(Double.parseDouble(netamtText.getText())+netamt2);
				String totalstring = new DecimalFormat("##.##").format(total);
				totalText.setText(totalstring);
			}
			catch(Exception e)
			{
		}
		}
		if(keyevt.getSource()==gstText)
		{
			if(gstText.getText().equals("0"))
		{
			totalText.setText(netamtText.getText().toString().trim());
		}
			else if(gstText.getText().equals(""))
			{
				totalText.setText("");
			}
			else if(netamtText.getText().equals(""))
			{
			}
			else
			{
				try
				{
					Double netamt=(Double.parseDouble(netamtText.getText())/Double.parseDouble(gstText.getText()));
					Double total=(Double.parseDouble(netamtText.getText())+netamt);
					String totalstring =new DecimalFormat("##.##").format(total);						
					totalText.setText(totalstring);
				}
				catch(Exception e)
				{
			}
			}
		}
	}
	public void keyTyped(KeyEvent arg0) {
			}
}