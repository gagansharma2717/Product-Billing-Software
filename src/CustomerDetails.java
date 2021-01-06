import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
public class CustomerDetails implements ActionListener
{
	JPanel panel;
	Font headingFont;
	JLabel headingLabel,nameLabel, addressLabel, cityLabel, phoneLabel,useridLabel,emailLabel;
	JTextField nameText,cityText, phoneText,useridText,emailText;
	JScrollPane addressScroll;
	JTextArea addressTextarea;
	JButton saveButton, resetButton;
	JSeparator sp; 
	public CustomerDetails()
	{
		panel=new JPanel();
		
		headingFont=new Font("Times New Roman", Font.BOLD+Font.ITALIC, 50);
		
		headingLabel=new JLabel("Add Customer Details");
		useridLabel=new JLabel("Customer Id: ");
		nameLabel=new JLabel("Enter Customer's Name : ");
		addressLabel=new JLabel("Enter Customer's Address : ");
		cityLabel=new JLabel("Enter City : ");
		phoneLabel=new JLabel("Enter Phone Number : ");
		emailLabel=new JLabel("<html>Enter Email ID : <br>(Optional)</html>");
	
		useridText=new JTextField(10);
		nameText=new JTextField(10);
		cityText=new JTextField(10);
		phoneText=new JTextField(10);
		emailText=new JTextField(10);
		
		addressTextarea=new JTextArea(5,10);
		addressTextarea.setLineWrap(true);
		addressTextarea.setWrapStyleWord(true);
		
		addressScroll=new JScrollPane(addressTextarea);
			
		saveButton=new JButton("Save Details");
		resetButton=new JButton("Reset");
	
		headingLabel.setBounds(10,10,500,50);
		headingLabel.setFont(headingFont);
		
		sp = new JSeparator();
		sp.setBackground(Color.BLACK);
		 	
		sp.setBounds(0,80,1500,30);
		useridLabel.setBounds(10,100,180,30);
		useridText.setBounds(190,100,180,30);
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
				
		saveButton.setBounds(50,380,120,30);
		saveButton.setFocusPainted(false);
		resetButton.setBounds(200,380,120,30);
		resetButton.setFocusPainted(false);

		saveButton.addActionListener(this);
		resetButton.addActionListener(this);
	
		panel.setLayout(null);
		panel.add(headingLabel);
		panel.add(sp);
		panel.add(useridLabel);
		panel.add(useridText);
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
		panel.add(saveButton);
		panel.add(resetButton);
	}
	public JPanel getPanel()
	{
		return panel;
	}
		public void actionPerformed(ActionEvent evt) 
	{
		if(evt.getSource()==saveButton)
		{
			if(nameText.getText().equals(""))
			{
				JOptionPane.showMessageDialog(panel, "Customer Name is required!");
				nameText.requestFocus();
			}
			else if(addressTextarea.getText().equals(""))
			{
				JOptionPane.showMessageDialog(panel, "Customer Address is required!");
				addressTextarea.requestFocus();
			}
			else if(cityText.getText().equals(""))
			{
				JOptionPane.showMessageDialog(panel, "City is required!");
				cityText.requestFocus();
			}
			else if(phoneText.getText().equals(""))
			{
				JOptionPane.showMessageDialog(panel, "Contact is required!");
				phoneText.requestFocus();
			}
			else
			{
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
					PreparedStatement ps=con.prepareStatement("insert into CustomerDetails values(?,?,?,?,?,?)");					
					ps.setString(1, useridText.getText());
					ps.setString(2, nameText.getText());
					ps.setString(3, addressTextarea.getText());
					ps.setString(4, cityText.getText());
					ps.setString(5, phoneText.getText());
					ps.setString(6, emailText.getText());
					int i=ps.executeUpdate();//insert, update, delete
					if(i>0)
					{
						JOptionPane.showMessageDialog(panel, "Record Inserted Successfully!");
						useridText.setText("");
						nameText.setText("");
						addressTextarea.setText(" ");
						cityText.setText(" ");
						phoneText.setText(" ");
						emailText.setText(" ");
					}
				}
				catch(Exception ee)
				{
					JOptionPane.showMessageDialog(panel, "Cannot Insert Duplicate Customer Id");
					useridText.requestFocus();
				}
			}
			}
		}
		else if(evt.getSource()==resetButton)
		{
			useridText.setText("");
			nameText.setText("");
			addressTextarea.setText(" ");
			cityText.setText(" ");
			phoneText.setText(" ");
			emailText.setText(" ");
			useridText.requestFocus();
		}
}
}