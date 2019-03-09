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
@Path("post")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class PostResource {
        private static final Logger LOGGER = Logger.getLogger(PostResource.class.getName());
        
        @Inject
        private PostLogic postLogic;

        @POST
        public PostDTO createPost(PostDTO pPost)throws BusinessLogicException
        {
            PostDTO nuevoPostDTO = new PostDTO(postLogic.createPost(pPost.toEntity()));
            return nuevoPostDTO;
        }
       
        
        @GET
    @Path("{postId: \\d+}")
    public PostDTO getPost(@PathParam("postId") Long postId) {
        PostEntity postEntity = postLogic.getPost(postId);
        if (postEntity == null) {
            throw new WebApplicationException("El recurso /post/" + postId + " no existe.", 404);
        }
        PostDTO postDTO = new PostDTO(postEntity);
        
        
        return postDTO;
    }
    @GET
    public List<PostDTO> getPosts() {
        List<PostDTO> listaBooks = listEntity2DetailDTO(postLogic.getPosts());
        return listaBooks;
    }
    
     @PUT
        @Path("{postId: \\d+}")
        public PostDTO updatePost(@PathParam("postId")Long postId, PostDTO pPost)throws BusinessLogicException
        {
            pPost.setId(postId);
            if(postLogic.getPost(postId) == null)
            {
                throw new  WebApplicationException("El recurso /post/" + postId + " no existe.", 404);
            }
            PostDTO detailDTO = new PostDTO(postLogic.updatePost(postId, pPost.toEntity()));
            return detailDTO;
        }
         @DELETE
        @Path("{postId: \\d+}")
        public void deletePost(@PathParam("postId") Long postId)throws BusinessLogicException
        {
            PostEntity entity = postLogic.getPost(postId);
            if(entity == null)
            {
                 throw new WebApplicationException("El recurso /post/" + postId + " no existe.", 404);
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
