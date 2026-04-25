package LoianeTrainingJavaAulas.licoesdecasa.aula28_29.ex1;

public class Teste {

    public static void main(String[] args) {
        testarQuadrado();
        testarTriangulo();
        testarCirculo();
        testarCubo();
        testarCilindro();

        System.out.println("Todos os testes passaram com sucesso.");
    }

    private static void testarQuadrado() {
        Quadrado quadrado = new Quadrado("Azul", 4, 4);
        assertAproximado("Area do quadrado", 16.0, quadrado.calcularArea());
    }

    private static void testarTriangulo() {
        Triangulo triangulo = new Triangulo("Verde", 10, 6);
        assertAproximado("Area do triangulo", 30.0, triangulo.calcularArea());
    }

    private static void testarCirculo() {
        Circulo circulo = new Circulo("Vermelho", 0, 0);
        circulo.setRaio(2);
        assertAproximado("Area do circulo", 12.56, circulo.calcularArea());
    }

    private static void testarCubo() {
        Cubo cubo = new Cubo("Branco", 3, 3);
        assertAproximado("Area do cubo", 54.0, cubo.calcularArea());
        assertAproximado("Volume do cubo", 27.0, cubo.calcularVolume());
    }

    private static void testarCilindro() {
        Cilindro cilindro = Cilindro.criarComRaio("Cilindro", "Cinza", 0, 5, 2);
        assertAproximado("Area do cilindro", 87.92, cilindro.calcularArea());
        assertAproximado("Volume do cilindro", 62.8, cilindro.calcularVolume());
    }

    private static void assertAproximado(String nomeTeste, double esperado, double atual) {
        double tolerancia = 0.0001;
        if (Math.abs(esperado - atual) > tolerancia) {
            throw new IllegalStateException(
                nomeTeste + " falhou. Esperado: " + esperado + " | Obtido: " + atual
            );
        }
        System.out.println(nomeTeste + " ok -> " + atual);
    }
}
