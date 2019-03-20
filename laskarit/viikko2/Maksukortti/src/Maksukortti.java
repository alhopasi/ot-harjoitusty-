
public class Maksukortti {

    private double saldo;

    public Maksukortti(double saldo) {
        this.saldo = saldo;
    }

    public double saldo() {
        return saldo;
    }

    public void lataaRahaa(double lisays) {
        if (lisays < 0) {
            return;
        }
        this.saldo += lisays;
        if (saldo > 150) {
            saldo = 150;
        }
    }

    public void syoEdullisesti() {
        this.otaRahaa(2.5);
    }

    public void syoMaukkaasti() {
        this.otaRahaa(4.0);
    }

    public boolean otaRahaa(double maara) {
        if (this.saldo < maara) {
            return false;
        }

        this.saldo = this.saldo - maara;
        return true;
    }

    @Override
    public String toString() {
        double euroa = saldo;
        return "Kortilla on rahaa " + saldo + " euroa";
    }
}
