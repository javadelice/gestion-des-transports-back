package dev.exception;

public class ReservationNonTrouveException extends RuntimeException {
	
	public ReservationNonTrouveException(){
        super();
    }
    
    public ReservationNonTrouveException(String s){
        super(s);
    }
	
}
