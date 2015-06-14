package grouptest;

import static org.testng.AssertJUnit.fail;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TC04_Test4 {
	 public WebDriver driver = null;
	 public Assignment home;
	 public String url = "http://www.investing.com/";

	/**
	 * Instantiate the TestNG Before Class Method.
	 * 
	 * @param sEnv - environment
	 * @throws Exception - error
	 */
	@BeforeClass(alwaysRun = true)
	//@Parameters("Environment")
	public void startSelenium() {
		try {
	        home = new Assignment();
	        home.beforeMethod();
		} catch (Exception e) {
			fail(e.toString());
		}

	}

    /**
     * Instantiate the TestNG After Class Method.
     * 
     * @throws Exception - error
     */
    @AfterClass(alwaysRun = true)
    public void stopSelenium() {
        try {
        	home.afterMethod();
        } catch (Exception e) {
            fail(e.toString());
        }

    }

    /*************************************************************************************
     * @throws Throwable No Return values are needed
     * 
     *************************************************************************************/
    @Test(groups = {"regression", "smoke", "all"})
    public void verifyTableData() throws Exception {
    	home.printTableData();
    }
}
