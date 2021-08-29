package hadoop.program;

public class CombinedProgram {
	
	public static void main(String[] args) throws Exception {	
		if(args != null && args.length == 3) {
			if(args[2].equals("check")) {
				LanguageFinderProgram.main(args);
				return;
			}
			if(args[2].equals("generate")) {
				CooccurrenceProgram.main(args);
				return;
			}
		}
		
		throw new IllegalStateException("Invalid arguments, usage: hadoop jar <jarname> <input> <output> <options>; "
				+ "valid options: [1] check [2] generate");
	}	
}
