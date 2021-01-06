import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
public class DeleteCustomer implements ActionListener
{
	JPanel panel;
	Font headingFont;
	JLabel headingLabel,customeridLabel,nameLabel, addressLabel, cityLabel, phoneLabel,emailLabel;
	JTextField nameText,cityText, phoneText,emailText;
	JScrollPane addressScroll;
	JTextArea addressTextarea;
	JButton deleteButton, resetButton;
	JComboBox idCombo;
	JSeparator sp;
	public DeleteCustomer()
	{
		panel=new JPanel();
		headingFont=new Font("Times New Roman", Font.BOLD+Font.ITALIC, 50);
		
		headingLabel=new JLabel("Delete Customer Details");
		customeridLabel=new JLabel("Customer Id:");
		nameLabel=new JLabel("Enter Customer's Name : ");
		addressLabel=new JLabel("Enter Customer's Address : ");
		cityLabel=new JLabel("Enter City : ");
		phoneLabel=new JLabel("Enter Phone Number : ");
		emailLabel=new JLabel("<html>Enter Email ID : <br>(Optional)</html>");
		
		nameText=new JTextField(10);
		cityText=new JTextField(10);
		phoneText=new JTextField(10);
		emailText=new JTextField(10);

		addressTextarea=new JTextArea(5,10);
		addressTextarea.setLineWrap(true);
		addressTextarea.setWrapStyleWord(true);
		
		addressScroll=new JScrollPane(addressTextarea);
		
		deleteButton=new JButton("Delete");
		deleteButton.setFocusPainted(false);
		resetButton=new JButton("Reset");
		resetButton.setFocusPainted(false);
		
		idCombo=new JComboBox();
		idCombo.insertItemAt("none", 0);
		idCombo.setSelectedIndex(0);
	
		headingLabel.setBounds(10,10,700,50);
		headingLabel.setFont(headingFont);
		
		sp = new JSeparator();
		sp.setBackground(Color.BLACK);
		 	
		sp.setBounds(0,80,1500,30);
		customeridLabel.setBounds(10,100,180,30);
		idCombo.setBounds(190,100,180,30);
		nameLabel.setBounds(10,140,180,30);
		nameText.setBounds(190,140,180,30);
		addressLabel.setBounds(10,180,180,30);
		addressScroll.setBounds(190,180,180,60);
		cityLabel.setBounds(10,250,180,30);
		cityText.setBounds(190,250,180,30);
		phoneLabel.setBounds(10,290,180,30);
		phoneText.setBounds(190,290,180,30);
		emailLabel.setBounds(10,330,180,30);
		emailText.setBounds(190,330,180,30);
				
		deleteButton.setBounds(50,380,120,30);
		resetButton.setBounds(200,380,120,30);
		
		deleteButton.addActionListener(this);
		resetButton.addActionListener(this);
		
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
		panel.setLayout(null);
		panel.add(headingLabel);
		panel.add(sp);
		panel.add(customeridLabel);
		panel.add(idCombo);
		panel.add(nameLabel);
		panel.add(nameText);
		panel.add(addressLabel);
		panel.add(addressScroll);
		panel.add(cityLabel);
		panel.add(cityText);
		panel.add(phoneLabel);
		panel.add(phoneText);
		panel.add(emailLabel);
		panel.add(emailText);
		panel.add(deleteButton);
		panel.add(resetButton);
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
	}
	public JPanel getPanel()
	{
		return panel;
	}
	public void actionPerformed(ActionEvent evt) 
	{
		if(evt.getSource()==deleteButton)
		{
			if(idCombo.getSelectedIndex()==0)
			{
				JOptionPane.showMessageDialog(panel,"Please Select Customer Id to delete the customer...");
			}
			String sPhoneNumber = phoneText.getText();
			String email=emailText.getText();
			Pattern pattern = Pattern.compile("\\d{10}");
			String pattern2="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			Pattern pattern23=Pattern.compile(pattern2);
			Matcher matcher = pattern.matcher(sPhoneNumber);
			Matcher matcher2=pattern23.matcher(email);

	      if (!matcher.matches())
	      {
	    	  JOptionPane.showMessageDialog(panel,"Invalid Phone Number");
	      }
	      else if(!matcher2.matches())
	      {
	    	  JOptionPane.showMessageDialog(panel,"Invalid Email Id");
	      	} 
	      else {
	    	  try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
				PreparedStatement ps=con.prepareStatement("Delete CustomerDetails WHERE customerID ='"+idCombo.getSelectedItem()+"'");
				int i=ps.executeUpdate();//insert, update, delete
				if(i>0)
				{
				JOptionPane.showMessageDialog(panel, "Record Deleted Successfully!");
				idCombo.setSelectedIndex(0);
				nameText.setText("");
				addressTextarea.setText(" ");
				cityText.setText("");
				phoneText.setText("");
				emailText.setText("");
				}
		}
		catch(Exception ee)
		{
		}
	    }
	}
	if(evt.getSource()==resetButton)
	{
		idCombo.setSelectedIndex(0);
		nameText.setText("");
		addressTextarea.setText(" ");
		cityText.setText(" ");
		phoneText.setText(" ");
		emailText.setText(" ");
	}
}
}