package mqclient.ameya.com.myapplication;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.eclipse.paho.client.mqttv3.MqttPersistenceException;



/**
 * Created by AMEYA on 10/10/2015.
 */
public class PublishService extends Fragment{

    public String publishTopic="";
    public String publishMessage="";




    Context context=getActivity();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.publish_main, container, false);

       final EditText topic= (EditText) view.findViewById(R.id.topic);
       final EditText message= (EditText) view.findViewById(R.id.message);
       final EditText count= (EditText) view.findViewById(R.id.count);

        Button publishButton = (Button) view.findViewById(R.id.buttonPublish);



        publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(MqHelper.isAlreadyConnected()){

                    try {
                        MqHelper.publish(topic.getText().toString(),message.getText().toString(),context);
                    }catch (MqttPersistenceException ex){
                        Log.d("Error:", ex.getMessage());
                    }
                }
            }
        });
        return view;
    }


}
