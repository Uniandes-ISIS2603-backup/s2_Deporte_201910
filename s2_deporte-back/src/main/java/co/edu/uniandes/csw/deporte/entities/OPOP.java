/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.entities;

/**
 *
 * @author estudiante
 */
public class OPOP {
        private static final Logger LOGGER = Logger.getLogger(CampeonatoClienteResource.class.getName());
        
        @Inject
        private CampeonatoClienteLogic campeonatoClienteLogic;
        
        @Inject
        private CampeonatoLogic campeonatoLogic;
        
         /**
     * Asocia un libro existente con un autor existente
     *
     * @param clienteId El ID del autor al cual se le va a asociar el libro
     * @param campeonatoId El ID del libro que se asocia
     * @return JSON {@link BookDetailDTO} - El libro asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{campeonatoId: \\d+}")
    public CampeonatoDTO addCampeonato(@PathParam("clienteId") Long clienteId, @PathParam("campeonatoId") Long campeonatoId) {
        LOGGER.log(Level.INFO, "CameponatoClienteResource addCampeonato: input: clienteId {0} , campeonatoId {1}", new Object[]{clienteId, campeonatoId});
        if (campeonatoLogic.getCampeonato(campeonatoId) == null) {
            throw new WebApplicationException("El recurso /campeonato/" + campeonatoId + " no existe.", 404);
        }
        CampeonatoDTO detailDTO = new CampeonatoDTO(campeonatoClienteLogic.addCampeonato(clienteId, campeonatoId));
        LOGGER.log(Level.INFO, "AuthorBooksResource addBook: output: {0}", detailDTO);
        return detailDTO;
    }

     /**
     * Busca y devuelve todos los libros que existen en un autor.
     *
     * @param clienteId El ID del autor del cual se buscan los libros
     * @return JSONArray {@link BookDetailDTO} - Los libros encontrados en el
     * autor. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<CampeonatoDTO> getCampeonato(@PathParam("ClienteId") Long clienteId) {
        LOGGER.log(Level.INFO, "CampeonatoClienteResource getCampeonato: input: {0}", clienteId);
        List<CampeonatoDTO> lista = booksListEntity2DTO(campeonatoClienteLogic.getCampeonato(clienteId));
        LOGGER.log(Level.INFO, "CampeonatoClienteResource getCampeonato: output: {0}", lista);
        return lista;
    }
    
     /**
     * Busca y devuelve el libro con el ID recibido en la URL, relativo a un
     * autor.
     *
     * @param clienteId El ID del autor del cual se busca el libro
     * @param campeonatoId El ID del libro que se busca
     * @return {@link BookDetailDTO} - El libro encontrado en el autor.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * si el libro no está asociado al autor
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @GET
    @Path("{campeonatoId: \\d+}")
    public CampeonatoDTO getCampeonato(@PathParam("ClienteId") Long clienteId, @PathParam("CampeonatoId") Long campeonatoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CampeonatoClienteResource getCampeonato: input: authorsId {0} , booksId {1}", new Object[]{clienteId, campeonatoId});
        if (bookLogic.getBook(campeonatoId) == null) {
            throw new WebApplicationException("El recurso /campeonato/" + campeonatoId + " no existe.", 404);
        }
        CampeonatoDTO detailDTO = new CampeonatoDTO(campeonatoClienteLogic.getCampeonato(clienteId, campeonatoId));
        LOGGER.log(Level.INFO, "CampeonatoClienteResource getCampeonato: output: {0}", detailDTO);
        return detailDTO;
    }
         /**
     * Actualiza la lista de libros de un autor con la lista que se recibe en el
     * cuerpo
     *
     * @param authorsId El ID del autor al cual se le va a asociar el libro
     * @param books JSONArray {@link BookDetailDTO} - La lista de libros que se
     * desea guardar.
     * @return JSONArray {@link BookDetailDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @PUT
    public List<CampeonatoDTO> replaceCampeonato(@PathParam("clienteId") Long authorsId, List<CampeonatoDTO> books) {
        LOGGER.log(Level.INFO, "AuthorBooksResource replaceBooks: input: authorsId {0} , books {1}", new Object[]{authorsId, books});
        for (BookDetailDTO book : books) {
            if (bookLogic.getBook(book.getId()) == null) {
                throw new WebApplicationException("El recurso /books/" + book.getId() + " no existe.", 404);
            }
        }
        List<BookDetailDTO> lista = booksListEntity2DTO(authorBookLogic.replaceBooks(authorsId, booksListDTO2Entity(books)));
        LOGGER.log(Level.INFO, "AuthorBooksResource replaceBooks: output: {0}", lista);
        return lista;
    }
    }
        

