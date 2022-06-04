
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Stack;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class GUIManager {
    JFrame fMain;
    JEditorPane jepMain;
    JPanel Buttons;
    JButton btnGo,btnPrev,btnNxt,btnHome,btnRef,btnHist,btnFav,btnSearch,btnFirewall;
    JTextField tfAddress;
    String webPage,homePage,title,currentPage="",pageContent,criteria;
    URL u;
    Stack<String> prev = new Stack<>();
    Stack<String> next = new Stack<>();
    Boolean fwStatus = false;
    String word;
    
    /** Creates a new instance of GUIManager */
    public GUIManager() {
        initGui();
    }
    private void initGui(){
        fMain = new JFrame();
        BtnHandler hndl;
        hndl = new BtnHandler(this);
        HyperLinkHandler hl;
        hl = new HyperLinkHandler(this);
      
        jepMain = new JEditorPane();
        jepMain.setPreferredSize(new Dimension(1280,700));
        jepMain.setEditable(false);
        
        jepMain.addHyperlinkListener(hl); 
        
        Buttons = new JPanel();
        Buttons.setLayout(new GridLayout(1, 8));
         
        Icon back = new ImageIcon((new ImageIcon("back.PNG").getImage().getScaledInstance(120, 100, Image.SCALE_DEFAULT)));
        Icon frwd = new ImageIcon((new ImageIcon("forward.PNG").getImage().getScaledInstance(120, 100, Image.SCALE_DEFAULT)));;
        Icon home = new ImageIcon((new ImageIcon("home.PNG").getImage().getScaledInstance(120, 100, Image.SCALE_DEFAULT)));;;
        Icon ref = new ImageIcon((new ImageIcon("refresh.PNG").getImage().getScaledInstance(120, 100, Image.SCALE_DEFAULT)));;;
        Icon hist = new ImageIcon((new ImageIcon("history.PNG").getImage().getScaledInstance(120, 100, Image.SCALE_DEFAULT)));;;
        Icon fav = new ImageIcon((new ImageIcon("fav.PNG").getImage().getScaledInstance(120, 100, Image.SCALE_DEFAULT)));;;
        Icon search = new ImageIcon((new ImageIcon("search.PNG").getImage().getScaledInstance(120, 100, Image.SCALE_DEFAULT)));;;
        Icon fw = new ImageIcon((new ImageIcon("firewall.PNG").getImage().getScaledInstance(120, 100, Image.SCALE_DEFAULT)));;;
        
        btnPrev = new JButton(back);
        btnNxt = new JButton(frwd);
        btnHome = new JButton(home);
        btnRef = new JButton(ref);
        btnHist = new JButton(hist);
        btnFav = new JButton(fav);
        btnSearch = new JButton(search);
        btnFirewall = new JButton(fw);
        
        Buttons.add(btnPrev);
        Buttons.add(btnNxt);
        Buttons.add(btnHome);
        Buttons.add(btnRef);
        Buttons.add(btnHist);
        Buttons.add(btnFav);
        Buttons.add(btnSearch);
        Buttons.add(btnFirewall);
        
        fMain.add(Buttons);
        
        //Loading firewall settings from a file
        try {
            String status;
            BufferedReader br = new BufferedReader(new FileReader(new File("fwstatus.txt")));
            status = br.readLine();
            br.close();
            
            if(status.equals("true"))
            {
                fwStatus = true;
            }
            else if(status.equals("false"))
            {
                fwStatus = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        btnPrev.setEnabled(false);
        btnNxt.setEnabled(false);
        
        btnGo = new JButton("Go");
        btnGo.addActionListener(hndl);
        
        tfAddress = new JTextField(110);
        tfAddress.addActionListener(hndl);
        
        //Refresh current page
        btnRef.addActionListener(hndl);
        
        //Set home page
        btnHome.addActionListener(hndl);
        
        //Add/View Favourite
        btnFav.addActionListener(hndl);
        
        //Move Back
        btnPrev.addActionListener(hndl);
        
        //Move Forward
        btnNxt.addActionListener(hndl);
        
        //Search in webpage
        btnSearch.addActionListener(hndl);
        
        //View History
        btnHist.addActionListener(hndl);
        
        //Firewall features
        btnFirewall.addActionListener(hndl);
                
        Container c = fMain.getContentPane();
        c.setLayout(new FlowLayout());
        
        fMain.add(tfAddress);
        fMain.add(btnGo);
        fMain.add(jepMain);
        fMain.add(new JScrollPane(jepMain));
        
        fMain.setSize(1300,720);
        fMain.setLocationRelativeTo(null);
        fMain.setVisible(true);
        fMain.setTitle("Browser");
        fMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Get home page from file to display at start
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("homepage.txt")));
            
            homePage = br.readLine();
            
            currentPage = homePage;
            
            //Display home page
            loadPage(homePage);
            
            br.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public void loadPage(String url){
        boolean found = false;
        try{
            if(fwStatus == true)
            {
                //Check if URL contains blocked key word
                try 
                {
                    BufferedReader br = new BufferedReader(new FileReader(new File("keywords.txt")));
                    
                    word = br.readLine();
                    
                    if(url.contains(word))
                    {
                        found = true;
                    }
                    while (word != null) {                
                        {
                            word = br.readLine();
                        }
                        if(word != null)
                        {   
                            if(url.contains(word))
                            {
                                found = true;
                            }
                        }
                    }
            
                    br.close();
       
                } catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }
            
            if(found == false)
            {jepMain.setPage(url);
            tfAddress.setText(url);
            webPage = url;
            u = new URL(url);
            
            //Save history into a text file
            try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy, HH:mm");  
            LocalDateTime now = LocalDateTime.now();  
            PrintWriter pw = new PrintWriter(new FileWriter(new File("history.txt"),true));
            
            pw.println(u + " - " + dtf.format(now));
            
            pw.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        }
            else if(found == true)
            {
                JOptionPane.showMessageDialog(null,"Warning! This page is blocked"
                        ,"Firewall Enabled",JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(IOException ioexp){
            JOptionPane.showMessageDialog(null,"page not found","bad url",JOptionPane.ERROR_MESSAGE);    
        }
    }
}