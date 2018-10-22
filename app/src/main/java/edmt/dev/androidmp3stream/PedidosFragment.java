package edmt.dev.androidmp3stream;

import android.graphics.Bitmap;
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
public class PedidosFragment extends Fragment {

    WebView wv;

    public PedidosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_pedidos, container, false);

        Bundle bundle = getArguments();

        wv = (WebView) rootView.findViewById(R.id.webViewPedido);

        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(false);
        wv.loadUrl("http://www.interativafm.org/peca-sua-musica");

        wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                ProgressBar pb = (ProgressBar) rootView.findViewById(R.id.progressBarPedido);
                pb.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                ProgressBar pb = (ProgressBar) rootView.findViewById(R.id.progressBarPedido);
                pb.setVisibility(View.INVISIBLE);
                wv.setVisibility(View.VISIBLE);
            }
        });
        return rootView;
    }
}
