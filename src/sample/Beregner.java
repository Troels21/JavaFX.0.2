package sample;

public class Beregner {
    double math;
    int puls;
    int i;
    double intervalmin=30;
    double intervalmax=190;
    boolean switchpulse;

    public void pulsSimulering(){
            i++;
            math = Math.random() * (intervalmax - intervalmin) + intervalmin;
            puls = (int) math;
            intervalmax = math + 5;
            intervalmin = math - 5;
    }
    public void stopPulse(){
        switchpulse = false;
    }
}
