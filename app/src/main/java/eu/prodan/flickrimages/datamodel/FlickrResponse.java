package eu.prodan.flickrimages.datamodel;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Created by User on 16.05.2018.
 */

public class FlickrResponse {
   @Attribute(name = "stat")
   private String stat;

   @Element(name = "photos")
   private FlickrPage photosList;

   public FlickrPage getPhotosList() {
      return photosList;
   }

   public void setPhotosList(FlickrPage photosList) {
      this.photosList = photosList;
   }

   public String getStat() {
      return stat;
   }

   public void setStat(String stat) {
      this.stat = stat;
   }
}
