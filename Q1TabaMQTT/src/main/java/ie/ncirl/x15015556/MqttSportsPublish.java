package ie.ncirl.x15015556;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttSportsPublish {
//	One publisher announces news in sports, sports/football, sports/cricket,
//	sports/regional, and another publisher that announces news on politics, 
//	politics/elections and business/regional. Implement these 2 publishers and 
//	demonstrate the sending of messages on these topics and subtopics.
	
	private static MqttClient client;
	
	public static void main(String[] args) {
		String sports = "/sports";
		String football = sports + "/football";
		String cricket = sports + "/cricket";
		String regional = sports + "/regional";
		
		String sportsStatus = "7 new articles: 2 in football, 1 in cricket, and 4 in regional";
		
		int qos = 2;
		
		String broker = "tcp://localhost:1883";
		String clientID = "SportsPublisher";
		
		MemoryPersistence pers = new MemoryPersistence();
		
		try {
			client = new MqttClient(broker, clientID, pers);
			MqttConnectOptions options = new MqttConnectOptions();
			
			options.setCleanSession(true);
			options.setKeepAliveInterval(180);
			
			System.out.println("Connection to broker: " + broker);
			client.connect(options);
			System.out.println("Connected");
			
			// This would be used to connect to the "Sports" middle device/URL in a real-world scenario.
//			String status = sportsLoc.getResult("Sports");
//			String footballStatus = status.getResult("Football");
//			String cricketStatus = status.getResult("Cricket");
//			String regionalStatus = status.getResult("Regional");
//			
//			publishMessage(sports, status, qos, false);
//			publishMessage(football, footballStatus, 2, false);
//			publishMessage(cricket, cricketStatus, 1, false);
//			publishMessage(regional, regionalStatus, 1, false);	
			
			publishMessage(sports, sportsStatus, qos, false);
			publishMessage(football, "2 new story articles", 1, false);
			publishMessage(cricket, "1 new story articles", 1, false);
			publishMessage(regional, "4 new story articles", 1, false);	
			
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
