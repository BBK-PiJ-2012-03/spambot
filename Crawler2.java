import java.util.*;
import java.net.*;
import java.io.*;
import javax.swing.text.BadLocationException;

public class Crawler implements Runnable {
	public SpamBotImpl sp = null;
	public Set<String> emailSet = null;
	public Set<String> linksToVisitSet = null;	
	public Set<String> linksVisitedSet = null;

	
	private String seed;
	
	
	public Crawler(SpamBotImpl spambot) {	
		this.sp = spambot;
		this.emailSet = spambot.emailSet;
		this.linksVisitedSet = spambot.linksVisitedSet;
		this.linksToVisitSet = spambot.linksToVisitSet;
	}
	
	
	
	public Set<String> getEmailSet() {
		return emailSet;
	}	
	
	public void run()  {
		
		run2();
		
	}
	
	public void run2() {
		synchronized(sp){
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
				//Set<String> str = myAnalyzer.getEmails();
				//System.out.println(str);
				emailSet.addAll(myAnalyzer.getEmails());
				
				linksToVisitSet.addAll(myAnalyzer.getLinks());			
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	}	
}	
	

