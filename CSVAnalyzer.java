import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CSVAnalyzer {
	 private final Reader reader;
	 private long count = 0;
	 private final Map<String, Integer> map;
	 private List<Map.Entry<String, Integer>> sortedMapList;
	 
	 private void incrementMap(String word) {
	        map.put(word, map.getOrDefault(word, 0) + 1);
	        ++count;
	    }
	 
	  private void fillMap() throws IOException {
	        StringBuilder word = new StringBuilder();

	        while (reader.ready()) { 
	        	word.append((char)reader.read());
	        }
	      
	        Pattern p = Pattern.compile("[\\p{L}0-9]+");
       	    Matcher m = p.matcher(word);
       	 
       	    while (m.find()) {
            	 incrementMap(m.group());
            }
	    }
	    
	    private void compareResult() throws IOException{
	        Comparator<Map.Entry<String, Integer>> comp = new Comparator<Map.Entry<String, Integer>>() {
	            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
	                 return o2.getValue() - o1.getValue();    
	            }
	        };
	        sortedMapList = map.entrySet().stream().sorted(comp).collect(Collectors.toList());
	    }
	    
	    public List<Map.Entry<String, Integer>> getsortedMapList() {
	    	return sortedMapList;
	    }
	    
	    public long getcount() {
	    	return count; 
	    }
	    
	    public CSVAnalyzer(Reader reader) throws IOException {
	        map = new HashMap<>();
	        sortedMapList = new ArrayList<>();
	        this.reader = reader;
	        analyze();
	    }
	    
	     void analyze() throws IOException {
	        fillMap();
	        compareResult();   
	    }
}
