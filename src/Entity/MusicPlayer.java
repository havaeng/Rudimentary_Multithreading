package Entity;

import Control.Controller;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer implements Runnable {
    private Controller controller;
    private boolean isRunning;

    /*
    Constructor for this class. Requires a controller. Starts the thread of the Runnable-implementation by calling
    start() which in turn calls the implemented run()-method.
     */
    public MusicPlayer(Controller controller){
        this.controller = controller;
        new Thread(this).start();
    }

    /*
    The run()-method. Requires a File object which is provided from the GUI via the Controller-class. Opens an
    audio stream with the provided File object. A Clip is then created from the stream, and started. 
     */
    @Override
    public void run() {
        System.out.println("Music player started with " + Thread.currentThread().getName());
        try {
            File file = new File(String.valueOf(controller.getFile()));
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
            while (isRunning) {
                Thread.sleep(1000);
            }
            clip.stop();
        } catch (IOException | InterruptedException | UnsupportedAudioFileException | LineUnavailableException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " ended.");
    }

    /*
    Sets the flag of the thread to true. The thread will start after this.
    **/
    public void setRunningTrue() {
        isRunning = true;
    }

    /*
    Sets the flag of the thread to false. The thread will terminate after this.
    **/
    public void setRunningFalse() {
        isRunning = false;
    }

    public boolean threadAlive(){
        return Thread.currentThread().isAlive();
    }

    public String getThreadName() {
        return Thread.currentThread().getName();
    }
}
