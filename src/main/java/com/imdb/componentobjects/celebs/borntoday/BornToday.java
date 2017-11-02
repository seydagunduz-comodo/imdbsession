package com.imdb.componentobjects.celebs.borntoday;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.time.Month;
import java.util.HashMap;
import java.util.List;


public class BornToday {

    final WebDriver driver;
    public BornToday(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    static Logger log;


    @FindBy(css = "table[class='results']")
    private WebElement table;

    @FindBy(css = "select[name = 'birth_month']")
    private WebElement selectMonth;

    @FindBy(css = "select[name = 'birth_day']")
    private WebElement selectDay;

    private final static String TABLE_NAME_FIELD_CSS = "td[class = 'name']";
    private final static String TABLE_NAME_DESCRIPTION_CSS = "span[class='description']";


    private HashMap<String,String> getTable(){

        // TODO: HashMap Unique Key Alıyor; name'de birden fazla var mı kontrolü yapılmalı.
        List<WebElement> rows = table.findElements(By.cssSelector("tr"));

        HashMap<String, String> bornToday = new HashMap<>();
        for (int i = 1; i<rows.size(); i++){
            WebElement name = rows.get(i).findElement(By.cssSelector(TABLE_NAME_FIELD_CSS)).findElement(By.cssSelector("a"));
            WebElement description = rows.get(i).findElement(By.cssSelector(TABLE_NAME_FIELD_CSS)).findElement(By.cssSelector(TABLE_NAME_DESCRIPTION_CSS));
            bornToday.put(name.getText(), description.getText().replaceAll(",", ""));
        }
        return bornToday;
    }

    public String getActorDescription(String actor){
        String actorDescription = getTable().get(actor);
        System.out.println(actorDescription);
        return actorDescription;
    }

    public void selectBornTodayByDate(Month month , int day){
        //month
        Select selectBirthMonth = new Select(selectMonth);
        String monthFormat = month.toString().substring(0,1) + month.toString().substring(1).toLowerCase();
        selectBirthMonth.selectByVisibleText(monthFormat);
        //day
        try {
            Select selectBirthDay = new Select(selectDay);
            selectBirthDay.selectByVisibleText(String.valueOf(day));
        }
        catch(org.openqa.selenium.NoSuchElementException e){
            log.error("No such day available for the given month.");
            throw e;
        }

    }

    public String[] getSelectedDate(){
        Select selectBirthMonth = new Select(selectMonth);
        WebElement selectedMonth = selectBirthMonth.getFirstSelectedOption();
        Select selectBirthDay = new Select(selectDay);
        WebElement selectedDay = selectBirthDay.getFirstSelectedOption();
        String[] selectedDate = {selectedMonth.toString(), selectDay.toString()};
        return selectedDate;
    }

}
