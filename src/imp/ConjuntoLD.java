package imp;

import api.ConjuntoTDA;

public class ConjuntoLD implements ConjuntoTDA {

	class Nodo {
		int info;
		Nodo sig;
	}
	
	Nodo c;
	
	@Override
	public void InicializarConjunto() {
		c = null;
	}

	@Override
	public void Agregar(int x) {
		if (! this.Pertenece(x)) {
			Nodo nuevo = new Nodo();			// el nodo para el nuevo habitante del conjunto
			nuevo.info = x;						// el valor que se incorpora
			nuevo.sig = c;						// el nodo se coloca al inicio de la lista
			c = nuevo;							// nueva referencia de la lista
		}
	}

	@Override
	public void Sacar(int x) {
		if (c != null) {
			if (c.info == x) {					// es el primer elemento de la lista
				c = c.sig;
			} else {
				Nodo aux = c;					// es cualquier otro; definimos un nodo viajero
				while (aux.sig != null && aux.sig.info != x) {
					aux = aux.sig;
				}
				if (aux.sig != null)			// si encontramos el valor...
					aux.sig = aux.sig.sig;		// ...lo circunvalamos
			}
		}
	}

	@Override
	public boolean ConjuntoVacio() {
		return (c == null);
	}

	@Override
	public boolean Pertenece(int x) {
		Nodo aux = c;					// definimos un nodo viajero para buscar el valor
		while (aux != null && aux.info != x)
			aux = aux.sig;
		return (aux != null);			// si fuera null, llegaría al final sin encontrarlo
	}

	@Override
	public int Elegir() {
		return c.info;
	}
	
}
