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

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class EscaneoProducto extends AppCompatActivity {

    //VARIABLE CON EL ROL
    private Button btnBuscarAdmin;
    private ImageButton btnScanner;
    private Button btnCerrarSesion;
    private String codigo;
    private AccesoDatos AD = new AccesoDatos();

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_admin_menu);

        AD.listarProducto();
        btnScanner = findViewById(R.id.botonEscaner);
        btnScanner.setOnClickListener(mOnClickListener);
        btnBuscarAdmin = findViewById(R.id.btnBuscarProducto);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesionAdmin);

        btnBuscarAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EscaneoProducto.this, MenuBuscar.class);
                intent.putExtra("actualizar","false");
                startActivity(intent);
            }
        });

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EscaneoProducto.this,MainActivity.class);
                startActivity(i);
                Toast toast = Toast.makeText(getApplicationContext(),"Sesión cerrada",Toast.LENGTH_LONG);
                toast.show();
            }
        });
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
