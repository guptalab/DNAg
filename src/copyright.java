  
//package DNAg;
//Code Genarated by JGuiD
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class copyright extends JFrame
{
    JLabel cp;
     JButton ok;
     
   public copyright()
   {
     getContentPane().setLayout(null);
     setupGUI();
    // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   void setupGUI()
   {
     cp = new JLabel("",SwingConstants.CENTER);
	cp.setLocation(50,30);
	cp.setSize(590,380);
	cp.setFont(new Font(" ", Font.PLAIN, 12));
	cp.setText("<html> <p align=center >  #=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=# <BR> DNAg: A DNA Self Assembly Generater <BR>    #=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=# <BR>  License for use and distribution <BR> #==================================# <BR> (C) 2012 Manish K Gupta , Laboratory of Natural Information Processing  DA-IICT,<BR> Gandhinagar, Gujarat 382007   <BR> <br> <BR> Email: mankg@computer.organization <BR> <BR>  This software is available as an open source (approval pending from Open Source Initiatives)to academic, non-profit institutions etc. under an open source license agreement and may be used only in accordance with the terms of the agreement.   Any selling or distribution of the program or its parts, original or modified, is prohibited without a written permission from Manish K Gupta.    <BR>================================  <BR>   Team <BR>  Principle Investigator: Manish K. Gupta, PhD.;  Key Developers:  Sonam Jain* and Priyanka Shukla*  <BR> * Key developers contributed equally to the project <BR> <BR>  Note: This Software is a Teacing Tool and you can use it on any computer, including a computer in a commercial organization. You don't need to register or pay for DNAg.</p></html>");
	getContentPane().add(cp);
	
JLabel link = new JLabel("<html><u>http://www.guptalab.org/DNAg\n</u></html>");
link.setLocation(255,145);
link.setSize(250,50);
link.setForeground(Color.blue);
link.setVisible(true);
getContentPane().add(link);
link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
link.addMouseListener(new MouseAdapter() {
public void mouseClicked(MouseEvent e) {
if (e.getClickCount() > 0) {
if (Desktop.isDesktopSupported()) {
Desktop desktop = Desktop.getDesktop();
try {
URI uri = new URI("http://www.guptalab.org/DNAg");
desktop.browse(uri);
} catch (IOException ex) {
// do nothing
} catch (URISyntaxException ex) {
//do nothing
}
} else {
//do nothing
}
 
}
}
});

	ok = new JButton();
	ok.setLocation(277,430);
	ok.setSize(100,25);
	ok.setText("OK");
	getContentPane().add(ok);
	ok.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent ae)
            {
    setVisible(false);}});	
	
	setTitle("Copyright");
	setSize(700,540);
	setVisible(true);
	setResizable(true);
	
	
   }
    public static void main( String args[] )
   {
     new copyright();
   }
   }  
