package Principal;


import PlataformaOnline.jacaranda.com.SerieException;
import PlataformaOnline.jacaranda.com.Series;
import PlataformaOnline.jacaranda.com.Tema;

public class Main {

	
	
	public static void main(String[] args) {
		
		Series series = new Series();
		Series seriesNueva=new Series();
		try {
			series.annadirSerie("This is us", 2015, Tema.DRAMA);
			series.annadirSerie("Friends", 1990, Tema.COMEDIA);
			series.annadirSerie("Dallas", 1970, Tema.INTRIGA);
			series.annadirTemporada("This is us", "Empezamos");
			series.annadirTemporada("This is us", "Seguimos");
			series.annadirTemporada("This is us", "Finalizamos");
			series.annadirTemporada("Friends", "Temporada especial");
			series.annadirCapituloTemporada("This is us", "Empezamos", "La familia");
			series.annadirCapituloTemporada("This is us", "Empezamos", "El problema");
			series.annadirCapituloTemporada("This is us", "Empezamos", "Los niños");
			series.annadirCapituloTemporada("This is us", "Empezamos", "CAsi el final");
			series.annadirCapituloTemporada("This is us", "Empezamos", "El final");
			
			series.escribirFicheroSeries("Ficheros//Series.csv");
			series.escribirFicheroTemporadas("Ficheros//Temporada.csv");
			series.escribirFicheroCapitulos("Ficheros//Capitulos.csv");
			
			//Leo los ficheros con los métodos que he realizado en la clase Series
			seriesNueva.leerFicheroSeries("Ficheros//Series.csv");
			seriesNueva.leerFicheroTemporadas("Ficheros//Temporada.csv");
			seriesNueva.leerFicheroCapitulos("Ficheros//Capitulos.csv");
			
			
			//Con estos metodos compruebo que he leido bien los ficheros y he creado todos los objetos
			System.out.println(seriesNueva.mostrarSeries());
			System.out.println("*****************************************************************************");
			System.out.println(seriesNueva.mostrarTemporadas());
			System.out.println("*****************************************************************************");
			System.out.println(seriesNueva.mostrarCapitulos());
			System.out.println("*****************************************************************************");

			
		} catch (SerieException e) {
			System.out.println(e.getMessage());
		}
		
		

	}
	

	
	
}
