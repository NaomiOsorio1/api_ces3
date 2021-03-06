package co.edu.poli.fruitexco.usuario.entities;

import co.edu.poli.fruitexco.usuario.entities.enumerator.Estado;
import co.edu.poli.fruitexco.usuario.entities.enumerator.Rol;
import java.util.Calendar;
import java.util.UUID;


public class Usuario {
    private Integer id;
    private String guid;
    private String email;
    private String userName;
    private String password;
    private Calendar expTime;
    private Calendar createdOn;
    private String phone;
    private String address;
    private Rol rol;
    private Estado status;

    public Usuario(Integer id, String email, String userName, String password, String phone, String address, String rol, String status) {

        this.id = id;
        this.guid = UUID.randomUUID().toString();
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.expTime = Calendar.getInstance();
        this.expTime.add(Calendar.MINUTE, 15);
        this.createdOn = Calendar.getInstance();
        this.phone = phone;
        this.address = address;
        this.rol = Rol.valueOf(rol);
        this.status = Estado.valueOf(status);

    }

    public Usuario(String email, String userName, String password, String phone, String address, String rol, String status) {

        this.guid = UUID.randomUUID().toString();
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.expTime = Calendar.getInstance();
        this.expTime.add(Calendar.MINUTE, 15);
        this.createdOn = Calendar.getInstance();
        this.phone = phone;
        this.address = address;
        this.rol = Rol.valueOf(rol.toUpperCase());
        this.status = Estado.valueOf(status.toUpperCase());

    }

    public Usuario() {
        this.guid = UUID.randomUUID().toString();
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }


    public String getGuid() {
        return guid;
    }
    public void setGuid(String guid) {
        this.guid = guid;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public Calendar getExpTime() {
        return expTime;
    }
    public void setExpTime(Calendar expTime) {
        this.expTime = expTime;
    }


    public Calendar getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Calendar createdOn) {
        this.createdOn = createdOn;
    }


    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }


    public Rol getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = Rol.valueOf(rol.toUpperCase());
    }


    public Estado getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = Estado.valueOf(status.toUpperCase());
    }
}
