package unicauca.sis;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class EditarProducto extends AppCompatActivity {

    private Button btnActualizar;
    private ImageButton btnVolver;
    private TextView nombreProducto;
    private TextView precioProducto;
    private TextView marcaProducto;
    private TextView cantidadProducto;
    private TextInputLayout precioNuevo;
    private String precio;
    private AccesoDatos datos = new AccesoDatos();

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_editar_producto);
        btnActualizar = findViewById(R.id.actualizarPoducto);
        btnVolver = findViewById(R.id.btnVolerEditar);
        nombreProducto = findViewById(R.id.textNombreEditarProducto);
        marcaProducto = findViewById(R.id.textMarcaEditarProducto);
        precioProducto = findViewById(R.id.textPrecioActualEditarProducto);
        cantidadProducto = findViewById(R.id.textCantidadEditarProducto);
        precioNuevo = findViewById(R.id.textPrecioNuevoEditarProducto);

        Bundle extras = getIntent().getExtras();
        final Producto producto = (Producto) extras.getSerializable("producto");

        precio = String.valueOf(producto.getPrecio());

        nombreProducto.setText("Nombre: " + producto.getNombre());
        marcaProducto.setText("Marca: " + producto.getMarca());
        precioProducto.setText("Precio: $" + precio);
        cantidadProducto.setText("Cantidad: " + producto.getCantidad() + producto.getMedida() );

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validacion()){
                    double precioEditado = Double.parseDouble(precioNuevo.getEditText().getText().toString().trim());
                    producto.setPrecio(precioEditado);
                    datos.actualizar(producto);
                }
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAdmi = new Intent(EditarProducto.this, EscaneoProducto.class);
                intentAdmi.putExtra("usuario", "usuario");
                intentAdmi.putExtra("password", "password");
                startActivity(intentAdmi);
            }
        });
    }

    private boolean validacion(){

        String precioEditado = precioNuevo.getEditText().getText().toString().trim();

        if(precioEditado.isEmpty()){
            precioNuevo.setError("Debe digitar un nuevo precio");
            return false;
        }

        return true;
    }
}
