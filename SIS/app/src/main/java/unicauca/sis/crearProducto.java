package unicauca.sis;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class crearProducto extends AppCompatActivity {

    private Button crear;
    private String codigo;
    private TextInputLayout nombre;
    private TextInputLayout precio;
    private TextInputLayout marca;
    private TextInputLayout cantidad;
    private String medidaTomada;
    private Spinner medidas;
    private AccesoDatos datos = new AccesoDatos();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_crear_producto);

        datos.listarProducto();

        crear = findViewById(R.id.btnCrearProducto);
        nombre = findViewById(R.id.inputNombreProducto);
        precio = findViewById(R.id.inputPrecioProducto);
        cantidad = findViewById(R.id.inputCantidadProducto);
        medidas = (Spinner) findViewById(R.id.spinnerMedida);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.combo_medidas,android.R.layout.simple_spinner_item);
        medidas.setAdapter(adapter);

        medidas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                medidaTomada = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Bundle extras = getIntent().getExtras();
        codigo = extras.getString("codigo");



        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto producto = new Producto();
                int id = (datos.getProductos()).size() + 1;
                producto.setId(String.valueOf(id));
                producto.setCodigo(codigo);
                producto.setNombre(nombre.getEditText().getText().toString().trim());
                producto.setPrecio(Double.parseDouble(precio.getEditText().getText().toString().trim()));
                producto.setCantidad(Double.parseDouble(cantidad.getEditText().getText().toString().trim()));
                producto.setMedida(medidaTomada);
                producto.setEstado(true);
            }
        });
    }
}
