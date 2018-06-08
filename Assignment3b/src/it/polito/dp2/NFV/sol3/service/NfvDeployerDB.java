package it.polito.dp2.NFV.sol3.service;

import java.util.GregorianCalendar;
import java.util.Set;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import it.polito.dp2.NFV.ConnectionPerformanceReader;
import it.polito.dp2.NFV.HostReader;
import it.polito.dp2.NFV.LinkReader;
import it.polito.dp2.NFV.NffgReader;
import it.polito.dp2.NFV.NfvReader;
import it.polito.dp2.NFV.NfvReaderException;
import it.polito.dp2.NFV.NfvReaderFactory;
import it.polito.dp2.NFV.NodeReader;
import it.polito.dp2.NFV.VNFTypeReader;
import it.polito.dp2.NFV.sol3.service.jaxb.Nodes;
import it.polito.dp2.NFV.sol3.service.jaxb.ConnectionPerformance;
import it.polito.dp2.NFV.sol3.service.jaxb.Performances;
import it.polito.dp2.NFV.sol3.service.jaxb.Ft;
import it.polito.dp2.NFV.sol3.service.jaxb.Host;
import it.polito.dp2.NFV.sol3.service.jaxb.HostNodes;
import it.polito.dp2.NFV.sol3.service.jaxb.In;
import it.polito.dp2.NFV.sol3.service.jaxb.Link;
import it.polito.dp2.NFV.sol3.service.jaxb.Links;
import it.polito.dp2.NFV.sol3.service.jaxb.Nffg;
import it.polito.dp2.NFV.sol3.service.jaxb.Nffgs;
import it.polito.dp2.NFV.sol3.service.jaxb.Node;
import it.polito.dp2.NFV.sol3.service.jaxb.Vnf;
import it.polito.dp2.NFV.sol3.service.jaxb.VnfCatalog;

public class NfvDeployerDB {

	private static NfvReader monitor;

	private static Nffgs nffgs;
	private static In in;
	private static VnfCatalog catalog;
	private static boolean deployed=false;

	private static Object lock = new Object();

	private static ReachabilityTester test=new ReachabilityTester();
	
	public static synchronized void init() {
		
		nffgs=new Nffgs();
		in=new In();
		in.setPerformances(new Performances());		
		catalog=new VnfCatalog();

		NfvReaderFactory factory = NfvReaderFactory.newInstance();
		try {
			monitor = factory.newNfvReader();
		} catch (NfvReaderException e) {
			e.printStackTrace();
		}
		deploy();
	}


	public static synchronized void deploy() {

		synchronized (lock) {
			if(deployed)
				return;

			//System.out.println("CIAO NFVDEPLOYERDB");

			for (HostReader hr:monitor.getHosts()) {
				System.out.println(hr.getName());
				Host host=createHost(hr);
				postHost(host);
			}


			for(HostReader hr1:monitor.getHosts()) 
				for(HostReader hr2:monitor.getHosts()) {
					ConnectionPerformanceReader cpr=monitor.getConnectionPerformance(hr1, hr2);
					ConnectionPerformance cp=new ConnectionPerformance();
					cp.setHost1(hr1.getName());
					cp.setHost2(hr2.getName());
					cp.setLatency(cpr.getLatency());
					cp.setThroughput(cpr.getThroughput());
					postPerformance(cp);
				}

			postCatalog(createCatalog(monitor.getVNFCatalog()));

			for(NffgReader nffg_r:monitor.getNffgs(null)) {
				System.out.println(nffg_r.getName());
				if(nffg_r.getName().compareTo("Nffg0")==0) {
					Nffg nffg=createNffg(nffg_r);
					deployNffg(nffg);
				}

			}			

			System.out.println("Numero di nffg: "+nffgs.getNffg().size());
			System.out.println("Numero di host: "+in.getHost().size());
			int numnodi=0;
			for(Nffg nffg:nffgs.getNffg())
				numnodi+=nffg.getNodes().getNode().size();
			System.out.println("Numero di nodi: "+numnodi);

			deployed=true;
			return;

		}

	}
	
	

	/*-------------------------- RESTITUISCE GLI OBJECT PRESENTI DEL DB DEL SERVICE --------------------------*/

	public static synchronized Node getNode(String nodeName) {

		synchronized (lock) {
			if(nodeName!=null) {

				for (Nffg nffg:nffgs.getNffg())
					for(Node node:nffg.getNodes().getNode())
						if(node.getName().compareTo(nodeName)==0)	
							return node;
			}

			return null;	
		}
	}

	public static synchronized Nffg getNffg(String nffgName) {

		synchronized (lock) {
			if(nffgName!=null) {
				for(Nffg nffg:nffgs.getNffg())
					if(nffg.getName().compareTo(nffgName)==0)
						return nffg;
			}
			return null;	
		}
	}

	public static synchronized Host getHost(String hostName) {
		synchronized (lock) {

			if(hostName!=null) {
				for(Host host:in.getHost())
					if(host.getName().compareTo(hostName)==0)
						return host;
			}
			return null;	
		}
	}

	public static synchronized Nffgs getAllNffgs() {
		synchronized (lock) {
			return nffgs;	
		}
	}

	public static synchronized In getIn() {
		synchronized (lock) {
			return in;	
		}
	}

	public static synchronized Nodes getNffgNodes(String nffgName) {
		synchronized (lock) {
			if(nffgName!=null) {

				for(Nffg nffg:nffgs.getNffg())
					if(nffg.getName().compareTo(nffgName)==0)
						return nffg.getNodes();
			}
			return null;	
		}
	}

	public static synchronized Nodes getHostNodes(String hostName) {
		synchronized (lock) {
			if(hostName!=null) {
				Nodes hostNodes=new Nodes();

				for(Host host:in.getHost()) {
					if(host.getName().compareTo(hostName)==0) 
						for(String nodeName:host.getHostNodes().getHostNode()) {
							Node hostNode=getNode(nodeName);
							hostNodes.getNode().add(hostNode);
						}
					return hostNodes;
				}
			}

			return null;	
		}
	}

	public static synchronized Node getHostNode(String nodeName, String hostName) {
		synchronized (lock) {
			if(nodeName!=null||hostName!=null) {
				for(Node node:getHostNodes(hostName).getNode())
					if(node.getName().compareTo(nodeName)==0)
						return node;
			}

			return null;	
		}
	}

	public static synchronized Node getNffgNode(String nodeName, String nffgName) {

		synchronized (lock) {
			if(nodeName!=null||nffgName!=null) {
				for(Node node:getNffg(nffgName).getNodes().getNode())
					if(node.getName().compareTo(nodeName)==0)
						return node;
			}
			return null;	
		}
	}

	public static synchronized ConnectionPerformance getPerformance(String hostName1, String hostName2) {

		synchronized (lock) {
			if(hostName1!=null||hostName2!=null) {
				for(ConnectionPerformance cp:in.getPerformances().getConnectionPerformance())
					if(cp.getHost1().compareTo(hostName1)==0&&cp.getHost2().compareTo(hostName2)==0)
						return cp;
			}
			return null;	
		}

	}

	public static synchronized Performances getPerformances(){

		synchronized (lock) {
			return in.getPerformances();	
		}
	}


	public static synchronized VnfCatalog getCatalog() {
		synchronized (lock) {
			return catalog;	
		}
	}

	public static synchronized Vnf getVnf(String vnfName) {

		synchronized (lock) {
			if(vnfName!=null) 
				for(Vnf vnf:catalog.getVnf()) 
					if (vnf.getName().compareTo(vnfName)==0)
						return vnf;
			return null;	
		}
	}


	public static synchronized Links getNodeLinks(String nodeName) {
		synchronized (lock) {
			if(nodeName!=null)
				return getNode(nodeName).getLinks();
			return null;	
		}
	}

	public static synchronized Links getNffgLinks(String nffgName) {

		synchronized (lock) {
			if(nffgName!=null) {
				Links links=new Links();

				for(Node node:getNffgNodes(nffgName).getNode()) {
					for(Link link:node.getLinks().getLink())
						links.getLink().add(link);
					return links;
				}
			}
			return null;	
		}

	}

	public static synchronized Link getLink(String linkName){
		synchronized (lock) {
			if(linkName!=null){
				for(Nffg nffg:nffgs.getNffg()){
					for(Node node:nffg.getNodes().getNode()){
						if(node.getLinks()!=null)
							for(Link link:node.getLinks().getLink())
								if(link.getName().compareTo(linkName)==0)
									return link;
					}
				}
			}
			return null;
		}
	}


	/*-------------------------- INSERISCE GLI OBJECT NEL DB DEL SERVICE --------------------------*/


	public static synchronized boolean deployNffg(Nffg nffg) {

		synchronized (lock) {
			if(nffg!=null) {		
				System.out.println("Deploy Nffg: "+nffg.getName());
				for(Node n:nffg.getNodes().getNode()) 

					test.loadNode(n);


				postNffg(nffg);

				return true;

			}

			return false;	
		}

	}

	public static synchronized boolean postNode(Node node) {

		synchronized (lock) {
			if(node!=null) {

				getNffg(node.getNffg()).getNodes().getNode().add(node);
				getHost(node.getHost()).getHostNodes().getHostNode().add(node.getName());

				test.loadNode(node);
				return true;
			}

			return false;
		}
	}


	public static synchronized boolean postNffg(Nffg nffg) {

		synchronized (lock) {
			if(nffg!=null) {

				System.out.println("Post nffg: "+nffg.getName());

				nffgs.getNffg().add(nffg);
				return true;
			}
			return false;	
		}
	}

	/*public static synchronized boolean updateNffg(Nffg nffg){

		synchronized (lock) {
			for(Nffg nffgLocal:nffgs.getNffg()){
				System.out.println(nffgLocal.getName());
				if(nffgLocal.getName().compareTo(nffg.getName())==0){
					System.out.println(nffgLocal.getName()+"="+nffg.getName());
					for(Node node:nffg.getNodes().getNode()){

						nffgLocal.getNodes().getNode().add(node);
						test.loadNode(node);
					}
				}
			}
			return false;
		}
	}*/

	public static synchronized boolean postLink(Link link) {

		synchronized (lock) {
			if(link!=null) {

				System.out.println("Link: "+link.getName()+" "+link.getSrcNode()+" "+link.getDstNode());				

				getNode(link.getSrcNode()).getLinks().getLink().add(link);
				getNode(link.getDstNode()).getLinks().getLink().add(link);

				test.loadRelationship(link);

				return true;
			}
			return false;	
		}
	}


	public static synchronized boolean postCatalog(VnfCatalog vnfCatalog) {

		synchronized (lock) {
			if(vnfCatalog!=null) {

				System.out.println("Post catalog");

				for(Vnf vnf:vnfCatalog.getVnf())
					catalog.getVnf().add(vnf);

				return true;
			}
			return false;	
		}
	}

	public static synchronized boolean postVnf(Vnf vnf) {

		synchronized (lock) {
			if(vnf!=null) {
				System.out.println("Post vnf: "+vnf.getName());
				catalog.getVnf().add(vnf);
				return true;
			}
			return false;	
		}
	}
	public static synchronized boolean postHost(Host host) {
		synchronized (lock) {
			if(host!=null) {

				System.out.println("Post host: "+host.getName());
				in.getHost().add(host);
				test.loadHost(host);
				return true;
			}
			return false;	
		}
	}

	public static synchronized boolean postPerformance(ConnectionPerformance connPerformance) {

		synchronized (lock) {
			if(connPerformance!=null) {

				System.out.println("Post performance");
				in.getPerformances().getConnectionPerformance().add(connPerformance);
				System.out.println("Aggiunta performance tra: "+connPerformance.getHost1()+" e "+connPerformance.getHost2());
				return true;
			}
			return false;	
		}
	}


	/*-------------------------- CREA OBJECT DAI CORRISPONDENTI READER --------------------------*/

	public static synchronized Nffg createNffg(NffgReader nffg_r) {

		synchronized (lock) {
			if(nffg_r!=null) {

				Nffg nffg=new Nffg();

				nffg.setName(nffg_r.getName());
				XMLGregorianCalendar deployTimeXC;
				try {
					deployTimeXC = DatatypeFactory.newInstance().newXMLGregorianCalendar((GregorianCalendar)nffg_r.getDeployTime());
					nffg.setDeployTime(deployTimeXC);
				} catch (DatatypeConfigurationException e) {
					e.printStackTrace();
				}

				Nodes nodes=new Nodes();

				for(NodeReader nr:nffg_r.getNodes()) {
					nodes.getNode().add(createNode(nr));
					nffg.setNodes(nodes);
				}

				System.out.println("Creato l'nffg: "+nffg.getName());

				return nffg;
			}
			return null;	
		}
	}

	public static synchronized Host createHost(HostReader hr) {

		synchronized (lock) {

			if(hr!=null) {
				Host host=new Host();
				host.setHostNodes(new HostNodes());
				host.setName(hr.getName());
				host.setAvailableMemory(hr.getAvailableMemory());
				host.setAvailableStorage(hr.getAvailableStorage());
				host.setMaxVNF(hr.getMaxVNFs());

				for(NodeReader nr:hr.getNodes()) 
					host.getHostNodes().getHostNode().add(nr.getName());

				System.out.println("Creato l'host: "+host.getName());
				return host;
			}
			return null;	
		}
	}

	public static synchronized Node createNode(NodeReader nr) {
		synchronized (lock) {
			if(nr!=null) {
				Node n=new Node();

				n.setName(nr.getName());
				n.setHost(nr.getHost().getName());
				n.setNffg(nr.getNffg().getName());

				Links links=new Links();

				for (LinkReader lr:nr.getLinks()) {
					links.getLink().add(createLink(lr));
					n.setLinks(links);
				}

				System.out.println("Creato il nodo: "+n.getName());
				return n;
			}
			return null;	
		}
	}

	public static synchronized Link createLink(LinkReader lr) {

		synchronized (lock) {
			if(lr!=null) {
				Link link=new Link();
				link.setName(lr.getName());
				link.setDstNode(lr.getDestinationNode().getName());
				link.setSrcNode(lr.getSourceNode().getName());
				link.setMaxLatency(lr.getLatency());
				link.setMinThroughput(lr.getThroughput());

				System.out.println("Creato il link: "+link.getName());
				return link;
			}
			return null;	
		}
	}

	public static synchronized Vnf createVnf(VNFTypeReader vnft_r) {
		synchronized (lock) {
			if(vnft_r!=null) {

				Vnf vnf=new Vnf();			

				System.out.println(vnft_r.getFunctionalType().toString().toLowerCase()+" "+vnft_r.getFunctionalType().name().toLowerCase());

				//System.out.println(Ft.fromValue(vnft_r.getFunctionalType().name()));

				vnf.setFunctionalType(Ft.fromValue(vnft_r.getFunctionalType().name().toLowerCase()));
				vnf.setName(vnft_r.getName());
				vnf.setRequiredDiskStorage(vnft_r.getRequiredStorage());
				vnf.setRequiredMemory(vnft_r.getRequiredMemory());

				return vnf;
			}
			return null;	
		}
	}

	public static synchronized VnfCatalog createCatalog(Set<VNFTypeReader> catalog) {
		synchronized (lock) {
			if(catalog!=null) {
				VnfCatalog vnfCatalog=new VnfCatalog();
				for(VNFTypeReader vnft_r:catalog)
					vnfCatalog.getVnf().add(createVnf(vnft_r));
				return vnfCatalog;
			}
			return null;	
		}

	}


}
