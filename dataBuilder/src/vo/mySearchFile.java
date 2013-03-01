package vo;

public class mySearchFile {

    private String id;
    private String attachmentId;
    private String title;   
    private String tag;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        System.out.println("id:" + this.id);
        System.out.println("attachmentId:" + this.attachmentId);
        System.out.println("tag:" + this.tag);
        System.out.println("content:" + this.content);
        System.out.println("type:" + this.type);
        return "";
    }
}
