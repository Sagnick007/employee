package employee_data;

import java.util.Map;
import utility.get_json;
import employee_Databases.mongo_db;
import employee_Databases.redis_db;

public class Employee_data {
	public String emp_id = "-1";
	public String name = "Employee";
	public String designation = "None";
	public String reporting_manager = "None";
	public String department = "None";
	public String phone = "None";
	public String email = "None";
	public String location = "None";
	
	
	public String test() {
		return "Hello " + name + " your id is ";
	}
	
	public String insert_employee()
	{
		mongo_db mongo_obj = new mongo_db();
		// Get auto generated employee id
	    emp_id = mongo_obj.getNextSequence("emp_id").toString();
	    mongo_obj.data.put("_id", emp_id);
		mongo_obj.data.put("name", name);
		mongo_obj.data.put("designation", designation);
		mongo_obj.data.put("reporting_manager", reporting_manager);
		mongo_obj.data.put("department", department);
		mongo_obj.data.put("phone", phone);
		mongo_obj.data.put("email", email);
		mongo_obj.data.put("location", location);
		mongo_obj.insert_data("employeedb", "employees");
		// Get the employee data in json format
		String emp_data = new get_json().get_json_from_map(mongo_obj.data);
		// Set data in redis
		new redis_db().set_redis_data("emp_id_" + emp_id, emp_data);
		return emp_data;
	}
	
	public void get_employee()
	{
		
	}
}
