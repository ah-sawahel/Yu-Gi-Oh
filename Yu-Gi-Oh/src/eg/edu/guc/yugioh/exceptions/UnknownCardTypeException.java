package eg.edu.guc.yugioh.exceptions;

public class UnknownCardTypeException extends UnexpectedFormatException{
	
	private String unknownType;
	
	public UnknownCardTypeException(){
		super();
	}
	
	public UnknownCardTypeException(String sf, int sl, String ut){
		super(sf, sl);
		setUnknownType(ut);
	}

	public String getUnknownType() {
		return unknownType;
	}

	public void setUnknownType(String unknownType) {
		this.unknownType = unknownType;
	}

}
