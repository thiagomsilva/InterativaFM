package edmt.dev.androidmp3stream;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {

    private AdView mAdView;
    private ImageButton btn_play_pause;
    private MediaPlayer mediaPlayer;
    private DetectorConexao dc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Botão Play/Pause
        btn_play_pause = (ImageButton) findViewById(R.id.btn_play_pause);
        dc = new DetectorConexao(this);

        btn_play_pause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(MainActivity.this);

                AsyncTask<String, String, String> mp3Play = new AsyncTask<String, String, String>() {

                    @Override
                    protected void onPreExecute() {
                        if (dc.Conectado() == false){
                            mDialog.setMessage("Sem conexão com a internet, favor verificar!");
                            mDialog.show();
                        } else {
                            mDialog.setMessage("Carregando...");
                            mDialog.show();
                        }
                    }

                    @Override
                    protected String doInBackground(String... params) {
                        try {
                            mediaPlayer.setDataSource(params[0]);
                            mediaPlayer.prepare();
                        } catch (Exception ex) {

                        }
                        return "";
                    }

                    @Override
                    protected void onPostExecute(String s) {

                        if (!mediaPlayer.isPlaying() && dc.Conectado()) {
                            mediaPlayer.start();
                            btn_play_pause.setImageResource(R.mipmap.ic_pause);

                        } else {
                            mediaPlayer.pause();
                            btn_play_pause.setImageResource(R.mipmap.ic_play);
                        }

                        mDialog.dismiss();
                    }
                };

                mp3Play.execute("http://216.245.220.173:8618/live"); // Endereço do Streaming
            }
        });

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(this);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        btn_play_pause.setImageResource(R.mipmap.ic_play);
    }
}
