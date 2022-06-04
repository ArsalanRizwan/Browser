
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
public class FirewallGUI {
    JFrame fr;
    JPanel Buttons;
    FwBtnHandler fwbh;
    BtnHandler bh;
    JButton btnAdd,btnDel,btnStatus;
    
    public FirewallGUI(BtnHandler b)
    {
        this.bh = b;
        initFavGUI();
    }
    
    public void initFavGUI()
    {
        fr = new JFrame();
        fr.setTitle("Firewall Settings");
        Buttons = new JPanel();
        fwbh = new FwBtnHandler(this);
        Buttons.setLayout(new FlowLayout());
        btnAdd = new JButton("Add Key Word");
        btnDel = new JButton("Delete Key Word");
        btnStatus = new JButton("Enable/Disable");
        
        Buttons.add(btnAdd);
        Buttons.add(btnDel);
        Buttons.add(btnStatus);
        
        btnAdd.setPreferredSize(new Dimension(150, 40));
        btnDel.setPreferredSize(new Dimension(150, 40));
        btnStatus.setPreferredSize(new Dimension(150, 40));
        
        btnAdd.addActionListener(fwbh);
        btnDel.addActionListener(fwbh);
        btnStatus.addActionListener(fwbh);
       
        fr.add(Buttons);
        fr.setSize(400,130);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
        fr.setResizable(false);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
