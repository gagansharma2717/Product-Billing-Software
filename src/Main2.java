import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.tree.*; 
public class Main2 implements ActionListener 
{
	JFrame frame;
	JPanel mainPanel, topPanel, bottomPanel,leftPanel;
	ImageIcon addcustomerIcon,viewcustomerIcon,updatecustomerIcon,paymentIcon,printbillIcon,addproductIcon,viewstockIcon,updatestockIcon,addvendorIcon,
	viewvendorIcon,updatevendorIcon,logoIcon,bottomlogoIcon;
	JButton addcustomerButton,viewcustomerButton,updatecustomerButton,paymentButton,printbillButton,addproductButton,viewstockButton,updatestockButton,addvendorButton,
	viewvendorButton,updatevendorButton;
	JLabel customerLabel,invoiceLabel,productLabel,vendorLabel,addcustomerLabel,viewcustomerLabel,paymentLabel,printbillLabel,updatecustomerLabel,addproductLabel,viewstockLabel,updatestockLabel,addvendorLabel,
	viewvendorLabel,updatevendorLabel,logoLabel,bottomlogoLabel;
	JButton generateButton,closeButton;
	JSeparator startsp,cussp,billsp,pdsp,vdrsp,endsp;
	Font headingFont, labelFont, textFont;
	JTree jt;
	//Layout
	BorderLayout border;
	FlowLayout flow;
	//Classes	
	BlankPanel blank;
	CustomerDetails ac;
	ViewCustomerDetails vcd;
	DeleteCustomer dc;
	CustomerPaymentDetails cpd;
	ViewPurchasedItems vpi;
	AddProduct ap;
	ViewProductDetails vpd;
	UpdateStock us;
	DeleteProduct dp;
	AddVendor av;
	ViewVendorDetails vvd;
	UpdateVendorDetails uvd;
	DeleteVendor dv;
	GenerateBill gb;
	PrintBill pb;
	UpdateCustomerDetails ucd;
	public Main2()
	{
		frame=new JFrame("SG Billings Software");

		mainPanel=new JPanel();
		topPanel=new JPanel();
		topPanel.setPreferredSize(new Dimension(30, 135));
		bottomPanel=new JPanel();
		bottomPanel.setPreferredSize(new Dimension(30, 60));
		leftPanel=new JPanel();
		leftPanel.setPreferredSize(new Dimension(205, 60));

		addcustomerIcon=new ImageIcon("Images/addcustomer.png");
		viewcustomerIcon=new ImageIcon("Images/viewcustomer.png");
		updatecustomerIcon=new ImageIcon("Images/updatecustomer1.png");
		paymentIcon=new ImageIcon("Images/payment.png");
		printbillIcon=new ImageIcon("Images/printBill.png");
		addproductIcon=new ImageIcon("Images/addproduct.png");
		viewstockIcon=new ImageIcon("Images/viewstock.png");
		updatestockIcon=new ImageIcon("Images/updatestock.png");
		addvendorIcon=new ImageIcon("Images/addvendor.png");
		viewvendorIcon=new ImageIcon("Images/viewvendor.png");
		updatevendorIcon=new ImageIcon("Images/updatevendor.png");
		logoIcon=new ImageIcon("Images/Sgtop.png");
		bottomlogoIcon=new ImageIcon("Images/sgbottom.jpg");
		
		addcustomerButton=new JButton(addcustomerIcon);
		addcustomerButton.setPreferredSize(new Dimension(48,48));
		viewcustomerButton=new JButton(viewcustomerIcon);
		viewcustomerButton.setPreferredSize(new Dimension(48,48));
		updatecustomerButton=new JButton(updatecustomerIcon);
		updatecustomerButton.setPreferredSize(new Dimension(48,48));
		paymentButton=new JButton(paymentIcon);
		paymentButton.setPreferredSize(new Dimension(48,48));		
		printbillButton=new JButton(printbillIcon);
		printbillButton.setPreferredSize(new Dimension(48,48));
		addproductButton=new JButton(addproductIcon);
		addproductButton.setPreferredSize(new Dimension(48,48));
		viewstockButton=new JButton(viewstockIcon);
		viewstockButton.setPreferredSize(new Dimension(48,48));
		updatestockButton=new JButton(updatestockIcon);
		updatestockButton.setPreferredSize(new Dimension(48,48));
		addvendorButton	=new JButton(addvendorIcon);
		addvendorButton.setPreferredSize(new Dimension(48,48));		
		viewvendorButton=new JButton(viewvendorIcon);
		viewvendorButton.setPreferredSize(new Dimension(48,48));
		updatevendorButton=new JButton(updatevendorIcon);
		updatevendorButton.setPreferredSize(new Dimension(48,48));
		
		customerLabel=new JLabel("Customer");
		invoiceLabel=new JLabel("Invoice");
		productLabel=new JLabel("Product");
		vendorLabel=new JLabel("Vendor");
		
		addcustomerLabel=new JLabel("<html>Add<br>Customer<br>Details</html>");
		viewcustomerLabel=new JLabel("<html>View<br>Customer<br>Details</html>");
		updatecustomerLabel=new JLabel("<html>Update<br>Customer<br>Details</html>");
		paymentLabel=new JLabel("<html>Customer<br>Payment<br>Details</html>");
		printbillLabel=new JLabel("<html>Print<br>Bill</html>");
		addproductLabel=new JLabel("<html>Add<br>Product<br>Details</html>");
		viewstockLabel=new JLabel("<html>View<br>Product<br>Details</html>");
		updatestockLabel=new JLabel("<html>Update<br>Product<br>Details</html>");
		addvendorLabel=new JLabel("<html>Add<br>Vendor<br>Details</html>");
		viewvendorLabel=new JLabel("<html>View<br>Vendor<br>Details</html>");
		updatevendorLabel=new JLabel("<html>Update<br>Vendor<br>Details</html>");
		logoLabel=new JLabel(logoIcon);
		bottomlogoLabel=new JLabel(bottomlogoIcon);
		
		generateButton=new JButton("Generate Bill");
		generateButton.setFocusPainted(false);
		
		closeButton=new JButton("Close");
		closeButton.setFocusPainted(false);
		
		startsp = new JSeparator();
		startsp.setBackground(Color.BLACK);
		cussp = new JSeparator(SwingConstants.VERTICAL);
		cussp.setBackground(Color.BLACK);
		billsp = new JSeparator(SwingConstants.VERTICAL);
		billsp.setBackground(Color.BLACK);
		pdsp = new JSeparator(SwingConstants.VERTICAL);
		pdsp.setBackground(Color.BLACK);
		vdrsp = new JSeparator(SwingConstants.VERTICAL);
		vdrsp.setBackground(Color.BLACK);
		endsp = new JSeparator(SwingConstants.VERTICAL);
		endsp.setBackground(Color.BLACK);

		headingFont=new Font("Curlz MT", Font.BOLD+Font.ITALIC, 30);
		labelFont=new Font("Times New Roman", Font.BOLD, 18);
		textFont=new Font("Tahoma", Font.ITALIC, 25);
		
		customerLabel.setFont(labelFont);
		invoiceLabel.setFont(labelFont);
		productLabel.setFont(labelFont);
		vendorLabel.setFont(labelFont);
		
		jt=new JTree();
	
		border=new BorderLayout();
		flow=new FlowLayout(FlowLayout.LEFT);
		
		frame.add(mainPanel);
		
		mainPanel.setLayout(border);
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		mainPanel.add(leftPanel, BorderLayout.WEST);
		blank=new BlankPanel();
		mainPanel.add(blank.getPanel(), BorderLayout.CENTER);
		
		topPanel.setLayout(null);
		startsp.setBounds(0,23,761,190);	
		customerLabel.setBounds(60,0,100,30);
		addcustomerButton.setBounds(10,30,48,48);
		addcustomerLabel.setBounds(15,85,56,48);
		viewcustomerButton.setBounds(70,30,48,48);
		viewcustomerLabel.setBounds(75,85,56,48);
		updatecustomerButton.setBounds(140,30,48,48);
		updatecustomerLabel.setBounds(145,85,56,48);
		cussp.setBounds(203,0,190,190);	
			
		invoiceLabel.setBounds(240,0,100,30);
		paymentButton.setBounds(210,30,48,48);
		paymentLabel.setBounds(215,85,56,48);
		printbillButton.setBounds(280,30,48,48);
		printbillLabel.setBounds(285,79,56,48);
		billsp.setBounds(340,0,190,190);
	
		productLabel.setBounds(420,0,100,30);
		addproductButton.setBounds(350,30,48,48);
		addproductLabel.setBounds(355,85,48,48);
		viewstockButton.setBounds(420,30,48,48);
		viewstockLabel.setBounds(425,85,56,48);
		updatestockButton.setBounds(490,30,48,48);	
		updatestockLabel.setBounds(495,86,56,48);
		vdrsp.setBounds(550,0,190,190);	
		
		vendorLabel.setBounds(630,0,100,30);
		addvendorButton.setBounds(560,30,48,48);
		addvendorLabel.setBounds(565,85,56,48);
		viewvendorButton.setBounds(630,30,48,48);	
		viewvendorLabel.setBounds(630,85,56,48);
		updatevendorButton.setBounds(700,30,48,48);	
		updatevendorLabel.setBounds(700,85,56,48);
		endsp.setBounds(760,0,190,190);	
		
		logoLabel.setBounds(1130,0,300,133);
		topPanel.add(startsp);
		topPanel.add(customerLabel);
		topPanel.add(addcustomerButton);
		topPanel.add(addcustomerLabel);
		topPanel.add(viewcustomerButton);
		topPanel.add(viewcustomerLabel);
		topPanel.add(updatecustomerButton);
		topPanel.add(updatecustomerLabel);
		topPanel.add(cussp);
		topPanel.add(invoiceLabel);
		topPanel.add(paymentButton);
		topPanel.add(paymentLabel);
		topPanel.add(printbillButton);
		topPanel.add(printbillLabel);
		topPanel.add(billsp);		
		topPanel.add(productLabel);
		topPanel.add(addproductButton);
		topPanel.add(addproductLabel);
		topPanel.add(viewstockButton);
		topPanel.add(viewstockLabel);
		topPanel.add(updatestockButton);
		topPanel.add(updatestockLabel);
		topPanel.add(pdsp);
		topPanel.add(vendorLabel);
		topPanel.add(addvendorButton);
		topPanel.add(addvendorLabel);
		topPanel.add(viewvendorButton);
		topPanel.add(viewvendorLabel);
		topPanel.add(updatevendorButton);
		topPanel.add(updatevendorLabel);
		topPanel.add(vdrsp);	
		topPanel.add(endsp);		
		topPanel.add(logoLabel);
		
		DefaultMutableTreeNode billing=new DefaultMutableTreeNode("Product Billing System");  
		DefaultMutableTreeNode customer=new DefaultMutableTreeNode("Customer Details");  
		DefaultMutableTreeNode product=new DefaultMutableTreeNode("Product Details");
		DefaultMutableTreeNode vendor=new DefaultMutableTreeNode("Vendor Details");
		billing.add(customer);  
		billing.add(product); 
		billing.add(vendor);
		
		final DefaultMutableTreeNode addcr=new DefaultMutableTreeNode("Add Customer Details");  
		final DefaultMutableTreeNode viewcr=new DefaultMutableTreeNode("View Customer Details");  
		final DefaultMutableTreeNode upcr=new DefaultMutableTreeNode("Update Customer Details");
		final DefaultMutableTreeNode dltcr=new DefaultMutableTreeNode("Delete Customer Details");  
		customer.add(addcr); customer.add(viewcr); customer.add(upcr);customer.add(dltcr);      
		
		final DefaultMutableTreeNode addpr=new DefaultMutableTreeNode("Add Product Details");  
		final DefaultMutableTreeNode viewpr=new DefaultMutableTreeNode("View Product Details");  
		final DefaultMutableTreeNode uppr=new DefaultMutableTreeNode("Update Product Details");  
		final DefaultMutableTreeNode dltpr=new DefaultMutableTreeNode("Delete Product Details"); 
		product.add(addpr); product.add(viewpr); product.add(uppr); product.add(dltpr);      
		
		final DefaultMutableTreeNode addvdr=new DefaultMutableTreeNode("Add Vendor Details");
		final DefaultMutableTreeNode viewvdr=new DefaultMutableTreeNode("View Vendor Details");  
		final DefaultMutableTreeNode upvdr=new DefaultMutableTreeNode("Update Vendor Details");
		final DefaultMutableTreeNode dltvdr=new DefaultMutableTreeNode("Delete Vendor Details");
		vendor.add(addvdr);vendor.add(viewvdr); vendor.add(upvdr); vendor.add(dltvdr);
		JTree jt=new JTree(billing);
	
		TreeCellRenderer rend = jt.getCellRenderer();
		if(rend instanceof DefaultTreeCellRenderer)
		{
			DefaultTreeCellRenderer dtce = (DefaultTreeCellRenderer)rend;
			dtce.setBackgroundNonSelectionColor(null);
		}
		jt.addTreeSelectionListener(new TreeSelectionListener() 
		{
			public void valueChanged(TreeSelectionEvent te) 
			{
				JTree tree = (JTree) te.getSource();
				DefaultMutableTreeNode selectednode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if(selectednode.getLastLeaf() == addcr)
				{
					mainPanel.removeAll();
					mainPanel.add(topPanel, BorderLayout.NORTH);
					mainPanel.add(bottomPanel, BorderLayout.SOUTH);
					mainPanel.add(leftPanel, BorderLayout.WEST);
					ac= new CustomerDetails();
					mainPanel.add(ac.getPanel(), BorderLayout.CENTER);
					mainPanel.revalidate();
					mainPanel.repaint();
				}
				else if(selectednode.getFirstLeaf() == viewcr)
				{
					mainPanel.removeAll();
					mainPanel.add(topPanel, BorderLayout.NORTH);
					mainPanel.add(bottomPanel, BorderLayout.SOUTH);
					mainPanel.add(leftPanel, BorderLayout.WEST);
					vcd=new ViewCustomerDetails();
					mainPanel.add(vcd.getPanel(),BorderLayout.CENTER);
					mainPanel.revalidate();
					mainPanel.repaint();
				}
				else if(selectednode.getFirstLeaf() == upcr)
				{
					mainPanel.removeAll();
					mainPanel.add(topPanel, BorderLayout.NORTH);
					mainPanel.add(bottomPanel, BorderLayout.SOUTH);
					mainPanel.add(leftPanel, BorderLayout.WEST);
					ucd=new UpdateCustomerDetails();
					mainPanel.add(ucd.getPanel(),BorderLayout.CENTER);
					mainPanel.revalidate();
					mainPanel.repaint();
				}
				else if(selectednode.getFirstLeaf() == dltcr)
				{
					mainPanel.removeAll();
					mainPanel.add(topPanel, BorderLayout.NORTH);
					mainPanel.add(bottomPanel, BorderLayout.SOUTH);
					mainPanel.add(leftPanel, BorderLayout.WEST);
					dc=new DeleteCustomer();
					mainPanel.add(dc.getPanel(),BorderLayout.CENTER);
					mainPanel.revalidate();
					mainPanel.repaint();
				}
				else if(selectednode.getLastLeaf() == addpr)
				{
					mainPanel.removeAll();
					mainPanel.add(topPanel, BorderLayout.NORTH);
					mainPanel.add(bottomPanel, BorderLayout.SOUTH);
					mainPanel.add(leftPanel, BorderLayout.WEST);
					ap=new AddProduct();
					mainPanel.add(ap.getPanel(),BorderLayout.CENTER);
					mainPanel.revalidate();
					mainPanel.repaint();
				}
				else if(selectednode.getFirstLeaf()== viewpr)
				{
					mainPanel.removeAll();
					mainPanel.add(topPanel, BorderLayout.NORTH);
					mainPanel.add(bottomPanel, BorderLayout.SOUTH);
					mainPanel.add(leftPanel, BorderLayout.WEST);
					vpd=new ViewProductDetails();
					mainPanel.add(vpd.getPanel(),BorderLayout.CENTER);
					mainPanel.revalidate();
					mainPanel.repaint();
				}
				else if(selectednode.getFirstLeaf() == uppr)
				{
					mainPanel.removeAll();
					mainPanel.add(topPanel, BorderLayout.NORTH);
					mainPanel.add(bottomPanel, BorderLayout.SOUTH);
					mainPanel.add(leftPanel, BorderLayout.WEST);
					us=new UpdateStock();
					mainPanel.add(us.getPanel(),BorderLayout.CENTER);
					mainPanel.revalidate();
					mainPanel.repaint();
				}
				else if(selectednode.getFirstLeaf() == dltpr)
				{
					mainPanel.removeAll();
					mainPanel.add(topPanel, BorderLayout.NORTH);
					mainPanel.add(bottomPanel, BorderLayout.SOUTH);
					mainPanel.add(leftPanel, BorderLayout.WEST);
					dp=new DeleteProduct();
					mainPanel.add(dp.getPanel(),BorderLayout.CENTER);
					mainPanel.revalidate();
					mainPanel.repaint();
				}
				else if(selectednode.getLastLeaf() == addvdr)
				{
					mainPanel.removeAll();
					mainPanel.add(topPanel, BorderLayout.NORTH);
					mainPanel.add(bottomPanel, BorderLayout.SOUTH);
					mainPanel.add(leftPanel, BorderLayout.WEST);
					av=new AddVendor();
					mainPanel.add(av.getPanel(),BorderLayout.CENTER);
					mainPanel.revalidate();
					mainPanel.repaint();
					}
				else if(selectednode.getFirstLeaf() == viewvdr)
				{
					mainPanel.removeAll();
					mainPanel.add(topPanel, BorderLayout.NORTH);
					mainPanel.add(bottomPanel, BorderLayout.SOUTH);
					mainPanel.add(leftPanel, BorderLayout.WEST);
					vvd=new ViewVendorDetails();
					mainPanel.add(vvd.getPanel(),BorderLayout.CENTER);
					mainPanel.revalidate();
					mainPanel.repaint();
				}
				else if(selectednode.getFirstLeaf() == upvdr)
				{
					mainPanel.removeAll();
					mainPanel.add(topPanel, BorderLayout.NORTH);
					mainPanel.add(bottomPanel, BorderLayout.SOUTH);
					mainPanel.add(leftPanel, BorderLayout.WEST);
					uvd=new UpdateVendorDetails();
					mainPanel.add(uvd.getPanel(),BorderLayout.CENTER);
					mainPanel.revalidate();
					mainPanel.repaint();
				}
				else if(selectednode.getFirstLeaf() == dltvdr)
				{
					mainPanel.removeAll();
					mainPanel.add(topPanel, BorderLayout.NORTH);
					mainPanel.add(bottomPanel, BorderLayout.SOUTH);
					mainPanel.add(leftPanel, BorderLayout.WEST);
					dv=new DeleteVendor();
					mainPanel.add(dv.getPanel(),BorderLayout.CENTER);
					mainPanel.revalidate();
					mainPanel.repaint();
				}
			}
		});
		jt.setBackground(null);
		leftPanel.setLayout(null);
		jt.setBounds(0,0,203,550);
		leftPanel.add(jt);
		jt.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		 		
		bottomPanel.setLayout(null);
		bottomlogoLabel.setBounds(0,2,500,60);
		generateButton.setBounds(550,10,150,40);
		closeButton.setBounds(710,10,150,40);
		
		bottomPanel.add(bottomlogoLabel);
		bottomPanel.add(generateButton);
		bottomPanel.add(closeButton);

		generateButton.setFont(labelFont);
		closeButton.setFont(labelFont);

		topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0,Color.BLACK));
		bottomPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0,Color.BLACK));
		leftPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2,Color.BLACK));
		
		addcustomerButton.addActionListener(this);
		viewcustomerButton.addActionListener(this);
		updatecustomerButton.addActionListener(this);
		paymentButton.addActionListener(this);
		printbillButton.addActionListener(this);
		addproductButton.addActionListener(this);
		viewstockButton.addActionListener(this);
		updatestockButton.addActionListener(this);
		addvendorButton.addActionListener(this);
		viewvendorButton.addActionListener(this);
		updatevendorButton.addActionListener(this);
		generateButton.addActionListener(this);
		closeButton.addActionListener(this);
		closeButton.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent event)
			{
			  if (event.getClickCount() == 2) {
				  String message = " Do You Want to Quit ? ";
	                String title = "Quit";
	                int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
	                if (reply == JOptionPane.YES_OPTION)
	                {
	                    System.exit(0);
	                }
			  }
			}
			public void mouseEntered(MouseEvent arg0)
			{
			}
			public void mouseExited(MouseEvent arg0) 
			{
			}
			public void mousePressed(MouseEvent arg0)
			{
			}
			public void mouseReleased(MouseEvent arg0) 
			{
			}
		});
		frame.setSize(500, 500);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent evt) 
	{
		if(evt.getSource()==addcustomerButton)
		{	
			mainPanel.removeAll();
			mainPanel.add(topPanel, BorderLayout.NORTH);
			mainPanel.add(bottomPanel, BorderLayout.SOUTH);
			mainPanel.add(leftPanel, BorderLayout.WEST);
			ac= new CustomerDetails();
			mainPanel.add(ac.getPanel(), BorderLayout.CENTER);
			mainPanel.revalidate();
			mainPanel.repaint();
		}
		else if(evt.getSource()==viewcustomerButton)
		{
			mainPanel.removeAll();
			mainPanel.add(topPanel, BorderLayout.NORTH);
			mainPanel.add(bottomPanel, BorderLayout.SOUTH);
			mainPanel.add(leftPanel, BorderLayout.WEST);
			vcd=new ViewCustomerDetails();
			mainPanel.add(vcd.getPanel(),BorderLayout.CENTER);
			mainPanel.revalidate();
			mainPanel.repaint();
		}
		else if(evt.getSource()==updatecustomerButton)
		{
			mainPanel.removeAll();
			mainPanel.add(topPanel, BorderLayout.NORTH);
			mainPanel.add(bottomPanel, BorderLayout.SOUTH);
			mainPanel.add(leftPanel, BorderLayout.WEST);
			ucd=new UpdateCustomerDetails();
			mainPanel.add(ucd.getPanel(),BorderLayout.CENTER);
			mainPanel.revalidate();
			mainPanel.repaint();
		}
		else if(evt.getSource()==paymentButton)
		{
			mainPanel.removeAll();
			mainPanel.add(topPanel, BorderLayout.NORTH);
			mainPanel.add(bottomPanel, BorderLayout.SOUTH);
			mainPanel.add(leftPanel, BorderLayout.WEST);
			cpd=new CustomerPaymentDetails();
			mainPanel.add(cpd.getPanel(),BorderLayout.CENTER);
			mainPanel.revalidate();
			mainPanel.repaint();
		}
		else if(evt.getSource()==printbillButton)
		{
			mainPanel.removeAll();
			mainPanel.add(topPanel, BorderLayout.NORTH);
			mainPanel.add(bottomPanel, BorderLayout.SOUTH);
			mainPanel.add(leftPanel, BorderLayout.WEST);
			pb=new PrintBill();
			mainPanel.add(pb.getPanel(),BorderLayout.CENTER);
			mainPanel.revalidate();
			mainPanel.repaint();
		}
		else if(evt.getSource()==addproductButton)
		{
			mainPanel.removeAll();
			mainPanel.add(topPanel, BorderLayout.NORTH);
			mainPanel.add(bottomPanel, BorderLayout.SOUTH);
			mainPanel.add(leftPanel, BorderLayout.WEST);
			ap=new AddProduct();
			mainPanel.add(ap.getPanel(),BorderLayout.CENTER);
			mainPanel.revalidate();
			mainPanel.repaint();
		}
		else if(evt.getSource()==viewstockButton)
		{
			mainPanel.removeAll();
			mainPanel.add(topPanel, BorderLayout.NORTH);
			mainPanel.add(bottomPanel, BorderLayout.SOUTH);
			mainPanel.add(leftPanel, BorderLayout.WEST);
			vpd=new ViewProductDetails();
			mainPanel.add(vpd.getPanel(),BorderLayout.CENTER);
			mainPanel.revalidate();
			mainPanel.repaint();
		}
		else if(evt.getSource()==updatestockButton)
		{
			mainPanel.removeAll();
			mainPanel.add(topPanel, BorderLayout.NORTH);
			mainPanel.add(bottomPanel, BorderLayout.SOUTH);
			mainPanel.add(leftPanel, BorderLayout.WEST);
			us=new UpdateStock();
			mainPanel.add(us.getPanel(),BorderLayout.CENTER);
			mainPanel.revalidate();
			mainPanel.repaint();
		}
		else if(evt.getSource()==addvendorButton)
		{
			mainPanel.removeAll();
			mainPanel.add(topPanel, BorderLayout.NORTH);
			mainPanel.add(bottomPanel, BorderLayout.SOUTH);
			mainPanel.add(leftPanel, BorderLayout.WEST);
			av=new AddVendor();
			mainPanel.add(av.getPanel(),BorderLayout.CENTER);
			mainPanel.revalidate();
			mainPanel.repaint();
		}
		else if(evt.getSource()==viewvendorButton)
		{
			mainPanel.removeAll();
			mainPanel.add(topPanel, BorderLayout.NORTH);
			mainPanel.add(bottomPanel, BorderLayout.SOUTH);
			mainPanel.add(leftPanel, BorderLayout.WEST);
			vvd=new ViewVendorDetails();
			mainPanel.add(vvd.getPanel(),BorderLayout.CENTER);
			mainPanel.revalidate();
			mainPanel.repaint();
		}
		else if(evt.getSource()==updatevendorButton)
		{
			mainPanel.removeAll();
			mainPanel.add(topPanel, BorderLayout.NORTH);
			mainPanel.add(bottomPanel, BorderLayout.SOUTH);
			mainPanel.add(leftPanel, BorderLayout.WEST);
			uvd=new UpdateVendorDetails();
			mainPanel.add(uvd.getPanel(),BorderLayout.CENTER);
			mainPanel.revalidate();
			mainPanel.repaint();
		}
		else if(evt.getSource()==generateButton)
		{
			mainPanel.removeAll();
			mainPanel.add(topPanel, BorderLayout.NORTH);
			mainPanel.add(bottomPanel, BorderLayout.SOUTH);
			mainPanel.add(leftPanel, BorderLayout.WEST);
			gb=new GenerateBill();
			mainPanel.add(gb.getPanel(),BorderLayout.CENTER);
			mainPanel.revalidate();
			mainPanel.repaint();
		}
			else if(evt.getSource()==closeButton)
		{
			mainPanel.removeAll();
			mainPanel.add(topPanel, BorderLayout.NORTH);
			mainPanel.add(bottomPanel, BorderLayout.SOUTH);
			mainPanel.add(leftPanel, BorderLayout.WEST);
			blank=new BlankPanel();
			mainPanel.add(blank.getPanel(),BorderLayout.CENTER);
			mainPanel.revalidate();
			mainPanel.repaint();
		}
	}
}	