package edu.pe.miconexion;

import java.sql.*;

public class SQLConexion {
	
	private static final String DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String URL_CONNECTION = "jdbc:sqlserver://localhost:1433;database=BD_BOLSATRABAJO";
	private static final String USER = "sa";
	private static final String PASSWORD = "123456";
	
	public static Connection getConexion() {
		Connection con = null;
		try {
			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
			System.out.println("Correcta conexión con la base de datos.");

		} catch (ClassNotFoundException ex) {
			System.out.println("No se ha encontrado el driver.");
		} catch (SQLException ex) {
			System.out.println("Error con la base de datos.");
		}
		return con;
	}
}