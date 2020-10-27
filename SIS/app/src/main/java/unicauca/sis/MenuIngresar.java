package unicauca.sis;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class MenuIngresar extends AppCompatActivity {
    public static  final String emailPattern ="[a-zA-Z0-9._-]+@[a-zA-Z]+\\.+[a-z]{2,}";
    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnLogin;
    private TextView txtValidate;
    private boolean isValidEmail= false;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ingresar);
        txtEmail = findViewById(R.id.txtUsuario);
        txtPassword  = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick (View v){
                // progressDialog.show();


                //validacion email
                txtEmail.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        //validate
                        txtValidate.setText("");
                        String email = txtEmail.getText().toString().trim();
                        isValidEmail = (email.matches(emailPattern) && s.length() > 0);
                        if (!isValidEmail) {
                            txtValidate.setTextColor(Color.rgb(255, 0, 0));
                            txtValidate.setText("Formato de email incorrecto");
                        }
                    }
                });
                login();

            }
            private void login () {
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                Intent intentIngre = new Intent(MenuIngresar.this,MenuInicioAdmin.class);
                intentIngre.putExtra("Email",email);
                intentIngre.putExtra("password",password);
                startActivity(intentIngre);
            }
        });

    }
}