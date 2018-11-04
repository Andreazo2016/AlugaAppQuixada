package ufc.com.alugaappquixada.Model;

public class Enterprise {
    private Integer id;
    private double latitute;
    private double longitute;
    private Owner owner;
    private String description;

    public Enterprise(Integer id, double latitute, double longitute, Owner owner, String description) {
        this.id = id;
        this.latitute = latitute;
        this.longitute = longitute;
        this.owner = owner;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getLatitute() {
        return latitute;
    }

    public void setLatitute(double latitute) {
        this.latitute = latitute;
    }

    public double getLongitute() {
        return longitute;
    }

    public void setLongitute(double longitute) {
        this.longitute = longitute;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
