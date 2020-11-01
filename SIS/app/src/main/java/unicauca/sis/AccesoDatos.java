package unicauca.sis;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.FirebaseApp;
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
    private ArrayList<User> users;
    private ArrayList<User> admins;

    public AccesoDatos() {
        this.productos = new ArrayList<Producto>();
        this.users = new ArrayList<User>();
        this.admins = new ArrayList<User>();


    }


    public void insertarProducto(Producto producto){
        miReferencia.child("Producto").child(producto.getId()).setValue(producto);
    }

    public void insertarUsuario(User usuario){
        System.out.println(usuario.getUsuario());
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
    public void listarAdmin(){
        miReferencia.child("Administrador").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    User admin = dataSnapshot.getValue(User.class);
                    admins.add(admin);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void listarUsuarios(){
        miReferencia.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);

                    users.add(user);
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

    public ArrayList<User> getUsers() {
        return users;
    }
    public ArrayList<User> getAdmins() {

        return admins;
    }

    public Producto getProducto(String codigo){
        for(Producto producto: productos){
            if(producto.getCodigo().equals(codigo)){
                return producto;
            }
        }
        return null;
    }

    public User getUsuario(String nombreUsuario){
        for(User user : users){
            if(user.getUsuario().equals(nombreUsuario)){
                return user;
            }
        }
        return null;
    }
}
