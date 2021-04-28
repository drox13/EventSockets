package queue_Stack_SimpleList;

import java.util.ArrayList;
/**
 * @author Dario baron
 *construye una lista de prioridad
 *usando un array de colas
 * @param <T> tipo de dato
 */
public class MyPriorityQueue<T> {
	private ArrayList<MyQueue<T>> listQueue;
	
	/**
	 * @param legthArray numero de prioridades existentes
	 */
	public MyPriorityQueue(int legthArray) {
		listQueue = new ArrayList<>();
		for (int i = 0; i < legthArray; i++) {
			listQueue.add(new MyQueue<>());
		}
	}
	
	/*
	 * determina si la cola esta vacias
	 */
	public boolean isEmpty() {
		for (MyQueue<T> myQueue : listQueue) {
			if(! myQueue.isEmtry()) {
				return false;
			}
		}
		return true;
	}
	/**
	 * agrega un dato a la cola
	 * @param info data para agrgar
	 * @param indexPriority prioridad del dato
	 */
	public void push(T info, int indexPriority) {
		listQueue.get(indexPriority).putToQueue(info);
	}
	
	/**
	 * desencola el dato correspondiente
	 * @return dato desencolado
	 */
	public T poll() {
		for (MyQueue<T> myQueue : listQueue) {
			if(!myQueue.isEmtry()) {
				return myQueue.getToQueue();
			}
		}
		return null;
	}
	
	/**
	 * Premite ver el dato que sigue en la cola sin eliminarlo
	 * @return dato que sigue ne la cola
	 */
	public T peek() {
		for (MyQueue<T> myQueue : listQueue) {
			if(!myQueue.isEmtry()) {
				return myQueue.peek();
			}
		}
		return null;
	}
	
	/**
	 * permite ver la cola
	 * @return cola en formato String
	 */
	public String show() {
		String priorityQueue = "";
		for (MyQueue<T> myQueue : listQueue) {
				priorityQueue += myQueue.showQueue();
		}
		return priorityQueue;
	}
}