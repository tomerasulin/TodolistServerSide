package il.ac.hit.model;
/**
 * In an application the Data Access Object (DAO) is a part of Data access layer. It is an object
 * that provides an interface to some type of persistence mechanism. By mapping application calls
 * to the persistence layer, DAO provides some specific data operations without exposing details
 * of the database. This isolation supports the Single responsibility principle. It separates what
 * data accesses the application needs, in terms of domain-specific objects and data types
 * (the public interface of the DAO), from how these needs can be satisfied with a specific DBMS,
 * database schema, etc.
 *
 * <p> Any change in the way data is stored and retrieved will not change the client code as the
 * client will be using interface and need not worry about exact source.
 *
 * @param <T> generic both for User and Item
 */
public interface IToDoListDAO<T> {
    /**
     * generic - add an object type of Item or User to the DB
     * @param ob
     * @throws ToDoListException
     */
    public void add(T ob) throws ToDoListException;

    /**
     * generic - update an object type of Item or user in the DB
     * @param ob
     * @throws ToDoListException
     */
    public void update(T ob) throws ToDoListException;

    /**
     * generic - delete an object type of Item or User from the DB
     * @param ob
     * @throws ToDoListException
     */
    public void delete(T ob) throws ToDoListException;

    /**
     * get all the items in the DB which connceted to the specific user with the exact id
     * @param id
     * @return array of items from the DB
     * @throws ToDoListException
     */
    public Item[] getItems(Long id) throws ToDoListException;

    /**
     * get the user from the DB with the same id
     * @param id
     * @return User object
     * @throws ToDoListException
     */
    public User getUser(Long id) throws ToDoListException;

}
