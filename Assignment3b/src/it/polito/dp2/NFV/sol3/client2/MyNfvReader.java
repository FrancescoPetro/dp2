package it.polito.dp2.NFV.sol3.client2;

import java.net.URI;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import it.polito.dp2.NFV.ConnectionPerformanceReader;
import it.polito.dp2.NFV.FunctionalType;
import it.polito.dp2.NFV.HostReader;
import it.polito.dp2.NFV.LinkReader;
import it.polito.dp2.NFV.NffgReader;
import it.polito.dp2.NFV.NodeReader;
import it.polito.dp2.NFV.VNFTypeReader;
import it.polito.dp2.NFV.lab3.ServiceException;
import it.polito.dp2.NFV.sol3.client2.generated.*;

public class MyNfvReader implements it.polito.dp2.NFV.NfvReader {

	private javax.ws.rs.client.Client cli;
	private WebTarget target;


	public MyNfvReader() {
		
		//System.out.println("CIAO NFVREADER");

		cli = ClientBuilder.newClient();	  
		target = cli.target(getBaseURI());
		//System.out.println("Client2 URL : " + getBaseURI().toString());		

	}

	private URI getBaseURI() {
		if(System.getProperty("it.polito.dp2.NFV.lab3.URL") == null) {
			if(System.getProperty("it.polito.dp2.NFV.lab3.PORT") == null)
				return UriBuilder.fromUri("http://localhost:8080/NfvDeployer/rest/").build();
			else
				return UriBuilder.fromUri("http://localhost:" + System.getProperty("it.polito.dp2.NFV.lab3.PORT") + "/NfvDeployer/rest/").build();
		}
		return UriBuilder.fromUri(System.getProperty("it.polito.dp2.NFV.lab3.URL")).build();
	}


	private In inService() throws ServiceException
	{
		Response response = target.path("nfv")
				.path("in")
				.path("all")
				.request(MediaType.APPLICATION_XML)
				.get();
		if(response.getStatus() == 201 || response.getStatus() == 200) {	
			In responseIn = response.readEntity(In.class);
			return responseIn;
		}
		else
			throw new ServiceException();
	}

	private Host hostService(String hostName) throws ServiceException
	{
		Response response = target.path("nfv")
				.path("in")
				.path(hostName)
				.request(MediaType.APPLICATION_XML)
				.get();
		if(response.getStatus() == 201 || response.getStatus() == 200) {	
			Host responseHost = response.readEntity(Host.class);
			return responseHost;
		}
		else
			throw new ServiceException();
	}

	private VnfCatalog catalogService() throws ServiceException
	{
		Response response = target.path("nfv")
				.path("catalog")
				.path("all")
				.request(MediaType.APPLICATION_XML)
				.get();
		if(response.getStatus() == 201 || response.getStatus() == 200) {	
			VnfCatalog responseCat = response.readEntity(VnfCatalog.class);
			return responseCat;
		}
		else
			throw new ServiceException();		
	}

	private Nffgs nffgsService() throws ServiceException
	{
		Response response = target.path("nfv")
				.path("nffgs")
				.path("all")
				.request(MediaType.APPLICATION_XML)
				.get();
		if(response.getStatus() == 201 || response.getStatus() == 200) {	
			Nffgs responseNffgs = response.readEntity(Nffgs.class);
			return responseNffgs;
		}
		else
			throw new ServiceException();		
	}

	private Nffg nffgService(String nffgName) throws ServiceException
	{
		Response response = target.path("nfv")
				.path("nffgs")
				.path(nffgName)
				.request(MediaType.APPLICATION_XML)
				.get();
		if(response.getStatus() == 201 || response.getStatus() == 200) {	
			Nffg responseNffg = response.readEntity(Nffg.class);
			return responseNffg;
		}
		else
			throw new ServiceException();		
	}

	private Performances performanceService() throws ServiceException
	{
		Response response = target.path("nfv")
				.path("in")
				.path("performances")
				.path("all")
				.request(MediaType.APPLICATION_XML)
				.get();
		if(response.getStatus() == 201 || response.getStatus() == 200) {	
			return response.readEntity(Performances.class);
		}
		else
			throw new ServiceException();
	}

	private Nodes nodesService() throws ServiceException{
		Response response = target.path("nfv")
				.path("nodes")
				.path("all")
				.request(MediaType.APPLICATION_XML)
				.get();
		if(response.getStatus() == 201 || response.getStatus() == 200) {	
			Nodes responseNodes  = response.readEntity(Nodes.class);
			return responseNodes;
		}
		else
			throw new ServiceException();
	}

	private Node nodeService(String nodeName) throws ServiceException{
		Response response = target.path("nfv")
				.path("nodes")
				.path(nodeName)
				.request(MediaType.APPLICATION_XML)
				.get();
		if(response.getStatus() == 201 || response.getStatus() == 200) {	
			Node responseNode  = response.readEntity(Node.class);
			return responseNode;
		}
		else
			throw new ServiceException();
	}

	private Link linkService(String linkName) throws ServiceException{
		Response response = target.path("nfv")
				.path("nffgs")
				.path("links")
				.path(linkName)
				.request(MediaType.APPLICATION_XML)
				.get();
		if(response.getStatus() == 201 || response.getStatus() == 200) {	
			Link responseLink  = response.readEntity(Link.class);
			return responseLink;
		}
		else
			throw new ServiceException();

	}


	public LinkReader getLinkReader(String linkName) {

		Link link;
		try {
			link = linkService(linkName);

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

					return MyNfvReader.this.getNodeReader(link.getSrcNode());
				}

				@Override
				public int getLatency() {
					return link.getMaxLatency();
				}

				@Override
				public NodeReader getDestinationNode() {
					return MyNfvReader.this.getNodeReader(link.getDstNode());
				}
			};

			return lr;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public NodeReader getNodeReader(String nodeName) {

		try {
			Node node=nodeService(nodeName);

			if(node!=null) {

				NodeReader nr=new NodeReader() {

					@Override
					public String getName() {
						return node.getName();
					}

					@Override
					public NffgReader getNffg() {
						return MyNfvReader.this.getNffg(node.getNffg());
					}

					@Override
					public Set<LinkReader> getLinks() {

						Links links=node.getLinks();

						if(links!=null) {

							Set<LinkReader> links_r=new HashSet<LinkReader>();

							for(Link link:links.getLink()) {
								links_r.add( MyNfvReader.this.getLinkReader(link.getName()));
							}

							return links_r;
						}

						return null;
					}

					@Override
					public HostReader getHost() {

						String hostName=node.getHost();
						if(hostName!=null) 

							return MyNfvReader.this.getHost(hostName);

						return null;
					}

					@Override
					public VNFTypeReader getFuncType() {

						String fType=node.getFunctionalType();
						if(fType!=null) {

							try {
								VnfCatalog vnfCatalog=catalogService();

								if(vnfCatalog!=null) {

									for(Vnf vnf:vnfCatalog.getVnf())
										if(vnf.getName().compareTo(fType)==0) {

											VNFTypeReader vnft_r=new VNFTypeReader() {

												@Override
												public String getName() {
													return fType;
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
													return FunctionalType.fromValue(fType);
												}
											};
											return vnft_r;
										}
								}
							} catch (ServiceException e) {
								e.printStackTrace();
							}
						}

						return null;
					}
				};
				return nr;
			}

		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ConnectionPerformanceReader getConnectionPerformance(HostReader arg0, HostReader arg1) {

		try {
			Performances performances=performanceService();
			if(performances!=null) {
				for(ConnectionPerformance performance:performances.getConnectionPerformance()){
					if(performance.getHost1().compareTo(arg0.getName())==0&&performance.getHost2().compareTo(arg1.getName())==0) {
						ConnectionPerformanceReader performance_r=new ConnectionPerformanceReader() {

							@Override
							public float getThroughput() {
								return performance.getThroughput();
							}

							@Override
							public int getLatency() {
								return performance.getLatency();
							}
						};

						return performance_r;
					}

				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		return null;
	}


	@Override
	public HostReader getHost(String arg0) {

		try {
			Host host=hostService(arg0);

			if(host!=null) {


				HostReader hr=new HostReader() {

					@Override
					public String getName() {
						return host.getName();
					}

					@Override
					public Set<NodeReader> getNodes() {

						try {
							Nodes nodes=nodesService();
							if(nodes!=null) {

								Set<NodeReader> nodes_r=new HashSet<NodeReader>();
								for(Node node:nodes.getNode()) {

									nodes_r.add(MyNfvReader.this.getNodeReader(node.getName()));
								}
								return nodes_r;

							}

						} catch (ServiceException e) {
							e.printStackTrace();
						}


						return null;
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
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		return null;
	}

	@Override
	public Set<HostReader> getHosts() {

		try {
			In in=inService();

			if(in!=null) {
				Set<HostReader> hosts_r=new HashSet<HostReader>();

				for(Host host:in.getHost())
					hosts_r.add(MyNfvReader.this.getHost(host.getName()));
				return hosts_r;
			}

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public NffgReader getNffg(String arg0) {

		try {
			Nffg nffg=nffgService(arg0);

			if(nffg!=null) {
				NffgReader nffg_r=new NffgReader() {

					@Override
					public String getName() {
						return nffg.getName();
					}

					@Override
					public Set<NodeReader> getNodes() {
						Set<NodeReader> nodes_r=new HashSet<NodeReader>();

						for(Node node:nffg.getNodes().getNode()) 

							nodes_r.add(MyNfvReader.this.getNodeReader(node.getName()));		

						return nodes_r;
					}

					@Override
					public NodeReader getNode(String arg0) {
						return MyNfvReader.this.getNodeReader(arg0);
					}

					@Override
					public Calendar getDeployTime() {

						return nffg.getDeployTime().toGregorianCalendar();
					}
				};

				return nffg_r;
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return null;
	}

	@Override
	public Set<NffgReader> getNffgs(Calendar arg0) {

		try {
			Nffgs nffgs=nffgsService();


			if(nffgs!=null) {

				//INSERIRE DEPLOYTIME IN NFFGS
				// IF NFFS.GETDEPLOYTIME=ARG0

				Set<NffgReader> nffgs_r=new HashSet<NffgReader>();

				for(Nffg nffg:nffgs.getNffg()) 

					nffgs_r.add(MyNfvReader.this.getNffg(nffg.getName()));

				return nffgs_r;

			}

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return null;
	}

	@Override
	public Set<VNFTypeReader> getVNFCatalog() {

		try {
			VnfCatalog vnfCatalog=catalogService();

			if(vnfCatalog!=null) {
				Set<VNFTypeReader> vnfts_r=new HashSet<VNFTypeReader>();

				for(Vnf vnf:vnfCatalog.getVnf()) {


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
							return FunctionalType.fromValue(vnf.getName());
						}
					};

					vnfts_r.add(vnft_r);
				}
				return vnfts_r;

			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
