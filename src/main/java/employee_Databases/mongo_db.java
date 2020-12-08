package employee_Databases;

import com.mongodb.*;
import java.util.*;

public class mongo_db {
	public Map<String, String> data = new Hashtable<>();
	
	public void insert_data(String database, String collection_name)
	{
	    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	    DB db = mongoClient.getDB(database);
	    DBCollection collection = db.getCollection(collection_name);
	    BasicDBObject document = new BasicDBObject();
	    data.forEach((k, v) -> {   
	    	document.put(k, v.toString());
        }); 
	    collection.insert(document);
	    mongoClient.close();
	}
	
	public String getNextSequence(String name)
	{
	    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	    // Now connect to your databases
	    DB db = mongoClient.getDB("employeedb");
	    DBCollection collection = db.getCollection("counters");
	    BasicDBObject find = new BasicDBObject();
	    find.put("_id", name);
	    BasicDBObject update = new BasicDBObject();
	    update.put("$inc", new BasicDBObject("seq", 1));
	    DBObject obj =  collection.findAndModify(find, update);
	    mongoClient.close();
	    // Getting integer sequence
	    String seq = obj.get("seq").toString();
	    seq = seq.split("[.]")[0];
	    return seq;
	}
}
