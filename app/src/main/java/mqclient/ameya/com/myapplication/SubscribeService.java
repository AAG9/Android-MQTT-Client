package mqclient.ameya.com.myapplication;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMEYA on 10/10/2015.
 */
public class SubscribeService extends Fragment {

    Context ctx=getActivity();
    List<String> subscribedTopicList = new ArrayList<String>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.subscribe_main, container, false);

        final EditText subscribe= (EditText) view.findViewById(R.id.subscribe);

        Button subscribeButton = (Button) view.findViewById(R.id.buttonSubscribe);

        subscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MqHelper.connectionStatus){
                    //subscribedTopicList.add(topic.getText().toString());
                    //String [] subscribedTopic=new String[subscribedTopicList.size()];
                   // subscribedTopicList.toArray(subscribedTopic);

                    if(MqHelper.isAlreadyConnected()) {
                        Log.e("MQTT","Unable to subscribe as we are already connected");
                    }
                    else{
                        try {
                            MqHelper.subscribe(subscribe.getText().toString(),ctx);
                        } catch (MqttException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        return view;
    }
}
