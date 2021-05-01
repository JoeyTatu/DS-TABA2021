package ie.ncirl.x15015556;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttPoliticsPublish {
	
	private static MqttClient client;

	public static void main(String[] args) {
//		One publisher announces news in sports, sports/football, sports/cricket,
//		sports/regional, and another publisher that announces news on politics, 
//		politics/elections and business/regional. Implement these 2 publishers and 
//		demonstrate the sending of messages on these topics and subtopics.
		
		String politics = "/politics";
		String elections = politics + "/elections";
		
		String business = "/business";
		String regional = business + "/regional";
		
		String politicsStatus = "1 new political article";
		
		String businessStatus = "1 new business article";
		
		int qos = 2;
		
		String broker = "tcp://localhost:1883";
		String clientID = "PoliticsPublisher";
		
		MemoryPersistence pers = new MemoryPersistence();
		
		try {
			client = new MqttClient(broker, clientID, pers);
			MqttConnectOptions options = new MqttConnectOptions();
			
			options.setCleanSession(true);
			options.setKeepAliveInterval(180);
			
			System.out.println("Connected to broker: " + broker);
			client.connect();
			System.out.println("Connected");
			
			// This would be used to connect to the "politics"/"business" middle device/URL in a real-world scenario.
//			String politicsStatus = politicsLoc.getResult("Politics");
//			String electionsStatus = politicsStatus.getResult("Elections");
//			
//			String businessStatus = businessLoc.getResult("Business");
//			String regionalStatus = businessStatus.getResult("Regional");
//		
//			
//			publishMessage(politics, politicsStatus, qos, false);
//			publishMessage(elections, electionsStatus, 2, false);
//			
//			publishMessage(business, businessStatus, 1, false);
//			publishMessage(regional, regionalStatus, 1, false);
			
			publishMessage(politics, politicsStatus, qos, false);
			publishMessage(elections, "New: Election set for 1 May 2022", 1, false);
			
			publishMessage(business, businessStatus, qos, false);
			publishMessage(regional, "3 new articles for Dublin region", 1, false);	
			
			client.disconnect();
			System.out.println("Disconnected");
			client.close();
			System.exit(0);
			
		}  catch (MqttException me) {
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
	
	public static void publishMessage(String topic, String payload, int qos, boolean retained) {
		
		System.out.println("Publishing message from " + topic + " --- " + payload);
		
		MqttMessage message = new MqttMessage(payload.getBytes());
		message.setRetained(retained);
		message.setQos(qos);
		
		try {
			client.publish(topic, message);
			
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
