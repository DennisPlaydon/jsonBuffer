//Author: Dennis Playdon

import java.security.SecureRandom;
import org.json.JSONObject;

public class JSONThread implements Runnable {
	private static final SecureRandom randomGenerator 
    = new SecureRandom();
	public String name;
	public JSONBuffer buffer;

	public JSONThread(String name, JSONBuffer buffer) {
		this.name = name;
		this.buffer = buffer;
	}
	
	public void run() {
		
		for (int i=0; i<20; i++) {
			
			//creating JSON objects with name and a random number
	        JSONObject obj = new JSONObject();
	        obj.put("name", name);
			int numberOfBooks = randomGenerator.nextInt(15);
			obj.put("items", numberOfBooks);
			
			//calls ThreadSafeBuffer to handle interactions with JSON objects
			buffer.pushJSON(name, obj);
			try {
    			Thread.sleep(randomGenerator.nextInt(20));
			}
			catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			//Patron reads from thread eventually after writing to it
			buffer.pullJSON(name, obj);
		}
	}
}

