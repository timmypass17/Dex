package android.example.dex.utilities;

import android.example.dex.db.entity.pokemon.Pokemon;
import android.example.dex.db.entity.pokemon.Prices;
import android.example.dex.db.entity.pokemon.TCGPlayer;

import java.text.DecimalFormat;
import java.util.List;

public class PokeUtil {

    public static String getCardNumber(String number) {
        StringBuilder n = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            // Append numbers only
            if (Character.isDigit(c)) {
                n.append(c);
            }
        }
        return String.valueOf(n);
    }

    public static String getPriceType(Pokemon pokemon) {
        String priceType = "N/A";
        String cardType = getAvailableCardType(pokemon);
        if (cardType.equals("normal")) {
            priceType = "Norm";
        } else if (cardType.equals("holo")) {
            priceType = "Holo";
        } else if (cardType.equals("reverse")) {
            priceType = "R.Holo";
        } else if (cardType.equals("first")) {
            priceType = "1st";
        }
        return priceType;
    }

    public static double getHighestPrice(Pokemon pokemon) {
        double price = 0.00;
        String cardType = getAvailableCardType(pokemon);
        if (cardType.equals("normal")) {
            price = pokemon.getTcgplayer().getPrices().getNormal().getMarket();
        } else if (cardType.equals("holo")) {
            price = pokemon.getTcgplayer().getPrices().getHolofoil().getMarket();
        } else if (cardType.equals("reverse")) {
            price = pokemon.getTcgplayer().getPrices().getReverseHolofoil().getMarket();
        } else if (cardType.equals("first")) {
            price = pokemon.getTcgplayer().getPrices().getFirstEditionHolofoil().getMarket();
        }
        return price;
    }

    public static String getPriceFormatted(Double price) {
        DecimalFormat moneyFormat = new DecimalFormat("$0.00");
        return moneyFormat.format(price);
    }

    public static String getAvailableCardType(Pokemon pokemon) {
        String type = "N/A";
        // Recall: Some cards do not even have TCGPlayer for some reason
        TCGPlayer tcgPlayer = pokemon.getTcgplayer();
        if (tcgPlayer != null) {
            Prices.Normal normal = tcgPlayer.getPrices().getNormal();
            Prices.Holofoil holofoil = tcgPlayer.getPrices().getHolofoil();
            Prices.ReverseHolofoil reverseHolofoil = tcgPlayer.getPrices().getReverseHolofoil();
            Prices.FirstEditionHolofoil firstEditionHolofoil = tcgPlayer.getPrices().getFirstEditionHolofoil();

            if (firstEditionHolofoil != null) {
                type = "first";
            } else if (reverseHolofoil != null) {
                type = "reverse";
            } else if (holofoil != null) {
                type = "holo";
            } else if (normal != null) {
                type = "normal";
            } else {
                type = "Price not found";
            }
        }
        return type;
    }

    // Get total owned
    public static int getTotalOwned(List<Pokemon> pokemonList) {
        int ownedCount = 0;
        for (Pokemon pokemon : pokemonList) {
            if (pokemon.isOwned == 1) {
                ownedCount += 1;
            }
        }
        return ownedCount;
    }
}
