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
import java.util.logging.Logger;
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
    public static final Logger LOGGER = Logger.getLogger(PostResource.class.getName());

    @Inject
    public BlogLogic blogLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    

    @POST
        public BlogDTO createPost(BlogDTO pBlog) throws BusinessLogicException
        {
             
        BlogDTO nuevoBlogDTO = new BlogDTO(blogLogic.createBlog(pBlog.toEntity()));
        return nuevoBlogDTO;
        }
        
        @GET
    @Path("{blogId: \\d+}")
    public BlogDetailDTO getBlog(@PathParam("blogId") Long blogId) {
        BlogEntity blogEntity = blogLogic.getBlog(blogId);
        if (blogEntity == null) {
            throw new WebApplicationException("El recurso /blog/" + blogId + " no existe.", 404);
        }
        BlogDetailDTO blogDetailDTO = new BlogDetailDTO(blogEntity);
        
        
        return blogDetailDTO;
    }
    
    @GET
    public List<BlogDetailDTO> getBlogs() {
        List<BlogDetailDTO> listaBooks = listEntity2DetailDTO(blogLogic.getBlogs());
        return listaBooks;
    }

    

        @PUT
        @Path("{blogId: \\d+}")
        public BlogDTO updatePost(@PathParam("blogId")Long blogId, BlogDetailDTO pBlog)throws BusinessLogicException
        {
            pBlog.setId(blogId);
            if(blogLogic.getBlog(blogId) == null)
            {
                throw new  WebApplicationException("El recurso /blog/" + blogId + " no existe.", 404);
            }
            BlogDetailDTO detailDTO = new BlogDetailDTO(blogLogic.updateBlog(blogId, pBlog.toEntity()));
            return detailDTO;
        }
      

        @DELETE
        @Path("{blogId: \\d+}")
        public void deletePost(@PathParam("blogId") Long blogId)throws BusinessLogicException
        {
            BlogEntity entity = blogLogic.getBlog(blogId);
            if(entity == null)
            {
                 throw new WebApplicationException("El recurso /blog/" + blogId + " no existe.", 404);
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
