package android.example.dex.models.pokemon;

import androidx.annotation.Nullable;

public class Prices {

    @Nullable
    private HoloFoil holofoil;
    @Nullable
    private Normal normal;
    @Nullable
    public HoloFoil getHolofoil() {
        return holofoil;
    }
    @Nullable
    public Normal getNormal() {
        return normal;
    }

    public static class HoloFoil {
        private double low;
        private double mid;
        private double high;
        private double market;
        private double directLow;

        public double getLow() {
            return low;
        }

        public double getMid() {
            return mid;
        }

        public double getHigh() {
            return high;
        }

        public double getMarket() {
            return market;
        }

        public double getDirectLow() {
            return directLow;
        }
    }

    public static class Normal {
        private double low;
        private double mid;
        private double high;
        private double market;
        private double directLow;

        public double getLow() {
            return low;
        }

        public double getMid() {
            return mid;
        }

        public double getHigh() {
            return high;
        }

        public double getMarket() {
            return market;
        }

        public double getDirectLow() {
            return directLow;
        }
    }
}
