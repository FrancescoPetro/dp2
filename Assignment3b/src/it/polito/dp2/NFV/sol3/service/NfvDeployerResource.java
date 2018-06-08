package it.polito.dp2.NFV.sol3.service;


import javax.ws.rs.Consumes;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.polito.dp2.NFV.sol3.service.jaxb.Node;
import it.polito.dp2.NFV.sol3.service.jaxb.Nodes;
import it.polito.dp2.NFV.sol3.service.jaxb.Performances;
import it.polito.dp2.NFV.sol3.service.jaxb.VnfCatalog;
import it.polito.dp2.NFV.sol3.service.jaxb.Vnf;
import it.polito.dp2.NFV.sol3.service.jaxb.Host;
import it.polito.dp2.NFV.sol3.service.jaxb.In;
import it.polito.dp2.NFV.sol3.service.jaxb.Link;
import it.polito.dp2.NFV.sol3.service.jaxb.Nffg;
import it.polito.dp2.NFV.sol3.service.jaxb.Nffgs;
import it.polito.dp2.NFV.sol3.service.jaxb.ConnectionPerformance;

@Path("nfv")
public class NfvDeployerResource {
	private static NfvDeployerService service=NfvDeployerService.getNfvService();
	
	public NfvDeployerResource() {

		//System.out.println("CIAO NFVDEPLOYERESOURCE");
		//service.init();

	}

	@GET 
	@Path("nffgs/all")
	@ApiOperation(	value = "get all the Nffgs", notes = "xml and json formats")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response getAllNffgs() {

		
		Nffgs nffgs=service.getAllNffgs();
		System.out.println("Get all nffgs: "+nffgs.getNffg().size());
		
		if(nffgs!=null)

			return Response.status(200).entity(nffgs).type(MediaType.APPLICATION_XML).build();

		throw new NotFoundException();
	}

	@GET 
	@Path("in/all")
	@ApiOperation(	value = "get all the Hosts", notes = "xml and json formats")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response getIn() {

			
		In in=service.getIn();
		System.out.println("Get in: "+in.getHost().size());

		if(in!=null)

			return Response.status(200).entity(in).type(MediaType.APPLICATION_XML).build();

		throw new NotFoundException();
	}

	@GET
	@Path("in/{name}")
	@ApiOperation(	value = "get one Host", notes = "xml and json formats")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response getHost( @PathParam("name") String hostName) {
		
		Host host=service.getHost(hostName);
		System.out.println("Get host: "+hostName);


		if(host!=null)

			return Response.status(200).entity(host).type(MediaType.APPLICATION_XML).build();
		throw new NotFoundException();
	}

	@GET
	@Path("nodes/{name}")
	@ApiOperation(	value = "get one Node", notes = "xml and json formats")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response getNode( @PathParam("name") String nodeName) {

		Node node=service.getNode(nodeName);
		System.out.println("Get node: "+nodeName);


		if(node!=null)

			return Response.status(200).entity(node).type(MediaType.APPLICATION_XML).build();
		throw new NotFoundException();
	}

	@GET
	@Path("nffgs/{name}/nodes/all")
	@ApiOperation(	value = "get the Nodes in the Nffg", notes = "xml and json formats")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response getNffgNodes( @PathParam("name") String nffgName) {

		System.out.println("Get nffg nodes");
		
		Nodes nffgNodes=service.getHostNodes(nffgName);

		if(nffgNodes!=null)

			return Response.status(200).entity(nffgNodes).type(MediaType.APPLICATION_XML).build();
		throw new NotFoundException();
	}

	@GET
	@Path("catalog/all")
	@ApiOperation(	value = "get the Vnf catalog", notes = "xml and json formats")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response getVnfCatalog() {
		
		System.out.println("Get vnf catalog");

		VnfCatalog catalog=service.getCatalog();

		if(catalog!=null)

			return Response.status(200).entity(catalog).type(MediaType.APPLICATION_XML).build();
		throw new NotFoundException();
	}

	@GET
	@Path("catalog/name/{name}")
	@ApiOperation(	value = "get the Vnf in the Vnf catalog", notes = "xml and json formats")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response getVnf(@PathParam("name") String vnfName) {
		
		System.out.println("Get vnf");

		Vnf vnf=service.getVnf(vnfName);

		if(vnf!=null)

			return Response.status(200).entity(vnf).type(MediaType.APPLICATION_XML).build();
		throw new NotFoundException();
	}

	@GET
	@Path("in/performances/all")
	@ApiOperation(	value = "get performances", notes = "xml and json formats")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response getPerformances() {

		
		Performances performances=service.getPerformances();
		System.out.println("Get performances: "+performances.getConnectionPerformance());

		
		if(performances!=null)
			return Response.status(200).entity(performances).type(MediaType.APPLICATION_XML).build();

		throw new NotFoundException();
	}

	@GET
	@Path("nffgs/{name}")
	@ApiOperation(	value = "get one NF-FG", notes = "xml and json formats")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response getNffg( @PathParam("name") String nffgName) {
		
		System.out.println("Get nffg");

		Nffg nffg=service.getNffg(nffgName);

		if(nffg!=null)

			return Response.status(200).entity(nffg).type(MediaType.APPLICATION_XML).build();
		throw new NotFoundException();

	}

	@POST
	@Path("nffgs/nodes")
	@ApiOperation(	value = "update a negotiate object", notes = "json and xml formats"
			)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 403, message = "Forbidden because negotiation failed"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})

	public Response insertNode( Node node, @Context UriInfo uriInfo) {

		
		System.out.println("Insert node");

		Nffg nffg = service.getNffg(node.getNffg());
		Host host=service.getHost(node.getHost());
		if (nffg == null|| node==null||host==null)
			throw new NotFoundException();


		if (!service.insertNode(node))
			throw new ForbiddenException("Forbidden because negotiation failed");	

		return Response.status(200).entity(node).type(MediaType.APPLICATION_XML).build();

	}

	@POST
	@Path("nffgs/links")
	@ApiOperation(	value = "update a negotiate object", notes = "json and xml formats"
			)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 403, message = "Forbidden because negotiation failed"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response insertLink( Link link, @Context UriInfo uriInfo) {
		
		System.out.println("Insert link");


		if (!service.insertLink(link))
			throw new ForbiddenException("Forbidden because negotiation failed");	

		return Response.status(200).entity(link).type(MediaType.APPLICATION_XML).build();

	}

	@GET
	@Path("nffgs/links/{name}")
	@ApiOperation(	value = "get one Host", notes = "xml and json formats")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response getLink( @PathParam("name") String linkName) {

		
		Link link=service.getLink(linkName);
		System.out.println("Get link: "+link.getName());


		if(link!=null)

			return Response.status(200).entity(link).type(MediaType.APPLICATION_XML).build();
		throw new NotFoundException();
	}

	@POST
	@Path("nffgs")
	@ApiOperation(	value = "insert one NF-FG", notes = "xml and json formats")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response insertNffg(Nffg nffg,@Context UriInfo uriInfo) {

		System.out.println("Insert Nffg: "+nffg.getName());

		if(!service.deployNffg(nffg))
			throw new ForbiddenException("Forbidden because negotiation failed");	

		return Response.status(200).entity(nffg).type(MediaType.APPLICATION_XML).build();

	}

	@POST
	@Path("catalog")
	@ApiOperation(	value = "get all the NF-FGs", notes = "xml and json formats")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response insertVnf(Vnf vnf, @Context UriInfo uriInfo){
		
		System.out.println("Insert vnf");


		if(vnf==null) 
			throw new NotFoundException();

		if(!service.insertVnf(vnf))
			throw new ForbiddenException("Forbidden because negotiation failed");	

		return Response.status(200).entity(vnf).type(MediaType.APPLICATION_XML).build();
	}

	@POST
	@Path("in/performances")
	@ApiOperation(	value = "get all the NF-FGs", notes = "xml and json formats")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response insertPerformance(ConnectionPerformance cp, @Context UriInfo uriInfo){
		
		System.out.println("Insert performance");


		if(cp==null) 
			throw new NotFoundException();

		if(!service.insertPerformance(cp))
			throw new ForbiddenException("Forbidden because negotiation failed");	

		return Response.status(200).entity(cp).type(MediaType.APPLICATION_XML).build();
	}



}
