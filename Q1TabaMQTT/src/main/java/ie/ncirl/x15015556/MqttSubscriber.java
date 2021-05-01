package ie.ncirl.x15015556;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttSubscriber {

	public static void main(String[] args) {
		String broker = "tcp://localhost:1883";
		String clientID = "Subscriber";
		
		MemoryPersistence pers = new MemoryPersistence();
		
		try {
			MqttClient client = new MqttClient(broker, clientID, pers);
			MqttConnectOptions options = new MqttConnectOptions();
			
			options.setCleanSession(true);
			
			client.setCallback(new Subscriber());
			
			System.out.println("Connected to broker: " + broker);
			client.connect(options);
			
			client.subscribe("/#");
			
		} catch (MqttException me) {
			System.out.println(
					"Reason: " + me.getReasonCode() +
					"\nMessage: " + me.getMessage() +
					"\nLoc message: " + me.getLocalizedMessage() +
					"\nCause: " + me.getCause() +
					"\nException: " + me +
					"\n"
					);
			 me.printStackTrace();
		}
	}
}
