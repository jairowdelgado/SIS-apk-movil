package unicauca.sis.utilidades;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Sqlconexion extends SQLiteOpenHelper {

    public Sqlconexion(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(DAO.DROP_TABLA_PRODUCTO);
        db.execSQL(DAO.CREATE_TABLA_PRODUCTO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DAO.DROP_TABLA_PRODUCTO);
        onCreate(db);
    }
}
