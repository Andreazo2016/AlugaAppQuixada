package ufc.com.alugaappquixada.Model;

import java.math.BigDecimal;

public class Apartment {

    private double priceRental;
    private Adress adress;
    private String description;
    private Owner owner;

    public double getPriceRental() {
        return priceRental;
    }

    public void setPriceRental(double priceRental) {
        this.priceRental = priceRental;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
