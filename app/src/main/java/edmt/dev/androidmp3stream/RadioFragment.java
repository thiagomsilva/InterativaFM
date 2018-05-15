package edmt.dev.androidmp3stream;


import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class RadioFragment extends Fragment implements MediaPlayer.OnCompletionListener {

    private AdView mAdView;
    private ImageButton btn_play_pause;
    private MediaPlayer mediaPlayer;
    private DetectorConexao dc;


    public RadioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View View = inflater.inflate(R.layout.fragment_radio, container, false);

        Bundle bundle = getArguments();


        // instancia do banner da google
        mAdView = (AdView) View.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Botão Play/Pause
        btn_play_pause = (ImageButton) View.findViewById(R.id.btn_play_pause);

        // instância do detector de conexão com a internet
        dc = new DetectorConexao (getActivity());

        btn_play_pause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // instancia da caixa de diálogo
                final ProgressDialog mDialog = new ProgressDialog(getActivity());

                AsyncTask<String, String, String> mp3Play = new AsyncTask<String, String, String>() {

                    @Override
                    protected void onPreExecute() {

                        // verifica se existe conexão com a internet
                        if (dc.Conectado() == false) {
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
        return View;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

        btn_play_pause.setImageResource(R.mipmap.ic_play);

    }

}
