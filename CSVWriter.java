import java.io.IOException;
import java.io.Writer;

public class CSVWriter {
	private final Writer writer;
	
	void writeResult(CSVAnalyzer analyzer) throws IOException {
		for (var it : analyzer.getsortedMapList()) {
            writer.write(it.getKey() + ";" + it.getValue().toString() + ";" + (100 * (double)it.getValue()/analyzer.getcount()) + "%\n");
		}    
	}
    
    public CSVWriter(Writer writer,  CSVAnalyzer analyzer) throws IOException { 
        this.writer = writer; 
        writeResult(analyzer);
    }
    
}

