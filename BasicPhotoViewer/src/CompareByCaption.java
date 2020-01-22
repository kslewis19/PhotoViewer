import java.util.Comparator;

public class CompareByCaption implements Comparator<Photograph>{
	
	/**
	 * 	Compares two photographs based on alphabetical order, if caption is the same it compares based on rating
	 *  @param two photo graph objects, the first being compared to the second
	 *  @return negative int if p1 caption comes before p2, positive int if p1 comes after p2
	 */
	public int compare(Photograph p1, Photograph p2) {
		
		
		if(p1.getCaption().compareTo(p2.getCaption())==0) {
			if(p1.getRating()==p2.getRating()) {
				return 0;
			}
			
			
			return p2.getRating()-p1.getRating();
		}
		
		return p1.getCaption().compareTo(p2.getCaption());
	}

}
