package com.mateusz;

import static com.mateusz.ThreadColour.RED;

public class NewThread extends Thread{

    @Override
    public void run(){

        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            System.out.println("Interrupted?");
        }

        Thread.currentThread().setName("Extended Thread");
        System.out.println(RED + "Thread -> " + Thread.currentThread().getName());
    }
}
