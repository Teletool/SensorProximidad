package com.example.johntitor.sensorproximidad;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    RelativeLayout layout;
    Button btn;
    Sensor s;
    SensorManager sensorM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.btnSensor);
        layout = (RelativeLayout)findViewById(R.id.layout);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorM = (SensorManager)getSystemService(SENSOR_SERVICE);
                s = sensorM.getDefaultSensor(Sensor.TYPE_PROXIMITY);
                sensorM.registerListener(MainActivity.this, s, SensorManager.SENSOR_DELAY_NORMAL);
                btn.setText("Activado");
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent evento) {
        float valor=Float.parseFloat(String.valueOf(evento.values[0]));
        if (valor <= 4){ //depende del telefono, mirar en android monitor
            layout.setBackgroundColor(Color.BLUE); //cambia a azul si algo esta cerca
        }else{
            layout.setBackgroundColor(Color.RED); //rojo si esta lejos
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
