package unicauca.sis;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import unicauca.sis.utilidades.DAO;
import unicauca.sis.utilidades.Sqlconexion;

public class LeerBD extends AppCompatActivity {
    private Producto producto;
    private User usuario;

    private Sqlconexion sqlconexion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public LeerBD(){

    }


    public LeerBD( User usuario) {

        this.usuario = usuario;
    }
    public Producto leerRecientes(){

        Producto pro= new Producto();
        sqlconexion = new Sqlconexion(this,"bd_reciente",null,1);
        SQLiteDatabase db = sqlconexion.getReadableDatabase();
        String[] parametros = {usuario.getUsuario()};
        String[] campos = {DAO.CAMPO_NOMBRE,DAO.CAMPO_MARCA,DAO.CAMPO_PRECIO,DAO.CAMPO_MEDIDA,DAO.CAMPO_CANTIDAD,DAO.CAMPO_USUARIO};
        try {
            Cursor cursor = db.query(DAO.TABLA_PRODUCTO,campos,DAO.CAMPO_CODIGO+"?",parametros,null,usuario.getUsuario(),null,null);
            cursor.moveToFirst();

            pro.setNombre(cursor.getString(0));
            pro.setMarca(cursor.getString(1));
            pro.setPrecio(Double.parseDouble(cursor.getString(2)));
            pro.setMedida(cursor.getString(3));
            pro.setCantidad(Double.parseDouble(cursor.getString(4)));

          System.out.println(pro.getNombre()+"  "+pro.getPrecio());


        }catch (Exception ex){
            Toast toast = Toast.makeText(getApplicationContext()," knfl",Toast.LENGTH_LONG);
            toast.show();
        }
        return pro;
    }
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
}
