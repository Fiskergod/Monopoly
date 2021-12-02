package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Utils {
    /**
     * Метод генерирует 2 случайных числа в заданных пределах и возвращает их сумму.
     *
     * @return Сумма 2 чисел.
     */
    public static int getCubesSum() {
        final int minCubeNumber = 1;
        final int maxCubeNumber = 6;
        int num = minCubeNumber + (int) (Math.random() * ((maxCubeNumber - minCubeNumber) + 1));
        int num2 = minCubeNumber + (int) (Math.random() * ((maxCubeNumber - minCubeNumber) + 1));
        return num + num2;
    }

    /**
     * Метод генерирует случайную стоимость магазина в заданных интервалах.
     *
     * @return Возвращает стоимость магазина.
     */
    public static int getShopValue() {
        final int minShopValue = 50;
        final int maxShopValue = 500;
        return minShopValue + (int) (Math.random() * ((maxShopValue - minShopValue) + 1));
    }

    /**
     * Метод генерирует начальную компенсацию.
     *
     * @return Значение компенсации.
     */
    public static int getInitialCompValue() {
        double minValue = 0.5 * getShopValue();
        double maxValue = 0.9 * getShopValue();
        return (int) (minValue + (Math.random() * ((maxValue - minValue) + 1)));
    }


    /**
     * Метод генерирует случайное число double от 0.1 до 1 и округляет его.
     *
     * @return Возвращает коэффициент компенсации.
     */
    public static double getCompensationCoeff() {
        final double minCompCoeff = 0.1;
        final double maxCompCoeff = 1;
        return Math.random() * (maxCompCoeff - minCompCoeff) + minCompCoeff;
    }

    /**
     * Метод генерирует случайное число double от 0.1 до 2 и округляет его.
     *
     * @return Возвращает коэффициент улучшения.
     */
    public static double getImproveCoeff() {
        final double minImpCoeff = 0.1;
        final double maxImpCoeff = 2;
        return Math.random() * (maxImpCoeff - minImpCoeff) + minImpCoeff;
    }

    /**
     * Метод генерирует случайное число double от 0.01 до 0.1 и округляет его.
     *
     * @return Возвращает штрафной коэффициент.
     */
    public static double getPenaltyCoeff() {
        final double minPenCoeff = 0.01;
        final double maxPenCoeff = 0.1;
        return Math.random() * (maxPenCoeff - minPenCoeff) + minPenCoeff;
    }

    /**
     * Метод принимает коэффициенты и округляет их.
     * @param coeff Коэффициент.
     * @return Возвращает BigDecimal.
     */
    public static BigDecimal getRoundCoeff(double coeff) {
            BigDecimal res = new BigDecimal(coeff);
            res = res.setScale(1, RoundingMode.HALF_UP);
        return res;
    }
}