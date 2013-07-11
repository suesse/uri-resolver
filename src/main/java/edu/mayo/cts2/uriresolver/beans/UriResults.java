package edu.mayo.cts2.uriresolver.beans;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class UriResults {
	private String resourceType;
	private String resourceName;
	private String resourceURI;
	private String baseEntityURI;
	private String versionOf;
	private List<String> identifiers;
	public String getResourceType() {
		return resourceType;
	}
	public String getResourceName() {
		return resourceName;
	}
	public String getResourceURI() {
		return resourceURI;
	}
	public String getBaseEntityURI() {
		return baseEntityURI;
	}
	public String getVersionOf() {
		return versionOf;
	}
	public List<String> getIdentifiers() {
		return identifiers;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public void setResourceURI(String resourceURI) {
		this.resourceURI = resourceURI;
	}
	public void setBaseEntityURI(String baseEntityURI) {
		this.baseEntityURI = baseEntityURI;
	}
	public void setVersionOf(String versionOf) {
		this.versionOf = versionOf;
	}
	public void setIdentifiers(List<String> identifiers) {
		this.identifiers = identifiers;
	}

	public void print(){
		String baseEntityURI = getBaseEntityURI();
		List<String> identifiers = getIdentifiers();
		String resourceName = getResourceName();
		String resourceType = getResourceType();
		String resourceURI = getResourceURI();
		String versionOf = getVersionOf();
		
		System.out.println(baseEntityURI + ", " + resourceName + ", " + resourceType + ", " + resourceURI + ", " + versionOf);
		if(identifiers != null){
			for(String id : identifiers){
				System.out.println(id);
			}
		}
	}

}