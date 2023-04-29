package test_data;

import java.util.HashMap;
import java.util.Map;

public class PetStoreTestData {

    public Map<String,Object> expectedDataMapMethod(String username,String firstname,String lastname,String email,String password,
            String phone,Integer userStatus){

        Map<String ,Object>expectedData=new HashMap<>();

        expectedData.put("username",username);
        expectedData.put("firstname",firstname);
        expectedData.put("lastname",lastname);
        expectedData.put("email",email);
        expectedData.put("password",password);
        expectedData.put("phone",phone);
        expectedData.put("userStatus",userStatus);

        return expectedData;

    }
}
