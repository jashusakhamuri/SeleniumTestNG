package UI.ambTestLabTests;

import UI.ambTestLabUtils.JSONTestDataFactory;
import UI.ambTestLabUtils.model.Fund;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import UI.ambTestLabPages.AdminMutualPage;

public class AdminMutualTest extends BaseTest {
    private WebDriver driver;
    private AdminMutualPage adminMutualPage;
    private Fund fund;

    @BeforeMethod
    public void setUpMutualPage() {
       fund = JSONTestDataFactory.readJson("testdata/fund.json", Fund.class);

        driver = getDriver();
        loginAsAdmin();
        adminMutualPage = new AdminMutualPage(getDriver());
        adminMutualPage.gotoMutual();
    }


    @Test
    public void getallCardnames() {
        adminMutualPage.collectNamesAcrossPages(1, 2, 3);
    }

    @Test
    public  void filltheForm(){

        adminMutualPage.fillFundForm(fund);
        adminMutualPage.saveFund();
        adminMutualPage.collectNamesAcrossPages(1);
    }


}