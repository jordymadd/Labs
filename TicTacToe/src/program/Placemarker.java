package program;

public enum Placemarker {
	
	MARKER_X('X'), MARKER_0('O'); 
	
	char mark = ' '; 
	
	Placemarker(char mark){
	this.mark = mark; 
	}
	
	public char getMark(){
		return mark; 
	}

}
