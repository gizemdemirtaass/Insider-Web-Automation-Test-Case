package pageObjects;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Career_Page_Objects extends BasePage {
    public Career_Page_Objects(WebDriver driver) { super(driver);}

    By text_our_locations = By.xpath("//h3[contains(text(), 'Our Locations')]");

    By text_life_at_insider = By.xpath("//h2[contains(text(), 'Life at Insider')]");

    By see_all_teams_btn = By.xpath("//a[contains(@class, 'btn') and contains(text(), 'See all teams')]");

    By text_finance_and_business_support = By.xpath("//h3[contains(text(), 'Finance & Business Support')]");

    public Career_Page_Objects open_career_page(String careerURL) {
        getUrl("https://useinsider.com/careers/");
        getInfoMessage("Career sayfasi acildi");
        return this;
    }

    public Career_Page_Objects assert_text_our_locations() {
        scrollToElement(text_our_locations);
        untilElementIsVisible(text_our_locations);
        Assert.assertTrue(elementIsDisplayed(text_our_locations), "Our Locations başlığı görüntülenmedi");
        getInfoMessage("Our Location başlığı görüntülendi");
        return this;
    }

    public Career_Page_Objects assert_text_life_at_insider() {
        scrollToElement(text_life_at_insider);
        untilElementIsVisible(text_life_at_insider);
        Assert.assertTrue(elementIsDisplayed(text_life_at_insider),"Life at Insider başlığı görüntülenmedi");
        getInfoMessage("Life at Insider başlığı görüntülendi");
        return this;
    }

    public Career_Page_Objects assert_button_see_all_teams () {
        scrollToElement(see_all_teams_btn);
        untilElementIsVisible(see_all_teams_btn);
        //waitUntilAndClick(see_all_teams_btn);
        Assert.assertTrue(elementIsDisplayed(see_all_teams_btn),"See All Teams butonuna düzgün bir şekilde görüntülenmedi");
        getInfoMessage("See All Teams butonu düzgün bir şekilde görüntülendi");
        return this;
    }


}
