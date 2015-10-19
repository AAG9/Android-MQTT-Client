package mqclient.ameya.com.myapplication;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by AMEYA on 10/9/2015.
 */
public class SettingsService extends Fragment {
    public static String URL;
    public static String portAddress;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.settings_main, container, false);

        final DBHelper dbHelper=new DBHelper(getActivity());

         final EditText brokerURL= (EditText) view.findViewById(R.id.brokerURL);
         final EditText port= (EditText) view.findViewById(R.id.port);
         final EditText username= (EditText) view.findViewById(R.id.username);
         final EditText password= (EditText) view.findViewById(R.id.password);


         final Button save = (Button) view.findViewById(R.id.saveButton);


            save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                Toast.makeText(this, "1", Toast.LENGTH_SHORT);
//                 dbHelper.updateData(dbHelper,1,brokerURL.getText().toString(),port.getText().toString(),username.getText().toString(),password.getText().toString());



            }
        });

        return view;
    }


}
