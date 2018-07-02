package edu.pe.misservicios;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import edu.pe.beans.Candidato;
import edu.pe.beans.Oferta;
import edu.pe.beans.Solicitud;
import edu.pe.miconexion.SQLConexion;

@Path("/ofertas")
public class BolsasTrabajoResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	List<Oferta> ofertas = new ArrayList<Oferta>();

	public List<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}

	public BolsasTrabajoResource() {

		try {
			Connection cn = SQLConexion.getConexion();

			PreparedStatement pst = cn.prepareStatement("SELECT O.NIDOFERTA,O.SOFERTA,O.NSALARIO,O.SDESOFERTA,O.SEMAIL,E.SRAZSOCIAL FROM OFERTA_TRABAJO O JOIN EMPRESA E ON O.SEMAIL=E.SEMAIL");

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Oferta o = new Oferta();
				o.setNIDOFERTA(rs.getInt(1));
				o.setSOFERTA(rs.getString(2));

				o.setNSALARIO(rs.getInt(3));
				o.setSDESOFERTA(rs.getString(4));
				o.setSEMAIL(rs.getString(5).trim());
				o.setSRAZSOCIAL(rs.getString(6).trim());

				ofertas.add(o);
			}

			cn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// SERVICIO PARA LOGUEARSE
	@GET
	@Path("/login/{emailCandidato}/{passwordCandidato}")
	public Response getCandidato(@PathParam("emailCandidato") String emailCandidato,
			@PathParam("passwordCandidato") String passwordCandidato) {
		if (emailCandidato.isEmpty() || passwordCandidato.isEmpty()) {
			return Response.noContent().build();
		}

		Candidato c = new Candidato();

		try {
			Connection cn = SQLConexion.getConexion();
			CallableStatement cstm = null;

			cstm = cn.prepareCall("{call usp_login_candidato(?,?)}");
			cstm.setString(1, emailCandidato);
			cstm.setString(2, passwordCandidato);

			ResultSet rs = cstm.executeQuery();

			while (rs.next()) {
				c.setSEMAIL(rs.getString(1));
				c.setSPASSWORD(rs.getString(2));
				c.setSDOCUMENTO(rs.getString(3));
				c.setSNOMBRE(rs.getString(4));
				c.setSAPELLIDOS(rs.getString(5));
				c.setSFECNACIMIENTO(rs.getString(6));
				c.setSGENERO(rs.getString(7));
				c.setNANOSEXPERIENCIA(rs.getInt(8));
				c.setSESPECIALIDAD(rs.getString(9));

			}

			cn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		GenericEntity<Candidato> entity = new GenericEntity<Candidato>(c, Candidato.class);

		return Response.ok().entity(entity).build();
	}

	// SERVICIO PARA LISTAR TODAS LAS OFERTAS
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Oferta> listarOfertas() {

		System.out.println("Cargando listado de Ofertas ...");

		return ofertas;
	}

	// SERVICIO PARA FILTRAR OFERTAS POR SALARIO MAYOR A...
	@GET
	@Path("/salariomayora/{salario}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Oferta> listarOfertaXSalario(@PathParam("salario") int salario) {
		System.out.println("Cargando listado de Ofertas por salario ...");

		ArrayList<Oferta> ofertasXsalario = (ArrayList<Oferta>) listarOfertas();

		List<Oferta> result = new ArrayList<Oferta>();

		for (Oferta oferta : ofertasXsalario) {
			if (oferta.getNSALARIO() >= salario) {
				result.add(oferta);
			}
		}
		return result;
	}

	// SERVICIO PARA EL FILTRO DE OFERTAS POR NOMBRE DE OFERTA
	@GET
	@Path("/nombre/{nombre}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Oferta> listarOfertasXNombre(@PathParam("nombre") String nombre) {
		System.out.println("Cargando listado de Ofertas por su nombre ...");

		List<Oferta> ofertas = new ArrayList<Oferta>();

		try {
			Connection cn = SQLConexion.getConexion();

			PreparedStatement pst = cn.prepareStatement("SELECT * FROM OFERTA_TRABAJO WHERE SOFERTA LIKE ?");
			pst.setString(1, nombre + "%");

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Oferta o = new Oferta();
				o.setNIDOFERTA(rs.getInt(1));
				o.setSOFERTA(rs.getString(2));

				o.setNSALARIO(rs.getInt(3));
				o.setSDESOFERTA(rs.getString(4));
				o.setSEMAIL(rs.getString(5).trim());

				ofertas.add(o);
			}

			cn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ofertas;
	}

	// SERVICIO PARA EL DETALLE DE LA OFERTA
	@GET
	@Path("/{codigo}")
	public Response getOferta(@PathParam("codigo") int codigo) {
		if (codigo <= 0) {
			return Response.noContent().build();
		}
		Oferta obj = null;

		for (Oferta oferta : ofertas) {
			if (oferta.getNIDOFERTA() == codigo) {
				obj = oferta;
				break;
			}
		}
		GenericEntity<Oferta> entity = new GenericEntity<Oferta>(obj, Oferta.class);

		return Response.ok().entity(entity).build();
	}

	// SERVICIO PARA ENVIAR UNA SOLICITUD DE TRABAJO
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response agregarSolicitud(Solicitud solicitud) {

		if (solicitud == null) {
			return Response.status(400).entity("Por favor, ingrese los datos de la solicitud").build();
		}
		if (solicitud.getNIDOFERTA() == 0 || solicitud.getSEMAIL().isEmpty()) {
			return Response.status(400).entity("Por favor, ingrese los datos correctos").build();
		}

		Solicitud s = new Solicitud();
		s.setSEMAIL(solicitud.getSEMAIL());
		s.setNIDOFERTA(solicitud.getNIDOFERTA());

		try {
			Connection cn = SQLConexion.getConexion();
			PreparedStatement pst = cn.prepareStatement("INSERT INTO SOLICITUD(SEMAIL,NIDOFERTA) VALUES(?,?)");

			pst.setString(1, s.getSEMAIL());
			pst.setInt(2, s.getNIDOFERTA());

			pst.executeUpdate();

			cn.close();

			System.out.println("Su solicitud fue profesada, revise su email : " + s.getSEMAIL());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok().entity(solicitud).build();
	}

}
