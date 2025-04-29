package pageTests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static utilities.extentReport.ExtentTestManager.startTest;

public class Career_Page_Tests extends BaseTest {

    @BeforeMethod
    public void beforeMethod(Method method) {
        startTest(method.getName(), "Insider Career Page Tests");
        welcome_page_objects
                .open_welcome_page(properties.getProperty("liveURL"))
                .accept_KVKK_if_display();
        career_page_objects
                .open_career_page(properties.getProperty("careerURL"));

    }
    @Test(description = "Our Locations Textinin Başarılı Görüntülenmesi")
    public void CAREER_PAGE_TC001_Successful_Display_of_Our_Locations_Text () {
        career_page_objects
                .assert_text_our_locations();
    }

    @Test(description = "Life at Insider Text inin Başarılı Görüntülenmesi")
    public void CAREER_PAGE_TC002_Successful_Display_of_Life_at_Insider () {
        career_page_objects
                .assert_text_life_at_insider();
    }
    @Test(description = "See All Teams Butonunun Başarılı Görüntülenmesi ve Fonksiyonunun Kontrolü")
    public void CAREER_PAGE_TC003_Successful_Display_of_See_All_Teams () {
        career_page_objects
                .assert_button_see_all_teams();
    }

}
