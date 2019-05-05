/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.PostDTO;
import co.edu.uniandes.csw.deporte.ejb.PostLogic;
import co.edu.uniandes.csw.deporte.entities.PostEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Juan Camilo Garcia
 */
@Path("post")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class PostResource {
        
        @Inject
        private PostLogic postLogic;

         private final static String EXC = "El recurso /post/";
    
    private final static String EXCE= " no existe.";
        /**
     * Crea un nuevo blog con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param blog {@link blogDTO} - EL blog que se desea guardar.
     * @return JSON {@link blogDTO} - El blog guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     */
        @POST
        public PostDTO createPost(PostDTO pPost)throws BusinessLogicException
        {
           return new PostDTO(postLogic.createPost(pPost.toEntity()));
        }
       
        /**
     * Busca el blog con el id asociado recibido en la URL y lo devuelve.
     *
     * @param blogId Identificador del blog que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link blogDTO} - El blog buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
        @GET
    @Path("{postId: \\d+}")
    public PostDTO getPost(@PathParam("postId") Long postId) {
        PostEntity postEntity = postLogic.getPost(postId);
        if (postEntity == null) {
            throw new WebApplicationException(EXC + postId + EXCE, 404);
        }
        return new PostDTO(postEntity);
        
        
    }
    
    /**
     * Busca y devuelve todos los blogs que existen en la aplicacion.
     *
     * @return JSONArray {@link blogDTO} - Los libros encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */

    @GET
    public List<PostDTO> getPosts() {
        return listEntity2DetailDTO(postLogic.getPosts());
    }
    
    /**
     * Actualiza el blog con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param blogId Identificador del blog que se desea actualizar. Este debe
     * ser una cadena de dígitos.
     * @param blog {@link blogDTO} El blog que se desea guardar.
     * @return JSON {@link blogDTO} - El libro guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el blog a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el blog.
     */
     @PUT
        @Path("{postId: \\d+}")
        public PostDTO updatePost(@PathParam("postId")Long postId, PostDTO pPost)throws BusinessLogicException
        {
            pPost.setId(postId);
            if(postLogic.getPost(postId) == null)
            {
                throw new  WebApplicationException(EXC + postId + EXCE, 404);
            }
            return new PostDTO(postLogic.updatePost(postId, pPost.toEntity()));
        }
        
         /**
     * Borra el blog con el id asociado recibido en la URL.
     *
     * @param blogId Identificador del libro que se desea borrar. Este debe ser
     * una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el blog.
     */
         @DELETE
        @Path("{postId: \\d+}")
        public void deletePost(@PathParam("postId") Long postId)throws BusinessLogicException
        {
            PostEntity entity = postLogic.getPost(postId);
            if(entity == null)
            {
                 throw new WebApplicationException(EXC + postId + EXCE, 404);
            }
            postLogic.deletePost(postId);
          
        }

         public List<PostDTO> listEntity2DetailDTO(List<PostEntity> entityList) {
        List<PostDTO> list = new ArrayList<>();
        for (PostEntity entity : entityList) {
            list.add(new PostDTO(entity));
        }
        return list;
    }
}
