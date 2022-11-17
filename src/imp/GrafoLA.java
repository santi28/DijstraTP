package imp;

import api.ConjuntoTDA;
import api.GrafoTDA;

public class GrafoLA implements GrafoTDA {

	class NodoGrafo{
		int nodo;
		NodoGrafo sigNodo;
		NodoArista arista;
	}
	
	class NodoArista{
		int peso;
		NodoGrafo nodoDestino;
		NodoArista sigArista;
	}
	
	NodoGrafo origen;

	@Override
	public void InicializarGrafo() {
		origen = null;
	}

	//------------------------------------------------------------------------------------//
	// Un nuevo nodo del grafo se agrega siempre al inicio.                               //
	// El nuevo nodo no tiene (inicialmente al menos) ninguna arista.                     //
	//------------------------------------------------------------------------------------//
	@Override
	public void AgregarVertice(int x) {
		NodoGrafo nuevo = new NodoGrafo();
		nuevo.nodo = x;
		nuevo.arista = null;
		nuevo.sigNodo = origen;
		origen = nuevo;
	}

	//------------------------------------------------------------------------------------//
	// Para eliminar un vértice, se lo elimina y se recorren todos los nodos para         //
	// eliminar todas las aristas que tengan el vértice por ser eliminado como destino.   //
	//------------------------------------------------------------------------------------//
	@Override
	public void EliminarVertice(int x) {
		if (origen.nodo == x)					// El primer vértice es el que debe eliminarse
			origen = origen.sigNodo;
		NodoGrafo viajero = origen;				// Se define un nodo viajero
		while (viajero != null) {					// El nodo "aux" recorre todos los vértices
			this.EliminarAristaEnNodo(viajero, x);
			if (viajero.sigNodo != null && viajero.sigNodo.nodo == x) {
				viajero.sigNodo = viajero.sigNodo.sigNodo;
			}
			viajero = viajero.sigNodo;
		}
	}

	//------------------------------------------------------------------------------------//
	// Para agregar una arista, se buscan ambos vértices involucrados y se agrega la      //
	// arista con el peso correspondiente. Se inserta la arista en la primera posición de //
	// la lista del nodo inicial.                                                         //
	//------------------------------------------------------------------------------------//
	@Override
	public void AgregarArista(int x, int y, int w) {
		NodoGrafo n1 = Vertice2Nodo(x);
		NodoGrafo n2 = Vertice2Nodo(y);
		NodoArista nuevo = new NodoArista();
		nuevo.peso = w;
		nuevo.nodoDestino = n2;
		nuevo.sigArista = n1.arista;
		n1.arista = nuevo;
	}

	//------------------------------------------------------------------------------------//
	// Para eliminar una arista, se busca el vértice inicial y se elimina la arista de    //
	// su lista de aristas.                                                               //
	//------------------------------------------------------------------------------------//
	@Override
	public void EliminarArista(int x, int y) {
		NodoGrafo nodo = Vertice2Nodo(x);
		EliminarAristaEnNodo(nodo, y);
	}


	//------------------------------------------------------------------------------------//
	// Para devolver el peso de una arista, se busca el vértice inicial y se devuelve el  //
	// peso de la arista correspondiente en su lista de aristas.                          //
	//------------------------------------------------------------------------------------//
	@Override
	public int PesoArista(int x, int y) {
		NodoGrafo nodo = Vertice2Nodo(x);
		NodoArista aux = nodo.arista;
		while (aux.nodoDestino.nodo != y)
			aux = aux.sigArista;
		return aux.peso;
	}

	//------------------------------------------------------------------------------------//
	// Para determinar la existencia de una arista, se la busca en la lista del nodo      //
	// inicial. Si la búsqueda es exitosa, se devuelve true; si no, false.                //
	//------------------------------------------------------------------------------------//
	@Override
	public boolean ExisteArista(int x, int y) {
		NodoGrafo nodo = Vertice2Nodo(x);
		NodoArista aux = nodo.arista;
		while (aux != null && aux.nodoDestino.nodo != y)
			aux = aux.sigArista;
		return (aux != null);					// Sólo es null si no se encontró la arista
	}

	@Override
	public ConjuntoTDA Vertices() {
		ConjuntoTDA CNodos = new ConjuntoLD();
		CNodos.InicializarConjunto();
		NodoGrafo aux = origen;
		while (aux != null) {
			CNodos.Agregar(aux.nodo);
			aux = aux.sigNodo;
		}
		return CNodos;
	}
	
	//------------------------------------------------------------------------------------//
	// Este método privado recibe un nodo y un vértice y elimina en ese nodo la arista    //
	// que tiene como destino el vértice "v". Si no hay ninguna arista apuntando a "v",   //
	// el método termina sin hacer nada.                                                  //
	//------------------------------------------------------------------------------------//
	private void EliminarAristaEnNodo(NodoGrafo nodo, int v) {
		NodoArista viajero = nodo.arista;
		if (viajero != null) {
			if (viajero.nodoDestino.nodo == v) {		// La arista que debe eliminarse es la primera
				nodo.arista = viajero.sigArista;
			} else {								// La arista que debe eliminarse es otra
				while(viajero.sigArista != null && viajero.sigArista.nodoDestino.nodo != v)
					viajero = viajero.sigArista;
				if (viajero.sigArista != null)			// Se encontró la arista
					viajero.sigArista = viajero.sigArista.sigArista;
			}
		}
	}
	
	//------------------------------------------------------------------------------------//
	// Este método privado busca el nodo correspondiente al vértice "x" y lo devuelve     //
	// En caso de que no lo encuentre, devuelve "null".                                   //
	//------------------------------------------------------------------------------------//
	private NodoGrafo Vertice2Nodo(int x) {	// Devuelve el nodo del vértice o null 
		NodoGrafo viajero = origen;
		while(viajero != null && viajero.nodo != x)
			viajero = viajero.sigNodo;
		return viajero;
	}

}
