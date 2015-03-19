package com.lifecosys.cv

import com.google.common.base.Predicate
import org.openqa.selenium.firefox.{ FirefoxDriver, FirefoxProfile }
import org.openqa.selenium.support.ui.{ ExpectedConditions, WebDriverWait }
import org.openqa.selenium.{ By, WebDriver, WebElement }

object CvServer {

  def main(args: Array[String]) {

    val driver = new FirefoxDriver(new FirefoxProfile)
    while (true) {
      println("Start voting")
      driver.get("http://cmee.gznu.edu.cn/vote/index.asp")
      wait(driver.findElement(By.cssSelector(".MainAbout2")).isDisplayed)(driver)
      val element: WebElement = driver.executeScript("""return $("td:contains('杨丹')").get(1)""").asInstanceOf[WebElement]
      element.findElement(By.name("VoTeid")) click ()
      driver.findElement(By.name("Submit")) click ()
      wait(driver.findElement(By.id("code")).isDisplayed)(driver)
      driver.executeScript("document.getElementById('code').focus()");
      new WebDriverWait(driver, 5000) until ExpectedConditions.alertIsPresent()
      val alert = driver.switchTo().alert();
      alert.accept()
      println("Vote completed, try again")
    }

    def wait(condition: ⇒ Boolean)(driver: WebDriver) =
      new WebDriverWait(driver, 5000) until new Predicate[WebDriver]() {
        override def apply(driver: WebDriver): Boolean = condition
      }

  }
}

