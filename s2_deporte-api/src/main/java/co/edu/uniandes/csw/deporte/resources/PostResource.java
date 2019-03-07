/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.deporte.resources;

import co.edu.uniandes.csw.deporte.dtos.PostDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Juan Camilo Garcia
 */
@Path("post")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class PostResource {
        public static final Logger LOGGER = Logger.getLogger(PostResource.class.getName());

        @POST
        public PostDTO createPost(PostDTO pPost)
        {
            return pPost;
        }
        
        @PUT
        public PostDTO modifyPost(PostDTO pPost)
        {
            return pPost;
        }
        
        @DELETE
        public PostDTO deletePost(PostDTO pPost)
        {
            return pPost;
        }
}
