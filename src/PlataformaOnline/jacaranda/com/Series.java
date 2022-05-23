package PlataformaOnline.jacaranda.com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class Series {

	private HashMap<String, Serie> mapSeries;

	/**
	 * Crea el objeto que nos servirá para tener las series
	 */
	public Series() {
		mapSeries = new HashMap<String, Serie>();
	}

	/**
	 * Añade una serie a la lista de series. Si existe una serie con el mismo nombre
	 * lanza una excpetion
	 * 
	 * @param serie
	 * @throws SerieException
	 */
	public void annadirSerie(String nombreSerie, int anno, Tema tema) throws SerieException {
		if (mapSeries.containsKey(nombreSerie)) {
			throw new SerieException("Ya existe esa serie");
		}
		Serie serie = new Serie(nombreSerie, anno, tema);
		mapSeries.put(serie.getNombreSerie(), serie);
	}

	/**
	 * Añade una temporada a la Serie cuyo nombre se le pasa por argumento, si no
	 * existe la Serie lanza una exception
	 * 
	 * @param serie
	 * @throws SerieException
	 */
	public void annadirTemporada(String nombreSerie, String temporada) throws SerieException {
		if (!mapSeries.containsKey(nombreSerie)) {
			throw new SerieException("No existe esa serie");
		}
		Serie serie = mapSeries.get(nombreSerie);
		serie.annadirTemporada(temporada);
	}

	/**
	 * Añade un capítulo a la temporada de la Serie cuyo nombre se le pasa por
	 * argumento, si no existe la Serie o la temporada lanza una exception
	 * 
	 * @param serie
	 * @throws SerieException
	 */
	public void annadirCapituloTemporada(String nombreSerie, String temporada, String capitulo) throws SerieException {
		if (!mapSeries.containsKey(nombreSerie)) {
			throw new SerieException("No existe esa serie");
		}
		Serie serie = mapSeries.get(nombreSerie);
		serie.annadirCapituloTemporada(temporada, capitulo);

	}

	/**
	 * Valorar una temporada de la Serie cuyo nombre se le pasa por argumento, si no
	 * existe la Serie o la temporada lanza una exception
	 * 
	 * @param serie
	 * @throws SerieException
	 */
	public void valorarTemporada(String nombreSerie, String temporada, int valoracion) throws SerieException {
		if (!mapSeries.containsKey(nombreSerie)) {
			throw new SerieException("No existe esa serie");
		}
		Serie serie = mapSeries.get(nombreSerie);
		serie.valorarTemporada(temporada, valoracion);
	}

	/**
	 * Devuelve el número de temporadas que tiene la serie que se pasa por parámetro
	 * Si no existe la serie saltará la excepción.
	 * 
	 * @param nombreSerie
	 * @return
	 * @throws SerieException
	 */

	public int numeroDeTemporadasDeUnaSerie(String nombreSerie) throws SerieException {
		if (!this.mapSeries.containsKey(nombreSerie)) {
			throw new SerieException("La serie no existe");
		}

		return this.mapSeries.get(nombreSerie).numeroTemporadas();
	}

	/**
	 * Modifica el tema de una serie. Si no se encuentra la serie o ya tenía ese
	 * tema saltará la excepción.
	 * 
	 * @param nombreSerie
	 * @param nuevoTema
	 * @throws SerieException
	 */

	public void modificarTema(String nombreSerie, Tema nuevoTema) throws SerieException {

		if (!this.mapSeries.containsKey(nombreSerie)) {
			throw new SerieException("La serie no existe");
		}
		if (this.mapSeries.get(nombreSerie).getTema().equals(nuevoTema)) {
			throw new SerieException("La serie ya tiene ese tema");
		}

		this.mapSeries.get(nombreSerie).setTema(nuevoTema);

	}

	/**
	 * Devolverá un listado ordenado de forma creciente por el año de la serie. Para
	 * cada serie se mostrará su nombre, año y número de temporadas. Si no hay
	 * ninguna serie de ese tema saltará la excepción.
	 * 
	 * @param tema
	 * @return
	 * @throws SerieException
	 */
	public String listadoOrdenadoSeriesDeUnTema(Tema tema) throws SerieException {
		ArrayList<Serie> listado = new ArrayList<>();
		for (String clave : this.mapSeries.keySet()) {
			Serie s = this.mapSeries.get(clave);
			if (s.getTema().equals(tema)) {
				listado.add(s);
			}

		}
		Collections.sort(listado);
		StringBuilder resultado = new StringBuilder("");
		for (Serie s : listado) {
			resultado.append("Nombre: " + s.getNombreSerie() + ". Tema: " + s.getTema() + ". Numero de temporadas: "
					+ s.numeroTemporadas());
		}

		return resultado.toString();
	}

	public void escribirFicheroSeries(String nombreFichero) {

		try {
			FileWriter flujoEscritura = new FileWriter(nombreFichero);
			PrintWriter filtroEscritura = new PrintWriter(flujoEscritura);

			for (String clave : this.mapSeries.keySet()) {
				Serie se = this.mapSeries.get(clave);
				filtroEscritura.println(se.getNombreSerie() + "," + se.getAnno() + "," + se.getTema());
			}

			flujoEscritura.close();
			filtroEscritura.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public void escribirFicheroTemporadas(String nombreFichero) {

		try {
			FileWriter flujoEscritura = new FileWriter(nombreFichero);
			PrintWriter filtroEscritura = new PrintWriter(flujoEscritura);

			for (String clave : this.mapSeries.keySet()) {
				Serie se = this.mapSeries.get(clave);
				for (Temporada t : se.getTemporadas()) {
					filtroEscritura.println(se.getNombreSerie() + "," + t.getNombreTemporada() + ","
							+ t.getCapitulos().size() + "," + t.getSumaOpiniones() + "," + t.getNumeroOpiniones());
				}
			}

			flujoEscritura.close();
			filtroEscritura.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public void escribirFicheroCapitulos(String nombreFichero) {

		try {
			FileWriter flujoEscritura = new FileWriter(nombreFichero);
			PrintWriter filtroEscritura = new PrintWriter(flujoEscritura);

			for (String clave : this.mapSeries.keySet()) {
				Serie se = this.mapSeries.get(clave);
				for (Temporada t : se.getTemporadas()) {
					for (String c : t.getCapitulos()) {
						filtroEscritura.println(se.getNombreSerie() + "," + t.getNombreTemporada() + "," + c);
					}
				}
			}

			flujoEscritura.close();
			filtroEscritura.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public void leerFicheroSeries(String nombreFichero) {
		String linea;
		try {
			FileReader flujoLectura = new FileReader(nombreFichero);
			BufferedReader filtroLectura = new BufferedReader(flujoLectura);
			linea = filtroLectura.readLine();
			while (linea != null) {
				String[] campos = linea.split(",");
				this.annadirSerie(campos[0], Integer.parseInt(campos[1]), Tema.valueOf(campos[2]));
				linea = filtroLectura.readLine();
			}
			filtroLectura.close();
			flujoLectura.close();
		} catch (IOException | NumberFormatException | SerieException e) {
			System.out.println(e.getMessage());
		}
	}

	public void leerFicheroTemporadas(String nombreFichero) {
		String linea;
		try {
			FileReader flujoLectura = new FileReader(nombreFichero);
			BufferedReader filtroLectura = new BufferedReader(flujoLectura);
			linea = filtroLectura.readLine();
			while (linea != null) {
				String[] campos = linea.split(",");
				for (String clave : this.mapSeries.keySet()) {
					Serie s = this.mapSeries.get(clave);
					if (campos[0].equals(s.getNombreSerie())) {
						this.annadirTemporada(campos[0], campos[1]);
					}
					
				}
				linea = filtroLectura.readLine();
			}
			filtroLectura.close();
			flujoLectura.close();
		} catch (IOException | SerieException e) {
			System.out.println(e.getMessage());
		}
	}

	public void leerFicheroCapitulos(String nombreFichero) {
		String linea;
		try {
			FileReader flujoLectura = new FileReader(nombreFichero);
			BufferedReader filtroLectura = new BufferedReader(flujoLectura);
			linea = filtroLectura.readLine();
			while (linea != null) {
				String[] campos = linea.split(",");
				for (String clave : this.mapSeries.keySet()) {
					Serie s = this.mapSeries.get(clave);
					if (campos[0].equals(s.getNombreSerie())) {
						for (Temporada t : s.getTemporadas()) {
							if (t.getNombreTemporada().equals(campos[1])) {
								this.annadirCapituloTemporada(campos[0], campos[1], campos[2]);
							}
						}

					}
				}
				linea = filtroLectura.readLine();

			}
			filtroLectura.close();
			flujoLectura.close();
		} catch (IOException | SerieException e) {
			System.out.println(e.getMessage());
		}
	}

	public String mostrarSeries() {
		StringBuilder resultado=new StringBuilder("");
		for (String clave : this.mapSeries.keySet()) {
			Serie s = this.mapSeries.get(clave);
			resultado.append(s.toString()+"\n");
			
		}
		return resultado.toString();
	}
	
	public String mostrarTemporadas() {
		StringBuilder resultado=new StringBuilder("");
		for (String clave : this.mapSeries.keySet()) {
			Serie s = this.mapSeries.get(clave);
			resultado.append("Serie: "+s.getNombreSerie()+"\n"+s.mostrarTemporadas());
			
		}
		return resultado.toString();
		
	}
	public String mostrarCapitulos() {
		StringBuilder resultado=new StringBuilder("");
		for (String clave : this.mapSeries.keySet()) {
			Serie s = this.mapSeries.get(clave);
			for(Temporada t: s.getTemporadas()) {
				resultado.append("Serie: "+s.getNombreSerie()+"\nTemporada: "+t.getNombreTemporada()+"\n"+t.mostrarLista());
			}
			
		}
		return resultado.toString();
		
	}

}
