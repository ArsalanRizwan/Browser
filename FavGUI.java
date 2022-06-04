
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class FavGUI {
    JFrame fr;
    JPanel Buttons;
    FavBtnHandler fbh;
    BtnHandler bh;
    JButton btnAdd,btnView;
    
    public FavGUI(BtnHandler b)
    {
        this.bh = b;
        initFavGUI();
    }
    
    public void initFavGUI()
    {
        fr = new JFrame();
        fr.setTitle("Favorites");
        Buttons = new JPanel();
        fbh = new FavBtnHandler(this);
        Buttons.setLayout(new FlowLayout());
        btnAdd = new JButton("Add Favorite");
        btnView = new JButton("View Favorite");
        
        Buttons.add(btnAdd);
        Buttons.add(btnView);
        
        btnAdd.setPreferredSize(new Dimension(150, 40));
        btnView.setPreferredSize(new Dimension(150, 40));
        
        btnAdd.addActionListener(fbh);
        btnView.addActionListener(fbh);
       
        fr.add(Buttons);
        fr.setSize(400,100);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
        fr.setResizable(false);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}