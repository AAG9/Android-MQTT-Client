package mqclient.ameya.com.myapplication;

/**
 * Created by AMEYA on 10/10/2015.
 */
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/**
 * Created by AMEYA on 10/10/2015.
 */
public class MqHelper {
    //static String topic        = "AmeyaChikodi";
    //static String content      = "Message from MqttPublishSample";


    static int qos             = 1;
    static String broker       = "tcp://m2m.eclipse.org:1883";
    static String clientId     = "JavaSample";
    static MqttAndroidClient mqClient;
    public static boolean connectionStatus =false;


    public static void doConnect(Context ctx) throws MqttException  {


        mqClient=new MqttAndroidClient(ctx,broker,clientId);

        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        System.out.println("Connecting to broker: " + broker);

        mqClient.connect(connOpts, ctx, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken iMqttToken) {
                Log.d("SUCCESS", "Connected");
                connectionStatus=true;
            }

            @Override
            public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                Log.d("ERROR", "Not connected");
            }
        });

    }

    public static void publish(String topic,String content,Context ctx) throws MqttPersistenceException {
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(qos);

        Log.d("Publishing in topic", topic);
        try {
            mqClient.publish(topic, message, ctx, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken iMqttToken) {
                    Log.d("SUCCESS", "Published");
                }

                @Override
                public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                    Log.d("ERROR", "Not Published");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public static void subscribe(String topic,Context context) throws MqttException {
        try {
            mqClient.subscribe(topic, qos, context, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken iMqttToken) {
                    Log.d("Success", "Subscribed!");
                }

                @Override
                public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                    Log.d("ERROR", "Not Subscribed");
                }
            });
        }catch (MqttPersistenceException ex){
            Log.d("Error", String.valueOf(ex));
        }
    }

    public static boolean isAlreadyConnected(){
        if(mqClient.isConnected()) {
            return true;
        }else {
            return false;
        }
    }
    public static void doDisconnect(final Context context) throws MqttException {
        if(isAlreadyConnected()){
            mqClient.disconnect(context, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken iMqttToken) {
                    Log.d("SUCCESS:","Disconnected!");
                    Toast.makeText(context,"Disconnected successfully",Toast.LENGTH_LONG);
                }

                @Override
                public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                    Log.d("ERROR:","Not Disconnected!");
                }
            });
        }
    }
}
