package jmguisado.iesnervion.es.macroidclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;

import jmguisado.iesnervion.es.macroidclient.Connectivity.MacroidConnection;

/**
 * Precondición para que esta actividad sea lanzada:
 * EXISTE UNA CONEXIÓN (SOCKET)
 */
public class MacroActivity extends AppCompatActivity {

    private DataOutputStream mDos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_macro_activities);

//        if(MacroidConnection.Instance().getCurrentConnection() != null) {
//            Toast.makeText(this, "Estás conectado", Toast.LENGTH_LONG).show();
//        }

        try {
            mDos = new DataOutputStream(MacroidConnection.Instance().getCurrentConnection().getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "Todo listo", Toast.LENGTH_SHORT).show();
    }

    /**
     * Método para el envío de datos al servidor general
     * usado por todos los botones, el número enviado
     * varía según el nombre del botón.
     *
     * @param view El botón pulsado
     */
    public void SendMacro(View view) {
        Button sender = (Button) view;
        String macroString = String.valueOf(sender.getText().charAt(1));

        try {
            mDos.write(macroString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
