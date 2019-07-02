package il.ac.hit.model;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;

import java.util.List;
/**
 * this class is layer that communicate with the DB
 *
 * @param <T> - generic type
 */
public class HibernateToDoListDAO<T> implements IToDoListDAO<T> {
    private static HibernateToDoListDAO instance = null;
    private SessionFactory factory;

    /**
     * Constructor - creating a sessionFactory one and only
     */
    private HibernateToDoListDAO() {
        factory = new AnnotationConfiguration().configure().buildSessionFactory();
    }

    //implemention of singleton
    public static HibernateToDoListDAO getInstance() {
        if (instance == null) {
            instance = new HibernateToDoListDAO();
        }
        return instance;
    }

    /**
     * add a T type of Item or User to the DB
     *
     * @param ob
     * @throws ToDoListException
     */
    @Override
    public void add(T ob) throws ToDoListException {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.save(ob);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new ToDoListException("Cant add the item into the DB", e);
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                e.getMessage();
            }
        }
    }

    /**
     * update T type of User or Item in the DB
     *
     * @param ob
     * @throws ToDoListException
     */
    @Override
    public void update(T ob) throws ToDoListException {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.update(ob);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new ToDoListException("Cant update the item in the DB", e);
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                e.getMessage();
            }
        }
    }

    /**
     * delete T type of Item or User from the DB
     * * @param ob
     *
     * @throws ToDoListException
     */
    @Override
    public void delete(T ob) throws ToDoListException {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.delete(ob);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new ToDoListException("Cant delete the item from the DB", e);
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                e.getMessage();
            }
        }
    }

    /**
     * get all items from the DB which connceted to the user with the specific id
     *
     * @param id
     * @return array of items
     * @throws ToDoListException
     */
    @Override
    public Item[] getItems(Long id) throws ToDoListException {
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(Item.class);
        Item[] items = null;
        try {
            session.beginTransaction();
            List<Item> itemList = criteria.add(Restrictions.eq("userID", id)).list();
            items = new Item[itemList.size()];
            items = itemList.toArray(items);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new ToDoListException("cant get the items from the DB", e);
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                e.getMessage();
            }
        }
        return items;
    }

    /**
     * get a user from the DB
     *
     * @param id
     * @return User object
     * @throws ToDoListException
     */
    @Override
    public User getUser(Long id) throws ToDoListException {
        Session session = factory.openSession();
        User user = null;
        try {
            session.beginTransaction();
            user = (User) session.get(User.class, id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new ToDoListException("cant get the items from the DB", e);
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                e.getMessage();
            }
        }

        return user;
    }

}
