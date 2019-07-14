package com.br.code.java.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TrabalhandoComData {

	public static void main(String[] args) {
		todos();
		//calendarDate();

	}
	
	private static void todos() {
		Date data = new Date();
        System.out.println("Data Agora: "+data);
        
        Calendar calendar = Calendar.getInstance();
        System.out.println("Data e Hora atual: " + calendar.getTime());
        
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1995); 
        c.set(Calendar.MONTH, Calendar.MARCH); 
        c.set(Calendar.DAY_OF_MONTH, 20);
         
        System.out.println("Data/Hora atual: "+c.getTime());
        System.out.println("Ano: "+c.get(Calendar.YEAR));
        System.out.println("Mês: "+c.get(Calendar.MONTH));
        System.out.println("Dia do Mês: "+c.get(Calendar.DAY_OF_MONTH));
        
        LocalDate lt = LocalDate.now();
        System.out.println("\n");
        System.out.println("Data Agora: "+lt );
        
        Locale brasil = new Locale("pt", "BR"); //Retorna do país e a língua
        Locale eua = Locale.US;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy",brasil);
        System.out.println("LocalDate.now(): " + formato.format(LocalDate.now()));
	}
	
	private static void calendarDate() {
		Calendar c = Calendar.getInstance();
		c.set(2013, Calendar.FEBRUARY, 28);
		Date data = c.getTime();
		System.out.println("Data atual sem formatação: " + data);

		// Formata a data
		DateFormat formataData = DateFormat.getDateInstance();
		System.out.println("Data atual com formatação: " + formataData.format(data));

		// Formata Hora
		DateFormat hora = DateFormat.getTimeInstance();
		System.out.println("Hora formatada: " + hora.format(data));

		// Formata Data e Hora
		DateFormat dtHora = DateFormat.getDateTimeInstance();
		System.out.println(dtHora.format(data));

	}

	//modo antigo
	private static Date stringToDate(String dataString) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data = null;
		try {
			data = formato.parse(dataString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	//modo atual
	private static void localeDate(String dataString) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate data = LocalDate.parse(dataString, formato);
		System.out.println(formato.format(data));
		
		
	}
}
