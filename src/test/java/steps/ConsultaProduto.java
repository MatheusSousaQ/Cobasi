package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class ConsultaProduto extends Base {

    private Base base;

    public ConsultaProduto(Base base) {
        this.base = base;
    }

    @Given("^que acesso o site da Cobasi$")
    public void queAcessoOSiteDaCobasi() {
        base.driver.get(base.url);
    }
    @Then("^Fecho modal de receber ofertas$")
    public void fechoModalDeReceberOfertas() {

        base.driver.switchTo().frame("social-push");

        By byFechaModalReceberOfertas = By.cssSelector("a#btClose");

        WebDriverWait wait = new WebDriverWait(base.driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(byFechaModalReceberOfertas));
        base.driver.findElement(byFechaModalReceberOfertas).click();

        base.driver.switchTo().defaultContent();
    }

    @And("^Pesquiso produto \"([^\"]*)\"$")
    public void pesquisoProduto(String produtoAPesquisar) throws InterruptedException {

        WebElement campoPesquisa = base.driver.findElement(By.name("q"));
        campoPesquisa.sendKeys(produtoAPesquisar, Keys.ENTER);
    }

    @And("^seleciono o produto \"([^\"]*)\"$")
    public void selecionoOProduto(String produtoDesejado)  {
        WebElement produto = base.driver.findElement(By.linkText(produtoDesejado));
        produto.click();
    }

    @When("^Exibe a tela do produto \"([^\"]*)\"$")
    public void exibeATelaDoProduto(String produtoDesejado)  {
        assertEquals(produtoDesejado + " - Cobasi", base.driver.getTitle());

        By byTituloDaPagina = By.cssSelector("h1.product__name");

        WebDriverWait wait = new WebDriverWait(base.driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(byTituloDaPagina));

        String tituloDaPagina = base.driver.findElement(byTituloDaPagina).getText();
        assertEquals(produtoDesejado, tituloDaPagina);
    }

    @And("^o preco a vista de \"([^\"]*)\"$")
    public void oPrecoAVistaDe(String preco)  {
        String precoProduto = base.driver.findElement(By.cssSelector("span.d-block.price__por")).getText();
        assertEquals(preco, precoProduto);

    }


}
