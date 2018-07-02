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
import edu.pe.beans.Empresa;
import edu.pe.beans.Oferta;
import edu.pe.beans.Solicitud;
import edu.pe.miconexion.SQLConexion;

@Path("/mispublicaciones")
public class EmpleadorResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	List<Candidato> candidatos = new ArrayList<Candidato>();

	BolsasTrabajoResource fuente = new BolsasTrabajoResource();

	List<Oferta> ofertas = fuente.getOfertas();

	public EmpleadorResource() {
		try {
			Connection cn = SQLConexion.getConexion();

			PreparedStatement pst = cn.prepareStatement("SELECT * FROM CANDIDATO");

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				Candidato c = new Candidato();

				c.setSEMAIL(rs.getString(1));
				c.setSPASSWORD(rs.getString(2));
				c.setSDOCUMENTO(rs.getString(3));
				c.setSNOMBRE(rs.getString(4));
				c.setSAPELLIDOS(rs.getString(5));
				c.setSFECNACIMIENTO(rs.getString(6));
				c.setSGENERO(rs.getString(7));
				c.setNANOSEXPERIENCIA(rs.getInt(8));
				c.setSESPECIALIDAD(rs.getString(9));

				candidatos.add(c);
			}

			cn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// SERVICIO PARA LOGUEARSE
	@GET
	@Path("/login/{emailEmpresa}/{passwordEmpresa}")
	public Response getEmpresa(@PathParam("emailEmpresa") String emailEmpresa,
			@PathParam("passwordEmpresa") String passwordEmpresa) {
		if (emailEmpresa.isEmpty() || passwordEmpresa.isEmpty()) {
			return Response.noContent().build();
		}

		Empresa e = new Empresa();

		try {
			Connection cn = SQLConexion.getConexion();
			CallableStatement cstm = null;

			cstm = cn.prepareCall("{call usp_login_empresa(?,?)}");
			cstm.setString(1, emailEmpresa);
			cstm.setString(2, passwordEmpresa);

			ResultSet rs = cstm.executeQuery();

			while (rs.next()) {
				e.setSEMAIL(rs.getString(1));
				e.setSPASSWORD(rs.getString(2));
				e.setSRAZSOCIAL(rs.getString(3));
				e.setSRUC(rs.getString(4));
				e.setSINDUSTRIA(rs.getString(5));
			}

			cn.close();

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		GenericEntity<Empresa> entity = new GenericEntity<Empresa>(e, Empresa.class);

		return Response.ok().entity(entity).build();
	}

	// SERVICIO PARA LISTAR TODAS LAS OFERTAS SEGÚN EL EMAIL DE LA EMPRESA
	@GET
	@Path("/{empresaEmail}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Oferta> listarOfertasXEmpresa(@PathParam("empresaEmail") String empresaEmail) {

		System.out.println("Cargando listado de Ofertas ...");

		List<Oferta> result = new ArrayList<Oferta>();

		for (Oferta oferta : ofertas) {
			if (oferta.getSEMAIL().equals(empresaEmail)) {
				result.add(oferta);
			}
		}

		return result;
	}

	// SERVICIO PARA VER EL DETALLE DEL POSTULANTE X OFERTA -- probar este,no se
	// si está bien,
	@GET
	@Path("/ofertas/{idOferta}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Candidato> listarCandidatosXSolicitudXOferta(@PathParam("idOferta") String idOferta) {

		List<Candidato> candidatosXOferta = new ArrayList<Candidato>();

		try {
			Connection cn = SQLConexion.getConexion();

			PreparedStatement pst = cn.prepareStatement(
					"SELECT C.SEMAIL,C.SDOCUMENTO,C.SNOMBRE,C.SAPELLIDOS,C.SFECNACIMIENTO,C.SGENERO,C.NANOSEXPERIENCIA ,C.SESPECIALIDAD FROM SOLICITUD S JOIN CANDIDATO C ON C.SEMAIL=S.SEMAIL WHERE S.NIDOFERTA=?");
			pst.setString(1, idOferta);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Candidato c = new Candidato();

				c.setSEMAIL(rs.getString(1));
				c.setSDOCUMENTO(rs.getString(2));
				c.setSNOMBRE(rs.getString(3));
				c.setSAPELLIDOS(rs.getString(4));
				c.setSFECNACIMIENTO(rs.getString(5));
				c.setSGENERO(rs.getString(6));
				c.setNANOSEXPERIENCIA(rs.getInt(7));
				c.setSESPECIALIDAD(rs.getString(8));

				candidatosXOferta.add(c);
			}

			cn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return candidatosXOferta;
	}
// JANNA ESTE NO SE LISTA WAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
	@GET
	@Path("/candidatos/{emailCandidato}")
	public Response getCandidato(@PathParam("emailCandidato") String emailCandidato) {
		if (emailCandidato.isEmpty()) {
			return Response.noContent().build();
		}

		Candidato c = new Candidato();

		try {
			Connection cn = SQLConexion.getConexion();

			PreparedStatement pst = cn.prepareStatement(
					"SELECT * FROM CANDIDATO WHERE SEMAIL=?");
			pst.setString(1, emailCandidato);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
	
				c.setSEMAIL(rs.getString(1));
				c.setSPASSWORD(rs.getString(2).trim());
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

}
