package edmt.dev.androidmp3stream;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class WhatsFragment extends Fragment {

    WebView web;

    public WhatsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View rootView = inflater.inflate(R.layout.fragment_whats, container, false);

        Bundle bundle = getArguments();

        web = (WebView) rootView.findViewById(R.id.webViewWhats);

        WebSettings ws = web.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(false);

        web.loadUrl("https://api.whatsapp.com/send?phone=5522997629116&text=Olá%20rádio%20interativa!");

        web.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                ProgressBar pb = (ProgressBar) rootView.findViewById(R.id.progressBarWhats);
                pb.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                ProgressBar pb = (ProgressBar) rootView.findViewById(R.id.progressBarWhats);
                pb.setVisibility(View.INVISIBLE);
                web.setVisibility(View.INVISIBLE);

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity( intent );
            }
        });
        return rootView;
    }

}