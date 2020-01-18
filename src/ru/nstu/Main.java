// Main.java
package ru.nstu;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        efficiency();
        PerformanceIndicators indicators = getPerformanceIndicators();
        print(indicators);
    }

    private static PerformanceIndicators getPerformanceIndicators() {
        int N = 0;
        int N_obsl = 0;
        int N_neobsl = 0;
        double T_prostoi = 0;
        double T_obsl = 0;
        double T_mod = 24;
        final double a = 0.2;
        final double b = 0.8;
        final double lambda = 4;
        double t_osv = 0;
        double t_prih = 0;
        double T_ozh = 0;
        int C = 0; // состояние системы
        double t_nach_obsl = 0;

        while (t_osv < T_mod) {
            double d_t_obsl;
            t_prih += -1/lambda * Math.log(Math.random());
            if (t_prih >= T_mod) { break; }
            if (t_prih < t_osv) { // канал занят
                if (C > 1) { // C = 2, канал занят, буфер занят
                    if (t_prih < t_nach_obsl) {
                        N_neobsl++;
                        N++;
                        break; // заявка не обслужена
                    } else {
                        // канал занят, заявка пришла после начала обслуживания
                    }
                } else  {
                    // C = 1, канал занят, буфер свободен
                }
                C = 2;
                t_nach_obsl = t_osv;
                T_ozh += (t_osv - t_prih);
                d_t_obsl = a + (b - a) * Math.random();
                t_osv = t_nach_obsl + d_t_obsl;
            } else { // канал свободен, буфер свободен
                C = 1;
                t_nach_obsl = t_prih;
                T_prostoi += (t_prih - t_osv);
                d_t_obsl = a + (b - a) * Math.random();
                t_osv = t_nach_obsl + d_t_obsl;
            }

            T_obsl += d_t_obsl;
            N_obsl++;
            N++;
        }

        return new PerformanceIndicators(N_obsl, N_neobsl, T_prostoi, T_obsl / N_obsl, T_ozh / N_obsl);
    }

    private static void efficiency() {
        int N = 50;
        double eps = 0.2;
        double t_alfa = 1.6759;
        List<PerformanceIndicators> PerformanceIndicatorsList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            PerformanceIndicators indicators = getPerformanceIndicators();
            PerformanceIndicatorsList.add(indicators);
            double sigma2 = PerformanceIndicatorsUtil.getSigma2(PerformanceIndicatorsList);

            int n = (int)(sigma2 / (eps * eps) * t_alfa * t_alfa);
            N = n > N ? n : N;
        }

        PerformanceIndicators averages = PerformanceIndicatorsUtil.getAverages(PerformanceIndicatorsList);
        print(averages);
    }

    private static void print(PerformanceIndicators indicators) {
        System.out.printf("Количество обслуженных заявок %d%n", indicators.N_obsl);
        System.out.printf("Количество необслуженных заявок %d%n", indicators.N_neobsl);
        System.out.printf("Время простоя %.3f%n", indicators.T_prostoi);
        System.out.printf("Среднее время обслуживания %.3f%n", indicators.T_sr_obsl);
        System.out.printf("Среднее время ожидания %.3f%n", indicators.T_sr_ozh);
    }
}
