package action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import org.ho.yaml.Yaml;

public class YamlReader {

    HashMap< String,ArrayList<String> > mainMap;
	
	@SuppressWarnings("unchecked")
	public YamlReader() throws FileNotFoundException {

		
		String filePath1 = "/home/qainfotech/eclipse-workspace/ModularTatoc/src/locator/joinedLocator.yml";
		mainMap = (HashMap<String, ArrayList<String> >)Yaml.load(new FileInputStream(filePath1));
	}
	
	
	public String getLocatorType(String LocatorName) {
		
		return mainMap.get(LocatorName).get(0);
		
	}
	
	public String getLocatorValue(String LocatorName) {
		
		return mainMap.get(LocatorName).get(1);
	}
	
}
