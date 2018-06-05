package edu.pe.beans;

public class Oferta {

	Integer NIDOFERTA,NSALARIO;
	String SOFERTA,SDESOFERTA,SEMAIL;
	
	String SRAZSOCIAL;
	
	public Oferta() {
		
	}

	public Oferta(Integer nIDOFERTA, Integer nSALARIO, String sOFERTA, String sDESOFERTA, String sEMAIL,String sRAZSOCIAL) {
		super();
		NIDOFERTA = nIDOFERTA;
		NSALARIO = nSALARIO;
		SOFERTA = sOFERTA;
		SDESOFERTA = sDESOFERTA;
		SEMAIL = sEMAIL;
		SRAZSOCIAL = sRAZSOCIAL;
	}

	public Integer getNIDOFERTA() {
		return NIDOFERTA;
	}

	public void setNIDOFERTA(Integer nIDOFERTA) {
		NIDOFERTA = nIDOFERTA;
	}

	public Integer getNSALARIO() {
		return NSALARIO;
	}

	public void setNSALARIO(Integer nSALARIO) {
		NSALARIO = nSALARIO;
	}

	public String getSOFERTA() {
		return SOFERTA;
	}

	public void setSOFERTA(String sOFERTA) {
		SOFERTA = sOFERTA;
	}

	public String getSDESOFERTA() {
		return SDESOFERTA;
	}

	public void setSDESOFERTA(String sDESOFERTA) {
		SDESOFERTA = sDESOFERTA;
	}

	public String getSEMAIL() {
		return SEMAIL;
	}

	public void setSEMAIL(String sEMAIL) {
		SEMAIL = sEMAIL;
	}
	
	public String getSRAZSOCIAL() {
		return SRAZSOCIAL;
	}

	public void setSRAZSOCIAL(String sRAZSOCIAL) {
		SRAZSOCIAL = sRAZSOCIAL;
	}

}
