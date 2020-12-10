package employeeDatabases;

import com.mongodb.*;
import java.util.*;

public class MongoDb {
	public Map<String, String> data = new Hashtable<>();
	
	public void insertData(String database, String collectionName)
	{
	    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	    DB db = mongoClient.getDB(database);
	    DBCollection collection = db.getCollection(collectionName);
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
	
	public String getMongoData(String database, String collectionName, String qKey, String qValue)
	{
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	    // Now connect to your databases
	    DB db = mongoClient.getDB(database);
	    DBCollection collection = db.getCollection(collectionName);
		BasicDBObject whereQuery = new BasicDBObject(qKey, qValue);
	  
	    DBCursor cursor = collection.find(whereQuery);
	    String Data = "";
	    while (cursor.hasNext()) {
	    	Data = cursor.next().toString();
	    }
	    cursor.close();
	    mongoClient.close();
	    return Data;
	}
}
