
/*
 * AboutDNAg.java
 *For the DNAg Tool : as a Submission of BTECH. Project for Btech. ICT Degree, 2013.
 *Submitted to Prof. Manish.K.Gupta.
 *
 *
 *	Created in April,2013
 *
 * @Authors Sonam Jain (200901118)and Priyanka Shukla (200901102)
*/  


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.*;


   
public class AboutDNAg extends JFrame
{
    JButton credit;
     JButton h;
     JLabel versio;
     JLabel d;
     JLabel c;
     JTextArea untitled_6;
     JPanel panelBgImgc;
     
   public AboutDNAg()
   {
   	ImageIcon imh = new ImageIcon("C://DNAg//DNAgicon.jpg");
panelBgImgc = new JPanel()
        {
            public void paintComponent(Graphics g) 
            {
                Image imgc = new ImageIcon("C://DNAg//DNAgicon.jpg").getImage();
                Dimension size = new Dimension(imgc.getWidth(null), imgc.getHeight(null));
        
                setLayout(null);
                g.drawImage(imgc, 0, 0, null);
            } 
        };
        
        getContentPane().add(panelBgImgc);
        panelBgImgc.setBounds(0, 0, imh.getIconWidth(), imh.getIconHeight());
        panelBgImgc.setLocation(20,20);
        panelBgImgc.setSize(100,100);
        panelBgImgc.setVisible(true);
        
        
        
     getContentPane().setLayout(null);
     setupGUI();
     //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   void setupGUI()
   {
     	credit = new JButton();
	credit.setLocation(0,154);
	credit.setSize(200,30);
	credit.setText("Credits");
	getContentPane().add(credit);
credit.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent ae)
            {
try{            Process show_structure=Runtime.getRuntime().exec("cmd /c START credits.pdf");	
    }catch(Exception e)        	{
    	System.out.println("error");
    }

            }});

	h = new JButton();
	h.setLocation(201,154);
	h.setSize(212,30);
	h.setText("Homepage");
	getContentPane().add(h);
	
	
	h.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent ae)
            {
try{            Process show_structure=Runtime.getRuntime().exec("cmd /c start firefox dnag.html");	
    }catch(Exception e)        	{
    	System.out.println("error");
    }

            }});


	versio = new JLabel();
	versio.setLocation(234,66);
	versio.setSize(77,25);
	versio.setText("Version : 1.0");
	getContentPane().add(versio);

	d = new JLabel();
	d.setLocation(256,41);
	d.setSize(39,25);
	d.setText("<html><b>DNAg</b><html>");
	getContentPane().add(d);

	c = new JLabel();
	c.setLocation(168,89);
	c.setSize(204,27);
	c.setText("<html>Copyright (c) 2013, Manish.K.Gupta<html>");
	getContentPane().add(c);
	
	

	 

                    
          /*untitled_6 = new JTextArea();
	untitled_6.setLocation(44,34);
	untitled_6.setSize(100,50);
	untitled_6.setText("text");
	untitled_6.setRows(5);
	untitled_6.setColumns(5);
	getContentPane().add(untitled_6);*/

	setTitle("About DNAg");
	setSize(423,217);
	setVisible(true);
	setResizable(true);
	
	
   }
   
      
        public static void main( String args[] )
   {
     new AboutDNAg();
   }
}