package unicauca.sis;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class crearProducto extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_crear_producto);

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);
    }
}
