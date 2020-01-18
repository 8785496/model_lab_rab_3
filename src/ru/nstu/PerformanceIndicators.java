// PerformanceIndicators.java
package ru.nstu;

public class PerformanceIndicators {
    int N_obsl = 0;
    int N_neobsl = 0;
    double T_prostoi = 0;
    double T_sr_obsl = 0;
    double T_sr_ozh = 0;

    public PerformanceIndicators(int N_obsl, int N_neobsl, double T_prostoi, double T_sr_obsl, double T_sr_ozh) {
        this.N_obsl = N_obsl;
        this.N_neobsl = N_neobsl;
        this.T_prostoi = T_prostoi;
        this.T_sr_obsl = T_sr_obsl;
        this.T_sr_ozh = T_sr_ozh;
    }
}
