package action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import org.ho.yaml.Yaml;

public class Locatersmap {
	
	HashMap<String, String> nameToTypeMap;
	HashMap<String, String> nameToValueMap;
	
	@SuppressWarnings("unchecked")
	public Locatersmap() throws FileNotFoundException {
		String filePath = "/home/qainfotech/eclipse-workspace/ModularTatoc/src/locator/locators.yml";
		nameToTypeMap = (HashMap<String, String>)Yaml.load(new FileInputStream(filePath));
		
		
		String filePath1 = "/home/qainfotech/eclipse-workspace/ModularTatoc/src/locator/locators1.yml";
		nameToValueMap = (HashMap<String, String>)Yaml.load(new FileInputStream(filePath1));	
	}
	
	
	public String getLocatorType(String LocatorName) {
		return nameToTypeMap.get(LocatorName);
		
	}
	
	public String getLocatorValue(String LocatorName) {
		return nameToValueMap.get(LocatorName);
	}

}
