package com.imdb.componentobjects.search;

import com.imdb.common.CustomWait;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;



public class Search {

    final WebDriver driver;
    public Search(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = "input[id = 'navbar-query']")
    private WebElement searchBar;

    @FindBy(css = "button[id = 'navbar-submit-button']")
    private WebElement searchButton;

    @FindBy(css = "div[id='navbar-suggestionsearch']")
    private WebElement searchSuggestionPanel;

//    @FindBy(css = "div[id='navbar-suggesdsdfsdtionsearch']")
//    private WebElement searchSuggestionPanel;

    @FindBy(css = "a[class = 'moreResults']")
    private WebElement searchSuggestionsMoreResults;


    public void search(String searchText){
        searchBar.click();
        searchBar.sendKeys(searchText);
        searchButton.click();
    }


    public void waitUntilSearchSuggestionsAreClickable(){
        CustomWait.waitUntilElementVisible(driver, searchSuggestionPanel);
        CustomWait.waitUntilElementVisible(driver, searchSuggestionsMoreResults);

//        try {
//            WebElement myDynamicElement = (new WebDriverWait(driver, 1))
//                    .until(ExpectedConditions.visibilityOf(searchSuggestionPanel));
//        } catch(TimeoutException e) {
//            //log
//            throw e;
//        }
//        try {
//            WebElement myDynamicTextElement = (new WebDriverWait(driver, 10))
//                    .until(ExpectedConditions.elementToBeClickable(searchSuggestionsMoreResults));
//        } catch(TimeoutException e) {
//            //log
//            throw e;
//        }
    }

    private List<WebElement> getSearchBarSuggestionsAsWebElements(String searchText){
        searchBar.click();
        searchBar.sendKeys(searchText);
        waitUntilSearchSuggestionsAreClickable();

        List<WebElement> searchSuggestionResults = searchSuggestionPanel.findElements(By.cssSelector("a"));
        return searchSuggestionResults;
    }

    public ArrayList<String> getSearchBarSuggestions(String searchText){
        ArrayList<String> searchSuggestions = new ArrayList<>();
        for (WebElement element: getSearchBarSuggestionsAsWebElements(searchText)) {
            searchSuggestions.add(element.getText());
        }
        System.out.println(searchSuggestions);
        return searchSuggestions;
    }

    public void clickOnSearchSuggestionsByIndex(String searchText, int searchSuggestionIndex){
        getSearchBarSuggestionsAsWebElements(searchText).get(searchSuggestionIndex).click();
    }

}
