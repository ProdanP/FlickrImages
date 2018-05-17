package eu.prodan.flickrimages.datamodel;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by User on 16.05.2018.
 */
@Root (name = "photos")
public class FlickrPage {
   @Attribute(name = "page")
   private String page;

   @Attribute(name = "pages")
    private String pages;

   @Attribute(name = "perpage")
    private String perPage;

   @Attribute(name = "total")
    private String total;

   @ElementList(entry = "photo", inline = true)
    private List<PhotoInfo> photoInfoList;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPerPage() {
        return perPage;
    }

    public void setPerPage(String perPage) {
        this.perPage = perPage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<PhotoInfo> getPhotoInfoList() {
        return photoInfoList;
    }

    public void setPhotoInfoList(List<PhotoInfo> photoInfoList) {
        this.photoInfoList = photoInfoList;
    }
}
