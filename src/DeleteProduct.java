import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class DeleteProduct implements ActionListener
{
	JPanel panel;
	Font headingFont;
	JLabel headingLabel,idLabel,nameLabel,vendornameLabel,netamtLabel,gstLabel,priceLabel,quantityLabel,totalLabel;
	JTextField nameText,vendornameText,netamtText,gstText,priceText,quantityText,totalText;
	JComboBox idCombo,nameCombo;
	JButton deleteButton, resetButton;
	JSeparator sp;
	public DeleteProduct()
	{
		panel=new JPanel();
		
		headingFont=new Font("Times New Roman", Font.BOLD+Font.ITALIC, 50);
	    
		headingLabel=new JLabel("Delete Product Details");
		idLabel=new JLabel("Product Id: ");
		nameLabel=new JLabel("Product Name: ");
		vendornameLabel=new JLabel("Vendor Name: ");
		gstLabel=new JLabel("Enter G.S.T : ");
		priceLabel=new JLabel("Enter Product Price : ");
		quantityLabel=new JLabel("Enter Quantity : ");
		netamtLabel=new JLabel("Enter Net Amount : ");
		totalLabel=new JLabel("Total : ");

		idCombo=new JComboBox();
		idCombo.insertItemAt("none", 0);
		idCombo.setSelectedIndex(0);
	
		idCombo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				if(idCombo.getSelectedIndex()==0)
				{	
				nameText.setText("");
				vendornameText.setText("");
				gstText.setText("");
				netamtText.setText("");
				priceText.setText("");
				quantityText.setText("");
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
						vendornameText.setText(vendorname.toString().trim());
						netamtText.setText(netamt.toString().trim());
						gstText.setText(gst.toString().trim());
						priceText.setText(price.toString().trim());
						quantityText.setText(quantity.toString().trim());
						totalText.setText(total.toString().trim());
					}
				}
				catch(Exception ee)
				{
				}		
			}
			});			
		priceText=new JTextField(10);
		nameText=new JTextField(10);
		vendornameText=new JTextField(10);
		quantityText=new JTextField(10);
		gstText=new JTextField(10);
		netamtText=new JTextField(10);
		totalText=new JTextField(10);
			
		deleteButton=new JButton("Delete");
		deleteButton.setFocusPainted(false);
		resetButton=new JButton("Reset");
		resetButton.setFocusPainted(false);
		
		headingLabel.setBounds(10,10,500,50);
		headingLabel.setFont(headingFont);
		
		sp = new JSeparator();
		sp.setBackground(Color.BLACK);
		 	
		sp.setBounds(0,80,1500,30);
		idLabel.setBounds(10,100,180,30);
		idCombo.setBounds(190,100,180,30);
		nameLabel.setBounds(10,140,180,30);
		nameText.setBounds(190,140,180,30);
		vendornameLabel.setBounds(10,180,180,30);
		vendornameText.setBounds(190,180,180,30);
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
		deleteButton.setBounds(50,440,120,30);
		resetButton.setBounds(200,440,120,30);
		
		panel.setLayout(null);
		panel.add(headingLabel);
		panel.add(sp);
		panel.add(idLabel);
		panel.add(idCombo);
		panel.add(nameLabel);
		panel.add(nameText);
		panel.add(vendornameLabel);
		panel.add(vendornameText);
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
		panel.add(deleteButton);
		panel.add(resetButton);
		
		deleteButton.addActionListener(this);
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
		}
	public JPanel getPanel()
	{
		return panel;
	}
	public void actionPerformed(ActionEvent evt1) 
	{
	if(evt1.getSource()==deleteButton)
	{
		if(idCombo.getSelectedIndex()==0)
		{
			JOptionPane.showMessageDialog(panel,"Please Select Product Id to delete the product...");
		}
		else
		{	
		try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
				PreparedStatement ps=con.prepareStatement("Delete ProductDetails WHERE productID ='"+idCombo.getSelectedItem()+"'");
				int i=ps.executeUpdate();//insert, update, delete
				if(i>0)
				{
				JOptionPane.showMessageDialog(panel, "Record Deleted Successfully!");
				idCombo.setSelectedIndex(0);
				nameText.setText("");
				vendornameText.setText("");
				priceText.setText("");
				netamtText.setText("");
				quantityText.setText("");
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
		idCombo.setSelectedIndex(0);
		nameText.setText("");
		vendornameText.setText("");
		gstText.setText(" ");
		netamtText.setText("");
		priceText.setText(" ");
		quantityText.setText(" ");
		totalText.setText(" ");
		}
}
}