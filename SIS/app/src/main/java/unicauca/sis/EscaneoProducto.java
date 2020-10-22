package unicauca.sis;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class EscaneoProducto extends AppCompatActivity {

    //VARIABLE CON EL ROL

    private ImageButton btnScanner;
    private String codigo;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_escaner);

        btnScanner = findViewById(R.id.botonEscaner);

        btnScanner.setOnClickListener(mOnClickListener);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents() != null){
                codigo = result.getContents();
                System.out.println(" *************************** El resultado del escaner es: " + codigo);
            }else{
                codigo = "error";
                System.out.println(" *************************** El resultado del escaner es: " + codigo);
            }
        }
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.botonEscaner:
                    new IntentIntegrator(EscaneoProducto.this).initiateScan();
                    break;
            }
        }
    };


}
