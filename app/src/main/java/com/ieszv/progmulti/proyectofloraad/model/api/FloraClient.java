package com.ieszv.progmulti.proyectofloraad.model.api;
import com.ieszv.progmulti.proyectofloraad.model.entity.CreateResponse;
import com.ieszv.progmulti.proyectofloraad.model.entity.Flora;
import com.ieszv.progmulti.proyectofloraad.model.entity.Imagen;
import com.ieszv.progmulti.proyectofloraad.model.entity.RowsResponse;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface FloraClient {
    //Obtener Flora por su ID
    @GET("api/flora/{id}")
    Call<Flora> getFlora(@Path("id") long id);
    //Sacar Flora desde la ruta de flora
    @GET("api/flora")
    Call<ArrayList<Flora>> getFlora();

    //Sacar Imagen desde la ruta de imagen
    @GET("api/imagen")
    Call<ArrayList<Imagen>> getImagen();

    //Crear flora desde la ruta
    @POST("api/flora")
    Call<CreateResponse> createFlora(@Body Flora flora);
    //Editar flora ruta
    @PUT("api/flora/{id}")
    Call<RowsResponse> editFlora(@Path("id") long id, @Body Flora flora);
    //Borrar flora ruta
    @DELETE("api/flora/{id}")
    Call<RowsResponse> deleteFlora(@Path("id") long id);
    //Borrar imagen ruta
    @DELETE("api/imagen/{id}")
    Call<RowsResponse> deleteImagen(@Path("id") long id);
    //Subir Imagen
    @Multipart
    @POST("api/imagen/subir")
    Call<Long> subirImagen(@Part MultipartBody.Part file, @Part("idflora") long idFlora, @Part("descripcion") String descripcion);
}