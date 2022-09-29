package pro.sky.combined_homeworks_spring_web.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundingDouble {

    public static double roundingDoubleToHundredths(double doubleNumber) {
        BigDecimal result = new BigDecimal(doubleNumber);
        result = result.setScale(2, RoundingMode.HALF_UP);
        doubleNumber = result.doubleValue();
        return doubleNumber;
    }
}
