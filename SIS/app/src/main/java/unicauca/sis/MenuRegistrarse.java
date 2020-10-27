package unicauca.sis;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MenuRegistrarse extends AppCompatActivity {

    private EditText txtLogin;
    private EditText txtPass;
    private EditText txtConfirPass;
    private EditText txtNombre;
    private EditText txtApellido;
    private Button btnRegistro;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registrar_usu);
        txtLogin = findViewById(R.id.txtUsuario);
        txtPass = findViewById(R.id.txtContraseniaReg);
        txtConfirPass = findViewById(R.id.txtContraseniaRegConf);
        txtNombre = findViewById(R.id.txtNombreRegistro);
        txtApellido = findViewById(R.id.txtApellidoRegistro);
        btnRegistro = findViewById(R.id.btnRegistrarUsu);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario=txtLogin.toString().trim();
                String pass=txtLogin.toString().trim();
                String confpass=txtLogin.toString().trim();
                String nombre=txtLogin.toString().trim();
                String apellido=txtLogin.toString().trim();
            }
        });



    }
    public boolean validarUsu(String login){
        Boolean bandera = false;

        return bandera;
    }
}
