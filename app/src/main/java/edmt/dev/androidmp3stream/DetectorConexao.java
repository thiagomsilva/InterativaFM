package edmt.dev.androidmp3stream;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by thiagomagalhaes on 17/02/2018.
 * Classe que faz a verificação da conexão com a internet
 */

public class DetectorConexao {

    Context context;

    public DetectorConexao(Context context) {
        this.context = context;

    }

    public boolean Conectado(){
        ConnectivityManager conexao = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if(conexao != null){
            NetworkInfo info = conexao.getActiveNetworkInfo();
            if(info != null){
                if(info.getState() == NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }

        return false;
    }
}
