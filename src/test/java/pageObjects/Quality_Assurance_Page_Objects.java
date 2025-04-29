package pageObjects;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Quality_Assurance_Page_Objects extends BasePage {
    public Quality_Assurance_Page_Objects(WebDriver driver) {super(driver);}

    By click_see_all_qa_jobs = By.xpath("//a[contains(text(), 'See all QA jobs')]");

    By text_filter_by_location = By.xpath("//form//label[contains(text(), 'Filter by Location')]");

    By filter_by_location_button = By.xpath("(//form//span[@class='select2-selection__rendered'])[1]");

    By filter_by_department_button = By.xpath("(//form//span[@class='select2-selection__rendered'])[2]");


    By filter_istanbul_turkey_option = By.xpath("//ul[contains(@class, 'select2-results__options')]/li[contains(text(), 'Istanbul, Turkiye')]");

    By filter_all_option = By.xpath("//ul[contains(@class, 'select2-results__options')]/li[contains(text(), 'All')]");

    By filter_quality_assurance_option = By.xpath("//ul[contains(@class,'select2-results__options')]/li[contains(text(), 'Quality Assurance')]");

    By filter_all2_option = By.xpath("//ul[contains(@class, 'select2-results__options')]/li[contains(text(), 'All')]");

    By text_browse = By.xpath("//section[3]//h3[contains(text(), 'Browse')]");

    By check_text_quality_assurance1 = By.xpath("(//span[normalize-space()='Quality Assurance'])[1]");

    By check_text_quality_assurance2 = By.xpath("(//span[normalize-space()='Quality Assurance'])[2]");

    By check_text_istanbul_turkey1 = By.xpath("(//div[@class='position-location text-large'])[1]");

    By check_text_istanbul_turkey2 = By.xpath("(//div[@class='position-location text-large'])[2]");

    By check_view_role_button = By.xpath("(//a[contains(text(), 'View Role')])[1]");

    By check_box1 = By.xpath("(//section[3]//div[2]//div[1])[1]");

    By check_apply_for_this_job_button = By.xpath("(//a[contains(@class, 'postings-btn template-btn-submit shamrock')])[1]");

    By check_text = By.xpath("//div[normalize-space()='Istanbul, Turkiye']");

    public Quality_Assurance_Page_Objects open_quality_assurance_page(String qualityPageURL) {
        getUrl(qualityPageURL);
        getInfoMessage("Career sayfasi acildi");
        return this;
    }

    public Quality_Assurance_Page_Objects check_quality_assurance_page_is_open () {
        waitUntilAndClick(click_see_all_qa_jobs);
        waitForLoad(1000);
        Assert.assertTrue(getCurrentUrl().contains("department=qualityassurance") || elementIsDisplayed(text_filter_by_location),"Quality Assurance sayfasi açılmadi");
        getInfoMessage("Quality Assurance sayfasi acildi");
        return this;
    }

    //public Quality_Assurance_Page_Objects click_all_button (){
    //    scrollToElement(filter_all_button);
    //    untilElementIsVisible(filter_all_button);
    //    waitForLoad(1000);
    //    waitUntilAndClick(filter_all_button);
        //waitForLoad(1000);
        //waitUntilAndClick(filter_istanbul_turkey_button);
        //waitForLoad(1000);
    //    return this;
    //}

    public Quality_Assurance_Page_Objects click_combobox_button() {
        scrollToElement(filter_by_location_button);
        untilElementIsVisible(filter_by_location_button);
        waitForLoad(20000); // biraz daha uzun bekle

        try {
            //refreshPage();
            waitUntilAndClick(filter_by_location_button);
            waitForLoad(2000);
        } catch (Exception e) {
            System.out.println("Normal click başarısız, JS ile tıklanıyor...");
            WebElement el = driver.findElement(filter_by_location_button);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        }
        //waitForLoad(2000);
        waitUntilAndClick(filter_istanbul_turkey_option);
        //waitForLoad(2000);
        try {
            //refreshPage();
            waitUntilAndClick(filter_by_department_button);
            waitForLoad(2000);
        } catch (Exception e) {
            System.out.println("Normal click başarısız, JS ile tıklanıyor...");
            WebElement el = driver.findElement(filter_by_department_button);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        }
        waitForLoad(2000);
        waitUntilAndClick(filter_quality_assurance_option);
        waitForLoad(5000);
        Assert.assertTrue(getCurrentUrl().contains("department=qualityassurance") || elementIsDisplayed(text_browse),"Filtreleme düzgün yapılamadı.");
        getInfoMessage("Filtreleme düzgün yapıldı.");


        return this;
    }

    public Quality_Assurance_Page_Objects assert_position1_is_open(){
        scrollToElement(check_text_quality_assurance1);
        untilElementIsVisible(check_text_quality_assurance1);
        Assert.assertTrue(elementIsDisplayed(check_text_quality_assurance1) && elementIsDisplayed(check_text_istanbul_turkey1),"İlk pozisyon düzgün görüntülenmedi.");
        getInfoMessage("İlk pozisyon düzgün görüntülendi.");
        return this;
    }

    public Quality_Assurance_Page_Objects assert_position2_is_open(){
        scrollToElement(check_text_quality_assurance2);
        untilElementIsVisible(check_text_quality_assurance2);
        Assert.assertTrue(elementIsDisplayed(check_text_quality_assurance2) && elementIsDisplayed(check_text_istanbul_turkey2),"İlk pozisyon düzgün görüntülenmedi.");
        getInfoMessage("İlk pozisyon düzgün görüntülendi.");
        return this;
    }

    public Quality_Assurance_Page_Objects assert_view_role_page_is_open(){
        clickElement(check_box1);
        waitForLoad(5000);
        //untilElementIsClickable(check_view_role_button);
        //untilElementIsVisible(check_view_role_button);
        waitUntilAndClick(check_view_role_button);
        waitForLoad(15000);
        Assert.assertTrue(elementIsDisplayed(check_text), "İş ilan sayfasi acilmadi.");
        getInfoMessage("İş ilan sayfasi acildi.");
        return this;
    }








}
