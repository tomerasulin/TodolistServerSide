package il.ac.hit.controller;

import il.ac.hit.model.HibernateToDoListDAO;
import il.ac.hit.model.Item;
import il.ac.hit.model.ToDoListException;
import il.ac.hit.model.User;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * this class handle all the POST and GET request from the client
 */
public class UserController {
    private HibernateToDoListDAO hibernateToDoListDAO = HibernateToDoListDAO.getInstance();
    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    /**
     * check if user exist in DB and login in case it is
     *
     * @param request
     * @param response
     * @throws ToDoListException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ToDoListException {
        //getting the parameters from the request
        String userId = request.getParameter("id");
        String password = request.getParameter("password");
        if (userId != null || password != null) {
            try {
                Long id = Long.parseLong(userId);
                User user = hibernateToDoListDAO.getUser(id);//check if user exist already in DB
                if (user != null) {
                    if (user.getPassword().equals(password)) {//case user and password match
                        request.setAttribute("next", "next");
                    } else {//case of wrong password
                        LOGGER.debug("Login invalid:Password");
                        request.setAttribute("error", "Wrong Password! try again.");
                    }
                } else {//user entered id that doesnt exists in the DB
                    LOGGER.debug("Login invalid:ID");
                    request.setAttribute("error", "ID doesnt exists! Please Signup.");
                }
            } catch (ToDoListException e) {
                request.setAttribute("error", e.getMessage());
            } catch (NumberFormatException e) {
                request.setAttribute("error", "id format wrong");
            }
            //setting a cookie for the user that just logged in
            Cookie cookie = new Cookie("id", request.getParameter("id"));
            cookie.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(cookie);
        }
    }

    /**
     * sign up a new user into the DB
     *
     * @param request
     * @param response
     * @throws ToDoListException
     */
    public void signup(HttpServletRequest request, HttpServletResponse response) throws ToDoListException {
        //getting the parameters from the request
        String userId = request.getParameter("id");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        if (userId != null && firstName != null && lastName != null && password != null) {
            try {
                Long id = Long.parseLong(userId);
                User user = hibernateToDoListDAO.getUser(id);
                if (user != null) {
                    LOGGER.debug("Signup invalid:Id already exists");
                    request.setAttribute("id", "ID already exists");
                } else {
                    User usertoadd = new User(id, firstName, lastName, password);//creating a new user to add to DB
                    hibernateToDoListDAO.add(usertoadd);
                    request.setAttribute("next", "next");
                }
            } catch (ToDoListException e) {
                request.setAttribute("error", e.getMessage());
            } catch (NumberFormatException e) {
                request.setAttribute("error", "id cannot contain letters");
            }
            //setting a cookie for the user that just signed up
            Cookie cookie = new Cookie("id", request.getParameter("id"));
            cookie.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(cookie);
        }
    }

    /**
     * present the items of the specific user from the DB
     *
     * @param request
     * @param response
     * @throws ToDoListException
     */

    public void itemlist(HttpServletRequest request, HttpServletResponse response) throws ToDoListException {
        String serial = request.getParameter("serial");
        String item = request.getParameter("item");
        String date = request.getParameter("date");
        String logout = request.getParameter("id");
        Cookie cookie = null;
        Cookie[] cookies = null;
        cookies = request.getCookies();
        Long userId = null;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if (cookie.getName().equals("id")) {
                    userId = Long.parseLong(cookie.getValue());
                    try {
                        User user = hibernateToDoListDAO.getUser(userId);
                        if (user != null) {
                            Item[] itemList = hibernateToDoListDAO.getItems(userId);
                            request.setAttribute("items", itemList);
                            request.setAttribute("user", user);
                            cookie.setComment(user.getFirstName());
                            break;
                        }
                    } catch (ToDoListException e) {
                        e.getMessage();
                    }
                }
            }
        }
        if (item != null && date != null) {
            userId = getUserID(request, response);
            try {
                Item[] items = hibernateToDoListDAO.getItems(userId);
                for (int i = 0; i < items.length; i++) {
                    if (items[i].getName().equals(item) && items[i].getDate().equals(date)) {
                        request.setAttribute("name", items[i].getName());
                        request.setAttribute("date", items[i].getDate());
                        request.setAttribute("serial", items[i].getSerialNum());
                        request.setAttribute("edit", "true");
                        break;
                    }
                }
            } catch (ToDoListException e) {
                e.getMessage();
            }
        }

        if (serial != null) {
            userId = getUserID(request, response);
            try {
                Item[] items = hibernateToDoListDAO.getItems(userId);
                for (int i = 0; i < items.length; i++) {
                    if (items[i].getSerialNum() == Integer.parseInt(serial)) {
                        hibernateToDoListDAO.delete(items[i]);
                        request.setAttribute("delete", "delete");
                        break;
                    }
                }
            } catch (ToDoListException e) {
                e.getMessage();
            }
        }
        if (logout != null) {
            request.setAttribute("logout", "logout");
            request.getSession().invalidate();
        }
    }

    /**
     * adding a new item to the DB
     *
     * @param request
     * @param response
     * @throws ToDoListException
     */
    public void addItem(HttpServletRequest request, HttpServletResponse response) throws ToDoListException {
        String item = request.getParameter("item");
        String date = request.getParameter("date");

        Long userId = getUserID(request, response);
        boolean added = item != null && date != null;
        if (added) {
            try {
                Item itemToAdd = new Item(item, date, userId);
                hibernateToDoListDAO.add(itemToAdd);
                request.setAttribute("back", true);
            } catch (ToDoListException e) {
                e.getMessage();
            }
        }
    }

    /**
     * update the item the user choose to update
     *
     * @param request
     * @param response
     * @throws ToDoListException
     */
    public void updateItem(HttpServletRequest request, HttpServletResponse response) throws ToDoListException {
        String name = request.getParameter("name");
        String date = request.getParameter("date");
        String serial = request.getParameter("serial");

        String newItem = request.getParameter("newitem");
        String newDate = request.getParameter("newdate");

        Long userId = getUserID(request, response);

        if (newItem != null && newDate != null) {
            try {
                Item[] items = hibernateToDoListDAO.getItems(userId);
                for (int i = 0; i < items.length; i++) {
                    if (items[i].getSerialNum() == Integer.parseInt(serial)) {
                        Item itemToUpdate = new Item(items[i].getSerialNum(), newItem, newDate, userId);
                        hibernateToDoListDAO.update(itemToUpdate);
                        request.setAttribute("back", "back");
                        break;
                    }
                }
            } catch (ToDoListException e) {
                e.getMessage();
            }
        } else {
            request.setAttribute("item", name);
            request.setAttribute("date", date);
            request.setAttribute("serial", serial);
        }
    }

    /**
     * this function return the current user id in the session
     *
     * @param request
     * @param response
     * @return Long id
     */
    Long getUserID(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = null;
        Cookie[] cookies = null;
        cookies = request.getCookies();
        Long userId = null;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if (cookie.getName().equals("id")) {
                    userId = Long.parseLong(cookie.getValue());
                    break;
                }
            }
        }
        return userId;
    }

}
