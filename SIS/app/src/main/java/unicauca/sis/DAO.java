package unicauca.sis;

import androidx.annotation.NonNull;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DAO {

    private FirebaseDatabase bd = FirebaseDatabase.getInstance();
    private DatabaseReference miReferencia =  bd.getReference();
    private ArrayList<Producto> productos;

    public DAO() {
        this.productos = new ArrayList<>();
    }

    public void crearProducto(Producto producto){
        miReferencia.child("Producto").child(producto.getId()).setValue(producto);
    }

    public void producto(String id){
        //System.out.println("\nLO QUE TRAIGO" + miReferencia.child("Producto").child(id));
    }


    public void listarDatos(){

        miReferencia.child("Producto").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Producto producto = dataSnapshot.getValue(Producto.class);
                    productos.add(producto);
                    System.out.println("El tamano de la lista es: " + productos.size());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public Producto getProducto(String id){
        for(Producto producto: productos){
            if(producto.getId().equals(id)){
                return producto;
            }
        }
        return null;
    }

}
