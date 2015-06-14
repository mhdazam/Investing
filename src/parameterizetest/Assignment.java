package parameterizetest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.opera.core.systems.OperaDriver;

public class Assignment {
 public WebDriver driver = null;
 public String url = "http://www.investing.com/";
 String driverName = "Chrome";
  
  public void beforeMethod() throws FileNotFoundException {
	  driver = getDriver(driverName);
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.navigate().to(url);
  }
  
  public void afterMethod() {
	  driver.quit();
  }

  public void verifyRegistration() throws Exception {	  
  	  //gotoHomepage();
  	  registration();
  }
  
  public void verifySigninSingout(String username, String password) throws Exception {
	  //gotoHomepage();
	  signin(username, password);
	  verifyTitle();
	  signout();
  }
  
  public void verifyLinkText() throws Exception {
	  //gotoHomepage();
	  linkText();
  }
  
  public void printTableData() throws Exception {
	  //gotoHomepage();
	  getTableValues();
  }
  
  
  //access to home page
  public void gotoHomepage() throws Exception {
	  Set<String> listOfWindow = driver.getWindowHandles();
	  Iterator<String> it = listOfWindow.iterator();
	  String popUpWindow = (String) it.next();
	  driver.switchTo().window(popUpWindow);
	  driver.findElement(By.linkText("Continue to Investing.com")).click();
	  driver.switchTo().defaultContent();
  }
  
  //registration
  public void registration() throws Exception {
	  click("//*[@id='userAccount']/div/a[2]");
	  Set<String> listOfWindow = driver.getWindowHandles();
	  Iterator<String> it = listOfWindow.iterator();
	  String popUpWindow = (String) it.next();
	  driver.switchTo().window(popUpWindow);
	  waitUntilVisible("//*[@id='RegistersiteuserForm_user_firstname']");
	  type("//*[@id='RegistersiteuserForm_user_firstname']", getMemberData("FirstName"));
	  type("//*[@id='RegistersiteuserForm_user_lastname']", getMemberData("LastName"));
	  select("//*[@id='RegistersiteuserForm_company_country_ID']", getMemberData("Country"));
	  type("//*[@id='RegistersiteuserForm_member_phone_area']", getMemberData("AreaCode"));
	  type("//*[@id='RegistersiteuserForm_member_phone_phone']", getMemberData("PhoneNumber"));
	  type("//*[@id='RegistersiteuserForm_user_email']", getMemberData("Email"));
	  type("//*[@id='RegistersiteuserForm_password']", getMemberData("Password"));
	  click("//*[@id='RegistersiteuserForm_termsAndConditions']");
	  waitUntilClickable("//*[@id='RegistersiteuserForm']/ul/li[9]/div/a");
	  click("//*[@id='RegistersiteuserForm']/ul/li[9]/div/a");
  }
  
  //sing in 
  public void signin(String email, String password) {
	  click("//*[@id='userAccount']/div/a[1]");
	  Set<String> listOfWindow = driver.getWindowHandles();
	  Iterator<String> it = listOfWindow.iterator();
	  String popUpWindow = (String) it.next();
	  driver.switchTo().window(popUpWindow);
	  waitUntilVisible("//*[@id='loginpage_email']");
	  type("//*[@id='loginpage_email']", email);
	  type("//*[@id='loginpage_password']", password);
	  waitUntilClickable("//*[@id='loginform']/ul/li[4]/a");
	  click("//*[@id='loginform']/ul/li[4]/a");
	  driver.switchTo().defaultContent();
  }
  
  //sign out
  public void signout() {
	  mouseOver("//*[@id='userAccount']/div[1]/span/a/span");
	  waitUntilVisible("//*[@id='myAccountHeaderPop']/div/ul[3]/li/a");
	  click("//*[@id='myAccountHeaderPop']/div/ul[3]/li/a");  
	  waitUntilVisible("//*[@id='userAccount']/div/a[1]");
	  boolean signin = isVisible("//*[@id='userAccount']/div/a[1]");
	  Assert.assertTrue(signin, "Signout was not successful");
  }
  
  //verify page title
  public void verifyTitle() {
	  String title = getTitleOfthePage();
	  Assert.assertEquals(title, "Investing.com - Stock Market Quotes & Financial News");
  }
  
  //validate element visible text
  public void linkText() throws Exception {
	  mouseOverAndValidateText("//*[@id='sb_pair_8839']/td[2]/a", "S&P 500 Futures"); 
	  mouseOverAndValidateText("//*[@id='sb_pair_8874']/td[2]/a", "Nasdaq Futures"); 
	  mouseOverAndValidateText("//*[@id='sb_pair_169']/td[2]/a", "US 30"); 
	  mouseOverAndValidateText("//*[@id='sb_pair_44336']/td[2]/a", "S&P 500 VIX"); 
	  mouseOverAndValidateText("//*[@id='sb_pair_172']/td[2]/a", "DAX"); 
	  mouseOverAndValidateText("//*[@id='sb_pair_178']/td[2]/a", "Nikkei 225"); 
	  mouseOverAndValidateText("//*[@id='sb_pair_8827']/td[2]/a", "US Dollar Index"); 
  }
  
  //retrieve table data
  public void getTableValues() throws Exception{
	  int rowCount = driver.findElements(By.xpath("//*[@id='leftColumn']/table[2]/tbody/tr")).size();
	  System.out.println("Number Of Rows = " + rowCount);
 
	  int colCount = driver.findElements(By.xpath("//*[@id='leftColumn']/table[2]/tbody/tr[1]/td")).size();
	  System.out.println("Number Of Columns = " + colCount);
 
	  String first_part = "//*[@id='leftColumn']/table[2]/tbody/tr[";
	  String second_part = "]/td[";
	  String third_part = "]";
	  String fourth_part = "/a";
 
	  for (int i=1; i <= rowCount; i++){
		  String final_xpath = first_part+i+second_part+"1"+third_part+fourth_part;
		  String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
		  System.out.print(Table_data +"  "); 
		  
		  for(int j=2; j<=colCount-1; j++){
			  String final_xpath1 = first_part+i+second_part+j+third_part;
			  String Table_data1 = driver.findElement(By.xpath(final_xpath1)).getText();
			  System.out.print(Table_data1 +"  ");   
		  }
		  System.out.println("");
		  System.out.println("");  
	  } 
  }
  
  //mouse over on element and verify text
  public void mouseOverAndValidateText(String locator, String expected) throws Exception {
	  mouseOver(locator);
	  String text = getText(locator);
	  System.out.println(text);
	  Assert.assertEquals(text, expected, "Expected doesn't match with actual");
  } 

  //choose a browser
  public WebDriver getDriver(String driverName) {
		
		switch(driverName) {
	    case "Firefox" : 	
	    	driver = new FirefoxDriver();
	    	break;
	    
	    case "Chrome" : 	
	       	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\config\\browser-driver\\chromedriver.exe");
			driver = new ChromeDriver();
	    	break;
	    
	    case "IE" : 	
	    	System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\src\\config\\browser-driver\\IEDriverServer.exe");
	    	driver = new InternetExplorerDriver();
	    	break;
	    
	    case "Safari" : 	
	    	 driver = new SafariDriver();
	    	break;
	    	
	    case "HtmlUnit" :
	    	 driver = new HtmlUnitDriver();
	    	 break;
	    	 
	    case "Opera" :
	    	 driver = new OperaDriver();
	    	 break;
	    	 
	    default:
	    	System.out.println("Unknown Driver");
	    	break;
		}
		return driver;
  }
  
  //get title of a web page
  public String getTitleOfthePage() {
	  String title = driver.getTitle();
	  return title;
  }

  //click element
  public void click(String locator){
	  driver.findElement(By.xpath(locator)).click();
  }

  
  //type on input box
  public void type(String locator, String value){
	  driver.findElement(By.xpath(locator)).clear();
	  driver.findElement(By.xpath(locator)).sendKeys(value);
	  
  }
  
  //find text by xpath
  public String getText(String locator){
	  String text = "";
	  text = driver.findElement(By.xpath(locator)).getText();
	  return text;
  }  
  
  //select element by option
  public void select(String webElement, String value){
	  Select select = new Select(driver.findElement(By.xpath(webElement)));
	  select.selectByVisibleText(value);
  }
  
  //check element visibility
  public boolean isVisible(String locator) {
	  boolean visible = false;
	  visible = driver.findElement(By.xpath(locator)).isDisplayed();
	  return visible;
  }
 
 //wait until element can be clicked
 @SuppressWarnings("unused")
 public void waitUntilClickable(String locator) {
	  WebDriverWait wait = new WebDriverWait(driver, 30);
	  WebElement waitFor = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
 }
 
//wait until element is visible
 @SuppressWarnings("unused")
 public void waitUntilVisible(String locator) {
	  WebDriverWait wait = new WebDriverWait(driver, 30);
	  WebElement waitFor = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
 }
 
 //mouse over on element
 public void mouseOver(String locator) {
	  try {
		  WebElement element = driver.findElement(By.xpath(locator));
		  Actions build = new Actions(driver);
		  Actions hover = build.moveToElement(element);
		  hover.perform();
	  } catch (Exception ex) {
		  WebElement element = driver.findElement(By.xpath(locator)); 
		  Locatable hover = (Locatable) element;
		  Mouse mouse = ((HasInputDevices)driver).getMouse();
		  mouse.mouseMove(hover.getCoordinates()); 
	  }
 }
 
 //retrieve member information from xml file
 public String getMemberData(String eleName){
	 String value = "";   
	 try{
	    	File file=new File(System.getProperty("user.dir")+"\\src\\config\\NewUserInfo.xml");
	    	DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
	    	DocumentBuilder db=dbf.newDocumentBuilder();
	    	Document doc=db.parse(file);
	    	doc.getDocumentElement().normalize();  

	    	NodeList nList=doc.getElementsByTagName("NewUserInfo");
	    	for(int i=0; i<nList.getLength(); i++){
	    		Node nNode=nList.item(i);
	    		if(nNode.getNodeType()==Node.ELEMENT_NODE){
	    			Element ele=(Element) nNode;
	    			value = ele.getElementsByTagName(eleName).item(i).getTextContent();
	    			System.out.println(value);
	    		}
	    	}

	    }catch(Exception e){
	        e.printStackTrace();
	    }
		return value;
  }

 public String captureScreenShot(String file){
     String path;
     try{
         File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         if(file.length() > 0){
             path = file;
         }else{
             path = source.getName();
         }
         FileUtils.copyFile(source, new File(path));
     }catch(IOException e){
         path = "Failed to capture screenshot" + e.getMessage();
     }
     return path;
 }
}























