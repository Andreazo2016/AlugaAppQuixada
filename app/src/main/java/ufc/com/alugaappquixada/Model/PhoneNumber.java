package ufc.com.alugaappquixada.Model;

public class PhoneNumber {
    private String label;
    private String number;

    public PhoneNumber(String label, String number) {
        this.label = label;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
