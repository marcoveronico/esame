package buttonListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 *classe che gestisce l'iterfaccia grafica della piattaforma
 */
public class PiattLyst implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		final JFrame frame =new JFrame();
		frame.setSize(400,400);
		frame.setTitle("Piattaforma IOT Inc.");
		frame.setLocation(100, 100);
		JPanel panel=new JPanel();
		JButton vis=new JButton("visiona dati impianto");
		vis.addActionListener(new VisualDatiLyst());
		JButton tras=new JButton("trasferisci dati raccolti a cliente");
		tras.addActionListener(new TrasDatiLyst());
		JButton acc=new JButton("accedi dashboard");
		acc.addActionListener(new VisulDashBoardLyst());
		JButton back=new JButton("menù precedente");
		class backLyst implements ActionListener 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				frame.dispose();
			}
		}	
		back.addActionListener(new backLyst());
		panel.add(vis);
		panel.add(tras);
		panel.add(acc);
		panel.add(back);
		frame.add(panel);
		frame.setVisible(true);
	}
}