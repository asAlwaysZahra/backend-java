package com.mycompany.app;

import com.mycompany.app.FileClasses.ReadFile;
import com.mycompany.app.FileClasses.WriteInFile;
import com.mycompany.app.Models.AccountInformation;
import com.mycompany.app.Models.Employee;
import com.mycompany.app.Models.LocationInfo;
import com.mycompany.app.Models.PersonalInformation;
import lombok.Cleanup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.*;

public class AppTest {

    private File jsonFile;
    private File xmlFile;

    private void setExampleJsonFile() {

        try {
            @Cleanup
            FileWriter writer = new FileWriter(jsonFile);
            writer.write("[\n" + "    {\n" + "        \"id\": 4051,\n" + "        \"name\": \"manoj\",\n" + "        \"email\": \"manoj@gmail.com\",\n" + "        \"password\": \"Test@123\",\n" + "        \"about\": null,\n" + "        \"token\": \"7f471974-ae46-4ac0-a882-1980c300c4d6\",\n" + "        \"country\": null,\n" + "        \"location\": null,\n" + "        \"lng\": 0,\n" + "        \"lat\": 0,\n" + "        \"dob\": null,\n" + "        \"gender\": 0,\n" + "        \"userType\": 1,\n" + "        \"userStatus\": 1,\n" + "        \"profilePicture\": \"Images/9b291404-bc2e-4806-88c5-08d29e65a5ad.png\",\n" + "        \"coverPicture\": \"Images/44af97d9-b8c9-4ec1-a099-010671db25b7.png\",\n" + "        \"enablefollowme\": false,\n" + "        \"sendmenotifications\": false,\n" + "        \"sendTextmessages\": false,\n" + "        \"enabletagging\": false,\n" + "        \"createdAt\": \"2020-01-01T11:13:27.1107739\",\n" + "        \"updatedAt\": \"2020-01-02T09:16:49.284864\",\n" + "        \"livelng\": 77.389849,\n" + "        \"livelat\": 28.6282231,\n" + "        \"liveLocation\": \"Unnamed Road, Chhijarsi, Sector 63, Noida, Uttar Pradesh 201307, India\",\n" + "        \"creditBalance\": 127,\n" + "        \"myCash\": 0\n" + "    }\n" + "]");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setFile() {
        try {
            jsonFile = new File("jsonFile.json");
            jsonFile.createNewFile();

            xmlFile = new File("xmlFile.xml");
            xmlFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindFiles() {
        ReadFile reader = ReadFile.getInstance();
        assertThrows(IOException.class, () -> reader.readJson("eEmployeeData.json"));
    }

    @Test
    public void testJsonFileFormat() {

        // false test
        assertFalse(xmlFile.getAbsolutePath().endsWith(".json"));

        // true test
        assertTrue(jsonFile.getAbsolutePath().endsWith(".json"));
    }

    @Test
    public void testValidInformation() throws IOException {
        // false test
        String s = Files.readString(jsonFile.toPath());
        assertFalse(s.startsWith("[") && s.endsWith("]"));

        // true test
        FileWriter writer = new FileWriter(jsonFile);
        writer.write("[{\"id\": 4051,\"name\": \"manoj\"}]");
        writer.close();

        s = Files.readString(jsonFile.toPath());
        assertTrue(s.startsWith("[") && s.endsWith("]"));
    }

    @Test
    public void testReadJson() throws IOException {

        this.setExampleJsonFile();

        ReadFile reader = ReadFile.getInstance();
        List<Employee> employees = reader.readJson(jsonFile.getPath());
        Employee em = new Employee(new PersonalInformation("manoj", "manoj@gmail.com", "Test@123", null, null, new LocationInfo(0, 0, null), null, 0), new AccountInformation(1, "Images/9b291404-bc2e-4806-88c5-08d29e65a5ad.png", "Images/44af97d9-b8c9-4ec1-a099-010671db25b7.png", false, false, false, false, "2020-01-01T11:13:27.1107739", "2020-01-02T09:16:49.284864", new LocationInfo(77.389849, 28.6282231, "Unnamed Road, Chhijarsi, Sector 63, Noida, Uttar Pradesh 201307, India")), 4051, "7f471974-ae46-4ac0-a882-1980c300c4d6", 1, 127, 0);

        assertEquals(employees.get(0).toString(), em.toString());
//        assertEquals(employees.get(0), em);
    }

    @Test
    public void testWriteInXml() throws IOException {

        this.setExampleJsonFile();

        ReadFile reader = ReadFile.getInstance();
        List<Employee> employees = reader.readJson(jsonFile.getPath());

        WriteInFile writer = WriteInFile.getInstance();
        writer.writeToXml(employees, xmlFile.getPath());
        String xml = Files.readString(Path.of(xmlFile.getPath()));

        assertEquals(xml, "<ArrayList><item><personalInformation><name>manoj</name><email>manoj@gmail.com</email><password>Test@123</password><about>null</about><country>null</country><locationInfo><lng>0.0</lng><lat>0.0</lat><location>null</location></locationInfo><dob>null</dob><gender>0</gender></personalInformation><accountInformation><userStatus>1</userStatus><profilePicture>Images/9b291404-bc2e-4806-88c5-08d29e65a5ad.png</profilePicture><coverPicture>Images/44af97d9-b8c9-4ec1-a099-010671db25b7.png</coverPicture><enablefollowme>false</enablefollowme><sendmenotifications>false</sendmenotifications><sendTextmessages>false</sendTextmessages><enabletagging>false</enabletagging><createdAt>2020-01-01T11:13:27.1107739</createdAt><updatedAt>2020-01-02T09:16:49.284864</updatedAt><liveLocationInfo><lng>77.389849</lng><lat>28.6282231</lat><location>Unnamed Road, Chhijarsi, Sector 63, Noida, Uttar Pradesh 201307, India</location></liveLocationInfo></accountInformation><id>4051</id><token>7f471974-ae46-4ac0-a882-1980c300c4d6</token><userType>1</userType><creditBalance>127.0</creditBalance><myCash>0.0</myCash></item></ArrayList>");
    }

    @After
    public void deleteFile() {
        jsonFile.delete();
        xmlFile.delete();
    }
}
