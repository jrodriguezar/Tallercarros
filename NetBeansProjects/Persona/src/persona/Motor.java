package persona;
/**
 *
 * @author Jhon
 */
public class Motor {
    private String marca;
    private int numserie;
    private int cilindros;

    public Motor(String marca, int numserie, int cilindros) {
        this.marca = marca;
        this.numserie = numserie;
        this.cilindros = cilindros;
    }

    public String getMarca() {
        return marca;
    }

    public int getNumserie() {
        return numserie;
    }

    public int getCilindros() {
        return cilindros;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setNumserie(int numserie) {
        this.numserie = numserie;
    }

    public void setCilindros(int cilindros) {
        this.cilindros = cilindros;
    }
}
