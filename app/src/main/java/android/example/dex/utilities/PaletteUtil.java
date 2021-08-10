package android.example.dex.utilities;

import android.example.dex.R;

public class PaletteUtil {

    public static int getColorText(String cardType) {
        if (cardType.equals("1st")) {
            return R.style.ChipLegendary;
        } else if (cardType.equals("R.Holo")) {
            return R.style.ChipRare;
        } else if (cardType.equals("Holo")) {
            return R.style.ChipRare;
        } else if (cardType.equals("Norm")) {
            return R.style.ChipCommon;
        } else {
            return R.color.black;
        }
    }

    public static int getBorderColor(String cardType) {
        if (cardType.equals("1st")) {
            return R.color.legendary_orange;
        } else if (cardType.equals("R.Holo")) {
            return R.color.rare_blue;
        } else if (cardType.equals("Holo")) {
            return R.color.rare_blue;
        } else if (cardType.equals("Norm")) {
            return R.color.common_gray;
        } else {
            return R.color.black;
        }
    }

    public static int getRarityColorText(String rarity) {
        if (rarity.equals("Common")) {
            return R.style.ChipCommon;
        } else if (rarity.equals("Uncommon")) {
            return R.style.ChipRare;
        } else if (rarity.equals("Rare") || rarity.equals("Promo")) {
            return R.style.ChipEpic;
        } else {
            return R.style.ChipLegendary;
        }
    }

    public static int getRarityBorderColor(String rarity) {
        if (rarity.equals("Common")) {
            return R.color.common_gray;
        } else if (rarity.equals("Uncommon")) {
            return R.color.rare_blue;
        } else if (rarity.equals("Rare") || rarity.equals("Promo")) {
            return R.color.epic_purple;
        } else {
            return R.color.legendary_orange;
        }
    }

}
