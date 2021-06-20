package android.example.dex.models.pokemon;

import androidx.annotation.Nullable;

import org.parceler.Parcel;

@Parcel
public class Prices {

    public Prices(){}

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

    @Parcel
    public static class HoloFoil {

        public HoloFoil(){}

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

    @Parcel
    public static class Normal {

        public Normal(){}

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
