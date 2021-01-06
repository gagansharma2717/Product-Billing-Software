import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class AddVendor implements ActionListener
{
	JPanel panel;
	Font headingFont;
	JLabel headingLabel,vendoridLabel,vendornameLabel,vendoraddressLabel,vendorcityLabel,vendorcontactLabel,vendoremailLabel;
	JTextField vendoridText,vendornameText,vendorcityText,vendorcontactText,vendoremailText;
	JScrollPane addressScroll;
	JTextArea addressTextarea;
	JButton saveButton, resetbutton;
	JSeparator sp;
	String id=null;
	public AddVendor()
	{
		panel=new JPanel();
		
		headingLabel=new JLabel("Add Vendor Details");
		vendoridLabel=new JLabel("Vendor Id: ");
		vendornameLabel=new JLabel("Enter Vendor's Name : ");
		vendoraddressLabel=new JLabel("Enter Vendor's Address : ");
		vendorcityLabel=new JLabel("Enter Vendor's City : ");
		vendorcontactLabel=new JLabel("Enter Vendor's Contact : ");
		vendoremailLabel=new JLabel("<html>Enter Email ID : <br>(Optional)</html>");
	
		vendoridText=new JTextField(10);
		vendornameText=new JTextField(10);
		vendorcontactText=new JTextField(10);
		vendoremailText=new JTextField(10);
		vendorcityText=new JTextField(10);				
		addressTextarea=new JTextArea(5,10);
		addressTextarea.setLineWrap(true);
		addressTextarea.setWrapStyleWord(true);
		
		addressScroll=new JScrollPane(addressTextarea);
		
		saveButton=new JButton("Save Details");
		saveButton.setFocusPainted(false);
		resetbutton=new JButton("Reset");
		resetbutton.setFocusPainted(false);
		sp = new JSeparator();
		sp.setBackground(Color.BLACK);
		headingFont=new Font("Times New Roman", Font.BOLD+Font.ITALIC, 50);
		    
		headingLabel.setBounds(10,10,500,50);
		headingLabel.setFont(headingFont);
		sp.setBounds(0,80,1500,30);
		
		vendoridLabel.setBounds(10,100,180,30);
		vendoridText.setBounds(190,100,180,30);
		vendornameLabel.setBounds(10,140,180,30);
		vendornameText.setBounds(190,140,180,30);
		vendoraddressLabel.setBounds(10,180,180,30);
		addressScroll.setBounds(190,180,180,70);
		vendorcityLabel.setBounds(10,260,180,30);
		vendorcityText.setBounds(190,260,180,30);
		vendorcontactLabel.setBounds(10,300,180,30);
		vendorcontactText.setBounds(190,300,180,30);
		vendoremailLabel.setBounds(10,340,180,30);
		vendoremailText.setBounds(190,340,180,30);
		saveButton.setBounds(50,390,120,30);
		resetbutton.setBounds(200,390,120,30);
		
		saveButton.addActionListener(this);
		resetbutton.addActionListener(this);
			
		panel.setLayout(null);
		panel.add(headingLabel);
		panel.add(sp);
		panel.add(vendoridLabel);
		panel.add(vendoridText);
		panel.add(vendornameLabel);
		panel.add(vendornameText);
		panel.add(vendoraddressLabel);
		panel.add(addressScroll);
		panel.add(vendorcityLabel);
		panel.add(vendorcityText);
		panel.add(vendorcontactLabel);
		panel.add(vendorcontactText);
		panel.add(vendoremailLabel);
		panel.add(vendoremailText);
		panel.add(saveButton);
		panel.add(resetbutton);
	}
	public JPanel getPanel()
	{
		return panel;
	}
	public void actionPerformed(ActionEvent evt) 
	{
		if(evt.getSource()==saveButton)
		{
			if(vendornameText.getText().equals(""))
			{
				JOptionPane.showMessageDialog(panel, "Vendor Name is required!");
				vendornameText.requestFocus();
			}
			else if(addressTextarea.getText().equals(""))
			{
				JOptionPane.showMessageDialog(panel, "Vendor Address is required!");
				addressTextarea.requestFocus();
			}
			else if(vendorcityText.getText().equals(""))
			{
				JOptionPane.showMessageDialog(panel, "Vendor City is required!");
				vendorcityText.requestFocus();
			}
			else if(vendorcontactText.getText().equals(""))
			{
				JOptionPane.showMessageDialog(panel, "Vendor Contact is required!");
				vendorcontactText.requestFocus();
			}
			else
			{
				String sPhoneNumber = vendorcontactText.getText();
				String email=vendoremailText.getText();
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
					PreparedStatement ps2 = con.prepareStatement("insert into VendorDetails (vendorID, name, address, city, phone, email) values (?, ?, ?, ?, ?, ?)");
					ps2.setString(1,vendoridText.getText());
					ps2.setString(2, vendornameText.getText());					
					ps2.setString(3, addressTextarea.getText());					
					ps2.setString(4, vendorcityText.getText());					
					ps2.setString(5, vendorcontactText.getText());					
					ps2.setString(6, vendoremailText.getText());					
					int s = ps2.executeUpdate();
					if(s>0)
					{
						JOptionPane.showMessageDialog(panel, "Record Inserted Successfully!");	
						vendoridText.setText("");
						vendornameText.setText("");
						addressTextarea.setText("");
						vendorcityText.setText("");
						vendorcontactText.setText("");
						vendoremailText.setText("");
					}
				}
				catch(Exception ee)
				{
					JOptionPane.showMessageDialog(panel, "Cannot Insert Duplicate Vendor Id");
					vendoridText.requestFocus();
				}
			}
			}
		}
		if(evt.getSource()==resetbutton)
		{	
			vendoridText.setText("");
			addressTextarea.setText("");
			vendorcityText.setText("");
			vendornameText.setText("");
			addressTextarea.setText("");
			vendorcontactText.setText("");
			vendoremailText.setText("");
			vendoridText.requestFocus();
		}
}
}	