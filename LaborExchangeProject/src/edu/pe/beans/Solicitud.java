package edu.pe.beans;

public class Solicitud {

	Integer NIDSOLICITUD,NIDOFERTA;
	String SEMAIL;
	
	public Solicitud() {
		
	}

	public Solicitud(Integer nIDSOLICITUD, Integer nIDOFERTA, String sEMAIL) {
		super();
		NIDSOLICITUD = nIDSOLICITUD;
		NIDOFERTA = nIDOFERTA;
		SEMAIL = sEMAIL;
	}
	
	public Solicitud(Integer nIDOFERTA, String sEMAIL) {
		super();
		NIDOFERTA = nIDOFERTA;
		SEMAIL = sEMAIL;
	}

	public Integer getNIDSOLICITUD() {
		return NIDSOLICITUD;
	}

	public void setNIDSOLICITUD(Integer nIDSOLICITUD) {
		NIDSOLICITUD = nIDSOLICITUD;
	}

	public Integer getNIDOFERTA() {
		return NIDOFERTA;
	}

	public void setNIDOFERTA(Integer nIDOFERTA) {
		NIDOFERTA = nIDOFERTA;
	}

	public String getSEMAIL() {
		return SEMAIL;
	}

	public void setSEMAIL(String sEMAIL) {
		SEMAIL = sEMAIL;
	}
	
	
}
