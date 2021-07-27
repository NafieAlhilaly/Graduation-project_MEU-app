import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import net.sf.jasperreports.engine.JRException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.log.SysoLogger;

public class ProgressBar extends Thread implements ActionListener{
	 public static JFrame frame;
	 public static JLabel done_label = new JLabel("process done!");
	 public static JButton button = new JButton("OK");
	 public static JProgressBar progress_bar; 
     public void run() {
    		 
		  
     }
	 
   
		

	 @Override
	 public void actionPerformed(ActionEvent e) {
		 if (e.getSource()==button) {
System.exit(0);
		 }
		 
	 }
	 
	public void create(){
		   // create a frame 
				 frame = new JFrame("processing"); 
				  
				        // create a panel 
				 JPanel p = new JPanel(); 
				  
				        // create a progressbar 
				 progress_bar = new JProgressBar(); 
				
				 p.setBounds(70, 20, 150, 40);
				 done_label.setBounds(100,65, 150, 20);
				 button.setBounds(70, 100, 150, 40);
				
				
				    
				 frame.setLocation(700, 350);
				 frame.setSize(310,200);
				 frame.setLayout(null);  
				
				 button.setVisible(true);
				 done_label.setVisible(false);
				 
				 frame.setVisible(true); 
				 button.setVisible(false);
				 done_label.setVisible(false); 
				
				 button.addActionListener(this);	
				  

				  
				
				  
				        // set initial value 
				 progress_bar.setValue(0); 
				 progress_bar.setStringPainted(true);
				  
				 frame.add(p);
				 frame.add(done_label);
				 frame.add(button);   
				 p.add(progress_bar);     
				 
				 
				
	}
	
	public void fill(int progress){
		progress_bar.setValue(progress);
		
	}
	 
}
