package eg.edu.guc.yugioh.exceptions;

public class UnknownSpellCardException extends UnexpectedFormatException{
	
	private String unknownSpell;
	
	public UnknownSpellCardException(){
		super();
	}
	
	public UnknownSpellCardException(String sf, int sl, String us){
		super(sf, sl);
		setUnknownSpell(us);
	}

	public String getUnknownSpell() {
		return unknownSpell;
	}

	public void setUnknownSpell(String unknownSpell) {
		this.unknownSpell = unknownSpell;
	}


}
