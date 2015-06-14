package crossbrowsertest;

import static org.testng.AssertJUnit.fail;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TC02_Test2 {
	 public WebDriver driver = null;
	 public Assignment home;
	 public String url = "http://www.investing.com/";

	/**
	 * Instantiate the TestNG Before Class Method.
	 * 
	 * @param sEnv - environment
	 * @throws Exception - error
	 */
	
	@Parameters("browser") 
	@BeforeClass(alwaysRun = true)
	public void startSelenium(String browser) {
		try {
	        home = new Assignment();
	        home.beforeMethod(browser);
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
    @Test(groups = {"full", "smoke", "all"})
    @Parameters({ "sUsername", "sPassword" })
    public void verifySigninSignout(String sUsername, String sPassword) throws Exception {
    	home.verifySigninSingout(sUsername, sPassword);
    }
}
