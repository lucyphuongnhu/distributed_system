import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Reading2 {
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		// JSONObject
		FileReader fr = new FileReader("./src/Customer2.json");
		JSONObject jo = (JSONObject) new JSONParser().parse(fr);

		//get ID
		long id = (long) jo.get("id");
        System.out.println("ID: " + id);

		//get name
		JSONArray nameArray = (JSONArray) jo.get("names");
		Iterator<String> iterator = nameArray.iterator();
		while (iterator.hasNext()) {
			System.out.println("Name: " + iterator.next());
		}

		//get age
		long age = (long) jo.get("age");
		System.out.println("Age: " + age);

		// getting accounts
		JSONArray accountArray = (JSONArray) jo.get("accounts");
		for (Object accountObject : accountArray){
			Map accountMap = (Map) accountObject;
			System.out.print("Account ID: " + accountMap.get("id") + " - ");
			System.out.println("Balance: " + accountMap.get("balance"));
		}
	}
}