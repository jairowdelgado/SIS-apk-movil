package unicauca.sis;

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

public class EscaneoProducto extends AppCompatActivity {

    //VARIABLE CON EL ROL

    private ImageButton btnScanner;
    private String codigo;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_escaner);

        btnScanner = findViewById(R.id.botonEscaner);

        btnScanner.setOnClickListener(mOnClickListener);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        /*
        if(result != null){
            if(result.getContents() != null){
                codigo = result.getContents();
                Intent menuIn= new Intent(this,EscaneoProducto.class);
                startActivity(menuIn);
            }else{
                codigo = "error";
                System.out.println(" *************************** El resultado del escaner es: " + codigo);
            }
        }*/

        Producto producto = new Producto();
        producto.setId("3030");
        producto.setNombre("juan");
        producto.setCodigo(30303);
        producto.setCantidad(3030);
        producto.setMarca("30303");
        producto.setPrecio(30000);
        producto.setMedida("g");
        DAO dao = new DAO();
        //dao.crearProducto(producto);
        dao.listarDatos();
        List<Producto> pr = dao.lista();
        System.out.println("*********TAMAÑO**********: "+pr.size());

/*
        ArrayList<Producto> productos = producto.listarDatos();

        for(int i = 0; i < productos.size(); i++){
            System.out.println(" NOMBRE :::: " + productos.get(i).getNombre());
        }
*/
        Intent i= new Intent(this,verProducto.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("producto", producto);
        i.putExtras(bundle);
        startActivity(i);
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
