
import com.mycompany.calculatorsmp.Operations;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;


public class OperationsTestMakeFormula {
    @DisplayName("Probar que el metodo MakeFormula da valores diferentes")
    @Test
    public void Mf1(){
        String formula1 = Operations.MakeFormula();
        String formula2 = Operations.MakeFormula();
        assertNotEquals(formula1, formula2); 
    }
    
    @DisplayName("Probar que el metodo MakeFormula crea una formula con al menos un numero y un operador")
    @Test
    public void Mf2() {
    String formula = Operations.MakeFormula();
    assertFalse(formula.isEmpty());
    }
    
    @DisplayName("Probar que el metodo MakeFormula crea una formula con maximo dos operadores")
    @Test
    public void Mf3() {
        String formula = Operations.MakeFormula();
        long contador = formula.chars().filter(c -> "+-*/".indexOf(c) >= 0).count();
        assertTrue(contador <= 2);
    }
    
}

   
    

