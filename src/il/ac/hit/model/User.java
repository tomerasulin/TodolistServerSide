package il.ac.hit.model;

/**
 * Mapping a User table to Java object
 */
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String password;

    /**
     * default constructor
     */
    public User(){}

    /**
     * Initializing a new user
     * @param id
     * @param firstName
     * @param lastName
     * @param password
     */
    public User(Long id,String firstName, String lastName,String password) throws ToDoListException {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
    }

    /**
     * get the first name of the user
     * @return String type
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * set the first name of the user with validation
     * @param firstName
     * @throws ToDoListException
     */
    public void setFirstName(String firstName)throws ToDoListException {
        if(firstName.matches("(.*)[0-9]+(.*)")){
            throw new ToDoListException("First Name cannot contain numbers!");
        }else{
            this.firstName = firstName;
        }
    }

    /**
     * get the last name of the user
     * @return String type
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * set the last name of the user include validation
     * @param lastName
     * @throws ToDoListException
     */
    public void setLastName(String lastName) throws ToDoListException {
        if(lastName.matches("(.*)[0-9]+(.*)")){
            throw new ToDoListException("Last Name cannot contain numbers!");
        }else{
            this.lastName = lastName;
        }
    }

    /**
     *  get the user id
     * @return Long type
     */
    public Long getId() {
        return id;
    }

    /**
     * set the user id
     * @param id
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * get the user password
     * @return String type
     */
    public String getPassword() {
        return password;
    }

    /**
     * set user password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
