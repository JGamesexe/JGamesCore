package com.jgamesexe.jgamescore.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class CoisasDeTempo {

    public static StringBuilder millesToTempoDecente(Long tempoOnline) {

        if (tempoOnline == null) {
            return new StringBuilder("§mTempo NULLLOOOOOOOOOOO");
        }

        int segundos = (int) (tempoOnline / 1000);
        int segundosOnline = segundos % 60;
        int minutos = (segundos - segundosOnline) / 60;
        int minutosOnline = minutos % 60;
        int horas = (minutos - minutosOnline) / 60;
        int horasOnline = horas % 24;
        int diasOnline = (horas - horasOnline) / 24;

        // StringBuilder msgSegundosOnline = new StringBuilder();
        StringBuilder msgMinutosOnline = new StringBuilder();
        StringBuilder msgHorasOnline = new StringBuilder();
        StringBuilder msgDiasOnline = new StringBuilder();

        //msgSegundosOnline.append(segundosOnline == 0 ? "" : segundosOnline == 1 ? "1 Segundo." : segundosOnline + " Segundos.");
        msgMinutosOnline.append(minutosOnline == 1 ? "1 Minuto." : minutosOnline + " Minutos.");
        msgHorasOnline.append(horasOnline == 1 ? "1 Hora e " : horasOnline + " Horas e ");
        msgDiasOnline.append(diasOnline == 1 ? "1 Dia, " : diasOnline + " Dias, ");

        //  66 Dias, 23 Horas e 1 Minuto
        if (diasOnline == 0 && horasOnline == 0) return new StringBuilder("" + msgMinutosOnline);
        if (diasOnline == 0) return new StringBuilder("" + msgHorasOnline + msgMinutosOnline);
        else return new StringBuilder("" + msgDiasOnline + msgHorasOnline + msgMinutosOnline);

    }

    public static StringBuilder millesToDataDecente(Long tempoMilles) {
        String mascaraDaData = "d 'de' MMMMM 'de' y, 'ás' HH:mm";
        Locale locale = new Locale("pt", "BR");

        SimpleDateFormat data = new SimpleDateFormat(mascaraDaData, locale);
        data.getCalendar().setTimeInMillis(tempoMilles);
        data.getCalendar().getTimeZone().setRawOffset((-3 * 60 * 60 * 1000));

        //  1 de Janeiro de 2018, ás 12:15
        return new StringBuilder("" + data.format(data.getCalendar().getTimeInMillis()));

    }

//    public static Long agora() {
//
//        SimpleDateFormat agora = new SimpleDateFormat();
//        agora.getCalendar().setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
////        agora.getCalendar().setTimeInMillis(System.currentTimeMillis());
////        agora.getCalendar().getTimeZone().setRawOffset((-3 * 60 * 60 * 1000));
////        agora.getCalendar().getTimeZone().setRawOffset((-2 * 60 * 60 * 1000));
//            return agora.getCalendar().getTimeInMillis();
//
//    }

}
