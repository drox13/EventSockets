package queue_Stack_SimpleList;

import exception.MyException;

/**
 * Permite crea un lista circular simplemente enlazada
 * @author Dario Baron
 *
 * @param <T> tipo de lato de la lista circular simple
 */
public class MySimplyLinkendCircularList<T> {
	private MyNode<T> first;
	private MyNode<T> last;

	public boolean isEmpty() {
		return first == null;
	}
	/**
	 * Permite agregar 
	 * @param info informacion que deseamos agregar
	 */
	public void add(T info) {
		if(first != null) {
			MyNode<T> newNode = new MyNode<>(info, first);
			last.next = newNode;
			last = newNode;
		}else{
			first = last = new MyNode<>(info);
			first.next = first;
		}
	}

	/**
	 * Remueve un dato de la lista
	 * @param info informacion que desea remover
	 */
	public void remove(T info) throws MyException{
		MyNode<T> cursor = first;
		MyNode<T> previus = last;
		if(cursor != null) {
			do {
				if(info.equals(cursor.infoNode)) {
					if(cursor == first) {
						if(cursor == cursor.next) {
							first = last = null;
							return;
						}else {
							first = cursor.next;
							previus.next = first;						
						}
					}else if(cursor == last) {
						previus.next = first;
						last = previus;
					}else {
						previus.next = cursor.next;
					}
				}
				previus = cursor;
				cursor = cursor.next;
			} while (cursor != first);
		}
	}

	/**
	 * Permite buscar una informacion en la lista
	 * @param info informacion que se desea buscar
	 * @return retorna el nodo que posee dicha informacion
	 */
	private MyNode<T> find(T info) {
		MyNode<T> nodeSearch = first;
		while(nodeSearch != last.next) {
			if(info.equals(nodeSearch.infoNode)) {
				return nodeSearch;
			}else {
				nodeSearch = nodeSearch.next;				
			}
		}
		if(!info.equals(nodeSearch.infoNode)) {
			nodeSearch = null;
		}
		return nodeSearch;
	}

	/**
	 * Permite verificar que un dato existe
	 * @param info informacion que se desea buscar
	 * @return verdadero si el dato existe, de lo contrario false
	 */
	public boolean isExist(T info) {
		return (find(info) != null)? true: false; 
	}
	/**
	 * permite optener el tamaño de la lista
	 * @return tañaño de la lista
	 */
	public int sizeList() {
		int count = 0;
		if(first != null) {
			MyNode<T> iterator = first;
			do {
				count++;
				iterator = iterator.next;
			} while (iterator != first);
		}
		return count;
	}

	/**
	 * Permite visualizar la lista circular
	 */
	public void showList() {
		if(first != null) {
			MyNode<T> iterator = first;
			do {
				System.out.println(iterator);
				iterator = iterator.next;
			} while (iterator != first);
		}
	}

	public MyNode<T> getFirst() {
		return first;
	}

	public MyNode<T> getLast() {
		return last;
	}
}