package Modelos;

public class Moneda {
    private float value;
    private String country;

    public Moneda(ExchangeRateApi money) {
        this.value = money.conversion_result();
        this.country = money.target_code();
    }

    @Override
    public String toString() {
        return this.value + " " + this.country;
    }
}
