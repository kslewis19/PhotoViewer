import java.util.ArrayList;

/**
 * 
 * 
 * Kayla Lewis
 * 
 */
public class Album extends PhotographContainer {
	
	
	//Constructor
	
	/**
	 * Creates an album object using a given name
	 * 
	 * @param name: string name of the album
	 */
	public Album(String name) {
		super(name);
		
	}

	
	// Methods
	
	
	/**
	 * Returns the name of the album
	 * 
	 * @return name: album name
	 */
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

