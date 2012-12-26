package buet.rafi.dictionary;

public class Bean {
	public String english;
	public String bangla;
	
	public Bean(String english, String bangla) {
		this.english = english;
		this.bangla = bangla;
	}

	@Override
	public String toString() {
		return "Bean [english=" + english + ", bangla=" + bangla + "]";
	}
}
