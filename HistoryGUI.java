
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
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
public class HistoryGUI {
    JFrame fr;
    DefaultListModel<String> model;
    JList<String> history;
    MouseHandler mh;
    BtnHandler bh;
    
    public HistoryGUI(BtnHandler b)
    {
        this.bh = b;
        initHistoryGUI();
        mh = new MouseHandler(this);
    }
    
    public void initHistoryGUI()
    {
        fr = new JFrame();
        fr.setTitle("History");
        model = new DefaultListModel<>();
        history = new JList<>(model);
        
        try 
        {
            BufferedReader br = new BufferedReader(new FileReader(new File("history.txt")));
            
            String line = br.readLine();
            
           model.addElement(line);
            
            while (line!=null) {                
                line = br.readLine();
                model.addElement(line);
            }
            br.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        fr.add(history);
        fr.add(new JScrollPane(history));
        
        fr.setSize(600,600);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
