package org.acme.getting.started;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.undertow.servlet.handlers.ServletRequestContext;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;


import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("/hello")
public class Servlet {
    ContainerResponseFilter r;

    @GET
    @Produces
    @RequestScoped
    public String hello(){
        System.out.println(ServletRequestContext.requireCurrent().getServletRequest());
        System.out.println(ServletRequestContext.requireCurrent().getServletResponse());
        System.out.println(ServletRequestContext.current().getCurrentServlet().toString());
        System.out.println(HttpServletRequest.BASIC_AUTH);
       System.out.println();
        return "Hello world";
    }
    
    @Produces
    @RequestScoped
    HttpServletResponse response() {
        return (HttpServletResponse) ServletRequestContext.requireCurrent().getServletResponse();
    }

    @GET
    @Path("/context")
    public Response get(@Context HttpHeaders headers){
        String conn=headers.getRequestHeader("Connection").get(0);
        
        for(String header : headers.getRequestHeaders().keySet()){
            System.out.println(header);
        }
        
        return Response.status(200).entity("addUser is called, userAgent : " + conn).build();
        

    }
}