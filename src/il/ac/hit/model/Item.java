package il.ac.hit.model;

/**
 * Mapping an Item table to Java object
 */
public class Item {

    private int serialNum;
    private String date;
    private String name;
    private Long userID;

    /**
     * default constructor
     */
    public Item(){}

    /**
     * Initializing the item
     * @param name
     * @param date
     * @param userID
     */
    public Item(String name, String date,Long userID) {
        setName(name);
        setDate(date);
        setUserID(userID);
    }

    /**
     * Initializing the item with a user ID
     * @param serialNum
     * @param name
     * @param date
     * @param userID
     */
    public Item(int serialNum,String name, String date,Long userID) {
        setSerialNum(serialNum);
        setName(name);
        setDate(date);
        setUserID(userID);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(int serialNum) {
        this.serialNum = serialNum;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getDate() { return date; }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Date: "+getDate()+" Name: "+getName();
    }
}
