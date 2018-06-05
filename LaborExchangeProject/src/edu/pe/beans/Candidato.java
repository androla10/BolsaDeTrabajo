package edu.pe.beans;

public class Candidato {
	
	String SEMAIL,SPASSWORD,SDOCUMENTO,SNOMBRE,SAPELLIDOS,SFECNACIMIENTO,SGENERO;
	Integer NANOSEXPERIENCIA;

	String SESPECIALIDAD;
	 
	public Candidato() {}


	public Candidato(String sEMAIL, String sPASSWORD, String sDOCUMENTO, String sNOMBRE, String sAPELLIDOS,
			String sFECNACIMIENTO, String sGENERO,Integer nANOSEXPERIENCIA,String sESPECIALIDAD) {
		super();
		SEMAIL = sEMAIL;
		SPASSWORD = sPASSWORD;
		SDOCUMENTO = sDOCUMENTO;
		SNOMBRE = sNOMBRE;
		SAPELLIDOS = sAPELLIDOS;
		SFECNACIMIENTO = sFECNACIMIENTO;
		SGENERO = sGENERO;
		NANOSEXPERIENCIA = nANOSEXPERIENCIA;
		SESPECIALIDAD = sESPECIALIDAD;
	}


	public String getSEMAIL() {
		return SEMAIL;
	}


	public void setSEMAIL(String sEMAIL) {
		SEMAIL = sEMAIL;
	}


	public String getSPASSWORD() {
		return SPASSWORD;
	}


	public void setSPASSWORD(String sPASSWORD) {
		SPASSWORD = sPASSWORD;
	}


	public String getSDOCUMENTO() {
		return SDOCUMENTO;
	}


	public void setSDOCUMENTO(String sDOCUMENTO) {
		SDOCUMENTO = sDOCUMENTO;
	}


	public String getSNOMBRE() {
		return SNOMBRE;
	}


	public void setSNOMBRE(String sNOMBRE) {
		SNOMBRE = sNOMBRE;
	}


	public String getSAPELLIDOS() {
		return SAPELLIDOS;
	}


	public void setSAPELLIDOS(String sAPELLIDOS) {
		SAPELLIDOS = sAPELLIDOS;
	}


	public String getSFECNACIMIENTO() {
		return SFECNACIMIENTO;
	}


	public void setSFECNACIMIENTO(String sFECNACIMIENTO) {
		SFECNACIMIENTO = sFECNACIMIENTO;
	}


	public String getSGENERO() {
		return SGENERO;
	}


	public void setSGENERO(String sGENERO) {
		SGENERO = sGENERO;
	}
	
	public Integer getNANOSEXPERIENCIA() {
		return NANOSEXPERIENCIA;
	}


	public void setNANOSEXPERIENCIA(Integer nANOSEXPERIENCIA) {
		NANOSEXPERIENCIA = nANOSEXPERIENCIA;
	}


	public String getSESPECIALIDAD() {
		return SESPECIALIDAD;
	}


	public void setSESPECIALIDAD(String sESPECIALIDAD) {
		SESPECIALIDAD = sESPECIALIDAD;
	}
	 
	 
	
	
	
}
