package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;

import javax.swing.SwingConstants;

import engines.GameEngine;

import java.awt.Font;


public class GameOverView extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public GameOverView() {
		setTitle("GAME OVER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 294);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnYes = new JButton("YES");
		btnYes.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				GameEngine.getInstance().startAgain();
				dispose();
			}
		});
		
		JLabel lblNewLabel = new JLabel("Game Over. Play Again?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnNo = new JButton("NO");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				System.exit(0);
			}
		});
		btnNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblYourScore = new JLabel("Your Score: " + GameEngine.getInstance().player.getScore());
		lblYourScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourScore.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnYes, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 257, Short.MAX_VALUE)
							.addComponent(btnNo, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
						.addComponent(lblYourScore, GroupLayout.PREFERRED_SIZE, 456, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblYourScore, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addGap(44)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNo, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
						.addComponent(btnYes, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
