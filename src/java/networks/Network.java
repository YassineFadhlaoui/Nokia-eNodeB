package networks;

/**
 *
 * @author yassine
 */
public class Network {
    private String name;
    public String SegmentationID;
    private String Type;
    private String Status;
    private String id;

    public Network(String name, String SegmentationID, String Type, String Status, String id) {
        this.name = name;
        this.SegmentationID = SegmentationID;
        this.Type = Type;
        this.Status = Status;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setSegmentationID(String SegmentationID) {
        this.SegmentationID = SegmentationID;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getName() {
        return name;
    }

    public String getSegmentationID() {
        return SegmentationID;
    }

    public String getType() {
        return Type;
    }

    public String getStatus() {
        return Status;
    }

    @Override
    public String toString() {
        return "Network{" + "name=" + name + ", SegmentationID=" + SegmentationID + ", Status=" + Status + ", id=" + id + '}';
    }
//overload constructor
    public Network(String name, String SegmentationID, String Type, String Status) {
        this.name = name;
        this.SegmentationID = SegmentationID;
        this.Type = Type;
        this.Status = Status;
    }

    

}
