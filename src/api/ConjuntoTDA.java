package api;

public interface ConjuntoTDA {
	public void InicializarConjunto();
	public void Agregar(int x);
	public void Sacar(int x);
	public boolean ConjuntoVacio();
	public boolean Pertenece(int x);
	public int Elegir();
}
