package pt.ipl.ti.poo.imobiliaria;

public enum OpcoesImovel {
    MORADIA(1, "Moradia"),
    APARTAMENTO(2, "Apartamento"),
    QUARTO(3, "Quarto"),
    TERRENO(3, "Terreno");

    private int numeroOpcao;
    private String textoOpcao;

    OpcoesImovel(int numeroOpcao, String textoOpcao) {
        this.numeroOpcao = numeroOpcao;
        this.textoOpcao = textoOpcao;
    }

    public int getNumeroOpcao() {
        return numeroOpcao;
    }

    public String getTextoOpcao() {
        return textoOpcao;
    }

    public static String[] getOpcoesAluguerString() {
        return new String[]{MORADIA.textoOpcao, APARTAMENTO.textoOpcao, QUARTO.textoOpcao};
    }
    
    public static String[] getOpcoesVendaString() {
        return new String[]{MORADIA.textoOpcao, APARTAMENTO.textoOpcao, TERRENO.textoOpcao};
    }
}
