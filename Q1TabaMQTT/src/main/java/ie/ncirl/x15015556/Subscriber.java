package ie.ncirl.x15015556;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Subscriber implements MqttCallback {
	
	 public Subscriber() {
		 
	 }

//		@Override //Error if not commented out
	public void connectionLost(Throwable cause) {
		System.out.println("Connection has been lost: " + cause.getStackTrace());
		
	}

//	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println(new String(message.getPayload()) + " arrived from topic " + topic + " with QOS " + message.getQos());
		
	}

//	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("Delivery is complete: " + token.isComplete());
		
	}
}
