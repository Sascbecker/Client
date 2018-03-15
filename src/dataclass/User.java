package dataclass;

/**
 * Klasse für alle aktionen mit Benutzer.
 * Speichern der IP-Adresse eines Benutzers in dieser Klasse
 * 
 * 
 * Deklaration für Aktion
 * 0 = nix
 * 1 = BenutzerAnlegen
 * 2 = LogIn
 */
public class User {
	
	private int aktion;
	private String absenderId;
	private String passwort;
	private String ipAdresse;
	private String returnCode;
	private boolean userAuthentifizierung;
	

	/**
	 * empty constructor
	 */
	public User() {}
	
	/**
	 * constructor without returnCode and ipAdresse
	 */
	public User(int aktion, String absenderId, String passwort)
	{
		this.aktion = aktion;
		this.absenderId = absenderId;
		this.passwort = passwort;
	}
	/**
	 * constructor without returnCode and ipAdresse, empfaengerId is unused
	 */

	public User(int aktion, String absenderId, String passwort, String ipAdresse)
	{
		this.aktion = aktion;
		this.absenderId = absenderId;
		this.passwort = passwort;
		this.ipAdresse = ipAdresse;
	}
	
	
	public int getAktion() { return aktion;}
	public String getAbsenderId() { return absenderId;}
	public String getPasswort() { return passwort;}
	public String getIpAdresse() { return ipAdresse;}
	public String getReturnCode() { return returnCode;}
	public boolean getUserAuthentifizierung() { return userAuthentifizierung;}
	
	public void setAktion(int aktion) { this.aktion = aktion;}
	public void setAbsenderId(String absenderId) {this.absenderId = absenderId;}
	public void setPasswort(String passwort) {this.passwort = passwort;}
	public void setIpAdresse(String ipAdresse) { this.ipAdresse = ipAdresse;}
	public void setReturnCode(String returnCode) {this.returnCode = returnCode;}
	public void setUserAuthentifizierung(boolean userAuthentifizierung) { this.userAuthentifizierung = userAuthentifizierung;}
	
}
