package unicauca.sis.utilidades;

public class DAO {
    public static final String TABLA_PRODUCTO = "productos";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_MARCA = "marca";
    public static final String CAMPO_PRECIO = "precio";
    public static final String CAMPO_CANTIDAD = "cantidad";
    public static final String CAMPO_MEDIDA = "medida";
    public static final String CAMPO_CODIGO = "codigo";
    public static final String CAMPO_ESTADO = "estado";

    public static final String CAMPO_USUARIO = "usuario";


    public static final String CREATE_TABLA_PRODUCTO =
            "CREATE TABLE "+TABLA_PRODUCTO+ " ( ID_TABLA INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_CODIGO+" TEXT, "+CAMPO_NOMBRE+" TEXT, "+CAMPO_MARCA+" TEXT, "+CAMPO_CANTIDAD+" TEXT, "+CAMPO_PRECIO+" TEXT, "+CAMPO_MEDIDA+" TEXT, "+CAMPO_ESTADO+" TEXT, "+CAMPO_USUARIO+" TEXT "+")";


    public static final String DROP_TABLA_PRODUCTO= " DROP TABLE IF EXISTS "+TABLA_PRODUCTO;
}
