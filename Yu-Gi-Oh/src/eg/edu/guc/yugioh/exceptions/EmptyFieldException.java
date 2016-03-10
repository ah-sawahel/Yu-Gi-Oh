package eg.edu.guc.yugioh.exceptions;

public class EmptyFieldException extends UnexpectedFormatException{
	
	private int sourceField;
	
	public EmptyFieldException(){
		super();
	}
	
	public EmptyFieldException(String sf, int sl, int sfi){
		super(sf,sl);
		setSourceField(sfi);
	}

	public int getSourceField() {
		return sourceField;
	}

	public void setSourceField(int sourceField) {
		this.sourceField = sourceField;
	}
}
