import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import play.*;
import play.db.jpa.Blob;
import play.jobs.*;
import play.libs.MimeTypes;
import play.test.*;

import models.*;

/**
 *  @file					Bootstrap.java
 *  @description
 *		Loads sample data (via yaml file) and images into the Spacebook application
 *		
 *  @author				Damien Murphy
 *  @since				17 May 2016
 *  @version 			1.0
 */
@OnApplicationStart
public class Bootstrap extends Job 
{ 
  /* (non-Javadoc)
   * @see play.jobs.Job#doJob()
   */
  public void doJob() throws FileNotFoundException
  {
    Fixtures.deleteDatabase();
    Fixtures.loadModels("data.yml");
    
    String homerImage = "app/homer.jpg";
    Blob homerBlob = new Blob ();
    homerBlob.set(new FileInputStream(homerImage), MimeTypes.getContentType(homerImage));
    User homer = User.findByEmail("homer@simpson.com");
    homer.profilePicture = homerBlob;
    homer.save();
    
    String margeImage = "app/marge.jpg";
    Blob margeBlob = new Blob ();
    margeBlob.set(new FileInputStream(margeImage), MimeTypes.getContentType(margeImage));
    User marge = User.findByEmail("marge@simpson.com");
    marge.profilePicture = margeBlob;
    marge.save();
    
    String lisaImage = "app/lisa.jpg";
    Blob lisaBlob = new Blob ();
    lisaBlob.set(new FileInputStream(lisaImage), MimeTypes.getContentType(lisaImage));
    User lisa = User.findByEmail("lisa@simpson.com");
    lisa.profilePicture = lisaBlob;
    lisa.save();
    
    String bartImage = "app/bart.jpg";
    Blob bartBlob = new Blob ();
    bartBlob.set(new FileInputStream(bartImage), MimeTypes.getContentType(bartImage));
    User bart = User.findByEmail("bart@simpson.com");
    bart.profilePicture = bartBlob;
    bart.save();
    
    String maggieImage = "app/maggie.jpg";
    Blob maggieBlob = new Blob ();
    maggieBlob.set(new FileInputStream(maggieImage), MimeTypes.getContentType(maggieImage));
    User maggie = User.findByEmail("maggie@simpson.com");
    maggie.profilePicture = maggieBlob;
    maggie.save();    
  }
}