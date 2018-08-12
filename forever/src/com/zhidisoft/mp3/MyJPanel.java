package com.zhidisoft.mp3;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MyJPanel extends JPanel {

	private JButton confirm=new JButton("好");
	private JButton cancel=new JButton("不好");
	private JLabel  textLabel=new JLabel("小姐姐，做我女朋友好不好？");
	private JLabel  imageLabel=new JLabel();
	
	ImageIcon lierer = new ImageIcon(this.getClass().getResource("/89b1OOOPICa6.jpg"));
	
	public MyJPanel() {
		this.add(confirm);
		this.add(cancel);
		
		textLabel.setFont(new   java.awt.Font("Dialog",   1,   20)); 
		textLabel.setForeground(Color.WHITE);
		
		this.add(textLabel);
		this.add(imageLabel);
		imageLabel.setIcon(lierer);
		imageLabel.setVisible(true);
		confirm.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
//				confirm.setLocation(200, 200);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "从现在开始，只有我能欺负你，遇到困难要第一时间告诉我，不许藏着掖着");
				System.exit(0);
			}
		});
		
		cancel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				int x=(int) (Math.random()*(FirstFrame.frameWidth-65));
				int y=(int) (Math.random()*(FirstFrame.frameHeight-65));
				/*if (FirstFrame.frameWidth-x<cancel.getWidth()) {
				   x=(int) (Math.random()*FirstFrame.frameWidth);
				}
				if (FirstFrame.frameHeight-y<cancel.getHeight()) {
				   y=(int) (Math.random()*FirstFrame.frameHeight);
				}*/
				cancel.setLocation(x, y);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}
		});
	}
	
	@Override
	public void doLayout() {
		super.doLayout();
		//jButton.setBounds(50, 50, 100, 50);
		int width = confirm.getWidth();
		confirm.setLocation(230-width, 200);
		cancel.setLocation(270,200);
		int widths = textLabel.getFontMetrics(new   java.awt.Font("Dialog",   1,   20)).stringWidth("小姐姐，做我女朋友好不好？");
		int myWidth= FirstFrame.frameWidth;
		textLabel.setLocation((myWidth-widths)/2, 100);
		imageLabel.setLocation(0, 0);
	}
}
