package unicauca.sis;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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
public class ScaneoUsuario  extends  AppCompatActivity{

        //VARIABLE CON EL ROL
        private Button btnReciente;
        private ImageButton btnScanner;
        private String codigo;
        private AccesoDatos AD = new AccesoDatos();

        @SuppressLint("WrongViewCast")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_prueba);

            AD.listarProducto();
            btnScanner = findViewById(R.id.botonEscaner);
            btnScanner.setOnClickListener(mOnClickListener);
            btnReciente=findViewById(R.id.btnRecientes);
            btnReciente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ScaneoUsuario.this,MenuRecientes.class);
                    startActivity(intent);
                }
            });

        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null){
            if(result.getContents() != null){

                Producto producto = AD.getProducto(String.valueOf(result));

                Intent i= new Intent(this,verProducto.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("producto", producto);
                i.putExtras(bundle);
                startActivity(i);

                /*
                codigo = result.getContents();
                Intent menuIn= new Intent(this,EscaneoProducto.class);
                startActivity(menuIn);
                */
            }else{
                codigo = "error";
                System.out.println(" *************************** El resultado del escaner es: " + codigo);
            }
        }
        }

        private View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.botonEscaner:
                        new IntentIntegrator(unicauca.sis.ScaneoUsuario.this).initiateScan();
                        break;
                }
            }
        };


    }

