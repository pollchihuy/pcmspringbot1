package com.example.pcmspringbot1.controller;

import com.example.pcmspringbot1.model.GroupMenu;
import com.example.pcmspringbot1.model.Menu;
import com.example.pcmspringbot1.repo.MenuRepo;
import com.example.pcmspringbot1.utils.DataGenerator;
import com.example.pcmspringbot1.utils.TokenGenerator;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MenuControllerTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private MenuRepo menuRepo;
    private JSONObject req;
    private Menu menu;
    private DataGenerator dataGenerator;
    private Random rand ;
    private String token ;
    private Integer status;
    private Boolean success;
    private String message;
    private String data;

    @BeforeClass
//    @BeforeSuite
    private void init(){
        token = new TokenGenerator(AuthControllerTest.authorization).getToken();
        rand = new Random();
        req = new JSONObject();
        menu = new Menu();
        dataGenerator = new DataGenerator();
        Optional<Menu> opMenu = menuRepo.findTopByOrderByIdDesc();
        menu = opMenu.get();
    }

    @BeforeTest
    private void setup(){
        /** untuk kebutuhan environment dll sebelum server dijalankan , letakkan disini */
    }



    @Test(priority = 0)
    private void save(){
        GroupMenu groupMenu = new GroupMenu();
        groupMenu.setId(1L);
        req.put("nama",dataGenerator.dataNamaTim());
        req.put("groupMenu",groupMenu);
        req.put("path","/"+dataGenerator.dataKota());

        RequestSpecification httpRequest = given().
                header("Content-Type","application/json").
                header("accept","*/*").
                header(AuthControllerTest.AUTH_HEADER,token).
                body(req);

        String pathVariable = "/menu";
        Response response = httpRequest.request(Method.POST, pathVariable);
        int responseCode = response.statusCode();
        JsonPath jPath = response.jsonPath();
        ResponseBody responseBody = response.getBody();// seluruh body dari response
        message = jPath.getString("message");
        status = Integer.parseInt(jPath.getString("status")==null?"0":jPath.getString("status"));
        success = Boolean.parseBoolean(jPath.getString("success"));
        data = jPath.getString("data");
        Assert.assertEquals(data,"");
        Assert.assertEquals(responseCode,201);
        Assert.assertEquals(message,"DATA BERHASIL DISIMPAN");
        Assert.assertEquals(status,201);
        Assert.assertEquals(success,true);
    }

    @Test(priority = 2)
    private void update(){
        GroupMenu groupMenu = new GroupMenu();
        groupMenu.setId(2L);
        String reqNama = dataGenerator.dataNamaTim();
        req.put("nama",reqNama);
        req.put("groupMenu",groupMenu);
        req.put("path","/"+dataGenerator.dataKota());

        RequestSpecification httpRequest = given().
                header("Content-Type","application/json").
                header("accept","*/*").
                header(AuthControllerTest.AUTH_HEADER,token).
                body(req);

        String pathVariable = "/menu/"+ menu.getId();
        Response response = httpRequest.request(Method.PUT, pathVariable);
        int responseCode = response.statusCode();
        JsonPath jPath = response.jsonPath();
        message = jPath.getString("message");
        status = Integer.parseInt(jPath.getString("status")==null?"0":jPath.getString("status"));
        success = Boolean.parseBoolean(jPath.getString("success"));
        data = jPath.getString("data");
        Assert.assertEquals(data,"");
        Assert.assertEquals(responseCode,200);
        Assert.assertEquals(message,"DATA BERHASIL DIUBAH");
        Assert.assertEquals(status,200);
        Assert.assertEquals(success,true);
    }

//    APP_PORT=8080;CONTOH=OK-BOS;CONTOH_LAIN=HUE;DB_PWD=b381990e93da47d98266f459e749d3af;DB_URL=abb07b3e6ce49452eee7f5532759ade255405fb9712a08b8b79917eea06353738db3d72c7d41436421fc59ebf1ae02fbbbde46a1679ac3f55072998cbae86e9d9649f318ddbb8d8fbcd1fae37a29e357a97847d15ca393b305e550261883dfbd;DB_USN=f691d7c07971842c371c2a2dc899f811;DDL_AUTO=update;EMAIL_USN=17d63b1126a9e9b307867db15245d8e7ab1442297b4e300640ed22a615eba3a0;JWT_SECRET=660a1e020c2fdc8c12043a5dd3321cf2c7e2da5b11c203f352901fe6770c319cca98bb7f0332964b2bde23046fc461b1;TEST_AUTO=y;FLAG_LOGGING=y
    @Test(priority = 1)
    private void findByIdTest(){
        RequestSpecification httpRequest = given().
        header("Content-Type","application/json").
        header(AuthControllerTest.AUTH_HEADER,token).
        header("accept","*/*");
        String pathVariable = "/menu/"+ menu.getId();
        Response response = httpRequest.request(Method.GET, pathVariable);
        ResponseBody responseBody = response.getBody();// seluruh body dari response
        System.out.println("====================================START RESPONSE BODY =================================================");
        System.out.println(responseBody.asPrettyString());// untuk melihat isi dari response body dalam bentuk JSON
        int responseCode = response.statusCode();
        JsonPath jPath = response.jsonPath();
        Long id = Long.parseLong(jPath.getString("data.id"));
        String nama = jPath.getString("data.nama");
        status = Integer.parseInt(jPath.getString("status")==null?"0":jPath.getString("status"));
        success = Boolean.parseBoolean(jPath.getString("success"));
        Assert.assertEquals(responseCode,200);
        Assert.assertEquals(nama, menu.getNama());
        Assert.assertEquals(id, menu.getId());
        Assert.assertEquals(status,200);
        Assert.assertEquals(success,true);
    }

    @Test(priority = 3)
    private void findAll(){
        RequestSpecification httpRequest = given().
                header("Content-Type","application/json").
                header("accept","*/*").
                header(AuthControllerTest.AUTH_HEADER,token).
                param("size",1);

        String pathVariable = "/menu";
        Response response = httpRequest.request(Method.GET, pathVariable);
        ResponseBody responseBody = response.getBody();// seluruh body dari response
        System.out.println("====================================START RESPONSE BODY =================================================");
        System.out.println(responseBody.asPrettyString());// untuk melihat isi dari response body dalam bentuk JSON
        int responseCode = response.statusCode();
        JsonPath jPath = response.jsonPath();
        /** untuk case ini pengambilan datanya menggunakan List */
        List<Map<String,Object>> lt = jPath.getList("data.content");
        status = Integer.parseInt(jPath.getString("status")==null?"0":jPath.getString("status"));
        success = Boolean.parseBoolean(jPath.getString("success"));
        message = jPath.getString("message");
        Integer pageNumber = Integer.parseInt(jPath.getString("data.current-page"));
        String columnName = jPath.getString("data.column-name");
        String sort = jPath.getString("data.sort");
        Integer totalData = Integer.parseInt(jPath.getString("data.total-data"));
        String value = jPath.getString("data.value");
        System.out.println("GET ID : "+ menu.getId());
        System.out.println("GET NAME : "+ menu.getNama());

        Assert.assertEquals(responseCode,200);
        Assert.assertEquals(status,200);
        Assert.assertEquals(success,true);
        Assert.assertEquals(message,"OK");
        Assert.assertEquals(pageNumber,0);//karena diset manual seperti itu, untuk automation ada cara nya agar dynamic
        Assert.assertEquals(columnName,"id");
        Assert.assertEquals(sort,"asc");
        Assert.assertEquals(value,"");
        Assert.assertEquals(totalData,lt.size());
    }

//    APP_PORT=8080;CONTOH=OK-BOS;CONTOH_LAIN=HUE;DB_PWD=b381990e93da47d98266f459e749d3af;DB_URL=abb07b3e6ce49452eee7f5532759ade255405fb9712a08b8b79917eea06353738db3d72c7d41436421fc59ebf1ae02fbbbde46a1679ac3f55072998cbae86e9d9649f318ddbb8d8fbcd1fae37a29e357a97847d15ca393b305e550261883dfbd;DB_USN=f691d7c07971842c371c2a2dc899f811;DDL_AUTO=update;EMAIL_USN=17d63b1126a9e9b307867db15245d8e7ab1442297b4e300640ed22a615eba3a0;JWT_SECRET=660a1e020c2fdc8c12043a5dd3321cf2c7e2da5b11c203f352901fe6770c319cca98bb7f0332964b2bde23046fc461b1;TEST_AUTO=y;FLAG_LOGGING=y
    @Test(priority = 4)
    private void uploadSheet(){
        RequestSpecification httpRequest = given().
                header("Content-Type","multipart/form-data").
                header("accept","*/*").
                header(AuthControllerTest.AUTH_HEADER,token).
                multiPart("file",new File(System.getProperty("user.dir")+"/src/test/resources/data-test/menu.xlsx"));
        String pathVariable = "/menu/upload";
        Response response = httpRequest.request(Method.POST, pathVariable);
        int responseCode = response.statusCode();
        JsonPath jPath = response.jsonPath();
        status = Integer.parseInt(jPath.getString("status")==null?"0":jPath.getString("status"));
        success = Boolean.parseBoolean(jPath.getString("success"));
        message = jPath.getString("message");
        Assert.assertEquals(responseCode,201);
        Assert.assertEquals(status,201);
        Assert.assertEquals(success,true);
        Assert.assertEquals(message,"DATA BERHASIL DISIMPAN");
    }

 //APP_PORT=8080;CONTOH=OK-BOS;CONTOH_LAIN=HUE;DB_PWD=b381990e93da47d98266f459e749d3af;DB_URL=abb07b3e6ce49452eee7f5532759ade255405fb9712a08b8b79917eea06353738db3d72c7d41436421fc59ebf1ae02fbbbde46a1679ac3f55072998cbae86e9d9649f318ddbb8d8fbcd1fae37a29e357a97847d15ca393b305e550261883dfbd;DB_USN=f691d7c07971842c371c2a2dc899f811;DDL_AUTO=update;EMAIL_USN=17d63b1126a9e9b307867db15245d8e7ab1442297b4e300640ed22a615eba3a0;JWT_SECRET=660a1e020c2fdc8c12043a5dd3321cf2c7e2da5b11c203f352901fe6770c319cca98bb7f0332964b2bde23046fc461b1;TEST_AUTO=y;FLAG_LOGGING=y
    @Test(priority = 5)
    private void downloadSheet(){
        RequestSpecification httpRequest = given().
                header("Content-Type","application/json").
                header("accept","*/*").
                param("column","nama").
                header(AuthControllerTest.AUTH_HEADER,token).
                param("value", menu.getNama());

        String pathVariable = "/menu/excel";
        Response response = httpRequest.request(Method.GET, pathVariable);
        ResponseBody responseBody = response.getBody();// seluruh body dari response
//        System.out.println("====================================START RESPONSE BODY =================================================");
        String prettyString = responseBody.asPrettyString();// karena bentuk nya gak karuan maka harus diambil 1 string saja sebagai pedoman dengan menggunakan fungsi string contains
        System.out.println(prettyString);// untuk melihat isi dari response body dalam bentuk JSON
        int responseCode = response.statusCode();
        Assert.assertEquals(responseCode,200);
//        Assert.assertTrue(prettyString.contains("[Content_Types].xml"));
    }

//    APP_PORT=8080;CONTOH=OK-BOS;CONTOH_LAIN=HUE;DB_PWD=b381990e93da47d98266f459e749d3af;DB_URL=abb07b3e6ce49452eee7f5532759ade255405fb9712a08b8b79917eea06353738db3d72c7d41436421fc59ebf1ae02fbbbde46a1679ac3f55072998cbae86e9d9649f318ddbb8d8fbcd1fae37a29e357a97847d15ca393b305e550261883dfbd;DB_USN=f691d7c07971842c371c2a2dc899f811;DDL_AUTO=update;EMAIL_USN=17d63b1126a9e9b307867db15245d8e7ab1442297b4e300640ed22a615eba3a0;JWT_SECRET=660a1e020c2fdc8c12043a5dd3321cf2c7e2da5b11c203f352901fe6770c319cca98bb7f0332964b2bde23046fc461b1;TEST_AUTO=y;FLAG_LOGGING=y
    @Test(priority = 6)
    private void generatePDF(){
        RequestSpecification httpRequest = given().
                header("Content-Type","application/json").
                header("accept","*/*").
                param("column","nama").
                header(AuthControllerTest.AUTH_HEADER,token).
                param("value", menu.getNama());

        String pathVariable = "/menu/pdf";
        Response response = httpRequest.request(Method.GET, pathVariable);
        ResponseBody responseBody = response.getBody();// seluruh body dari response
//        System.out.println("====================================START RESPONSE BODY =================================================");
        String prettyString = responseBody.asPrettyString();// karena bentuk nya gak karuan maka harus diambil 1 string saja sebagai pedoman dengan menggunakan fungsi string contains
        System.out.println(prettyString);// untuk melihat isi dari response body dalam bentuk JSON
        int responseCode = response.statusCode();
        Assert.assertEquals(responseCode,200);
//        Assert.assertTrue(prettyString.contains("startxref"));
    }

    //    APP_PORT=8080;CONTOH=OK-BOS;CONTOH_LAIN=HUE;DB_PWD=b381990e93da47d98266f459e749d3af;DB_URL=abb07b3e6ce49452eee7f5532759ade255405fb9712a08b8b79917eea06353738db3d72c7d41436421fc59ebf1ae02fbbbde46a1679ac3f55072998cbae86e9d9649f318ddbb8d8fbcd1fae37a29e357a97847d15ca393b305e550261883dfbd;DB_USN=f691d7c07971842c371c2a2dc899f811;DDL_AUTO=update;EMAIL_USN=17d63b1126a9e9b307867db15245d8e7ab1442297b4e300640ed22a615eba3a0;JWT_SECRET=660a1e020c2fdc8c12043a5dd3321cf2c7e2da5b11c203f352901fe6770c319cca98bb7f0332964b2bde23046fc461b1;TEST_AUTO=y;FLAG_LOGGING=y
    @Test(priority = 100)
    private void delete(){
        /** jika ingin menjalankan suite / integration test fungsional delete di taruh pada urutan paling akhir , karena data yang dipilih di awal di gunakan untuk validasi di fungsi-fungsi sebelumnya */
        RequestSpecification httpRequest = given().
                header("Content-Type","application/json").
                header("accept","*/*").
                header(AuthControllerTest.AUTH_HEADER,token);
        String pathVariable = "/menu/"+ menu.getId();
        Response response = httpRequest.request(Method.DELETE, pathVariable);
        ResponseBody responseBody = response.getBody();// seluruh body dari response
        int responseCode = response.statusCode();
        JsonPath jPath = response.jsonPath();
        status = Integer.parseInt(jPath.getString("status")==null?"0":jPath.getString("status"));
        message = jPath.getString("message");
        success = Boolean.parseBoolean(jPath.getString("success"));
        data = jPath.getString("data");
        Assert.assertEquals(data,"");
        Assert.assertEquals(responseCode,200);
        Assert.assertEquals(status,200);
        Assert.assertEquals(success,true);
        Assert.assertEquals(message,"DATA BERHASIL DIHAPUS");
    }
}