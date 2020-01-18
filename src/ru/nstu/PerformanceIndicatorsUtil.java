// PerformanceIndicatorsUtil.java
package ru.nstu;

import java.util.List;

public class PerformanceIndicatorsUtil {
    public static PerformanceIndicators getAverages(List<PerformanceIndicators> indicatorsList) {
        int size = indicatorsList.size();
        double N_obsl = 0;
        double N_neobsl = 0;
        double T_prostoi = 0;
        double T_sr_obsl = 0;
        double T_sr_ozh = 0;

        for (PerformanceIndicators indicators: indicatorsList) {
            N_obsl += indicators.N_obsl;
            N_neobsl += indicators.N_neobsl;
            T_prostoi += indicators.T_prostoi;
            T_sr_obsl += indicators.T_sr_obsl;
            T_sr_ozh += indicators.T_sr_ozh;
        }

        N_obsl = N_obsl / size;
        N_neobsl = N_neobsl / size;
        T_prostoi = T_prostoi / size;
        T_sr_obsl = T_sr_obsl / size;
        T_sr_ozh = T_sr_ozh / size;

        return  new PerformanceIndicators((int)N_obsl, (int)N_neobsl, T_prostoi, T_sr_obsl, T_sr_ozh);
    }

    public static double getSigma2(List<PerformanceIndicators> indicatorsList) {
        double sum = 0;
        for (PerformanceIndicators item: indicatorsList) {
            sum += item.T_sr_ozh;
        }
        double M_T = sum / indicatorsList.size();
        double sigma_2 = 0;
        for (PerformanceIndicators item: indicatorsList) {
            sigma_2 += (M_T - item.T_sr_ozh) * (M_T - item.T_sr_ozh);
        }
        return sigma_2 / indicatorsList.size();
    }
}
