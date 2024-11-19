package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ArticlePage {
	@FindBy(xpath="(//button//a[contains(text(),'Edit')])[1]")
	WebElement editBtn;
	
	@FindBy(xpath="//h1[contains(text(),'Critical')]")
	WebElement titleChk;
	
	public ArticlePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public String titleTxtCheck() {
		System.out.println("The display Txt.."+titleChk.getText());
		return titleChk.getText();
	}
	
	public void clickOnEditBtn() {
		editBtn.click();
	}
}
