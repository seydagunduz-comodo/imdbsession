package com.imdb.componentobjects.menunavigation;

import com.imdb.base.PageUrls;
import com.imdb.componentobjects.menunavigation.MenuItems.MainMenuItems;
import com.imdb.componentobjects.menunavigation.MenuItems.SubMenuItems;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.Logger;


public class MenuNavigation {

    final WebDriver driver;
    public MenuNavigation(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    static Logger log;

    /**LOCATORS*/


    /** Main Menu Items*/
    @FindBy(css = "li[id='navTitleMenu']")
    private WebElement moviesTvShowtimesMenuItem;

    @FindBy(id = "navNameMenu")
    private WebElement celebritiesEventsPhotosMenuItem;

    @FindBy(id = "navNewsMenu")
    private WebElement newsCommunityMenuItem;

    @FindBy(id = "navWatchlistMenu")
    private WebElement watchlistMenuItem;

    private static final String DOWN_ARROW_CSS = "span[class='downArrow']";



    /** Movies, TV, Showtimes SubMenuItems */
    @FindBy(css = "[href = '/movies-in-theaters/?ref_=nv_mv_inth_1']")
    private WebElement inTheaters;

    @FindBy(css = "[href = '/showtimes/?ref_=nv_mv_sh_2']")
    private WebElement showtimesTickets;

    @FindBy(css = "[href = '/search/title?count=100&groups=oscar_best_picture_winners&sort=year,desc&ref_=nv_ch_osc_2']")
    private WebElement oscarWinners;

    @FindBy(css = "div[id = 'navMenu1']")
    private WebElement moviesSubMenu;


    /** Celebs, Events */
    @FindBy(css = "[href = '/search/name?birth_monthday=10-30&refine=birth_monthday&ref_=nv_cel_brn_1']")
    private WebElement bornToday;

    @FindBy(css = "div[id = 'navMenu2']")
    private WebElement celebSubMenu;


    private void hoverMenuDownArrow(WebElement menuItem){
        Actions action = new Actions(driver);
        try {
            WebElement downArrow = menuItem.findElement(By.cssSelector(DOWN_ARROW_CSS));
            action.moveToElement(downArrow).perform();
        } catch (Exception e){
            // TODO:
            System.out.println("cannot hover over menu: " + menuItem);
            throw e;
        }

    }

    private void waitForSubMenuVisibilty(WebElement subMenu) {
        WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.visibilityOf(subMenu));
    }

    private void hoverOnMainMenuItem(MainMenuItems mainMenuItems){
        switch (mainMenuItems) {
            case MOVIES_TV_SHOWTIMES:
                hoverMenuDownArrow(moviesTvShowtimesMenuItem);
                waitForSubMenuVisibilty(moviesSubMenu);
                break;
            case CELEBS_EVENTS_PHOTOS:
                hoverMenuDownArrow(celebritiesEventsPhotosMenuItem);
                waitForSubMenuVisibilty(celebSubMenu);
                break;
        }
    }

    private void assertPageURL(String url){
        try {
            Assert.assertTrue(driver.getCurrentUrl().equals(url));
        } catch (Exception e) {
            log.error("could not navigate to url successfully: " + url);
            throw e;
        }

    }

    public void navigateTo(SubMenuItems subMenuItems){

        switch (subMenuItems) {
            case IN_THEATERS:
                hoverOnMainMenuItem(MainMenuItems.MOVIES_TV_SHOWTIMES);
                inTheaters.click();
                assertPageURL(PageUrls.IN_THEATERS);
                break;
            case BORN_TODAY:
                hoverOnMainMenuItem(MainMenuItems.CELEBS_EVENTS_PHOTOS);
                bornToday.click();
                assertPageURL(PageUrls.BORN_TODAY);
                break;
            case OSCAR_WINNERS:
                hoverOnMainMenuItem(MainMenuItems.MOVIES_TV_SHOWTIMES);
                oscarWinners.click();
                assertPageURL(PageUrls.OSCAR_WINNERS);
                break;
        }
    }


}
