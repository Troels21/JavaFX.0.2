package sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ScheduledExecutorService;

class SimuleringTest{
    Simulering ss;
    ScheduledExecutorService Eventhandler;

    @BeforeEach
    void setUp() throws Exception {
        ss = new Simulering();
    }


    @Test
    void writeToInterFaceTest() {
        for (int i=0;i<10;i++){
        ss.SpO2Simulation();
        ss.temperatureSimulation();
        ss.pulseSimulation();
        ss.ekgSimulation();
        System.out.println(ss.puls+"  "+ss.temp+"   "+ss.SpO2double+"   "+ss.red); }
    }

    @Test
    void pulseSimulationTest() {
        System.out.println("puls sim test");
        ss.pulseSimulation();
        int tal1 = ss.puls;
        ss.pulseSimulation();
        int tal2 = ss.puls;
        Assertions.assertTrue(tal1 != tal2);
        System.out.println("her skal være rigtigt, da tallene er forskellige");
        ss.pulseSimulation();
        tal1 = ss.puls;
        tal2 = ss.puls;
        Assertions.assertFalse(tal1 != tal2);
        System.out.println("Her skal være fejl da begge tal er det samme tal");
        System.out.println();
    }

    @Test
    void spO2SimulationTest() {
        System.out.println("Spo2 sim test");
        ss.SpO2Simulation();
        double tal1 = ss.SpO2double;
        ss.SpO2Simulation();
        double tal2 = ss.SpO2double;
        Assertions.assertTrue(tal1 != tal2);
        System.out.println("her skal være rigtigt, da tallene er forskellige");
        ss.pulseSimulation();
        tal1 = ss.SpO2double;
        tal2 = ss.SpO2double;
        Assertions.assertFalse(tal1 != tal2);
        System.out.println("Her skal være fejl da begge tal er det samme tal");
        System.out.println();
    }

    @Test
    void temperatureSimulationTest() {
        System.out.println("Temp sim test");
        ss.temperatureSimulation();
        double tal1 = ss.temp;
        ss.temperatureSimulation();
        double tal2 = ss.temp;
        Assertions.assertTrue(tal1 != tal2);
        System.out.println("her skal være rigtigt, da tallene er forskellige");
        ss.temperatureSimulation();
        tal1 = ss.temp;
        tal2 = ss.temp;
        Assertions.assertFalse(tal1 != tal2);
        System.out.println("Her skal være fejl da begge tal er det samme tal");
        System.out.println();
    }

    @Test
    void ekgSimulationTest() {
        System.out.println("EKG sim test");
        ss.ekgSimulation();
        int tal1 = ss.red;
        ss.ekgSimulation();
        int tal2 = ss.red;
        Assertions.assertTrue(tal1 != tal2);
        System.out.println("her skal være rigtigt, da tallene er forskellige");
        ss.ekgSimulation();
        tal1 = ss.red;
        tal2 = ss.red;
        Assertions.assertFalse(tal1 != tal2);
        System.out.println("Her skal være fejl da begge tal er det samme tal");
        System.out.println();
    }

}