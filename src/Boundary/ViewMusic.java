/*package Boundary;

import Control.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;

public class ViewMusic {
    private JLabel lblPlaying;            // Playing text
    private JLabel lblAudio;            // Audio file
    private JButton btnOpen;            // Open audio file
    private JButton btnPlay;            // Start playing audio
    private JButton btnStop;            // Stop playing
    private Controller controller;
    private File selectedFile;
    private GUI gui;

    public ViewMusic(GUI gui) {
        this.gui = gui;
        initialize();
    }

    public void initialize() {
        JPanel pnlSound = new JPanel();
        Border b1 = BorderFactory.createTitledBorder("Music Player");
        pnlSound.setBorder(b1);
        pnlSound.setBounds(10, 12, 450, 100);
        pnlSound.setLayout(null);

        lblPlaying = new JLabel("");
        lblPlaying.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblPlaying.setBounds(20, 16, 450, 20);
        pnlSound.add(lblPlaying);

        JLabel lbl1 = new JLabel("Path: ");
        lbl1.setBounds(10, 44, 500, 13);
        pnlSound.add(lbl1);

        lblAudio = new JLabel();
        lblAudio.setBounds(115, 44, 300, 13);
        pnlSound.add(lblAudio);

        btnOpen = new JButton("Open");
        btnOpen.setBounds(6, 71, 75, 23);
        btnOpen.addActionListener(l -> {
            try {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

                int response = fileChooser.showOpenDialog(frame);

                if (response == JFileChooser.APPROVE_OPTION) {
                    selectedFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    btnStop.setEnabled(true);
                    btnPlay.setEnabled(true);
                }

                if (selectedFile == null)
                    JOptionPane.showMessageDialog(null, "Error: no file chosen.");

                lblPlaying.setText("Now playing: " + selectedFile.getName());
                lbl1.setText("Path: " + selectedFile.getAbsolutePath());
                controller.setFile(selectedFile);

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        });
        pnlSound.add(btnOpen);

        btnPlay = new JButton("Play");
        btnPlay.setBounds(88, 71, 75, 23);
        btnPlay.setEnabled(false);
        btnPlay.addActionListener(l -> {
            if (selectedFile == null) {
                JOptionPane.showMessageDialog(null, "Error: choose a file first.");
            } else {
                controller.startMusicPlayer();
                controller.setRunningTrueMusic();
            }
        });
        pnlSound.add(btnPlay);

        btnStop = new JButton("Stop");
        btnStop.setBounds(169, 71, 75, 23);
        btnStop.setEnabled(false);
        btnStop.addActionListener(l -> {
            controller.setRunningFalseMusic();
        });
        pnlSound.add(btnStop);
        gui.addPanel();
    }
}*/
