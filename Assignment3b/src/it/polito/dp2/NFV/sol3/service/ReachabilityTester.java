package it.polito.dp2.NFV.sol3.service;

import com.sun.jersey.api.client.ClientResponse;


import it.polito.dp2.NFV.sol3.service.generated.Localhost_Neo4JSimpleXMLWebapi;
import it.polito.dp2.NFV.sol3.service.generated.Nodes;
import it.polito.dp2.NFV.sol3.service.generated.Node;
import it.polito.dp2.NFV.sol3.service.generated.Labels; 
import it.polito.dp2.NFV.sol3.service.generated.ObjectFactory;
import it.polito.dp2.NFV.sol3.service.generated.Properties;
import it.polito.dp2.NFV.sol3.service.generated.Property;
import it.polito.dp2.NFV.sol3.service.generated.Relationship;
import it.polito.dp2.NFV.sol3.service.generated.Relationships;


public class ReachabilityTester {
	private static com.sun.jersey.api.client.Client cli;
	private static ObjectFactory obj;
	private static Nodes nodes;
	private static Nodes hostNodes;
	private static Relationships rels;
	private static Relationships hostRels;

	public ReachabilityTester() {
		cli=Localhost_Neo4JSimpleXMLWebapi.createClient();
		obj=new ObjectFactory();
		nodes=obj.createNodes();
		hostNodes=obj.createNodes();
		rels=obj.createRelationships();
		hostRels=obj.createRelationships();
	}


	public void loadNode(it.polito.dp2.NFV.sol3.service.jaxb.Node n) {

		System.out.println("loadNode di Reachability Tester");

		Node node=obj.createNode();
		node.setId(n.getName());

		System.out.println(n.getName());

		Properties props=obj.createProperties();

		Property prop=obj.createProperty();
		prop.setName("name");

		Nodes.Node nodesNode=obj.createNodesNode();

		prop.setName("name");
		prop.setValue(n.getName());
		props.getProperty().add(prop);

		// Set Label of the node
		Labels labels=new Labels();
		labels.getLabel().add("Node");


		// Set the node
		node.setProperties(props);
		nodesNode.setProperties(props);
		nodesNode.setLabels(labels);

		ClientResponse respNode=Localhost_Neo4JSimpleXMLWebapi.data(cli,Localhost_Neo4JSimpleXMLWebapi.BASE_URI).node().postXml(node, ClientResponse.class);
		System.out.println(respNode.getStatus());
		Node nodeResp=respNode.getEntity(Node.class);
		String nodeId=nodeResp.getId();
		//print("Nodi postati: "+ ++i +"\n"+nodeId);


		Localhost_Neo4JSimpleXMLWebapi.data(cli,Localhost_Neo4JSimpleXMLWebapi.BASE_URI).nodeNodeidLabels(nodeId).postXml(labels, ClientResponse.class);
		//print("Label postate per il nodo: "+ nodeId);



		nodesNode.setId(nodeId);
		nodes.getNode().add(nodesNode);

		//return nodeResp;

		if(n.getLinks()!=null) {
			System.out.println(n.getLinks().getLink().size());

			for(it.polito.dp2.NFV.sol3.service.jaxb.Link l:n.getLinks().getLink()) {

				for(Nodes.Node srcNode:nodes.getNode()) {
					for(Property srcProp:srcNode.getProperties().getProperty()) {

						if(l.getSrcNode().compareTo(srcProp.getValue())==0) {

							for(Nodes.Node dstNode:nodes.getNode()) {
								for(Property dstProp:dstNode.getProperties().getProperty()) {
									if(l.getDstNode().compareTo(dstProp.getValue())==0) {

										Relationships.Relationship relsRel=new Relationships.Relationship();
										relsRel.setType("ForwardsTo");
										relsRel.setDstNode(dstNode.getId());
										relsRel.setSrcNode(srcNode.getId());		
										rels.getRelationship().add(relsRel);

										Relationship rel=new Relationship();
										rel.setType("ForwardsTo");
										rel.setDstNode(dstNode.getId());
										rel.setSrcNode(srcNode.getId());

										ClientResponse relResp=Localhost_Neo4JSimpleXMLWebapi.data(cli,Localhost_Neo4JSimpleXMLWebapi.BASE_URI).nodeNodeidRelationships(srcNode.getId()).postXml(rel, ClientResponse.class);

										System.out.println(relResp.getEntity(Relationship.class).getId());
										//print("Relationship postata tra il nodo "+srcNode.getId()+"ed il nodo "+dstNode.getId()+" con Id: "+resp.getEntity(Relationship.class).getId());


									}
								}
							}
						}
					}
				}
			}
		}

		//return null;

	}

	public void loadRelationship(it.polito.dp2.NFV.sol3.service.jaxb.Link l) {
		
		for(Nodes.Node srcNode:nodes.getNode()) {
			for(Property srcProp:srcNode.getProperties().getProperty()) {

				if(l.getSrcNode().compareTo(srcProp.getValue())==0) {

					for(Nodes.Node dstNode:nodes.getNode()) {
						for(Property dstProp:dstNode.getProperties().getProperty()) {
							if(l.getDstNode().compareTo(dstProp.getValue())==0) {

								Relationships.Relationship relsRel=new Relationships.Relationship();
								relsRel.setType("ForwardsTo");
								relsRel.setDstNode(dstNode.getId());
								relsRel.setSrcNode(srcNode.getId());		
								rels.getRelationship().add(relsRel);

								Relationship rel=new Relationship();
								rel.setType("ForwardsTo");
								rel.setDstNode(dstNode.getId());
								rel.setSrcNode(srcNode.getId());

								ClientResponse relResp=Localhost_Neo4JSimpleXMLWebapi.data(cli,Localhost_Neo4JSimpleXMLWebapi.BASE_URI).nodeNodeidRelationships(srcNode.getId()).postXml(rel, ClientResponse.class);

								System.out.println(relResp.getEntity(Relationship.class).getId());
								//print("Relationship postata tra il nodo "+srcNode.getId()+"ed il nodo "+dstNode.getId()+" con Id: "+resp.getEntity(Relationship.class).getId());


							}
						}
					}
				}
			}
		}


	}


	public void loadHost(it.polito.dp2.NFV.sol3.service.jaxb.Host h) {

		System.out.println("loadHost di Reachability Tester");
		// Create new host
		Node host=new Node();
		Nodes.Node hostNode=new Nodes.Node();

		// Set property of the host
		Property hostProp=new Property();					
		hostProp.setName("name");
		hostProp.setValue(h.getName());

		// Set properties of the host
		Properties hostProps=new Properties();
		hostProps.getProperty().add(hostProp);

		// Set Label of the host
		Labels hostLabels=new Labels();
		hostLabels.getLabel().add("Host");

		// Set the host
		host.setProperties(hostProps);
		hostNode.setProperties(hostProps);
		hostNode.setLabels(hostLabels);

		ClientResponse resp=Localhost_Neo4JSimpleXMLWebapi.data(cli,Localhost_Neo4JSimpleXMLWebapi.BASE_URI).node().postXml(host, ClientResponse.class);
		Node hostResp=resp.getEntity(Node.class);
		System.out.println(hostResp.getId());
		String hostId=hostResp.getId();
		//print("Host postati: "+ ++i +"\n"+hostId);

		hostNode.setId(hostId);
		hostNodes.getNode().add(hostNode);

		Localhost_Neo4JSimpleXMLWebapi.data(cli,Localhost_Neo4JSimpleXMLWebapi.BASE_URI).nodeNodeidLabels(hostId).postXml(hostLabels, ClientResponse.class);
		//print("Label postate per l'host: "+ hostId);

		for(String hn:h.getHostNodes().getHostNode()) {
			for(Nodes.Node node:nodes.getNode()) {
				for(Property prop:node.getProperties().getProperty()) {
					if(prop.getValue().compareTo(hn)==0) {

						// Create the relationship between the node and the host in which it's allocated
						Relationships.Relationship hostRelsRel=new Relationships.Relationship();
						hostRelsRel.setSrcNode(node.getId());
						hostRelsRel.setDstNode(hostId);
						hostRelsRel.setType("AllocatedOn");

						hostRels.getRelationship().add(hostRelsRel);

						Relationship hostRel=new Relationship();
						hostRel.setSrcNode(node.getId());
						hostRel.setDstNode(hostId);
						hostRel.setType("AllocatedOn");

						ClientResponse respRel=Localhost_Neo4JSimpleXMLWebapi.data(cli,Localhost_Neo4JSimpleXMLWebapi.BASE_URI).nodeNodeidRelationships(hostId).postXml(hostRel, ClientResponse.class);
						System.out.println(respRel.getEntity(Relationship.class).getId());
						//print("Relationship postata tra il nodo "+node.getId()+" e l'host "+hostId+" con Id: "+respRel.getEntity(Relationship.class).getId());


					}
				}
			}
		}


	}



}
