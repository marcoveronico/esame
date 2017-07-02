package buttonListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import progr.Dashboard;
import progr.Impianto;
import progr.utility;;

/**
 * 
 * classe che gestisce la visualizzazione della tabella riassuntiva 
 * delle rilevazioni di tutti i sensori installati nell'impianto
 */

public class VisulDashBoardLyst extends Test implements ActionListener 
{
	public void actionPerformed(ActionEvent arg0)
	{
		if(dashb.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "nessun impianto installato");
		}
		else
		{	
			//frame per la visualizzazione della tabella riassuntiva
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
			final JTextField lab1Field=new JTextField(15);
			JLabel lab3=new JLabel("inserire password impianto");
			final JPasswordField psw=new JPasswordField(15);
			JButton sel=new JButton("accedi dashbord impianto selezionato");
			
			class selLyst implements ActionListener 
			{
				public void actionPerformed(ActionEvent arg0)
				{
					utility ut=new utility();
					JFrame frame =new JFrame();
					frame.setSize(600,500);
					frame.setTitle("Impianto ");
				    frame.setLocation(100, 100);
					JPanel panel=new JPanel();
					JLabel lab1=new JLabel("elenco sensori dashbord");
					JTextArea text=new JTextArea(25,50);
					//controllo della corrispondenza impianto-password
					boolean flag=true;
					for(Dashboard s:dashb)
					{
						if(s.getNomeImp().equalsIgnoreCase(lab1Field.getText())&&s.getPswImp().equalsIgnoreCase(String.copyValueOf(psw.getPassword())))
						{ flag=false;
							for(String x:s.getdash())
							{ 
							  text.append(s.getNomeImp()+" : " +ut.calDataString(x)+"\n");
						    }
						}
					}
					if(flag) 	text.append("password errata");
					JScrollPane scroll=new JScrollPane(text);
					panel.add(lab1);
					panel.add(scroll);
					frame.add(panel);
					frame.setVisible(true);
				}
			}
			sel.addActionListener(new selLyst());
			JButton back=new JButton("menù precedente");
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
			panel.add(psw);
			panel.add(sel);
			panel.add(back);
			frame.add(panel);
			frame.setVisible(true);
		}
	}
}
