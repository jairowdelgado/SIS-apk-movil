package unicauca.sis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class verProducto  extends AppCompatActivity {

    private TextView verNombreProducto;
    private TextView verPrecioProducto;
    private TextView verMarcaProducto;
    private TextView verCantidadProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ver_producto);

        verNombreProducto = findViewById(R.id.verNombreProducto);
        verPrecioProducto = findViewById(R.id.verPrecioProducto);
        verMarcaProducto = findViewById(R.id.verMarcaProducto);
        verCantidadProducto = findViewById(R.id.verCantidadProducto);

        mostrarProducto();

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
