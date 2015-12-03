package buet.rafi.dictionary.model;

public class Word {
	public int id;
	public String english;
	public String bangla;
	public String status;
	
	public Word(String english, String bangla) {
		this.english = english;
		this.bangla = bangla;
	}
	
	public Word(int id, String english, String bangla, String status) {
		this.id = id;
		this.english = english;
		this.bangla = bangla;
		this.status = status;
	}

	@Override
	public String toString() {
		return "(english=" + english + ", bangla=" + bangla + ")";
	}
}
