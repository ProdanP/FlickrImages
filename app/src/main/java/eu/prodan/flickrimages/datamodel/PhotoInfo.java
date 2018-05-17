package eu.prodan.flickrimages.datamodel;

import org.simpleframework.xml.Attribute;

/**
 * Created by User on 17.05.2018.
 */

public class PhotoInfo {
    @Attribute(name = "id")
    String id;
    @Attribute(name = "owner")
    String owner;

    @Attribute (name = "secret")
    String secret;

    @Attribute(name = "server")
    String server;

    @Attribute (name = "farm")
    String farm;

    @Attribute (name = "title")
    String  title;

    @Attribute (name = "ispublic")
    String isPublic;

    @Attribute (name = "isfriend")
    String isFrient;

    @Attribute (name = "isfamily")
    String isFamily;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    public String getIsFrient() {
        return isFrient;
    }

    public void setIsFrient(String isFrient) {
        this.isFrient = isFrient;
    }

    public String getIsFamily() {
        return isFamily;
    }

    public void setIsFamily(String isFamily) {
        this.isFamily = isFamily;
    }

    public String getImageUrl(){
        return "https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg"
                .replace("{farm-id}", farm)
                .replace("{server-id}", server)
                .replace("{id}", id)
                .replace("{secret}", secret);
    }
}

