package unicauca.sis;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MenuRegistrarse extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtEmailVeri;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registrar_usu);
    }
}
