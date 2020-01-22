/**
 * 
 * 
 * Kayla Lewis
 * 
 */
import java.beans.Expression;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;


public class PhotoLibrary extends PhotographContainer{
	
	 
	 /**
	  * Unique integer assigned to this PhotoLibrary
	  */
	 private int id;
	 /**
	  * List of Photograph objects in this PhotoLibrary
	  */
	 
	 private HashSet<Album> albums= new HashSet<Album>();
	 
	 // Constructors
	 
	/**
	 *  Creates a PhotoLibrary using 2 parameters
	 * @param name
	 * @param id
	 */
	 public PhotoLibrary(String name, int id) {
		 super(name);
		 this.id= id;
	 
	 }
	// Methods
	 
	/**
	* Returns the HashSet of albums in this PhotoLibrary
	* 
	* @return albums: set of unique albums the user has created
	*/
	public HashSet<Album> getAlbums() {
		return albums;
	}
	
	/**
	 * Sets the name instance variable to the parameter
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Returns the id instance variable
	 * @return id: unique number assigned to this PhotoLibrary
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Attempts to remove the Photograph from the photos ArrayList as well as any albums the photo is in and returns if successful
	 * 
	 * @param a photograph object
	 * @return Return true if the Photograph was removed or false if it was not there.

	 */
	public boolean removePhoto(Photograph p) {
		if (photos.contains(p)) {
			photos.remove(p);
			for(Album a: albums) {
				a.removePhoto(p);
			}
			
			return true;
		}
		
			return false;
		
	}
	/**
	 * Checks if the parameter object is equal to this Photo
	 * 
	 * @param an object
	 * @return Return true if the parameter object equals the current person

	 */
	public boolean equals(Object o) {
		if(!(o instanceof PhotoLibrary)) {
			return false;
		}
			PhotoLibrary p = (PhotoLibrary) o;
		if(id== p.getId()) {
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
		return "Name: "+ name+ " Id: "+ id + " Photos: "+photos+ "Albums:"+albums;
	}
	/**
	 * @param two people objects
	 * @return ArrayList<Photograph> of the photos that both Person a and Person b have posted to their feeds
	 */
	public static ArrayList<Photograph> commonPhotos(PhotoLibrary a, PhotoLibrary b){
		
		ArrayList<Photograph> results= new ArrayList<Photograph>();
		for(Photograph p1: a.getPhotos()) {
			for(Photograph p2: b.getPhotos()) {
				if(p1.equals(p2)) {
					results.add(p1);
				}
			}
		}
		return results;
	}
	/**
	 * Provides a double ratio for the rate of similar photos in each PhotoLibrary
	 * 
	 * @param two PhotoLibrary objects
	 * @return number of commonPhotos to both people divided by smaller of the number of
			   photos a has posted and the number of photos b has posted
	 */
	public static double similarity(PhotoLibrary a, PhotoLibrary b) {
		int numSim= commonPhotos(a,b).size();
		double small=0;
		if(a.numPhotographs()<b.numPhotographs()) {
			small= a.numPhotographs();
		}
		else {
			small= b.numPhotographs();
		}
		if (numSim==0) {
			return 0.0;
		}
		return numSim/small;
		
	}
	
	
	/**
	 * This is a private helper method. Given an album name, return the Album with that name from the set of albums.
	 * 
	 * @param albumName : name of album
	 * @return the album with the given name
	 */
	private Album getAlbumByName(String albumName) {
		for(Album a: albums) {
			if(a.getName().equals(albumName)) {
				return a;
			}
		}
		return null;
	}
	/**
	 * Creates a new Album with name albumName and adds it to the list of albums, only if an
 	 * Album with that name does not already exist
	 *
	 * @param albumName: name of album to be added
	 * @return returns true if album was created, otherwise false
	 */
	public boolean createAlbum(String albumName) {
			if (getAlbumByName(albumName)==null) {
			Album a= new Album(albumName);
			albums.add(a);
			return true;
		}
			
		
		return false;
	}
	/**
	 * Removes an album with the name albumName if it exists with the albums HashSet
	 * 
	 * @param albumName: name of album to be removed
	 * @return returns true is album was removed, otherwise false
	 */
	public boolean removeAlbum(String albumName) {
		if (getAlbumByName(albumName)!=null) {
			albums.remove(getAlbumByName(albumName));
			return true;
		}
			
		
		return false;
	}
	
	/**
	 * Checks if p is in list of photos and not already in albumName, then attempts to add them if satisfies those conditions
	 *
	 * @param p: photo to be added
	 * @param albumName: album the photo is being added to
	 * @return returns true if the photo was added to the album
	 */
	public boolean addPhotoToAlbum(Photograph p, String albumName) {
		if(albums.contains(getAlbumByName(albumName))) {
			Album a= getAlbumByName(albumName);
			if(a.getPhotos()==null) {
				a.addPhoto(p);
				return true;
			}
			if(hasPhoto(p) && !a.hasPhoto(p)) {
				a.addPhoto(p);
				return true;
			}
		}
		
		
		return false;
	}
	/**
	 * Removes the Photograph p from the Album in the set of albums that has name albumName
	 * 
	 * @param p: the photo to be removed
	 * @param albumName: the album the photo is to be removed from
	 * @return returns true if the photo was removed from the album
	 */
	public boolean removePhotoFromAlbum(Photograph p, String albumName) {
		if (getAlbumByName(albumName)==null)
		{
			return false;
		}
		Album a= getAlbumByName(albumName);
		if(p==null) {
			return false;
		}
		
		if(a.hasPhoto(p)) {
			a.removePhoto(p);
			return true;
		}
		return false;
	}
	
	
	
	
	public static void main(String[] args) {
//		PhotoLibrary a = new PhotoLibrary("MyLibrary", 1);
//        Photograph b = new Photograph("mypic45.jpg", "Grand Canyon", "2014-01-11", 2);
//        Photograph c = new Photograph("water.jpg", "Rafting", "2016-05-11", 3);
//        Photograph d = new Photograph("water2.jpg", "Rafting 2", "2016-09-30", 1);
//		
//			
//			System.out.println(a.getPhotosBetween("2019-01-01", "2019-02-11"));
//	 System.out.println(photoLib1.hasPhoto(photo5));;
//	 System.out.println(photoLib1.photos.size());
//		// Test Person Constructor 
//		PhotoLibrary p1= new PhotoLibrary("Jeff",123);
//		PhotoLibrary p2= new PhotoLibrary("Sally",321);
//		PhotoLibrary p3= new PhotoLibrary("Tim", 111);
//		//Test Photo Constructor
//		Photograph pic1= new Photograph("Jeff at park","park.png");
//		Photograph pic2= new Photograph("Sally at park","park2.png");
//		Photograph pic3= new Photograph("Dude at beach","park3.png");
//		Photograph pic4= new Photograph("Jeff at park","park.png");
//		Photograph pic5= new Photograph("Jeff at beach","beach.png");
//		
//		// Test takePhoto
//		System.out.println();
//		System.out.println("*** Take Photo Test 1:");
//		System.out.println("Jeff Before: "+ p1.getPhotos());
//		System.out.println("Take photo 1 succesful: "+ p1.takePhoto(pic1));
//		System.out.println("Jeff After: "+ p1.getPhotos());
//		System.out.println("*** Take Photo Test 2:");
//		System.out.println("Jeff Before: "+ p1.getPhotos());
//		System.out.println("Take photo 1 succesful(not duplicate): "+ p1.takePhoto(pic1));
//		System.out.println("Jeff After: "+ p1.getPhotos());
//		// Test hasPhoto
//		System.out.println();
//		System.out.println("*** Has Photo Test 1:");
//		System.out.println("Jeff Before: "+ p1.getPhotos());
//		System.out.println("Has photo 1 succesful: "+ p1.hasPhoto(pic1));
//		System.out.println("*** Has Photo Test 2:");
//		System.out.println("Jeff Before: "+ p1.getPhotos());
//		System.out.println("Take photo 2 succesful: "+ p1.hasPhoto(pic2));
//		System.out.println("Jeff After: "+ p1.getPhotos());
//		// Test erasePhoto
//		System.out.println();
//		System.out.println("***Erase Photo Test 1:");
//		System.out.println("Jeff Before: "+ p1.getPhotos());
//		System.out.println("Erase photo 1 succesful: "+ p1.erasePhoto(pic1));
//		System.out.println("Jeff After: "+ p1.getPhotos());
//		System.out.println("*** Erase Photo Test 2:");
//		System.out.println("Jeff Before: "+ p1.getPhotos());
//		System.out.println("Erase photo 2 succesful: "+ p1.hasPhoto(pic2));
//		System.out.println("Jeff After: "+ p1.getPhotos());
//		// Test numPhotographs
//		System.out.println();
//		System.out.println("*** Num Photos Test 1:");
//		p2.takePhoto(pic1);
//		p2.takePhoto(pic2);
//		System.out.println("Sally's Photos "+ p2.getPhotos());
//		System.out.println("Number of Sally's Photos "+ p2.numPhotographs());
//		System.out.println("*** Num Photos Test 2:");
//		p2.takePhoto(pic3);
//		System.out.println("Sally's Photos "+ p2.getPhotos());
//		System.out.println("Number of Sally's Photos "+ p2.numPhotographs());
//		// Test equals
//		System.out.println();
//		System.out.println("*** Equals Test 1:");
//		System.out.println("Sally = Jeff: "+ p1.equals(p2));
//		System.out.println("*** Equals Test 2:");
//		System.out.println("Sally = Sally: "+ p2.equals(p2));
//		// Test toString
//		System.out.println();
//		System.out.println("*** toString Test 1:");
//		System.out.println("Jeff "+ p1);
//		System.out.println("*** toString Test 2:");
//		System.out.println("Sally "+ p2);
//		//Test commonPhotos
//		System.out.println();
//		System.out.println("*** Common Test 1:");
//		System.out.println("Jeff: "+ p1.getPhotos());
//		System.out.println("Sally: "+ p2.getPhotos());
//		System.out.println("Common: "+commonPhotos(p1,p1));
//		System.out.println("*** Common Test 2:");
//		p1.takePhoto(pic3);
//		System.out.println("Jeff: "+ p1.getPhotos());
//		System.out.println("Sally: "+ p2.getPhotos());
//		System.out.println("Common: "+commonPhotos(p1,p1));
//		// Test similarity
//		System.out.println();
//		System.out.println("*** Similarity Test 1:");
//		System.out.println("Jeff: "+ p1.getPhotos());
//		System.out.println("Sally: "+ p2.getPhotos());
//		System.out.println("Similarity: "+similarity(p1,p2));
//		System.out.println("*** Similarity Test 2:");
//		p3.takePhoto(pic3);
//		p3.takePhoto(pic1);
//		p3.takePhoto(pic5);
//		System.out.println("Tinme: "+ p3.getPhotos());
//		System.out.println("Sally: "+ p2.getPhotos());
//		System.out.println("Similarity: "+similarity(p3,p2));
	
		 
	}

}

