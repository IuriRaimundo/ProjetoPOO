package pt.ipl.ti.poo;

public class Localizacao {
    private String pais;
    private String cidade;
    private String morada;
    private String codigoPostal;

    public Localizacao(String pais, String cidade, String morada, String codigoPostal) {
        this.pais = pais;
        this.cidade = cidade;
        this.morada = morada;
        this.codigoPostal = codigoPostal;
    }

    public String getPais() {
        return pais;
    }

    public String getCidade() {
        return cidade;
    }

    public String getMorada(){
        return morada;
    }

    public String getCodigoPostal(){
        return codigoPostal;
    }
}
