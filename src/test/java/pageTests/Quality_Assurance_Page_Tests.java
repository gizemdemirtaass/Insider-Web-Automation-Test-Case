package pageTests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static utilities.extentReport.ExtentTestManager.startTest;

public class Quality_Assurance_Page_Tests extends BaseTest {

    @BeforeMethod
    public void beforeMethod(Method method) {
        startTest(method.getName(), "Insider Quality Assurance Page Tests");
        welcome_page_objects
                .open_welcome_page(properties.getProperty("liveURL"));
        //welcome_page_objects
        //        .accept_KVKK_if_display();
        quality_assurance_objects
                .open_quality_assurance_page(properties.getProperty("qualityPageURL"));

    }

    @Test
    public void QUALITY_PAGE_TC001_Open_and_Click_Quality_Page() {
        quality_assurance_objects
                .check_quality_assurance_page_is_open()
                .click_combobox_button()
                .assert_position1_is_open()
                .assert_position2_is_open()
                .assert_view_role_page_is_open();
    }

}
