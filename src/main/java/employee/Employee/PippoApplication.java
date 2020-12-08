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
        
        // GET("/employee/{id}", routeContext -> routeContext.send("Hello World"));
        GET("/employee/{id}", routeContext -> {
            String id = routeContext.getParameter("id").toString(); // read parameter "id"
            if(new PatternChecker().checkDigit(id))
            {
            	EmployeeData emp_obj = new EmployeeData();
            	emp_obj.empId = id;
            }
            else
            {
            	routeContext.send("No employee with id:" + id + " is registered.");
            }
        });
        
        POST("/employee", routeContext -> {
        	EmployeeData emp_obj = new EmployeeData();
        	emp_obj.name = routeContext.getParameter("name").toString();
        	emp_obj.designation = routeContext.getParameter("designation").toString();
        	emp_obj.reportingManager = routeContext.getParameter("reportingManager").toString();
        	emp_obj.department = routeContext.getParameter("department").toString();
        	emp_obj.phone = routeContext.getParameter("phone").toString();
        	emp_obj.email = routeContext.getParameter("email").toString();
        	emp_obj.location = routeContext.getParameter("location").toString();
        	String emp_data = emp_obj.insertEmployee();
        	
        	routeContext.json().send(emp_data);
        });
    }
}
