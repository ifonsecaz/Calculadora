package calculadora;


import java.util.EmptyStackException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @param <T> -
 */
public class PilaArreglo<T> implements PilaADT<T> {
    private T[] pila;
    private int tope;
    private final int MAX = 100;

    public PilaArreglo() {
        pila = (T[])new Object[MAX];
        tope = -1;
    }
    
    public PilaArreglo(int max){
        pila = (T[])new Object[max];
        tope = -1;
    }

    @Override
    public void push(T dato) {
        if(tope==pila.length-1){
            expand();
        }
        tope++;
        pila[tope]=dato;
    }

    @Override
    public T pop() {
        if(this.isEmpty()){
            throw new EmptyStackException();
        }
        T resul = pila[tope];
        pila[tope]=null;
        tope--;
        return resul;
    }

    @Override
    public T peek() {
        if(this.isEmpty()){
            throw new EmptyStackException();
        }
        return pila[tope];
    }

    @Override
    public boolean isEmpty() {
        return tope==-1;
    }
    
    private void expand() {
        T[] nuevo = (T[])new Object[pila.length*2];
        for(int i=0;i<=tope;i++){
            nuevo[i]=pila[i];
        }
        pila=nuevo;
    }
}
