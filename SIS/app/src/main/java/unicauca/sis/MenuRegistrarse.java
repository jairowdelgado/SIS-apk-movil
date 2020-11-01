package unicauca.sis;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MenuRegistrarse extends AppCompatActivity {

    private EditText txtLogin;
    private EditText txtPass;
    private EditText txtConfirPass;
    private EditText txtNombre;
    private EditText txtApellido;
    private Button btnRegistro;
    private TextView txtValidarPass;
    private ArrayList<User> users;
    private User usu;
    private AccesoDatos acceso;

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
        txtValidarPass = findViewById(R.id.txtValidatePass);
        acceso = new AccesoDatos();
        acceso.listarUsuarios();



        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 String usuario=txtLogin.getText().toString().trim();
                 String pass=txtPass.getText().toString().trim();
                 String conf=txtConfirPass.getText().toString().trim();
                 String nombre=txtNombre.getText().toString().trim();
                 String apellido=txtApellido.getText().toString().trim();
                if(!usuario.equals("") && !nombre.equals("") && !apellido.equals("") && !pass.equals("") && !conf.equals("")){
                    txtValidarPass.setText("");
                        if(pass.equals(conf)){
                            boolean bandera=true;
                            usu = new User((String) usuario,(String) pass,nombre+" "+apellido);

                            users = new ArrayList<>();
                            users = acceso.getUsers();
                            System.out.println(usu.getUsuario()+"  f "+users.get(0).getUsuario());
                            for (int i=0; i<users.size();i++) {
                                if(usu.getUsuario().equals(users.get(i).getUsuario())){
                                    Toast t = Toast.makeText(getApplicationContext(),"El usuario ya se encuentra registrado",Toast.LENGTH_LONG);
                                    bandera=false;
                                    t.show();
                                }

                            }
                            if(bandera) {
                                acceso.insertarUsuario(usu);
                                Intent intent = new Intent(MenuRegistrarse.this,MenuIngresar.class);
                                startActivity(intent);
                            }
                        }else{
                            txtValidarPass.setTextColor(Color.rgb(255, 0, 0));
                            txtValidarPass.setText("Las contraseñas no coinciden");
                        }
                }else{
                    Toast t = Toast.makeText(getApplicationContext(),"los Campos no pueden estar vacios",Toast.LENGTH_LONG);
                    t.show();
                }

            }
        });



    }
    public void validarUsu(User usuarioN){


    }
}
