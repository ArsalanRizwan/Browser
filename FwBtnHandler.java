
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class FwBtnHandler implements ActionListener {

    JFrame kwfr;
    JFrame fwsetfr;
    JRadioButton btnEn;
    JRadioButton btnDis;
    JButton btnAp;
    FirewallGUI fw;
    String keyword;
    DefaultListModel<String> model;
    JList<String> keywords;
    FwMouseHandler fwmh;
    
    public  FwBtnHandler(FirewallGUI f)
    {
        this.fw = f;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Add Key Word"))
        {
            fw.fr.dispose();
            keyword = JOptionPane.showInputDialog("Enter a key word to save ");
                         
            if(keyword != null && keyword != "")
            {
                try {
                    PrintWriter pw = new PrintWriter(new FileWriter(new File("keywords.txt"),true));
                    pw.println(keyword);
                    pw.close();
            
                }   catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        else if(e.getActionCommand().equals("Delete Key Word"))
        {
            fw.fr.dispose();
            
            JOptionPane.showMessageDialog(kwfr,"Select a key word to delete");
            
            kwfr = new JFrame();
            model = new DefaultListModel<>();
            keywords = new JList<>(model);
            
            try 
        {
            BufferedReader br = new BufferedReader(new FileReader(new File("keywords.txt")));
            
            String line = br.readLine();
            
           model.addElement(line);
            
            while (line!=null) {                
                line = br.readLine();
                model.addElement(line);
            }
            br.close();
            
        } catch (Exception ex) 
        {
            ex.printStackTrace();
        }
        
        kwfr.add(keywords);
        kwfr.add(new JScrollPane(keywords));
        
        kwfr.setSize(600,600);
        kwfr.setLocationRelativeTo(null);
        kwfr.setVisible(true);
        
        fwmh = new FwMouseHandler(this);
        
        kwfr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
        else if(e.getActionCommand().equals("Enable/Disable"))
        {
            fw.fr.dispose();
            
            fwsetfr = new JFrame();
            btnEn = new JRadioButton("Enable Firewall");   
            btnEn.setBounds(100,50,150,30);      
            btnDis = new JRadioButton("Disable Firewall");    
            btnDis.setBounds(100,100,150,30);    
            ButtonGroup bg = new ButtonGroup();    
            bg.add(btnEn);
            bg.add(btnDis);    
            btnAp = new JButton("Apply");    
            btnAp.setBounds(100,150,80,30);    
            
            btnAp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(btnEn.isSelected()){    
                fw.bh.gui.fwStatus = true;
                fwsetfr.dispose();
                
                //Saving firewall settings in a file
            try {
                PrintWriter pw = new PrintWriter(new FileWriter(new File("fwstatus.txt")));
                pw.println(fw.bh.gui.fwStatus);
                pw.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            }    
            else if(btnDis.isSelected()){    
                fw.bh.gui.fwStatus = false;
                fwsetfr.dispose();
                
                //Saving firewall settings in a file
            try {
                PrintWriter pw = new PrintWriter(new FileWriter(new File("fwstatus.txt")));
                pw.println(fw.bh.gui.fwStatus);
                pw.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            }    
            }
        });    
            fwsetfr.add(btnEn);
            fwsetfr.add(btnDis);
            fwsetfr.add(btnAp);    
            fwsetfr.setSize(300,300);    
            fwsetfr.setLayout(null);
            fwsetfr.setLocationRelativeTo(null);
            fwsetfr.setResizable(false);
            fwsetfr.setVisible(true);
        }  
    }
}
