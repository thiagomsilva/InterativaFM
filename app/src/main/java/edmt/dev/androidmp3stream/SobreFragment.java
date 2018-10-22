package edmt.dev.androidmp3stream;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SobreFragment extends Fragment {

    //private Toolbar mtoolbar;


    public SobreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //mtoolbar = findViewById(R.id.)
        //mtoolbar.setTitle("Sobre");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sobre, container, false);
    }

}
