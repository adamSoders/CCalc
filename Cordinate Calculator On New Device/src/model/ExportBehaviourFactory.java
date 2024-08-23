package model;

import java.io.File;
import java.io.IOException;

public class ExportBehaviourFactory {
	
	private ExportBehaviour e;

	public ExportBehaviourFactory() { // Creates exportbehaviour based on the extension of a file
		
	}
	
	public ExportBehaviour createExportBehaviour(File file) throws IOException { 
		
		String fileName = file.getName();
		
		if (fileName.endsWith(".ser")) {
			e = new FileSerializer();	
		} else if (fileName.endsWith(".htm")) {
			e = new HtmlEXporter();
		} else {
			throw new IOException("Unsupported file extension");
		}
		
		return e;
			
		}
		
}
