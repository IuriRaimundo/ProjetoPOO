package pt.ipl.ti.poo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;

public class Data implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Dia da data
     */
    private final int dia;

    /**
     * Mês da data
     */
    private final int mes;

    /**
     * Ano da data
     */
    private final int ano;

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    /**
     * Esta função obtem a data atual.
     * @return Data atual.
     */
    public static Data getDataAtual() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Lisbon"));
        return new Data(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH)+1, cal.get(Calendar.YEAR));
    }

    @Override
    public String toString() {
        return dia + "/" + mes + "/" + ano;
    }
}
