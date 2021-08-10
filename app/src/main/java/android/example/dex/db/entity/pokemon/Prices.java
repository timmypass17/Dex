package android.example.dex.db.entity.pokemon;

import android.example.dex.utilities.PokeUtil;

import androidx.annotation.Nullable;
import androidx.room.Embedded;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.text.DecimalFormat;

import static android.example.dex.utilities.PokeUtil.getAvailableCardType;

// Recall: @Embedded - Table now looks like holofoil_low, holofoil_mid, holofoil_market...

@Parcel
public class Prices {

    public Prices(){}

    @Embedded(prefix = "normal_")
    @Nullable
    public Normal normal;

    @Embedded(prefix = "holofoil_")
    @Nullable
    public Holofoil holofoil;

    @Embedded(prefix = "reverseHolofoil_")
    @Nullable
    public ReverseHolofoil reverseHolofoil;

    @Embedded(prefix = "firstEditionHolofoil_")
    @SerializedName("1stEditionHolofoil")
    @Nullable
    public FirstEditionHolofoil firstEditionHolofoil;

    public double highestPrice;

    public void setHighestPrice(double highestPrice) {
        this.highestPrice = highestPrice;
    }

    @Nullable
    public Normal getNormal() {
        return normal;
    }

    @Nullable
    public Holofoil getHolofoil() {
        return holofoil;
    }
    @Nullable
    public ReverseHolofoil getReverseHolofoil() {
        return reverseHolofoil;
    }
    @Nullable
    public FirstEditionHolofoil getFirstEditionHolofoil() {
        return firstEditionHolofoil;
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
    public static class Holofoil {

        public Holofoil(){}

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
    public static class ReverseHolofoil {

        public ReverseHolofoil(){}

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
    public static class FirstEditionHolofoil {

        public FirstEditionHolofoil(){}

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
