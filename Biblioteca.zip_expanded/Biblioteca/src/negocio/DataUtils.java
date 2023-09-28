package negocio;

import java.util.Calendar;
import java.util.Date;

public class DataUtils {

    public static Date adicionarDias(Date data, int quantidadeDias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.DAY_OF_MONTH, quantidadeDias);
        return calendar.getTime();
    }

    public static boolean verificarDiaSemana(Date data, int diaSemana) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        int dia = calendar.get(Calendar.DAY_OF_WEEK);
        return dia == diaSemana;
    }

    public static Date obterDataComDiferencaDias(int quantidadeDias) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, quantidadeDias);
        return calendar.getTime();
    }
}
