import javax.swing.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

import java.io.File;
import java.util.Scanner;

public class StopWatch extends Applet implements Runnable, ActionListener
{

    // Create panel, label and 2 buttons and add Audio clip
    JPanel panel1 = new JPanel();
    JLabel label1 = new JLabel();
    JButton start = new JButton("Start");
    JButton stop = new JButton("Stop");
    // create an AudioClip object called step
    AudioClip step;

    // create time in hours, minutes, seconds, and millisecond for stop watch
    int hour;
    int minute;
    int second;
    int millisecond;

    //  display time as string
    String displaytime;

    // boolean state of stopwatch as on or off
    boolean on;

    // initialization
    public void init()
    {
        // set the StopWay as initially off
        on = false;

        //add the GridLayout for the panel
        panel1.setLayout(new GridLayout(4, 1,4, 4));
        setSize(250,250); //set Size of GridLayout
        // initialize time variables
        hour = 0;
        minute = 0;
        second = 0;
        millisecond = 0;

        displaytime = "00 : 00 : 00 : 000";  // displaytime string initialized format in minutes and seconds
        label1.setText(displaytime); //put the displaytime string into the label
        panel1.add(label1); //put the label into the panel

        // Add ActionListener to Start button and add Start button to panel
        start.addActionListener((ActionListener) this);
        panel1.add(start);

        // Add ActionListener to Stop button and add Stop button to panel
        stop.addActionListener((ActionListener) this);
        panel1.add(stop);

        add(panel1); //add panel to content pane

        // starting thread for StopWatch class , with Runnable and later for run()
        new Thread(this, "StopWatch").start();

        // create an AudioClip object and the location of audio clip
        AudioClip step = getAudioClip(getDocumentBase(), "C:\\step.wav");

    }

    // update function  and the timer

    public void update()
    {
        millisecond++;
        if (millisecond == 1000)
        {
            millisecond = 0;
            second++;
            if (second == 60)
            {
                second = 0;
                minute++;
                if (minute == 60)
                {
                    minute = 0;
                    hour++;
                }
            }
        }
    }

    // changing label 
    public void changeLabel()
    {

        // Properly formatting the displaytime
        if (hour < 10)
            displaytime = "0" + hour + " : ";
        else
            displaytime = hour + " : ";

        if (minute < 10)
            displaytime += "0" + minute + " : ";
        else
            displaytime += minute + " : ";

        if (second < 10)
            displaytime += "0" + second + " : ";
        else
            displaytime += second + " : ";

        if (millisecond < 10)
            displaytime += "00" + millisecond;
        else if (millisecond < 100)
            displaytime += "0" + millisecond;
        else
            displaytime += millisecond;

        label1.setText(displaytime);
    }

    // thread run function
        public void run()
    {
        // this runs while the stopwatch is on
        while (on)
        {
            try
            {
                // pause 1 millisecond 
                Thread.sleep(1);
                // update the time 
                update();
                // changeLabel 
                changeLabel();
            } catch (InterruptedException e)
            {
                System.out.println(e);
            }
        }
    }

    // ActionPerformed after the buttons are clicked
    public void actionPerformed(ActionEvent e)
    {
        // if Start button is clicked
        if (e.getSource() == start)
        {
            on = true;
            new Thread(this, "StopWatch").start();
            //step.play(); //plays the audio object here
        }
        // if Stop button is clicked
        if (e.getSource() == stop)
        {
            on = false;
            step.play(); //plays the audio object here
        }
    }








}