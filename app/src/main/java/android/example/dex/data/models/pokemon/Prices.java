package android.example.dex.data.models.pokemon;

import androidx.annotation.Nullable;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.parceler.Parcel;

@Parcel
public class Prices {

    @Embedded(prefix = "normal_")
    @Nullable
    public Normal normal;

    @Embedded(prefix = "holofoil_") // Table now looks like holofoil_low, holofoil_mid, holofoil_market...
    @Nullable
    public HoloFoil holofoil;

    public Prices(){}

    @Nullable
    public HoloFoil getHolofoil() {
        return holofoil;
    }
    @Nullable
    public Normal getNormal() {
        return normal;
    }

    public double getPrice() {
        if (getNormal() != null) {
            return getNormal().getMarket();
        } else if (getHolofoil() != null) {
            return getHolofoil().getMarket();
        } else {
            return -1;
        }
    }

    @Parcel
    public static class Normal {

        public Normal(){}

        public double low;
        public double mid;
        public double high;
        public double market;
        public double directLow;

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
    public static class HoloFoil {

        public HoloFoil(){}

        public double low;
        public double mid;
        public double high;
        public double market;
        public double directLow;

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
