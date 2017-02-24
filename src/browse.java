import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.awt.*;

import javax.swing.JOptionPane;


 import java.awt.event.*;
import javax.swing.*;

public class browse {
 
  public JFrame frame;
  public JTextField txtPath;
 static browse window = new browse();
 
  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
       try {
          
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
 
  /**
   * Create the application.
   */
  public browse() {
    initialize();
  }
 
  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100,100,500,200);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);
         
    txtPath = new JTextField();
    txtPath.setBounds(20, 50, 350, 20);
    frame.getContentPane().add(txtPath);
    txtPath.setColumns(10);
    
    JButton ok = new JButton("OK");
    ok.setBounds(150, 80, 80, 20);
    frame.getContentPane().add(ok);
    
    JButton cancel = new JButton("Cancel");
    cancel.setBounds(250, 80, 80, 20);
    frame.getContentPane().add(cancel);
    
    cancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	System.exit(0);
      	}});
    
    
    ok.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String address=txtPath.getText();
        String address1="";
//        char a="/";


if(txtPath.getText().isEmpty())
            	{
            	
            		JOptionPane.showMessageDialog(null, "PLease Enter Path of Package, It can't be Empty");
   		    	
   		    	
              	}
              	
              	
        for(int i=0;i<address.length();i++)
        {
        	if( (int) address.charAt(i)== 92)
        	{
        	System.out.println("i encountered");
        	address1=address1+"//";}
        	else
        	address1=address1+address.charAt(i);
        }
   
   	
              	
        System.out.println(address);
         System.out.println(address1);
window.frame.setVisible(false);
       new output_jframe(address1);
        
      }
    });
    
    
         
    JButton btnBrowse = new JButton("Browse");
    btnBrowse.setBounds(390, 50, 80, 20);
    frame.getContentPane().add(btnBrowse);
         
    btnBrowse.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
 
        // For Directory
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
 
        // For File
        //fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
 
        fileChooser.setAcceptAllFileFilterUsed(false);
 
        int rVal = fileChooser.showOpenDialog(null);
        if (rVal == JFileChooser.APPROVE_OPTION) {
          txtPath.setText(fileChooser.getSelectedFile().toString());
        }
      }
    });
  }
}