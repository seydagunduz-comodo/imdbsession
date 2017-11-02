package com.imdb.componentobjects.movies.oscarwinners;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.ArrayList;
import java.util.List;

public class OscarWinners {

    final WebDriver driver;
    public OscarWinners(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "span[class='runtime']")
    private List<WebElement> runtime;


    // arrayList film adı ve skor, insertion order is important; duplicates allowed

    public ArrayList<String> getRuntimeList(){
        ArrayList<String> runtimeList = new ArrayList<>();
        for (int i =0; i<runtime.size(); i++){
            runtimeList.add(runtime.get(i).getText());
        }
        return runtimeList;
    }

    public int getMovieCountWithEmptyRuntime(){
        int emptyRuntimeCounter = 0;
        for (WebElement runtimeCounter:runtime) {
            if (runtimeCounter.getText().trim().length() == 0){
                emptyRuntimeCounter++;
            }
        }
        return emptyRuntimeCounter;
    }

}
