package android.example.dex.db.entity.pokemon;

import androidx.annotation.Nullable;
import androidx.room.Embedded;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Prices {

    public Prices(){}

    @Embedded(prefix = "normal_")
    @Nullable
    public Normal normal;

    @Embedded(prefix = "holofoil_") // Table now looks like holofoil_low, holofoil_mid, holofoil_market...
    @Nullable
    public Holofoil holofoil;

    @Embedded(prefix = "reverseHolofoil_")
    @Nullable
    public ReverseHolofoil reverseHolofoil;

    @Embedded(prefix = "firstEditionHolofoil_")
    @SerializedName("1stEditionHolofoil")
    @Nullable
    public FirstEditionHolofoil firstEditionHolofoil;


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

    public static String getPrice(Pokemon pokemon) {
        // Recall: Some cards do not even have TCGPlayer for some reason
        String price = "$0.00";
        TCGPlayer tcgPlayer = pokemon.getTcgplayer();
        // Null checking json values
        if (tcgPlayer != null) {
            Normal normal = tcgPlayer.getPrices().getNormal();
            Holofoil holofoil = tcgPlayer.getPrices().getHolofoil();
            ReverseHolofoil reverseHolofoil = tcgPlayer.getPrices().getReverseHolofoil();
            FirstEditionHolofoil firstEditionHolofoil = tcgPlayer.getPrices().getFirstEditionHolofoil();
            if (normal != null) {
                price = "$" + normal.getMarket();
            }
            if (holofoil != null) {
                price = "$" + holofoil.getMarket();
            }
            if (reverseHolofoil != null) {
                price = "$" + reverseHolofoil.getMarket();
            }
            if (firstEditionHolofoil != null) {
                price = "$" + firstEditionHolofoil.getMarket();
            }
        }
        return price;
    }

    public static String getPriceType(Pokemon pokemon) {
        String priceType = "N/A";
        TCGPlayer tcgPlayer = pokemon.getTcgplayer();
        if (tcgPlayer != null) {
            Normal normal = tcgPlayer.getPrices().getNormal();
            Holofoil holofoil = tcgPlayer.getPrices().getHolofoil();
            ReverseHolofoil reverseHolofoil = tcgPlayer.getPrices().getReverseHolofoil();
            FirstEditionHolofoil firstEditionHolofoil = tcgPlayer.getPrices().getFirstEditionHolofoil();
            if (normal != null) {
                priceType = "Normal";
            }
            if (holofoil != null) {
                priceType = "Hoilfoil";
            }
            if (reverseHolofoil != null) {
                priceType = "Reverse Holofoil";
            }
            if (firstEditionHolofoil != null) {
                priceType = "1st Edition Holofoil";
            }
        }
        return priceType;
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
