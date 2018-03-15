package dataclass;

import java.util.List;

import dataclass.User;

/**
 * dataclass for Contacts
 * doesn't do anything, only contains information about a user
 *
 */
public class Kontakte {

	private String userId;
	private List<User> kontaktliste;
	private List<Group> groupliste;
	
	/**
	 * empty constructor
	 */
	public Kontakte()
	{
	}
	
	
	public String getUserId() { return userId;}
	
	public List<User> getKontaktListe () { return kontaktliste;}
	public List<Group> getGroupListe() { return groupliste;}
	
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	
	public void setKontaktListe(List<User> kontaktListe) { this.kontaktliste = kontaktListe;}
	public void setGroupListe(List<Group> groupliste) { this.groupliste = groupliste; }
}
