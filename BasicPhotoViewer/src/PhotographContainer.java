import java.sql.Date;
import java.util.ArrayList;

public abstract class PhotographContainer {




	/**
	 * String representing the name of the photo album
	 */
	protected String name;
	/**
	 * An ArrayList of photos that are within the album
	 */
	protected ArrayList<Photograph> photos= new ArrayList<Photograph>();

	/**
	 * Returns the name of the album
	 * 
	 * @return name: album name
	 */

	//Constructor
	public PhotographContainer(String name) {
		this.name = name;

	}

	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the album to the parameter
	 * 
	 * @param name: the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the ArrayList of photos in the album
	 * 
	 * @return photos: photos in album
	 */
	public ArrayList<Photograph> getPhotos() {
		return photos;
	}
	/**
	 * Adds the Photograph p to the list of the current object’s photos if it was not already in that list

	 * @param p: the photo to be added
	 * @return returns true if the photo was added successfully, otherwise returns false
	 */
	public boolean addPhoto(Photograph p) {
		if(p==null) {
			return false;
		}
		if (this.photos==null ||!photos.contains(p)) {
			photos.add(p);
			return true;
		}

		return false;

	}
	/**
	 * Finds if parameter p is in the list of photos
	 * 
	 * @param p: the object being compared to the ArrayList
	 * @return return true if the current object has p in its list of photos, otherwise returns false
	 */
	public boolean hasPhoto(Photograph p) {
		for(Photograph photo: photos) {
			if (photo.equals(p)){
				return true;
			}


		}
		return false;
	}
	/**
	 * Removes Photograph p from the album, if it exists in the list of photos
	 *  
	 * @param p: the photo to be removed
	 * @return returns true if the photo was successfully removed, otherwise returns false
	 */
	public boolean removePhoto(Photograph p) {
		if (photos.contains(p)) {
			photos.remove(p);
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Returns the size of the photos ArrayList
	 * 
	 * @return size of photos
	 */
	public int numPhotographs() {
		return photos.size();
	}
	/**
	 * Returns an ArrayList of photos from the photos feed that have a rating greater than
	 * or equal to the given parameter
	 *
	 * @param rating: integer between 0-5
	 * @return results: photos that have the given rating of higher
	 */
	public ArrayList<Photograph> getPhotos(int rating){
		ArrayList<Photograph> results= new ArrayList<Photograph>();
		if(rating>=0 && rating<=5) {

			for(Photograph p:photos){

				if (p.getRating()>= rating) {

					results.add(p);
				}
			}
			return results;

		}
		else {
			return null;
		}
	}
	/**
	 * Return an ArrayList of photos from the photos feed that were taken in the year
	 * represented by the parameter
	 *
	 * @param year: year desired photos where taken
	 * @return results: photos that where taken in given year
	 */
	public ArrayList<Photograph> getPhotosInYear(int year){
		ArrayList<Photograph> results= new ArrayList<Photograph>();
		if(year<=2019&& year>0) {
			for(Photograph p: photos) {
				int iYear=Integer.parseInt(p.getDateTaken().substring(0,4)); 
				if (year== iYear) {
					results.add(p);
				}
			}
			return results;
		}

		else {
			return null;
		}
	}
	/**
	 * Returns an ArrayList of photos from the photos feed that were taken between beginDate and endDate
	 * 
	 * @param beginDate: inclusive starting bounds for desired dates
	 * @param endDate: inclusive ending bounds for desired dates
	 * @return results: photos taken between or on the given dates
	 */
	public ArrayList<Photograph> getPhotosBetween(String beginDate, String endDate){
		ArrayList<Photograph> results= new ArrayList<Photograph>();
		if(beginDate.length()!=10||endDate.length()!=10) {
			return null;
		}
		Integer startYear = Integer.valueOf(beginDate.substring(0,4));
		Integer startMonth = Integer.valueOf(beginDate.substring(5,7));
		Integer startDay = Integer.valueOf(beginDate.substring(8));
		Integer endYear = Integer.valueOf(endDate.substring(0,4));
		Integer endMonth = Integer.valueOf(endDate.substring(5,7));
		Integer endDay = Integer.valueOf(endDate.substring(8));

		//checks for invalid dates
		if(startYear > 2019|| endYear > 2019 || startMonth > 11 || endMonth > 11 || startDay > 31 || endDay > 31) {
			return null;
		}

		@SuppressWarnings("deprecation")
		Date start= new Date(startYear-1900,startMonth-1,startDay);
		@SuppressWarnings("deprecation")
		Date end= new Date(endYear-1900,endMonth-1,endDay);

		for (Photograph p: photos) {
			Integer pYear = Integer.valueOf(p.getDateTaken().substring(0,4));
			Integer pMonth = Integer.valueOf(p.getDateTaken().substring(5,7));
			Integer pDay = Integer.valueOf(p.getDateTaken().substring(8));
			@SuppressWarnings("deprecation")
			Date pDate= new Date(pYear-1900,pMonth-1,pDay);

			if(pDate==start|| pDate==end) {
				results.add(p);
			}
			else if(pDate.after(start)&& pDate.before(end)) {
				results.add(p);
			}
		}
		return results;

	}
	/**
	 * Returns an ArrayList of photos from the photos feed that were taken in the year
	 *  and month represented by the parameters
	 *
	 * @param year: year desired photos where taken
	 * @param month: month desired photos where taken
	 * @return results: photos that where taken in given year and month
	 */
	public ArrayList<Photograph> getPhotosInMonth(int month, int year){
		ArrayList<Photograph> results= new ArrayList<Photograph>();
		if(year/1000<10 && month<=12 && month > 0) {
			for(Photograph p: photos) {

				int iYear=Integer.parseInt(p.getDateTaken().substring(0,4)); 
				int iMonth=Integer.parseInt(p.getDateTaken().substring(5,7)); 
				if(month== iMonth && year== iYear) {
					results.add(p);
				}
			}
			return results;
		}
		else {
			return null;
		}
	}

	/**
	 * Checks if the parameter object is equal to this album
	 * 
	 * @param an object
	 * @return Return true if the parameter object equals the current album
	 */
	public boolean equals(Object o) {
		if(!(o instanceof Album)) {
			return false;
		}
		Album a = (Album) o;
		if(name== a.getName()) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Word description of this class
	 * 
	 * @return string describing the current instance of this class
	 */
	public String toString() {
		return "Name: "+ name+ " Photos: " + photos ;
	}

}



