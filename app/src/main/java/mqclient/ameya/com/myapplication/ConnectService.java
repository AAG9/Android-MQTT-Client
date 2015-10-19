package mqclient.ameya.com.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;


import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Created by AMEYA on 10/17/2015.
 */
public class ConnectService extends Fragment{
    Context mActivity;


    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (FragmentActivity) activity;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_main, container, false);


        final Switch connectSwitch = (Switch) view.findViewById(R.id.connectSwitch);


        //set the switch to ON
        //mySwi.setChecked(true);
        //attach a listener to check for changes in state

        connectSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    try {
                        MqHelper.doConnect(mActivity);
                    } catch (MqttException ex) {
                        Log.d("ERROR", ex.getMessage());
                    }


                } else {
                    try {
                        MqHelper.doDisconnect(mActivity);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        return view;
    }
}
