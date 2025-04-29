package pageObjects;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class Welcome_Page_Objects extends BasePage {
    public Welcome_Page_Objects(WebDriver driver) { super(driver);}


    By company_btn = By.xpath("(//*[@class=\"nav-link dropdown-toggle hide-after\"])[5]");

    By career_btn = By.xpath("//*[@id=\"navbarNavDropdown\"]/ul[1]/li[6]/div/div[2]/a[2]");

    By kvkk_accept_button = By.id("//*[@id='wt-cli-accept-all-btn']");

    public Welcome_Page_Objects open_welcome_page(String liveURL) {
        getUrl("https://useinsider.com/");
        //Assert.assertTrue(elementIsDisplayed(welcome_page_login_btn), "Login sayfası açılmadı.");
        getInfoMessage("https://useinsider.com/ sayfası açıldı.");
        return this;
    }
    public Welcome_Page_Objects accept_KVKK_if_display() {
        if (elementIsPresent(kvkk_accept_button)) {
            untilElementIsClickable(kvkk_accept_button);
            waitForLoad(1000);
            clickElement(kvkk_accept_button);
            getInfoMessage("KVKK butonu görüntülendi ve onaylandı.");

        }
        return this;
    }
    public Welcome_Page_Objects assert_welcome_page_is_open() {
        Assert.assertTrue(getCurrentUrl().equals("https://useinsider.com/"), "Welcome sayfasi acilmadi.");
        getInfoMessage("Welcome sayfasi acildi.");
        return this;
    }
    public Welcome_Page_Objects click_company_button_assert_company_menu() {
        clickElement(company_btn);
        Assert.assertTrue(getCurrentUrl().equals("https://useinsider.com/") && elementIsPresent(company_btn), "Sirket menusu acilmadi");
        getInfoMessage("Sirket menusu acildi");
        return this;
    }
    public Welcome_Page_Objects assert_career_page_is_open(){
        clickElement(career_btn);
        //untilElementIsVisible(career_btn);
        //untilElementIsClickable(career_btn);
        //clickElement(career_btn);
        Assert.assertTrue(getCurrentUrl().contains("https://useinsider.com/careers/"), "Career sayfasi acilmadi");
        getInfoMessage("Career sayfasi acildi");
        return this;
    }
}
