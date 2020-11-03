package unicauca.sis;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import unicauca.sis.utilidades.DAO;
import unicauca.sis.utilidades.Sqlconexion;

public class MenuRecientes extends AppCompatActivity {

    private Sqlconexion sqlconexion;
    private Button bntRecientes;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_busqueda);
        leerRecientes();
    }

    public Producto leerRecientes(){

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
            Cursor cursor = db.query(DAO.TABLA_PRODUCTO,campos,DAO.CAMPO_USUARIO+"=? ",parametros,null,null,null,null);
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


            for (int j=0; j<liPro.size();j++){
                System.out.println("imptime pro "+liPro.get(j).getPrecio());
            }


        }catch (Exception ex){
            Toast toast = Toast.makeText(getApplicationContext()," knfl",Toast.LENGTH_LONG);
            toast.show();
        }
        return pros;
    }

}
