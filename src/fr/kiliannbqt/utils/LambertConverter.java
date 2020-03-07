package fr.kiliannbqt.utils;

public class LambertConverter {

    /*
     * CONSTANTS
     */
    final double GRS80E = (float) 0.081819191042816;
    final double LONG_0 = 3;
    final double XS = 700000;
    final double YS = (float) 12655612.0499;
    final double N = (float) 0.7256077650532670;
    final double C = (float) 11754255.4261;

    /*
     * CONVERSION METHOD
     */

    double[] lambert93toWGS84(double lambertE, double lambertN) {
        double delX = lambertE - XS;
        double delY = lambertN - YS;
        double gamma = Math.atan(-delX / delY);
        double R = Math.sqrt(delX * delX + delY * delY);
        double latiso = Math.log(C / R) / N;

        double sinPhiit0 = Math.tanh(latiso + GRS80E * Math.atan(GRS80E * Math.sin(1)));
        double sinPhiit1 = Math.tanh(latiso + GRS80E * Math.atan(GRS80E * sinPhiit0));
        double sinPhiit2 = Math.tanh(latiso + GRS80E * Math.atan(GRS80E * sinPhiit1));
        double sinPhiit3 = Math.tanh(latiso + GRS80E * Math.atan(GRS80E * sinPhiit2));
        double sinPhiit4 = Math.tanh(latiso + GRS80E * Math.atan(GRS80E * sinPhiit3));
        double sinPhiit5 = Math.tanh(latiso + GRS80E * Math.atan(GRS80E * sinPhiit4));
        double sinPhiit6 = Math.tanh(latiso + GRS80E * Math.atan(GRS80E * sinPhiit5));

        double longRad = Math.asin(sinPhiit6);
        double latRad = gamma / N + LONG_0 / 180 * Math.PI;

        double longitude = latRad / Math.PI * 180;
        double latitude = longRad / Math.PI * 180;

        double[] values = {latitude, longitude};
        return (values);
    }

}
