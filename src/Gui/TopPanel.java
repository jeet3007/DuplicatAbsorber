package Gui;

import FileFind.DefaultFileWalker;
import FileFind.FileEventListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class TopPanel extends JPanel implements ComponentMixin {
    private JLabel label ;
    private DefaultFileWalker fileWalker;
    private File selectedDirectory;
    private JTextField directoryField;
    private JButton BrowseButton;
    private LayoutManager layoutManager;
    private String Filename;
    private Interactor interactor;


    public String getFilename() {
        return Filename;
    }

    public void setInteractor(Interactor interactor) {
        this.interactor = interactor;
    }

    public TopPanel() {
        layoutManager = new BoxLayout(this, BoxLayout.X_AXIS);
        label = new JLabel("The directory:-");
        directoryField = new JTextField(40);
        BrowseButton = new JButton("Browse");
        fileWalker = new DefaultFileWalker();

        addListners();
        setLayout(layoutManager);

        Border raisedbevel = BorderFactory.createLoweredSoftBevelBorder();
        setBorder(raisedbevel);

        addComponents();
    }

    private void addComponents() {
        add(Box.createHorizontalStrut(10));
        add(label);
        add(Box.createHorizontalStrut(10));
        add(directoryField);
        add(Box.createHorizontalStrut(10));
        add(BrowseButton);
        add(Box.createHorizontalStrut(10));
    }

    private void addListners(){
        BrowseButton.addActionListener((ev)-> FileSelection());
    }

    public void addFileListener(FileEventListener fileEventListener){
        fileWalker.setFileEventListener(fileEventListener);
    }
    public void start(){
        try {
            Files.walkFileTree(Paths.get(directoryField.getText()),fileWalker);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void FileSelection(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int a=fileChooser.showOpenDialog(this);
        if(a ==JFileChooser.APPROVE_OPTION){
            selectedDirectory=fileChooser.getSelectedFile();
            directoryField.setText(selectedDirectory.getAbsolutePath());
            //check for the directory permissions
            if(interactor !=null){
                interactor.interact();
            }
        }





    }

    private boolean _validate(){
        return false;
    }


}
