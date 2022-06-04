
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class HyperLinkHandler implements HyperlinkListener {

    GUIManager gui;
    
    public HyperLinkHandler(GUIManager g)
    {
        this.gui=g;
    }
    
    @Override
    public void hyperlinkUpdate(HyperlinkEvent e) {
    
        if(e.getEventType()==HyperlinkEvent.EventType.ACTIVATED)
        {
            if (gui.currentPage != "")
            {
                gui.prev.add(gui.currentPage);
            }
            gui.currentPage = e.getURL().toString();
            gui.btnPrev.setEnabled(true);
            gui.loadPage(e.getURL().toString());
               
        }
    
    }
}
