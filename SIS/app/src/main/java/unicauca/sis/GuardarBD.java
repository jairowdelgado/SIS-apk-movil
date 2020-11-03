package unicauca.sis;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

import unicauca.sis.utilidades.DAO;
import unicauca.sis.utilidades.Sqlconexion;

public class GuardarBD extends AppCompatActivity {
    private Producto producto;
    private User usuario;

    public GuardarBD(){

    }
    public GuardarBD(Producto producto,User usuario) {
        this.producto = producto;
        this.usuario = usuario;
    }

    public void registrarRecientes(){
        Sqlconexion sqlconexion= new Sqlconexion(getApplicationContext(),"bd_recientes",null,1);
        SQLiteDatabase db = sqlconexion.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(DAO.CAMPO_CODIGO,producto.getCodigo());
        values.put(DAO.CAMPO_NOMBRE,producto.getNombre());
        values.put(DAO.CAMPO_CANTIDAD,producto.getCantidad());
        values.put(DAO.CAMPO_PRECIO,producto.getPrecio());
        values.put(DAO.CAMPO_MARCA,producto.getMarca());
        values.put(DAO.CAMPO_MEDIDA,producto.getMedida());
        values.put(DAO.CAMPO_ESTADO,producto.isEstado());

        values.put(DAO.CAMPO_USUARIO,usuario.getUsuario());

        Long idre =db.insert(DAO.TABLA_PRODUCTO,DAO.CAMPO_CODIGO,values);
        db.close();
    }
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
