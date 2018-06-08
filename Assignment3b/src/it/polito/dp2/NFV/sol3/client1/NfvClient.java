package it.polito.dp2.NFV.sol3.client1;


import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;


import it.polito.dp2.NFV.lab3.AllocationException;
import it.polito.dp2.NFV.lab3.DeployedNffg;
import it.polito.dp2.NFV.lab3.NffgDescriptor;
import it.polito.dp2.NFV.lab3.NodeDescriptor;
import it.polito.dp2.NFV.lab3.ServiceException;
import it.polito.dp2.NFV.lab3.UnknownEntityException;



public class NfvClient implements it.polito.dp2.NFV.lab3.NfvClient{
	private Client client;
	private WebTarget target;

	public NfvClient() {
		
		//System.out.println("CIAO NFVCLIENT ");
		client = ClientBuilder.newClient();	    
		target = client.target(getBaseURI());
		
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

	@Override
	public DeployedNffg deployNffg(NffgDescriptor nffg_d) throws AllocationException, ServiceException {

		System.out.println("deployNffg in NfvClient");

		it.polito.dp2.NFV.sol3.client1.DeployedNffg depNffg=new it.polito.dp2.NFV.sol3.client1.DeployedNffg();

		int count=0;
		for(NodeDescriptor nd:nffg_d.getNodes()){
			System.out.println("Host del Node descriptor n°"+count+" :"+nd.getHostName());
			count++;
		}
		
		System.out.println("Numero di nodi nell'Nffg Descriptor: "+nffg_d.getNodes().size());
		
		depNffg.deployNffg(nffg_d);
		
		return depNffg;

	}

	@Override
	public DeployedNffg getDeployedNffg(String name) throws UnknownEntityException, ServiceException {

		System.out.println("getDeployedNffg in NfvClient");
		
		it.polito.dp2.NFV.sol3.client1.DeployedNffg depNffg=new it.polito.dp2.NFV.sol3.client1.DeployedNffg();
		
		return depNffg.getDeployedNffg(name);
	}

}
