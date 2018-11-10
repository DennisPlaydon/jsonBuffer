//Author: Dennis Playdon

import org.json.JSONObject;

public interface JSONBuffer {
	public void pullJSON(String name, JSONObject object);
	
	public void pushJSON(String name, JSONObject object);
}
