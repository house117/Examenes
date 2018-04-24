/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author House
 */
public class NumeroControlNoExisteException extends Exception{
    public NumeroControlNoExisteException() {
		
	}
	public NumeroControlNoExisteException(String msg) {
		super(msg);
	}
}
