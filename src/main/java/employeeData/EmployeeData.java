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
		MongoDb mongoObj = new MongoDb();
		// Get auto generated employee id
		empId = mongoObj.getNextSequence("emp_id").toString();
		mongoObj.data.put("_id", empId);
		mongoObj.data.put("name", name);
		mongoObj.data.put("designation", designation);
		mongoObj.data.put("reportingManager", reportingManager);
		mongoObj.data.put("department", department);
		mongoObj.data.put("phone", phone);
		mongoObj.data.put("email", email);
		mongoObj.data.put("location", location);
		mongoObj.insertData("employeedb", "employees");
		// Get the employee data in json format
		String empData = GetJson.getJsonFromMap(mongoObj.data);
		// Set data in redis
		new RedisDb().setRedisData("empId_" + empId, empData);
		return empData;
	}
	
	public String getEmployee()
	{
		String empData = getEmployeeFromRedis();
		if(empData == null)
		{
			empData = getEmployeeFromMongo();
		}
		return empData;
	}
	
	public String getEmployeeFromMongo()
	{
		MongoDb mongoObj = new MongoDb();
		String empData = mongoObj.getMongoData("employeedb", "employees", "_id", empId);
		return empData;
	}
	
	public String getEmployeeFromRedis()
	{
		RedisDb redisObj = new RedisDb();
		String empData = redisObj.getRedisData("empId_"+empId);
		return empData;
	}
}
