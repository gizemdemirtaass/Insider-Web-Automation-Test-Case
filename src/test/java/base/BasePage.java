package base;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.extentReport.ExtentTestManager;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BasePage {
    public WebDriver driver;

    public WebDriverWait wait;

    protected JavascriptExecutor js;

    List<String> getElementTexts = new ArrayList<>();

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void getUrl(String url) {
        driver.get(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void clearTextField(By elementBy) {
        WebElement element = driver.findElement(elementBy);
        element.click();
        waitForLoad(2000);
        element.clear();
        while (!element.getAttribute("value").isEmpty()) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    public void clickRandomElement() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> images = driver.findElements(By.tagName("img"));
        if (images.size() > 0) {
            wait.until(ExpectedConditions.visibilityOfAllElements(images));
            Random random = new Random();
            int randomIndex = random.nextInt(images.size());
            WebElement randomImage = images.get(randomIndex);

            wait.until(ExpectedConditions.elementToBeClickable(randomImage));

            randomImage.click();

            waitForLoad(2000);
        } else {
            System.out.println("No images found on the page.");
        }
    }


    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void fillInputTextBox(By elementBy, String text) {
        untilElementIsVisible(elementBy);
        HighlightElement(driver.findElement(elementBy));
        clearTextField(elementBy);
        waitForLoad(500);
        driver.findElement(elementBy).sendKeys(text);
    }

    protected void sendTextPressEnter(By elementBy, String text) {
        HighlightElement(driver.findElement(elementBy));
        driver.findElement(elementBy).sendKeys(text);
        driver.findElement(elementBy).sendKeys(Keys.ENTER);
    }

    protected void clickElement(By elementBy) {
        untilElementIsVisible(elementBy);
        HighlightElementClick(driver.findElement(elementBy));
        driver.findElement(elementBy).click();
    }

    protected String getHiddenAttribute(By elementBy) {
        untilElementIsVisible(elementBy);
        HighlightElementClick(driver.findElement(elementBy));
        return driver.findElement(elementBy).getAttribute("aria-hidden");
    }

    protected void uploadFile(By elementBy, String path) {
        untilElementIsVisible(elementBy);
        HighlightElementClick(driver.findElement(elementBy));
        driver.findElement(elementBy).sendKeys(path);
    }

    protected String getInfoMessage(String message) {
        ExtentTestManager.getTest().log(Status.INFO, message);
        return message;
    }

    protected boolean elementIsPresent(By element) {
        waitForLoad(2000);
        return driver.findElements(element).size() > 0;
    }

    protected int elementSize(By element) {
        waitForLoad(2000);
        return driver.findElements(element).size();
    }

    protected WebElement findWebElement(By elementBy) {
        WebElement el = driver.findElement(elementBy);
        HighlightElement(el);
        return el;
    }

    protected int sizeOfList(By element) {
        return driver.findElements(element).size();
    }

    protected List<String> getTextList(By element) {
        getElementTexts.clear();
        List<WebElement> listElement = driver.findElements(element);
        for (int i = 0; i < listElement.size(); i++) {
            waitForLoad(100);
            getElementTexts.add(listElement.get(i).getText());
        }
        return getElementTexts;
    }

    protected List<String> getTextListLowerCase(By element) {
        getElementTexts.clear();
        List<WebElement> listElement = driver.findElements(element);
        for (int i = 0; i < listElement.size(); i++) {
            waitForLoad(100);
            getElementTexts.add(listElement.get(i).getText().toLowerCase());
        }
        return getElementTexts;
    }

    protected void clickElementIsPresent(By element) {
        if (elementIsPresent(element)) {
            clickElement(element);
        } else {
            waitForLoad(3000);
            clickElement(element);
        }
    }

    public void MouseClickEvent(By element) {
        WebElement elementToHover = driver.findElement(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(elementToHover).click().perform();
        waitForLoad(3000);
    }

    public boolean isSubtitleIconEnabled(By element) {
        WebElement subtitleIcon = driver.findElement(element);
        String classAttribute = subtitleIcon.getAttribute("class");
        return !classAttribute.contains("disabled");
    }

    public boolean isElementDisabled(By element) {
        try {
            WebElement webElement = driver.findElement(element);

            String opacity = webElement.getCssValue("opacity");
            if ("0".equals(opacity)) {
                return true;
            }

            String pointerEvents = webElement.getCssValue("pointer-events");
            if ("none".equals(pointerEvents)) {
                return true;
            }
            return false;
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Öğe bulunamadı: " + element.toString());
        }
    }

    private void retryClick(By subtitleIcon, int i, int i1) {
    }

    protected WebElement untilElementIsVisible(By elementBy) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
            HighlightElement(element);
            return element;
        } catch (Exception e) {
            return null;
        }
    }

    public void HighlightElement(WebElement el) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", el,
                " border: 3px solid red;");
    }

    protected void HighlightElementClick(WebElement el) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", el,
                " border: 3px solid blue;");
    }

    protected void waitForLoad(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void switchWindow(int index) {
        driver.switchTo().frame(index);
    }

    public void switchTab(int index) {
        ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTb.get(index));
    }

    public boolean pageSourceContain(String message) {
        waitForLoad(2000);
        return driver.getPageSource().contains(message);
    }

    protected String getTextofElement(By elementBy) {
        waitForLoad(3000);
        untilElementIsVisible(elementBy);
        return driver.findElement(elementBy).getText();
    }

    protected boolean elementIsDisplayed(By elementBy) {
        return driver.findElement(elementBy).isDisplayed();
    }

    public boolean elementExists(By element) {
        try {
            // Eğer elementin bulunduğu bir liste boş değilse, element sayfada mevcut demektir
            return !driver.findElements(element).isEmpty();
        } catch (Exception e) {
            // Eğer herhangi bir hata oluşursa (örneğin, driver kapalıysa), false döndür
            return false;
        }
    }


    protected boolean elementIsEnabled(By elementBy) {
        return driver.findElement(elementBy).isEnabled();
    }

    protected boolean untilElementIsClickable(By elementBy) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(elementBy));
            HighlightElement(driver.findElement(elementBy));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //protected void waitUntilAndClick(By elementBy) {
    //    untilElementIsClickable(elementBy);
    //    waitForLoad(100);
    //    clickElement(elementBy);
    //}
    protected void waitUntilAndClick(By elementBy) {
        scrollToElement(elementBy); // elementi görünür alana getir
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(elementBy));
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            // Alternatif çözüm: JavaScript ile tıklama
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }


    protected List findElementsOfList(By elementBy) {
        return driver.findElements(elementBy);
    }

    protected void scrollToElement(By elementBy) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(elementBy);
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    protected void scrollInView(By elementBy) {
        waitForLoad(1000);
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(elementBy);
        actions.moveToElement(element);
        actions.clickAndHold();
        actions.moveToElement(element);
        actions.release().perform();
    }

    /**
     * WebElement element = driver.findElement(By.id("id_of_element"));
     * ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
     * Thread.sleep(500);
     */
    protected void scrollDown() {
        waitForLoad(1000);
        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        jsx.executeScript("window.scrollBy(0,450)", "");
    }

    protected void scrollUp() {
        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        jsx.executeScript("window.scrollBy(0,-450)", "");
    }

    protected void switchContent() {
        driver.getWindowHandles().forEach(tab -> driver.switchTo().window(tab));
    }

    protected WebElement untilElementIsPresence(By elementBy) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
            HighlightElement(element);
            return element;
        } catch (Exception e) {
            return null;
        }
    }

    protected void moveMouse() {
        Actions action = new Actions(driver);
        // Mouse'u tarayıcının sol üst köşesine taşır.
        action.moveToElement(driver.findElement(By.tagName("body")), 0, 0).perform();
    }


    public void moveMouseToElement(By element) {
        WebElement elementToHover = driver.findElement(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(elementToHover).perform();
    }

    public Dimension getScreenSize() {
        return driver.manage().window().getSize();
    }
}
