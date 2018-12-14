package com.ilensys;

import java.applet.*;
import java.awt.*;
import java.net.*;

import javax.swing.JOptionPane;

import java.awt.event.*;
import java.io.IOException;

public class TestApplet extends Applet implements ActionListener {
   public void init() {
      String link = "yahoo";
      Button b = new Button(link);
      b.addActionListener(this);
      add(b);
   }
   public void actionPerformed(ActionEvent ae) {
      Button src = (Button)ae.getSource();
      String link = "http://www."+src.getLabel()+".com";
      
      try {
        // AppletContext a = getAppletContext();
         URL u = new URL(link);
         Desktop.getDesktop().browse(new URI(link));
         this.setVisible(false);
        // this.stop();
      
        // a.showDocument(u,"_blank");
      } catch (MalformedURLException e){
         System.out.println(e.getMessage());
      } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   
   public void destroy()
   {
      // JOptionPane.showMessageDialog(null,"Applet : Destroyed"); 
   }
   
   public void stop()
   {
      // JOptionPane.showMessageDialog(null,"Applet : Stopped"); 
   }
}