package KevinBaconGame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.princeton.cs.algs4.Queue;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KevinBaconNumber extends JFrame
{

	private JPanel contentPane;
	//static ErrorScreen error = new ErrorScreen();
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
					KevinBaconNumber frame = new KevinBaconNumber();
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
	public KevinBaconNumber()
	{
		
		Toolkit.getDefaultToolkit().setDynamicLayout(true);

//		BaconGame game = new BaconGame(PickStartingCharacter.seed,PickEndingCharacter.destination);
//		Queue<String> path = game.getPath(PickStartingCharacter.seed,PickEndingCharacter.destination);
		
		try{
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			BaconGame game = new BaconGame(PickStartingCharacter.seed,PickEndingCharacter.destination);
			Queue<String> path = game.getPath(PickStartingCharacter.seed,PickEndingCharacter.destination);
  			int baconNumber = game.getBaconNumber();
  			JLabel lblNewLabel = new JLabel();
  			lblNewLabel.setText(PickStartingCharacter.seed + " and " + PickEndingCharacter.destination + " have a kevin bacon number of " + baconNumber);
  			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
  			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
  			lblNewLabel.setBounds(10, 111, 418, 28);
  			contentPane.add(lblNewLabel);
  			
  			JButton btnNewButton = new JButton("Explore Connections");
  			btnNewButton.addActionListener(new ActionListener() {
  				public void actionPerformed(ActionEvent e) 
  				{
  					Connections connection = new Connections();
  					connection.setVisible(true);
  				}
  			});
  			btnNewButton.setBounds(38, 184, 155, 23);
  			contentPane.add(btnNewButton);
  			
  			JButton btnNewButton_1 = new JButton("Start Over");
  			btnNewButton_1.addActionListener(new ActionListener() {
  				public void actionPerformed(ActionEvent e) 
  				{
  					KevinBaconStartPage start = new KevinBaconStartPage();
  					start.setVisible(true);
  				}
  			});
  			btnNewButton_1.setBounds(247, 184, 140, 23);
  			contentPane.add(btnNewButton_1);
         } catch(IllegalArgumentException e){ 
 			 PickEndingCharacter.error.setVisible(true);
 			PickEndingCharacter.error.setAlwaysOnTop(true);
         }
	}

}
