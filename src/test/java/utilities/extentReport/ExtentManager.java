package utilities.extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;

public class ExtentManager {
    private static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./Insider Test Report - " + java.time.LocalDate.now() + ".html");
        try {
            reporter.loadXMLConfig(new File("extent_config.xml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        reporter.config().setReportName("Insider Web Test Suite Report");
        extentReports.attachReporter(reporter);
        return extentReports;
    }
}
