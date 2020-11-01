package unicauca.sis;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TablaBuscar extends AppCompatActivity {
    private TableLayout tablaLayout;
    private Context contexto;
    private  ArrayList<String> datos ;
    private Producto producto;
    private TableRow filaTabla;
    private TextView txtCelda;
    private Button btnCelda;
    private int indexCol;
    private int indexFil;

    public TablaBuscar() {
    }

    public TablaBuscar(TableLayout tablaLayout, Context contexto){
        this.tablaLayout =tablaLayout;
        this.contexto=contexto;
        this.datos = new ArrayList<>();
        this.datos.add("Nombre : ");
        this.datos.add("Marca : ");
        this.datos.add("Medida : ");
        this.datos.add("Precio : ");

    }

    public void addData(Producto pro){
        this.producto=pro;
        crearDatatable();
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



    private void crearDatatable(){
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
        nuevoBoton();
        btnCelda.setText("Editar");
        btnCelda.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
        filaTabla.addView(btnCelda, newTableRowParams());
        nuevoBoton();
        btnCelda.setText("Cambiar Estado");
        filaTabla.addView(btnCelda, newTableRowParams());
        tablaLayout.addView(filaTabla);


    }
    public void backgroundHeader(int color){
        indexCol=0;
        while (indexCol<4){
            txtCelda=getCelda(0,indexCol++);
            txtCelda.setBackgroundColor(color);
            txtCelda.setTextColor(Color.WHITE);
            txtCelda.setTextSize(20);
        }
    }

    private TableRow getRow(int index){
        return (TableRow) tablaLayout.getChildAt(index);
    }
    private  TextView getCelda(int filaCelda, int colCelda){
        filaTabla=getRow(filaCelda);
        return  (TextView) filaTabla.getChildAt(colCelda);
    }
    private  Button getBoton(int filaCelda, int colCelda){
        filaTabla=getRow(filaCelda);
        return  (Button) filaTabla.getChildAt(colCelda);
    }

    private TableRow.LayoutParams newTableRowParams(){
        TableRow.LayoutParams parametros= new TableRow.LayoutParams();
        parametros.setMargins(2,2,2,2);
        parametros.weight=1;
        return parametros;
    }



}
