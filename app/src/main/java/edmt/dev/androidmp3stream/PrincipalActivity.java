package edmt.dev.androidmp3stream;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* BOTÃO DE E-MAIL

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Chamada do Fragment rádio quando inicia o app
        RadioFragment radio = new RadioFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.conteudo_fragment, radio).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

/*    @Override       CONFIGURAÇÕES
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_radio) {

            // Chamada do Fragment rádio pelo menu
            RadioFragment radio = new RadioFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.conteudo_fragment, radio).commit();


        } else if (id == R.id.nav_pedidos) {

            //Chamada do Fragment pedidos pelo menu
            PedidosFragment pedido = new PedidosFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.conteudo_fragment, pedido).commit();


        } else if (id == R.id.nav_whatsapp) {

            //Chamada do Fragment WhatsApp pelo menu
            WhatsFragment zap = new WhatsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.conteudo_fragment, zap).commit();

        } else if (id == R.id.nav_sobre) {

            //Chamada do Fragment WhatsApp pelo menu
            SobreFragment sobre = new SobreFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.conteudo_fragment, sobre).commit();

        } else if (id == R.id.nav_privacidade) {

            //Chamada do Fragment política de privacidade pelo menu
            PrivacidadeFragment privacidade = new PrivacidadeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.conteudo_fragment, privacidade).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
