package unicauca.sis;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.Console;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EscaneoProducto extends AppCompatActivity {

    //VARIABLE CON EL ROL

    private ImageButton btnScanner;
    private String codigo;
    private AccesoDatos AD = new AccesoDatos();

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_escaner);

        AD.listarProducto();
        btnScanner = findViewById(R.id.botonEscaner);
        btnScanner.setOnClickListener(mOnClickListener);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null){
            if(result.getContents() != null){
                //codigo = result.getContents();
                Intent menuIn= new Intent(this, crearProducto.class);
                menuIn.putExtra("codigo", String.valueOf(result.getContents()));
                startActivity(menuIn);
            }else{

            }
        }

        /*
        ESTE SIRVE PARA VER UN PRODUCTO ATRAVÉS DE UN CODIGO
        Producto producto = AD.getProducto("20");

        Intent i= new Intent(this,verProducto.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("producto", producto);
        i.putExtras(bundle);
        startActivity(i);
        */

    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.botonEscaner:
                    new IntentIntegrator(EscaneoProducto.this).initiateScan();
                    break;
            }
        }
    };


}
