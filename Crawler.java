import java.util.*;
import java.net.*;
import java.io.*;
import javax.swing.text.BadLocationException;

public class Crawler {
	Set<String> emailSet = null;
	Set<String> linksToVisitSet = null;
	Set<String> linksVisitedSet = null;
	
	
	public Crawler(String seedUrl) {
		emailSet = new HashSet<String>();
		linksToVisitSet = new HashSet<String>();
		linksVisitedSet = new HashSet<String>();
		
		linksToVisitSet.add(seedUrl);
	}
	
	public Set<String> getEmailSet() {
		return emailSet;
	}	
	
	public void start()  {
		Iterator<String> ite = linksToVisitSet.iterator();
		WebAnalyzer myAnalyzer;
		
		while(ite.hasNext() && emailSet.size() < 100){
			String link = ite.next();
			try{
				myAnalyzer = new WebAnalyzer(link);

				linksVisitedSet.add(link);
				linksToVisitSet.remove(link);
				//System.out.println(link); //debug
				//System.out.println(myAnalyzer.getEmails()); //debug
				emailSet.addAll(myAnalyzer.getEmails());
				linksToVisitSet.addAll(myAnalyzer.getLinks());			
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}	
	

