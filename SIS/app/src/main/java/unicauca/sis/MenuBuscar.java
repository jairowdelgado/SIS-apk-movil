package unicauca.sis;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MenuBuscar extends AppCompatActivity {
    private EditText etBuscarPro;
    private ImageButton btnBuscar;
    private Button btnAtras;
    private TextView txtCelda;
    private Context contexto;
    private Button btnCelda;
    private ArrayList<Producto> listaP;
    private final AccesoDatos accesoDatos=new AccesoDatos();
    private TableLayout tablaLayout;
    private ArrayList<String> datos;
    private TableRow filaTabla;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_busqueda);
        etBuscarPro = (EditText) findViewById(R.id.txtBuscarProducto);
        btnBuscar = (ImageButton) findViewById(R.id.btnBusqueda);
        //btnAtras = findViewById(R.id.btnAtrasBuscar);
        tablaLayout = (TableLayout) findViewById(R.id.tlResulBusqueda);
        this.contexto=getApplicationContext();


        accesoDatos.listarProducto();


        btnBuscar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String busqueda=etBuscarPro.getText().toString().trim();
                boolean ban=false;
                if(!busqueda.equals("")){
                    int count = tablaLayout.getChildCount();
                    for (int i = 0; i < count; i++) {
                        View child = tablaLayout.getChildAt(i);
                        if (child instanceof TableRow)
                            ((TableRow)child).removeAllViews();
                    }

                    Producto pr=new Producto();
                    listaP= new ArrayList<>();
                    listaP=accesoDatos.getProductos();

                    for(int i=0; i<listaP.size();i++){
                        System.out.println(" "+listaP.get(i).toString());
                        if(listaP.get(i).getNombre().equals(busqueda)){
                            pr=listaP.get(i);
                            i=listaP.size();
                            ban=true;
                        }
                    }
                    if(ban)
                        crearDatatable(pr);
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(),"Producto no encontrado ",Toast.LENGTH_LONG);
                        toast.show();
                    }

                }else{
                    Toast toast= Toast.makeText(getApplicationContext(),"Debe ingresar algo en la busqueda",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    private void nuevaFila(){
        filaTabla=new TableRow(contexto);
    }
    private void nuevaCelda(){
        txtCelda=new TextView(contexto);
        txtCelda.setGravity(Gravity.CENTER);
        txtCelda.setTextSize(18);
        txtCelda.setBackgroundColor(Color.WHITE);
        txtCelda.setTextColor(Color.BLACK);
        txtCelda.setPadding(10,2,10,10);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            txtCelda.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        }
    }
    private void nuevoBoton(){
        btnCelda=new Button(contexto);
        btnCelda.setGravity(Gravity.CENTER);
        btnCelda.setTextSize(18);
        btnCelda.setBackgroundColor(Color.WHITE);
        btnCelda.setTextColor(Color.rgb(42,42,198));
    }



    private void crearDatatable(Producto producto){
        final Producto pro = producto;
        this.datos = new ArrayList<>();
        this.datos.add("Nombre : ");
        this.datos.add("Marca : ");
        this.datos.add("Medida : ");
        this.datos.add("Precio : ");
        this.datos.add("Estado : ");

        nuevaFila();
        nuevaCelda();
        txtCelda.setText(datos.get(0));
        filaTabla.addView(txtCelda, newTableRowParams());
        nuevaCelda();
        txtCelda.setText(producto.getNombre());
        filaTabla.addView(txtCelda, newTableRowParams());
        tablaLayout.addView(filaTabla);

        nuevaFila();
        nuevaCelda();
        txtCelda.setText(datos.get(1));
        filaTabla.addView(txtCelda, newTableRowParams());
        nuevaCelda();
        txtCelda.setText(producto.getMarca());
        filaTabla.addView(txtCelda, newTableRowParams());
        tablaLayout.addView(filaTabla);

        nuevaFila();
        nuevaCelda();
        txtCelda.setText(datos.get(2));
        filaTabla.addView(txtCelda, newTableRowParams());
        nuevaCelda();
        txtCelda.setText(producto.getMedida());
        filaTabla.addView(txtCelda, newTableRowParams());
        tablaLayout.addView(filaTabla);

        nuevaFila();
        nuevaCelda();
        txtCelda.setText(datos.get(3));
        filaTabla.addView(txtCelda, newTableRowParams());
        nuevaCelda();
        txtCelda.setText(String.valueOf(producto.getPrecio()));
        filaTabla.addView(txtCelda, newTableRowParams());
        tablaLayout.addView(filaTabla);

        nuevaFila();
        nuevaCelda();
        txtCelda.setText(datos.get(4));
        filaTabla.addView(txtCelda, newTableRowParams());
        nuevaCelda();
        boolean estado = producto.isEstado();
        String estad="";
        if(estado){
            estad ="En inventario";
        }else{
            estad = "Descontinuado";
        }
        txtCelda.setText(estad);
        filaTabla.addView(txtCelda, newTableRowParams());
        tablaLayout.addView(filaTabla);

        nuevaFila();
        nuevoBoton();
        btnCelda.setText("Editar");
        btnCelda.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuBuscar.this,EditarProducto.class);
                intent.putExtra("productoId",pro.getId());
                startActivity(intent);
            }
        });
        filaTabla.addView(btnCelda, newTableRowParams());
        nuevoBoton();
        btnCelda.setText("Cambiar Estado");
        btnCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Producto aux = pro;
            boolean bandera = true;

            if(aux.isEstado())
                bandera=false;
            aux.setEstado(bandera);

            accesoDatos.actualizar(aux);

            }
        });
        filaTabla.addView(btnCelda, newTableRowParams());
        tablaLayout.addView(filaTabla);


    }
    private TableRow.LayoutParams newTableRowParams(){
        TableRow.LayoutParams parametros= new TableRow.LayoutParams();
        parametros.setMargins(2,2,2,2);
        parametros.weight=1;
        return parametros;
    }


}
