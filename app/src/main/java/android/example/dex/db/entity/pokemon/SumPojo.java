package android.example.dex.db.entity.pokemon;

import java.text.DecimalFormat;

public class SumPojo {
    public float normalTotal;
    public float hoilTotal;
    public float reverseHoilTotal;
    public float firstEditionTotal;

    public float getNormalTotal() {
        return normalTotal;
    }

    public float getHoilTotal() {
        return hoilTotal;
    }

    public float getReverseHoilTotal() {
        return reverseHoilTotal;
    }

    public float getFirstEditionTotal() {
        return firstEditionTotal;
    }

    public String getCollectionPrice() {
        DecimalFormat df = new DecimalFormat("#.00");
        return "$" + df.format(normalTotal + hoilTotal + reverseHoilTotal + firstEditionTotal);
    }
}
