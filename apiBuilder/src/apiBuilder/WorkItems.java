package apiBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.example.pepsico.addNewStory.user.UserStory;

public class WorkItems {
//	https://dev.azure.com/PepsiCoIT
	static String ServiceUrl = "https://dev.azure.com/PepsiCoIT/";
//	/Global_Template_PBNA_PBC_Phase0/
	static String TeamProjectName = "Global_Template_PBNA_PBC_Phase0";
	static String UrlEndGetWorkItemById = "/_apis/wit/workitems/";
	static Integer WorkItemId = 5551863;
	static String PAT = "6abjcysr3daolmmjwnukkgtasscpoa7gtq6tx7wu3qb2mg3jgnoa";
	
///Legacy%20Integration/_apis/work/backlogs/Microsoft.RequirementCategory/workItems?api-version=7.1-preview.1
	public static void main(String[] args) {

		System.out.println("please choose you options");
		System.out.println("1. Get Work Item details");
		System.out.println("2. Post Work Item details");
		System.out.println("3. Get all Work Ids details");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		switch (choice) {
		case 1: {
			getworkitems();
			break;
		}
		case 2: {
			setWorkItems();
			break;
		}
		case 3:{
			getAllIds();
			break;
		}

		default:
			System.out.println("/n/nNULL");
		}
	}

//	Url used:-https://dev.azure.com/PepsiCoIT/Global_Template_PBNA_PBC_Phase0/Legacy%20Integration/_apis/work/backlogs/Microsoft.RequirementCategory/workItems?api-version=7.1-preview.1
	// retriving all the data
	@SuppressWarnings("rawtypes")
	public static void getAllIds() {
		try {
			
			String AuthStr = ":" + PAT;
			Base64 base64 = new Base64();

			String encodedPAT = new String(base64.encode(AuthStr.getBytes()));
			String endpoint="/Legacy%20Integration/_apis/work/backlogs"
					+ "/Microsoft.RequirementCategory/workItems?api-version=7.1-preview.1";

			URL url = new URL(ServiceUrl + TeamProjectName + endpoint);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestProperty("Authorization", "Basic " + encodedPAT);
			System.out.println("URL - " + url.toString());
			System.out.println("PAT - " + encodedPAT);
			con.setRequestMethod("GET");
			List<UserStory> us=new ArrayList<>();
			int status = con.getResponseCode();

			if (status == 200) {
				String responseBody;
				try (Scanner scanner = new Scanner(con.getInputStream())) {
					responseBody = scanner.useDelimiter("\\A").next();
					System.out.println(responseBody);
				}

				try {
					Object obj = new JSONParser().parse(responseBody);
					JSONObject jo = (JSONObject) obj;
//					System.out.println(jo.getClass().getName());
					@SuppressWarnings("unchecked")
					List<Object> workItems=(ArrayList<Object>) jo.get("workItems");
					for(Object o:workItems) {
						UserStory us1=new UserStory();
						us1.setId((Long) ( (HashMap) ((HashMap) o).get("target")).get("id"));
						us.add(us1);
					}
						//System.out.println(( (HashMap) ((HashMap) workItems.get(0)).get("target")).get("id").getClass().getName());
					for(UserStory us1:us)
					System.out.println(us1.getId());
				}
				catch(Exception e) {
					e.printStackTrace();
				}

			}
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
	
	//**********Get Work Items**********
	@SuppressWarnings({ "unused", "rawtypes" })
	public static void getworkitems() {
		try {

			String AuthStr = ":" + PAT;
			Base64 base64 = new Base64();

			String encodedPAT = new String(base64.encode(AuthStr.getBytes()));

			URL url = new URL(ServiceUrl + TeamProjectName + UrlEndGetWorkItemById + WorkItemId.toString());
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestProperty("Authorization", "Basic " + encodedPAT);
			System.out.println("URL - " + url.toString());
			System.out.println("PAT - " + encodedPAT);
			con.setRequestMethod("GET");

			int status = con.getResponseCode();

			if (status == 200) {
				String responseBody;
				try (Scanner scanner = new Scanner(con.getInputStream())) {
					responseBody = scanner.useDelimiter("\\A").next();
					System.out.println(responseBody);
				}

				try {
					Object obj = new JSONParser().parse(responseBody);
					JSONObject jo = (JSONObject) obj;
					UserStory us=new UserStory();
					
					String rev = jo.get("_links").toString();
					System.out.println(":- "+jo.getClass().getName());
					@SuppressWarnings("unchecked")
					Map<String, Object> fields = (Map<String, Object>) jo.get("fields");
					@SuppressWarnings("unchecked")
					Map<String, Object> links = (Map<String, Object>) jo.get("_links");
					String jsonUrl = jo.get("url").toString();

					
//					Intialization
					us.setId((Long) jo.get("id"));
					us.setTitle((String)fields.get("System.Title"));
					
//					OutPut
					System.out.println("WorkItemId - " + us.getId());
					System.out.println("WorkItemTitle - " + us.getTitle());
					System.out.println("Work Item Type - " + fields.get("System.WorkItemType"));
					System.out.println("State - " + fields.get("System.State"));
					System.out.println("Tags - " + fields.get("System.Tags"));
					us.setTags(Arrays.asList(((String) fields.get("System.Tags")).split(";")));
					System.out.println(us.getTags());
					System.out.println("Assigned To :-"
							+ ((Map) fields.get("System.AssignedTo")).get("displayName"));
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}

			con.disconnect();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setWorkItems() {
		try {

			String AuthStr = ":" + PAT;
			Base64 base64 = new Base64();

			String encodedPAT = new String(base64.encode(AuthStr.getBytes()));

			URL url = new URL(ServiceUrl + TeamProjectName + UrlEndGetWorkItemById + WorkItemId.toString());
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestProperty("Authorization", "Basic " + encodedPAT);
			System.out.println("URL - " + url.toString());
			System.out.println("PAT - " + encodedPAT);
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			OutputStream os = con.getOutputStream();
//			os.write(POST_PARAMS.getBytes());
//			os.flush();

			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}