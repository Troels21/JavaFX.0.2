package sample;

public class Beregner {
    double math,math2,math3;
    int puls,temp,u, red;
    String Spo2;
    double intervalmin=70;
    double intervalmax=70;
    double intervalmin2=98;
    double intervalmax2=100;
    double intervalmin3=36;
    double intervalmax3=40;

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
    public void ekgSimulation(){
        int Red[] ={111694,111566,111550,111526,111519,111569,111597,111600,111624,111667,111711,111758,
                111789,111831,111883,111898,111930,111964,112003,112045,112069,112038,111791,111607,111527,
                111492,111471,111482,111545,111564,111569,111604,111634,111699,111743,111804,111832,111873,
                111919,111948,111999,112023,112057,112096,112127,112063,111822,111638,111558,111520,111489,
                111492,111558,111574,111592,111615,111670,111730,111773,111834,111857,111892,111925,111955,
                112014,112031,112082,112113,112148,112176,111999,111787,111667,111624,111586,111589,111629,
                111656,111678,111691,111719,111755,111802,111852,111882,111925,111958,111991,112025,112051,
                112106,112143,112124,111921,111711,111605,111571,111563,111581,111637,111657,111670,111705,
                111745,111797,111829,111858,111892,111916,111951,111968,111990,112019,112041,112073,111993,
                111763,111552,111472,111433,111402,111429,111485,111514,111525,111572,111621,111663,111734,
                111770,111818,111852,111886,111949,111979,112024,112056,112107,112067,111837,111647,111551,
                111520,111488,111513,111578,111577,111615,111640,111679,111732,111781,111832,111870,111912,
                111956,111995,112025,112050,112102,112136,112066,111832,111690,111663,111632,111654,111686,
                111715,111724,111731,111789,111822,111868,111905,111943,111998,112030,112073,112103,112137,
                112178,112203,112182,111990,111832,111766,111750,111736,111725,111788,111799,111797,111829,
                111865,111922,111955,111999,112030,112056};
        if(u < Red.length){
            red=Red[u];
            u++;
        }
        else{u=0;}

    }
}
