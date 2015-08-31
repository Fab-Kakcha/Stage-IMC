package crud;

public class Etat {
	
	private String code;
	private String intitule;
	
	
	public Etat(String code, String intitule) {
		this.code = code;
		this.intitule = intitule;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getIntitule() {
		return intitule;
	}
	
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	
	@Override
	public String toString() {
		return "Etat [code=" + code + ", intitule=" + intitule + "]";
	}
	
}
