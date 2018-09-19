package persona;
/**
 *
 * @author Jhon
 */
public class Rueda {
    private String marca;
    private String modelo;
    private int pulgadas;

    public Rueda(String marca, String modelo, int pulgadas) {
        this.marca = marca;
        this.modelo = modelo;
        this.pulgadas = pulgadas;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getPulgadas() {
        return pulgadas;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPulgadas(int pulgadas) {
        this.pulgadas = pulgadas;
    }
}
