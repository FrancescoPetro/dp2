package it.polito.dp2.NFV.sol3.client1;

import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import it.polito.dp2.NFV.FunctionalType;
import it.polito.dp2.NFV.HostReader;
import it.polito.dp2.NFV.LinkReader;
import it.polito.dp2.NFV.NffgReader;
import it.polito.dp2.NFV.NodeReader;
import it.polito.dp2.NFV.VNFTypeReader;
import it.polito.dp2.NFV.lab3.AllocationException;
import it.polito.dp2.NFV.lab3.LinkAlreadyPresentException;
import it.polito.dp2.NFV.lab3.LinkDescriptor;
import it.polito.dp2.NFV.lab3.NffgDescriptor;
import it.polito.dp2.NFV.lab3.NoNodeException;
import it.polito.dp2.NFV.lab3.NodeDescriptor;
import it.polito.dp2.NFV.lab3.ServiceException;
import it.polito.dp2.NFV.sol3.client1.generated.Host;
import it.polito.dp2.NFV.sol3.client1.generated.In;
import it.polito.dp2.NFV.sol3.client1.generated.Link;
import it.polito.dp2.NFV.sol3.client1.generated.Links;
import it.polito.dp2.NFV.sol3.client1.generated.Nffg;
import it.polito.dp2.NFV.sol3.client1.generated.Nffgs;
import it.polito.dp2.NFV.sol3.client1.generated.Node;
import it.polito.dp2.NFV.sol3.client1.generated.Nodes;
import it.polito.dp2.NFV.sol3.client1.generated.Performances;
import it.polito.dp2.NFV.sol3.client1.generated.Vnf;
import it.polito.dp2.NFV.sol3.client1.generated.VnfCatalog;

public class DeployedNffg implements it.polito.dp2.NFV.lab3.DeployedNffg{

	private static Integer nodeNum=0;
	private static Integer nffgNum=0;
	private static Integer linkNum=0;
	private static Nffgs nffgs;

	private static Nffg nffg;
	private static In in;

	private Map<NodeDescriptor,String> nodeDescriptorMap;

	private Client client;
	private WebTarget target;

	private URI getBaseURI() {
		if(System.getProperty("it.polito.dp2.NFV.lab3.URL") == null) {
			if(System.getProperty("it.polito.dp2.NFV.lab3.PORT") == null)
				return UriBuilder.fromUri("http://localhost:8080/NfvDeployer/rest/").build();
			else
				return UriBuilder.fromUri("http://localhost:" + System.getProperty("it.polito.dp2.NFV.lab3.PORT") + "/NfvDeployer/rest/").build();
		}
		return UriBuilder.fromUri(System.getProperty("it.polito.dp2.NFV.lab3.URL")).build();
	}

	private static DeployedNffg depNffg = new DeployedNffg();

	public DeployedNffg getDeployedNffg() {
		System.out.println("CIAO DEPLOYEDNFFG");
		return depNffg;
	}

	public DeployedNffg getDeployedNffg(String nffgName){
		try {
			nffg=getNffg(nffgName);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return depNffg;
	}


	public DeployedNffg() {

		//System.out.println("CIAO DEPLOYEDNFFG");

		this.client = ClientBuilder.newClient();	    
		this.target = client.target(getBaseURI());

	}

	public Nffg getNffg(String nffgName) throws ServiceException {

		System.out.println("getNffg in DeployedNffg");

		if(nffgName!=null) {
			Nffg nffg=new Nffg();
			nffg.setName(nffgName);
			nffg.setNodes(new Nodes());
			nffg.setDeployTime(null);
			Response response = target.path("nfv")
					.path("nffgs")
					.path(nffgName)
					.request(MediaType.APPLICATION_XML)
					.get();	

			if(response.getStatus() == 200) {	
				Nffg responseNffg = response.readEntity(Nffg.class);
				return responseNffg;
			}
			else
				throw new ServiceException();
		}
		return null;

	}

	public Nffgs getNffgs() throws ServiceException {

		System.out.println("getNffgs in DeployedNffg");

		Response response = target.path("nfv")
				.path("nffgs")
				.path("all")
				.request(MediaType.APPLICATION_XML)
				.get();	

		if(response.getStatus() == 200) {	
			Nffgs responseNffgs = response.readEntity(Nffgs.class);
			return responseNffgs;
		}

		else
			throw new ServiceException();

	}

	public In getIn() throws ServiceException {
		Response response = target.path("nfv")
				.path("in")
				.path("all")
				.request(MediaType.APPLICATION_XML)
				.get();
		if(response.getStatus() == 200) {	
			In in = response.readEntity(In.class);
			return in;
		}
		else
			throw new ServiceException();

	}

	public Performances getPerformances() throws ServiceException {
		Response response = target.path("nfv")
				.path("performances")
				.path("all")
				.request(MediaType.APPLICATION_XML)
				.get();
		if(response.getStatus() == 200) {	
			Performances performances = response.readEntity(Performances.class);
			return performances;
		}
		else
			throw new ServiceException();

	}

	public VnfCatalog getCatalog() throws ServiceException {
		Response response = target.path("nfv")
				.path("catalog")
				.path("all")
				.request(MediaType.APPLICATION_XML)
				.get();
		if(response.getStatus() == 200) {	
			VnfCatalog catalog = response.readEntity(VnfCatalog.class);
			return catalog;
		}
		else
			throw new ServiceException();
	}

	public Nodes getNodes() throws ServiceException {
		Response response = target.path("nfv")
				.path("nodes")
				.path("all")
				.request(MediaType.APPLICATION_XML)
				.get();
		if(response.getStatus() == 200) {	
			Nodes nodes = response.readEntity(Nodes.class);
			return nodes;
		}
		else
			throw new ServiceException();
	}

	/*	public boolean updateNffg(Nffg nffg) throws ServiceException, AllocationException{

		if(nffg!=null)
			for(Node node:nffg.getNodes().getNode()){
				insertNewNode(node);
				return true;
			}
		return false;

	}*/

	public boolean insertNewNode(Node n) throws ServiceException, AllocationException{

		System.out.println("Nodo da inserire :"+" "+n.getName()+" "+n.getFunctionalType()+" "+n.getHost()+" "+n.getNffg());

		Node nodeResp=new Node();

		nodeResp.setName(n.getName());
		nodeResp.setLinks(n.getLinks());
		nodeResp.setHost(n.getHost());
		nodeResp.setNffg(n.getNffg());
		nodeResp.setFunctionalType(n.getFunctionalType());

		Response nodeResponse = target.path("nfv")
				.path("nffgs")
				.path("nodes")
				.request(MediaType.APPLICATION_XML)
				.post(Entity.entity(nodeResp,MediaType.APPLICATION_XML));

		if(nodeResponse.getStatus() == 201 || nodeResponse.getStatus() == 200) {	
			nodeResp = nodeResponse.readEntity(Node.class);
			System.out.println("Risposta dal server per il nodo postato: "+nodeResp.getName()+" "+nodeResp.getFunctionalType()+" "+nodeResp.getHost()+" "+nodeResp.getNffg());
			nffgs=getNffgs();
			in=getIn();
			return true;

		}

		else if(nodeResponse.getStatus() == 500) {	

			throw new ServiceException();
		}
		else
		{
			System.out.println(nodeResponse.getStatus()+" : "+nodeResponse.getStatusInfo());
			throw new AllocationException();
		}

		/*for(Host host:in.getHost()) {		
			if(host.getName().compareTo(nodeResp.getHost())==0) {					
				host.getHostNodes().getHostNode().add(nodeResp.getName());		

			}
		}

		nffg.getNodes().getNode().add(nodeResp);*/

	}

	public boolean insertLink(Link l) throws ServiceException, AllocationException{
		if(l!=null){

			System.out.println("Link da inserire: "+" "+l.getName()+" "+l.getSrcNode()+" "+l.getDstNode());

			Response linkResponse = target.path("nfv")
					.path("nffgs")
					.path("links")
					.request(MediaType.APPLICATION_XML)
					.post(Entity.entity(l,MediaType.APPLICATION_XML));

			if(linkResponse.getStatus() == 201 || linkResponse.getStatus() == 200) {	
				Link linkResp = linkResponse.readEntity(Link.class);
				System.out.println("Risposta dal server per il link inserito: "+linkResp.getName()+" "+linkResp.getSrcNode()+" "+linkResp.getDstNode());
				nffgs=getNffgs();
				in=getIn();
				return true;

			}

			else if(linkResponse.getStatus() == 500) {	

				throw new ServiceException();
			}
			else
			{
				System.out.println(linkResponse.getStatus()+" : "+linkResponse.getStatusInfo());
				throw new AllocationException();
			}

		}
		return false;
	}

	public void deployNffg(NffgDescriptor nffg_d) throws ServiceException, AllocationException {

		System.out.println("deployNffg in DeployedNffg");

		nodeDescriptorMap=new ConcurrentHashMap<NodeDescriptor,String>();

		in=getIn();
		nffgs=getNffgs();

		nffg=new Nffg();
		Nodes nodes=new Nodes();
		for(Node node:nodes.getNode())
			node.setLinks(new Links());
		nffg.setNodes(nodes);

		String lastname="";
		for(Nffg localNffg:nffgs.getNffg())
			lastname=localNffg.getName().substring(4);

		if(lastname.compareTo(nffgNum.toString())==0)
			nffgNum++;

		lastname="Nffg"+nffgNum;

		System.out.println("Numero di nffg: "+nffgs.getNffg().size());
		System.out.println(lastname);

		nffg.setName(lastname);

		Response response = target.path("nfv")
				.path("nffgs")
				.request(MediaType.APPLICATION_XML)
				.post(Entity.entity(nffg,MediaType.APPLICATION_XML));

		if(response.getStatus() == 201 || response.getStatus() == 200) {	
			Nffg nffgResponse = response.readEntity(Nffg.class);
			System.out.println("Riposta dal server per l'NFFG postato: "+nffgResponse.getName());

		}

		else if(response.getStatus() == 500) {	
			System.out.println(response.getStatus());
			System.out.println(response.getStatusInfo());
			throw new ServiceException();
		}
		else
		{
			throw new AllocationException();
		}


		nffgs=getNffgs();


		for(NodeDescriptor node_d:nffg_d.getNodes()){

			Node node=new Node();
			node.setLinks(new Links());
			String nodeName=node_d.getFuncType().getName()+nodeNum.toString()+node_d.getHostName();

			node.setName(nodeName);
			node.setHost(node_d.getHostName());
			node.setFunctionalType(node_d.getFuncType().getName());
			node.setNffg(nffg.getName());

			//	System.out.println("Nodo da inserire: "+node.getName()+" "+node.getHost()+" "+node.getNffg()+" "+node.getFunctionalType()+" "+node.getLinks());

			insertNewNode(node);



			nodeDescriptorMap.put(node_d, nodeName);
			System.out.println(nodeDescriptorMap.get(node_d));
			nodeNum++;

		}

		for(NodeDescriptor node_d:nffg_d.getNodes()){
			for(LinkDescriptor link_d:node_d.getLinks()){

				System.out.println(nodeDescriptorMap.get(link_d.getSourceNode())+" "+nodeDescriptorMap.get(link_d.getDestinationNode())+" "+link_d.getThroughput()+" "+link_d.getLatency());

				Link link=new Link();

				String linkName="Link"+linkNum;
				link.setName(linkName);
				link.setMaxLatency(link_d.getLatency());
				link.setMinThroughput(link_d.getThroughput());
				link.setSrcNode(nodeDescriptorMap.get(link_d.getSourceNode()));
				link.setDstNode(nodeDescriptorMap.get(link_d.getDestinationNode()));

				for(Node node:nffg.getNodes().getNode())
					if(node.getName().compareTo(link.getSrcNode())==0||node.getName().compareTo(link.getDstNode())==0)
						node.getLinks().getLink().add(link);

				System.out.println("Link da inserire: "+link.getName()+" "+link.getSrcNode()+" "+link.getDstNode()+" "+link.getMaxLatency()+" "+link.getMinThroughput());

				insertLink(link);
				linkNum++;

			}
		}

		Date date = new Date();
		GregorianCalendar calendar=new GregorianCalendar();
		calendar.setTime(date);
		XMLGregorianCalendar deployTime;
		try {
			deployTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
			nffg.setDeployTime(deployTime);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//updateNffg(nffg);

		nffgs.getNffg().add(nffg);

		System.out.println("NFFG "+nffg.getName()+" postato nella lista di NFFG");

		nffgNum++;

	}

	@Override
	public NodeReader addNode(VNFTypeReader type, String hostName) throws AllocationException, ServiceException {

		Node node=new Node();

		String nodeName=type.getName()+nodeNum+hostName;
		node.setName(nodeName);
		node.setNffg(nffg.getName());
		node.setLinks(new Links());
		node.setHost(hostName);
		node.setFunctionalType(type.getName().toLowerCase());

		insertNewNode(node);
		nffg.getNodes().getNode().add(node);
		//updateNffg(nffg);
		nodeNum++;
		return getNodeReader(nodeName);
	}

	@Override
	public LinkReader addLink(NodeReader source, NodeReader dest, boolean overwrite)
			throws NoNodeException, LinkAlreadyPresentException, ServiceException {

		String linkName="Link"+linkNum;

		if (overwrite) {

			System.out.println(nffg.getName());
			nffg=getNffg(nffg.getName());
			System.out.println(nffg.getNodes().getNode().size());
			for(Node node:nffg.getNodes().getNode())
				if(node.getLinks()!=null) {
					for(Link link:node.getLinks().getLink())
						if(link.getSrcNode().compareTo(source.getName())==0&&link.getDstNode().compareTo(dest.getName())==0){

							link.setName(linkName);
							link.setMinThroughput(Float.parseFloat("0"));
							link.setMaxLatency(0);	

							try {
								insertLink(link);
							} catch (AllocationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
				}


		}

		else{
			System.out.println(nffg.getName());
			nffg=getNffg(nffg.getName());
			System.out.println(nffg.getNodes().getNode().size());
			for(Node node:nffg.getNodes().getNode())
				if(node.getLinks()!=null) {

					for(Link link:node.getLinks().getLink())
						if(link.getSrcNode().compareTo(source.getName())==0&&link.getDstNode().compareTo(dest.getName())==0)
							throw new LinkAlreadyPresentException();
				}

			Link link=new Link();
			link.setName(linkName);
			link.setDstNode(dest.getName());
			link.setSrcNode(source.getName());
			link.setMaxLatency(0);
			link.setMinThroughput(Float.parseFloat("0"));

			try {
				insertLink(link);
			} catch (AllocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
		linkNum++;
		return getLinkReader(linkName);


	}

	@Override
	public NffgReader getReader() throws ServiceException {

		return getNffgReader(nffg.getName());
	}


	public HostReader getHostReader(String hostName){
		if(hostName!=null){

			for(Host host:in.getHost())
				if(host.getName().compareTo(hostName)==0){

					HostReader hr=new HostReader() {

						@Override
						public String getName() {
							return host.getName();
						}

						@Override
						public Set<NodeReader> getNodes() {

							Set<NodeReader> nodes=new HashSet<NodeReader>();
							for(String nodeName:host.getHostNodes().getHostNode())
								nodes.add(getNodeReader(nodeName));

							return nodes;
						}

						@Override
						public int getMaxVNFs() {
							return host.getMaxVNF();
						}

						@Override
						public int getAvailableStorage() {
							return host.getAvailableStorage();
						}

						@Override
						public int getAvailableMemory() {
							return host.getAvailableMemory();
						}
					};
					return hr;
				}
		}
		return null;
	}

	public NodeReader getNodeReader(String nodeName){
		if(nodeName!=null){

			for(Nffg nffg:nffgs.getNffg())
				for(Node node:nffg.getNodes().getNode())
					if(node.getName().compareTo(nodeName)==0){

						NodeReader nr=new NodeReader() {

							@Override
							public String getName() {
								return node.getName();
							}

							@Override
							public NffgReader getNffg() {
								return getNffgReader(node.getName());
							}

							@Override
							public Set<LinkReader> getLinks() {
								Set<LinkReader> links=new HashSet<LinkReader>();
								for(Link link:node.getLinks().getLink())
									links.add(getLinkReader(link.getName()));

								return links;
							}

							@Override
							public HostReader getHost() {
								return getHostReader(node.getHost());
							}

							@Override
							public VNFTypeReader getFuncType() {


								try {

									for(Vnf vnf:getCatalog().getVnf())
										if(vnf.getName().compareTo(node.getFunctionalType())==0){


											VNFTypeReader vnft_r=new VNFTypeReader() {

												@Override
												public String getName() {
													return vnf.getName();
												}

												@Override
												public int getRequiredStorage() {
													return vnf.getRequiredDiskStorage();
												}

												@Override
												public int getRequiredMemory() {
													return vnf.getRequiredMemory();
												}

												@Override
												public FunctionalType getFunctionalType() {
													return FunctionalType.fromValue(vnf.getName().toLowerCase());
												}
											};
											return vnft_r;
										}

								} catch (ServiceException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								return null;
							}
						};
						return nr;
					}
		}
		return null;
	}

	public LinkReader getLinkReader(String linkName){
		if(linkName!=null){
			for(Nffg nffg:nffgs.getNffg())
				for(Node node:nffg.getNodes().getNode())
					if(node.getLinks()!=null)
						for(Link link:node.getLinks().getLink())
							if(link.getName().compareTo(linkName)==0){
								LinkReader lr=new LinkReader() {

									@Override
									public String getName() {
										return link.getName();
									}

									@Override
									public float getThroughput() {
										return link.getMinThroughput();
									}

									@Override
									public NodeReader getSourceNode() {
										return getNodeReader(link.getSrcNode());
									}

									@Override
									public int getLatency() {
										return link.getMaxLatency();
									}

									@Override
									public NodeReader getDestinationNode() {
										return getNodeReader(link.getDstNode());
									}
								};
								return lr;
							}
		}
		return null;
	}

	public NffgReader getNffgReader(String nffgName){
		if(nffgName!=null){
			for(Nffg nffg:nffgs.getNffg())
				if(nffg.getName().compareTo(nffgName)==0){
					NffgReader nffg_r=new NffgReader() {

						@Override
						public String getName() {
							return nffg.getName();
						}

						@Override
						public Set<NodeReader> getNodes() {
							Set<NodeReader> nodes=new HashSet<NodeReader>();

							for(Node node:nffg.getNodes().getNode())
								nodes.add(getNodeReader(node.getName()));

							return nodes;
						}

						@Override
						public NodeReader getNode(String arg0) {
							return getNodeReader(arg0);
						}

						@Override
						public Calendar getDeployTime() {
							return nffg.getDeployTime().toGregorianCalendar();
						}
					};
					return nffg_r;
				}
		}
		return null;
	}

}
