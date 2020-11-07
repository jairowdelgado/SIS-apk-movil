package unicauca.sis;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

import unicauca.sis.utilidades.DAO;
import unicauca.sis.utilidades.Sqlconexion;

public class ScaneoUsuario  extends  AppCompatActivity{

        //VARIABLE CON EL ROL
        private Button btnReciente;
        private ImageButton btnScanner;
        private Button btnCerrarSesion;
        private String codigo;
        private AccesoDatos AD = new AccesoDatos();

        @SuppressLint("WrongViewCast")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_prueba);

            AD.listarProducto();
            btnScanner = findViewById(R.id.botonEscaner);
            btnCerrarSesion = findViewById(R.id.btnCerrarSesionUsuario);
            btnScanner.setOnClickListener(mOnClickListener);
            btnReciente=findViewById(R.id.btnRecientes);
            btnReciente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ScaneoUsuario.this,MenuRecientes.class);
                    Bundle  usua=getIntent().getExtras();
                    intent.putExtra("usuario",usua.getString("usuario"));
                    startActivity(intent);
                }
            });

            btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ScaneoUsuario.this,MainActivity.class);
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
                Bundle  usua=getIntent().getExtras();
                User usuario=new User();
                usuario.setUsuario(usua.getString("usuario"));
                Producto producto = new Producto();
                producto=AD.getProducto(result.getContents());
                registrarRecientes(producto,usuario);

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
    public void registrarRecientes(Producto producto,User usuario){
        Sqlconexion sqlconexion= new Sqlconexion(this,"bd_reciente",null,1);
        SQLiteDatabase db = sqlconexion.getWritableDatabase();


        ContentValues values= new ContentValues();
        values.put(DAO.CAMPO_CODIGO,producto.getCodigo());
        values.put(DAO.CAMPO_NOMBRE,producto.getNombre());
        values.put(DAO.CAMPO_MARCA,producto.getMarca());
        values.put(DAO.CAMPO_CANTIDAD,producto.getCantidad());
        values.put(DAO.CAMPO_PRECIO,producto.getPrecio());
        values.put(DAO.CAMPO_MEDIDA,producto.getMedida());
        values.put(DAO.CAMPO_ESTADO,producto.isEstado());

        values.put(DAO.CAMPO_USUARIO,usuario.getUsuario());
        System.out.println(DAO.CAMPO_MARCA+" mara  "+producto.getMarca());
        Long idre =db.insert(DAO.TABLA_PRODUCTO,DAO.CAMPO_CODIGO,values);
        db.close();
    }
    }

