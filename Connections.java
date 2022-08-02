package KevinBaconGame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;

public class Connections extends JFrame
{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Connections frame = new Connections();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Connections()
	{
		Toolkit.getDefaultToolkit().setDynamicLayout(true);

			BaconGame game = new BaconGame(PickStartingCharacter.seed,PickEndingCharacter.destination);
			Queue<String> path = game.getPath(PickStartingCharacter.seed,PickEndingCharacter.destination);
			if(path == null)
			{
				ErrorScreen error = new ErrorScreen();
				 error.setVisible(true);
			}
			else {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("These are the connections");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(100, 56, 220, 24);
			contentPane.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("");
			String connectionOutput = "";
			while (!path.isEmpty())
				{
					connectionOutput += path.dequeue() + " -> \"";
				}
			//lblNewLabel_1.setText(connectionOutput);
			lblNewLabel_1.setText("<html>"+ connectionOutput +"</html>");
			lblNewLabel_1.setBounds(88, 91, 265, 42);
			contentPane.add(lblNewLabel_1);
			
			
			
			JButton btnNewButton = new JButton("Start Over");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					KevinBaconStartPage start = new KevinBaconStartPage();
					start.setVisible(true);
				}
			});
			btnNewButton.setBounds(64, 153, 89, 23);
			contentPane.add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("Exit");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					 System.exit(0);
				}
			});
			btnNewButton_1.setBounds(264, 153, 89, 23);
			contentPane.add(btnNewButton_1);
			}
	}
}
