package jmguisado.iesnervion.es.macroidclient;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.regex.Pattern;

import jmguisado.iesnervion.es.macroidclient.Connectivity.MacroidConnection;

/**
 * Esta actividad contiene una entrada de texto donde debe introducirse
 * la IP del servidor Macroid, la lógica más abajo provee validación de entrada
 * y establecimientos de event listeners para el correcto funcionamiento.
 */
public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private Pattern IPv4_Pattern = Pattern.compile("^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

    }


//    Method that handles the btn_Connect onClick

    /**
     * Intenta realizar una conexión con el servidor indicado
     * en el EditText
     * @param view
     */
    public void TryConnect(View view) {
        EditText et_IP = (EditText) findViewById(R.id.et_IP);
        String IP = et_IP.getText().toString();

        // Comprobación de la IP con Expresión Regular, podría extraerse a otra clase

        boolean validIP = IPv4_Pattern.matcher(IP).matches();

        Toast.makeText(this, validIP ? "Válido" : "No válido", Toast.LENGTH_SHORT).show();

        new TryConnectTask().execute(IP);
    }

    private class TryConnectTask extends AsyncTask<String, Void, Socket> {
        @Override
        protected Socket doInBackground(String... params) {
            Socket clientSocket = null;
            String IP = params[0];
            try {
                clientSocket = new Socket(IP, 52027);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return clientSocket;
        }

        @Override
        protected void onPostExecute(Socket socket) {
            Intent macroActivityIntent = new Intent(mContext, MacroActivity.class);
            MacroidConnection.Instance().setCurrentConnection(socket);

            if (socket != null) {
                startActivity(macroActivityIntent);
            }
        }
    }
}
