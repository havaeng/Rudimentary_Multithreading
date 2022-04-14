package Control;

import Boundary.GUI;
import java.io.File;
import java.time.LocalDateTime;

import Entity.MusicPlayer;
import Entity.TimeEntity;
import Entity.TextThread;

public class Controller {
    private final GUI gui = new GUI(this);
    private File file;
    private TextThread textThread;
    private MusicPlayer music;
    private TimeEntity timeEntity;


    public Controller(){
        gui.setUp();
    }

    public void setFloatingTextBounds(){
        gui.setFloatingTextBounds();
    }

    public void startText() {
        this.textThread = new TextThread(this);
    }

    public void setFile(File selectedFile) {
        this.file = selectedFile;
    }

    public File getFile(){
        return file;
    }

    public void startMusicPlayer(){
        this.music = new MusicPlayer(this);
    }

    public void setRunningFalseText() {
        textThread.setRunningFalse();
    }

    public void setRunningTrueMusic() {
        music.setRunningTrue();
    }

    public void setRunningFalseMusic(){
        music.setRunningFalse();
    }

    public void stopRotation() {
        timeEntity.setIsRunningFalse();
    }

    public void startRotation() {
        this.timeEntity = new TimeEntity(this);
    }

    public void updateTime(LocalDateTime now) {
        gui.updateTime(now);
    }

    public boolean getAliveMusic(){
        return music.threadAlive();
    }
}
