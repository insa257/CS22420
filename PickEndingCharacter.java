package KevinBaconGame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.princeton.cs.algs4.Queue;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JLayeredPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionEvent;

public class PickEndingCharacter extends JFrame
{
	static String destination = "";
	private JPanel contentPane;
	static ErrorScreen error = new ErrorScreen();

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
					PickStartingCharacter frame = new PickStartingCharacter();
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
	public PickEndingCharacter()
	{
		Toolkit.getDefaultToolkit().setDynamicLayout(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PICK YOUR CHARACTERS");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(27, 11, 377, 39);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 61, 397, 193);
		contentPane.add(scrollPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		scrollPane.setViewportView(layeredPane);
		
		JLabel lblNewLabel_1 = new JLabel("ENDING CHARACTER");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(104, 23, 171, 14);
		layeredPane.add(lblNewLabel_1);
		
		String[] actorExamples = {
			null,	
			"Tom Hanks",
			"Jennifer Anniston",
			"Vince Vaughn"
		};
		JComboBox comboBox = new JComboBox(actorExamples);
		comboBox.setEditable(true);
		comboBox.setBounds(255, 86, 102, 22);
		layeredPane.add(comboBox);
		ActionListener actionListener = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) 
		      {
		    	  destination = (String) comboBox.getSelectedItem();
		      }
		    };
		    comboBox.addActionListener(actionListener);
		
		
		JLabel lblNewLabel_2 = new JLabel("<html>"+"SUGGESTION"+ "</html>");
		lblNewLabel_2.setBounds(10, 48, 94, 30);
		layeredPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("TOM CRUISE");
		btnNewButton.setBounds(47, 86, 108, 23);
		layeredPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("<html>"+ "PICK AN OPTION OR TYPE YOUR OWN" + "</html>");
		lblNewLabel_3.setBounds(159, 48, 198, 30);
		layeredPane.add(lblNewLabel_3);

		JButton btnNewButton_1 = new JButton("Next");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					KevinBaconNumber number = new KevinBaconNumber();
					number.setVisible(true);
				} catch (Exception e2)
				{
					
					 error.setVisible(true);
					 error.setAlwaysOnTop(true);
				}
			}
		});
		btnNewButton_1.setBounds(138, 137, 89, 23);
		layeredPane.add(btnNewButton_1);
	}
}
