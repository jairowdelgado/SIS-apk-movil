package unicauca.sis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void ingresar(View view){
        //Intent menuIn= new Intent(this,MenuInicioAdmin.class);
        Intent menuIn= new Intent(this,EscaneoProducto.class);
        startActivity(menuIn);
    }

}