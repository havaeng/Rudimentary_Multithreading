package Entity;

import Control.Controller;

import java.time.LocalDateTime;

public class TimeEntity {
    private Controller controller;
    private boolean isRunning;

    /*
    Constructor. Requires a Controller. When constructor is called, it sets the boolean for the thread's while loop
    to true, and starts the thread of the inner class of this class.
    **/
    public TimeEntity(Controller controller){
        this.controller = controller;
        isRunning = true;
        start();
    }
    /*
    Sets the flag of the thread to false. The thread will terminate after this.
    **/
    public void setIsRunningFalse() {
        this.isRunning = false;
    }

    /*
    Method used to instantiate the inner class and call it's run()-method.
    **/
    public void start(){
        new TimeManager().start();
    }

    /*
    Inner class. Contains the run()-method.
    **/
    private class TimeManager extends Thread {

        /*
        Run-method of this thread. While isRunning is true, an instance of LocalDateTime is passed to the GUI
        via the Controller once per second. If isRunning is set to true during its runtime, the thread
        will run through its method and terminate. Overrides run()-method of Thread superclass.
        **/
        @Override
        public void run(){
            System.out.println("Clock started with " + Thread.currentThread().getName());
            while (isRunning){
                LocalDateTime now = LocalDateTime.now();
                controller.updateTime(now);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " ended.");
        }
    }
}
