package co.edu.poli.fruitexco.usuario.servlet;

import co.edu.poli.fruitexco.usuario.entities.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@WebServlet(name = "SrvlUsuario", value = "/SrvlUsuario")
public class SrvlUsuario extends HttpServlet {

    public static ArrayList<Usuario> USUARIOS = new ArrayList<>(

            Arrays.asList(
                    new Usuario(1, "andres@gmail.com", "Andres", "02874937283", "3157495246", "Calle 48 # 125 - 4", "ADMINISTRADOR", "ACTIVO"),
                    new Usuario(2, "erikcano@gmail.com", "Erik", "783hd874r8", "3214834154", "Calle 86 # 107 - 2", "CANDIDATO", "ACTIVO"),
                    new Usuario(1, "alejo2@gmail.com", "Alejandro", "936646g4rg", "31284959393", "Calle 26 # 80 - 48", "CANDIDATO", "ACTIVO")
            )

    );

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json");

        this.setAccessControlHeaders(response);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String id = request.getParameter("id");

        if (id != null){
            Usuario usu = searchUsuario(id);
            out.print(gson.toJson(usu));
        }else {
            out.print(gson.toJson(SrvlUsuario.USUARIOS));
        }
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json");

        this.setAccessControlHeaders(response);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        JsonObject body = new JsonParser().parse(this.getParamsFromPost(request)).getAsJsonObject();
        Random rd = new Random();
        int min = 0;
        int max = 1000;

        Usuario usuario = new Usuario(
                (rd.nextInt(max - min) + min),
                body.get("email").getAsString(),
                body.get("userName").getAsString(),
                body.get("password").getAsString(),
                body.get("phone").getAsString(),
                body.get("address").getAsString(),
                body.get("rol").getAsString(),
                body.get("status").getAsString()
        );

        SrvlUsuario.USUARIOS.add(usuario);

        out.print(gson.toJson(usuario));
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json");

        this.setAccessControlHeaders(response);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        JsonObject body = new JsonParser().parse(this.getParamsFromPost(request)).getAsJsonObject();
        Random rd = new Random();
        int min = 0;
        int max = 1000;
        String id = request.getParameter("id");

        if (id != null) {
            Usuario usu = searchUsuario(id);
            usu.setEmail(body.get("email").getAsString());
            usu.setUserName(body.get("userName").getAsString());
            usu.setPassword(body.get("password").getAsString());
            usu.setPhone(body.get("phone").getAsString());
            usu.setAddress(body.get("address").getAsString());
            usu.setRol(body.get("rol").getAsString());
            usu.setStatus(body.get("status").getAsString());
            out.print(gson.toJson(usu));
        }else{
            String error = "No se ha encontrado el objeto.";
            out.print(gson.toJson(error));
        }

        out.flush();

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        int index = searchIndex(id);
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json");

        this.setAccessControlHeaders(response);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        if (index != -1){

            SrvlUsuario.USUARIOS.remove(index);
            String mensaje = "Elemento eliminado con exito.";
            out.print(gson.toJson(mensaje));

        }else{

            String error = "No se ha encontrado el objeto.";
            out.print(gson.toJson(error));

        }

        out.flush();

    }

    private Usuario searchUsuario(String id) {

        for (Usuario usu: SrvlUsuario.USUARIOS) {

            if (usu.getId().equals(Integer.parseInt(id))){
                return usu;
            }

        }
        return null;
    }

    private int searchIndex(String id) {
        int i = 0;

        for (Usuario usu: SrvlUsuario.USUARIOS) {
            if (usu.getId().equals(Integer.parseInt(id))){
                return i;
            }else{
                i ++;
            }
        }
        return -1;
    }

    private String getParamsFromPost(HttpServletRequest request) throws IOException {

        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();

        while (line != null) {
            sb.append(line + "\n");
            line = reader.readLine();
        }

        reader.close();
        String params = sb.toString();
        return params;
    }

    private void setAccessControlHeaders(HttpServletResponse resp) {

        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");

    }
}
