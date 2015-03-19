package Gui;

import FileFind.FileEventListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Girish on 19-03-2015.
 */
public class startButtonEventlistener implements ActionListener, FileEventListener {

    private TopPanel topPanel;


    public startButtonEventlistener(TopPanel topPanel){
        this.topPanel = topPanel;

    }



    public void setTopPanel(TopPanel topPanel) {
        this.topPanel = topPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        topPanel.addFileListener();

    }
}