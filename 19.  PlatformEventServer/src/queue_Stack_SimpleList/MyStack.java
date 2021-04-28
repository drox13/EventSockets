package queue_Stack_SimpleList;

import java.util.Comparator;

import exception.MyException;

/**
 * Clase que permte crea una pila
 * @author Dario Baron
 *
 * @param <T> tipo de informacion que se guardara en la pila
 */
public class MyStack <T>{
	private static final String ERROR_MESSAGE = "la Pila se encuentra vacia, no se pueden recuperar elementos";
	private MyNode<T> head;
	private Comparator<T> comparator;
	
	/**
	 * @param comparator por el que busca un dato
	 */
	public MyStack(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	/**
	 * permite ver si la pila esta vacia
	 * @return verdadero si la pila esta vacis
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Permite apilar
	 * @param info informacion que se desea agregar
	 */
	public void push(T info) {
		if(head != null) {
			head = new MyNode<>(info, head);
		}else {
			head = new MyNode<>(info);
		}
	}
	/**
	 * Permite desapilar
	 * @return dato que se desapilo (retiro)
	 * @throws MyException se lanza si no hay mas datos
	 */
	public T pop() throws MyException {
		if(head != null) {
			MyNode<T> element  = head;
			head = head.next;
			return element.infoNode;
		}
		throw new MyException(ERROR_MESSAGE);
	}
	
	/**
	 * optiene el dato de encima de la pila(sin desapilar)
	 * @return el dato o nulo si esta vacia la pila
	 */
	public T peek() {
		return (head != null)? head.infoNode : null;
	}
	
	/**
	 * Permite ver si existe un dato dentro de la pila
	 * @param info informacion a buscar
	 * @return retorna verdadero si lo encuentra, de lo contrario falso
	 */
	public boolean isExist(T info) {
		MyNode<T> reference = head;
		while(reference != null) {
			if(comparator.compare(reference.infoNode, info) == 0) {
				return true;
			}
			reference = reference.next;
		}
		return false;
	}
	
	/**
	 * Permite determinar el tama�o de la pila
	 * @return
	 */
	public int sizeStack() {
		int count = 0;
		MyNode<T> aux = head;
		while(aux != null) {
			count++;
			aux = aux.next;
		}
		return count;
	}
	
	/**
	 * Permite ver toda la pila
	 * @return 
	 */
	public String showStack() {
		String stack = "";
		MyNode<T> aux = this.head;
		while(aux != null) {
			stack += aux.infoNode + "\n"; 
			aux = aux.next;
		}
		return stack;
	}
}
