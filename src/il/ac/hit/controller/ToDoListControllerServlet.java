package il.ac.hit.controller;


import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * this class functioning as a Router that handle each HTTP request from the web
 */
@WebServlet(name = "ToDoListControllerServlet",urlPatterns = {"/todolistcontroller/*"})
public class ToDoListControllerServlet extends HttpServlet {
    private static String pkg = "il.ac.hit.controller";
    private static final Logger LOGGER = Logger.getLogger(ToDoListControllerServlet.class);
    public ToDoListControllerServlet() {
        super();
    }

    /**
     * called by the server to allow a sevlet to handle POST requests
     * The HTTP POST method allows the client to send data to the server
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Called by the server to allow a servlet to handle a GET requests
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str = request.getPathInfo();
        String vec[] = str.split("/");
        String controller = vec[1];
        String action = vec[2];

        //composing the controller class name
        String controllerClassName = controller.substring(0,1).toUpperCase()+ controller.substring(1)+"Controller";
        try {
            Class clazz = Class.forName(pkg+"."+controllerClassName);
            Object object = clazz.newInstance();
            Method method = clazz.getMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(object,request,response);
            LOGGER.info("GET:controller-"+str);
            getServletContext().getRequestDispatcher("/"+action+".jsp").forward(request,response);


        }catch (ClassNotFoundException| InstantiationException |IllegalAccessException| NoSuchMethodException| InvocationTargetException e){
            e.getMessage();
        }
    }
}
