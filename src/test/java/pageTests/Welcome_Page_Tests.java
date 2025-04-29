package pageTests;

import base.BaseTest;
import org.jsoup.Connection;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static utilities.extentReport.ExtentTestManager.startTest;

public class Welcome_Page_Tests extends BaseTest {



    @BeforeMethod
    public void beforeMethod(Method method) {
        startTest(method.getName(), "Tabii Landing Page Tests");
        welcome_page_objects
                .open_welcome_page(properties.getProperty("liveURL"))
                .accept_KVKK_if_display();

    }

    @Test(retryAnalyzer = utilities.listeners.Retry.class)
    public void WELCOME_001_Welcome_Sayfasinin_Basarili_Sekilde_Acilmasi() {
        welcome_page_objects
                .assert_welcome_page_is_open();
    }

    @Test(description = "Company Menüsünün Başarılı Görüntüleme Testi")
    public void WELCOME_002_Company_Menu_Basarili_Sekilde_Acilmasi() {
        welcome_page_objects
                .click_company_button_assert_company_menu();
    }
    @Test(description = "Career Sayafasının Başarılı Şekilde Açılması")
    public void WELCOME_003_Career_Page_Basarili_Sekilde_Acilmasi(){
        welcome_page_objects
                .assert_career_page_is_open();
    }
}
