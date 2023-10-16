import factory.FactoryWebDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

public class Test1 {

    WebDriver driver = new FactoryWebDriver().getWebDriver();

    public static Date applyDateFromString(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM");
        return formatter.parse(date);
    }

    @Test
    public void test1() throws InterruptedException, ParseException {
        driver.get("https://otus.ru");
        WebElement footer1 = driver.findElement(By.tagName("footer"));
        //  driver.manage().addCookie(new Cookie("key", "value"));

        List<WebElement> specCards = driver.findElements(By.xpath("//div[@class='sc-12yergf-11 fgNPoG']"));
        List<WebElement> specialNameList = driver.findElements(By.xpath("//div[@class='sc-12yergf-10 bddZLL']"));
        WebElement specialPriceList = driver.findElement(By.xpath("//span[@class='sc-1pljn7g-3 cdTYKW']"));
        List<WebElement> specialPrice = driver.findElements(By.xpath("//span[@class='sc-12yergf-7 dPBnbE']"));


        List<WebElement> courseCards = driver.findElements(By.xpath("//div[@class='sc-1pljn7g-6 kbUYTE']"));
        List<WebElement> courseNameList = driver.findElements(By.xpath("//h5[@class='sc-1pljn7g-1 hvCeDA']"));
        WebElement coursePriceList = driver.findElement(By.xpath("//span[@class='sc-1pljn7g-3 cdTYKW']"));
        WebElement coursePrice = driver.findElement(By.xpath("//div[@class='sc-1pljn7g-2 iWNTGn']"));

//        Actions actions = new Actions(driver);
//        actions.scrollToElement(footer1).perform();


        List<WebElement> courseList1 = driver.findElements(By.xpath("//h5[@class='sc-1pljn7g-1 hvCeDA']"));
        courseList1.addAll(driver.findElements(By.xpath("//div[@class='sc-12yergf-10 bddZLL']")));


        String field = "Linux";
        WebElement findedCourse = courseList1.stream().filter(name -> name.getText().contains(field)).findFirst().get();
        highLighterMethod(driver, findedCourse);
        sleep(3000);



        for (WebElement el : specialPrice) {
            System.out.println(el.getText().substring(2).split("\n")[0]);
        }

        System.out.println("result начала курса");
        System.out.println(getFirstStartedCourse(specialPrice).get().getText());
        System.out.println("result конец рассчета курса");

//        List<String> dateList = specCards.stream()
//                .forEach(x -> x.findElement(By.xpath("//div[@class='sc-1pljn7g-2 iWNTGn']")).getText().substring(2));
//              //  .collect(Collectors.toList());

        System.out.println(courseList1.stream().filter(name -> name.getText().contains(field)).findFirst().get().getText());
       // findedCourse.click();
        getFirstStartedCourse(specialPrice).get().click();
        //  courseList1.stream().filter(name -> name.getText().contains(field)).findFirst().get().click();
        sleep(2000);




        driver.quit();


    }

    public Optional<WebElement> getFirstStartedCourse(List<WebElement> elements){
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        return elements.stream().reduce((el1, el2) -> {
            try {
                return formatter.parse(el1.getText().substring(2).split("\n")[0]).before(formatter.parse(el2.getText().substring(2).split("\n")[0])) ? el1 : el2;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    public void highLighterMethod(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 60px solid red;');", element);
    }


}
