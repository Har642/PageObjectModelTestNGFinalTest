package testScripts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.TestBase;
import pages.AfterEditTitleChk;
import pages.ArticlePage;
import pages.EditPage;
import pages.LoginPage;
import pages.NewArticleDataEntry;
import pages.NewArticleHomePage;

public class NewArticleLoginTest {
	WebDriver driver;
	LoginPage loginPage;
	NewArticleHomePage newArticleHome;
	NewArticleDataEntry newArticleDataEntry;
	ArticlePage articlePage;
	EditPage editPage;
	AfterEditTitleChk articleEditTitleCheck;

	public NewArticleLoginTest() {
		TestBase.initDriver();
		driver = TestBase.getDriver();
		loginPage = new LoginPage(driver);
		newArticleHome = new NewArticleHomePage(driver);
		newArticleDataEntry = new NewArticleDataEntry(driver);
		articlePage = new ArticlePage(driver);
		editPage = new EditPage(driver);
		articleEditTitleCheck = new AfterEditTitleChk(driver);

	}

	@BeforeTest
	public void setup() throws InterruptedException {
		TestBase.openUrl("https://conduit-realworld-example-app.fly.dev/#/login");
		loginPage.ValidLogin("hari.harish11135@gmail.com", "Harish@1735");
		Thread.sleep(2000);

	}
	
	@Test(priority=1)
	public void newArticleHomePageTest() throws InterruptedException {
		boolean isPopularTagAvailable = newArticleHome.isPopularTagsVisible();
		Assert.assertTrue(isPopularTagAvailable);
		
		boolean isUsernameVisible = newArticleHome.isUsernameIsVisible();
		Assert.assertTrue(isUsernameVisible);
		Thread.sleep(2000);
		newArticleHome.goToNewArticle();
	}
	
	@Test(priority=2)
	public void dataEntry() throws InterruptedException {
		newArticleDataEntry.enterDataIntoFields("Critical Thinking Skills.", "It's about the critical thinking in IT industry",
				"Critical thinking is the ability to analyze information objectively and make a reasoned judgment. It involves evaluating sources, such as data, facts, observable phenomena, and research findings.",
				"Developer,Tester,Support");
		Thread.sleep(2000);
		newArticleDataEntry.clickSubmitBtn();
		
	}
	
	@Test(priority=3)
	public void articleHomeCheck() throws InterruptedException {
		Thread.sleep(2000);
		String chkTitleInArticlePage = articlePage.titleTxtCheck();
		Assert.assertEquals(chkTitleInArticlePage, "Critical Thinking Skills.");
		Thread.sleep(2000);
		
		articlePage.clickOnEditBtn();
		
	}
	
	@Test(priority=4)
	public void updateTheData() throws InterruptedException {
		editPage.removeTxt1();
		editPage.updateData1("Critical Thinking Skills in IT Industry.");
		Thread.sleep(3000);
		
		editPage.removeTxt2();
		editPage.updateData2("Developing critical thinking skills is essential for academic success and everyday decision-making. Here are strategies and examples to help enhance critical thinking skills.");
		Thread.sleep(3000);
		
		editPage.removeTxt3();
		editPage.updateData3("Implementation,Backend,Frontend");
		Thread.sleep(3000);
		
		editPage.clickOnUpdateBtn();
	}
	
	@Test(priority=5)
	public void finalTest() throws InterruptedException {
		String chkTitleInEditArticlePage = articleEditTitleCheck.titleCheck();
		Assert.assertEquals(chkTitleInEditArticlePage, "Critical Thinking Skills in IT Industry.");
		
		Thread.sleep(5000);
		articleEditTitleCheck.clickOnDeleteBtn();
		
		//If  want to delete the article it will ask me for alert "OK", so for that we are accepting that alert, then it will deleted.
		Alert alert = driver.switchTo().alert();
		System.out.println("Alert Text:..." +alert.getText());
		Thread.sleep(1000);
		alert.accept();

		Thread.sleep(2000);

		//After everything I want to close the driver, so I'm using the instance of the tearDown driver
		TestBase.tearDown();
	}
	
	
	
	
}
