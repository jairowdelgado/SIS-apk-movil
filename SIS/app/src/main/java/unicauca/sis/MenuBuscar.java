package unicauca.sis;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import unicauca.sis.R;

public class MenuBuscar extends AppCompatActivity {
    private EditText etBuscarPro;
    private ImageButton btnBuscar;
    private Button btnAtras;
    private ArrayList<Producto> listaP;
    private AccesoDatos accesoDatos;
    private RecyclerView rcResultado;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_busqueda);
        etBuscarPro = (EditText) findViewById(R.id.txtBuscarProducto);
        btnBuscar = (ImageButton) findViewById(R.id.btnBusqueda);
        //btnAtras = findViewById(R.id.btnAtrasBuscar);
        rcResultado = findViewById(R.id.rvResulBusqueda);
        accesoDatos=new AccesoDatos();
        accesoDatos.listarProducto();


        btnBuscar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String busqueda=etBuscarPro.getText().toString().trim();
                if(!busqueda.equals("")){

                    Producto pr=new Producto();
                    listaP= new ArrayList<>();
                    listaP=accesoDatos.getProductos();

                    for(int i=0; i<listaP.size();i++){
                        if(listaP.get(i).getNombre().equals(busqueda)){
                            pr=listaP.get(i);
                            i=listaP.size();
                        }
                    }
                    imprimeResul(pr);
                }
            }
        });
    }
    void imprimeResul(Producto pr){

    }

}
