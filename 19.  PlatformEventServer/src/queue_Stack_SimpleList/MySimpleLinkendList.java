package queue_Stack_SimpleList;

import java.util.Comparator;
import java.util.Iterator;

import exception.MyException;

/**
 * Clase que permite crear una lista simplemete enlazada
 * @author Dario Baron
 *
 * @param <T>
 */
public class MySimpleLinkendList <T> implements Iterable<T>{
	private static final String NO_MORE_DATA = "No hay datos en la lista";
	private static final String ERROR_MESSAGE_NO_DATA_FOUND = "No se encontro el dato";
	private MyNode<T> head;
	private Comparator<T> comparator;
	
	public MySimpleLinkendList() {
		this.head = null;
	}
	
	public MySimpleLinkendList(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	/**
	 * nos permite ver si la lista esta vacia
	 * @return verdadero si esta vacio, de lo contrario falso
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Permite agregar al inicio de la lista simple
	 * @param info informacion que se va ha agregar
	 */
	public void addToHead(T info) {
		if(head != null) {
			head = new MyNode<>(info, head);
		}else {
			head = new MyNode<>(info);
		}
	}

	/**
	 * Permite Agregar al final de la lista
	 * @param info informacion que se va ha guardar
	 */
	public void addToTail(T info) {
		if(head != null) {
			MyNode<T> last = head;
			while(last.next != null) {
				last = last.next;
			}
			last.next = new MyNode<>(info);
		}else {
			head = new MyNode<>(info);
		}
	}
	/**
	 * Permite agregar un dato despues del dato de referencia.
	 * si no existe una cabeza, lo inserta en la cabeza
	 * @param infoReference dato de refrencia, luego de este se agregara el dato
	 * @param info dato que se desea agregar
	 */
	public void addToMedium(T infoReference, T info) throws MyException{
		if(head != null) {
			MyNode<T> nodeInfo = fildInfo(infoReference);
			if(nodeInfo != null) {
				nodeInfo.next = new MyNode<>(info, nodeInfo.next);
			}else {
				throw new MyException(ERROR_MESSAGE_NO_DATA_FOUND);
			}
		}else {
			head = new MyNode<>(info);
		}
	}

	/**
	 * Busca un dato en la lista 
	 * @param info dato que desea buscar
	 * @return retorna el nodo que contien el dato
	 */
	private MyNode<T> fildInfo(T info){
		MyNode<T> cursor = head;
		while(cursor != null) {
			if(comparator.compare(cursor.infoNode, info) == 0) {
				return cursor;
			}else {
				cursor = cursor.next;
			}
		}
		return cursor;
	}

	/**
	 * Permite ver si una informacion existe dentro de la lista
	 * @param info info que se dea buscar
	 * @return true si se encuentra, de locontrario false
	 */
	public boolean isExist(T info) {
		MyNode<T> exist = fildInfo(info);
		if(exist != null && comparator.compare(info, exist.infoNode) == 0) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * permite optener el tama�o de la lista 
	 * @return el tama�o de la lista
	 */
	public int sizeList() {
		int count = 0;
		MyNode<T> iterator = head;
		while(iterator != null) {
			count++;
			iterator = iterator.next;
		}
		return count;
	}
	
	/**
	 * Borra un dato de la lista
	 * @param info dato a borrar
	 */
	public void deleat(T info) {
		if(head != null && info != head.infoNode) {
			deleatToInfo(info);
		}else {
			deleatToHead();
		}
	}
	
	/**
	 * Borra el dato inicial de la lista
	 */
	public void deleatToHead() {
		if(head != null) {
			head = head.next;
		}
	}

	/**
	 * Borra el dato que se ingres por parametro
	 * @param info informacion que se desea borrar
	 */
	private void deleatToInfo(T info) {
		if(! info.equals(head.infoNode)) {
			MyNode<T> cursor = head;
			MyNode<T> previus = head;
			while(cursor != null) {
				if(comparator.compare(cursor.infoNode, info) == 0) {
					if(cursor.next != null) {
						previus.next = cursor.next;
					}else {
						previus.next = null;
					}
					return;
				}
				previus = cursor;
				cursor = cursor.next;
			}			
		}
	}

	/**
	 * Permite visualizar la lista
	 * @return retorna la lista
	 */
	public String showList() {
		String list = "";
		MyNode<T> aux = head;
		while(aux != null) {
			list += aux.infoNode + "---> ";
			aux = aux.next;
		}
		return list;
	}

	/**
	 * optiene el ultimo dato de la lista
	 * @return el ultimo dato de la lista
	 */
	public T getDatalast() throws MyException{
		if(head != null) {
			MyNode<T> aux = head;
			while(aux.next != null) {
				aux = aux.next;
			}
			return aux.infoNode;			
		}else {
			throw new MyException(NO_MORE_DATA);
		}
	}

	public MyNode<T> getHead() {
		return head;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			
			private MyNode<T> actual = head;
			
			@Override
			public boolean hasNext() {
				return actual != null;
			}

			@Override
			public T next() {
				T info = actual.infoNode;
				actual = actual.next;
				return info;
			}
		};
	}
}