package dev.exceptions;

public class ReservationNonTrouveException extends RuntimeException {
	
	public ReservationNonTrouveException(){
        super();
    }
    
    public ReservationNonTrouveException(String s){
        super(s);
    }
	
}
