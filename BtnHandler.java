
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
public class BtnHandler implements ActionListener {

    GUIManager gui;
    HistoryGUI hi;
    FavGUI fav;
    FirewallGUI fw;
    
    public  BtnHandler(GUIManager g)
    {
        this.gui=g;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals("Go"))
        {
            if (gui.currentPage != "")
            {
                gui.prev.add(gui.currentPage);
            }
            gui.currentPage = gui.tfAddress.getText();
            gui.btnPrev.setEnabled(true);
            gui.loadPage(gui.tfAddress.getText());
        }
        //Refresh current page
        else if(gui.btnRef.equals(e.getSource()))
        {
            gui.loadPage(gui.webPage);
        }
        //Set home page
        else if(gui.btnHome.equals(e.getSource()))
        {
             gui.homePage = JOptionPane.showInputDialog("Enter URL to set home page");
            
                if(!(gui.homePage.equals(""))) {
                
                    try {
                    PrintWriter pw = new PrintWriter(new FileWriter(new File("homepage.txt")));
                    pw.println(gui.homePage);
                    pw.close();
            
                }   catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
                else
                {
                    JOptionPane.showMessageDialog(null,"Cannot enter blank input!","Invalid URL",
                            JOptionPane.ERROR_MESSAGE);
                }
        }
        //Add/View Favourite
        else if(gui.btnFav.equals(e.getSource()))
        {
            fav = new FavGUI(this);
        }
        //Move Back
         else if(gui.btnPrev.equals(e.getSource()))
        {
            if (gui.prev.isEmpty() || gui.currentPage == gui.prev.peek())
            {}
            else
            {
                gui.next.add(gui.currentPage);
                gui.currentPage = gui.prev.peek();
                gui.loadPage(gui.prev.pop());
                
                gui.btnNxt.setEnabled(true);
                
                if(gui.prev.isEmpty())
                {
                    gui.btnPrev.setEnabled(false);
                }
            }
        }
         //Move Forward
         else if(gui.btnNxt.equals(e.getSource()))
        {
            if (gui.next.isEmpty() || gui.currentPage == gui.next.peek())
            {}
            else
            {
                gui.prev.add(gui.currentPage);
                gui.currentPage = gui.next.peek();
                gui.loadPage(gui.next.pop());
                
                gui.btnPrev.setEnabled(true);
                
                if(gui.next.isEmpty())
                {
                    gui.btnNxt.setEnabled(false);
                }
            }
        }
         //Perform search and count in webpage
          else if(gui.btnSearch.equals(e.getSource()))
        {
            try 
            {
                gui.pageContent = gui.jepMain.getDocument().getText(0, gui.jepMain.getDocument().getLength());
            } 
            catch (Exception ex) 
            {
                ex.printStackTrace();
            }
            
            int count = 0;
            gui.criteria = JOptionPane.showInputDialog("Enter search criteria");
            
            String temp[] = gui.pageContent.split(" ");
            
            for(int i=0;i<temp.length;i++)
            {
                if(gui.criteria.equals(temp[i]))
                {
                    count++;
                }
            }
            
            JOptionPane.showMessageDialog(null,"Number of matches found: " + count);
        }
          //View History
          else if(gui.btnHist.equals(e.getSource()))
        {
           hi = new HistoryGUI(this);
        }
          //Firwall features
           else if(gui.btnFirewall.equals(e.getSource()))
        {
           fw = new FirewallGUI(this);
        }
        else
        {
            String url;
            url = e.getActionCommand();
            
            if (gui.currentPage != "")
            {
                gui.prev.add(gui.currentPage);
            }
            gui.currentPage = url;
            gui.btnPrev.setEnabled(true);
            gui.loadPage(url);
        }
    }
}