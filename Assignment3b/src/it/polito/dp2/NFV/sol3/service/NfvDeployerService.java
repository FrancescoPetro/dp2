package it.polito.dp2.NFV.sol3.service;


import it.polito.dp2.NFV.sol3.service.jaxb.VnfCatalog;
import it.polito.dp2.NFV.sol3.service.jaxb.ConnectionPerformance;
import it.polito.dp2.NFV.sol3.service.jaxb.Host;
import it.polito.dp2.NFV.sol3.service.jaxb.In;
import it.polito.dp2.NFV.sol3.service.jaxb.Link;
import it.polito.dp2.NFV.sol3.service.jaxb.Nffg;
import it.polito.dp2.NFV.sol3.service.jaxb.Nffgs;
import it.polito.dp2.NFV.sol3.service.jaxb.Node;
import it.polito.dp2.NFV.sol3.service.jaxb.Nodes;
import it.polito.dp2.NFV.sol3.service.jaxb.Performances;
import it.polito.dp2.NFV.sol3.service.jaxb.Vnf;


public class NfvDeployerService {

	private Object lock1 = new Object();
	
	private static NfvDeployerService service = new NfvDeployerService();
    
	public static NfvDeployerService getNfvService() {
		return service;
	}

	public NfvDeployerService() {
		init();
	}
	public void init() {
		synchronized (lock1) {

			NfvDeployerDB.init();
		}
	}

	public Nffg getNffg(String nffgName) {

		synchronized (lock1) {
			if(nffgName!=null)
				return NfvDeployerDB.getNffg(nffgName);
			return null;	
		}
	}


	public Nffgs getAllNffgs() {
		synchronized (lock1) {
			return NfvDeployerDB.getAllNffgs();

		}

	}


	public In getIn() {
		synchronized (lock1) {
			return NfvDeployerDB.getIn();

		}
	}

	public Host getHost(String hostName) {
		
		synchronized (lock1) {
			if(hostName!=null)
				return NfvDeployerDB.getHost(hostName);
			return null;	
		}

	}

	public Node getNode(String nodeName) {
		synchronized (lock1) {
			if(nodeName!=null)
				return NfvDeployerDB.getNode(nodeName);
			return null;	
		}

	}
	public Node getNodeFromHostName(String nodeName,String hostName) {
		synchronized (lock1) {
			if(nodeName!=null||hostName!=null)
				return NfvDeployerDB.getHostNode(nodeName, hostName);
			return null;	
		}

	}

	public Node getNodeFromNffgName(String nodeName,String nffgName){
		synchronized (lock1) {
			if(nodeName!=null||nffgName!=null)
				return NfvDeployerDB.getNffgNode(nodeName, nffgName);
			return null;	
		}

	}

	public Nodes getNffgNodes (String nffgName) {
		synchronized (lock1) {
			if(nffgName!=null)
				return NfvDeployerDB.getNffgNodes(nffgName);
			return null;	
		}

	}

	public Nodes getHostNodes(String hostName) {
		synchronized (lock1) {
			if(hostName!=null)
				return NfvDeployerDB.getHostNodes(hostName);
			return null;	
		}

	}

	public VnfCatalog getCatalog() {
		synchronized (lock1) {
			return NfvDeployerDB.getCatalog();
		}
	}

	public Vnf getVnf(String vnfName) {
		synchronized (lock1) {
			if(vnfName!=null)
				return NfvDeployerDB.getVnf(vnfName);
			return null;	
		}

	}

	public Link getLink(String linkName){
		synchronized (lock1) {
			if(linkName!=null)
				return NfvDeployerDB.getLink(linkName);
		}
		return null;
	}

	public Performances getPerformances(){
		synchronized (lock1) {

			return NfvDeployerDB.getPerformances();
		}
	}

	/*public boolean updateNffg(Nffg nffg){
		synchronized (lock1) {
			if(nffg!=null)
				return NfvDeployerDB.updateNffg(nffg);
			return false;
		}
	}*/
	
	public boolean insertNode(Node node) {
		synchronized (lock1) {
			if(node!=null)
				return NfvDeployerDB.postNode(node);
			return false;	
		}

	}

	public boolean deployNffg(Nffg nffg) {
		synchronized (lock1) {
			if(nffg!=null)
				return NfvDeployerDB.postNffg(nffg);
			return false;	
		}
	}

	public boolean insertCatalog(VnfCatalog catalog) {

		synchronized (lock1) {
			if(catalog!=null) {
				return NfvDeployerDB.postCatalog(catalog);
			}
			return false;	
		}

	}

	public boolean insertVnf(Vnf vnf) {

		synchronized (lock1) {
			if(vnf!=null) {
				return NfvDeployerDB.postVnf(vnf);
			}
			return false;	
		}
	}

	public boolean insertLink(Link link) {
		synchronized (lock1) {
			if(link!=null)
				return NfvDeployerDB.postLink(link);
			return false;	
		}

	}

	public boolean insertPerformance(ConnectionPerformance cp){

		synchronized (lock1) {
			if(cp!=null){
				return NfvDeployerDB.postPerformance(cp);
			}

			return false;	
		}
	}

}
