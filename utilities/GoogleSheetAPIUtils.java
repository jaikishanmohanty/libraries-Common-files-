package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesResponse;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

public class GoogleSheetAPIUtils {
	private static final String APPLICATION_NAME = "Login from Google Sheets";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final String TOKENS_DIRECTORY_PATH = "tokens";

	/**
	 * Global instance of the scopes required by this quickstart. If modifying these
	 * scopes, delete your previously saved tokens/ folder.
	 */
	public static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS);
	private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

	/**
	 * Creates an authorized Credential object.
	 * 
	 * @param HTTP_TRANSPORT The network HTTP Transport.
	 * @return An authorized Credential object.
	 * @throws IOException If the credentials.json file cannot be found.
	 */
	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		// Load client secrets.
		InputStream in = GoogleSheetAPIUtils.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES)
						.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
						.setAccessType("offline").build();
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
		return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	}

	/*
	 * This method for set timeouts and handle HTTP errors that your code might
	 * receive. The setConnectTimeout and setReadTimeout methods are used to set the
	 * connect and read timeouts to three minutes (in milliseconds) for all requests
	 * Update - 08/02/2021
	 */
	private HttpRequestInitializer setHttpTimeout(final HttpRequestInitializer requestInitializer) {
		return new HttpRequestInitializer() {
			@Override
			public void initialize(HttpRequest httpRequest) throws IOException {
				requestInitializer.initialize(httpRequest);
				httpRequest.setConnectTimeout(3 * 60000); // 3 minutes connect timeout
				httpRequest.setReadTimeout(3 * 60000); // 3 minutes read timeout
			}
		};
	}

	// Create a HttpRequestInitializer, which will provide a baseline configuration
	// to all requests.
	/*
	 * HttpRequestInitializer requestInitializer = request -> { new
	 * HttpCredentialsAdapter(getCredentials(HTTP_TRANSPORT)).initialize(request);
	 * request.setConnectTimeout(60000); // 1 minute connect timeout
	 * request.setReadTimeout(60000); // 1 minute read timeout };
	 */
	/*
	 * public String[][] readDataFromGoogleSheet(String spreadsheetId, String range)
	 * throws IOException, GeneralSecurityException { // Build a new authorized API
	 * client service. final NetHttpTransport HTTP_TRANSPORT =
	 * GoogleNetHttpTransport.newTrustedTransport(); final int app_name =1; final
	 * int menu_item = 2; final int sub_menu_item = 3; Sheets service = new
	 * Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
	 * .setApplicationName(APPLICATION_NAME) .build(); ValueRange response =
	 * service.spreadsheets().values().get(spreadsheetId, range).execute(); // No Of
	 * Rows List<List<Object>> range_values = response.getValues();
	 * System.out.println("Values Data :"+range_values.size()+"All Rows Data:"
	 * +range_values.toString());
	 * 
	 * String [][] sheetData = new String[range_values.size()][];
	 * 
	 * 
	 * i = row j = column
	 * 
	 * 
	 * if (range_values == null || range_values.isEmpty()) {
	 * System.out.println("No data found."); } else { // Starting from 1 as not
	 * reading the header row for(int i = 1; i < range_values.size(); i++) { // Get
	 * value of each column - row wise List<Object> row_value = range_values.get(i);
	 * sheetData[i] = new String[row_value.size()]; if (i == 1) {
	 * sheetData[i][app_name] = (String) row_value.get(app_name); break; }
	 * 
	 * 
	 * for(int j = 0; j < row_value.size(); j++) { sheetData[i][j] = (String)
	 * row_value.get(j);
	 * System.out.println("Each Cell Data :"+sheetData[i][j].toString()); } } }
	 * return sheetData; }
	 */

	public String[][] readDataFromGoogleSheet(String spreadsheetId, String range)
			throws IOException, GeneralSecurityException {
		// Build a new authorized API client service.
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				setHttpTimeout(getCredentials(HTTP_TRANSPORT))).setApplicationName(APPLICATION_NAME).build();
//		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
//				.setApplicationName(APPLICATION_NAME).build();
		// System.out.println("Service Data
		// :"+service.spreadsheets().values()+"!!!!!!!:"+service.toString());
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		// No Of Rows
		List<List<Object>> values = response.getValues();
		// System.out.println("Values Data :"+values.size()+"All Rows
		// Data:"+values.toString());
		// Convert List to Array
		String[][] sheetData = new String[values.size()][];
		if (values == null || values.isEmpty())
			System.out.println("No data found.");
		else
			for (int i = 0; i < values.size(); i++) {
				// Get value of each column - row wise
				List<Object> value = values.get(i);
				// System.out.println("Value Data :"+value.size()+"All Column Data For 1
				// row"+value.toString());
				sheetData[i] = new String[value.size()];

				for (int j = 0; j < value.size(); j++)
					sheetData[i][j] = (String) value.get(j);
				// System.out.println("Each Cell Data :"+sheetData[i][j].toString());
			}
		return sheetData;
	}

	public String[][] readDataFromGoogleSheetColumnWise(String spreadsheetId, String range)
			throws IOException, GeneralSecurityException {
		// Build a new authorized API client service.
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();
		// System.out.println("Service Data
		// :"+service.spreadsheets().values()+"!!!!!!!:"+service.toString());
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		// No Of Rows
		List<List<Object>> values = response.getValues();
		// System.out.println("Values Data :"+values.size()+"All Rows
		// Data:"+values.toString());

		// Convert List to Array
		String[][] sheetData = new String[values.size()][];
		if (values == null || values.isEmpty())
			System.out.println("No data found.");
		else
			for (int i = 0; i < values.size(); i++) {

				// Get value of each column - row wise
				List<Object> value = values.get(i);

				// System.out.println("Value Data :"+value.size()+"All Column Data For 1
				// row"+value.toString());
				sheetData[i] = new String[value.size()];
				for (int j = 0; j < value.size(); j++)
					sheetData[i][j] = (String) value.get(j);
				// System.out.println("Each Cell Data :"+sheetData[i][j].toString());

			}
		return sheetData;
	}

	public void writeDataToGoogleSheet(String spreadsheetId, String sheetRange, String sheetName, String sheetColumn,
			String key, String data) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		ValueRange response = service.spreadsheets().values().get(spreadsheetId, sheetRange).execute();
		List<List<Object>> readValues = response.getValues();

		// System.out.println("READ VALUES:"+readValues.toString()+"READ VALUES
		// LENGTH:"+readValues.size());

		if (readValues == null || readValues.isEmpty())
			System.out.println("No data found.");
		else {

			int rowNums = 1;
			for (List<Object> row : readValues) {
				// System.out.println("ROW VALUE:"+row.toString());
				rowNums++;
				for (Object obj : row)
					// System.out.println("OBJECT VALUE:"+obj.toString());
					if (obj.toString().equalsIgnoreCase(key)) {
						String valueInputOption = "RAW";
						List<List<Object>> values = Arrays.asList(Arrays.asList(data));
						// System.out.println("VALUES======:"+values.toString()+"VALUES
						// LENGTH:"+values.size());
						String writeDataRange = sheetName + "!" + sheetColumn + rowNums + ":" + sheetColumn;
						ValueRange requestBody = new ValueRange();
						requestBody.setValues(values);
						requestBody.setRange(writeDataRange);
						requestBody.setMajorDimension("COLUMNS");

						// System.out.println("REQUEST BODY:"+requestBody.size()+"REQUEST BODY
						// DATA:"+requestBody.toString());
						UpdateValuesResponse result = service.spreadsheets().values()
								.update(spreadsheetId, writeDataRange, requestBody)
								.setValueInputOption(valueInputOption).execute();
					}
			}
		}
	}

	public void writeBooleanDataToGoogleSheet(String spreadsheetId, String sheetRange, String sheetName,
			String sheetColumn, String key, Boolean data) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, sheetRange).execute();
		List<List<Object>> readValues = response.getValues();
		/// int rowNums = 1;

		if (readValues == null || readValues.isEmpty())
			System.out.println("No data found.");
		else {

			int rowNums = 1;
			for (List<Object> row : readValues) {
				// System.out.println("ROW VALUE:" + row.toString());
				rowNums++;
				// for (Object obj : row)
				// if (obj.toString().equalsIgnoreCase(key)) {

				String valueInputOption = "RAW";
				List<List<Object>> values = Arrays.asList(Arrays.asList(data));
				// System.out.println("VALUES======:" + values.toString() + "VALUES LENGTH:" +
				// values.size());

				String writeDataRange = sheetName + "!" + sheetColumn + rowNums + ":" + sheetColumn;
				ValueRange requestBody = new ValueRange();
				requestBody.setValues(values);
				requestBody.setRange(writeDataRange);
				requestBody.setMajorDimension("COLUMNS");
				// System.out.println("REQUEST BODY:"+requestBody.size()+"REQUEST BODY
				// DATA:"+requestBody.toString());
				if ((row.get(4)).equals(key)) {
					UpdateValuesResponse result = service.spreadsheets().values()
							.update(spreadsheetId, writeDataRange, requestBody).setValueInputOption(valueInputOption)
							.execute();
					// System.out.println("66666666666666666666666666666666666666");

					// }
				}

			}

		}

	}

	public void writeBooleanDataToGoogleSheetRowWise(String spreadsheetId, String sheetRange, String sheetName,
			String sheetColumn, int key, Boolean data) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		ValueRange response = service.spreadsheets().values().get(spreadsheetId, sheetRange).execute();
		List<List<Object>> readValues = response.getValues();

		// System.out.println("READ VALUES:"+readValues.toString()+"READ VALUES
		// LENGTH:"+readValues.size());

		if (readValues == null || readValues.isEmpty())
			System.out.println("No data found.");
		else {

			int rowNums = 1;
			for (List<Object> row : readValues) {
				// System.out.println("ROW VALUE:"+row.toString());
				rowNums++;
				// for (Object obj : row) {
				// System.out.println("OBJECT VALUE:"+obj.toString());
				// if (obj.toString().equalsIgnoreCase(key)) {
				String valueInputOption = "RAW";
				List<List<Object>> values = Arrays.asList(Arrays.asList(data));
				// System.out.println("VALUES======:"+values.toString()+"VALUES
				// LENGTH:"+values.size());
				String writeDataRange = sheetName + "!" + sheetColumn + key + ":" + sheetColumn;
				// System.out.println(":--------" + writeDataRange);
				ValueRange requestBody = new ValueRange();
				requestBody.setValues(values);
				requestBody.setRange(writeDataRange);
				requestBody.setMajorDimension("COLUMNS");

				// System.out.println("REQUEST BODY:"+requestBody.size()+"REQUEST BODY
				// DATA:"+requestBody.toString());
				UpdateValuesResponse result = service.spreadsheets().values()
						.update(spreadsheetId, writeDataRange, requestBody).setValueInputOption(valueInputOption)
						.execute();
				// }
				// }
			}
		}

	}

	public void writeDataToGoogleSheetRowWise(String spreadsheetId, String sheetRange, String sheetName,
			String sheetColumn, int key, String data)
			throws GeneralSecurityException, IOException, InterruptedException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		// System.out.println("writeDataToGoogleSheetRowWise 1.1");
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();
		// System.out.println("writeDataToGoogleSheetRowWise 1.2");

		ValueRange response = service.spreadsheets().values().get(spreadsheetId, sheetRange).execute();
//		System.out.println("writeDataToGoogleSheetRowWise 1.3");
//		System.out.println(
//				"ZZZZZZZZZZZZZZZZ:" + service.spreadsheets().values().get(spreadsheetId, sheetRange).execute());
		List<List<Object>> readValues = response.getValues();
//		System.out.println("writeDataToGoogleSheetRowWise 1.4");
//		System.out.println("TTTTTTTTTTTTTTT:" + sheetRange);
//		System.out.println("44444444444444444:" + response.getValues().size());
//
//		System.out.println("#############:" + response.getValues());

		// System.out.println("READ VALUES:"+readValues.toString()+"READ VALUES
		// LENGTH:"+readValues.size());
		String valueInputOption = "RAW";
//		System.out.println("writeDataToGoogleSheetRowWise 1.5");

		List<List<Object>> values = Arrays.asList(Arrays.asList(data));
		// System.out.println("VALUES======:"+values.toString()+"VALUES
		// LENGTH:"+values.size());
		String writeDataRange = sheetName + "!" + sheetColumn + key + ":" + sheetColumn;
		ValueRange requestBody = new ValueRange();
		requestBody.setValues(values);
		requestBody.setRange(writeDataRange);
		requestBody.setMajorDimension("COLUMNS");

		// System.out.println("REQUEST BODY:"+requestBody.size()+"REQUEST BODY
		// DATA:"+requestBody.toString());
		System.out.println("writeDataToGoogleSheetRowWise 1.6");
		try {
//			System.out.println("writeDataToGoogleSheetRowWise ++++++++++++++++++++++++");
//			System.out.println("@@@@@@@@@@@@@@@@@@:" + values.toString());
//			System.out.println("IIIIIIIIIIIIIIIIII:" + writeDataRange);

			UpdateValuesResponse result = service.spreadsheets().values()
					.update(spreadsheetId, writeDataRange, requestBody).setValueInputOption(valueInputOption).execute();
		} catch (Exception e) {
			System.out.println("writeDataToGoogleSheetRowWise +++++++++++ Socket Catch block");
			// TODO: handle exception
			Thread.sleep(5000);

			UpdateValuesResponse result = service.spreadsheets().values()
					.update(spreadsheetId, writeDataRange, requestBody).setValueInputOption(valueInputOption).execute();
//			System.out
//					.println("writeDataToGoogleSheetRowWise AAAAAAAAAAAAAAAAAAAAAAAAAA+++++++++++ Socket Catch block");

		}
		/*
		 * if (readValues == null || readValues.isEmpty())
		 * System.out.println("No data found."); else {
		 * 
		 * int rowNums = 1; for (List<Object> row : readValues) { //
		 * System.out.println("ROW VALUE:"+row.toString()); rowNums++; for (Object obj :
		 * row) { // System.out.println("OBJECT VALUE:"+obj.toString()); // if
		 * (obj.toString().equalsIgnoreCase(key)) // { String valueInputOption = "RAW";
		 * System.out.println("writeDataToGoogleSheetRowWise 1.5");
		 * 
		 * List<List<Object>> values = Arrays.asList(Arrays.asList(data)); //
		 * System.out.println("VALUES======:"+values.toString()+"VALUES //
		 * LENGTH:"+values.size()); String writeDataRange = sheetName + "!" +
		 * sheetColumn + key + ":" + sheetColumn; ValueRange requestBody = new
		 * ValueRange(); requestBody.setValues(values);
		 * requestBody.setRange(writeDataRange);
		 * requestBody.setMajorDimension("COLUMNS");
		 * 
		 * // System.out.println("REQUEST BODY:"+requestBody.size()+"REQUEST BODY //
		 * DATA:"+requestBody.toString());
		 * System.out.println("writeDataToGoogleSheetRowWise 1.6"); try {
		 * System.out.println("writeDataToGoogleSheetRowWise ++++++++++++++++++++++++");
		 * System.out.println("@@@@@@@@@@@@@@@@@@:" + values.toString());
		 * System.out.println("IIIIIIIIIIIIIIIIII:" + writeDataRange);
		 * 
		 * UpdateValuesResponse result = service.spreadsheets().values()
		 * .update(spreadsheetId, writeDataRange, requestBody)
		 * .setValueInputOption(valueInputOption).execute(); } catch (Exception e) {
		 * System.out.
		 * println("writeDataToGoogleSheetRowWise +++++++++++ Socket Catch block"); //
		 * TODO: handle exception Thread.sleep(5000);
		 * 
		 * UpdateValuesResponse result = service.spreadsheets().values()
		 * .update(spreadsheetId, writeDataRange, requestBody)
		 * .setValueInputOption(valueInputOption).execute(); System.out.println(
		 * "writeDataToGoogleSheetRowWise AAAAAAAAAAAAAAAAAAAAAAAAAA+++++++++++ Socket Catch block"
		 * );
		 * 
		 * } System.out.println("writeDataToGoogleSheetRowWise 1.7");
		 * 
		 * // } } } }
		 */
	}

	/*
	 * public void writeDataToGoogleSheetRowWise(String spreadsheetId, String
	 * sheetRange, String sheetName, String sheetColumn, int key, String data)
	 * throws GeneralSecurityException, IOException, InterruptedException {
	 * 
	 * final NetHttpTransport HTTP_TRANSPORT =
	 * GoogleNetHttpTransport.newTrustedTransport();
	 * System.out.println("writeDataToGoogleSheetRowWise 1.1"); Sheets service = new
	 * Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
	 * .setApplicationName(APPLICATION_NAME).build();
	 * System.out.println("writeDataToGoogleSheetRowWise 1.2");
	 * 
	 * ValueRange response = service.spreadsheets().values().get(spreadsheetId,
	 * sheetRange).execute();
	 * System.out.println("writeDataToGoogleSheetRowWise 1.3"); System.out.println(
	 * "ZZZZZZZZZZZZZZZZ:" + service.spreadsheets().values().get(spreadsheetId,
	 * sheetRange).execute()); List<List<Object>> readValues = response.getValues();
	 * System.out.println("writeDataToGoogleSheetRowWise 1.4");
	 * System.out.println("TTTTTTTTTTTTTTT:" + sheetRange);
	 * System.out.println("44444444444444444:" + response.getValues().size());
	 * 
	 * System.out.println("#############:" + response.getValues());
	 * 
	 * // System.out.println("READ VALUES:"+readValues.toString()+"READ VALUES //
	 * LENGTH:"+readValues.size());
	 * 
	 * if (readValues == null || readValues.isEmpty())
	 * System.out.println("No data found."); else {
	 * 
	 * int rowNums = 1; for (List<Object> row : readValues) { //
	 * System.out.println("ROW VALUE:"+row.toString()); rowNums++; for (Object obj :
	 * row) { // System.out.println("OBJECT VALUE:"+obj.toString()); // if
	 * (obj.toString().equalsIgnoreCase(key)) // { String valueInputOption = "RAW";
	 * System.out.println("writeDataToGoogleSheetRowWise 1.5");
	 * 
	 * List<List<Object>> values = Arrays.asList(Arrays.asList(data)); //
	 * System.out.println("VALUES======:"+values.toString()+"VALUES //
	 * LENGTH:"+values.size()); String writeDataRange = sheetName + "!" +
	 * sheetColumn + key + ":" + sheetColumn; ValueRange requestBody = new
	 * ValueRange(); requestBody.setValues(values);
	 * requestBody.setRange(writeDataRange);
	 * requestBody.setMajorDimension("COLUMNS");
	 * 
	 * // System.out.println("REQUEST BODY:"+requestBody.size()+"REQUEST BODY //
	 * DATA:"+requestBody.toString());
	 * System.out.println("writeDataToGoogleSheetRowWise 1.6"); try {
	 * System.out.println("writeDataToGoogleSheetRowWise ++++++++++++++++++++++++");
	 * System.out.println("@@@@@@@@@@@@@@@@@@:" + values.toString());
	 * System.out.println("IIIIIIIIIIIIIIIIII:" + writeDataRange);
	 * 
	 * UpdateValuesResponse result = service.spreadsheets().values()
	 * .update(spreadsheetId, writeDataRange, requestBody)
	 * .setValueInputOption(valueInputOption).execute(); } catch (Exception e) {
	 * System.out.
	 * println("writeDataToGoogleSheetRowWise +++++++++++ Socket Catch block"); //
	 * TODO: handle exception Thread.sleep(5000);
	 * 
	 * UpdateValuesResponse result = service.spreadsheets().values()
	 * .update(spreadsheetId, writeDataRange, requestBody)
	 * .setValueInputOption(valueInputOption).execute(); System.out.println(
	 * "writeDataToGoogleSheetRowWise AAAAAAAAAAAAAAAAAAAAAAAAAA+++++++++++ Socket Catch block"
	 * );
	 * 
	 * } System.out.println("writeDataToGoogleSheetRowWise 1.7");
	 * 
	 * // } } } } }
	 */

	public void writeDataInGoogleSpreadSheet(String spreadsheetId, String sheetName, String colName, String rowNo,
			String value) throws IOException, GeneralSecurityException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();
		List<ValueRange> data = new ArrayList<>();

		data.add(new ValueRange().setRange(sheetName + "!" + colName + rowNo)
				.setValues(Arrays.asList(Arrays.asList(value))));
		BatchUpdateValuesRequest batchBody = new BatchUpdateValuesRequest().setValueInputOption("USER_ENTERED")
				.setData(data);
		BatchUpdateValuesResponse batchResult = service.spreadsheets().values().batchUpdate(spreadsheetId, batchBody)
				.execute();

	}
}
