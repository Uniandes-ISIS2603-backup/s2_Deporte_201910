/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.BlogDTO;
import co.edu.uniandes.csw.deporte.dtos.BlogDetailDTO;
import co.edu.uniandes.csw.deporte.ejb.BlogLogic;
import co.edu.uniandes.csw.deporte.entities.BlogEntity;
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
@Path("blog")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class BlogResource {

@Inject
private BlogLogic blogLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.


private static final String EXC = "El recurso /blog/";

    private static final String EXCE = " no existe.";

/**
 * Crea un nuevo blog con la informacion que se recibe en el cuerpo de la
 * petición y se regresa un objeto identico con un id auto-generado por la
 * base de datos.
 *
 * @param blog {@link BlogDTO} - EL blog que se desea guardar.
 * @return JSON {@link BlogDTO} - El blog guardado con el atributo id
 * autogenerado.
 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
 */
@POST
    public BlogDTO createPost(BlogDTO pBlog) throws BusinessLogicException
    {

    return new BlogDTO(blogLogic.createBlog(pBlog.toEntity()));
    }

    /**
 * Busca el blog con el id asociado recibido en la URL y lo devuelve.
 *
 * @param blogId Identificador del blog que se esta buscando. Este debe
 * ser una cadena de dígitos.
 * @return JSON {@link BlogDetailDTO} - El blog buscado
 * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
 * Error de lógica que se genera cuando no se encuentra el libro.
 */

    @GET
@Path("{blogId: \\d+}")
public BlogDetailDTO getBlog(@PathParam("blogId") Long blogId) {
    BlogEntity blogEntity = blogLogic.getBlog(blogId);
    if (blogEntity == null) {
        throw new WebApplicationException(EXC + blogId + EXCE, 404);
    }
   return new BlogDetailDTO(blogEntity);



}

/**
 * Busca y devuelve todos los blogs que existen en la aplicacion.
 *
 * @return JSONArray {@link BlogDetailDTO} - Los libros encontrados en la
 * aplicación. Si no hay ninguno retorna una lista vacía.
 */
@GET
public List<BlogDetailDTO> getBlogs() {
    return listEntity2DetailDTO(blogLogic.getBlogs());

}


/**
 * Actualiza el blog con el id recibido en la URL con la información que se
 * recibe en el cuerpo de la petición.
 *
 * @param blogId Identificador del blog que se desea actualizar. Este debe
 * ser una cadena de dígitos.
 * @param blog {@link BlogDTO} El blog que se desea guardar.
 * @return JSON {@link BookDetailDTO} - El libro guardada.
 * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
 * Error de lógica que se genera cuando no se encuentra el blog a
 * actualizar.
 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
 * Error de lógica que se genera cuando no se puede actualizar el blog.
 */
    @PUT
    @Path("{blogId: \\d+}")
    public BlogDTO updatePost(@PathParam("blogId")Long blogId, BlogDetailDTO pBlog)throws BusinessLogicException
    {
        pBlog.setId(blogId);
        if(blogLogic.getBlog(blogId) == null)
        {
            throw new  WebApplicationException(EXC + blogId + EXCE, 404);
        }
       return new BlogDetailDTO(blogLogic.updateBlog(blogId, pBlog.toEntity()));

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
    @Path("{blogId: \\d+}")
    public void deletePost(@PathParam("blogId") Long blogId)throws BusinessLogicException
    {
        BlogEntity entity = blogLogic.getBlog(blogId);
        if(entity == null)
        {
             throw new WebApplicationException(EXC + blogId + EXCE, 404);
        }
        blogLogic.deleteBlog(blogId);

    }

     public List<BlogDetailDTO> listEntity2DetailDTO(List<BlogEntity> entityList) {
    List<BlogDetailDTO> list = new ArrayList<>();
    for (BlogEntity entity : entityList) {
        list.add(new BlogDetailDTO(entity));
    }
    return list;
}

}
