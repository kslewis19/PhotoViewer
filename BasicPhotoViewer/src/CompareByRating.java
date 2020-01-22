import java.util.Comparator;

public class CompareByRating implements Comparator<Photograph> {

	/**
	 * 	Compares two photographs based on alphabetical order, if caption is the same it compares based on rating
	 *  @param two photo graph objects, the first being compared to the second
	 *  @return negative int if p1 has lower rating than p2, positive int if p1 has higher rating than p2
	 */
	public int compare(Photograph p1, Photograph p2) {
		if(p1.getRating()==p2.getRating()){
			if(p1.getCaption().equals(p2.getCaption()))
			{
				return 0;
			}
			CompareByCaption comp= new CompareByCaption();
			
			return comp.compare(p1,p2);
			
		}
		else if(p1.getRating()>p2.getRating())
		{
			return -1;
		}
		else {
			return 1;
		}
		
	}

}
