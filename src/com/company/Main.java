package com.company;

public class Main {
    public static void main(String[] args) {
        Process process[] = {
                new Process(1, 0, 6),
                new Process(2, 3, 4),
                new Process(3, 4, 5),
                new Process(4, 5, 12),
                new Process(5, 7, 8)
        };

        printAvgTime(process, process.length);
    }

    // n is the number of processes taking place

    static void findWaitingTime(Process my_process[], int n, int waiting_time[]) {
        int remaining_time[] = new int[n];

        // Copy the burst time into remaining_time[]
        for (int i = 0; i < n; i++)
            remaining_time[i] = my_process[i].burst_time;

        int numOfCompleteProcesses = 0, t = 0, minm = Integer.MAX_VALUE;
        int shortest = 0, finish_time;
        boolean check = false;

        // Loop until all processes get completely executed
        while (numOfCompleteProcesses != n) {

            // Find process with minimum remaining time at every single time lap
            for (int j = 0; j < n; j++) {
                if ((my_process[j].arrival_time <= t) &&
                        (remaining_time[j] < minm) && remaining_time[j] > 0) {
                    minm = remaining_time[j];
                    shortest = j;
                    check = true;
                }
            }

            if (check == false) {
                t++;
                continue;
            }

            // Reduce its remaining time by one
            remaining_time[shortest]--;

            // Check if its remaining time becomes 0 and update minimum
            minm = remaining_time[shortest];
            if (minm == 0)
                minm = Integer.MAX_VALUE;

            // If a process gets completely executed
            if (remaining_time[shortest] == 0) {

                // Increment numOfCompleteProcesses
                numOfCompleteProcesses++;

                //in order to start loop again
                check = false;

                // Find finish time of current process
                finish_time = t + 1;

                // Calculate waiting time for each completed process.
                waiting_time[shortest] = finish_time
                        - my_process[shortest].burst_time
                        - my_process[shortest].arrival_time;

                if (waiting_time[shortest] < 0)
                    waiting_time[shortest] = 0;
            }

            // Increment time
            t++;
        }
    }

    static void findTurnAroundTime(Process my_process[], int n, int waiting_time[], int taround_time[]) {
        // calculating turnaround time by adding burst time and waiting time
        for (int i = 0; i < n; i++)
            taround_time[i] = my_process[i].burst_time + waiting_time[i];
    }

    // Method to calculate average time
    static void printAvgTime(Process my_process[], int n) {

        int waiting_time[] = new int[n], taround_time[] = new int[n];
        int total_wt = 0, total_tat = 0;

        // Function to find waiting time of all processes
        findWaitingTime(my_process, n, waiting_time);

        // Function to find turn around time for all processes
        findTurnAroundTime(my_process, n, waiting_time, taround_time);

        // Print processes
        System.out.println("|Processes|   " +
                " |Arrival Time|   " +
                " |Burst time|    " +
                " |Waiting time|   " +
                " |Turn around time|   ");

        // Calculate total waiting time and total turnaround time
        for (int i = 0; i < n; i++) {
            total_wt = total_wt + waiting_time[i];
            total_tat = total_tat + taround_time[i];
            System.out.println(" " + my_process[i].process_id + "\t\t\t\t"
                    + my_process[i].arrival_time + "\t\t\t\t\t" +
                    +my_process[i].burst_time + "\t\t\t\t" + waiting_time[i]
                    + "\t\t\t\t\t" + taround_time[i]);
        }

        System.out.println("Average waiting time = " +
                (float) total_wt / (float) n);
        System.out.println("Average turn around time = " +
                (float) total_tat / (float) n);
    }
}