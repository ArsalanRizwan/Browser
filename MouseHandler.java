
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
public class MouseHandler implements MouseListener{

    HistoryGUI hi;

    public MouseHandler(HistoryGUI hi) {
        this.hi = hi;
        hi.history.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        String value = (String)hi.history.getModel().getElementAt(hi.history.locationToIndex(me.getPoint()));
        String temp[] = value.split(" ");
        
        if (hi.bh.gui.currentPage != "")
            {
                hi.bh.gui.prev.add(hi.bh.gui.currentPage);
            }
            hi.bh.gui.currentPage = temp[0];
            
            hi.bh.gui.btnPrev.setEnabled(true);
            hi.bh.gui.loadPage(temp[0]);
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
