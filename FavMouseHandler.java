
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class FavMouseHandler implements MouseListener{ 

    FavBtnHandler fbh;
    
    public FavMouseHandler(FavBtnHandler f)
    {
        this.fbh = f;
        fbh.favorite.addMouseListener(this);
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        String value = (String)fbh.favorite.getModel().getElementAt(fbh.favorite.locationToIndex(me.getPoint()));
        String temp[] = value.split(" ");
        
        if (fbh.fg.bh.gui.currentPage != "")
            {
               fbh.fg.bh.gui.prev.add(fbh.fg.bh.gui.currentPage);
            }
            fbh.fg.bh.gui.currentPage = temp[0];
            
            fbh.fg.bh.gui.btnPrev.setEnabled(true);
            fbh.fg.bh.gui.loadPage(temp[0]);
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