package controller;

import controller.ProfileManage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserProfileServletTest {

    private ProfileManage userProfileServlet; 

    @Before
    public void setUp() {
        userProfileServlet = new ProfileManage(); 
    }

    @Test
    public void testValidateUserInfo_NameTooLong() {
        String name = "Tên của tôi rất dài, dài đến mức vượt quá năm mươi ký tự này"; 
        String phone = "0123456789"; 
        String address = "123 Main St"; 

        String result = userProfileServlet.validateUserInfo(name, phone, address);
        assertEquals("Name must be less than 50 characters.", result);
    }

    @Test
    public void testValidateUserInfo_PhoneNotNumeric() {
        String name = "John Doe";
        String phone = "1234567abc"; // Không phải số
        String address = "123 Main St"; // Hợp lệ

        String result = userProfileServlet.validateUserInfo(name, phone, address);
        assertEquals("Phone number must be exactly 10 digits.", result); 
    }

    @Test
    public void testValidateUserInfo_PhoneTooShort() {
        String name = "John Doe";
        String phone = "1234567"; 
        String address = "123 Main St"; 

        String result = userProfileServlet.validateUserInfo(name, phone, address);
        assertEquals("Phone number must be exactly 10 digits.", result); 
    }

    @Test
    public void testValidateUserInfo_AddressTooLong() {
        String name = "John Doe";
        String phone = "0123456789"; 
        String address = "Địa chỉ này quá dài, vượt quá một trăm hai mươi ký tự để kiểm tra lỗi." +
                " Đây là một phần bổ sung để làm cho địa chỉ dài hơn.";

        String result = userProfileServlet.validateUserInfo(name, phone, address);
        assertEquals("Address must be less than 120 characters.", result); 
    }

    @Test
    public void testValidateUserInfo_ValidInput() {
        String name = "John Doe";
        String phone = "0123456789"; 
        String address = "123 Main St"; 

        String result = userProfileServlet.validateUserInfo(name, phone, address);
        assertEquals(null, result); 
    }
}
