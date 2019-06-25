package solicitacaoferias.etechoracio.com.br.etec_solicitaodefrias.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static String formatDate (int day, int month, int year){
        try{
            Calendar cal = Calendar.getInstance();
            cal.set(year, month,  day);
            return DATE_FORMAT.format(cal.getTime());
        } catch (Exception e) {
            return null;
        }
    }

    public static Date toDate (String srtData){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        try{
             Date data_retorno = formato.parse(srtData);
             return data_retorno;
        }
        catch (ParseException e){
            e.getMessage();
            throw new RuntimeException();
        }
    }

    public static boolean isSegundaFeira(Date data){
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        int dia = cal.get(Calendar.DAY_OF_WEEK);
        return dia == 2;
    }


    public static Date addDays(Date data, int qtde) {
        Calendar c = Calendar.getInstance();
        //int ano = c.get(Calendar.YEAR);
        //int mes = c.get(Calendar.MONTH);
       // int dia = c.get(Calendar.DAY_OF_MONTH);
        c.setTime(data);
        c.add(Calendar.DATE, qtde);
        Date dt = c.getTime();
        return dt;
    }
}
