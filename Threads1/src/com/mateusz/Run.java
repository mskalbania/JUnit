package com.mateusz;

import static com.mateusz.ThreadColour.CYAN;
import static com.mateusz.ThreadColour.GREEN;

public class Run {

    public static void main(String[] args) {

        //Different ways to start new threads, interrupts, joins and sleeps

        System.out.println("Thread -> " + Thread.currentThread().getName());

        //Extend Thread class
        NewThread secondThread = new NewThread();
        secondThread.start();

        //Anonymous Thread class implementing Runnable
        Thread thirdThread = new Thread(){
            @Override
            public void run(){

                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    System.out.println(CYAN + "Interrupted?");
                    return;
                }

                Thread.currentThread().setName("Anonymous Thread");
                System.out.println(CYAN + "Thread -> " + Thread.currentThread().getName());
            }
        };
        thirdThread.start();

        //Passing lambda to Thread constructor
        Thread fourthThread = new Thread(() -> {
            try {
                //joining to red thread and waiting for execution
                System.out.println(GREEN + "Joined red. Waiting for it to be executed");
                secondThread.join(10000  );
                Thread.sleep(500);
            }catch (InterruptedException e){
                System.out.println("Interrupted?");
            }
            Thread.currentThread().setName("Lambda Thread");
            System.out.println(GREEN + "Thread -> " + Thread.currentThread().getName());
        });
        fourthThread.start();

        //interrupt cyan thread
        thirdThread.interrupt();

    }
}
