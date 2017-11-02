package com.imdb.testsuites;

import com.imdb.base.BaseUITestCase;
import com.imdb.base.PageUrls;
import com.imdb.componentobjects.celebs.borntoday.BornToday;
import com.imdb.componentobjects.menunavigation.MenuItems.SubMenuItems;
import com.imdb.componentobjects.menunavigation.MenuNavigation;
import com.imdb.componentobjects.movies.oscarwinners.OscarWinners;
import com.imdb.componentobjects.search.Search;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.time.Month;

public class TestCase extends BaseUITestCase{

    MenuNavigation menuNavigation;
    BornToday bornToday;
    OscarWinners oscarWinners;
    Search search;


    @Before
    public void beforeMethod() {
        super.beforeMethod();
        driver.get(PageUrls.MAIN_PAGE_URL);

        menuNavigation = new MenuNavigation(driver);
        bornToday = new BornToday(driver);
        oscarWinners = new OscarWinners(driver);
        search = new Search(driver);
    }

    @Test
    public void test1(){
        menuNavigation.navigateTo(SubMenuItems.BORN_TODAY);
        Assert.assertTrue(bornToday.getActorDescription("Fiona Dourif").equals("Actress Dirk Gently's Holistic Detective Agency"));
        bornToday.selectBornTodayByDate(Month.JANUARY, 5);
    }

    @Test
    public void test2(){
        menuNavigation.navigateTo(SubMenuItems.IN_THEATERS);
//        Assert.assertTrue(oscarWinners.getRuntimeList().get(0).contains("111 min"));
//        Assert.assertTrue(oscarWinners.getMovieCountWithEmptyRuntime() == 0);
    }

    @Test
    public void test3(){
        search.clickOnSearchSuggestionsByIndex("harry potter", 0);
    }


    @After
    public void afterMethod(){
        super.afterMethod();
    }
}
