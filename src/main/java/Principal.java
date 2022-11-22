import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class Principal {
	
	private static ArrayList<Equipos> listaEquipos = new ArrayList<Equipos>();
	
	//LOCALIZACION DEL FICHERO PROPERTIES
	static Properties prop = new Properties();
		
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, SQLException, ClassNotFoundException {
		//LEER EL DOCUMENTO XML DE LEBORO
		leerXML();
		
		prop.load(new FileInputStream(new File("C:\\PRUEBAS\\gestores.properties")));
		
		//MOSTRAR POR PANTALLA UN MENU QUE PERMITA ELEGIR AL USUARIO ENTRE DIFERENTES GESTORES DE BBDD
		System.out.println("PROGRAMA GESTOR \r 1.MYSQL \r 2.POSTGRESQL \r 3.SQLite \r 4.EXIT");
		Scanner sc = new Scanner(System.in);
		int opcion = sc.nextInt();
		switch(opcion) {
			case 1:
				//	OPCION MYSQL
				System.out.println("Ha elegido el gestor MySQL");
				opcionMySQL();
				break;
			case 2:
				System.out.println("Ha elegido el gestor PostgreSQL");
				opcionPostgreSQL();
				break;
			case 3:
				System.out.println("Ha elegido el gestor SQLite.");
				opcionSQLite();
				break;
			case 4:
				System.out.println("Saliendo del programa");
				System.exit(0);
				break;
			default:
				System.out.println("Opcion no valida");
		}

	}

	private static void opcionSQLite() throws SQLException {
		System.out.println("Insertando datos en SQLite...");	
		Connection conexion = DriverManager.getConnection(prop.getProperty("RUTASQLITE")+ "AD_Control1.db");
		PreparedStatement ps = conexion.prepareStatement("INSERT INTO equipo (codequipo,ciudad,anho,nombre) VALUES (?,?,?,?)");
		for( int i =0; i<listaEquipos.size(); i++) {
			ps.setInt(1, listaEquipos.get(i).getCodEquipo());
			ps.setString(4, listaEquipos.get(i).getNombre());
			ps.setString(2, listaEquipos.get(i).getCiudad());
			ps.setInt(3, listaEquipos.get(i).getAnho());
			ps.executeUpdate();
		}
	}

	private static void opcionPostgreSQL() throws SQLException, ClassNotFoundException { //postgresql
		System.out.println("Insertando datos en PostgreSQL...");
		Connection conexion = DriverManager.getConnection(prop.getProperty("RUTAPOSTGRESQL") + "ad_control1" , prop.getProperty("USUARIOPOSTGRESQL"), prop.getProperty("PASSWORDPOSTGESQL"));
		PreparedStatement ps = conexion.prepareStatement("INSERT INTO equipos (codequipo,nombre,ciudad,anho) VALUES (?,?,?,?)");
		for( int i =0; i<listaEquipos.size(); i++) {
			ps.setInt(1, listaEquipos.get(i).getCodEquipo());
			ps.setString(2, listaEquipos.get(i).getNombre());
			ps.setString(3, listaEquipos.get(i).getCiudad());
			ps.setInt(4, listaEquipos.get(i).getAnho());
			ps.executeUpdate();
		}
	}
	

	private static void opcionMySQL() throws SQLException {//mysql
		System.out.println("Insertando datos en MySQL...");
		Connection conexion = DriverManager.getConnection(prop.getProperty("RUTAMYSQL") + "AD_Control1", prop.getProperty("USUARIOMYSQL"), prop.getProperty("PASSWORDMYSQL"));
		PreparedStatement ps = conexion.prepareStatement("INSERT INTO equipos (codequipo,nombre,ciudad,anho) VALUES (?,?,?,?)");
		for( int i =0; i<listaEquipos.size(); i++) {
			ps.setInt(1, listaEquipos.get(i).getCodEquipo());
			ps.setString(2, listaEquipos.get(i).getNombre());
			ps.setString(3, listaEquipos.get(i).getCiudad());
			ps.setInt(4, listaEquipos.get(i).getAnho());
			ps.executeUpdate();
		}
	}

	private static void leerXML() throws ParserConfigurationException, SAXException, IOException {
		FileInputStream fichero = new FileInputStream("C:\\PRUEBAS\\EXAMEN\\leboro.xml");
		
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = factoria.newDocumentBuilder();
		
		Document documento = db.parse(fichero);
		
			//PARA LEER EQUIPOS XML
		NodeList equipoCodigo = documento.getElementsByTagName("codequipo");
		NodeList equipoNombre = documento.getElementsByTagName("nombreEq");
		NodeList equipoCiudad = documento.getElementsByTagName("Ciudad");
		NodeList equipoAnho = documento.getElementsByTagName("anho");
		
		for(int i = 0; i<equipoCodigo.getLength(); i++) {
			listaEquipos.add(new Equipos(Integer.parseInt(equipoCodigo.item(i).getTextContent()),
					equipoNombre.item(i).getTextContent(),
					equipoCiudad.item(i).getTextContent(),
					Integer.parseInt(equipoAnho.item(i).getTextContent())
					));
		}
	}	


}
