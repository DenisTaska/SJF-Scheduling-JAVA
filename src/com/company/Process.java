package com.company;

public class Process {

    int process_id;
    int burst_time;
    int arrival_time;

    public Process(int process_id, int arrival_time, int burst_time) {
        this.process_id = process_id;
        this.burst_time = burst_time;
        this.arrival_time = arrival_time;
    }
}