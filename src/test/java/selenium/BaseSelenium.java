package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseSelenium {

    private WebDriver driver;
    private final long TIME_OUT = 15;

    public void inicializaNavegador(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/navegador/chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        System.setProperty("webdriver.chrome.args", "--disable-logging");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().deleteAllCookies();
        System.out.println("Iniciando o navegador.");
    }

    public void abrirUrl(String url){
        this.driver.get(url);
        this.driver.manage().timeouts().pageLoadTimeout(TIME_OUT, TimeUnit.SECONDS);
    }

    public void escrever(By elemento,String texto){
        busca(elemento).sendKeys(texto);
    }

    public void clicar(By elemento){
        busca(elemento).click();
    }

    public void moverParaElemento(By elemento){
        new Actions(this.driver).moveToElement(busca(elemento)).build().perform();
    }

    public String obtemTexto(By elemento){
        return busca(elemento).getText();
    }

    public void esperaSerClicavel(By elemento){
        new WebDriverWait(this.driver, TimeUnit.SECONDS.toSeconds(TIME_OUT))
                         .until(ExpectedConditions.elementToBeClickable(busca(elemento)));
    }

    public void esperaExistir(By elemento){
            new WebDriverWait(this.driver, TimeUnit.SECONDS.toSeconds(TIME_OUT))
                    .until(ExpectedConditions.presenceOfElementLocated(elemento));
    }

    public void fecharNavegador(){
        if (this.driver != null){
            this.driver.quit();
        }
    }

    private WebElement busca(By elemento){
        return this.driver.findElement(elemento);
    }
    
}
