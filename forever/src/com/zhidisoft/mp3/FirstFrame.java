package com.zhidisoft.mp3;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class FirstFrame extends Frame {
	
	private MyJPanel jPanel = new MyJPanel();
	
	public static int frameWidth=500;
	
	public static int frameHeight=500;
	
	int count=1;

	 public FirstFrame() {
		this.setTitle("勇敢爱一回");
		this.setSize(frameWidth,frameHeight);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.add(jPanel);
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				if (count==1) {
					JOptionPane.showMessageDialog(null, "工资全归你");
				}
				if (count==2) {
					JOptionPane.showMessageDialog(null, "家务全归我");
				}
				if (count==3) {
					JOptionPane.showMessageDialog(null, "都听你的");
					count=0;
				}
				count ++;
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				
				
			}
			
			
		});
		try {
			/*URL url = this.getClass().getResource("/程响 - 不再联系.mp3");
			String path = java.net.URLDecoder.decode(url.getPath(),"utf-8");  
			AudioPlayer audioPlayer=new AudioPlayer(new File(path));
			audioPlayer.play();*/
			InputStream resourceAsStream = this.getClass().getResourceAsStream("/程响 - 不再联系.mp3");
			BufferedInputStream buffer = new BufferedInputStream(resourceAsStream);
			Player player=new Player(buffer);
			player.play();
			} catch (JavaLayerException e) {
				e.printStackTrace();
			}
		
	}
	 
	 public static void main(String[] args) {
		new FirstFrame();
		
	}
}
