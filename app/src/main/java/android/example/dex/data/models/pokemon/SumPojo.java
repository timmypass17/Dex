package android.example.dex.data.models.pokemon;

public class SumPojo {
    public float normalTotal;
    public float hoilTotal;

    public float getNormalTotal() {
        return normalTotal;
    }

    public float getHoilTotal() {
        return hoilTotal;
    }

    public float getNormalAndHoilPrice() {
        return normalTotal + hoilTotal;
    }
}
