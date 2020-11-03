package unicauca.sis;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import unicauca.sis.utilidades.DAO;
import unicauca.sis.utilidades.Sqlconexion;

public class MenuRecientes extends AppCompatActivity {

    private Sqlconexion sqlconexion;
    private TableLayout tablaLayout;
    private TextView txtCelda;
    private Context contexto;
    private TableRow filaTabla;
    private ArrayList<String> datos;
    private Button btnAtras;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_recientes);
        tablaLayout = findViewById(R.id.tlRecientes);

        this.contexto=getApplicationContext();
        btnAtras=findViewById(R.id.btnAtrasReciente);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuRecientes.this,ScaneoUsuario.class);
                startActivity(intent);
            }
        });
        leerRecientes();
    }

    public void leerRecientes(){

        Bundle  usua=getIntent().getExtras();
        User usuario=new User();

        usuario.setUsuario(usua.getString("usuario"));
        ArrayList<Producto> liPro=new ArrayList<>();
        Producto pros=new Producto();
        sqlconexion = new Sqlconexion(this,"bd_reciente",null,1);
        SQLiteDatabase db = sqlconexion.getReadableDatabase();
        String[] parametros = {usuario.getUsuario()};
        String[] campos = {DAO.CAMPO_NOMBRE,DAO.CAMPO_MARCA,DAO.CAMPO_PRECIO,DAO.CAMPO_MEDIDA,DAO.CAMPO_CANTIDAD,DAO.CAMPO_USUARIO};
        try {
            Cursor cursor = db.query(DAO.TABLA_PRODUCTO,campos,DAO.CAMPO_USUARIO+"=? ",parametros,null,null," ID_TABLA DESC ",null);
            cursor.moveToFirst();
            do {
                System.out.println(cursor.getCount() + " imptime ro " + cursor.getString(2));
                Producto pro = new Producto();
                pro.setNombre(cursor.getString(0));
                pro.setMarca(cursor.getString(1));
                pro.setPrecio(Double.parseDouble(cursor.getString(2)));
                pro.setMedida(cursor.getString(3));
                pro.setCantidad(Double.parseDouble(cursor.getString(4)));
                liPro.add(pro);
            }while(cursor.moveToNext());
            crearDatatable(liPro);



        }catch (Exception ex){
            Toast toast = Toast.makeText(getApplicationContext()," knfl",Toast.LENGTH_LONG);
            toast.show();
        }
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

    private void crearDatatable(ArrayList<Producto> listaProducto){

        if(listaProducto.size()>0) {
            System.out.println("sqqss ");
            this.datos = new ArrayList<>();
            this.datos.add("Nombre : ");
            this.datos.add("Marca : ");
            this.datos.add("Medida : ");
            this.datos.add("Precio : ");
            this.datos.add("Cantidad : ");
            for (final Producto producto : listaProducto) {
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
                txtCelda.setText(String.valueOf(producto.getCantidad()));
                filaTabla.addView(txtCelda, newTableRowParams());
                tablaLayout.addView(filaTabla);

                nuevaFila();
                nuevaCelda();
                txtCelda.setText("");
                txtCelda.setBackgroundColor(Color.rgb(33,150,243));
                filaTabla.addView(txtCelda);
                tablaLayout.addView(filaTabla);

            }
        }else{
            Toast.makeText(getApplicationContext(),"No hay productos recientes ",Toast.LENGTH_LONG).show();
        }
    }
    private TableRow.LayoutParams newTableRowParams(){
        TableRow.LayoutParams parametros= new TableRow.LayoutParams();
        parametros.setMargins(2,2,2,2);
        parametros.weight=1;
        return parametros;
    }

}
