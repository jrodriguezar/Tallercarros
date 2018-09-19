package persona;
/**
 *
 * @author Jhon
 */
public class Chasis {
    private String tipo;
    
    public Chasis(String tipo){
        this.tipo = tipo;
    }

    public void setSerie(String serie) {
        this.tipo = serie;
    }

    public String getSerie() {
        return tipo;
    }
    
}
