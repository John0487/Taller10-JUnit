/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.calculatorsmp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import com.mycompany.calculatorsmp.Operations;

/**
 *
 * @author josmocobos
 */
public class OperationsTest {
    
    @Test
    @DisplayName("División por cero debe lanzar ArithmeticException")
    void testDivisionByZero() {
        ArithmeticException exception = assertThrows(
            ArithmeticException.class,
            () -> Operations.solve("10/0"),
            "Dividir por cero debería lanzar ArithmeticException"
        );
        
        assertEquals("División por cero no permitida", exception.getMessage());
    }
    
    @Test
    @DisplayName("División por cero en expresiones complejas")
    void testDivisionByZeroInComplexExpression() {
        assertThrows(
            ArithmeticException.class,
            () -> Operations.solve("5+3/0-2"),
            "División por cero en expresión compleja debería lanzar ArithmeticException"
        );
    }
    
    @Test
    @DisplayName("Fórmula vacía debe lanzar IllegalArgumentException")
    void testEmptyFormula() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> Operations.solve(""),
            "Una fórmula vacía debería lanzar IllegalArgumentException"
        );
        
        assertTrue(exception.getMessage().contains("Fórmula vacía") || 
                  exception.getMessage().contains("inválida"),
            "El mensaje debería indicar que la fórmula está vacía o es inválida");
    }
    
    @Test
    @DisplayName("Fórmula null debe lanzar IllegalArgumentException")
    void testNullFormula() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> Operations.solve(null),
            "Una fórmula null debería lanzar IllegalArgumentException"
        );
        
        assertTrue(exception.getMessage().contains("null") || 
                  exception.getMessage().contains("inválida"),
            "El mensaje debería indicar que la fórmula es null o inválida");
    }
    
    @Test
    @DisplayName("Operador inválido debe lanzar IllegalArgumentException")
    void testInvalidOperator() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> Operations.solve("5@3"),
            "Un operador inválido debería lanzar IllegalArgumentException"
        );
        
        assertTrue(exception.getMessage().contains("Operador") || 
                  exception.getMessage().contains("inválido"),
            "El mensaje debería indicar que el operador no es válido");
    }
    
    @Test
    @DisplayName("Paréntesis desbalanceados deben lanzar IllegalArgumentException")
    void testUnbalancedParentheses() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, 
                () -> Operations.solve("(5+3"), 
                "Paréntesis de apertura sin cierre"),
            () -> assertThrows(IllegalArgumentException.class, 
                () -> Operations.solve("5+3)"), 
                "Paréntesis de cierre sin apertura"),
            () -> assertThrows(IllegalArgumentException.class, 
                () -> Operations.solve("((5+3)"), 
                "Múltiples paréntesis desbalanceados")
        );
    }
    
    @Test
    @DisplayName("Fórmula que termina con operador debe lanzar IllegalArgumentException")
    void testFormulaEndingWithOperator() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, 
                () -> Operations.solve("5+3+"), 
                "Fórmula terminando en suma"),
            () -> assertThrows(IllegalArgumentException.class, 
                () -> Operations.solve("10*2-"), 
                "Fórmula terminando en resta"),
            () -> assertThrows(IllegalArgumentException.class, 
                () -> Operations.solve("8/"), 
                "Fórmula terminando en división")
        );
    }
    
    @Test
    @DisplayName("Fórmula que empieza con operador (excepto signo negativo)")
    void testFormulaStartingWithOperator() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, 
                () -> Operations.solve("+5+3"), 
                "Fórmula empezando con suma"),
            () -> assertThrows(IllegalArgumentException.class, 
                () -> Operations.solve("*5+3"), 
                "Fórmula empezando con multiplicación"),
            () -> assertThrows(IllegalArgumentException.class, 
                () -> Operations.solve("/5+3"), 
                "Fórmula empezando con división")
        );
    }
    
    @Test
    @DisplayName("Paréntesis vacíos deben lanzar IllegalArgumentException")
    void testEmptyParentheses() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> Operations.solve("5+()"),
            "Paréntesis vacíos deberían lanzar IllegalArgumentException"
        );
        
        assertTrue(exception.getMessage().contains("vacío") || 
                  exception.getMessage().contains("inválido"),
            "El mensaje debería indicar paréntesis vacíos");
    }
    
    @Test
    @DisplayName("Overflow en operaciones debe manejarse correctamente")
    void testOverflowHandling() {
        // Test con números muy grandes que podrían causar overflow
        assertDoesNotThrow(() -> {
            String largeNumberFormula = "999999999999999*999999999999999";
            Operations.solve(largeNumberFormula);
        }, "Operaciones con números grandes no deberían lanzar excepciones inesperadas");
    }
    
    @Test
    @DisplayName("Potencia con base cero y exponente negativo")
    void testZeroPowerNegativeExponent() {
        ArithmeticException exception = assertThrows(
            ArithmeticException.class,
            () -> Operations.solve("0^-1"),
            "Cero elevado a exponente negativo debería lanzar ArithmeticException"
        );
        
        assertTrue(exception.getMessage().contains("cero") || 
                  exception.getMessage().contains("potencia"),
            "El mensaje debería mencionar el problema con cero y potencia negativa");
    }
    
    @Test
    @DisplayName("Fórmula con caracteres especiales no permitidos")
    void testSpecialCharacters() {
        String[] invalidFormulas = {
            "5+3;", "2*4:", "1+2=3", "5<3", "2>1", "3&4", "5|2"
        };
        
        for (String formula : invalidFormulas) {
            assertThrows(IllegalArgumentException.class, 
                () -> Operations.solve(formula),
                () -> "La fórmula '" + formula + "' con caracteres especiales debería ser inválida");
        }
    }
}
