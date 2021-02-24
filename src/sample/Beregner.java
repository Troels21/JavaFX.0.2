package sample;

public class Beregner {
    double math,math2,math3;
    int puls,temp;
    String Spo2;
    double intervalmin=70;
    double intervalmax=70;
    double intervalmin2=98;
    double intervalmax2=100;
    double intervalmin3=36;
    double intervalmax3=40;
    boolean switchpulse;

    public void pulseSimulation(){
            math = Math.random() * (intervalmax - intervalmin) + intervalmin;
            puls = (int) math;
            intervalmax = math + 5;
            intervalmin = math - 5;
    }
    public void SpO2Simulation(){
        math2 = Math.random() * (intervalmax2 - intervalmin2) + intervalmin2;
        Spo2 = String.valueOf((int)math2)+"%";
        intervalmax2 = math2 + 0.05;
        intervalmin2 = math2 - 0.05;
    }
    public void temperatureSimulation(){
        math3 = Math.random() * (intervalmax3 - intervalmin3) + intervalmin3;
        temp = (int)math3;
        intervalmax3 = math3 + 0.05;
        intervalmin3 = math3 - 0.05;
    }
    public void stopPulse(){
        switchpulse = false;
    }
}
