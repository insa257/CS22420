package KevinBaconGame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class ErrorScreen extends JFrame
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
					ErrorScreen frame = new ErrorScreen();
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
	public ErrorScreen()
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("This Connection doesn't exist");
		lblNewLabel.setBounds(125, 103, 204, 59);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Back To Start Page");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				KevinBaconStartPage start = new KevinBaconStartPage();
				start.setVisible(true);
			}
		});
		btnNewButton.setBounds(125, 173, 156, 23);
		contentPane.add(btnNewButton);
	}

}
