/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.PostDTO;
import co.edu.uniandes.csw.deporte.ejb.ClientePostsLogic;
import co.edu.uniandes.csw.deporte.ejb.PostLogic;
import co.edu.uniandes.csw.deporte.entities.PostEntity;
import co.edu.uniandes.csw.deporte.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClientePostsResource {
     private static final Logger LOGGER = Logger.getLogger(ClientePostsResource.class.getName());

    @Inject
    private ClientePostsLogic editorialBooksLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private PostLogic bookLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    /**
     * Guarda un libro dentro de una editorial con la informacion que recibe el
     * la URL. Se devuelve el libro que se guarda en la editorial.
     *
     * @param clienteId Identificador de la editorial que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param postId Identificador del libro que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link BookDTO} - El libro guardado en la editorial.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{postId: \\d+}")
    public PostDTO addPost(@PathParam("clienteId") Long clienteId, @PathParam("postId") Long postId) {
        LOGGER.log(Level.INFO, "EditorialBooksResource addBook: input: editorialsID: {0} , booksId: {1}", new Object[]{clienteId, postId});
        if (bookLogic.getPost(postId) == null) {
            throw new WebApplicationException("El recurso /post/" + postId + " no existe.", 404);
        }
        PostDTO bookDTO = new PostDTO(editorialBooksLogic.addPost(postId, clienteId));
        LOGGER.log(Level.INFO, "EditorialBooksResource addBook: output: {0}", bookDTO);
        return bookDTO;
    }
     /**
     * Busca y devuelve todos los libros que existen en la editorial.
     *
     * @param clienteId Identificador de la editorial que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link BookDetailDTO} - Los libros encontrados en la
     * editorial. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<PostDTO> getBooks(@PathParam("clienteId") Long clienteId) {
        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: input: {0}", clienteId);
        List<PostDTO> listaDetailDTOs = booksListEntity2DTO(editorialBooksLogic.getPosts(clienteId));
        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }
    /**
     * Busca el libro con el id asociado dentro de la editorial con id asociado.
     *
     * @param clienteId Identificador de la editorial que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param postId Identificador del libro que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link BookDetailDTO} - El libro buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro en la
     * editorial.
     */
    @GET
    @Path("{postId: \\d+}")
    public PostDTO getBook(@PathParam("clienteId") Long clienteId, @PathParam("postId") Long postId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EditorialBooksResource getBook: input: editorialsID: {0} , booksId: {1}", new Object[]{clienteId, postId});
        if (bookLogic.getPost(postId) == null) {
            throw new WebApplicationException("El recurso /editorials/" + clienteId + "/books/" + postId + " no existe.", 404);
        }
        PostDTO bookDetailDTO = new PostDTO(editorialBooksLogic.getPost(clienteId, postId));
        LOGGER.log(Level.INFO, "EditorialBooksResource getBook: output: {0}", bookDetailDTO);
        return bookDetailDTO;
    }
     /**
     * Remplaza las instancias de Book asociadas a una instancia de Editorial
     *
     * @param clienteId Identificador de la editorial que se esta
     * remplazando. Este debe ser una cadena de dígitos.
     * @param posts JSONArray {@link BookDTO} El arreglo de libros nuevo para la
     * editorial.
     * @return JSON {@link BookDTO} - El arreglo de libros guardado en la
     * editorial.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @PUT
    public List<PostDTO> replacePosts(@PathParam("clienteId") Long clienteId, List<PostDTO> posts) {
        LOGGER.log(Level.INFO, "EditortalBooksResource replaceBooks: input: editorialsId: {0} , books: {1}", new Object[]{clienteId, posts});
        for (PostDTO book : posts) {
            if (bookLogic.getPost(book.getId()) == null) {
                throw new WebApplicationException("El recurso /books/" + book.getId() + " no existe.", 404);
            }
        }
        List<PostDTO> listaDetailDTOs = booksListEntity2DTO(editorialBooksLogic.replacePosts(clienteId, booksListDTO2Entity(posts)));
        LOGGER.log(Level.INFO, "EditorialBooksResource replaceBooks: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }
/**
     * Convierte una lista de BookEntity a una lista de BookDetailDTO.
     *
     * @param entityList Lista de BookEntity a convertir.
     * @return Lista de BookDTO convertida.
     */
    private List<PostDTO> booksListEntity2DTO(List<PostEntity> entityList) {
        List<PostDTO> list = new ArrayList();
        for (PostEntity entity : entityList) {
            list.add(new PostDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de BookDetailDTO a una lista de BookEntity.
     *
     * @param dtos Lista de BookDetailDTO a convertir.
     * @return Lista de BookEntity convertida.
     */
    private List<PostEntity> booksListDTO2Entity(List<PostDTO> dtos) {
        List<PostEntity> list = new ArrayList<>();
        for (PostDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
