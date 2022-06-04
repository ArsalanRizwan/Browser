
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class FwMouseHandler implements MouseListener{
    
    FwBtnHandler fwbh;
    
    public FwMouseHandler(FwBtnHandler f)
    {
        this.fwbh = f;
        fwbh.keywords.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        String value = (String)fwbh.keywords.getModel().getElementAt(fwbh.keywords.locationToIndex(me.getPoint()));
        
         try 
        {
            BufferedReader br = new BufferedReader(new FileReader(new File("keywords.txt")));
   
            PrintWriter pw = new PrintWriter(new FileWriter(new File("temp.txt")));
                   
            String line = br.readLine();
            
            if(!(line.equals(value)))
            {
                pw.println(line);
            }
            
            while (line != null) {                
                line = br.readLine();
                
                if(line != null)
                {   
                    if(!(line.equals(value)))
                    {
                        pw.println(line);
                    }
                }
            }
        
            br.close();
            pw.close();
        
        } catch (Exception e) {
            e.printStackTrace();
        }
         
         File k = new File("keywords.txt");
         k.delete();
         
         File t =new File("temp.txt");
         t.renameTo(k);
        
         JOptionPane.showMessageDialog(null,"Key word deleted");
         
         fwbh.model.removeElement(value);
    }
    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }    
}