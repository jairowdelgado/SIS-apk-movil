package unicauca.sis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;

import java.util.ArrayList;

public class MenuIngresar extends AppCompatActivity {
    public static  final String emailPattern ="[a-zA-Z0-9._-]+@[a-zA-Z]+\\.+[a-z]{2,}";
    private EditText txtLogin;
    private EditText txtPassword;
    private Button btnLogin;
    private TextView txtValidate;
    private boolean isValidEmail= false;
    private ArrayList<User> users;
    private  AccesoDatos accesoDatos ;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ingresar);
        txtLogin = findViewById(R.id.txtUsuario);
        txtPassword  = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        //FirebaseApp.initializeApp(this);
        accesoDatos = new AccesoDatos();
        accesoDatos.listarAdmin();
        accesoDatos.listarUsuarios();



        btnLogin.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick (View v){
                // progressDialog.show();
                    String usuario = txtLogin.getText().toString().trim();
                    String password = txtPassword.getText().toString().trim();
                    boolean bandera=false;
                    if(!usuario.equals("") && !password.equals("")) {

                        users = new ArrayList<>();
                        users = accesoDatos.getUsers();
                        users.addAll(accesoDatos.getAdmins());
                        for (int i = 0; i < users.size(); i++) {
                            if (!users.get(i).equals(null) && users.get(i).getUsuario().equals(usuario)) {
                                if (users.get(i).getContrasenia().equals(password)) {
                                    bandera = true;
                                }
                                i = users.size();
                            }
                        }
                        if (bandera) {
                            Intent intentIngre = new Intent(MenuIngresar.this, MenuInicioAdmin.class);
                            intentIngre.putExtra("Login", usuario);
                            intentIngre.putExtra("password", password);
                            startActivity(intentIngre);
                        } else {
                            Toast t= Toast.makeText(getApplicationContext(), "El usuario o contraseña son incorrectos", Toast.LENGTH_LONG);
                            t.show();
                        }
                    }else {
                        Toast t =Toast.makeText(getApplicationContext(), "Los campos no pueden estar vacios", Toast.LENGTH_LONG);
                        t.show();
                    }

            }

        });

    }
}