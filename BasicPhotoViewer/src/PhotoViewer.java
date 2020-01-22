import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class PhotoViewer extends JFrame{
	/**
	 *  The Photograph container that holds the photographs being viewed
	 */
	private PhotographContainer imageAlbum;
	/**
	 * int variable that keeps track of the position of the current photo
	 */
	private int currentPhoto=0;
	/**
	 *  Creates a new photo viewer object 
	 *
	 */
	public PhotoViewer() {

	}
	/**
	 *  Main method that creates a photoViewer object, loads 5 photos in the viewers album, and creates the gui
	 * @param args
	 */
	public static void main(String[] args) {
		PhotoViewer myViewer = new PhotoViewer();
		// relative path for Macs/Linux:
		String imageDirectory =
				"images/";
		//		// relative path for PCs:
		//		String imageDirectory =
		//		"images\\";
		Photograph p1 = new Photograph( imageDirectory + "photo1.jpg"," Angry Dog", "2009-06-30", 5);
		Photograph p2 = new Photograph( imageDirectory + "photo2.jpg", "Cute Dog", "2014-06-31", 4);
		Photograph p3 = new Photograph( imageDirectory + "photo3.jpg","A Good Boy", "2013-06-20", 3);
		Photograph p4 = new Photograph( imageDirectory + "photo4.jpg","Gazing", "2015-07-30", 3);
		Photograph p5 = new Photograph( imageDirectory + "photo5.jpg","Fluffy", "2011-06-05", 1);

		myViewer.imageAlbum = new PhotoLibrary("Test Library", 1);
		myViewer.imageAlbum.addPhoto(p1);
		myViewer.imageAlbum.addPhoto(p2);
		myViewer.imageAlbum.addPhoto(p3);
		myViewer.imageAlbum.addPhoto(p4);
		myViewer.imageAlbum.addPhoto(p5);

		//Collections.sort(myViewer.imageAlbum.photos);
		javax.swing.SwingUtilities.invokeLater(() -> myViewer.createAndShowGUI() );

	}
	/*
	 * Defines and creates and intializes GUI object
	 * 
	 */
	private void createAndShowGUI() {

		class GUI extends JFrame{

			private JButton exit;
			private JButton previous;
			private JButton next;
			private JButton sortDate;
			private JButton sortCaption;
			private JButton sortRating;
			private JRadioButton setRating1;
			private JRadioButton setRating2;
			private JRadioButton setRating3;
			private JRadioButton setRating4;
			private JRadioButton setRating5;
			private JPanel side;
			private ImageIcon i1;
			private JLabel imageLabel1;
			private ImageIcon i2;
			private JLabel imageLabel2;
			private ImageIcon i3;
			private JLabel imageLabel3;
			private ImageIcon i4;
			private JLabel imageLabel4;
			private ImageIcon i5;
			private JLabel imageLabel5;
			private JPanel center;
			private ImageIcon mainImage;
			private JLabel mainImageHolder;

			/**
			 * GUI constructor creates gui object and calls inialized method
			 */

			public GUI() {
				initialize();

			}
			/**
			 * Creates the first display of the gui using JPanels, JButtons, and JLabels
			 */
			private void initialize() {
				//set window size
				// makes gui fullscreen
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				this.setSize(screenSize.width, screenSize.height);
				//what to do when closing
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				// a title 
				this.setTitle("Instagram Photoviewer");
				//sets the layout of the content pane to border layout
				this.getContentPane().setLayout(new BorderLayout(0,0));


				// TOP PANEL

				JPanel top= new JPanel();
				// exit button
				exit= new JButton("Exit");
				exit.setActionCommand("exit");
				exit.addActionListener(new ButtonListener());
				top.add(exit);
				// previous button
				previous= new JButton("Previous");
				previous.setActionCommand("previous");
				previous.addActionListener(new ButtonListener());
				top.add(previous);
				// next button
				next= new JButton("Next");
				next.setActionCommand("next");
				next.addActionListener(new ButtonListener());
				top.add(next);
				// sortDate button
				sortDate= new JButton("Sort By Date");
				sortDate.setActionCommand("sortDate");
				sortDate.addActionListener(new ButtonListener());
				top.add(sortDate);
				// sortCaption button
				sortCaption= new JButton("Sort By Caption");
				sortCaption.setActionCommand("sortCaption");
				sortCaption.addActionListener(new ButtonListener());
				top.add(sortCaption);
				// sortRating button
				sortRating= new JButton("Sort By Rating");
				sortRating.setActionCommand("sortRating");
				sortRating.addActionListener(new ButtonListener());
				top.add(sortRating);

				this.getContentPane().add(top,BorderLayout.NORTH);




				//BOTTOM PANEL

				JPanel bottom= new JPanel();
				bottom.add(new JLabel("Set Rating: "));
				// rate 1
				setRating1= new JRadioButton("1");
				setRating1.addActionListener(new ButtonListener());
				bottom.add(setRating1);
				// rate 2
				setRating2= new JRadioButton("2");

				setRating2.addActionListener(new ButtonListener());
				bottom.add(setRating2);
				// rate 3
				setRating3= new JRadioButton("3");
				setRating3.addActionListener(new ButtonListener());
				bottom.add(setRating3);
				// rate 4
				setRating4= new JRadioButton("4");
				setRating4.addActionListener(new ButtonListener());
				bottom.add(setRating4);
				// rate 5
				setRating5= new JRadioButton("5");
				setRating5.addActionListener(new ButtonListener());
				bottom.add(setRating5);
				ButtonGroup bg=new ButtonGroup(); 
				bg.add(setRating1);
				bg.add(setRating2);
				bg.add(setRating3);
				bg.add(setRating4);
				bg.add(setRating5);

				this.getContentPane().add(bottom,BorderLayout.SOUTH);

				// SIDE PANEL
				this.setSidePanel();


				// CENTER PANEL
				this.setCenterPanel();
			}


			/**
			 * 	  
			 *  Sub class that defines a button listener and the actions each button is allowed to perform
			 *
			 */
			class ButtonListener implements ActionListener {
				class CompareByDate implements Comparator<Photograph>{

					public int compare(Photograph p1, Photograph p2) {
						String d1= p1.getDateTaken();
						String d2= p2.getDateTaken();

						Integer p1Year = Integer.valueOf(d1.substring(0,4));
						Integer p1Month = Integer.valueOf(d1.substring(5,7));
						Integer p1Day = Integer.valueOf(d1.substring(8));
						Integer p2Year = Integer.valueOf(d2.substring(0,4));
						Integer p2Month = Integer.valueOf(d2.substring(5,7));
						Integer p2Day = Integer.valueOf(d2.substring(8));   

						@SuppressWarnings("deprecation")
						Date date1= new Date(p1Year-1900,p1Month-1,p1Day);

						@SuppressWarnings("deprecation")
						Date date2= new Date(p2Year-1900,p2Month-1,p2Day);

						if(date1.after(date2)) {
							return -1;
						}
						else if(date1.before(date2)) {
							return 1;
						}
						return 0;

					}
				}
				/**
				 * Helper class that implements differenct actions based on the buttons pressed or selected
				 */
				public void actionPerformed(ActionEvent e) {
					if(setRating1.isSelected()){    
						imageAlbum.getPhotos().get(currentPhoto).setRating(1);
					} 
					if(setRating2.isSelected()){    
						imageAlbum.getPhotos().get(currentPhoto).setRating(2);
					}  
					if(setRating3.isSelected()){    
						imageAlbum.getPhotos().get(currentPhoto).setRating(3);
					}  
					if(setRating4.isSelected()){    
						imageAlbum.getPhotos().get(currentPhoto).setRating(4);
					}  
					if(setRating5.isSelected()){    
						imageAlbum.getPhotos().get(currentPhoto).setRating(5);
					}  
					if (e.getActionCommand().equals("exit")) {
						System.exit(0);
					}
					if (e.getActionCommand().equals("previous")) {
						currentPhoto-=1;
						if(currentPhoto<0) {
							currentPhoto=4;	
						}
					}
					if (e.getActionCommand().equals("next")) {
						currentPhoto+=1;
						if(currentPhoto>4) {
							currentPhoto=0;
						}

					}
					if (e.getActionCommand().equals("sortDate")) {
						currentPhoto=0;
						CompareByDate dateCompare = new CompareByDate(); 
						Collections.sort(imageAlbum.getPhotos(), dateCompare); 
					}
					if (e.getActionCommand().equals("sortCaption")) {
						currentPhoto=0;
						CompareByCaption captionCompare = new CompareByCaption(); 
						Collections.sort(imageAlbum.getPhotos(), captionCompare); 
					}
					if (e.getActionCommand().equals("sortRating")) {
						currentPhoto=0;
						CompareByRating ratingCompare = new CompareByRating(); 
						Collections.sort(imageAlbum.getPhotos(), ratingCompare); 
					}
					setCenterPanel();
					setSidePanel();
					revalidate();

				}
			}
			/**
			 *  Helper method that creates and updates the side panel of the gui
			 */
			private void setSidePanel() {
				side= new JPanel();

				side.setLayout(new BoxLayout(side, BoxLayout.Y_AXIS));


				i1= new ImageIcon(imageAlbum.getPhotos().get(0).getFilename());
				imageLabel1 = new JLabel();
				imageLabel1.setHorizontalTextPosition(SwingConstants.RIGHT);
				imageLabel1.setText(imageAlbum.getPhotos().get(0).toString());
				imageLabel1.setIcon(new ImageIcon(i1.getImage().getScaledInstance(180, 150, Image.SCALE_DEFAULT)));
				side.add(imageLabel1);
				imageLabel1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						currentPhoto=0;
						setCenterPanel();
						revalidate();
					}

				});


				i2= new ImageIcon(imageAlbum.getPhotos().get(1).getFilename());
				imageLabel2 = new JLabel();
				imageLabel2.setHorizontalTextPosition(SwingConstants.RIGHT);
				imageLabel2.setText(imageAlbum.getPhotos().get(1).toString());
				imageLabel2.setIcon(new ImageIcon(i2.getImage().getScaledInstance(180, 150, Image.SCALE_DEFAULT)));
				side.add(imageLabel2);
				imageLabel2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						currentPhoto=1;
						setCenterPanel();
						revalidate();
					}

				});


				i3= new ImageIcon(imageAlbum.getPhotos().get(2).getFilename());
				imageLabel3 = new JLabel();
				imageLabel3.setHorizontalTextPosition(SwingConstants.RIGHT);
				imageLabel3.setText(imageAlbum.getPhotos().get(2).toString());
				imageLabel3.setIcon(new ImageIcon(i3.getImage().getScaledInstance(180, 150, Image.SCALE_DEFAULT)));
				side.add(imageLabel3);
				imageLabel3.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						currentPhoto=2;
						setCenterPanel();
						revalidate();
					}

				});

				i4= new ImageIcon(imageAlbum.getPhotos().get(3).getFilename());
				imageLabel4 = new JLabel();
				imageLabel4.setHorizontalTextPosition(SwingConstants.RIGHT);
				imageLabel4.setText(imageAlbum.getPhotos().get(3).toString());
				imageLabel4.setIcon(new ImageIcon(i4.getImage().getScaledInstance(180, 150, Image.SCALE_DEFAULT)));
				side.add(imageLabel4);
				imageLabel4.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						currentPhoto=3;
						setCenterPanel();
						revalidate();
					}

				});

				i5= new ImageIcon(imageAlbum.getPhotos().get(4).getFilename());
				imageLabel5 = new JLabel();
				imageLabel5.setHorizontalTextPosition(SwingConstants.RIGHT);
				imageLabel5.setText(imageAlbum.getPhotos().get(4).toString());
				imageLabel5.setIcon(new ImageIcon(i5.getImage().getScaledInstance(180, 150, Image.SCALE_DEFAULT)));
				side.add(imageLabel5);
				imageLabel5.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						currentPhoto=4;
						setCenterPanel();
						revalidate();
					}

				});
				getContentPane().add(side,BorderLayout.WEST);

			}
			/*
			 * Helper method that creates and updates the center panel of the gui
			 */
			private void setCenterPanel() {

				center= new JPanel();
				mainImageHolder = new JLabel();
				mainImage = new ImageIcon(imageAlbum.getPhotos().get(currentPhoto).getFilename());
				mainImageHolder.setIcon(new ImageIcon(mainImage.getImage().getScaledInstance(800, 650, Image.SCALE_DEFAULT)));
				center.add(mainImageHolder);
				getContentPane().add(center,BorderLayout.CENTER);

			}

		}

		try {
			GUI gui= new GUI();
			gui.setVisible(true);
		}
		catch(Exception e){
			System.out.println("Error");
		}
	}
}
