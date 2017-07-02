package buttonListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import progr.Dashboard;
import progr.Impianto;
import progr.Test;
import progr.utility;

public class TrasDatiLyst extends Test implements ActionListener
{
	static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	static String dbName="jdbcDashBoard";
	static String connectionURL = "jdbc:derby:" + dbName + ";create=true";
	public void actionPerformed(ActionEvent arg0)
	{
		if(dashb.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "nessun impianto/sensore installato");
		}
		else
		{	
			
			final JFrame frame =new JFrame();
			frame.setSize(400,400);
			frame.setTitle("dashBoard");
			frame.setLocation(100, 100);
			JPanel panel=new JPanel();
			JLabel lab1=new JLabel("lista Impianti");
			JTextArea text=new JTextArea(2,20);
			for(Impianto x:piat.ImpList)
			 text.append(x.getNomeIm()+"\n");
			JLabel lab2=new JLabel("inserire nome impianto ");
			final JTextField lab1Field=new JTextField(10);
			JLabel lab3=new JLabel("inserire password impianto");
			final JTextField lab3Field=new JTextField(10);
			JButton sel=new JButton("trasferisci dashbord impianto selezionato");
			final JProgressBar progressBar = new JProgressBar();
			progressBar.setMaximum(100);
			progressBar.setValue(0);
			progressBar.setIndeterminate(false);
			
			class selLyst implements ActionListener 
			{
				public void actionPerformed(ActionEvent arg0)
				{
					final utility ut=new utility();
					final JFrame frame =new JFrame();
					frame.setSize(400,400);
					frame.setTitle("Trasferisci file ");
				    frame.setLocation(100, 100);
					JPanel panel=new JPanel();
					JLabel lab1=new JLabel("selezionare modalit� trasferimento");
					JButton testo=new JButton("formato txt");
					class TestoLyst implements ActionListener 
					{
						public void actionPerformed(ActionEvent arg0)
						{	
							boolean flag=true;
							PrintWriter out = null;
							try {
								 out =new PrintWriter("C:\\dashboard.txt");
								} catch (FileNotFoundException e) {e.printStackTrace();}
							for(Dashboard s:dashb)
							{
								if(s.getNomeImp().equalsIgnoreCase(lab1Field.getText())&&s.getPswImp().equalsIgnoreCase(lab3Field.getText()))
								{ 
							     progressBar.setValue(0);
							     for(String x:s.getdash())
									{ 
									flag=false;
									 out.println(s.getNomeImp()+" : "+ut.calDataString(x));
									}
								}
							}
							out.close();
							if(flag==false) JOptionPane.showMessageDialog(null, "file txt creato in unit� C:");
						  }
					  }
					//gestione tasto crea database
					JButton database=new JButton("formato db");
						class DBLyst implements ActionListener 
						{
							public void actionPerformed(ActionEvent arg0)
							{  
								for(Dashboard s:dashb)
								{
									if(s.getNomeImp().equalsIgnoreCase(lab1Field.getText())&&s.getPswImp().equalsIgnoreCase(lab3Field.getText()))
									{
										progressBar.setValue(0);
										progressBar.setIndeterminate(true);
										try {	
											Connection conn = null;
										      Statement st;
										      PreparedStatement psInsert = null;
										      String createString = "CREATE TABLE SENSORI   "
										    	        +  " (RILEVAZIONE VARCHAR(100) NOT NULL,"
										    	        + "  IDIMPIANTO VARCHAR(32) NOT NULL ) " ;
										      Class.forName(driver);
										      conn = DriverManager.getConnection(connectionURL);
										      st = conn.createStatement();
										      try {
										    	   st.execute(createString) ;
										    	   progressBar.setValue(70);
										          }catch(SQLException e1){}
										      psInsert = conn.prepareStatement(
										                "insert into SENSORI values (?, ?)");
											  for(String x:s.getdash())
											  { psInsert.setString(2, s.getNomeImp());
											    psInsert.setString(1, ut.calDataString(x));
											    psInsert.executeUpdate();
											   }
											  
											   st.execute("drop table SENSORI");
											   conn.commit();
											   st.close();
											   if (conn != null) 
											   {
										         conn.close();
										         conn = null;
										       }
											  
											 }
										     catch (ClassNotFoundException e){ e.printStackTrace();}
										     catch (SQLException e1) {e1.printStackTrace(); }
									}
								}
								progressBar.setValue(100);
								progressBar.setIndeterminate(false);
							}
						}	
				JButton back=new JButton("men� precedente");
				class backLyst implements ActionListener 
				{
				   public void actionPerformed(ActionEvent arg0)
					{
					frame.dispose();
					}
				}
				testo.addActionListener(new TestoLyst());
				database.addActionListener(new DBLyst());
				back.addActionListener(new backLyst());
				panel.add(lab1);
				panel.add(testo);
				panel.add(database);
				panel.add(progressBar);
				panel.add(back);
				frame.add(panel);
				frame.setVisible(true);
			  }
			}
		    sel.addActionListener(new selLyst());
			JButton back=new JButton("men� precedente");
			class backLyst implements ActionListener 
			{
				public void actionPerformed(ActionEvent arg0)
				{
					frame.dispose();
				}
			}	
			back.addActionListener(new backLyst());
			panel.add(lab1);
			panel.add(text);
			panel.add(lab2);
			panel.add(lab1Field);
			panel.add(lab3);
			panel.add(lab3Field);
			panel.add(sel);
			panel.add(back);
			frame.add(panel);
			frame.setVisible(true);
		}
	}
}
