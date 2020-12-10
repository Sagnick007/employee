package employee.Employee;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.pippo.core.Application;
import ro.pippo.core.route.TrailingSlashHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import employeeData.EmployeeData;
import utility.PatternChecker;

/**
 * A simple Pippo application.
 *
 * @see employee.Employee.PippoLauncher#main(String[])
 */
public class PippoApplication extends Application {

    private final static Logger log = LoggerFactory.getLogger(PippoApplication.class);

    @Override
    protected void onInit() {
    	// add trailing slash filter
    	ANY("/.*", new TrailingSlashHandler(false)); // remove trailing slash
        getRouter().ignorePaths("/favicon.ico");

        // send 'Hello World' as response
        GET("/", routeContext -> routeContext.send("Hello World"));

        // send a template as response
        GET("/template", routeContext -> {
            String message;

            String lang = routeContext.getParameter("lang").toString();
            if (lang == null) {
                message = getMessages().get("pippo.greeting", routeContext);
            } else {
                message = getMessages().get("pippo.greeting", lang);
            }

            routeContext.setLocal("greeting", message);
            routeContext.render("hello");
        });
        
        GET("/list_employee", routeContext -> {
        	routeContext.render("list_employee");
        });
        
        GET("/employee/{id}", routeContext -> {
            String id = routeContext.getParameter("id").toString();
            String db = routeContext.getParameter("db").toString();
            db = (db == null)?"":db;
            if(PatternChecker.checkDigit(id))
            {
            	EmployeeData empObj = new EmployeeData();
            	empObj.empId = id;
            	String empData = null;
            	if(!db.equals("redis"))
            	{
            		empData = empObj.getEmployee();
            	}
            	else
            	{
            		empData = empObj.getEmployeeFromRedis();
            	}
            	if(empData != null)
            	{
            		routeContext.json().send(empData);
            	}
            }
            routeContext.send("No employee with id:" + id + " is registered.");
        });
        
        POST("/employee", routeContext -> {
        	EmployeeData empObj = new EmployeeData();
        	empObj.name = routeContext.getParameter("name").toString();
        	empObj.designation = routeContext.getParameter("designation").toString();
        	empObj.reportingManager = routeContext.getParameter("reportingManager").toString();
        	empObj.department = routeContext.getParameter("department").toString();
        	empObj.phone = routeContext.getParameter("phone").toString();
        	empObj.email = routeContext.getParameter("email").toString();
        	empObj.location = routeContext.getParameter("location").toString();
        	String empData = empObj.insertEmployee();
        	
        	routeContext.json().send(empData);
        });
    }
}
