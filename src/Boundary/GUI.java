package Boundary;

import Control.Controller;
import Entity.TextThread;
import java.awt.*;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.View;

public class GUI {
    private JFrame frame;		        // The Control.Main window
    private Random random = new Random();
    private JButton btnDisplayStart;	// Start thread moving display
    private JButton btnDisplayStop;	    // Stop moving display thread
    private JButton btnStartRotation;        // Start moving graphics thread
    private JButton btnStopRotation;	        // Stop moving graphics thread

    private JButton btnGo;		        // Start game catch me
    private JPanel pnlMove;		        // The panel to move display in
    private JPanel pnlRotate;	        // The panel to move graphics in
    private JPanel pnlGame;		        // The panel to play in

    private JTextArea txtHits;	        // Display hits
    private JComboBox cmbSkill;	        // Skill combo box, needs to be filled in
    private TextThread textThread;
    private JLabel timeLabel = new JLabel();
    private JLabel floatingText = new JLabel();
    private Controller controller;
    private File selectedFile;
    private JLabel lblPlaying;            // Playing text
    private JLabel lblAudio;            // Audio file
    private JButton btnOpen;            // Open audio file
    private JButton btnPlay;            // Start playing audio
    private JButton btnStop;            // Stop playing

    public GUI(Controller controller) {
        pnlMove = new JPanel();
        this.controller = controller;
    }

    public void setUp() {
        frame = new JFrame();
        frame.setBounds(0, 0, 819, 438);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setTitle("Multiple thread application");
        initializeGUI();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);	// Start middle screen
    }

    /**
     * Sets up the Boundary.GUI with components
     */
    private void initializeGUI() {
        //Music player
        // The music player outer panel

        JPanel pnlSound = new JPanel();
        Border b1 = BorderFactory.createTitledBorder("Music player");
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
                btnPlay.setEnabled(false);
            }
        });
        pnlSound.add(btnPlay);

        btnStop = new JButton("Stop");
        btnStop.setBounds(169, 71, 75, 23);
        btnStop.setEnabled(false);
        btnStop.addActionListener(l -> {
            controller.setRunningFalseMusic();
            btnPlay.setEnabled(true);
        });
        pnlSound.add(btnStop);

        frame.add(pnlSound);

        //Floating text
        JPanel pnlDisplay = new JPanel();
        Border b2 = BorderFactory.createTitledBorder("Floating text thread");

        pnlDisplay.setBorder(b2);
        pnlDisplay.setBounds(12, 118, 222, 269);
        pnlDisplay.setLayout(null);

        btnDisplayStart = new JButton("Start");
        btnDisplayStart.setBounds(10, 226, 75, 23);
        btnDisplayStart.addActionListener(l -> textStarted());
        pnlDisplay.add(btnDisplayStart);

        btnDisplayStop = new JButton("Stop");
        btnDisplayStop.setBounds(135, 226, 75, 23);
        btnDisplayStop.addActionListener(l -> textStopped());
        pnlDisplay.add(btnDisplayStop);

        pnlMove = new JPanel();
        pnlMove.setBounds(10,  19,  200,  200);
        Border b21 = BorderFactory.createLineBorder(Color.black);
        pnlMove.setBorder(b21);
        pnlDisplay.add(pnlMove);
        frame.add(pnlDisplay);

        //Rotating triangle

        // The moving graphics outer panel
        JPanel pnlTriangle = new JPanel();
        Border b3 = BorderFactory.createTitledBorder("Rotation thread");
        pnlTriangle.setBorder(b3);
        pnlTriangle.setBounds(240, 118, 222, 269);
        pnlTriangle.setLayout(null);

        // Add buttons and drawing panel to this panel
        btnStartRotation = new JButton("Start");
        btnStartRotation.setBounds(10, 226, 75, 23);
        btnStartRotation.addActionListener(l -> {
            controller.startRotation();
            btnStartRotation.setEnabled(false);
        });
        pnlTriangle.add(btnStartRotation);

        btnStopRotation = new JButton("Stop");
        btnStopRotation.setBounds(135, 226, 75, 23);
        btnStopRotation.addActionListener(l -> {
            controller.stopRotation();
            btnStartRotation.setEnabled(true);
            timeLabel.setText("Clock inactive");
        });
        pnlTriangle.add(btnStopRotation);

        pnlRotate = new JPanel();
        pnlRotate.setBounds(10,  19,  200,  200);
        Border b31 = BorderFactory.createLineBorder(Color.black);
        pnlRotate.setBorder(b31);
        pnlTriangle.add(pnlRotate);

        timeLabel = new JLabel();
        timeLabel.setBounds(0,50,20,20);

        timeLabel.setText("Clock inactive");
        pnlRotate.add(timeLabel);

        // Add this to main window
        frame.add(pnlTriangle);


        //Farid's game
        /*
        // The game outer panel
        JPanel pnlCatchme = new JPanel();
        Border b4 = BorderFactory.createTitledBorder("Catch Me");
        pnlCatchme.setBorder(b4);
        pnlCatchme.setBounds(468, 12, 323, 375);
        pnlCatchme.setLayout(null);

        // Add controls to this panel
        JLabel lblSkill = new JLabel("Skill:");
        lblSkill.setBounds(26, 20, 50, 13);
        pnlCatchme.add(lblSkill);
        JLabel lblInfo = new JLabel("Hit Image with Mouse");
        lblInfo.setBounds(107, 13, 150, 13);
        pnlCatchme.add(lblInfo);
        JLabel lblHits = new JLabel("Hits:");
        lblHits.setBounds(240, 20, 50, 13);
        pnlCatchme.add(lblHits);
        cmbSkill = new JComboBox();			// Need to be filled in with data
        cmbSkill.setBounds(19, 41, 61, 23);
        pnlCatchme.add(cmbSkill);
        btnGo = new JButton("GO");
        btnGo.setBounds(129, 41, 75, 23);
        pnlCatchme.add(btnGo);
        txtHits = new JTextArea();			// Needs to be updated
        txtHits.setBounds(233, 41, 71, 23);
        Border b40 = BorderFactory.createLineBorder(Color.black);
        txtHits.setBorder(b40);
        pnlCatchme.add(txtHits);
        pnlGame = new JPanel();
        pnlGame.setBounds(19, 71, 285, 283);
        Border b41 = BorderFactory.createLineBorder(Color.black);
        pnlGame.setBorder(b41);
        pnlCatchme.add(pnlGame);
        frame.add(pnlCatchme);
        */
    }

    private void textStopped() {
        controller.setRunningFalseText();
        btnDisplayStop.setEnabled(false);
        btnDisplayStart.setEnabled(true);
        floatingText.setText(null);
    }

    private void textStarted() {
        controller.startText();
        pnlMove.add(floatingText);
        btnDisplayStart.setEnabled(false);
        btnDisplayStop.setEnabled(true);
    }

    public void setFloatingTextBounds() {
        int x = random.nextInt(0,200);
        int y = random.nextInt(0,200);
        floatingText.setText(Thread.currentThread().getName());
        floatingText.setLocation(x, y);
    }

    public void updateTime(LocalDateTime now) {
        timeLabel.setText(String.valueOf(now).substring(11, 19));
    }
}