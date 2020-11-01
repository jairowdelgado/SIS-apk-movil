package unicauca.sis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EditarProducto extends AppCompatActivity {

    private Button btnActualizar;
    private AccesoDatos ad = new AccesoDatos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_editar_producto);
        btnActualizar = findViewById(R.id.actualizarPoducto);

        ad.listarProducto();

        Bundle extras = getIntent().getExtras();
        Producto producto = (Producto) extras.getSerializable("producto");
        System.out.println(producto.getNombre());

        //Producto pr = ad.getProducto(id);
        //System.out.println("EL producto es " + pr);

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
