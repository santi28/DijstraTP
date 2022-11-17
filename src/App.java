// APIs
import api.GrafoTDA;
import api.ConjuntoTDA;
import api.DiccionarioSimpleTDA;
// Implementaciónes
import imp.GrafoLA;
import imp.ConjuntoLD;
import imp.DicSimpleL;

public class App {
    public static void main(String[] args) throws Exception {
        GrafoTDA grafo = new GrafoLA();
        grafo.InicializarGrafo();

        // Agregando vértices
        grafo.AgregarVertice(1);
        grafo.AgregarVertice(2);
        grafo.AgregarVertice(3);
        grafo.AgregarVertice(4);
        grafo.AgregarVertice(5);
        grafo.AgregarVertice(6);

        // Aristas superiores
        grafo.AgregarArista(1, 2, 3);
        grafo.AgregarArista(2, 4, 5);
        grafo.AgregarArista(4, 6, 2);

        // Aristas centrales
        grafo.AgregarArista(2, 3, 1);
        grafo.AgregarArista(3, 4, 10);
        grafo.AgregarArista(4, 5, 1);

        // Aristas inferiores
        grafo.AgregarArista(1, 3, 2);
        grafo.AgregarArista(3, 5, 10);
        grafo.AgregarArista(5, 6, 3);

        ConjuntoTDA recorrido = dijkstraDistance(grafo, 1, 6);
        imprimirConjunto(recorrido);
    }

    public static ConjuntoTDA dijkstraDistance(
        GrafoTDA grafo, 
        int origen, 
        int destino
    ) {
        // Crear un diccionario para guardar las distancias
        DiccionarioSimpleTDA distancias = new DicSimpleL();
        distancias.InicializarDiccionario();

        // Crear un diccionario para guardar los padres
        DiccionarioSimpleTDA padres = new DicSimpleL();
        padres.InicializarDiccionario();

        // Crear un conjunto para guardar los vértices visitados
        ConjuntoTDA visitados = new ConjuntoLD();
        visitados.InicializarConjunto();

        // Crear un conjunto para guardar los vértices no visitados
        ConjuntoTDA noVisitados = new ConjuntoLD();
        noVisitados.InicializarConjunto();

        // Inicializar los diccionarios
        ConjuntoTDA vertices = grafo.Vertices();
        while (!vertices.ConjuntoVacio()) {
            int vertice = vertices.Elegir();
            vertices.Sacar(vertice);

            distancias.Agregar(vertice, Integer.MAX_VALUE);
            padres.Agregar(vertice, -1);
            noVisitados.Agregar(vertice);
        }

        // Inicializar el vértice origen
        distancias.Agregar(origen, 0);

        // Mientras noVisitados no esté vacío
        while (!noVisitados.ConjuntoVacio()) {
            // Obtener el vértice no visitado con menor distancia
            int vertice = noVisitados.Elegir();
            int distancia = distancias.Recuperar(vertice);
            noVisitados.Sacar(vertice);

            ConjuntoTDA adyacentes = adyacentes(grafo, vertice);
            while (!adyacentes.ConjuntoVacio()) {
                int adyacente = adyacentes.Elegir();
                adyacentes.Sacar(adyacente);

                int peso = grafo.PesoArista(vertice, adyacente);
                int distanciaAdyacente = distancias.Recuperar(adyacente);

                if (distancia + peso < distanciaAdyacente) {
                    distancias.Agregar(adyacente, distancia + peso);
                    padres.Agregar(adyacente, vertice);
                }
            }
        }

        // Crear un conjunto para guardar el camino
        ConjuntoTDA camino = new ConjuntoLD();
        camino.InicializarConjunto();

        // Agregar el vértice destino al camino
        camino.Agregar(destino);

        // Mientras el vértice destino tenga un padre
        while (padres.Recuperar(destino) != -1) {
            // Agregar el padre del vértice destino al camino
            camino.Agregar(padres.Recuperar(destino));

            // Actualizar el vértice destino
            destino = padres.Recuperar(destino);
        }

        return camino;
    }

    public static ConjuntoTDA adyacentes(
        GrafoTDA grafo, 
        int vertice
    ) {
        ConjuntoTDA vertices = grafo.Vertices();
        ConjuntoTDA adyacentes = new ConjuntoLD();

        while (!vertices.ConjuntoVacio()) {
            int v = vertices.Elegir();
            vertices.Sacar(v);
            if (grafo.ExisteArista(vertice, v)) {
                adyacentes.Agregar(v);
            }
        }

        return adyacentes;
    }

    // Metodos de conjuntos

    public static void imprimirConjunto(ConjuntoTDA conjunto) {
        System.out.print("{");
        while (!conjunto.ConjuntoVacio()) {
            int vertice = conjunto.Elegir();
            conjunto.Sacar(vertice);
            System.out.print(vertice);
            if (!conjunto.ConjuntoVacio()) {
                System.out.print(", ");
            }
        }
        System.out.println("}");
    }

    // Crear un metodo que tome dos numeros (min, max) y devuelva un numero aleatorio entre min y max
    public static int random(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
