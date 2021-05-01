package ie.ncirl.x15015556;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
//import org.eclipse.paho.client.mqttv3.*;

public class MqttSubscriberExtra {

	public static void main(String[] args) {
		String broker = "tcp://localhost:1883";
		String clientID = "SubscriberExtra";
		
		MemoryPersistence pers = new MemoryPersistence();
		
		try {
			MqttClient client = new MqttClient(broker, clientID, pers);
//			MqttDurable ds = new MqttDurable();
			MqttConnectOptions options = new MqttConnectOptions();
			
//			options.setCleanSession(true); // Non-durable
			
//			Q5(e)
			options.setCleanSession(false); // Non-durable
			
			client.setCallback(new Subscriber());
			

			
			System.out.println("Connected to broker: " + broker);
			client.connect(options);
			
			//Q1(b)
			client.subscribe("/sports/football");
			
			//Q1(c)
			client.subscribe("/politics/#");
			
			//Q1(d)
			client.subscribe("/+/regional");
			// "+" denotes a wild card - "?" and "*" do not work
			
			//Q1(e)
//			client.subscribe("$SharedSubscription/DurableSubs/poltics");

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
