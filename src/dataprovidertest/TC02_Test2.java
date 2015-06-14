package dataprovidertest;

import static org.testng.AssertJUnit.fail;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
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
    
    @DataProvider(name = "Authentication")
    public static Object[][] credentials() {
          return new Object[][] { { "mzazam07@gmail.com", "nyc2015" }, { "testuser_1", "Test@123" }};
    }

    /*************************************************************************************
     * @throws Throwable No Return values are needed
     * 
     *************************************************************************************/
    @Test(dataProvider = "Authentication", groups = {"regression", "smoke", "all"})
    public void verifySigninSignout(String sUsername, String sPassword) throws Exception {
    	home.verifySigninSingout(sUsername, sPassword);
    }
}
