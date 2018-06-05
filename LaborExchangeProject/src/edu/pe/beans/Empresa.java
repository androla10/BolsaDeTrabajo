package edu.pe.beans;

public class Empresa {

	
	String SEMAIL ,SPASSWORD,SRAZSOCIAL,SRUC,SINDUSTRIA;
	
	public Empresa() {
		
	}

	public Empresa(String sEMAIL, String sPASSWORD, String sRAZSOCIAL, String sRUC, String sINDUSTRIA) {
		super();
		SEMAIL = sEMAIL;
		SPASSWORD = sPASSWORD;
		SRAZSOCIAL = sRAZSOCIAL;
		SRUC = sRUC;
		SINDUSTRIA = sINDUSTRIA;
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

	public String getSRAZSOCIAL() {
		return SRAZSOCIAL;
	}

	public void setSRAZSOCIAL(String sRAZSOCIAL) {
		SRAZSOCIAL = sRAZSOCIAL;
	}

	public String getSRUC() {
		return SRUC;
	}

	public void setSRUC(String sRUC) {
		SRUC = sRUC;
	}

	public String getSINDUSTRIA() {
		return SINDUSTRIA;
	}

	public void setSINDUSTRIA(String sINDUSTRIA) {
		SINDUSTRIA = sINDUSTRIA;
	}
	
	
	
	
}
