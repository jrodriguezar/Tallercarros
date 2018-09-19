package persona;
/**
 *
 * @author Jhon
 */
public class Carro {
    private Chasis chasis;
    private String numserie;
    private Rueda[] ruedas;
    private Motor motor;

    public Carro(String numserie, Rueda[] ruedas, String tipo, Motor motor) {
        this.chasis = new Chasis(tipo);
        this.numserie = numserie;
        this.ruedas = ruedas;
        this.motor = motor;
    }

    public Chasis getChasis() {
        return chasis;
    }

    public String getNumserie() {
        return numserie;
    }

    public Rueda[] getRuedas() {
        return ruedas;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setChasis(Chasis chasis) {
        this.chasis = chasis;
    }

    public void setNumserie(String numserie) {
        this.numserie = numserie;
    }

    public void setRuedas(Rueda[] ruedas) {
        this.ruedas = ruedas;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }
    
    public String mostrardatosrueda(){
        return this.ruedas[0].getMarca();
    }
}