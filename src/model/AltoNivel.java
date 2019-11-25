package model;

import java.util.Collection;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class AltoNivel extends Medicamento {
	
	private AltoNivel izq;
	private AltoNivel der;
	
	private DoubleProperty costo;
	private IntegerProperty dosis_mg;

	public AltoNivel(String id, String codigo, String nombre,double costo,int dosis) {
		super(id, codigo, nombre);
		this.costo = new SimpleDoubleProperty(costo);
		this.dosis_mg = new SimpleIntegerProperty(dosis);
	}

	public Double getCosto() {
		return costo.get();
	}

	public void setCosto(Double costo) {
		this.costo.set(costo);
	}

	public Integer getDosis_mg() {
		return dosis_mg.get();
	}

	public void setDosis_mg(Integer dosis_mg) {
		this.dosis_mg.set(dosis_mg);
	}
	
	public void insertar(AltoNivel nuevo) {
		 if( compareTo( nuevo ) > 0 )
	        {
	            // Debe agregar el nuevo contacto por el sub�rbol izquierdo
	            if( izq == null )
	                izq = nuevo;
	            else
	                izq.insertar( nuevo );
	        }
	        else
	        {
	            // Debe agregar el nuevo contacto por el sub�rbol derecho
	            if( der == null )
	                der = nuevo;
	            else
	                der.insertar( nuevo );
	        }
	}
	
	
	 /**
     * Retorna una colecci�n con los nombres de todos los medicamentos, ordenados alfab�ticamente en orden ascendente
     * @param acumulado colecci�n donde se van agregando los medicamentos ordenadamente
     */
    public void inorden( Collection<AltoNivel> acumulado )
    {
        // Recorre en inorden el sub�rbol izquierdo
        if( izq != null )
            izq.inorden( acumulado );
        // Incluye en el recorrido el contacto de la ra�z
        acumulado.add((AltoNivel)this);
        // Recorre en inorden el sub�rbol derecho
        if( der != null )
            der.inorden( acumulado );
    }
    
    /**
     * Retorna el n�mero de medicamentos que hay en el �rbol que comienza en este nodo
     * @return n�mero de medicamentos en el �rbol que comienza en este nodo
     */
    public int darPeso( )
    {
        int p1 = ( izq == null ) ? 0 : izq.darPeso( );
        int p2 = ( der == null ) ? 0 : der.darPeso( );
        return 1 + p1 + p2;
    }
    
    /**
     * Retorna el n�mero de hojas que hay en el �rbol que comienza en este nodo
     * @return n�mero de hojas que hay en el �rbol que comienza en este nodo
     */
    public int contarHojas( )
    {
        if( esHoja( ) )
            return 1;
        else
        {
            int h1 = ( izq == null ) ? 0 : izq.contarHojas( );
            int h2 = ( der == null ) ? 0 : der.contarHojas( );
            return h1 + h2;
        }
    }
    
    /**
     * Indica si este nodo es una hoja
     * @return true si este nodo es una hoja y false en caso contrario
     */
    public boolean esHoja( )
    {
        return izq == null && der == null;
    }
    
    /**
     * Elimina un contacto del �rbol que comienza en este nodo.
     * @param unNombre nombre del contacto que se va a eliminar - hay un contacto en el �rbol que se llama unNombre
     * @return el �rbol de contactos despu�s de eliminar el contacto indicado
     */
    public AltoNivel eliminar( String unNombre )
    {
        if( esHoja( ) )
            // Tiene que ser el elemento que estamos buscando
            return null;
        if( super.getNombre().compareToIgnoreCase( unNombre ) == 0 )
        {
            if( izq == null )
                return der;
            if( der == null )
                return izq;
            // Localiza el menor contacto del sub�rbol derecho
            AltoNivel sucesor = der.darMenor( );
            // Elimina del sub�rbol derecho el elemento que acaba de localizar
            der = der.eliminar( sucesor.getNombre( ) );
            // Deja el elemento localizado en la ra�z del �rbol de respuesta
            sucesor.izq = izq;
            sucesor.der = der;
            return sucesor;
        }
        else if( super.getNombre().compareToIgnoreCase( unNombre ) > 0 )
            izq = izq.eliminar( unNombre );
        else
            der = der.eliminar( unNombre );
        return this;
    }
    
    /**
     * Retorna el medicamento que alfab�ticamente corresponde al menor medicamento del �rbol que parte de este nodo
     * @return contacto con menor nombre
     */
    public AltoNivel darMenor( )
    {
        return ( izq == null ) ? this : izq.darMenor( );
    }
	
	 /**
     * Compara este medicamento con otro
     * @param o es el otro medicamento con el que se compara
     * @return -1 si este medicamento es menor al otro, 0 si son iguales y 1 si este contacto es mayor al otro
     */
    public int compareTo( Object o )
    {
        AltoNivel otro = ( AltoNivel )o;
        return super.getNombre().compareToIgnoreCase( otro.getNombre() );
    }
	

}
