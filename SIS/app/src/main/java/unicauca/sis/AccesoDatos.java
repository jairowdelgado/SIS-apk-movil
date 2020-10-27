package unicauca.sis;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AccesoDatos {

    private FirebaseDatabase bd = FirebaseDatabase.getInstance();
    private DatabaseReference miReferencia =  bd.getReference();
    private ArrayList<Producto> productos;
    private ArrayList<Usuario> usuarios;

    public AccesoDatos() {
        this.productos = new ArrayList<Producto>();
        this.usuarios = new ArrayList<Usuario>();
    }

    public void insertarProducto(Producto producto){
        miReferencia.child("Producto").child(producto.getId()).setValue(producto);
    }

    public void insertarUsuario(Usuario usuario){
        miReferencia.child("Usuario").child(usuario.getUsuario()).setValue(usuario);
    }

    public void listarProducto(){
        miReferencia.child("Producto").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Producto producto = dataSnapshot.getValue(Producto.class);
                    productos.add(producto);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void listarUsarios(){
        miReferencia.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Usuario usuario = dataSnapshot.getValue(Usuario.class);
                    usuarios.add(usuario);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void actualizar(Producto producto){
        miReferencia.child("Producto").child(producto.getId()).setValue(producto);
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public Producto getProducto(String codigo){
        for(Producto producto: productos){
            if(producto.getCodigo().equals(codigo)){
                return producto;
            }
        }
        return null;
    }

    public Usuario getUsuario(String nombreUsuario){
        for(Usuario usuario: usuarios){
            if(usuario.getUsuario().equals(nombreUsuario)){
                return usuario;
            }
        }
        return null;
    }
}
