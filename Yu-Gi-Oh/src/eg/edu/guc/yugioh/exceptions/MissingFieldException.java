package eg.edu.guc.yugioh.exceptions;

public class MissingFieldException extends UnexpectedFormatException{
	
	public MissingFieldException(){
		super();
	}
	
	public MissingFieldException(String sf, int sl){
		super(sf,sl);
	}
}
