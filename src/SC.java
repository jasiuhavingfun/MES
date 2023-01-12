import static java.lang.Math.sqrt;

public class SC // schemat calkowania
{
    public static double[] PC2 = {-1 / sqrt(3), 1 / sqrt(3)}; // punkty calkowania
    public static double[] W2 = {1, 1}; // wagi punktow
    public static double[] PC3 = {-sqrt(0.6), 0, sqrt(0.6)};  // punkty calkowania
    public static double[] W3 = {0.555555, 0.888888, 0.555555}; // wagi punktow
    public static double[] PC4 =  {-sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0)), -sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)),
            sqrt((3.0 / 7.0) - (2.0 / 7.0) * sqrt(6.0 / 5.0)), sqrt((3.0 / 7.0) + (2.0 / 7.0) * sqrt(6.0 / 5.0))};
    public static double[] W4 = {(18 - sqrt(30)) / 36, (18 + sqrt(30)) / 36, (18 + sqrt(30)) / 36, (18 - sqrt(30)) / 36};


}