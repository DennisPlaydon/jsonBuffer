//Author: Dennis Playdon

import java.util.Arrays;
import java.util.Stack;
import org.json.JSONObject;

public class ThreadSafeBuffer implements JSONBuffer {
	private Stack<JSONObject> jsonStack = new Stack<JSONObject>();
    
    public synchronized void pullJSON(String patron, JSONObject object) {
    	
    	//While buffer is empty tell thread to wait
    	while (jsonStack.empty()) {
    		try {
        		wait();
    		}
    		catch (InterruptedException e) {
    			Thread.currentThread().interrupt();
    		}
    	}
    	System.out.println(patron + " READ from Stack");
    	System.out.println("Item read: " + jsonStack.pop());
    	System.out.println(Arrays.toString(jsonStack.toArray()));
    	//Let all waiting threads know there is less objects in the array than the max buffer size
    	notifyAll();
    }
    
    public synchronized void pushJSON(String patron, JSONObject object) {
    	int bufferSize = 1;
    	//Allows buffer size to be changed
    	while (jsonStack.size() >= bufferSize) {
    		try {
        		wait();
    		}
    		catch (InterruptedException e) {
    			Thread.currentThread().interrupt();
    		}
    	}
    	jsonStack.push(object);
    	System.out.println(patron + " WROTE to Stack");
    	System.out.println(Arrays.toString(jsonStack.toArray()));
    	// Let all waiting threads know there is JSON objects in the array
    	notifyAll();
    }
}
