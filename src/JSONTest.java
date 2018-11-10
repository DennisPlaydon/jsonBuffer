//Author: Dennis Playdon

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class JSONTest {
	
	public static void main(String[] args) {
		JSONBuffer buffer = new ThreadSafeBuffer();
	    
		//creating some threads with different people
		JSONThread alice = new JSONThread("Alice", buffer);
		JSONThread bob = new JSONThread("Bob", buffer);
		JSONThread charlie = new JSONThread("Charlie", buffer);
		JSONThread donna = new JSONThread("Donna", buffer);
		JSONThread eve = new JSONThread("Eve", buffer);
		JSONThread fred = new JSONThread("Fred", buffer);
		
		ExecutorService es = Executors.newCachedThreadPool();
		
		es.execute(alice);
		es.execute(bob);
		es.execute(charlie);
		es.execute(donna);
		es.execute(eve);
		es.execute(fred);
		
		es.shutdown();
	}
}
