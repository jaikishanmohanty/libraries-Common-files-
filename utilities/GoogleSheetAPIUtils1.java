package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.auth.AuthenticationException;
import org.eclipse.jetty.util.log.Log;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Data;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesResponse;
import com.google.api.services.sheets.v4.model.CellData;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import com.google.api.services.sheets.v4.model.GridCoordinate;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.RowData;
import com.google.api.services.sheets.v4.model.UpdateCellsRequest;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.gdata.client.spreadsheet.ListQuery;
import com.google.gdata.client.spreadsheet.SpreadsheetQuery;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;
import com.google.gdata.util.ServiceException;

public class GoogleSheetAPIUtils1 {
	private static final String APPLICATION_NAME = "Login from Google Sheets";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    public static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    
    private static Sheets sheetsService;
    
   /* private static HttpTransport transport;
    private static JacksonFactory jsonFactory;
    private static FileDataStoreFactory dataStoreFactory;
    private static List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);
    */
    
    
    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = GoogleSheetAPIUtils.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public String[][] readDataFromGoogleSheet(String spreadsheetId, String range) throws IOException, GeneralSecurityException {
    	// Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
        List<List<Object>> values = response.getValues();
        String [][] sheetData = new String[values.size()][];
        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
        } else {
        	for(int i = 0; i < values.size(); i++) {
                List<Object> value = values.get(i);
        		/*System.out.println("Values Size:11111111111"+values.size());
        		System.out.println("Values of get(i):2222222222222222222"+value);
        		System.out.println("Values arg 0 index:"+values.get(0));
        		System.out.println("No of columns:"+value.size());*/
                sheetData[i] = new String[value.size()];
                for(int j = 0; j < value.size(); j++) {
                	if(value.get(j).toString().isEmpty()) {
                		sheetData[i][j] = "";
                	}else {
                	sheetData[i][j] = (String) value.get(j);
                	}
                }
            }
        }
        return sheetData;
    }
   
    
    public void WriteExample(String spreadsheetId) throws IOException, GeneralSecurityException {
   	 final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        
    /*List<Request> requests = new ArrayList<>();
         List<CellData> values = new ArrayList<>();
         values.add(new CellData()
                   .setUserEnteredValue(new ExtendedValue()
                           .setStringValue("Hello World!")));
         values.add(new CellData()
                 .setUserEnteredValue(new ExtendedValue()
                         .setStringValue("Hello World!")));
         values.add(new CellData()
                 .setUserEnteredValue(new ExtendedValue()
                         .setStringValue("Hello World!")));
         System.out.println("Size Of values"+values.size());
           requests.add(new Request()
                   .setUpdateCells(new UpdateCellsRequest()
                           .setStart(new GridCoordinate()
                                   .setSheetId(0)
                                   .setRowIndex(1)
                                   .setColumnIndex(0))
                           .setRows(Arrays.asList(
                                   new RowData().setValues(values)))
                           .setFields("userEnteredValue,userEnteredFormat.backgroundColor")));
           System.out.println("Size of request"+requests.size());
           
            BatchUpdateSpreadsheetRequest batchUpdateRequest = new BatchUpdateSpreadsheetRequest()
                   .setRequests(requests);
           service.spreadsheets().batchUpdate(spreadsheetId, batchUpdateRequest)
                   .execute();*/
         
         List<ValueRange> data = new ArrayList<>();
           data.add(new ValueRange()
             .setRange("D1")
             .setValues(Arrays.asList(
               Arrays.asList("January Total", "=B2+B3"))));
           data.add(new ValueRange()
             .setRange("D4")
             .setValues(Arrays.asList(
               Arrays.asList("February Total", "=B5+B6"))));
           BatchUpdateValuesRequest batchBody = new BatchUpdateValuesRequest()
             .setValueInputOption("USER_ENTERED")
             .setData(data);
           BatchUpdateValuesResponse batchResult = service.spreadsheets().values()
             .batchUpdate(spreadsheetId, batchBody)
             .execute();
            
       }
	  public void writeDataInGoogleSpreadSheet(String spreadsheetId,String sheetName,String colName,String rowNo,String value) throws IOException, GeneralSecurityException {
	    	
		  final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
	    	Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
	    			.setApplicationName(APPLICATION_NAME)
	    			.build();
	    	List<ValueRange> data = new ArrayList<>();
	    	/*data.add(new ValueRange()
	    			.setRange(colName+rowNo)
	    			.setValues(Arrays.asList(
	    					Arrays.asList(value))));*/
	    	data.add(new ValueRange()
	    			.setRange(sheetName+"!"+colName+rowNo)
	    			.setValues(Arrays.asList(
	    					Arrays.asList(value))));
	    	BatchUpdateValuesRequest batchBody = new BatchUpdateValuesRequest()
	    			.setValueInputOption("USER_ENTERED")
	    			.setData(data);
	    	BatchUpdateValuesResponse batchResult = service.spreadsheets().values()
	    			.batchUpdate(spreadsheetId, batchBody)
	    			.execute();
	
	    }
	  public static void writeDataToGoogleSheet(String spreadsheetId, String range) throws GeneralSecurityException, IOException {


		  final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		  Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				  .setApplicationName(APPLICATION_NAME)
				  .build();
		  String valueInputOption = "RAWS";
		  List<List<Object>> values = Arrays.asList(
				  Arrays.asList("Value 11100"), 
				  Arrays.asList("Value 22200", "Value 32200"), 
				  Arrays.asList("Value 42200", "Value 52002"),
				  Arrays.asList("Value 6200"));

		  ValueRange requestBody = new ValueRange(); 
		  requestBody.setValues(values);
		  requestBody.setRange(range);
		  requestBody.setMajorDimension("ROWS");

		  System.out.println(requestBody);

		  UpdateValuesResponse result =
				  service.spreadsheets().values().update(spreadsheetId, range, requestBody)
				  .setValueInputOption(valueInputOption)
				  .execute();
		  System.out.printf("%d cells updated.", result.getUpdatedCells());
	  }

    
    
   /* public static Sheets getSheetsService() throws IOException {
	    Credential credential = authorize();
	    return new Sheets.Builder(transport, jsonFactory, credential)
	            .setApplicationName("INSERT_YOUR_APPLICATION_NAME")
	            .build();
	}*/
  /* public void WriteExample(String spreadsheetId) throws IOException {
        Sheets service = getSheetsService();
        List<Request> requests = new ArrayList<>();

          List<CellData> values = new ArrayList<>();


          values.add(new CellData()
                    .setUserEnteredValue(new ExtendedValue()
                            .setStringValue("Hello World!")));
            requests.add(new Request()
                    .setUpdateCells(new UpdateCellsRequest()
                            .setStart(new GridCoordinate()
                                    .setSheetId(0)
                                    .setRowIndex(0)
                                    .setColumnIndex(0))
                            .setRows(Arrays.asList(
                                    new RowData().setValues(values)))
                            .setFields("userEnteredValue,userEnteredFormat.backgroundColor")));

            BatchUpdateSpreadsheetRequest batchUpdateRequest = new BatchUpdateSpreadsheetRequest()
                    .setRequests(requests);
            service.spreadsheets().batchUpdate(spreadsheetId, batchUpdateRequest)
                    .execute();
        }*/
   /*public static Credential authorize() throws IOException {
	    // Load client secrets.
	    File cfile = new File(System.getProperty("user.dir") + "/src/credentials.json");
	    cfile.createNewFile();
	    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(new FileInputStream(cfile)));

	    // Build flow and trigger user authorization request.
	    GoogleAuthorizationCodeFlow flow =
	            new GoogleAuthorizationCodeFlow.Builder(
	                    transport, jsonFactory, clientSecrets, scopes)
	                    .setDataStoreFactory(dataStoreFactory)
	                    .setAccessType("offline")
	                    .build();
	    Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
	    return credential;
	}

	*/

  /*public void writeSomething(List<Data> myData) {
	  final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
      Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
              .setApplicationName(APPLICATION_NAME)
              .build();
	    try {
	        String id = "INSERT_SHEET_ID";
	        String writeRange = "INSERT_SHEET_NAME!A3:E";

	        List<List<Object>> writeData = new ArrayList<>();
	        for (Data someData: myData) {
	            List<Object> dataRow = new ArrayList<>();
	            dataRow.add(someData.data1);
	            dataRow.add(someData.data2);
	            dataRow.add(someData.data3);
	            dataRow.add(someData.data4);
	            dataRow.add(someData.data5);
	            writeData.add(dataRow);
	        }

	        ValueRange vr = new ValueRange().setValues(writeData).setMajorDimension("ROWS");
	        service.spreadsheets().values()
	                .update(id, writeRange, vr)
	                .setValueInputOption("RAW")
	                .execute();
	    } catch (Exception e) {
	        // handle exception
	    }
  }*/
  
   /*public void writeDataToGoogleSheet() throws GeneralSecurityException, IOException, ServiceException
    {
	   
	SpreadsheetService service = new SpreadsheetService("MySpreadsheetIntegration-v1");
    	// Put the path of p12 or json  file that is downloaded from Google Console
    	File p12 = new File("C:/Users/priyankag/WorkSpace/SCMS_Project/src/credentials.json");
    	HttpTransport httpTransport = new NetHttpTransport();
    	JacksonFactory jsonFactory = new JacksonFactory();
    	String[] SCOPESArray = {
    			"https://spreadsheets.google.com/feeds", "https://spreadsheets.google.com/feeds/spreadsheets/private/full",
    			"https://docs.google.com/feeds"
    	};
    	final List SCOPES = Arrays.asList(SCOPESArray);
    	//Put your google service account id
    	GoogleCredential credential =
    			new GoogleCredential.Builder().setTransport(httpTransport).setJsonFactory(jsonFactory).setServiceAccountId("xxxxxxx.iam.gserviceaccount.com").setServiceAccountScopes(SCOPES).setServiceAccountPrivateKeyFromP12File(p12).build();
    	service.setOAuth2Credentials(credential);
    	// Define the URL to request.  This should never change.
    	URL SPREADSHEET_FEED_URL =
    			new URL("https://spreadsheets.google.com/feeds/worksheets/1GecUtseIFRdvH_YXRgk_mgH459iaFs-JX-8j7IyDFP0/private/full");
    	// Make a request to the API and get all spreadsheets.
    	SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL, SpreadsheetFeed.class);
    	List<SpreadsheetEntry> spreadsheets = feed.getEntries();
    	System.out.println("Total Sheets- " + spreadsheets.size());
    	if (spreadsheets.size() == 0) {
    	}
    	SpreadsheetEntry spreadsheet = spreadsheets.get(0);
    	System.out.println(spreadsheet.getTitle().getPlainText());
    	// Get the first worksheet of the first spreadsheet.
    	WorksheetFeed worksheetFeed = service.getFeed(spreadsheet.getWorksheetFeedUrl(), WorksheetFeed.class);
    	List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
    	WorksheetEntry worksheet = worksheets.get(0);
    	System.out.println(worksheet.getTitle().getPlainText());
    	// Create a local representation of the new row.
    	ListEntry row = new ListEntry();
    	row.getCustomElements().setValueLocal("Cell1", "Testing");
    	row.getCustomElements().setValueLocal("Cell2", "New Data");
    	// Send the new row to the API for insertion.
    	row = service.insert(spreadsheet.getWorksheetFeedUrl(), row);
    }*/
			 private static final String SPREADSHEET_SERVICE_URL = "https://spreadsheets.google.com/feeds/spreadsheets/private/full";
			 SpreadsheetService service = new SpreadsheetService("spreadsheetservice");
			
			 
			 private SpreadsheetEntry getSpreadsheet(String sheetName) {
				 try {
					 URL spreadSheetFeedUrl = new URL(SPREADSHEET_SERVICE_URL);
			
					 SpreadsheetQuery spreadsheetQuery = new SpreadsheetQuery(
							 spreadSheetFeedUrl);
					 spreadsheetQuery.setTitleQuery(sheetName);
					 spreadsheetQuery.setTitleExact(true);
					 SpreadsheetFeed spreadsheet = service.getFeed(spreadsheetQuery,
							 SpreadsheetFeed.class);
			
					 if (spreadsheet.getEntries() != null
							 && spreadsheet.getEntries().size() == 1) {
						 return spreadsheet.getEntries().get(0);
					 } else {
						 return null;
					 }
				 } catch (Exception ex) {
				 }
			
			
				 return null;
			 }

			 private Map<String, Object> getRowData(ListEntry row) {
				 Map<String, Object> rowValues = new HashMap<String, Object>();
				 for (String tag : row.getCustomElements().getTags()) {
					 Object value = row.getCustomElements().getValue(tag);
					 rowValues.put(tag, value);
				 }
				 return rowValues;
			 }
			 private WorksheetEntry getWorkSheet(String sheetName, String workSheetName) {
				 try {
					 SpreadsheetEntry spreadsheet = getSpreadsheet(sheetName);
			
					 if (spreadsheet != null) {
						 WorksheetFeed worksheetFeed = service.getFeed(
								 spreadsheet.getWorksheetFeedUrl(), WorksheetFeed.class);
						 List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
			
						 for (WorksheetEntry worksheetEntry : worksheets) {
							 String wktName = worksheetEntry.getTitle().getPlainText();
							 if (wktName.equals(workSheetName)) {
								 return worksheetEntry;
							 }
						 }
					 }
				 } catch (Exception ex) {
			
				 }
			
				 return null;
			 }
			
			 private ListEntry createRow(Map<String, Object> rowValues) {
				 ListEntry row = new ListEntry();
				 for (String columnName : rowValues.keySet()) {
					 Object value = rowValues.get(columnName);
					 row.getCustomElements().setValueLocal(columnName,
							 String.valueOf(value));
				 }
				 return row;
			 }
			 private void updateRow(ListEntry row, Map<String, Object> rowValues) {
				 for (String columnName : rowValues.keySet()) {
					 Object value = rowValues.get(columnName);
					 row.getCustomElements().setValueLocal(columnName,
							 String.valueOf(value));
				 }
			 }
			
			 private ListFeed getListFeedForRow(String sheetName, String workSheetName,
					 String pkColumnName, Object pkColumnValue) throws Exception {
				 WorksheetEntry worksheet = getWorkSheet(sheetName, workSheetName);
				 URL listFeedUrl = worksheet.getListFeedUrl();
				 ListQuery listQuery = new ListQuery(listFeedUrl);
				 if (pkColumnValue instanceof String) {
					 listQuery.setSpreadsheetQuery(pkColumnName + " = \"" + pkColumnValue + "\"");
				 } else {
					 listQuery.setSpreadsheetQuery(pkColumnName + " = " + pkColumnValue);
				 }
			
				 return service.query(listQuery, ListFeed.class);
			 }
			 private ListEntry getRow(String sheetName, String workSheetName,
					 String pkColumnName, Object pkColumnValue) throws Exception {
				 ListFeed listFeed = getListFeedForRow(sheetName, workSheetName,
						 pkColumnName, pkColumnValue);
				 if (listFeed.getEntries().size()==1) {
					 return listFeed.getEntries().get(0);
				 } else {
					 throw new Exception("Can't find row with id "+pkColumnName+" = "+pkColumnValue);
				 }
			
			 }
			
			 /* ListEntry row = getRow(sheetName, workSheetName,
			         pkColumnName, pkColumnValue);
			updateRow(row, rowValues);
			row.update();*/
}

