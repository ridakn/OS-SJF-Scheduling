import java.io.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

public class SJFSchedule {

    public void userInputSJF() {
        
        //Method to implement SJF using user input
        double totalT = 0;
        
        Scanner r = new Scanner(System.in);
        System.out.println("Please enter number of processes: ");
        int N = r.nextInt();
        
        double arrivalTimes[], burstTimes[], waitingTime[], turnATime[], wT = 0, tAT = 0;
        arrivalTimes = new double[N+1];
        burstTimes = new double[N+1];
        double totalTime[];
        totalTime = new double[N];
        waitingTime = new double[N+1];
        turnATime = new double[N];
        
        int P[];
        P = new int[N];
        
        //Taking in user input
        for(int i = 0; i < N; i++) {
            
            System.out.println("Please enter arrival time for process " + (i+1));
            arrivalTimes[i] = r.nextInt();
            P[i] = i+1;
        }
        
        for(int i = 0; i < N; i++) {
            System.out.println("Please enter burst time for process " + (i+1));
            burstTimes[i] = r.nextInt();
        }
        
        double temp = 0;
        int t = 0;
        
        //Sorting process according to arrival time, first to last.
        for(int i = 0; i < N; i++) {
            
            for(int j = 0; j < N; j++) {
                
                if(arrivalTimes[i] < arrivalTimes[j]) {
                    
                    temp = arrivalTimes[i];
                    arrivalTimes[i] = arrivalTimes[j];
                    arrivalTimes[j] = temp;
                    
                    temp = burstTimes[i];
                    burstTimes[i] = burstTimes[j];
                    burstTimes[j] = temp;
                    
                    t = P[i];
                    P[i] = P[j];
                    P[j] = t;
                }
            }   
        }    
        
        System.out.println("Process \t Arrival \t Burst \t Waiting \t Turn around");
        
        //Sorting processes according to arrival time and burst time where burst time is the smallest one and arrival time is less than the current total time.
        double smallestB = 0;
        int j = 1;
        totalT += arrivalTimes[0];
        for (int i = 0; i < N; i++) {
            
            totalT += burstTimes[i];
            totalTime[i] = totalT;
            smallestB = burstTimes[j];
            
            for(int m = j; m < N; m++) {
                
                if((arrivalTimes[m] <= totalT) && (burstTimes[m] < smallestB)) {
                    
                    temp = arrivalTimes[j];
                    arrivalTimes[j] = arrivalTimes[m];
                    arrivalTimes[m] = temp;
                    
                    temp = burstTimes[j];
                    burstTimes[j] = burstTimes[m];
                    burstTimes[m] = temp;
                    
                    t = P[j];
                    P[j] = P[m];
                    P[m] = t;
                    
                }
            }
            
            waitingTime[i+1] = totalTime[i] - arrivalTimes[i+1];
            turnATime[i] = totalTime[i] - arrivalTimes[i];
            j++; 
            
        }
        
        
        for(int i = 0; i < N; i++) {
            System.out.println(P[i] + "\t\t" + arrivalTimes[i] + "\t\t" + burstTimes[i] + "\t\t" + waitingTime[i] + "\t\t" + turnATime[i]);
            wT += waitingTime[i];
            tAT += turnATime[i];
        }

        double avgWT = wT / N;
        double avgTAT = tAT / N;
        double avgCT = totalT / N;
        
        System.out.println("");
        System.out.println("Average waiting time = " + avgWT + ", Average turn around time = " + avgTAT + ", Completion time = " + totalT); 
    }
    
     public void fileInputSJF() {
        //Method to implement SJF using input from file with two columns of values - first is arrival time and second column is burst times.
        double totalT = 0;
        double arrivalTimes[], burstTimes[], waitingTime[], turnATime[], totalTime[], wT = 0, tAT = 0;
        int P[];
        
        Scanner r = new Scanner(System.in);
        System.out.println("Please enter filename: (Eg. testinput1.txt) ");
        String fname = r.nextLine();
        File file = new File(fname);
        BufferedReader reader = null;
        int N = 0, i = 0;  
        try {

            reader = new BufferedReader(new FileReader(file));
            while (reader.readLine() != null) {  N++; } 
            reader.close();
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            arrivalTimes = new double[N+1];
            burstTimes = new double[N+1];
            P = new int[N];
            totalTime = new double[N];
            waitingTime = new double[N+1];
            turnATime = new double[N];
            while ((text = reader.readLine()) != null) {  
                
                String[] v = text.split(",");
                arrivalTimes[i] = (Double.parseDouble(v[0]));
                burstTimes[i] = (Double.parseDouble(v[1]));
                P[i] = i+1;
                i++;
            }
    
            double temp = 0;
            int t = 0;
        
            for(int m = 0; m < N; m++) {
            
                for(int j = 0; j < N; j++) {
                
                    if(arrivalTimes[m] < arrivalTimes[j]) {
                    
                        temp = arrivalTimes[m];
                        arrivalTimes[m] = arrivalTimes[j];
                        arrivalTimes[j] = temp;
                    
                        temp = burstTimes[m];
                        burstTimes[m] = burstTimes[j];
                        burstTimes[j] = temp;
                    
                        t = P[m];
                        P[m] = P[j];
                        P[j] = t;
                    }
                }   
            } 
            
            System.out.println("Process \t Arrival \t Burst \t Waiting \t Turn around");
        
            double smallestB = 0;
            int j = 1;
            totalT += arrivalTimes[0];
            for (i = 0; i < N; i++) {
            
                totalT += burstTimes[i];
                totalTime[i] = totalT;
                smallestB = burstTimes[j];
                
                for(int m = j; m < N; m++) {
                
                    if((arrivalTimes[m] <= totalT) && (burstTimes[m] < smallestB)) {
                         
                        temp = arrivalTimes[j];
                        arrivalTimes[j] = arrivalTimes[m];
                        arrivalTimes[m] = temp;
                    
                        temp = burstTimes[j];
                        burstTimes[j] = burstTimes[m];
                        burstTimes[m] = temp;
                    
                        t = P[j];
                        P[j] = P[m];
                        P[m] = t;
                    
                    }
                }
            
                waitingTime[i+1] = totalTime[i] - arrivalTimes[i+1];
                turnATime[i] = totalTime[i] - arrivalTimes[i];
                j++; 
                
            }
            
            for( i = 0; i < N; i++) {
            
                System.out.println(P[i] + "\t\t" + arrivalTimes[i] + "\t\t" + burstTimes[i] + "\t\t" + waitingTime[i] + "\t\t" + turnATime[i]);
                wT += waitingTime[i];
                tAT += turnATime[i];
            }
            
            double avgWT = wT / N;
            double avgTAT = tAT / N;
            double avgCT = totalT / N;
        
            System.out.println("");
            System.out.println("Average waiting time = " + avgWT + ", Average turn around time = " + avgTAT + ", Completion time = " + totalT); 
            
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                   reader.close();
                }
            } catch (IOException e) {
            }
        
        }

    }
    public static void main (String[] args) {
        
        SJFSchedule s1 = new SJFSchedule();
        System.out.println("Do you want to: 1: Read from a file or 2: Give input?");
        System.out.println("Please type 1 or 2");
        
        Scanner r = new Scanner(System.in);
        
        int ans = r.nextInt();
        
        if(ans == 1) { s1.fileInputSJF(); }
        else if(ans == 2) { s1.userInputSJF(); }
        
 
    }
}
