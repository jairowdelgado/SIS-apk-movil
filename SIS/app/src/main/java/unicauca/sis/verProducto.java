package unicauca.sis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class verProducto  extends AppCompatActivity {

    private TextView verNombreProducto;
    private TextView verPrecioProducto;
    private TextView verMarcaProducto;
    private TextView verCantidadProducto;
    private ImageButton verBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ver_producto);

        verBack = findViewById(R.id.verBack);

        verNombreProducto = findViewById(R.id.verNombreProducto);
        verPrecioProducto = findViewById(R.id.verPrecioProducto);
        verMarcaProducto = findViewById(R.id.verMarcaProducto);
        verCantidadProducto = findViewById(R.id.verCantidadProducto);

        mostrarProducto();

        verBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = getIntent().getExtras();
                String usuario =  extras.getString("usuario");
                //String contrasenia=extras.getString("contrasenia");
                Intent intentIngre = new Intent(verProducto.this, ScaneoUsuario.class);
                intentIngre.putExtra("usuario",usuario);
                //intentIngre.putExtra("contrasenia", contrasenia);
                startActivity(intentIngre);
            }
        });

    }

    private void mostrarProducto(){
        Bundle extras = getIntent().getExtras();
        Producto producto = (Producto) extras.getSerializable("producto");
        verNombreProducto.setText(producto.getNombre());
        verMarcaProducto.setText(producto.getMarca());
        verPrecioProducto.setText("$" + producto.getPrecio());
        verCantidadProducto.setText(producto.getCantidad() + producto.getMedida());
    }



}
