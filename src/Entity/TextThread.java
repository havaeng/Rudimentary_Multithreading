package Entity;

import Control.Controller;

public class TextThread extends Thread {
    private final Controller controller;
    private boolean isRunning;

    /*
    Constructor. Requires an instance of Controller.
    **/
    public TextThread(Controller controller){
        this.controller = controller;
        isRunning = true;
        start();
    }

    /*
    Run method of text. So long as isRunning is true, a method in Controller will be run every 300 ms that updates
    the coordinates of the floating text in its JPanel. If isRunning is set to true during its runtime, the thread
    will run through its method and terminate. Overrides run()-method of Thread superclass.
    **/
    @Override
    public void run(){
        System.out.println("Text floater started with " + Thread.currentThread().getName());
        while (isRunning){
            controller.setFloatingTextBounds();
            try {
                Thread.sleep(150);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " ended.");
    }

    /*
    Sets the flag of the thread to false. The thread will terminate after this.
    **/
    public void setRunningFalse() {
        isRunning = false;
    }
}
