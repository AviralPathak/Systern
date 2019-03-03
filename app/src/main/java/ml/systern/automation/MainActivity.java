package ml.systern.automation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btn1);
                String clientId = MqttClient.generateClientId();
                MqttAndroidClient client =
                        new MqttAndroidClient(MainActivity.this, "tcp://broker.hivemq.com:1883",
                                clientId);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    IMqttToken token = client.connect();
                    token.setActionCallback(new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Toast.makeText(MainActivity.this, "Connected", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Toast.makeText(MainActivity.this, "Not Connected", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
