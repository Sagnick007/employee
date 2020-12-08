package employeeData;

import java.util.Map;

import employeeDatabases.MongoDb;
import employeeDatabases.RedisDb;
import utility.GetJson;

public class EmployeeData {
	public String empId = "-1";
	public String name = "Employee";
	public String designation = "None";
	public String reportingManager = "None";
	public String department = "None";
	public String phone = "None";
	public String email = "None";
	public String location = "None";
	
	
	public String test() {
		return "Hello " + name + " your id is ";
	}
	
	public String insertEmployee()
	{
		MongoDb mongo_obj = new MongoDb();
		// Get auto generated employee id
		empId = mongo_obj.getNextSequence("emp_id").toString();
	    mongo_obj.data.put("_id", empId);
		mongo_obj.data.put("name", name);
		mongo_obj.data.put("designation", designation);
		mongo_obj.data.put("reportingManager", reportingManager);
		mongo_obj.data.put("department", department);
		mongo_obj.data.put("phone", phone);
		mongo_obj.data.put("email", email);
		mongo_obj.data.put("location", location);
		mongo_obj.insert_data("employeedb", "employees");
		// Get the employee data in json format
		String emp_data = new GetJson().getJsonFromMap(mongo_obj.data);
		// Set data in redis
		new RedisDb().setRedisData("empId_" + empId, emp_data);
		return emp_data;
	}
}
