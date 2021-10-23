package Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import selenium.BaseSelenium;

public class AtividadeSelenium extends BaseSelenium {

    private final String URL = "https://chrome.google.com/webstore/category/extensions?hl=pt-br";
    private final By INPUT_PESQUISAR = By.xpath("//input[@id='searchbox-input']");
    private final By LINK_IDE = By.xpath("//a[contains(@href,'selenium-ide')]");
    private final By TEXTO_VERSAO = By.xpath("//span[text()='Versão']//../span[contains(@class,'md')]");

    @BeforeEach
    public void iniciar(){
        inicializaNavegador();
        System.out.println(String.format("Acessando a url: %s.", URL));
        abrirUrl(URL);
    }

    @ParameterizedTest
    @CsvSource({"Selenium IDE, 3.17.1"})
    public void validarPluginDoSelenium(String texto, String versao){
        System.out.println("Efetuando o teste.");
        esperaExistir(INPUT_PESQUISAR);
        escrever(INPUT_PESQUISAR, texto + Keys.ENTER);
        esperaExistir(LINK_IDE);
        esperaSerClicavel(LINK_IDE);
        clicar(LINK_IDE);
        esperaExistir(TEXTO_VERSAO);
        moverParaElemento(TEXTO_VERSAO);
        Assertions.assertEquals(versao, obtemTexto(TEXTO_VERSAO));
    }

    @AfterEach
    public void finalizar(){
        System.out.println("Fechando o navegador.");
        fecharNavegador();
    }
}
