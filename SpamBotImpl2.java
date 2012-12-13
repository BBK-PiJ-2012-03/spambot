import java.net.*;
import java.util.*;
import java.io.IOException;
import javax.swing.text.BadLocationException;
public class SpamBotImpl implements SpamBot {

	public Set<String> emailSet = new HashSet<String>();
	public Set<String> linksToVisitSet = new HashSet<String>();
	public Set<String> linksVisitedSet = new HashSet<String>();

	String seedUrl;
	
	int processors_num = Runtime.getRuntime().availableProcessors();
	
	public SpamBotImpl() {
		seedUrl = null;
		emailSet = new HashSet<String>();
	}
	
	public void setSeed(String seedUrl){
		this.seedUrl = seedUrl;
	}	
	
	public String getSeed() {
		return seedUrl;
	}

	public void scanSite() {
		
		linksToVisitSet.add(seedUrl);
		
		for (int i = 0; i < processors_num; i++) {
			
			
			Runnable r = new Crawler(this);
			Thread t = new Thread(r);
			t.start();
		}
		
		
		
		
	}
	
	public Set<String> getEmails() {
		return emailSet;
	}
	
	public static void main(String[] args){
		SpamBotImpl script = new SpamBotImpl();
		script.launch();
		
		/* debug
		try{
			WebAnalyzer myAnalyzer = new WebAnalyzer("http://www.dcs.bbk.ac.uk/~peng/");
			System.out.println(myAnalyzer.getEmails());
		} catch(Exception e){
			e.printStackTrace();
		}
		*/
	}

	public void launch() {
		setSeed("http://www.thecareersgroup.co.uk/contact-us/college-careers-services.aspx");
		scanSite();
		
		
		System.out.println(emailSet);
		
	}
}	
		