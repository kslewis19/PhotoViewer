import java.io.File;
import java.sql.Date;

/**
 * 
 * 
 * Kayla Lewis
 * 
 */
public class Photograph implements Comparable<Photograph> {

	/**
	 * String of words describing this photograph
	 */
	private String caption;

	/**
	 *  Name of the file the picture is saved as 
	 */
	private String fileName;

	/**
	 *  A String containing the date the photograph was taken
	 */
	private String dateTaken;

	/**
	 *  Represents the rating of the photograph on a scale from 0 to 5, no other values are allowed
	 */
	private int rating;
	/**
	 * The file that the physical image is saved to 
	 */
	private File imageFile;

	// Constructors

	/**
	 * creates a Photograph using 2 parameters
	 * @param caption
	 * @param fileName
	 */
	public Photograph(String caption,String fileName){
		this.caption= caption;
		this.fileName= fileName;
		imageFile = new File(fileName);
	}

	/**
	 * creates a Photograph using 4 parameters
	 * 
	 * @param filename
	 * @param caption
	 * @param dateTaken
	 * @param rating
	 */
	public Photograph(String fileName, String caption, String dateTaken, int rating) {
		this.caption= caption;
		this.fileName = fileName;
		this.dateTaken= dateTaken;
		this.rating= rating;
	}

	// Methods
	/**
	 * 
	 * @param p: Compares the dateTaken of the current Photograph object with the parameter p
	 * @return negative int if object taken before p or negative int if taken after, if taken at the same time will return int based on caption
	 */
	public int compareTo(Photograph o) {

		Photograph p = (Photograph) o;

		Integer pYear = Integer.valueOf(p.getDateTaken().substring(0,4));
		Integer pMonth = Integer.valueOf(p.getDateTaken().substring(5,7));
		Integer pDay = Integer.valueOf(p.getDateTaken().substring(8));
		@SuppressWarnings("deprecation")
		Date pDate= new Date(pYear-1900,pMonth-1,pDay);

		Integer thisYear = Integer.valueOf(this.getDateTaken().substring(0,4));
		Integer thisMonth = Integer.valueOf(this.getDateTaken().substring(5,7));
		Integer thisDay = Integer.valueOf(this.getDateTaken().substring(8));
		@SuppressWarnings("deprecation")
		Date thisDate= new Date(thisYear-1900,thisMonth-1,thisDay);
		if(thisDate.before(pDate)) {
			return -1;
		}
		else if (thisDate.after(pDate)) {
			return 1;
		}
		else {
			CompareByCaption comp= new CompareByCaption();

			return comp.compare(this,p);
		}
	}
	/**
	 * Returns the dateTaken variable
	 * 
	 * @return the dateTaken
	 */
	public String getDateTaken() {
		return dateTaken;
	}
	/**
	 * Sets the dataTaken instance variable to the parameter
	 * 
	 * @param dateTaken: the dateTaken to set
	 */
	public void setDateTaken(String dateTaken) {
		this.dateTaken = dateTaken;
	}
	/**
	 * Returns the rating instance variable
	 * 
	 * @return the rating: rating of photograph form 0-5
	 */
	public int getRating() {
		return rating;
	}
	/**
	 * Sets the rating instance variable to the parameter
	 * @param rating: the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}
	/**
	 * Returns caption instance variable
	 * 
	 * @return caption: string describing picture
	 */
	public String getCaption() {
		return caption;
	}

	/** 
	 * Returns th3e fileName instance variable
	 * 
	 * @return fileName: string being returned
	 */
	public String getFilename() {
		return fileName;
	}
	/**
	 * @return the imageFile
	 */
	public File getImageFile() {
		return imageFile;
	}

	/**
	 * @param imageFile the imageFile to set
	 */
	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

	/**
	 *  Checks if the object is equal to this instance of a photograph
	 * @param an object
	 * @return Returns whether or not the parameter object equals the current object

	 */
	public boolean equals(Object o) {
		if(!(o instanceof Photograph)) {
			return false;
		}
		Photograph p = (Photograph) o;
		if(this.caption == p.getCaption() && this.fileName == p.getFilename()) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 *  @return String describing Photograph
	 */
	public String toString() {
		return "<html>Caption: "+ caption+"<br>"+ "Date: "+ dateTaken+"<br>"+"Rating: "+ rating+"<html>";
	}
	public static void main(String[] args) {
		Photograph photo5= new Photograph("fileName5","caption5","2016-21-01",5);
		Photograph photo6= new Photograph("fileName5","zzzzzzzz","2016-21-01",5);
		CompareByCaption comp= new CompareByCaption();


		System.out.println(comp.compare(photo5,photo6));
	}


}
