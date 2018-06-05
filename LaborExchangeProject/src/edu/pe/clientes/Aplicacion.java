package edu.pe.clientes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.PostUpdate;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.pe.beans.Candidato;
import edu.pe.beans.Empresa;
import edu.pe.beans.Oferta;
import edu.pe.beans.Solicitud;

public class Aplicacion {

	private static final String URL_SERVICIO_REST = "http://localhost:8080/LaborExchangeProject/rest/";
	
	//POST
	
	public static Solicitud postSolicitud(Solicitud solicitud) {
		Client cliente = ClientBuilder.newClient();
		WebTarget webTarget = cliente.target(URL_SERVICIO_REST);
		WebTarget path = webTarget.path("ofertas");
		
		Invocation.Builder invocationBuilder = path.request(MediaType.APPLICATION_JSON);
		
		Response response = invocationBuilder.post(Entity.entity(solicitud, MediaType.APPLICATION_JSON));
		Solicitud retorno = null;
		
		System.out.println(" ENHORABUENA!");//+ response.getStatus());
		
		
		if(response.getStatus() == 401){
			System.out.println("No tiene los permisos necesarios");
		}
		
		if (response.getStatus() == 200) {
			retorno = response.readEntity(Solicitud.class);
		}
		return retorno;
	}
	
	
	// PARA CANDIDATOS
	public static List<Oferta> getListaOfertas() {

		Client cliente = ClientBuilder.newClient();
		WebTarget webTarget = cliente.target(URL_SERVICIO_REST);
		WebTarget path = webTarget.path("ofertas");

		Invocation.Builder invocationBuilder = path.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		List<Oferta> lista = response.readEntity(new GenericType<List<Oferta>>() {
		});
		return lista;
	}

	public static List<Oferta> getListaOfertasNombre(String nombre) {
		Client cliente = ClientBuilder.newClient();
		WebTarget webTarget = cliente.target(URL_SERVICIO_REST);
		WebTarget path = webTarget.path("ofertas").path("nombre").path(nombre);

		Invocation.Builder invocationBuilder = path.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		System.out.println("procesando...");
		List<Oferta> lista = response.readEntity(new GenericType<List<Oferta>>() {
		});

		return lista;
	}

	public static List<Oferta> getListaMayoe(String salario) {
		Client cliente = ClientBuilder.newClient();
		WebTarget webTarget = cliente.target(URL_SERVICIO_REST);
		WebTarget path = webTarget.path("ofertas").path("salariomayora").path(salario);

		Invocation.Builder invocationBuilder = path.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		List<Oferta> lista = response.readEntity(new GenericType<List<Oferta>>() {
		});
		return lista;
	}

	// PARA EMPRESAS

	// MÉTODO PARA RETORNAR LAS OFERTAS DE TRABAJO PUBLICADAS POR UNA EMPRESA
	public static List<Oferta> getListaOfertasXEmpresa(String emailEmpresa) {
		Client cliente = ClientBuilder.newClient();
		WebTarget webTarget = cliente.target(URL_SERVICIO_REST);
		WebTarget path = webTarget.path("mispublicaciones").path(emailEmpresa);

		Invocation.Builder invocationBuilder = path.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		List<Oferta> lista = response.readEntity(new GenericType<List<Oferta>>() {
		});
		return lista;
	}

	// MÉTODO QUE RETORNA LOS POSTULANTES EN BASE A UNA OFERTA DE TRABAJO
	public static List<Candidato> getListaCandidatosXOferta(String idOferta) {
		Client cliente = ClientBuilder.newClient();
		WebTarget webTarget = cliente.target(URL_SERVICIO_REST);
		WebTarget path = webTarget.path("mispublicaciones").path("ofertas").path(idOferta);

		Invocation.Builder invocationBuilder = path.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		List<Candidato> lista = response.readEntity(new GenericType<List<Candidato>>() {
		});
		return lista;
	}
	
	// MÉTODO QUE RETORNA EL DETALLE DE UN CANDIDATO
		public static Oferta getOferta(String idOferta) {
			Client cliente = ClientBuilder.newClient();
			WebTarget webTarget = cliente.target(URL_SERVICIO_REST);
			WebTarget path = webTarget.path("ofertas").path(idOferta);

			Invocation.Builder invocationBuilder = path.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.get();

			Oferta o = response.readEntity(Oferta.class);
			return o;
		}

	// MÉTODO QUE RETORNA EL DETALLE DE UN CANDIDATO
	public static Candidato getCandidato(String emailCandidato) {
		Client cliente = ClientBuilder.newClient();
		WebTarget webTarget = cliente.target(URL_SERVICIO_REST);
		WebTarget path = webTarget.path("mispublicaciones").path("candidatos").path(emailCandidato);

		Invocation.Builder invocationBuilder = path.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		Candidato c = response.readEntity(Candidato.class);
		return c;
	}

	// MÉTODO LOGIN PARA EL CANDIDATO
	public static Candidato getLoginCandidato(String emailCandidato, String passwordCandidato) {
		Client cliente = ClientBuilder.newClient();
		WebTarget webTarget = cliente.target(URL_SERVICIO_REST);
		WebTarget path = webTarget.path("ofertas").path("login").path(emailCandidato).path(passwordCandidato);

		Invocation.Builder invocationBuilder = path.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		Candidato c = response.readEntity(Candidato.class);
		return c;
	}

	// MÉTODO QUE RETORNA EL DETALLE DE UN CANDIDATO
	public static Empresa getLoginEmpresa(String emailEmpresa, String passwordEmpresa) {
		Client cliente = ClientBuilder.newClient();
		WebTarget webTarget = cliente.target(URL_SERVICIO_REST);
		WebTarget path = webTarget.path("mispublicaciones").path("login").path(emailEmpresa).path(passwordEmpresa);

		Invocation.Builder invocationBuilder = path.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		Empresa e = response.readEntity(Empresa.class);
		return e;
	}
	
	
	public static void main(String[] args) {

		List<Oferta> ofertas = getListaOfertas();

		System.out.println("***********************************SISTEMA DE OFERTAS DE TRABAJO************************************");
		System.out.println("****************************************************************************************************");
		System.out
				.println("Buen día,si es un postulante ingrese 'candidato' en todo caso,si fuese una empresa ingrese 'empresa'");
		System.out.println("****************************************************************************************************");
		
		Scanner inputTipoUsuario = new Scanner(System.in);

		String inpTipoUsuario = inputTipoUsuario.nextLine();

		if (inpTipoUsuario.equalsIgnoreCase("candidato")) {
			System.out.println("****************************************LOGIN DE CANDIDATOS******************************************");
			System.out.println("EMAIL :");
			Scanner inputEmailCandidato = new Scanner(System.in);

			String inpEmailPostulante = inputEmailCandidato.nextLine();

			System.out.println("PASSWORD :");
			Scanner inputPasswordCandidato = new Scanner(System.in);

			String inpPasswordCandidato = inputPasswordCandidato.nextLine();

			Candidato candidatoLogeado = new Candidato();
			try {
				candidatoLogeado = getLoginCandidato(inpEmailPostulante, inpPasswordCandidato);
			} catch (Exception e) {
				candidatoLogeado.setSEMAIL(null);
			}
			 
			if (!(candidatoLogeado.getSEMAIL().equals(""))) {
				System.out.println("****************************************************************************************");
				System.out.println(
						"**********************************INGRESO EXITOSO***************************************");
				System.out.println("***********************BIENVENIDO " + candidatoLogeado.getSNOMBRE() + " "
						+ candidatoLogeado.getSAPELLIDOS() + " **************************");
				System.out.println("****************************************************************************************");

				System.out.println("**********************************MENÚ DE OPCIONES**************************************");

				System.out.println("1 - Ver todas las ofertas");
				System.out.println("2 - Filtrar las ofertas por su nombre");
				System.out.println("3 - Filtrar las ofertas por salario mayor a...");

				System.out.println("***************************************************************************************");

				System.out.println("¿Qué desea realizar? : ");
				Scanner scanner = new Scanner(System.in);
				String opcion = scanner.nextLine();
				System.out.println("*************************LISTADO DE TODAS LAS OFERTAS*********************************");

				if (opcion.equals("1")) {

					for (Oferta oferta : ofertas) {
						System.out.println("**************************************************************************");
						System.out.println("ID : " + oferta.getNIDOFERTA() + " - " + oferta.getSOFERTA()+"("+oferta.getSRAZSOCIAL()+")");
					}

					System.out.println("************************************************************************");
					System.out.println("¿A qué oferta desea postular? : ");
					Scanner scanner2 = new Scanner(System.in);

					String opcion2 = scanner2.nextLine();
					
					Oferta ofertaDetalle = getOferta(opcion);
					
					System.out.println("************************************************************************");
					System.out.println("N° : "+ ofertaDetalle.getNIDOFERTA());
					System.out.println("************************************************************************");
					System.out.println("Oferta : "+ ofertaDetalle.getSOFERTA());
					System.out.println("Empresa : "+ ofertaDetalle.getSRAZSOCIAL());
					System.out.println("Salario : "+ ofertaDetalle.getNSALARIO());
					System.out.println("************************************************************************");
					System.out.println("************************************************************************");
					System.out.println("Desripción :");
					System.out.println(ofertaDetalle.getSDESOFERTA());
					System.out.println("************************************************************************");
					System.out.println("¿Desea postular a esta oferta? Sí(Y) - No(N)");
					Scanner inputSiONo = new Scanner(System.in);

					String inpSiONo = inputSiONo.nextLine();
					
					if(inpSiONo.equalsIgnoreCase("Y")){
						Solicitud solicitudnueva = new Solicitud(Integer.parseInt(opcion2), inpEmailPostulante);
						postSolicitud(solicitudnueva);
						
						System.out.println("*********************************************");
						System.out.println("*****SU SOLICITUD SE GUARDÓ CORRECTAMENTE*****");
					}
					
					if(inpSiONo.equalsIgnoreCase("N")){
						System.out.println("**************************************************");
						System.out.println("*****MUCHAS GRACIAS,REINICIE LA APLICACIÓN*****");
					}

				}

				else if (opcion.equalsIgnoreCase("2")) {
					System.out.println("Ingrese el Nombre : ");
					Scanner scanner2 = new Scanner(System.in);
					String opcion2 = scanner2.nextLine();

					List<Oferta> ofertas2 = getListaOfertasNombre(opcion2);

					if (ofertas2 == null) {
						System.out.println("error");

					} else {

						for (Oferta oferta : ofertas2) {
							System.out.println("******************************************************");
							System.out.println("ID : " + oferta.getNIDOFERTA() + " - " + oferta.getSOFERTA());
						}

					}

				}

				else if (opcion.equals("3")) {
					System.out.println("Ingrese el monto : ");
					Scanner scanner2 = new Scanner(System.in);
					String opcion2 = scanner2.nextLine();

					List<Oferta> ofertas2 = getListaMayoe(opcion2);

					if (ofertas2 == null) {
						System.out.println("Error");

					} else {

						for (Oferta oferta : ofertas2) {
							System.out.println("*****************listado******************");
							System.out.println("ID : " + oferta.getNIDOFERTA() + " - " + oferta.getSOFERTA() + " : "
									+ oferta.getNSALARIO());
						}

					}

				} 
			}if(candidatoLogeado.getSEMAIL()==null) {
				System.out.println("*******INICIO DE SESIÓN FALLIDO***********");
			}
		}
		if (inpTipoUsuario.equals("empresa")) {
			System.out.println("*******LOGIN DE EMPRESAS*******");
			System.out.println("Ingrese su email :");
			Scanner inputEmailEmpresa = new Scanner(System.in);

			String inpEmailEmpresa = inputEmailEmpresa.nextLine();

			System.out.println("Ingrese su password :");
			Scanner inputPasswordEmpresa = new Scanner(System.in);

			String inpPasswordEmpresa = inputPasswordEmpresa.nextLine();

			Empresa empresaLogeada = getLoginEmpresa(inpEmailEmpresa, inpPasswordEmpresa);

			if (!(empresaLogeada.getSEMAIL().equals(""))) {
				System.out.println("*********************************");
				System.out.println("*******INGRESO EXITOSO***********");
				System.out.println("*********************************");
				System.out.println("*******BIENVENIDO " + empresaLogeada.getSRAZSOCIAL() + " ***********");
				System.out.println("*********************************");
				System.out.println("*********************************");
				System.out.println("*******MENÚ DE OPCIONES***********");

				System.out.println("1 - Ver mis ofertas publicadas");

				System.out.println("*****************************");

				System.out.println("¿Qué desea realizar? : ");
				Scanner scanner = new Scanner(System.in);
				String opcion = scanner.nextLine();
				System.out.println("Usted seleccionó " + opcion);

				if (opcion.equals("1")) {
					for (Oferta oferta : getListaOfertasXEmpresa(inpEmailEmpresa)) {
						System.out.println("***********************************");
						System.out.println("ID : " + oferta.getNIDOFERTA() + " - " + oferta.getSOFERTA());
					}
				}

				System.out.println("*********************************");
				System.out.println("Ver postulantes de la oferta : ");
				Scanner scanner2 = new Scanner(System.in);

				String opcion2 = scanner2.nextLine();

				System.out.println("***********************************");
				System.out.println("************CANDIDATOS DE LA OFERTA " + opcion2 + " ************");

				// ESTE LISTADO DEBERÍA SER EL DE POSTULANTES POR OFERTAS DE
				// TRABAJO PASANDO EL PARAMETRO (OPCION2) Y LUEGO HACERLE UN
				// FOR
				for (Candidato candidato : getListaCandidatosXOferta(opcion2)) {
					System.out.println("Candidato : " + candidato.getSEMAIL().trim() + " || " + candidato.getSNOMBRE() + " "
							+ candidato.getSAPELLIDOS());
				}

				System.out.println("*******Ver el detalle del candidato : *******");
				Scanner inputEmailCandidato2 = new Scanner(System.in);
				
				String inpEmailCandidato2 = inputEmailCandidato2.nextLine();
				inputEmailCandidato2.close();

				Candidato candidatoDetalle = getCandidato(inpEmailCandidato2);

				System.out.println("***********************************");
				System.out.println("************DETALLE DEL CANDIDATO************");
				System.out.println("Email : " + candidatoDetalle.getSEMAIL());
				System.out.println("Nombre: " + candidatoDetalle.getSNOMBRE() + " " + candidatoDetalle.getSAPELLIDOS()
						+ "||" + "Documento: " + candidatoDetalle.getSDOCUMENTO() + "||" + "Especialidad: "
						+ candidatoDetalle.getSESPECIALIDAD() + "||" + "Años de experiencia: "
						+ candidatoDetalle.getNANOSEXPERIENCIA());
				scanner.close();
				scanner2.close();
				
			} else {
				System.out.println("*******INICIO DE SESIÓN FALLIDO***********");
			}
			
			inputTipoUsuario.close();
			inputEmailEmpresa.close();
			
		}
		
	}
	
}
