
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
public class FavBtnHandler implements ActionListener {

    JFrame favfr;
    FavGUI fg;
    DefaultListModel<String> model;
    JList<String> favorite;
    FavMouseHandler fmh;
    
    public  FavBtnHandler(FavGUI f)
    {
        this.fg = f;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Add Favorite"))
        {
            fg.fr.dispose();
            fg.bh.gui.title = JOptionPane.showInputDialog("Enter title of current web page");
                         
            if(fg.bh.gui.title != null)
            {
                try {
                    PrintWriter pw = new PrintWriter(new FileWriter(new File("favourite.txt"),true));
                    pw.println(fg.bh.gui.u + " " + fg.bh.gui.title);
                    pw.close();
            
                }   catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        else if(e.getActionCommand().equals("View Favorite"))
        {
            fg.fr.dispose();
            
            favfr = new JFrame();
            model = new DefaultListModel<>();
            favorite = new JList<>(model);
            
            try 
        {
            BufferedReader br = new BufferedReader(new FileReader(new File("favourite.txt")));
            
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
        
        favfr.add(favorite);
        favfr.add(new JScrollPane(favorite));
        
        favfr.setSize(600,600);
        favfr.setLocationRelativeTo(null);
        favfr.setVisible(true);
        
        fmh = new FavMouseHandler(this);
        
        favfr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    }    
}