package android.example.dex.db.entity.pokemon;

import java.text.DecimalFormat;

public class SumPojo {
    public float normalTotal;
    public float hoilTotal;

    public float getNormalTotal() {
        return normalTotal;
    }

    public float getHoilTotal() {
        return hoilTotal;
    }

    public String getNormalAndHoilPrice() {
        DecimalFormat df = new DecimalFormat("#.00");
        return "$" + df.format(normalTotal + hoilTotal);
    }
}
