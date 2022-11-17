package prin;
import api.GrafoTDA;
import api.ConjuntoTDA;
//import api.ColaTDA;
//import api.PilaTDA;
import imp.GrafoLA;
import imp.ConjuntoLD;
//import imp.ColaLD;
//import imp.PilaLD;
public class prin {

	public static void mostrarGrafo(GrafoTDA A) {
		ConjuntoTDA V1 = A.Vertices();
		while (!V1.ConjuntoVacio()) {
			int x1 = V1.Elegir();
			V1.Sacar(x1);
			ConjuntoTDA V2 = A.Vertices();
			while (!V2.ConjuntoVacio()) {
				int x2 = V2.Elegir();
				V2.Sacar(x2);
				if (A.ExisteArista(x1,x2))
					System.out.print(x1+"->("+A.PesoArista(x1,x2)+")->"+x2+" ");
				else
					System.out.print(x1+"->(0)->"+x2+" ");
			}
			System.out.println("");
		}
	}
	
	//---------------------------------------------------------------------------------------//
	// Este procedimiento muestra la secuencia de una búsqueda DFS (Depth-First Search) en   //
	// un grafo dirigido sin pesos. Para esto, se pusieron pesos ficticios 1 en las aristas. //
	// Los nodos pendientes se colocan en una pila.                                          //
	//---------------------------------------------------------------------------------------//
/*	public static void DFS (GrafoTDA G, int v) {
		PilaTDA Pendientes = new PilaLD();
		Pendientes.InicializarPila();
		ConjuntoTDA Visitados = new ConjuntoLD();
		Visitados.InicializarConjunto();
		Pendientes.Apilar(v);
		Visitados.Agregar(v);
		while(! Pendientes.PilaVacia()) {
			int actual = Pendientes.Tope();
			System.out.print(actual+" ");
			Pendientes.Desapilar();
			ConjuntoTDA Vertices = G.Vertices();
			while (! Vertices.ConjuntoVacio()) {
				int nodo = Vertices.Elegir();
				Vertices.Sacar(nodo);
				if (G.ExisteArista(actual, nodo) && ! Visitados.Pertenece(nodo)) {
					Pendientes.Apilar(nodo);
					Visitados.Agregar(nodo);
				}
			}
		}
		System.out.println("");
	}*/

	//---------------------------------------------------------------------------------------//
	// Este procedimiento muestra la secuencia de una búsqueda BFS (Breadth-First Search)    //
	// en   un grafo dirigido sin pesos. Para esto, se pusieron pesos ficticios 1 en las     //
	// aristas. Los nodos pendientes se colocan en una cola.                                 //
	//---------------------------------------------------------------------------------------//
/*	public static void BFS (GrafoTDA G, int v) {
		ColaTDA Pendientes = new ColaLD();
		Pendientes.InicializarCola();
		ConjuntoTDA Visitados = new ConjuntoLD();
		Visitados.InicializarConjunto();
		Pendientes.Acolar(v);
		Visitados.Agregar(v);
		while(! Pendientes.ColaVacia()) {
			int actual = Pendientes.Primero();
			System.out.print(actual+" ");
			Pendientes.Desacolar();
			ConjuntoTDA Vertices = G.Vertices();
			while (! Vertices.ConjuntoVacio()) {
				int nodo = Vertices.Elegir();
				Vertices.Sacar(nodo);
				if (G.ExisteArista(actual, nodo) && ! Visitados.Pertenece(nodo)) {
					Pendientes.Acolar(nodo);
					Visitados.Agregar(nodo);
				}
			}
		}
		System.out.println("");
	}*/

	public static void mostrarConjunto(ConjuntoTDA A) {
		System.out.print("{");
		ConjuntoTDA B = copiarConjunto(A);
		while(!B.ConjuntoVacio()) {
			int x = B.Elegir();
			System.out.print(x);
			B.Sacar(x);
			if (!B.ConjuntoVacio())
				System.out.print(", ");
		}
		System.out.println("}");
	}
	
	public static ConjuntoTDA copiarConjunto(ConjuntoTDA A) {
		ConjuntoTDA B = new ConjuntoLD();
		ConjuntoTDA Inter = new ConjuntoLD();
		B.InicializarConjunto();
		Inter.InicializarConjunto();	
		while(!A.ConjuntoVacio()) {
			int x = A.Elegir();
			A.Sacar(x);
			B.Agregar(x);
			
			Inter.Agregar(x);
		}
		while(!Inter.ConjuntoVacio()) {
			int x = Inter.Elegir();
			Inter.Sacar(x);
			A.Agregar(x);
		}
		return B;
	}

	public static int contarElementos(ConjuntoTDA A) {
		ConjuntoTDA B = copiarConjunto(A);
		int cont = 0;
		while (!B.ConjuntoVacio()) {
			int x = B.Elegir();
			B.Sacar(x);
			cont++;
		}
		return cont;
	}

	public static void main(String[] args) {
		GrafoTDA G = new GrafoLA();
		G.InicializarGrafo();
		G.AgregarVertice(1);
		G.AgregarVertice(2);
		G.AgregarVertice(3);
		G.AgregarVertice(4);
		G.AgregarVertice(5);
		G.AgregarVertice(6);
		G.AgregarVertice(7);
		G.AgregarVertice(8);
		G.AgregarArista(1,2,1);
		G.AgregarArista(1,3,1);
		G.AgregarArista(2,3,1);
		G.AgregarArista(2,4,1);
		G.AgregarArista(2,5,1);
		G.AgregarArista(3,1,1);
		G.AgregarArista(3,5,1);
		G.AgregarArista(3,6,1);
		G.AgregarArista(3,7,1);
		G.AgregarArista(4,8,1);
		G.AgregarArista(5,6,1);	
		G.AgregarArista(5,8,1);
		G.AgregarArista(6,7,1);
		G.AgregarArista(7,3,1);
		G.AgregarArista(8,6,1);
		mostrarGrafo(G);
//		DFS(G, 1);
//		BFS(G, 1);
	}
}

