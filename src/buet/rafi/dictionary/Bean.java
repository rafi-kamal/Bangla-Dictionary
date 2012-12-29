package buet.rafi.dictionary;

public class Bean {
	public int id;
	public String english;
	public String bangla;
	public String status;
	
	public Bean(String english, String bangla) {
		this.english = english;
		this.bangla = bangla;
	}
	
	public Bean(int id, String english, String bangla, String status) {
		this.id = id;
		this.english = english;
		this.bangla = bangla;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Bean [english=" + english + ", bangla=" + bangla + "]";
	}
}
